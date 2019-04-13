package com.ocean;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceImgFmt;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.ApiProxy;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

public class CommonAdTest {
	//public static final String SERVER_IP = "114.215.17.214";
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 10000;
	private static final int zkpos = 124;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

		int i = 1;
		while(i > 0){
			CommonAdTest client = new CommonAdTest();
		    AdRecomReply rs = client.startClient_openning();
		    System.out.println(rs);
			
			//AdRecomReply rs = client.startClient_banner();
			//AdRecomReply rs = client.startClient_infoflow();
		    //AdRecomReply rs=client.startClient_interstitial();
		    
			 i--;
		}

	}
	//信息流
	public AdRecomReply startClient_infoflow(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("meinvtuji");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			adreq.setNet("wifi");
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("861239031289657");

			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("123.181.29.180");
			userInfo.setOsversion("5.1");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("ivvi SS1-01");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0:37:42:f9:f4:65");
			userInfo.setAaid("f9579560e89bdb72");
			userInfo.setAdid("f9579560e89bdb72");
			userInfo.setBrand_name("Coolpad");
			userInfo.setUa("Dalvik/2.1.0 (Linux; U; Android 5.1;Coolpad Y803-8 Build/LMY47D)");
			userInfo.setUa_webv("Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
			
			userInfo.setImsi("460021330988496");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			adreq.setHash("6131eaac31bda93e1496943680672413");
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(61);
			attri.setSpaceType(AdSpaceType.INFOFLOW);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
		
			attri.setSpaceHeight(320);
			attri.setSpaceWidth(640);
			attri.setAllowedOpentype(1);
			attri.setSpacePosition(0);
			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
			imgFormats.add(AdSpaceImgFmt.GIF);imgFormats.add(AdSpaceImgFmt.JPG);
			attri.setImgFormats(imgFormats);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.APP));
			attri.setTitleMin(0);
			attri.setTitleMax(100);
			attri.setCwMin(0);
			attri.setCwMax(100);
			attri.setJoinDspName(JoinDSPEmu.XUANYIN.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("13535355", adreq);
			return result;
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
		return null;
	}
	//banner
	public AdRecomReply startClient_banner(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("meinvtuji");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("861239031289657");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("123.181.29.180");
			userInfo.setOsversion("5.1");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("ivvi SS1-01");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0:37:42:f9:f4:65");
			userInfo.setAaid("f9579560e89bdb72");
			userInfo.setAdid("f9579560e89bdb72");
			userInfo.setBrand_name("Coolpad");
			userInfo.setUa("Dalvik/2.1.0 (Linux; U; Android 5.1;Coolpad Y803-8 Build/LMY47D)");
			userInfo.setImsi("460021330988496");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
			userInfo.setLat("31.167");
			userInfo.setLon("112.582");
			
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			adreq.setHash("6131eaac31bda93e1496943680672413");
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(64);
			attri.setSpaceType(AdSpaceType.BANNER);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
			attri.setSpaceHeight(100);
			attri.setSpaceWidth(640);
			attri.setAllowedOpentype(1);
			attri.setSpacePosition(0);
			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
			imgFormats.add(AdSpaceImgFmt.GIF);imgFormats.add(AdSpaceImgFmt.JPG);
			attri.setImgFormats(imgFormats);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.APP));
			attri.setTitleMin(0);
			attri.setTitleMax(100);
			attri.setCwMin(0);
			attri.setCwMax(100);
			attri.setJoinDspName(JoinDSPEmu.YINGNA.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("13535355", adreq);
			return result;
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
		return null;
	}
	public AdRecomReply startClient_openning(){
		
		
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("中华日历-API");
			adreq.setType("");
			adreq.setVersion("3.0.2");
			adreq.setResult_num(1);
			adreq.setOgin_name("861239031289657");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setChannel("Coolpad");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			adreq.setHash("6131eaac31bda93e1496943680672413");
			adreq.setNet("wifi");
			
			
/*			(app:ivvi应用商店, type:, version:2.0.27, result_num:1, ogin_name:869750021897178, log:true, token:7, channel:酷派,
 *  userinfo:AdUserInfo(imei:869750021897178, os:android, osversion:5.1, phonemodel:Coolpad Y91-921, mobile:CMCC, 
 *  client_ip:223.10.221.23, city:140900, lon:112.727938829, lat:38.461030573, ua:Dalvik/2.1.0 (Linux; U; Android 5.1.1; Coolpad Y91-921 Build/LMY47V),
 *   aaid:5901ab3e92a0730d, adid:5901ab3e92a0730d, brand_name:Coolpad, device_height:1280, device_width:720, mac:d0:37:42:c1:1d:0d, 
 *   imsi:460079971477331, dip:2, density:320), net:wifi, hash:8697500218971781520015299666118, rver:0, 
 *   check_ver:false, userAdSpaceAttri:UserAdSpaceAttri(spaceWidth:720, spaceHeight:1080, expectedMarketTypes:[LINK, VIDEO, APP], 
 *   allowedOpentype:2, spacePosition:1, spaceType:OPENING, appId:2068, adSpaceId:22495, imgFormats:[JPG], imgMaxSize:100, titleMin:0,
 *    titleMax:100, cwMin:0, cwMax:100, adSources:[null, SELF], from:OTA, allowedHtml:true, joinDspName:zhangku, h5type:0, apptype:0, joinPoseId:280))*/
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("861239031289657");
			//userInfo.setImei("86253303003298");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("223.10.221.23");
			userInfo.setOsversion("5.1");
			userInfo.setOs_api_level("21");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("Coolpad Y91-921");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0:37:42:c1:1d:0d");
			userInfo.setAaid("bdcf98b6c9c55883");
			userInfo.setAdid("bdcf98b6c9c55883");
			userInfo.setBrand_name("Coolpad");
			userInfo.setUa("Dalvik/2.1.0 (Linux; U; Android 5.1.1; Coolpad Y91-921 Build/LMY47V)");
			userInfo.setUa_webv("Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
			
			userInfo.setImsi("460079971477331");
			userInfo.setDevice_height(1080);
			userInfo.setDevice_width(720);
			userInfo.setLat("31.167");
			userInfo.setLon("112.582");
			userInfo.setDip("2.0");
			userInfo.setDensity("320");
			userInfo.setSn("L3610S0037A1600357");
			userInfo.setLac(20952);
			userInfo.setCid(9486744);
	
			
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(61);
			attri.setSpaceType(AdSpaceType.OPENING);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
		
			attri.setSpaceHeight(1080);
			attri.setSpaceWidth(720);
			attri.setAllowedOpentype(1);
			attri.setSpacePosition(0);
			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
			imgFormats.add(AdSpaceImgFmt.GIF);imgFormats.add(AdSpaceImgFmt.JPG);
			attri.setImgFormats(imgFormats);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.APP));
			attri.setTitleMin(0);
			attri.setTitleMax(100);
			attri.setCwMin(0);
			attri.setCwMax(100);
