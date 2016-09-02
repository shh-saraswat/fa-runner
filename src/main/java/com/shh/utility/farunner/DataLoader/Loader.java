package com.shh.utility.farunner.DataLoader;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Loader {
	
	/*
	 * return filename with full path
	 */
	public File getFile();

	/*
	 * parse the metadata file and convert into Map
	 * return Map<String, String>
	 * Map application/file with there runner file
	 */	
	public void loadDataToMap();
	
	/*
	 * return Map<String, String>
	 * the Application data
	 */	
	public Map<String, String> getAppData();
	
	/*
	 * return suggestion from dat
	 */
	public List<String> getSuggestions(String regex);	
}
