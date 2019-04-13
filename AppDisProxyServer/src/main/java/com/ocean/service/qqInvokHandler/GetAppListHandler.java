package com.ocean.service.qqInvokHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getAppList.GetAppListPullReqBody;
import com.ocean.persist.app.dis.qqDownloader.getAppList.GetAppListPuller;
import com.ocean.persist.app.dis.qqDownloader.getAppList.GetAppListResponse;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListRespBody;
import com.ocean.persist.app.dis.qqDownloader.getSubjectList.GetSubjectListRespBody;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.base.QQBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
@Component(value="getAppListHandler")
public class GetAppListHandler   extends QQBaseHandler{
	@Autowired
	private GetAppListPuller getAppListPuller;
	/*按分类id 查询*/
	private GetAppListPullReqBody parseGetAppListPullParams(AppDisRecomReq req,GetCategoryListRespBody catR) throws AppDisException{

		if(catR.getRet()!=0){
			 throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get getAppList parse parameter  error,return error code="+catR.getRet());
		}
		if(CollectionUtils.isEmpty(catR.getCategoryList())){
			return null;
		}
		GetAppListPullReqBody appPB=new GetAppListPullReqBody();
		if(req.isSetPage()){
			appPB.setPageSize(req.getPage().getPageSize());
		}else{
			appPB.setPageSize(pageSize);//默认请求10个
		}
	
		//上下文，分页的时候使用
		if(req.isSetInterParam()&&req.getInterParam().getRequiredParam()!=null){
			for(ExtData ext:req.getInterParam().getRequiredParam()){
				if(QQDownloaderRequest.CONTEXTDATA.equals(ext.getKey())){
					appPB.setContextData(Arrays.asList(ext.getValue().split(",")));
				}
			}
		}else{
			appPB.setContextData((List)Collections.emptyList());
		}
		
		//获取分类id列表
		appPB.setCategoryId(catR.getCategoryList().get(0).getCategoryId());
		appPB.setListType(LISTTYPE_CATEGORY);//按分类查询
		
		
		return appPB;
	}
	/*按分类id 查询*/
	private GetAppListPullReqBody parseGetAppListPullParams(AppDisRecomReq req,GetSubjectListRespBody subR) throws AppDisException{

		if(subR.getRet()!=0){
			 throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get getAppList parse parameter error,return error code="+subR.getRet());
		}
		if(CollectionUtils.isEmpty(subR.getSubjectList())){
			return null;
		}
		GetAppListPullReqBody appPB=new GetAppListPullReqBody();
		if(req.isSetPage()){
			appPB.setPageSize(req.getPage().getPageSize());
		}else{
			appPB.setPageSize(pageSize);//默认
		}
	
		//上下文，分页的时候使用
		if(req.isSetInterParam()&&req.getInterParam().getRequiredParam()!=null){
			for(ExtData ext:req.getInterParam().getRequiredParam()){
				if(QQDownloaderRequest.CONTEXTDATA.equals(ext.getKey())){
					appPB.setContextData(Arrays.asList(ext.getValue().split(",")));
				}
			}
		}else{
			appPB.setContextData((List)Collections.emptyList());
		}
		
		//获取分类id列表

		appPB.setColumnId(subR.getSubjectList().get(0).getId());
		
		appPB.setListType(LISTTYPE_SUBJECT);//按分类查询
		return appPB;
	}
	@Override
	public GetAppListResponse invok(AppDisRecomReq req){
		return null;
	}
	
	public GetAppListResponse invok(AppDisRecomReq req,Set<String> categoryIds) throws AppDisException {
		// TODO Auto-generated method stub
		GetAppListPullReqBody appPB=getAppListPullParamsByCache(req,(String)categoryIds.toArray()[new Random().nextInt(categoryIds.size())]);
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_GETAPPLIST,req,appPB);
		return (GetAppListResponse)getAppListPuller.api(appR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}
	public GetAppListResponse invok(AppDisRecomReq req,GetCategoryListRespBody catRB) throws AppDisException{
		if(catRB==null){
			 throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get getAppList error,GetCategoryListRespBody is empty!");
		}
		GetAppListPullReqBody appPB=parseGetAppListPullParams(req,catRB);
		if(appPB==null){
			return null;
		}
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_GETAPPLIST,req,appPB);
		return (GetAppListResponse)getAppListPuller.api(appR);
	}
	public GetAppListResponse invok(AppDisRecomReq req,GetSubjectListRespBody subRB) throws AppDisException{
		if(subRB==null){
			 throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get getAppList error,GetSubjectListRespBody is empty!");
		}
		GetAppListPullReqBody appPB=parseGetAppListPullParams(req,subRB);
		if(appPB==null){
			return null;
		}
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_GETAPPLIST,req,appPB);
		return (GetAppListResponse)getAppListPuller.api(appR);
	}
	private GetAppListPullReqBody getAppListPullParamsByCache(AppDisRecomReq req,String categoryId) throws AppDisException{
		if(StringUtils.isEmpty(categoryId)){
			 throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"categoryId is empty");
		}
		GetAppListPullReqBody appPB=new GetAppListPullReqBody();
		if(req.isSetPage()){
			appPB.setPageSize(req.getPage().getPageSize());
		}else{
			appPB.setPageSize(pageSize);//默认请求10个
		}
	
		//上下文，分页的时候使用
		if(req.isSetInterParam()&&req.getInterParam().getRequiredParam()!=null){
			for(ExtData ext:req.getInterParam().getRequiredParam()){
				if(QQDownloaderRequest.CONTEXTDATA.equals(ext.getKey())){
					appPB.setContextData(Arrays.asList(ext.getValue().split(",")));
				}
			}
		}else{
			appPB.setContextData((List)Collections.emptyList());
		}
		
		appPB.setCategoryId(Integer.parseInt(categoryId));
		appPB.setListType(10);//按分类查询
		
		
		return appPB;
	}
	public GetAppListPuller getGetAppListPuller() {
		return getAppListPuller;
	}

	public void setGetAppListPuller(GetAppListPuller getAppListPuller) {
		this.getAppListPuller = getAppListPuller;
	}

}
