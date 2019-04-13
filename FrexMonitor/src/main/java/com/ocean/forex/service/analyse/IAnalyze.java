package com.ocean.forex.service.analyse;

import com.ocean.forex.common.MonitorException;
import com.ocean.forex.entity.analyzedata.AbstractAnalyticalData;
import com.ocean.forex.entity.event.base.AbstractEvent;
import com.ocean.forex.entity.event.base.DefaultEvent;
import com.ocean.forex.entity.strategy.AbstractStrategy;

public interface IAnalyze {
	public AbstractEvent analyze(AbstractAnalyticalData ad,AbstractStrategy st) throws MonitorException;
}
