package com.ocean.service.fuxi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.fuxi.appasyn.FuxiAppAsynResponse;
import com.ocean.persist.app.dis.fuxi.appasyn.FuxiAppasynParams;
import com.ocean.persist.app.dis.fuxi.appasyn.FuxiAppasynSearchPuller;
import com.ocean.persist.app.dis.fuxi.appasyn.FuxiAsnApp;
import com.ocean.persist.app.dis.fuxi.getRcommendAdList.FuxiGetRecommendADListPuller;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.GetRecommendADListReqBody;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
@Component(value="FuxiGetRecommendADListHandler")
public class FuxiGetRecommendADListHandler    extends BaseHandler{
    @Autowired 
    private FuxiGetRecommendADListPuller getRecommendADListPuller;
    
    @Autowired 
    private  FuxiAppasynSearchPuller fuxiAppasynSearchPuller;
	public  ThreadLocal<List<FuxiAsnApp>>  appSearchList;
	{
		appSearchList=new ThreadLocal<List<FuxiAsnApp>>();
	}
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		GetRecommendADListReqBody reqBody=parseGetRecommendAppListParam(req);
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,reqBody);
		return getRecommendADListPuller.api(appR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}
	private GetRecommendADListReqBody parseGetRecommendAppListParam(AppDisRecomReq req) throws AppDisException{
		GetRecommendADListReqBody body=new GetRecommendADListReqBody();
		List<FuxiAsnApp> appL=this.getSearchApp(req);
		if(CollectionUtils.isEmpty(appL)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR, "Fuxi GetRecommendADList param error when get pkg list from Wangka API!");
		}
		List<String> pkgL=new ArrayList<String>();
		for(FuxiAsnApp app:appL){
			pkgL.add(app.get_package());
		}
		body.setAppList(pkgL);
		return body;
	}
	public FuxiGetRecommendADListPuller getGetRecommendADListPuller() {
		return getRecommendADListPuller;
	}
	public void setGetRecommendADListPuller(
			FuxiGetRecommendADListPuller getRecommendADListPuller) {
		this.getRecommendADListPuller = getRecommendADListPuller;
	}
	private void cacheAppList(FuxiAppAsynResponse resp){
		if(resp==null||resp.getContent()==null){
			return;
		}
		

		if(CollectionUtils.isNotEmpty(resp.getContent().getList())){
			List<FuxiAsnApp> appL=new ArrayList<FuxiAsnApp>();
			appL.addAll(resp.getContent().getList());
			appSearchList.set(appL);
		}
		
	}
	private void reqWangkaAppList() throws AppDisException{
		FuxiAppasynParams param=new FuxiAppasynParams();
		param.setFrom_client("server");
		param.setChannel_id("20022a");
		param.setApp_id("b1022a");
		param.setPn(1);
		param.setRn(0);
		param.setTimestamp(String.valueOf(System.currentTimeMillis()/1000));
		StringBuffer sb=new StringBuffer();
		sb.append("app_id=b1022a&channel_id=20022a&from_client=server&pn=1&rn=0&timestamp=").append(param.getTimestamp())
		.append("Y6hcQYcTCRQ7F7FsXIB3uHPEFsLiYyR1");
		param.setSign(DigestUtils.md5Hex(sb.toString()).toLowerCase());
		FuxiAppAsynResponse resp=(FuxiAppAsynResponse)fuxiAppasynSearchPuller.api(param,"");
		this.cacheAppList(resp);
	}
	public List<FuxiAsnApp> getSearchApp(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(appSearchList.get())){
			reqWangkaAppList();
			
		}
		if(CollectionUtils.isEmpty(appSearchList.get())){
			
			return null;
		}
		int size=pageSize;
		if(req.isSetPage()){
			size=req.getPage().getPageSize();
		}
		
		List<FuxiAsnApp> list=new ArrayList<FuxiAsnApp>();
		{
			Iterator<FuxiAsnApp> it=appSearchList.get().iterator();
			for(int i=0;i<size;i++){
				if(it.hasNext()){
					list.add(it.next());
					it.remove();
				}
			}

			return list;
		}
	}
	public FuxiAppasynSearchPuller getWkPkgSearchPuller() {
		return fuxiAppasynSearchPuller;
	}
	public void setWkPkgSearchPuller(FuxiAppasynSearchPuller fuxiAppasynSearchPuller) {
		this.fuxiAppasynSearchPuller = fuxiAppasynSearchPuller;
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
}
