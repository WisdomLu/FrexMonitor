package com.ocean.proxy.server;

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
public class ProxyClient {

//	public static final String SERVER_IP = "120.27.119.130";
//	public static final String SERVER_IP = "localhost";
//	public static final String SERVER_IP = "115.29.48.209";
//	public static final String SERVER_IP = "139.129.201.76";
//	public static final String SERVER_IP = "123.56.241.125";
//	public static final String SERVER_IP = "139.129.214.178";
//	public static final String SERVER_IP = "121.42.47.109";//公网服务器
	//public static final String SERVER_IP = "114.215.220.220";
	public static final String SERVER_IP = "127.0.0.1";
	//public static final String SERVER_IP = "121.42.47.109";
//	#--搏点 8090,灵集 8096,泰莱 8094,讯飞 8096,万流�? 8098,mex 8100,
//	adview 8102,玩咖 8104,头条 8106,光音 8108,搜影 8110,onemob 8112, 有道 8114
//	众橙 8116, inmobi 8118, 瑞恩 8120, 捷酷 8122, 仙果 8124
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 8000;
	
	private static final int zkpos = 222;
	/**
	 * @param userName
	 */
	public AdRecomReply startClient(String userName) {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
//			 TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
//			HelloWorldService.Client client = new HelloWorldService.Client(protocol);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
//			String result = client.sayHello(userName); 
			
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("Alphago主题商店");
			adreq.setType("ALL");
			adreq.setVersion("1.0");
//			adreq.setResult_num(999);
			adreq.setOgin_name("zk");
			AdUserInfo userInfo = new AdUserInfo();
			String imei = UniversalUtils.randomNumber(14);
//			System.out.println(imei);
			userInfo.setOs("iOS");
			userInfo.setImei(imei);
			userInfo.setImsi("28038907135691");
//			userInfo.setImei("28038907135691");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("58.221.56.202");
			userInfo.setOsversion("5.1");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("p8");
			userInfo.setMobile("CMCC");
			userInfo.setCity("Shenzhen");
			userInfo.setMac("04:02:1F:B7:8F:8D");
//			userInfo.setAaid("9774D56D6321349C");
			userInfo.setAdid("9774D56D6321349C");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("huawei");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("Mozilla/5.0(Linux;Android4.0.4;GT-I9220 Build/IMM76D)");
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);		
			
			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(999);
			attri.setTitleMin(0);
			attri.setCwMax(999);
//			attri.setSpaceType(AdSpaceType.INFOFLOW);
//			attri.setAdSpaceId(Position.P3.getId());
//			attri.setSpaceType(AdSpaceType.OPENING);
//			attri.setAdSpaceId(Position.P1.getId());
			attri.setSpaceHeight(100);
			attri.setSpaceWidth(640);
			userInfo.setOs(BaseAdSupplier.OS_IOS);
			userInfo.setDevice_height(1920);
			userInfo.setDevice_width(1080);
//			userInfo.setImsi("460011901617139");
			attri.setSpaceType(AdSpaceType.BANNER);
			attri.setAdSpaceId(zkpos);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.VIDEO));
			//attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.VIDEO));
			attri.setJoinDspName(JoinDSPEmu.LINGJI.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("1", adreq);
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
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		
//		ProxyClient client = new ProxyClient();
//		AdRecomReply rs = client.startClient("Michael");
//		System.out.println(rs);
		
		int i = 1;
		while(i > 0){
			ProxyClient client = new ProxyClient();
			AdRecomReply rs = client.startClient("Michael");
			//AdRecomReply rs = client.test2();
			System.out.println(rs);
			i--;
		}
		
//		int se = 0, ce = 0, le = 0, pe = 0, ie = 0, de = 0, count = 193;
//		HttpClient httpClient = HttpClient.getInstance();
//		for (int i = count; i > 0; i--) {
//			
//			ProxyClient client = new ProxyClient();
//			AdRecomReply rs;
//			rs = client.startClient("Michael");
//			System.out.println(rs);
//			AdContent content = rs.getAd_content();
//			if(content == null){
//				pe ++;
//				continue;
//			}
//			AdMutiAction action = content.getMutiAction().get(0);
//			try {
//				httpClient.get(action.getLinkurl());
//			} catch (HttpInvokeException e1) {
//				e1.printStackTrace();
//				le ++;
//			}
//			
//			Map<String, List<String>> urls = content.getThirdReportLinks();
//			// 曝光链接
//			List<String> showUrls = urls.get(DSPProxy.SHOW);
//			for (String string : showUrls) {
//				if(StringUtils.isEmpty(string)){
//					continue;
//				}
//				string = string.replace("%%WIN_PRICE%%", "50");
//				try {
//					httpClient.get(string);
//				} catch (HttpInvokeException e) {
//					e.printStackTrace();
//					se ++;
//				}
//			}
//			List<String> clickUrls= urls.get(DSPProxy.CLICK);
//			for (String string : clickUrls) {
//				if(StringUtils.isEmpty(string)){
//					continue;
//				}
//				try {
//					httpClient.get(string);
//				} catch (HttpInvokeException e) {
//					e.printStackTrace();
//					ce ++;
//				}
//			}
//			List<String> downUrls= urls.get(DSPProxy.DOWNLOAD);
//			for (String string : downUrls) {
//				if(StringUtils.isEmpty(string)){
//					continue;
//				}
//				try {
//					httpClient.get(string);
//				} catch (HttpInvokeException e) {
//					e.printStackTrace();
//					de ++;
//				}
//			}
//			List<String> installUrls= urls.get(DSPProxy.INSTALL);
//			for (String string : installUrls) {
//				if(StringUtils.isEmpty(string)){
//					continue;
//				}
//				try {
//					httpClient.get(string);
//				} catch (HttpInvokeException e) {
//					e.printStackTrace();
//					ie ++;
//				}
//			}
//			System.out.println("虚拟流量数还剩：" + i);
//		}
//		System.out.println("请求次数�?" + count 
//				+ "，广告拉取失败次数：" + pe
//				+ "，落地页链接访问失败次数�?" + le
//				+ "，曝光监测链接访问失败次数：" + se
//				+ "，点击监测链接访问失败次数：" + ce
//				+ "，下载监测链接访问失败次数：" + de
//				+ "，安装监测链接访问失败次数：" + ie);
		
		
		
//		String str = "\\345\\215\\223\\346\\230\\223\\345\\244\\251\\346\\260\\224";
//		Pattern pattern = Pattern.compile("\\\\");
//		String [] strs = pattern.split(str);
//		StringBuffer sb = new StringBuffer();
//		for(String s : strs){
//		    if(s != null && s.trim().length() > 0){
//		        String st = Integer.toHexString(Integer.valueOf(s,8));
//		        sb.append("%").append(st);
//		    }
//		}
//		try {
//		    System.out.println(URLDecoder.decode(sb.toString(), "utf-8"));
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
	}
	public AdRecomReply test2(){
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
//			System.out.println(imei);
			userInfo.setOs("android");
			userInfo.setImei("861275031082429");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("36.74.12.213");
			userInfo.setOsversion("23");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("Coolpad E502");
			//userInfo.setMobile("");
			//userInfo.setCity("150100");
			//userInfo.setMac("40cc5d9703a840d52f5c0053ad905424");
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
//			attri.setSpaceType(AdSpaceType.INFOFLOW);
//			attri.setAdSpaceId(Position.P3.getId());
			attri.setAppId(129);
			attri.setSpaceType(AdSpaceType.OPENING);
			attri.setAdSpaceId(zkpos);
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
			attri.setJoinDspName(JoinDSPEmu.ZHANGYOU.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("1", adreq);
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
	
	public AdRecomReply test(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiProxy.Client client = new ApiProxy.Client(protocol);
			transport.open();
			AdRecomReq adreq = new AdRecomReq();
			adreq.setApp("Mixadx");
			adreq.setType("ALL");
			adreq.setVersion("1.0");
			adreq.setResult_num(1);
			adreq.setOgin_name("40cc5d9703a840d52f5c0053ad905424");
			adreq.setLog(true);
			adreq.setToken("10");
			adreq.setRver(0);
			adreq.setCheck_ver(false);
			AdUserInfo userInfo = new AdUserInfo();
			String imei = UniversalUtils.randomNumber(14);
//			System.out.println(imei);
			userInfo.setOs("android");
			userInfo.setImei("40cc5d9703a840d52f5c0053ad905424");
			
			// 116.226.72.4 58.221.56.201
			userInfo.setClient_ip("223.104.15.230");
			userInfo.setOsversion("4.4.2");
//			userInfo.setPhonemodel("iPhone 7");
			userInfo.setPhonemodel("Meizu MX4");
			//userInfo.setMobile("");
			userInfo.setCity("150100");
			userInfo.setMac("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setAaid("9774D56D6321349C");
			userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//			userInfo.setBrand_name("Apple");
			userInfo.setBrand_name("unknow");
//			userInfo.setIdfa("160e18a715ccea95");
			userInfo.setUa("curl/7.35.0");
			userInfo.setDevice_height(1920);
			userInfo.setDevice_width(1152);
//			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
			adreq.setUserinfo(userInfo);

			UserAdSpaceAttri attri = new UserAdSpaceAttri();
			attri.setTitleMax(999);
			attri.setTitleMin(0);
			attri.setCwMax(999);
//			attri.setSpaceType(AdSpaceType.INFOFLOW);
//			attri.setAdSpaceId(Position.P3.getId());
			attri.setAppId(78);
			attri.setSpaceType(AdSpaceType.OPENING);
			attri.setAdSpaceId(451);
			attri.setSpaceHeight(360);
			attri.setSpaceWidth(640);
			attri.setAllowedOpentype(0);
			attri.setSpacePosition(0);
			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
			imgFormats.add(AdSpaceImgFmt.GIF);imgFormats.add(AdSpaceImgFmt.JPG);
			attri.setImgFormats(imgFormats);
			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK));
			attri.setTitleMin(0);
			attri.setTitleMax(100);
			attri.setCwMin(0);
			attri.setCwMax(100);
			attri.setJoinDspName(JoinDSPEmu.LINGJI.getAbbrev());
			adreq.setUserAdSpaceAttri(attri);
			AdRecomReply result = client.poll("1", adreq);
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
