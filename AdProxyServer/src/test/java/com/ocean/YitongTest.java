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

public class YitongTest {
	//public static final String SERVER_IP = "114.215.17.214";
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 10000;
	private static final int zkpos = 486;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		
		int i = 1;
		while(i > 0){
			YitongTest client = new YitongTest();
		    AdRecomReply rs = client.startClient_openning();
			//AdRecomReply rs = client.startClient_banner();
			//AdRecomReply rs = client.startClient_infoflow();
		   // AdRecomReply rs=client.startClient_interstitial();
			System.out.println(rs);
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
			attri.setJoinDspName(JoinDSPEmu.YITONG.getAbbrev());
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
			attri.setJoinDspName(JoinDSPEmu.YITONG.getAbbrev());
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
			adreq.setApp("ivvi应用商店");
			adreq.setType("");
			adreq.setVersion("2.0.27");
			adreq.setResult_num(1);
			adreq.setOgin_name("861239031289657");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setChannel("Coolpad");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			adreq.setHash("6131eaac31bda93e1496943680672413");
			adreq.setNet("wifi");
			
			
/*			userinfo:AdUserInfo(imei:861239031289657, os:android, osversion:5.1, phonemodel:Coolpad Y803-8, mobile:CMCC, 
					client_ip:123.181.29.180, city:130400, lon:, lat:, ua:Dalvik/2.1.0 (Linux; U; Android 5.1;Coolpad Y803-8 Build/LMY47D), aaid:f9579560e89bdb72, adid:f9579560e89bdb72, brand_name:Coolpad, 
					device_height:1184, device_width:720, mac:d0:37:42:f9:f4:65, imsi:460021330988496, 
					dip:2, density:320), net:wifi, hash:8612390312896571512064892049365, rver:0, check_ver:false, 
					userAdSpaceAttri:UserAdSpaceAttri(spaceWidth:720, spaceHeight:1280, expectedMarketTypes:[LINK, VIDEO, APP], allowedOpentype:2, spacePosition:1, spaceType:OPENING, appId:2068, adSpaceId:22496, imgFormats:[JPG], imgMaxSize:100, titleMin:0, titleMax:100, cwMin:0, cwMax:100, adSources:[null, SELF], from:OTA, allowedHtml:true, joinDspName:speed, h5type:0, apptype:0, joinPoseId:271))
			*/
			
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
		
			attri.setSpaceHeight(960);
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
			attri.setJoinDspName(JoinDSPEmu.YITONG.getAbbrev());
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
			attri.setJoinDspName(JoinDSPEmu.YITONG.getAbbrev());
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
