package com.ocean.bid.service;

import org.springframework.stereotype.Component;

import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.service.ABaiduAdSupplier;
import com.ocean.proxy.api.service.AdHubAdSupplier;
import com.ocean.proxy.api.service.AdviewAdSupplier;
import com.ocean.proxy.api.service.AdwoAdSupplier;
import com.ocean.proxy.api.service.BoclickAdSupplier;
import com.ocean.proxy.api.service.BoruiAdSupplier;
import com.ocean.proxy.api.service.BoyuAdSupplier;
import com.ocean.proxy.api.service.BoyuV2AdSupplier;
import com.ocean.proxy.api.service.DianguanAdSupplier;
import com.ocean.proxy.api.service.DiankaiAdSupplier;
import com.ocean.proxy.api.service.FireBirdAdSupplier;
import com.ocean.proxy.api.service.FlyingAdSupplier;
import com.ocean.proxy.api.service.FmobiAdSupplier;
import com.ocean.proxy.api.service.HelianAdSupplier;
import com.ocean.proxy.api.service.HuixuanAdSupplier;
import com.ocean.proxy.api.service.HuzhongAdSupplier;
import com.ocean.proxy.api.service.ICloudAdSupplier;
import com.ocean.proxy.api.service.InmobiAdSupplier;
import com.ocean.proxy.api.service.JichengAdSupplier;
import com.ocean.proxy.api.service.JiekuAdSupplier;
import com.ocean.proxy.api.service.JingzhunAdSupplier;
import com.ocean.proxy.api.service.JugaoAdSupplier;
import com.ocean.proxy.api.service.LingjiAdSupplier;
import com.ocean.proxy.api.service.MaijikeAdSupplier;
import com.ocean.proxy.api.service.MexAdSupplier;
import com.ocean.proxy.api.service.MiquwanAdSupplier;
import com.ocean.proxy.api.service.OnemobAdSupplier;
import com.ocean.proxy.api.service.PaerjieteOLAdSupplier;
import com.ocean.proxy.api.service.PairuiAdSupplier;
import com.ocean.proxy.api.service.QidianAdSupplier;
import com.ocean.proxy.api.service.RedStoneAdSupplier;
import com.ocean.proxy.api.service.RuanGaoYunAdSupplier;
import com.ocean.proxy.api.service.RuishiAdSupplier;
import com.ocean.proxy.api.service.RyanAdSupplier;
import com.ocean.proxy.api.service.ShaiboAdSupplier;
import com.ocean.proxy.api.service.ShenmiAdSupplier;
import com.ocean.proxy.api.service.SouyingAdSupplier;
import com.ocean.proxy.api.service.TaylorAdSupplier;
import com.ocean.proxy.api.service.TianmeiAdSupplier;
import com.ocean.proxy.api.service.ToutiaoAdSupplier;
import com.ocean.proxy.api.service.UniPlayAdSupplier;
import com.ocean.proxy.api.service.VamakerAdSupplier;
import com.ocean.proxy.api.service.WangxiangAdSupplier;
import com.ocean.proxy.api.service.WangyueAdSupplier;
import com.ocean.proxy.api.service.WangyueV2AdSupplier;
import com.ocean.proxy.api.service.WankaV1AdSupplier;
import com.ocean.proxy.api.service.WeiyuAdSupplier;
import com.ocean.proxy.api.service.WosoAdSupplier;
import com.ocean.proxy.api.service.WuliAdSupplier;
import com.ocean.proxy.api.service.XDAdSupplier;
import com.ocean.proxy.api.service.XianguoAdSupplier;
import com.ocean.proxy.api.service.XuanyinAdSupplier;
import com.ocean.proxy.api.service.XunfaAdSupplier;
import com.ocean.proxy.api.service.XunfeiAdSupplier;
import com.ocean.proxy.api.service.YidianzxAdSupplier;
import com.ocean.proxy.api.service.YijifenAdSupplier;
import com.ocean.proxy.api.service.YileyunAdSupplier;
import com.ocean.proxy.api.service.YinchengAdSupplier;
import com.ocean.proxy.api.service.YingnaAdSupplier;
import com.ocean.proxy.api.service.YitongAdSupplier;
import com.ocean.proxy.api.service.YoudaoAdSupplier;
import com.ocean.proxy.api.service.YoukenAdSupplier;
import com.ocean.proxy.api.service.YouxiaoAdSupplier;
import com.ocean.proxy.api.service.YuanshengAdSupplier;
import com.ocean.proxy.api.service.ZKAdSupplier;
import com.ocean.proxy.api.service.ZhangYouAdSupplier;
import com.ocean.proxy.api.service.ZhongchengAdSupplier;
import com.ocean.proxy.api.service.ZhuoyiAdSupplier;
import com.ocean.proxy.api.service.ZhuoyiV2AdSupplier;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
@Component(value="adBidService")
public class AdBidServiceImpl implements AdBidService{
	//竞价预留接口
	@Override
	public JoinDSPEmu bid(AdRecomReq adreq) {
		// TODO Auto-generated method stub
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		String joinDspName=attri.getJoinDspName();
		JoinDSPEmu joinDs=JoinDSPEmu.getJoinDspByName(joinDspName);
		
		/*
		 * bid business code
		
		*/
        return joinDs;

	}
	@Override
	public AbstractAdSupplier getAdSupplier(AdRecomReq adreq) {
		JoinDSPEmu joinDs=bid(adreq);
		switch (joinDs) {
			case RYAN:
			    return (RyanAdSupplier)SystemContext.getServiceHandler().getService(RyanAdSupplier.class);
			case SHENMI:
				return (ShenmiAdSupplier)SystemContext.getServiceHandler().getService(ShenmiAdSupplier.class);
			case ONEMOB:
				return (OnemobAdSupplier)SystemContext.getServiceHandler().getService(OnemobAdSupplier.class);
			case LINGJI:
				return (LingjiAdSupplier)SystemContext.getServiceHandler().getService("lingjiAdSupplier");
			case ZHANGYOU:
			    return (ZhangYouAdSupplier)SystemContext.getServiceHandler().getService(ZhangYouAdSupplier.class);
			case YOUDAO:
			    return (YoudaoAdSupplier)SystemContext.getServiceHandler().getService(YoudaoAdSupplier.class);
			case XUNFEI:
			    return (XunfeiAdSupplier)SystemContext.getServiceHandler().getService(XunfeiAdSupplier.class);
			case SOUYING:
			    return (SouyingAdSupplier)SystemContext.getServiceHandler().getService(SouyingAdSupplier.class);
			case XIANGUO:
			    return (XianguoAdSupplier)SystemContext.getServiceHandler().getService(XianguoAdSupplier.class);
			case INMOBI:
			    return (InmobiAdSupplier)SystemContext.getServiceHandler().getService(InmobiAdSupplier.class);
			case ZHONGCHENG:
			    return (ZhongchengAdSupplier)SystemContext.getServiceHandler().getService(ZhongchengAdSupplier.class); 
			case TOUTIAO:
			    return (ToutiaoAdSupplier)SystemContext.getServiceHandler().getService(ToutiaoAdSupplier.class); 
			case YIJIFEN:
			    return (YijifenAdSupplier)SystemContext.getServiceHandler().getService(YijifenAdSupplier.class); 
			case ADVIEW:
			    return (AdviewAdSupplier)SystemContext.getServiceHandler().getService(AdviewAdSupplier.class);   
			case WANKA: 
				return (WankaV1AdSupplier)SystemContext.getServiceHandler().getService(WankaV1AdSupplier.class);  
			case REDSTONE:
			    return (RedStoneAdSupplier)SystemContext.getServiceHandler().getService(RedStoneAdSupplier.class);  
			case ADHUB:
			    return (AdHubAdSupplier)SystemContext.getServiceHandler().getService(AdHubAdSupplier.class);  
			case BOCLICK:
			    return (BoclickAdSupplier)SystemContext.getServiceHandler().getService(BoclickAdSupplier.class);  
			case JIEKU:
			    return (JiekuAdSupplier)SystemContext.getServiceHandler().getService(JiekuAdSupplier.class);  
			case TAYLOR:
			    return (TaylorAdSupplier)SystemContext.getServiceHandler().getService(TaylorAdSupplier.class); 
			case VAMAKER:
			    return (VamakerAdSupplier)SystemContext.getServiceHandler().getService(VamakerAdSupplier.class); 
			case RUANGAOYUN:
			    return (RuanGaoYunAdSupplier)SystemContext.getServiceHandler().getService(RuanGaoYunAdSupplier.class); 
			case SPEED:
				return (MiquwanAdSupplier)SystemContext.getServiceHandler().getService(MiquwanAdSupplier.class); 
			case JUGAO:
			    return (JugaoAdSupplier)SystemContext.getServiceHandler().getService(JugaoAdSupplier.class);
			case YINCHENG: 
				return (YinchengAdSupplier)SystemContext.getServiceHandler().getService(YinchengAdSupplier.class);
			case HELIAN: 
				return (HelianAdSupplier)SystemContext.getServiceHandler().getService(HelianAdSupplier.class);
			case HUIXUAN: 
				return (HuixuanAdSupplier)SystemContext.getServiceHandler().getService(HuixuanAdSupplier.class);
			case SHAIBO: 
				return (ShaiboAdSupplier)SystemContext.getServiceHandler().getService(ShaiboAdSupplier.class);
			case DIANKAI: 
				return (DiankaiAdSupplier)SystemContext.getServiceHandler().getService(DiankaiAdSupplier.class);
			case HUZHONG: 
				return (HuzhongAdSupplier)SystemContext.getServiceHandler().getService(HuzhongAdSupplier.class);
			case ZHUOYI: 
				return (ZhuoyiV2AdSupplier)SystemContext.getServiceHandler().getService(ZhuoyiV2AdSupplier.class);
			case ZHANGKU:
				return (ZKAdSupplier)SystemContext.getServiceHandler().getService(ZKAdSupplier.class);
			case XD:
				return (XDAdSupplier)SystemContext.getServiceHandler().getService(XDAdSupplier.class);
			case ICLOUD:
				return (ICloudAdSupplier)SystemContext.getServiceHandler().getService(ICloudAdSupplier.class);
			case UNIPLAY:
				return (UniPlayAdSupplier)SystemContext.getServiceHandler().getService(UniPlayAdSupplier.class);
			case 	MEX:
				return (MexAdSupplier)SystemContext.getServiceHandler().getService(MexAdSupplier.class);
			case	FMOBI:
				return (FmobiAdSupplier)SystemContext.getServiceHandler().getService(FmobiAdSupplier.class);
			case    WOSO:
				return (WosoAdSupplier)SystemContext.getServiceHandler().getService(WosoAdSupplier.class);
			case   ABAIDU: 
				return (ABaiduAdSupplier)SystemContext.getServiceHandler().getService(ABaiduAdSupplier.class);
			case   ADWO:
				return (AdwoAdSupplier)SystemContext.getServiceHandler().getService(AdwoAdSupplier.class);
			case   FIREBIRD:
				return (FireBirdAdSupplier)SystemContext.getServiceHandler().getService(FireBirdAdSupplier.class);
			case   YITONG:
				return (YitongAdSupplier)SystemContext.getServiceHandler().getService(YitongAdSupplier.class);
			case 	FLYING:
				return (FlyingAdSupplier)SystemContext.getServiceHandler().getService(FlyingAdSupplier.class);
			case   YUANSHENG:
				return (YuanshengAdSupplier)SystemContext.getServiceHandler().getService(YuanshengAdSupplier.class);
			case 	YOUXIAO:
				return (YouxiaoAdSupplier)SystemContext.getServiceHandler().getService(YouxiaoAdSupplier.class);
			case	YINGNA:
				return (YingnaAdSupplier)SystemContext.getServiceHandler().getService(YingnaAdSupplier.class);
				
			case	WANGXIANG:
				return (WangxiangAdSupplier)SystemContext.getServiceHandler().getService(WangxiangAdSupplier.class);
			case	WANGYUE:
				return (WangyueV2AdSupplier)SystemContext.getServiceHandler().getService(WangyueV2AdSupplier.class);
				
			case JICHENG:
				return (JichengAdSupplier)SystemContext.getServiceHandler().getService(JichengAdSupplier.class);
			case YOUKEN:
				return (YoukenAdSupplier)SystemContext.getServiceHandler().getService(YoukenAdSupplier.class);
			case YILEYUN:
				return (YileyunAdSupplier)SystemContext.getServiceHandler().getService(YileyunAdSupplier.class);
			case JINGZHUN:
				return (JingzhunAdSupplier)SystemContext.getServiceHandler().getService(JingzhunAdSupplier.class);
			case QIDIAN:
				return (QidianAdSupplier)SystemContext.getServiceHandler().getService(QidianAdSupplier.class);
			case BORUI:
				return (BoruiAdSupplier)SystemContext.getServiceHandler().getService(BoruiAdSupplier.class);
			case PAIRUI:
				return (PairuiAdSupplier)SystemContext.getServiceHandler().getService(PairuiAdSupplier.class);
			case BOYU:
				//return (BoyuAdSupplier)SystemContext.getServiceHandler().getService(BoyuAdSupplier.class);
				return (BoyuV2AdSupplier)SystemContext.getServiceHandler().getService(BoyuV2AdSupplier.class);
			case RUISHI:
				return (RuishiAdSupplier)SystemContext.getServiceHandler().getService(RuishiAdSupplier.class);
			case PAERJIATE:
				return (PaerjieteOLAdSupplier)SystemContext.getServiceHandler().getService(PaerjieteOLAdSupplier.class);
			case DIANGUAN:
				return (DianguanAdSupplier)SystemContext.getServiceHandler().getService(DianguanAdSupplier.class);
			case YIDIANZX:
				return (YidianzxAdSupplier)SystemContext.getServiceHandler().getService(YidianzxAdSupplier.class);
			case WEIYU:
				return (WeiyuAdSupplier)SystemContext.getServiceHandler().getService(WeiyuAdSupplier.class);
			case WULI:
				return (WuliAdSupplier)SystemContext.getServiceHandler().getService(WuliAdSupplier.class);
				
			case XUANYIN:
				return (XuanyinAdSupplier)SystemContext.getServiceHandler().getService(XuanyinAdSupplier.class);
			case MAIJIKE:
				return (MaijikeAdSupplier)SystemContext.getServiceHandler().getService(MaijikeAdSupplier.class);
			case XUNFA:
				return (XunfaAdSupplier)SystemContext.getServiceHandler().getService(XunfaAdSupplier.class);
			case TIANMEI:
				return (TianmeiAdSupplier)SystemContext.getServiceHandler().getService(TianmeiAdSupplier.class);
				
			default:
			    throw new BusinessException(ErrorCode.INTER_ERROR,"can not find the mapping server:"+joinDs.getAbbrev());
		}
	}

}
