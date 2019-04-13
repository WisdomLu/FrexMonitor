package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.huzhong.HuzhongAd;
import com.ocean.persist.api.proxy.huzhong.HuzhongAdDevice;
import com.ocean.persist.api.proxy.huzhong.HuzhongAdPullParams;
import com.ocean.persist.api.proxy.huzhong.HuzhongAdPullResponse;
import com.ocean.persist.api.proxy.huzhong.HuzhongAdPuller;
import com.ocean.persist.api.proxy.huzhong.HuzhongImp;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
@Component(value="huzhongAdSupplier")
public class HuzhongAdSupplier    extends AbstractAdSupplier{
    @Autowired 
    private HuzhongAdPuller huzhongAdPuller;
	protected static final Map<String, Integer> hzMobiles = new HashMap<String, Integer>(3);
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();

	static{

		hzMobiles.put("CMCC", 1);
		hzMobiles.put("CUCC", 2);
		hzMobiles.put("CTCC", 3);
		conn.put(NETWORK_2G, 2);
		conn.put(NETWORK_3G, 3);
		conn.put(NETWORK_4G, 4);
		conn.put(NETWORK_WIFI, 1);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub

		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		HuzhongAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		HuzhongAdPullResponse response = (HuzhongAdPullResponse)huzhongAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response,adreq.getUserAdSpaceAttri().getSpaceWidth(),adreq.getUserAdSpaceAttri().getSpaceHeight());
	}
	private HuzhongAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("app pkg name is empty!");
		}
		AdUserInfo user=adreq.getUserinfo();
		HuzhongAdPullParams param=new HuzhongAdPullParams();
		param.setIp(user.getClient_ip());
		try {
			param.setUa(URLEncoder.encode(user.getUa(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		param.setSi(positionInfo.getPos());
		param.setReqid(UUID.randomUUID().toString().replaceAll("-", ""));
		param.setBf(positionInfo.getSettlementPrice());
		//device
		HuzhongAdDevice device=new HuzhongAdDevice();

		String osCode = user.getOs();
		// 如果是ios
	    int os = 1;
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
			device.setUdid(user.getIdfa());
			device.setIdentify_type("idfa");
			
			os=2;
	    }else if(OS_ANDROID.equals(osCode)){
			device.setUdid(user.getImei());
			device.setIdentify_type("imei");
			device.setAndroid_id(user.getAdid());
			os=1;
		}
		if(StringUtils.isNotEmpty(user.getMac())){
			device.setMac(user.getMac());
		}
		device.setVendor(user.getBrand_name());
		try {
			device.setModel(URLEncoder.encode(user.getPhonemodel(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		device.setOs(os);
		device.setOs_version(this.convOSVersion(user.getOsversion()));
		Integer network=conn.get(adreq.getNet());
		device.setNetwork(network==null?0:network);
		
		Integer op=hzMobiles.get(user.getMobile());
		device.setOperator(op==null?0:op);
		device.setWidth(user.getDevice_width());
		device.setHeight(user.getDevice_height());
		String dj = JsonUtils.toJson(device);
		//param.setDevice(dj);
		try {
			param.setDevice(URLEncoder.encode(dj,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		param.setApp_version(adreq.getVersion());
		param.setPackage_name(positionInfo.getText3());
		param.setMimes("img");
		param.setV("1.4.1");
		
		return param;
	}
	private AdRecomReply parseResult(HuzhongAdPullResponse response,int width,int height)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAd())){
			return null;
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response,width,height));
		return reply;
	}
	private List<AdContent>  dealAds(HuzhongAdPullResponse resp,int width,int height){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(HuzhongAd ad:resp.getAd()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String markTitle= defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	      
	        if(ad.getAction()==0){
	        	acType=ACTION_APP;
	        }
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	        }
	        if(StringUtils.isNotEmpty(ad.getDesc())){
	        	markTitle=ad.getDesc();
	        }
	        if(StringUtils.isNotEmpty(ad.getUrl())){
		        content.setLinkurl(urlFmt(ad.getUrl(),width,height));
		        action.setLinkurl(urlFmt(ad.getUrl(),width,height));
	        }

	        
	        if(StringUtils.isNotEmpty(ad.getHtml())){
	        	content.setHtmlSnippet(ad.getHtml());
	        	content.setIsHtmlAd(true);
	        }
			//img
			List<AdImg> imgs = new ArrayList<AdImg>();
			if(StringUtils.isNotEmpty(ad.getSrc())){
				AdImg img=new AdImg();
				img.setSrc(ad.getSrc());
				imgs.add(img);
			}
			if(CollectionUtils.isNotEmpty(ad.getExt_urls())){
				for(String url:ad.getExt_urls()){
					AdImg img=new AdImg();
					img.setSrc(url);
					imgs.add(img);
				}
			}
			if(ad.getApp()!=null){
				if(StringUtils.isNotEmpty(ad.getApp().getPackage_name())){
					action.setCpPackage(ad.getApp().getPackage_name());
				}
				if(StringUtils.isNotEmpty(ad.getApp().getIcon_url())){
					action.setCpIcon(ad.getApp().getIcon_url());
				}
				
			}
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			List<String> clickL=new ArrayList<String>();

/*			if(StringUtils.isNotEmpty(ad.getDp_url())&&CollectionUtils.isNotEmpty(ad.getDp_clk())){//deeplink
				action.setActiveUri(ad.getDp_url());
				if(CollectionUtils.isNotEmpty(ad.getDp_clk())){
					for(String url:ad.getDp_clk()){
						clickL.add(this.urlFmt(url,width,height));
					}
					
				}
			}else */if(CollectionUtils.isNotEmpty(ad.getClk())){
				for(String  url:ad.getClk()){
					clickL.add(this.urlFmt(url,width,height));
				}
			    
			}
			map.put(CLICK, clickL)	;
			if(ad.getImp()!=null&&CollectionUtils.isNotEmpty(ad.getImp().getType())){
				map.put(SHOW, ad.getImp().getType());
			}
			List<String> downL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getDownload_urls())){
				downL.addAll(ad.getDownload_urls());
			
			}
			 
			if(CollectionUtils.isNotEmpty(ad.getInstall_urls())){
				downL.addAll(ad.getInstall_urls());
				
				
			}
			map.put(DOWNLOAD, downL);
			
		
			if(CollectionUtils.isNotEmpty(ad.getInstalled_urls())){
				map.put(INSTALL, ad.getInstalled_urls());
				
			}

			
			content.setImglist(imgs);
			content.setThirdReportLinks(map);
		
		    action.setType(acType); 
		    action.setGuideTitle(title);
			content.setMarketTitle(markTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
		
	}
	private String urlFmt(String url,int width,int height){
		String tmp=url.replaceAll("__AZMX__","%%DOWNX%%").replaceAll("__AZMY__", "%%DOWNY%%")
				.replaceAll("__AZCX__", "%%UPX%%").replaceAll("__AZCY__", "%%UPY%%")
				.replaceAll("__WIDTH__", String.valueOf(width)).replaceAll("__HEIGHT__", String.valueOf(height));
		return tmp;
	}
	public HuzhongAdPuller getHuzhongAdPuller() {
		return huzhongAdPuller;
	}
	public void setHuzhongAdPuller(HuzhongAdPuller huzhongAdPuller) {
		this.huzhongAdPuller = huzhongAdPuller;
	}

}
