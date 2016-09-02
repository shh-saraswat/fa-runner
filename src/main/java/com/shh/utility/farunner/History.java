package com.shh.utility.farunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.shh.utility.farunner.utils.FileUtils;

public class History {
	private List<String> historyList;
	private File historyFile;
	
	History(){
		historyFile = FileUtils.locateFileFromResource(FaRunner.HISTORY_FILE_NAME);
		historyList = new ArrayList<String>();
		readHistory();
	}
	
	private void readHistory(){
		this.historyList = FileUtils.readFileToList(historyFile);
	}
	
	public List<String> getHistoryList(){
		return this.historyList;
	}

	public void updateHistory(){
		FileUtils.writeListToFile(historyList, historyFile);
	}
	
	public void updateHistory(String newName){
		this.historyList.add(newName);
	}
	
	public List<String> getSuggestions(String regex) {

		  ArrayList<String> matches = new ArrayList<String>();

		  Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		  for (String name: this.historyList) {
		    if (pattern.matcher(name).matches()) {
		      matches.add(name);
		    }
		  }

		  return matches;
	}
}
