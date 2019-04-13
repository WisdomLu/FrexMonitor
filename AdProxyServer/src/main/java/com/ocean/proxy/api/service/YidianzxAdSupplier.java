package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.yidianzx.Base64Basic;
import com.ocean.persist.api.proxy.yidianzx.Base64Spec;
import com.ocean.persist.api.proxy.yidianzx.YidianzxApp;
import com.ocean.persist.api.proxy.yidianzx.YidianzxBanner;
import com.ocean.persist.api.proxy.yidianzx.YidianzxBid;
import com.ocean.persist.api.proxy.yidianzx.YidianzxDevice;
import com.ocean.persist.api.proxy.yidianzx.YidianzxGeo;
import com.ocean.persist.api.proxy.yidianzx.YidianzxImp;
import com.ocean.persist.api.proxy.yidianzx.YidianzxReq;
import com.ocean.persist.api.proxy.yidianzx.YidianzxResp;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYidianzxTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;


@Component(value = "YidianzxAdSupplier")
public class YidianzxAdSupplier extends AbstractAsynAdSupplier {
	private static final String YD_PRICE="\\$\\{PRICE\\}";
	private static final int SEC_TYPE_KEY=12;
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		YidianzxReq params = parseParams(attribute);
		// 调用API
		YidianzxResp response = (YidianzxResp) invoke(params, YidianzxResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),
				"joinDSP:yidianzx");
		// 解析结果
		return parseResult(response,attribute.getDspPosition().getText2());
	}

	public AdRecomReply parseResult(YidianzxResp resp,String key) throws AdPullException {
		if (resp == null) {
			return null;
		}
		if (CollectionUtils.isEmpty(resp.getSeatBid())) {
			return  null;
			
		}

		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(resp.getSeatBid().get(0).getBid(),key));
		return adresp;
	}
	private List<AdContent>  dealAds(List<YidianzxBid> ads,String key) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(YidianzxBid ad:ads){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	      //***********************END 常规设置*************
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	       
	        if(ad.getCtype()==2){
	        	acType=ACTION_APP;
	        }
	        if(CollectionUtils.isNotEmpty(ad.getAurl())){
	        	for(String url:ad.getAurl()){
	        		AdImg img=new AdImg();
		            img.setSrc(url);
		            imgs.add(img);
	        	}
		        
	        }
	        if(StringUtils.isNotEmpty(ad.getCurl())){
		        content.setLinkurl(ad.getCurl());
				action.setLinkurl(ad.getCurl());
	        }else  if(StringUtils.isNotEmpty(ad.getDurl())){
	            content.setLinkurl(ad.getDurl());
	    		action.setLinkurl(ad.getDurl());
	        }
	        List<String> showReport=new ArrayList<String>();
	        if(StringUtils.isNotEmpty(ad.getNurl())){
	        	String format_url=format(ad.getNurl(),key,ad.getId(),ad.getImpid(),ad.getAdid()
	        			          ,ad.getCrid(),String.valueOf(ad.getPrice()),String.valueOf(System.currentTimeMillis()));
	        	showReport.add(format_url);
	        }
	        if(CollectionUtils.isNotEmpty(ad.getMurl())){
	        	showReport.addAll(ad.getMurl());
	        }
	        
	        
	    	map.put(SHOW, showReport);
	        
	    	if(CollectionUtils.isNotEmpty(ad.getCmurl())){
	    		map.put(CLICK, ad.getCmurl());
	    	}
	    	if(CollectionUtils.isNotEmpty(ad.getDmurl())){
	    		map.put(DOWNLOAD, ad.getDmurl());
	    	}
	    	if(StringUtils.isNotEmpty(ad.getDpkgname())){
	    		action.setCpPackage(ad.getDpkgname());
	    	}
			content.setThirdReportLinks(map);
			//img
			content.setImglist(imgs);
		    action.setType(acType); 
			content.setMarketTitle(marketTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	private  String format(String str,String key,String id,String impid,String adid,String crid,String price,String t) throws  AdPullException{
	    StringBuilder sb=new StringBuilder();
	    
		sb.append("id=").append(id).append("&impid=").append(impid)
		  .append("&adid=").append(adid).append("&crid=").append(crid)
		  .append("&price=").append(price).append("&t=").append(t);
		
		logger.debug("yidianzx data  befor encode ck:{}",sb.toString());
		//hmacsha1
	    String ck=Base64Basic.encodeWithHmacSha1(key, sb.toString());
	    
	    logger.debug("yidianzx data  after hmacsha1:{}",ck);
	    
	    //base64
	    sb.append("&ck=").append(ck);
	    String result=Base64Spec.encode(sb.toString(),SEC_TYPE_KEY);
		logger.debug("yidianzx data  after Base64:{}",result);
		String format= str.replaceAll(YD_PRICE,result);
	
		return format;
	}

/*	public static void main(String[] args){

	    String text = "id=89ad33ce-b1c9-48d0-93d3-4f86d291bce1&impid=504&adid=600005966&crid=600005865&price=280&t=1536913417343";
	    String key="Gp31XM48j977mRvsu2r7ZXlcScbdB6fB";
	    String ck=Base64Basic.encodeWithHmacSha1(key, text);
	    
	    System.out.println("ck:"+ck);
	    String result=Base64Spec.encode(text+"&ck="+ck,SEC_TYPE_KEY);
	    
        System.out.println("result:"+result);
	}*/
	public YidianzxReq parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText1())||StringUtils.isEmpty(posInfo.getText2())) {
			throw new AdPullException("rtb from or key is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		YidianzxReq param = new YidianzxReq();
		param.setId(adreq.getHash());
		
		//imp
		YidianzxImp imp=new YidianzxImp();
		//imp.setId(String.valueOf(attr.getAdSpaceId()));
		imp.setId("1");//对方要求设置1
		
		YidianzxBanner banner=new YidianzxBanner();
		banner.setTemplate(Arrays.asList("BIG_IMAGE","THREE_IMAGE"));
		
		int ctype=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.YIDIANZX_CTYPE,1);
		banner.setCtype(ctype);
		banner.setW(attr.getSpaceWidth());
		banner.setH(attr.getSpaceHeight());
		imp.setBanner(banner);
		param.setImp(Collections.singletonList(imp));
		
		//device
		YidianzxDevice device=new YidianzxDevice();
		device.setUa(user.getUa());
		device.setIp(user.getClient_ip());
		device.setDevicetype(1);
		String os = user.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs("android");
			device.setDidmd5(DigestUtils.md5Hex(user.getImei()));
			device.setDidsha1(DigestUtils.sha1Hex(user.getImei()));
			
			device.setDpidmd5(DigestUtils.md5Hex(user.getAdid()));
			device.setDpidsha1(DigestUtils.sha1Hex(user.getAdid()));
			

		}else{
			device.setOs("IOS");
			device.setDidmd5(DigestUtils.md5Hex(user.getIdfa()));
			device.setDidsha1(DigestUtils.sha1Hex(user.getIdfa()));
			
		}
		device.setMacmd5(DigestUtils.md5Hex(user.getMac()));
		device.setMacsha1(DigestUtils.sha1Hex(user.getMac()));
		
		device.setOsv(user.getOsversion());
		device.setW(user.getDevice_width());
		device.setH(user.getDevice_height());
		device.setMake(user.getBrand_name());
		device.setModel(user.getPhonemodel());
		device.setConnectiontype(this.convertConnType(adreq.getNet()));
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			YidianzxGeo geo=new YidianzxGeo();
			geo.setCity(user.getCity_name());
			geo.setLat(Double.parseDouble(user.getLat()));
			geo.setLon(Double.parseDouble(user.getLon()));
			geo.setLalotype(1);
			device.setGeo(geo);
		}
		param.setDevice(device);
		
		//app
		YidianzxApp app=new YidianzxApp();
		app.setId(adreq.getChannel());
		app.setName(adreq.getApp());
		app.setVer(adreq.getVersion());
		param.setApp(app);
		
		param.setAt(1);
		int test=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.YIDIANZX_TEST,0);	
		param.setTest(test==1?true:false);
		
		param.setFrom(posInfo.getText1());
		return param;
	}

	
	private Integer convertConnType(String net) {
		if ("wifi".equals(net)) {
			return 2;
		}
		if ("2g".equals(net)) {
			return 4;
		}
		if ("3g".equals(net)) {
			return 5;
		}
		if ("4g".equals(net)) {
			return 6;
		}
		
		return 0;
	}

	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyYidianzxTask task = new AdProxyYidianzxTask();
		YidianzxReq param = (YidianzxReq) params;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("x-caifeng-rtb-version", "1.0");
		headers.put("x-caifeng-rtb-from", param.getFrom());
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YIDIANZX_URL));
		task.setHeaders(headers);
		return task;
	}
}
