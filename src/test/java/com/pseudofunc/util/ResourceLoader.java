package com.pseudofunc.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * utility to read file.
 * first it will check the classpath. If found,we read the file, else we check the filesystem
 */

// I didn't relate to this class.
public class ResourceLoader {

	private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);
	
	public static InputStream getResource(String path) throws Exception {
		
		log.info("reading resource from location: {}", path);
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		
		if(Objects.nonNull(stream))
			return stream;
		
		return Files.newInputStream(Path.of(path));
	}
	
}
