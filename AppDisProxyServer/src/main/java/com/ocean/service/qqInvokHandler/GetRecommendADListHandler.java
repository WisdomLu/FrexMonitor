package com.ocean.service.qqInvokHandler;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getRankAppADList.GetRankAppADListReqBody;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.GetRecommendADListPuller;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.GetRecommendADListReqBody;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.base.QQBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
@Component(value="getRecommendADListHandler")
public class GetRecommendADListHandler    extends QQBaseHandler{
    @Autowired 
    private GetRecommendADListPuller getRecommendADListPuller;
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		GetRecommendADListReqBody reqBody=parseGetRecommendAppListParam(req);
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,reqBody);
		return getRecommendADListPuller.api(appR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}
	private GetRecommendADListReqBody parseGetRecommendAppListParam(AppDisRecomReq req) throws AppDisException{
		//AppInfo app=req.getAppInfo();
		InterParam inter=req.getInterParam();
		if(inter==null||StringUtils.isEmpty(inter.getKeyWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR, "GetRecommendADList param error!");
		}
		GetRecommendADListReqBody body=new GetRecommendADListReqBody();
		body.setAppList(Arrays.asList(inter.getKeyWord()));
		return body;
	}
	public GetRecommendADListPuller getGetRecommendADListPuller() {
		return getRecommendADListPuller;
	}
	public void setGetRecommendADListPuller(
			GetRecommendADListPuller getRecommendADListPuller) {
		this.getRecommendADListPuller = getRecommendADListPuller;
	}
     
}
