package com.shh.utility.farunner.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtils {
	public static File locateFileFromResource(final String fileName){
		ClassLoader classLoader = FileUtils.class.getClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}
	/*
	 * Read each line of file and return as a list 
	 */
	public static List<String> readFileToList(File file){
		List<String> allLines = new ArrayList<String>();
		try {
	    	 FileReader reader = new FileReader(file);
	    	 BufferedReader bufferedReader = new BufferedReader(reader);
	         String line;
	         while ((line = bufferedReader.readLine()) != null) {
	        	 allLines.add(line);
	         }
	         bufferedReader.close();
	         reader.close();
	      } catch (IOException e) {
	    	return Collections.emptyList();
		}
		return allLines;
	}

	/*
	 * Write list to file
	 * return true on success 
	 */
	public static boolean writeListToFile(List<String> allLines, File file){
		Path filePath = Paths.get(file.getAbsolutePath());
		try {
			Files.write(filePath, allLines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
