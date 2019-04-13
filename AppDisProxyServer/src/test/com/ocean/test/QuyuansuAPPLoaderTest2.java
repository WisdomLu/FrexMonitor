package com.ocean.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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

public class QuyuansuAPPLoaderTest2 {
    //public static final String SERVER_IP = "114.215.17.214";
	
    public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9099;
	public static final int TIMEOUT = 10000;
	private static final int zkpos = 253;
	
	@Test
	public void test(){
		for(int i=0;i<1;i++){
			 getAppList();
		}
	}
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
			userInfo.setUid(UUID.randomUUID().toString().replaceAll("_", "")+new Random().nextInt(1000000));
			userInfo.setNet("wifi");
			userInfo.setCity("深圳");
			userInfo.setClientIp("111.174.133.21");
			userInfo.setUa("Mozilla/5.0 (Linux; Android 5.1.1; ivvi SS1-03 Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36");
			adreq.setUserInfo(userInfo);
			
			//设备信息
			DeviceInfo device=new DeviceInfo();
			device.setOs("android");
			device.setImei("99000563925"+(int)(Math.random()*10000));
			//device.setImei("861054034561147");
			device.setOsversion("20");
			device.setOsVerName("5.0");
			device.setPhonemodel("ivvi SS1-03");
			device.setMobile("CMCC");
			device.setMac("d0374258e915");
			device.setAaid("a419d3463d15471f");
			device.setAdid("a419d3463d15471f");
			device.setBrandName("coolpad");
			device.setDeviceHeight(640);
			device.setDeviceWidth(360);
			device.setImsi("460110361034090");
			
			device.setDpi("200");
			device.setDip("3.5");
			device.setSerialNo("L3610S004762307327");
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
			interParam.setPkgName("com.UCMobile");
			adreq.setInterParam(interParam);
			
            adreq.setAppSpaceInfo(space);
            adreq.setJoinSource(JoinSource.QUYUANSU2_APPSTORE);
            adreq.setInterType(InterType.APP_RECOMMEND_SEARCH);
            
            //设置平台申请参数
            List<ExtData> extL=new ArrayList<ExtData>();
            ExtData from=new ExtData();
            from.setKey("id");
            ///from.setValue("uuappstore");
           // from.setValue("duocaisearch");
           // from.setValue("duocairecomb");
           // from.setValue("duocaitag01");
            from.setValue("duocaiv3");
            
            extL.add(from);
            ExtData token=new ExtData();
            token.setKey("token");
           // token.setValue("9DF583B33AAF6C40D3FEA6D861F4E1C2");
            //token.setValue("E6464E983DDBC7BA1B6DDCFF4D47094F");
            //token.setValue("A7FE2F73BDC42CD8C0FB2B470945ADB5");
           // token.setValue("30F7EBDE99881AC599A8E76F29E82729");
            token.setValue("EAD13441CC77020313769BA376D9FF53");
            extL.add(token);
            
            
            adreq.setJoinParam(extL);
            AppDisRecomReply result = client.search(adreq);
			System.out.println(result);
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
