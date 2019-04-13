package com.ocean.service.qihooInvokeHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;

public class QihooAppStoreSupplierBase extends AbstractAppDisSupplier{
	public final static String QIHOO_PAY_MODE="qihoo.pay.mode";
	public final static String QIHOO_CLIENT_IP="__CLIENT_IP__";
	public final static String QIHOO_CLIENT_UA_="__CLIENT_UA__";
	public final static String QIHOO_LONGITUDE_="__LONGITUDE__";//经度，实际获取到的值即可，无需做特殊处理 
	public final static String QIHOO_LATITUDE_="__LATITUDE__";//纬度，实际获取到的即可，无需做特殊处理
	public final static String QIHOO_OFFSET_X_="__OFFSET_X__";//点击的横坐标,广告view上的相对坐标,不是屏幕的坐标 											 
	public final static String QIHOO_OFFSET_Y_="__OFFSET_Y__";//点击的纵坐标,广告view上的相对坐标,不是屏幕的坐标 
	public final static String QIHOO_EVENT_TIME_START="__EVENT_TIME_START__";
	public final static String QIHOO_EVENT_TIME_END="__EVENT_TIME_END__";
	public final static int QIHOO_REPORT_TYPE_PV=1;
	public final static int QIHOO_REPORT_TYPE_CLK=2;
	public final static int QIHOO_REPORT_TYPE_DOWNLOAD=3;
	public final static int QIHOO_REPORT_TYPE_INSTALL=4;
	public final static int QIHOO_REPORT_TYPE_ACTIVE=5;
	public String parseMacro(int type,String str){
		if(type==QIHOO_REPORT_TYPE_CLK){
			str=str.replaceAll(QIHOO_OFFSET_X_, MACRO_DOWN_X)
					  .replaceAll(QIHOO_OFFSET_Y_,MACRO_DOWN_Y);
			
		}
		str=str.replaceAll(QIHOO_EVENT_TIME_START, MACRO_EVENT_TIME_START)
				  .replaceAll(QIHOO_EVENT_TIME_END,MACRO_EVENT_TIME_END);
		return str;
	}
	@Override
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> pvReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> clickReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> beginDownloadReport(String interTye,
			AppDisRecomReq req, AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> endDownloadReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> installReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

}
