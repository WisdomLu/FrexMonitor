package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.lingji.LingjiAdResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

/**
 * 从灵集获取原生广告数据<p>
 * 灵集的请求参数<p>
 * <a href="http://docs.thextrader.cn/media/mobile_native_protocol.html">
 * http://docs.thextrader.cn/media/mobile_native_protocol.html
 * </a>
 * @author xy
 *
 */
@Component(value="lingjiAdSupplier")
public class LingjiAdSupplier extends AbstractAdSupplier{
	private static final Map<Integer, Integer> ACTION_WEBs = new HashMap<Integer, Integer>(5);
	static{
		// 应用推广
		ACTION_WEBs.put(1, 2);
		// 网页推广
		ACTION_WEBs.put(2, 1);
		// 未知
		ACTION_WEBs.put(3, 3);
	}

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo userInfo = adreq.getUserinfo();
		String os = userInfo.getOs();
		if(OS_PC.equals(os)){
			LJPCAdSupplier lJPCAdSupplier=(LJPCAdSupplier)SystemContext.getServiceHandler().getService(LJPCAdSupplier.class);
			return lJPCAdSupplier.invoke(attribute);
		}else if(OS_ANDROID.equals(os)||OS_IOS.equals(os)){
			LJMAdSupplier lJMAdSupplier=(LJMAdSupplier)SystemContext.getServiceHandler().getService(LJMAdSupplier.class);
			return lJMAdSupplier.invoke(attribute);
		}else{
			throw new AdPullException("未知的操作系统：" + os);
		}
	}
	
	protected AdRecomReply parseResult(LingjiAdResponse response) throws AdPullException {
		
		if(response == null){
			return null;
		}
		
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		// 广告内容
		AdContent content = new AdContent();
		// 广告id
		//content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));

		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = response.getCm();
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		// 曝光监测
		Map<String, List<String>> showUrls = response.getPm();
		List<String> urls = new ArrayList<String>();
		if(showUrls != null){
			
			Set<String> keys = showUrls.keySet();
			for (String key : keys) {
				urls.addAll(showUrls.get(key));
			}
		}
		map.put(SHOW, urls);
		content.setThirdReportLinks(map);
		
		// 类型
		AdMutiAction action = new AdMutiAction();
		Integer type = ACTION_WEBs.get(response.getAction());
		if(type == null){
			// 1表示链接推广
			type = ACTION_WEB;
		}
		action.setType(type);
		action.setLinkurl(response.getLdp());
		content.setMutiAction(Collections.singletonList(action));
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}

	
}
