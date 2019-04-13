package com.ocean.bid.service;

import org.springframework.stereotype.Component;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.JoinSource;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.BaiduDownloaderAppSupplier;
import com.ocean.service.BaitongDownloaderAppSupplier;
import com.ocean.service.DaluomaAppStoreAppSupplier;
import com.ocean.service.FuxiQQDownloaderAppSupplier;
import com.ocean.service.HaiqibingDownloaderSupplier;
import com.ocean.service.MiliangDownloaderSupplier;
import com.ocean.service.OnemobiDownloaderAppSupplier;
import com.ocean.service.QQDownloaderAppSupplier;
import com.ocean.service.Qihoo360T2AppStoreAppSupplier;
import com.ocean.service.QihooAppStoreAppSupplier;
import com.ocean.service.QuyuansuDownloaderSupplier;
import com.ocean.service.Quyuansu2DownloaderSupplier;
import com.ocean.service.TianmeiDownloaderSupplier;
import com.ocean.service.WankaDownloaderAppSupplier;
import com.ocean.service.WanyujxDownloaderSupplier;
import com.ocean.service.WxCpdDownloaderSupplier;
import com.ocean.service.YitongDownloaderSupplier;
import com.ocean.service.YouranDownloaderSupplier;
import com.ocean.service.YouranT2DownloaderSupplier;
import com.ocean.service.YouyouAppStoreAppSupplier;
import com.ocean.service.ZKDownloaderAppSupplier;
import com.ocean.service.ZsDownloaderSupplier;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
@Component(value="appDisBidServiceImpl")
public class AppDisBidServiceImpl implements AppDisBidService{

	public JoinSource bid(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		JoinSource joingSource=req.getJoinSource();
	    if(joingSource==null){
	    	throw new BusinessException(ErrorCode.PARAM_ERROR,"parameter joinsourse is empty!");

	    }
		/*
		 * bid business code
		
		*/
		return joingSource;

	}
	public AbstractAppDisSupplier getAdSupplier(AppDisRecomReq adreq) throws AppDisException {
		JoinSource joingSource=bid(adreq);
		switch (joingSource) {
			case TENCENT_QQDOWNLOADER:
			    return (QQDownloaderAppSupplier)SystemContext.getServiceHandler().getService(QQDownloaderAppSupplier.class);
			case QIHOO_APPSTORE:
			    return (QihooAppStoreAppSupplier)SystemContext.getServiceHandler().getService(QihooAppStoreAppSupplier.class);
			case BAIDU_APPSEARCH:
			    return (BaiduDownloaderAppSupplier)SystemContext.getServiceHandler().getService(BaiduDownloaderAppSupplier.class);
			case ONEMOBI_APPSTORE:
				return (OnemobiDownloaderAppSupplier)SystemContext.getServiceHandler().getService(OnemobiDownloaderAppSupplier.class);
				
		   //*************掌酷应用对接***********
			case SOGOU_ZHUSHOU:
				return (ZKDownloaderAppSupplier)SystemContext.getServiceHandler().getService(ZKDownloaderAppSupplier.class);

			case ANZHI_APPSTORE:
				return (ZKDownloaderAppSupplier)SystemContext.getServiceHandler().getService(ZKDownloaderAppSupplier.class);
			case PP_ASSISTANT:
				return (ZKDownloaderAppSupplier)SystemContext.getServiceHandler().getService(ZKDownloaderAppSupplier.class);
				
			case WANKA_APPSTORE:
				//return (ZKDownloaderAppSupplier)SystemContext.getServiceHandler().getService(ZKDownloaderAppSupplier.class);//
				return (WankaDownloaderAppSupplier)SystemContext.getServiceHandler().getService(WankaDownloaderAppSupplier.class);
			case	WANGXIANG_APPSTORE:
				return (WxCpdDownloaderSupplier)SystemContext.getServiceHandler().getService(WxCpdDownloaderSupplier.class);
			case HAIQIBING_APPSTORE:
				return (HaiqibingDownloaderSupplier)SystemContext.getServiceHandler().getService(HaiqibingDownloaderSupplier.class);
			//
			case QIHOO_TYPE2_APPSTORE:
				return (Qihoo360T2AppStoreAppSupplier)SystemContext.getServiceHandler().getService(Qihoo360T2AppStoreAppSupplier.class);
			case QUQUANSU_APPSTORE:
				return (QuyuansuDownloaderSupplier)SystemContext.getServiceHandler().getService(QuyuansuDownloaderSupplier.class);
			case QUYUANSU2_APPSTORE:
				return (Quyuansu2DownloaderSupplier)SystemContext.getServiceHandler().getService(Quyuansu2DownloaderSupplier.class);
			case YITONG_APPSTORE:
				return (YitongDownloaderSupplier)SystemContext.getServiceHandler().getService(YitongDownloaderSupplier.class);
			case YOURAN_APPSTORE:
				return (YouranDownloaderSupplier)SystemContext.getServiceHandler().getService(YouranDownloaderSupplier.class);
			case DALUOMA_APPSTORE:
				return (DaluomaAppStoreAppSupplier)SystemContext.getServiceHandler().getService(DaluomaAppStoreAppSupplier.class);
			case WANYUJIUXIN_APPSTORE:
				return (WanyujxDownloaderSupplier)SystemContext.getServiceHandler().getService(WanyujxDownloaderSupplier.class);
			case YOURAN_TYPE2_APPSTORE:
				return (YouranT2DownloaderSupplier)SystemContext.getServiceHandler().getService(YouranT2DownloaderSupplier.class);
			case BAIDUBAITONG_APPSTORE:
				return (BaitongDownloaderAppSupplier)SystemContext.getServiceHandler().getService(BaitongDownloaderAppSupplier.class);
			case	TIANMEI_APPSTORE :
				return (TianmeiDownloaderSupplier)SystemContext.getServiceHandler().getService(TianmeiDownloaderSupplier.class);
			case	MILIANG_APPSTORE :
				return (MiliangDownloaderSupplier)SystemContext.getServiceHandler().getService(MiliangDownloaderSupplier.class);
			case YOUYOU_APPSTORE:
				return (YouyouAppStoreAppSupplier)SystemContext.getServiceHandler().getService(YouyouAppStoreAppSupplier.class);
			case ZS_APPSTORE:
				return (ZsDownloaderSupplier)SystemContext.getServiceHandler().getService(ZsDownloaderSupplier.class);
			default:
			    throw new AppDisException(ErrorCode.INTER_ERROR,"do not find mapping server:"+joingSource.name());
		}
	}

}
