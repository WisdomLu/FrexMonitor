package com.ocean.service.qqInvokHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.InterType;
import com.ocean.handler.RedisHandler;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.CategoryDetail;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListPullReqBody;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListPuller;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListRespBody;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListResponse;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.base.QQBaseHandler;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
@Component(value="getCategoryListHandler")
public class GetCategoryListHandler  extends QQBaseHandler{
	@Autowired
    private GetCategoryListPuller getCategoryListPuller;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private GetCategoryListPullReqBody  parseGetCategoryListPullParams(AppDisRecomReq req) throws AppDisException{
		GetCategoryListPullReqBody catParam=new GetCategoryListPullReqBody();
		if(!req.isSetShieldType()){
			catParam.setReqType(0);
		}else if(req.getShieldType().contains(AppType.TYPE_APP)&&!req.getShieldType().contains(AppType.TYPE_GAME)){
			catParam.setReqType(-2);
		}else if(req.getShieldType().contains(AppType.TYPE_GAME)&&!req.getShieldType().contains(AppType.TYPE_APP)){
			catParam.setReqType(-1);
		}

    	return catParam;
    }
	@Override
	public GetCategoryListResponse invok(AppDisRecomReq req) throws AppDisException {
				logger.info("{}:{} get catgoryId list from cache  is empty!",req.getJoinSource().name(),InterType.APP_LIST_SEARCH.name());
				QQDownloaderRequest catR=getQQDownloaderAttri(AppDisConstants.INTER_GETCATEGORYDETAILLIST,req,parseGetCategoryListPullParams(req));
				GetCategoryListResponse catResp=(GetCategoryListResponse)getCategoryListPuller.api(catR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
				if(catResp==null){
					logger.error("joinAppStore:TENCENT_QQDOWNLOADER , get category list is empty!");
					return null;
				}
				
				
				//缓存应用id列表
				GetCategoryListRespBody catRB=(GetCategoryListRespBody)catResp.getBody();
				if(catRB!=null&&catRB.getRet()==0&&CollectionUtils.isNotEmpty(catRB.getCategoryList())){
					Set<String> set=getCategoryIds(catRB);
					logger.info("joinAppStore:TENCENT_QQDOWNLOADER,try to cache the category ids {}",set);
					RedisHandler redisHandler=RedisHandler.buildHandler();
					redisHandler.cacheCategoryId(req.getJoinSource(), set);
				}

		return catResp;
	}
	private Set<String> getCategoryIds(GetCategoryListRespBody catRB){

		   Set<String> set=new HashSet<String>();
		   List<CategoryDetail> catL=  catRB.getCategoryList();
		   for(CategoryDetail cat:catL ){
			   if(!set.contains(cat.getCategoryId())){
				   set.add(String.valueOf(cat.getCategoryId()));
			   }
			   
		   }
		   return set;
	}
	public GetCategoryListPuller getGetCategoryListPuller() {
		return getCategoryListPuller;
	}
	public void setGetCategoryListPuller(GetCategoryListPuller getCategoryListPuller) {
		this.getCategoryListPuller = getCategoryListPuller;
	}
}
