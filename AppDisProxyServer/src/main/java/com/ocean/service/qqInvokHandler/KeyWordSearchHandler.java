package com.ocean.service.qqInvokHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.keywordSearch.KeyWordSearchPuller;
import com.ocean.persist.app.dis.qqDownloader.keywordSearch.KeyWordSearchReqBody;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.base.QQBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
@Component(value="keyWordSearchHandler")
public class KeyWordSearchHandler   extends QQBaseHandler{
	@Autowired
	KeyWordSearchPuller KeyWordSearchPuller;
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		KeyWordSearchReqBody reqBody=parseKeyWordSearchParam(req);
		QQDownloaderRequest appR=getQQDownloaderAttri(AppDisConstants.INTER_KEYWORD_SEARCH,req,reqBody);
		return KeyWordSearchPuller.api(appR,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}
	
	private KeyWordSearchReqBody parseKeyWordSearchParam(AppDisRecomReq req) throws AppDisException{
		KeyWordSearchReqBody reqBody=new KeyWordSearchReqBody();
		InterParam interParam=req.getInterParam();
		if(StringUtil.isEmpty(interParam.getKeyWord())&&StringUtils.isEmpty(interParam.getHotWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"key word search parameter is empty!");
		}
		String keyWord=interParam.getKeyWord();
		if(StringUtil.isEmpty(keyWord)){
			keyWord=interParam.getHotWord();
		}
		reqBody.setKeyword(keyWord);
		if(req.isSetPage()){
			reqBody.setPageSize(req.getPage().getPageSize());
		}else{
			reqBody.setPageSize(pageSize);//默认请求10个
		}
	
		//上下文，分页的时候使用
		if(req.isSetInterParam()&&req.getInterParam().getRequiredParam()!=null){
			for(ExtData ext:req.getInterParam().getRequiredParam()){
				if(QQDownloaderRequest.CONTEXTDATA.equals(ext.getKey())){
					reqBody.setContextData(Arrays.asList(ext.getValue().split(",")));
				}
			}
		}else{
			reqBody.setContextData((List)Collections.emptyList());
		}
	

		return reqBody;
	}
	
	public KeyWordSearchPuller getKeyWordSearchPuller() {
		return KeyWordSearchPuller;
	}
	public void setKeyWordSearchPuller(KeyWordSearchPuller keyWordSearchPuller) {
		KeyWordSearchPuller = keyWordSearchPuller;
	}

}