//			attri.setJoinDspName(JoinDSPEmu.RUANGAOYUN.getAbbrev());
			attri.setJoinDspName(JoinDSPEmu.XUNFEI.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("13535355", adreq);
			return result;
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
		return null;
	}
	
	public AdRecomReply startClient_interstitial(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("meinvtuji");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			adreq.setHash("6131eaac31bda93e1496943680672413");
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("861239031289657");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("123.181.29.180");
			userInfo.setOsversion("5.1");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("ivvi SS1-01");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0:37:42:f9:f4:65");
			userInfo.setAaid("f9579560e89bdb72");
			userInfo.setAdid("f9579560e89bdb72");
			userInfo.setBrand_name("Coolpad");
			userInfo.setUa("Dalvik/2.1.0 (Linux; U; Android 5.1;Coolpad Y803-8 Build/LMY47D)");
			userInfo.setImsi("460021330988496");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
			userInfo.setLat("31.167");
			userInfo.setLon("112.582");

//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(61);
			attri.setSpaceType(AdSpaceType.INTERSTITIAL);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
		
			attri.setSpaceHeight(640);
			attri.setSpaceWidth(960);
			attri.setAllowedOpentype(1);
			attri.setSpacePosition(0);
			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
			imgFormats.add(AdSpaceImgFmt.GIF);imgFormats.add(AdSpaceImgFmt.JPG);
			attri.setImgFormats(imgFormats);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.APP));
			attri.setTitleMin(0);
			attri.setTitleMax(100);
			attri.setCwMin(0);
			attri.setCwMax(100);
			attri.setJoinDspName(JoinDSPEmu.TIANMEI.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("13535355", adreq);
			return result;	
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
		return null;
	}
}
