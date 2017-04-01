package com.ec.facilitator.base.util;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
public class JSONHelper {
	
	static ObjectMapper s_mapper = null;
	
	static{
		s_mapper = new ObjectMapper();
		s_mapper.disable(MapperFeature.AUTO_DETECT_GETTERS);
		s_mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS);
		s_mapper.disable(MapperFeature.AUTO_DETECT_SETTERS);
		s_mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		s_mapper.setDateFormat(new SimpleDateFormat(DatetimeUtil.JACKSON_DATE_TIME_FORMAT));
		s_mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	}
	
	public static <T> T readValue(String content, Class<T> valueType) throws Exception{
		return s_mapper.readValue(content, valueType);
	}
	
	public static <T> T readValue(String content, JSONTypeRef<T> valueTypeRef) throws Exception{
		return s_mapper.readValue(content, valueTypeRef);
	}
	
    public static <T> T readValueFromFile(String path, JSONTypeRef<T> valueTypeRef) throws Exception
    {
    	File file = new File(path);
    	if (file.exists()){
    		return s_mapper.readValue(file, valueTypeRef);
    	}
    	else{
    		return null;
    	}
    } 
	
    public static <T> T readValueFromFile(String path, Class<T> valueType) throws Exception
    {
    	File file = new File(path);
    	if (file.exists()){
    		return s_mapper.readValue(file, valueType);
    	}
    	else{
    		return null;
    	}
    } 
    
    /**
     * Method that can be used to serialize any Java value as
     * JSON output, written to File provided.
     */
    public static void writeValueToFile(String path, Object value) throws Exception
    {
    	s_mapper.writeValue(FileHelper.createNewFile(path), value);
    }
    
	/**
     * Method that can be used to serialize any Java value as
     * a String. Functionally equivalent to calling
     * {@link #writeValue(Writer,Object)} with {@link java.io.StringWriter}
     * and constructing String, but more efficient.
     *<p>
     * Note: prior to version 2.1, throws clause included {@link IOException}; 2.1 removed it.
     */
	public static String writeValueAsString(Object value) throws Exception{
		return s_mapper.writeValueAsString(value);
	}
}
