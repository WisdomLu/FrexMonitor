package com.ocean;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import redis.clients.jedis.JedisPool;

import com.ocean.core.common.UniversalUtils;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceImgFmt;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.ApiProxy;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.proxy.api.base.BaseAdSupplier;

/**
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class YijifenProxyClient {

//	public static final String SERVER_IP = "120.27.119.130";
//	public static final String SERVER_IP = "localhost";
//	public static final String SERVER_IP = "115.29.48.209";
//	public static final String SERVER_IP = "139.129.201.76";
//	public static final String SERVER_IP = "123.56.241.125";
//	public static final String SERVER_IP = "139.129.214.178";
//	public static final String SERVER_IP = "121.42.47.109";//公网服务器
	public static final String SERVER_IP = "127.0.0.1";
	//public static final String SERVER_IP = "114.215.17.214";
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 8000;
	private static final int zkpos = 247;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		
		int i = 1;
		while(i > 0){
			YijifenProxyClient client = new YijifenProxyClient();
		   // AdRecomReply rs = client.startClient_opening();
			//AdRecomReply rs = client.startClient_banner();
			 AdRecomReply rs = client.startClient_infoflow();
			//System.out.println(DigestUtils.md5Hex("Readmoon123456"));;
			System.out.println(rs);
			i--;
		}

	}
	//开屏
	public AdRecomReply startClient_opening(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("AlphaGo 主题商店");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			
			AdUserInfo userInfo = new AdUserInfo();
			String imei = UniversalUtils.randomNumber(14);
			userInfo.setOs("android");
			userInfo.setImei("861275031082429");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("36.74.12.213");
			userInfo.setOsversion("5.0");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("Coolpad E502");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setAaid("9774D56D6321349C");
			//userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("coolpad");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("mozilla/5.0 (linux; android 6.0; coolpad e502 build/mra58k; wv) applewebkit/537.36 (khtml, like gecko) version/4.0 chrome/55.0.2883.91 mobile safari/537.36");
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
			//banner的固定尺寸是720*120的。开屏是720*1280的。信息流尺寸可以勾选
			attri.setSpaceHeight(720);
			attri.setSpaceWidth(1280);
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
			attri.setJoinDspName(JoinDSPEmu.YIJIFEN.getAbbrev());
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
	//开屏
	public AdRecomReply startClient_banner(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("AlphaGo 主题商店");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			
			AdUserInfo userInfo = new AdUserInfo();
			String imei = UniversalUtils.randomNumber(14);
			userInfo.setOs("android");
			userInfo.setImei("861275031082429");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("36.74.12.213");
			userInfo.setOsversion("5.0");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("Coolpad E502");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setAaid("9774D56D6321349C");
			//userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("coolpad");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("mozilla/5.0 (linux; android 6.0; coolpad e502 build/mra58k; wv) applewebkit/537.36 (khtml, like gecko) version/4.0 chrome/55.0.2883.91 mobile safari/537.36");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(64);
			attri.setSpaceType(AdSpaceType.BANNER);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
			//banner的固定尺寸是720*120的。开屏是720*1280的。信息流尺寸可以勾选
			attri.setSpaceHeight(720);
			attri.setSpaceWidth(120);
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
			attri.setJoinDspName(JoinDSPEmu.YIJIFEN.getAbbrev());
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
	//开屏
	public AdRecomReply startClient_infoflow(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("AlphaGo 主题商店");
			adreq.setType("");
			adreq.setVersion("3.5.6");
			adreq.setResult_num(1);
			adreq.setOgin_name("");
			adreq.setLog(true);
			adreq.setToken("7");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			
			AdUserInfo userInfo = new AdUserInfo();
			String imei = UniversalUtils.randomNumber(14);
			userInfo.setOs("android");
			userInfo.setImei("861275031082429");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("36.74.12.213");
			userInfo.setOsversion("19");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("Coolpad E502");
			userInfo.setMobile("CMCC");
			//userInfo.setCity("150100");
			userInfo.setMac("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setAaid("9774D56D6321349C");
			//userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("coolpad");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("mozilla/5.0 (linux; android 4.4.4; sk3-01 build/ktu84p) applewebkit/537.36 (khtml, like gecko) version/4.0 chrome/33.0.0.0 mobile safari/537.36");
			userInfo.setDevice_height(640);
			userInfo.setDevice_width(360);
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);
			
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(100);
			attri.setTitleMin(0);
			attri.setCwMax(100);
			attri.setAppId(64);
			attri.setSpaceType(AdSpaceType.INFOFLOW);
			attri.setAdSpaceId(zkpos);
			//1:BANNER 2:插屏 3:开屏 4.信息流
			//banner的固定尺寸是720*120的。开屏是720*1280的。信息流尺寸可以勾选
			attri.setSpaceHeight(300);
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
			attri.setJoinDspName(JoinDSPEmu.YIJIFEN.getAbbrev());
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
