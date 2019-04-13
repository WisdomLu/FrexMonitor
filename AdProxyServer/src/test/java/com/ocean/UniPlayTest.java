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

public class UniPlayTest {
	//public static final String SERVER_IP = "114.215.17.214";
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 10000;
	private static final int zkpos =478;  
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		
		int i = 1;
		while(i > 0){
			UniPlayTest client = new UniPlayTest();
		    //AdRecomReply rs = client.startClient_openning();
			//AdRecomReply rs = client.startClient_banner();
			
		    AdRecomReply rs = client.startClient_infoflow();
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
			adreq.setApp("信息流");
			adreq.setType("");
			adreq.setVersion("1.0.35");
			adreq.setResult_num(1);
			adreq.setOgin_name("869014029559003");
			adreq.setLog(true);
			adreq.setToken("10");
			adreq.setNet("wifi");
			adreq.setHash("8690140295590031505145461298559");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("869014029559003");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("183.14.28.126");
			userInfo.setOsversion("22");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("m1 metal");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("68:3e:34:c5:d5:fd");
			userInfo.setAaid("19968b4450d06515");
			userInfo.setAdid("19968b4450d06515");
			//userInfo.setBrand_id("");
			userInfo.setBrand_name("Meizu");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("Mozilla/5.0 (Linux; Android 5.1; m1 metal Build/LMY47I; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.147 Mobile Safari/537.36");
			userInfo.setDevice_height(1920);
			userInfo.setDevice_width(1080);
			userInfo.setDip("3");
			userInfo.setDensity("480");
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(134);
			attri.setSpaceType(AdSpaceType.INFOFLOW);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
		
			attri.setSpaceHeight(300);
			attri.setSpaceWidth(720);
			attri.setAllowedOpentype(2);
			attri.setSpacePosition(1);
			attri.setImgMaxSize(100);
			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
			/*imgFormats.add(AdSpaceImgFmt.GIF);*/imgFormats.add(AdSpaceImgFmt.JPG);
			attri.setImgFormats(imgFormats);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.APP));
			attri.setTitleMin(0);
			attri.setTitleMax(100);
			attri.setCwMin(0);
			attri.setCwMax(100);
			attri.setJoinDspName(JoinDSPEmu.UNIPLAY.getAbbrev());
			attri.setAdSources(null);
			adreq.setUserAdSpaceAttri(attri);
			attri.setAllowedHtml(false);
			attri.setH5type(0);
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
			adreq.setNet("wifi");
			adreq.setHash(String.valueOf(System.currentTimeMillis()));
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("866867021441253");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("116.236.204.78");
			userInfo.setOsversion("5.0");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("ivvi SS1-01");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0374262c525");
			userInfo.setAaid("5071b66c660b118b");
			userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("ivvi");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("Mozilla/5.0 (Linux; Android 4.4.4; ivvi SS1-01 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
/*			userInfo.setLat("22.606714");
			userInfo.setLon("114.129928");*/
			adreq.setUserinfo(userInfo);
			
			
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
			attri.setJoinDspName(JoinDSPEmu.UNIPLAY.getAbbrev());
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
			adreq.setApp("meinvtuji");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			adreq.setHash(String.valueOf(System.currentTimeMillis()));
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("866867021441253");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("116.236.204.78");
			userInfo.setOsversion("5.0");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("ivvi SS1-01");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0374262c525");
			userInfo.setAaid("83baae01279c9860");
			userInfo.setAdid("83baae01279c9860");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("ivvi");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("Mozilla/5.0 (Linux; Android 4.4.4; SK3-02 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36");
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
			attri.setJoinDspName(JoinDSPEmu.UNIPLAY.getAbbrev());
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
			
			AdUserInfo userInfo = new AdUserInfo();
			userInfo.setOs("android");
			userInfo.setImei("866867021441253");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("116.236.204.78");
			userInfo.setOsversion("5.0");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("ivvi SS1-01");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("d0374262c525");
			userInfo.setAaid("5071b66c660b118b");
			//userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("ivvi");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("Mozilla/5.0 (Linux; Android 4.4.4; ivvi SS1-01 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
			userInfo.setLat("22.606714");
			userInfo.setLon("114.129928");
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
			attri.setJoinDspName(JoinDSPEmu.UNIPLAY.getAbbrev());
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
