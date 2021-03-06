package com.shh.utility.farunner.DataLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.shh.utility.farunner.FaRunner;
import com.shh.utility.farunner.utils.FileUtils;

public class FlatFileLoader implements Loader {
	
	private File metaDataFile;
	private Map<String, String> dataValue;
	
	public FlatFileLoader(){
		metaDataFile = FileUtils.locateFileFromResource(FaRunner.DATA_VALUE_FILE_NAME);
		dataValue = new HashMap<String, String>();
		loadDataToMap();
	}
	
	public File getFile() {
		return metaDataFile;
	}

	public void loadDataToMap() {	
	    int flag = 0;
	    String key = "";
	    String value;
	    for(String line : FileUtils.readFileToList(metaDataFile)) {
	        if(flag%2 == 0){
	        		 key = line;
	        }else{
	        		 value = line;
	        		if(key.isEmpty() || value.isEmpty()){
	        			break;
	        		}
	        		 dataValue.put(key, value);
	    	}
	        	 flag++;
	    }
	}

	public Map<String, String> getAppData() {
		return dataValue;
	}
	
	public String getValue(String key){
		return dataValue.get(key);
	}
	
	public List<String> getSuggestions(String regex) {
		ArrayList<String> matches = new ArrayList<String>();

		Iterator<Entry<String, String>> it = dataValue.entrySet().iterator();
		while (it.hasNext()) {
			  Map.Entry pair = (Map.Entry)it.next();
		    if (pair.getKey().toString().toLowerCase().contains(regex.toLowerCase())) {
		    	matches.add(pair.getKey().toString());
		    }
		}
		return matches;
	}

}
