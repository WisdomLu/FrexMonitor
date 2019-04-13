package com.ocean.forex.service.notify;

import com.ocean.forex.entity.event.base.AbstractEvent;
public interface IAlert {
	public void notify(AbstractEvent event);
}
