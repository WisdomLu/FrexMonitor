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
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;

public class DaluomaAPPLoaderTest {
    //public static final String SERVER_IP = "114.215.17.214";
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
			userInfo.setUid("866970031067595");
			userInfo.setNet("wifi");
			userInfo.setCity("南昌市");
			userInfo.setClientIp("117.21.66.113");
			userInfo.setUa("Dalvik/2.1.0 (Linux; U; Android 6.1; K-Touch X11 Build/MRA58K)");
			adreq.setUserInfo(userInfo);

			//设备信息
			DeviceInfo device=new DeviceInfo();
			device.setOs("android");
			device.setImei("866970031067595");
			device.setOsversion("23");
			device.setOsVerName("6.0");
			device.setPhonemodel("K-Touch X11");
			device.setMobile("CMCC");
			device.setMac("00:FF:F7:21:C3:1F");
			device.setAaid("a60d6176e5a3a767");
			device.setAdid("a60d6176e5a3a767");
			device.setBrandName("K-Touch");
			device.setDeviceHeight(1184);
			device.setDeviceWidth(720);
			device.setImsi("460079708534230");
			device.setDpi("320");
			device.setDip("2.0");
			adreq.setDevice(device);

			
			//广告位信息
			AppSpaceInfo space=new AppSpaceInfo();
			space.setAppId(61);
			space.setAdSpaceId(zkpos);
			space.setSpaceHeight(640);
			space.setSpaceWidth(9601);

			AppInfo app=new  AppInfo();
			app.setType(AppType.TYPE_APP);
			
			app.setPkgName("com.ibimuyu.appstore");
			app.setAppName("ivvi应用商店");
			app.setVersionCode("4712047");
			app.setVersionName("2.0.47");
			adreq.setAppInfo(app);
			
			InterParam interParam=new InterParam();
			interParam.setKeyWord("健身");
			interParam.setPkgName("com.mintq.xqd com.stdk.jq");//com.rong360.app   com.mintq.xqd com.stdk.jq
			adreq.setInterParam(interParam);
			
            adreq.setAppSpaceInfo(space);
            adreq.setJoinSource(JoinSource.DALUOMA_APPSTORE);
            adreq.setInterType(InterType.KEY_WORD_SEARCH);

            
            //设置平台申请参数
            List<ExtData> extL=new ArrayList<ExtData>();
            ExtData organization=new ExtData();
            organization.setKey("organization");
            //from.setValue("moyu");
            organization.setValue("1010");
            
            extL.add(organization);

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
