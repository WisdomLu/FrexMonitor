package com.ocean.core.common.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inveno.base.BaseModel;
import com.inveno.base.Pagin;
import com.inveno.util.DetachedCriteriaUtil;
import com.inveno.util.MemCachedUtil;
import com.inveno.util.PropertyUtils;
import com.inveno.util.StringUtil;
import com.ocean.core.common.AsignUtil;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.ReturnData;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.validator.ValidatorHandler;
/**
 * CRM实现顶层接口的抽象类
 * 
 * @author yaoyuan
 * @version 1.0 date 2008-04-27
 */
public abstract class AbstractBaseService {

	public static final Logger logger = LoggerFactory.getLogger(AbstractBaseService.class);
    /**
     * 通过放射机制来实现bean类给持久类的复制操作
     * @param form 源类对象
     * @param to 目标类对象
     * @param exceptParamsSet 指定不需要进行复制的属性集合
     * @return t Object
     * @author Alex 2015-10-25
     */
	public <M,N> N assign(M from,N to,String[] exceptParamArra) {
		AsignUtil<M,N> ad_reflexUtil=new AsignUtil<M,N>();
		try{
			to=ad_reflexUtil.dataExchange(from, to,exceptParamArra);
		}catch(Exception e){
			logger.error("assign error！", e);
		    throw new BusinessException(ErrorCode.INTER_ERROR,"method assign error！");
		}
		return to;
	}
	public  boolean validate(Object from) {
		try{
			ReturnData returnData= ValidatorHandler.getValidator().validate(from);
			if(ErrorCode.INTER_ERROR==returnData.getErrorCode()){
				logger.error("validate error:{}",returnData.getErrorMsg() );
				return false;
			}
		}catch(Exception e){
			logger.error("validate error！", e);
		    throw new BusinessException(ErrorCode.INTER_ERROR,"method validate error！");
		}

		return true;
	}
    /* 判断是否为数值类型
     * @since 2015-12-1
     * @Author Alex
     * @param str字符串
     * */
	public boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	 } 

}
