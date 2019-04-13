package com.ocean.service.qqInvokHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.GetRecommendADListReqBody;
import com.ocean.persist.app.dis.qqDownloader.getSubjectList.GetSubjectListPuller;
import com.ocean.persist.app.dis.qqDownloader.getSubjectList.GetSubjectListReqBody;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.base.QQBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月18日 
      @version 1.0 
 */
@Component(value="getSubjectListHandler")
public class GetSubjectListHandler     extends QQBaseHandler{
	@Autowired
	private GetSubjectListPuller getSubjectListPuller;
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		GetSubjectListReqBody reqBody=parseGetSubjectListParam(req);
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_GETSUBJECTLIST,req,reqBody);
		return getSubjectListPuller.api(appR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}
	private GetSubjectListReqBody parseGetSubjectListParam(AppDisRecomReq req) throws AppDisException{
		AppInfo app=req.getAppInfo();
		if(app==null||StringUtils.isEmpty(app.getPkgName())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR, "GetRecommendADList param error!");
		}
		GetSubjectListReqBody body=new GetSubjectListReqBody();
		if(req.isSetPage()){
			body.setPageSize(req.getPage().getPageSize());
		}else{
			body.setPageSize(pageSize);//默认请求10个
		}
		if(req.isSetInterParam()&&req.getInterParam().getRequiredParam()!=null){
			for(ExtData ext:req.getInterParam().getRequiredParam()){
				if(QQDownloaderRequest.CONTEXTDATA.equals(ext.getKey())){
					body.setPageContext(Arrays.asList(ext.getValue().split(",")));
				}
			}
		}else{
			body.setPageContext((List)Collections.emptyList());
		}
		return body;
	}
	public GetSubjectListPuller getGetSubjectListPuller() {
		return getSubjectListPuller;
	}
	public void setGetSubjectListPuller(GetSubjectListPuller getSubjectListPuller) {
		this.getSubjectListPuller = getSubjectListPuller;
	}

}
