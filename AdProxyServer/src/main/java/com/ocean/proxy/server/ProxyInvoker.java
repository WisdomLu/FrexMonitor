package com.ocean.proxy.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;

import com.inveno.util.CollectionUtils;
import com.ocean.bid.service.AdBidService;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.BaseAdSupplier;
import com.ocean.proxy.api.helper.AdSupplierCfgLoader;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

public class ProxyInvoker extends AbstractProxyInvoker{
	private  final Logger logger = MyLogManager.getLogger();
	public  final Logger monitorLogger = MyLogManager.getMonitor();
	public void ping() throws TException {
		
	}

	public AdRecomReply poll(String uid, AdRecomReq adreq) throws TException {
		long ts = System.currentTimeMillis();
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		String joinDSP=adreq.getUserAdSpaceAttri().getJoinDspName();
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);


		
		AdRecomReply recomReply =null;
		//获取三方广告位信息
		AdSupplierCfgLoader cfgLoader=AdSupplierCfgLoader.buildLoader();
		DSPPosition dspPosition=null;
		int empty=0;
		try {
			dspPosition = cfgLoader.getPosition(String.valueOf(JoinDSPEmu.getJoinDspByName(attri.getJoinDspName()).getValue()),String.valueOf(attri.getAdSpaceId()),attri.getJoinPoseId());
			if(dspPosition == null){
				logger.error("no space mapping info,space id:{}", attri.getAdSpaceId());
				throw new AdPullException("no space mapping info,space id:" + attri.getAdSpaceId());
			}
			
			
			adreq.setOgin_name(uid);
			logger.info("requestId:{},joinDSP:{},ad space id:{},uid:{},ad request and req info:{}",adreq.getHash(),joinDSP,attri.getAdSpaceId(),uid,adreq.toString());
			recomReply = invoke(adreq,dspPosition);

		}
		catch (AdPullException e) {
			empty=3;
			logger.error("requestId:{},ad rquest error(AdPullException),joinDSP:{},ad space id:{},uid:{},error Code:{},error Msg:{}",adreq.getHash(),joinDSP,attri.getAdSpaceId(),uid,e.getCode(), e.getMsg());
			recomReply= noad();
			// 暂定 0表示没有广告
			recomReply.setStatus(ErrorCode.INTER_ERROR);
			
		}catch (BusinessException e) {
			empty=3;
			logger.error("requestId:{},ad rquest error(BusinessException),joinDSP:{},ad space id:{},uid:{},error Code:{},error Msg:{}",adreq.getHash() ,joinDSP,attri.getAdSpaceId(),uid,e.getErrorCode(), e.getMsg());
			recomReply= noad();
			// 暂定 0表示没有广告
			recomReply.setStatus(ErrorCode.INTER_ERROR);
		
		}catch(Exception e){
			empty=3;
			logger.error("requestId:{},ad rquest error(Exception),joinDSP:{},ad space id:{},uid:{},error Msg:{}",adreq.getHash() ,joinDSP,attri.getAdSpaceId(),uid, e,e);
			//e.printStackTrace();
			recomReply= noad();
			// 暂定 0表示没有广告
			recomReply.setStatus(ErrorCode.INTER_ERROR);
			
		}finally{
			
			// 没有广告
			if(recomReply == null||recomReply.getStatus()==ErrorCode.INTER_ERROR){
				empty=1;
				recomReply= noad();
				logger.info("requestId:{},joinDSP:{},ad space id:{},uid:{},ad reply-empty and reply info: {}",adreq.getHash(),joinDSP,attri.getAdSpaceId(),uid,recomReply.toString());
				
			}else{
				String content=isLogOpen==1?recomReply.toString():PersistConstants.OMIT_CONTENT;
				logger.info("requestId:{},joinDSP:{},ad space id:{},uid:{},ad reply-filled and reply info:{}",adreq.getHash(),joinDSP,attri.getAdSpaceId(),uid,content);
				
			}
			//>>>>>>>>>>>>>>>>>>>>>>>>>请求时间日志<<<<<<<<<<<<<<<<<<<<<<<<<<<
			long ls = System.currentTimeMillis();
			logger.info("requestId:{},joinDSP:{},ad space id:{},uid:{},request time cost:{} ms",adreq.getHash(),joinDSP,attri.getAdSpaceId(),uid,ls - ts);
			int timeOut=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.HTTP_TIME_OUT, 1000);
			if((ls - ts)>=timeOut){
				empty=2;
			}
			
