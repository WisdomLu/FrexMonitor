package com.ocean.forex.service.dataAsyn;

import java.util.List;
import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.common.MonitorException;
import com.ocean.forex.entity.ExchangeRate;

public interface IDataAsynService {
   public List<ExchangeRate> getExchangeRate(FrexCurrency fc)  throws MonitorException ;

   
}
