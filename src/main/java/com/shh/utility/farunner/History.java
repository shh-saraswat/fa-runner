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

	private void updateHistory(){
		FileUtils.writeListToFile(historyList, historyFile);
	}
	
	public void updateHistory(String newName){
		if(historyList.contains(newName)){
			return;
		}
		this.historyList.add(0, newName);
		updateHistory();
	}
	
	public List<String> getSuggestions(String regex) {

		  ArrayList<String> matches = new ArrayList<String>();

		  for (String name: this.historyList) {
		    if (name.contains(regex)) {
		      matches.add(name);
		    }
		  }

		  return matches;
	}
}
