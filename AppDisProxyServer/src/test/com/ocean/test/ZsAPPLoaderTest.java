package com.ocean.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecommend;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppSpaceInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.InterType;
import com.ocean.app.dis.proxy.thrift.entity.JoinSource;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;

public class ZsAPPLoaderTest {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9099;
	public static final int TIMEOUT = 10000;
	private static final int zkpos = 253;
	
	@Test
	public void getAppList(){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			AppDisRecommend.Client client = new AppDisRecommend.Client(protocol);
			transport.open();
			AppDisRecomReq adreq = new AppDisRecomReq();
			
			//用户信息 
			UserInfo userInfo=new UserInfo();
			userInfo.setUid(""+new Random().nextInt(1000000000));
			userInfo.setNet("wifi");
			userInfo.setCity("深圳");
			userInfo.setClientIp("111.174.133.21");
			userInfo.setUa("Mozilla/5.0 (Linux; Android 5.1.1; ivvi SS1-03 Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36");
			userInfo.setLon("20.0");
			userInfo.setLat("20");
			adreq.setUserInfo(userInfo);
			
			//设备信息  so 
			DeviceInfo device=new DeviceInfo();
			device.setPhonemodel("8298-A01");//型号
			device.setAdid("a419d3463d15471f");//androidid
			device.setSerialNo("L3610S004762307327");
			device.setBrandName("华为");//手机产商
			device.setOs("android");
			device.setOsversion("20");
			device.setOsVerName("5.1");
			device.setMobile("CMCC");//运营商
			device.setDeviceHeight(640);
			device.setDeviceWidth(360);
			device.setDip("3.5");
			device.setDpi("200");
			device.setMac("d0374258e979");
			device.setImei("866011032262875");
			device.setImsi("460110361034090");
			adreq.setDevice(device);
	
			
			//广告位信息
			AppSpaceInfo space=new AppSpaceInfo();
			space.setAppId(61);
			space.setAdSpaceId(zkpos);
			space.setSpaceHeight(640);
			space.setSpaceWidth(9601);
			
			AppInfo app=new  AppInfo();
			app.setPkgName("com.ivvi.android.appstore");
			app.setType(AppType.TYPE_APP);
			app.setAppName("ivvi应用商店");
			app.setVersionCode("13");
			app.setVersionName("2.0.27");
			adreq.setAppInfo(app);
			
			InterParam interParam=new InterParam();
			interParam.setKeyWord("uc浏览器");
			interParam.setHotWord("新闻");
			//interParam.setPkgName("com.ss.android.ugc.live");
			adreq.setInterParam(interParam);
			
            adreq.setAppSpaceInfo(space);
            adreq.setJoinSource(JoinSource.ZS_APPSTORE);
            adreq.setInterType(InterType.APP_LIST_SEARCH);
            //adreq.setInterType(InterType.PKG_SEARCH);//T2
            

            //设置平台申请参数 
            List<ExtData> extL=new ArrayList<ExtData>();
            
            ExtData token=new ExtData();
            token.setKey("id");
//            token.setValue("11018");
            token.setValue("11019");//T2
            extL.add(token);
            
            ExtData key=new ExtData();
            key.setKey("key");
   //         key.setValue("ZDNlYjE4NzE5NTFjNDI5MGM0ZTFjNzAzYjUyZDU0ODI=");
            key.setValue("ZDAwZjUwNjllYjIxOTIxOTMwYmUzYmNjNWYzMDEzZTA=");//T2
            extL.add(key);
            
            adreq.setJoinParam(extL);
            
            AppDisRecomReply result = client.search(adreq);
            System.out.println("result="+result);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
}
