package com.pseudofunc.util;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	private static final Logger log = LoggerFactory.getLogger(JSONUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	// commenting and making it generic by using generic.
//	public static VendorPortalTestData getTestData(String path) {
//		try(InputStream stream = ResourceLoader.getResource(path)){
//			return mapper.readValue(stream, VendorPortalTestData.class);
//		}catch(Exception e) {
//			log.error("unable to fetch file from path: {}", path, e);
//		}
//		
//		return null;
//	}
	
	public static <T> T getTestData(String path, Class<T> type) {
		try(InputStream stream = ResourceLoader.getResource(path)){
			return mapper.readValue(stream, type);
		}catch(Exception e) {
			log.error("unable to fetch file from path: {}", path, e);
		}
		
		return null;
	}
	
	
}
