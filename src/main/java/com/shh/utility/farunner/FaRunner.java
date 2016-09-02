package com.shh.utility.farunner;

import java.util.ArrayList;
import java.util.List;

import com.shh.utility.farunner.DataLoader.FlatFileLoader;
import com.shh.utility.farunner.DataLoader.Loader;

public class FaRunner {
	public static String DATA_VALUE_FILE_NAME = "";
	public static String HISTORY_FILE_NAME = "";

	private Loader loader;
	private History history;
	
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
}
