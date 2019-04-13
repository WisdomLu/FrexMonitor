package com.ocean.service.qqInvokHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getRankAppADList.GetRankAppADListPuller;
import com.ocean.persist.app.dis.qqDownloader.getRankAppADList.GetRankAppADListReqBody;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.base.QQBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
@Component(value="getRankAppADListHandler")
public class GetRankAppADListHandler    extends QQBaseHandler{
    @Autowired
    GetRankAppADListPuller getRankAppADListPuller;
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		GetRankAppADListReqBody reqBody=parseGetRankAppListParam(req);
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_RANK_SEARCH,req,reqBody);
		return getRankAppADListPuller.api(appR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}
	private GetRankAppADListReqBody parseGetRankAppListParam(AppDisRecomReq req) throws AppDisException{
		GetRankAppADListReqBody reqBody=new GetRankAppADListReqBody();
		long categoryId=SystemContext.getDynamicPropertyHandler().getInt(AppDisConstants.INTER_RANK_CATEGORYID, -1);
		reqBody.setCategoryId(categoryId);//0:全部,-1:软件,-2:游戏, 其他如106是社交(sceneId == 6时不需要)
		if(req.isSetPage()){
			reqBody.setPageSize(req.getPage().getPageSize());
		}else{
			reqBody.setPageSize(pageSize);//默认请求10个
		}
	
		//上下文，分页的时候使用
		if(req.isSetInterParam()&&req.getInterParam().getRequiredParam()!=null){
			for(ExtData ext:req.getInterParam().getRequiredParam()){
				if(QQDownloaderRequest.CONTEXTDATA.equals(ext.getKey())){
					reqBody.setPageContext(Arrays.asList(ext.getValue().split(",")));
				}
			}
		}else{
			reqBody.setPageContext((List)Collections.emptyList());
		}
		long sceneid=SystemContext.getDynamicPropertyHandler().getInt(AppDisConstants.INTER_RANK_SCENEID, 3);
		reqBody.setSceneId(sceneid);//3--新版下载榜, 4--流行榜, 5--新品榜, 6--热销榜
		return reqBody;
	}
	public GetRankAppADListPuller getGetRankAppADListPuller() {
		return getRankAppADListPuller;
	}
	public void setGetRankAppADListPuller(
			GetRankAppADListPuller getRankAppADListPuller) {
		this.getRankAppADListPuller = getRankAppADListPuller;
	}
}
