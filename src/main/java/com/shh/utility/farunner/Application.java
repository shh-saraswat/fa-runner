package com.shh.utility.farunner;

import com.shh.utility.farunner.ui.SearchBox;

public class Application {
	public static void main(String[] arg){
		FaRunner farunner = new FaRunner();
		SearchBox searchBox = new SearchBox("Fa-Runner", farunner);
	}
}
