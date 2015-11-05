package com.beyole.util;

import java.sql.Timestamp;

import org.codehaus.jackson.map.module.SimpleDeserializers;

public class BaseDeserializers extends SimpleDeserializers {
	public BaseDeserializers(){
		addDeserializer(Timestamp.class, new BaseTimestampDeserializer());
	}
}
