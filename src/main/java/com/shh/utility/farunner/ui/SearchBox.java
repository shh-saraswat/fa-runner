package com.shh.utility.farunner.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import com.shh.utility.farunner.FaRunner;

public class SearchBox extends JFrame implements ActionListener {
	
	private JTextField searchBoxField;
	private JButton showSearchButton;
	private JList suggestion;
	private FaRunner faRunner;
	
	SearchBox(String title, FaRunner faRunner){
		super(title);
		this.faRunner = faRunner;
		
		this.setLayout(new FlowLayout());
		this.setSize(300, 200);
		this.setVisible(true);
		this.setResizable(false);
		
		searchBoxField = new JTextField(50);
		suggestion = new JList();
		showSearchButton = new JButton("RUN");
		showSearchButton.addActionListener(this);
		

		this.add(searchBoxField);
		this.add(suggestion);
		this.add(showSearchButton);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void actionPerformed(ActionEvent e) {
		String searchField = this.searchBoxField.getText();
		if(!searchField.isEmpty()){
			
		}
	}
}
