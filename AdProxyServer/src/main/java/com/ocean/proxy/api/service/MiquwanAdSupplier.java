package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.miquwan.MiquwanAd;
import com.ocean.persist.api.proxy.miquwan.MiquwanAdPullResponse;
import com.ocean.persist.api.proxy.miquwan.MiquwanAdpullParams;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyMiquwanTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
@Component(value="miquwanAdSupplier")
public class MiquwanAdSupplier    extends AbstractAsynAdSupplier{
	protected static final Map<String, Integer> speedmobiles = new HashMap<String, Integer>(4);

	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	static{
		speedmobiles.put("CMCC", 1);
		speedmobiles.put("CTCC", 2);
		speedmobiles.put("CUCC", 3);
		
		conn.put(NETWORK_2G, 3);
		conn.put(NETWORK_3G, 4);
		conn.put(NETWORK_4G, 5);
		conn.put(NETWORK_WIFI, 1);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		MiquwanAdpullParams params = parseParams(attribute);
		// 调用API
		MiquwanAdPullResponse response = (MiquwanAdPullResponse) invoke(params,MiquwanAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:speed");
		// 解析结果
		return parseResult(response);
	}
	private MiquwanAdpullParams parseParams(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		AdUserInfo user=adreq.getUserinfo();
		MiquwanAdpullParams param=new MiquwanAdpullParams();
		param.setVersion("1");
		param.setC_type(1);//1-api 2-sdk 3-wap
		//广告位信息
		param.setMid(positionInfo.getPos());//广告位id
		param.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
		String osCode = user.getOs();
		String  osType="ANDROID";
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	osType="IOS";
	        param.setIdfa(user.getIdfa());
	    }
       

		param.setOs(osType);
		if(StringUtils.isNotEmpty(user.getMac())&&!user.getMac().equals(user.getImei())){
			param.setMac(user.getMac());
		}
		param.setOsv(this.convOSVersion(user.getOsversion()));
		if(StringUtils.isEmpty(adreq.getNet())){
			param.setNetworktype(0);
		}else{
			Integer net=conn.get(adreq.getNet());
			param.setNetworktype(net==null?0:net);
		}
		param.setRemoteip(user.getClient_ip());
		param.setMake(user.getBrand_name());
		param.setBrand(user.getBrand_name());
		param.setModel(user.getPhonemodel());
		param.setDevicetype("1");
		param.setImei(user.getImei());
		if(StringUtils.isNotEmpty(user.getImsi())){
			param.setImsi(user.getImsi());
		}

    	if(StringUtils.isNotEmpty(user.getAdid())){
    		param.setAndroidid(user.getAdid());
    	}
		param.setWidth(user.getDevice_width());
		param.setHeight(user.getDevice_height());
		//param.setOrientation(0);
		//param.setAppid(appid);
		param.setAppame(adreq.getApp());

		if(StringUtils.isEmpty(user.getMobile())){
			param.setOperator(0);
		}else{
			Integer carr=speedmobiles.get(user.getMobile());
			param.setOperator(carr==null?0:carr);
		}

		return param;
		
	}
	private AdRecomReply parseResult(MiquwanAdPullResponse response)
			throws AdPullException {
		if(response == null||response.getAdInfo()==null){
			return null;
		}
		if(!"0".equals(response.getRet())){
			
			throw new AdPullException("ad request error,return error code:"+response.getRet());
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		//返回结果解析
		MiquwanAd adResponse=response.getAdInfo();
		
		//------------------BEGIN 常规设置-------------------
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		action.setType(ACTION_WEB);  
	
		//------------------END 常规设置---------------------
       
		if(1== adResponse.getAc_type()||4==adResponse.getAc_type()){
			action.setType(ACTION_APP); 
		}
		
		//所有广告都是html片段
		if("4".equals(adResponse.getCreative_type())){
			content.setIsHtmlAd(true);
			content.setHtmlSnippet(adResponse.getHtmlStr());
		}else{
			 //设置落地页
			String url=adResponse.getClick_url();
			if(adResponse.getClick_position()==1&&adResponse.getAc_type()!=4){
				url=formatUrl(url);
			}
			content.setLinkurl(url);
			action.setLinkurl(url);
			
			
			List<AdImg> imgs = new ArrayList<AdImg>();
			if(StringUtils.isNotEmpty(adResponse.getImg_url())){
				AdImg img = new AdImg();
				img.setSrc(adResponse.getImg_url());
				imgs.add(img);
			}
			
			if(StringUtils.isNotEmpty(adResponse.getTitle())){
				title=adResponse.getTitle();
			}
			
			// 图片地址
			content.setImglist(imgs);
		}

		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> clickL=new ArrayList<String>();
		List<String> viewL=new ArrayList<String>();
		List<String> download_start=new ArrayList<String>();
		List<String> download=new ArrayList<String>();
		List<String> install=new ArrayList<String>();
	
		if(!CollectionUtils.isEmpty(adResponse.getImpress_notice_urls())){
			viewL.addAll(adResponse.getImpress_notice_urls());
			map.put(SHOW, viewL);
		}
		if(!CollectionUtils.isEmpty(adResponse.getClick_notice_urls())){
			clickL.addAll(adResponse.getClick_notice_urls());
			if(adResponse.getAc_type()==4&&adResponse.getClick_position()==1){
				String url=formatUrl(clickL.get(0));
				
				
				//下载地址需要解析提取
				content.setLinkurl(url);
				action.setLinkurl(url);
				action.setLinkurl_type(1);
				
				//clickL.set(0, url);
				clickL.remove(0);
			}
			
			
		}
		if(!CollectionUtils.isEmpty(adResponse.getDownload_start_notice_urls())){
			download_start.addAll(adResponse.getDownload_start_notice_urls());
			if(adResponse.getAc_type()==4){
				String url=appendClickId(download_start.get(0));
				download_start.set(0, url);
				
			}
			clickL.addAll(download_start);
		}
		map.put(CLICK, clickL);
		if(CollectionUtils.isEmpty(adResponse.getDownload_notice_urls())){
			download.addAll(adResponse.getDownload_notice_urls());
			if(adResponse.getAc_type()==4){
				String url=appendClickId(download.get(0));
				download.set(0, url);
			}
			map.put(DOWNLOAD, download);
		}
		if(CollectionUtils.isEmpty(adResponse.getInstall_notice_urls())){
			install.addAll(adResponse.getInstall_notice_urls());
			if(adResponse.getAc_type()==4){
				String url=appendClickId(install.get(0));
				install.set(0, url);
			}
			map.put(INSTALL, install);
		}
		content.setThirdReportLinks(map);
		
		// 类型

 	
		action.setGuideTitle(title);
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));

		recomReply.setAd_contents(Collections.singletonList(content));
		return recomReply;
	}
	private String appendClickId(String url){
	
		if(StringUtils.isEmpty(url)){
			return "";
		}
		StringBuilder sb=new StringBuilder(url);
		String tem=url.endsWith("clickid=")?"":"clickid=";
		sb.append(tem).append("%%CLICKID%%");
		return sb.toString();

	}
	private String formatUrl(String url){
		
		if(StringUtils.isEmpty(url)){
			return "";
		}
		StringBuilder sb=new StringBuilder(url);
		String tem=url.endsWith("s=")?"":"&s=";
		sb.append(tem).append("{\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}");
		return sb.toString();
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyMiquwanTask task=new AdProxyMiquwanTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		MiquwanAdpullParams param = (MiquwanAdpullParams) params;
		task.setHeaders(headers);
		

		
		//logger.info("joinDSP:speed {} request param:{}",hashCode,UniversalUtils.replaceBlank(param.toString()));
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.SPEED_URL));
		
		return task;
	}
}
