package com.ocean.core.common.base;

import org.apache.commons.lang.builder.*;

public abstract class AbstractBaseEntity extends AbstractEntityObject {

	private static final long serialVersionUID = 6725613657960263070L;

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
