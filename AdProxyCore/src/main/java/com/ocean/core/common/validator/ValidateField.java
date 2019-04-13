package com.ocean.core.common.validator;

public @interface ValidateField {
	ValidateType validateType() default ValidateType.OPTIOAL;//the validate type
	String name() default "";//parameter name
	DataType dataType() default DataType.NULL;
	
}
