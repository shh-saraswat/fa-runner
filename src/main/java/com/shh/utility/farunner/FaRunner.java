package com.shh.utility.farunner;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.shh.utility.farunner.DataLoader.FlatFileLoader;
import com.shh.utility.farunner.DataLoader.Loader;

public class FaRunner {
	public static String DATA_VALUE_FILE_NAME = "dvfile.dat";
	public static String HISTORY_FILE_NAME = "history.dat";
	public static String ICON_FILE_NAME = "img/fa.png";

	private Loader loader;
	private History history;
	
	private File application;
	
	FaRunner(){
		loader = new FlatFileLoader();
		history = new History();
	}
	
	public List<String> getSuggestions(final String name){
		ArrayList<String> matches = new ArrayList<String>();
		matches.addAll(history.getSuggestions(name));	
		for(String suggest : loader.getSuggestions(name)){
			if(!matches.contains(suggest)){
				matches.add(suggest);
			}
		}
		
		return matches;
	}
	
	public boolean loadApplicationFile(String fileName){
		if(loader.getAppData().containsKey(fileName)){
			application = new File(loader.getAppData().get(fileName));
			return true;
		}
		return false;
	}
	
	public void runApplication(){
		Desktop desktop = Desktop.getDesktop();
	    try {
			desktop.open(application);
		} catch (IOException e) {
			return;
		}
	}

	public void updateHistory(String searchField) {
		history.updateHistory(searchField);		
	}
}