			//输出到logstash的日志
			monitorLogger.info("ad|proxy|{}|{}|{}|{}|{}",attri.getAdSpaceId(),dspPosition.getId(),uid,empty,ls - ts);
			
		}

		return recomReply;
	}
	private AdRecomReply invoke(AdRecomReq adreq,DSPPosition dspPosition)throws AdPullException{

		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>BEGIN  请求相应互串测试代码>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		int test_adspaecId=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.TEST_ADSPACE_ID,-1);
		if(attri.getAdSpaceId()==test_adspaecId){
			return test(adreq.getHash());
		}
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>END  请求相应互串测试代码>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// 设置默认参数
		AdUserInfo userInfo = adreq.getUserinfo();
		// 操作系统
		String os = userInfo.getOs();
		if(StringUtils.isEmpty(os)){
			os = AbstractAdSupplier.OS_ANDROID;
			userInfo.setOs(os);
		}
		// 设备宽高
		if(userInfo.getDevice_width() == 0){
			userInfo.setDevice_width(AbstractAdSupplier.PARAM_DEFAULT_DEVICE_WIDTH);
		}
		if(userInfo.getDevice_height() == 0){
			userInfo.setDevice_height(AbstractAdSupplier.PARAM_DEFAULT_DEVICE_HEIGHT);
		}
		if(StringUtils.isEmpty(userInfo.getDensity())&&StringUtils.isNotEmpty(userInfo.getDip())){

			userInfo.setDensity(String.valueOf(Double.parseDouble(userInfo.getDip())*160));
		}
		/*	
		//************BEGIN 查询经纬度***********
		int isLbsSearch=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.LBS_THRIFT_STATE,0);
		if(StringUtils.isNotEmpty(userInfo.getClient_ip())&&isLbsSearch==1){
			long ts = System.currentTimeMillis();
			try{
				LBSByIPReq req=new LBSByIPReq();
				req.setIp(userInfo.getClient_ip());
				LBSByIPResp resp=LBSClientDispatcher.build().invok(req);
				logger.info("{} get lbs info:{}", adreq.getHash(),resp);
				if(resp!=null){
					userInfo.setLat(resp.getContent().getLat());
					userInfo.setLon(resp.getContent().getLng());
				}
			}catch(Exception e){
				logger.error("get lbs info error ", e);
			}finally{
				long ls = System.currentTimeMillis();
				logger.info("{} get lbs info time cost:{} ms",adreq.getHash(),ls - ts);
			}
			
		}*/
		//************END 查询经纬度***********
		// ip
		//数据规范化
		userInfo.setClient_ip(userInfo.getClient_ip().trim());
		if(StringUtils.isNotEmpty(userInfo.getLat())){
			userInfo.setLat(userInfo.getLat().trim());
		}
		if(StringUtils.isNotEmpty(userInfo.getLon())){
			userInfo.setLon(userInfo.getLon().trim());
		}
		AbstractAdSupplier.InvokeAttribute attribute = new AbstractAdSupplier.InvokeAttribute(adreq, dspPosition);
		AdBidService adBidService=(AdBidService)SystemContext.getServiceHandler().getService(AdBidService.class);
		BaseAdSupplier  proxy=adBidService.getAdSupplier(adreq);
		

		// 处理结果
		AdRecomReply recomReply = proxy.invoke(attribute);
		
		if(recomReply == null){
			return null;
		}
		
		
		//content format
		this.contentFormat(recomReply.getAd_contents(), adreq.getHash(), JoinDSPEmu.getJoinDspByName(attri.getJoinDspName()).getId2adr(), dspPosition);
		
		recomReply.setRequstId(adreq.getHash());
		return recomReply;
	}
	private AdRecomReply test(String hashCode){
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(666);
		

		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		if(StringUtils.isNotEmpty(hashCode)){
			content.setPhone(hashCode);
			
		}
		content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));
		
		AdMutiAction action = new AdMutiAction();
		String title = "广告";
		String marketTitle="广告";
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
        int acType=1;
      //***********************END 常规设置*************
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
   
		AdImg img=new AdImg();
        img.setSrc("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2069625180,2162766275&fm=27&gp=0.jpg");
        imgs.add(img);
    	
        content.setLinkurl("www.baidu.com");
		action.setLinkurl("www.baidu.com");
		
		//content.setThirdReportLinks(map);
		//img
		content.setImglist(imgs);
	    action.setType(acType); 
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));
	
		adresp.setAd_contents(Collections.singletonList(content));
		
		try {
			long wait_time=(long)(800*Math.random());
			Thread.sleep(wait_time);//等待0-800ms
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresp;
	}
	private void contentFormat(List<AdContent> contents,String hashCode,int joinDsp,DSPPosition dspPosition){
		for(AdContent content:contents){
			content.setSettlementPrice(dspPosition.getSettlementPrice());
			content.setIdFromAdSrc(dspPosition.getId());
			content.setPosition(defpos);
			content.setIsNeedSrcImg(true);
			if(StringUtils.isEmpty(content.getAdSource())){
				content.setAdSource(AbstractAdSupplier.PARAM_DEFAULT_AD_SOURCE);
			}
			
			content.setAdSrc(joinDsp);
			
			int actType=checkActType(content);
			if(actType>0){
				content.getMutiAction().get(0).setType(actType);
			}
			
			if(StringUtils.isNotEmpty(hashCode)){
				content.setPhone(hashCode);
				
			}
			
			//设置无关的参数
			if(StringUtils.isNotEmpty(dspPosition.getClickAreas())){
				String ca[]=dspPosition.getClickAreas().split(",");
				List<Integer> caL=new ArrayList<Integer>();
				for(String str:ca){
					caL.add(Integer.parseInt(str));
				}
				content.setMaterialClickAreas(caL);
			}
			if(dspPosition.getRetryDld()!=null){
				content.setRetryDownTimes(dspPosition.getRetryDld());
			}
			if(CollectionUtils.isNotEmpty(content.getMutiAction())){
				AdMutiAction multiAction=content.getMutiAction().get(0);
				if(dspPosition.getSiRate()!=null){
					multiAction.setSilentInstallRate(dspPosition.getSiRate());
				}
				if(dspPosition.getActiveRate()!=null){
					multiAction.setActiveRate(dspPosition.getActiveRate());
				}
				if(dspPosition.getClickRate()!=null){
					multiAction.setClickRate(dspPosition.getClickRate());
				}

			}
			//广告位id设置
			content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));
			
		}
		
	}
	private int checkActType(AdContent content){
		if(CollectionUtils.isEmpty(content.getMutiAction())){
			return -1;
		}
		AdMutiAction action=	content.getMutiAction().get(0);
		if(action.getType()==2){
			return -1;//确定是app就不用校验了
		}
		if(StringUtils.isNotEmpty(action.getLinkurl())&&action.getLinkurl().toLowerCase().endsWith(".apk")){
			return 2;//app
		}
		return -1;
	}
	private AdRecomReply noad(){
		AdRecomReply recomReply = new AdRecomReply();
		// 暂定 0表示没有广告 
		recomReply.setStatus(ErrorCode.AD_EMPTY);
		return recomReply;
	}
	public int notice_click(String uid, long adid) throws TException {
		return 0;
	}

	public void prefetch(String uid, AdRecomReq adreq) throws TException {
		
	}

	public int notice(String uid, long adid) throws TException {
		// TODO Auto-generated method stub
		return 0;
	}
}
