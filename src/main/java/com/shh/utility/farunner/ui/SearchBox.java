package com.shh.utility.farunner.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.shh.utility.farunner.FaRunner;
import com.shh.utility.farunner.utils.FileUtils;

public class SearchBox extends JFrame implements ActionListener, KeyListener {
	
	private JTextField searchBoxField;
	private JButton showSearchButton;
	private FaRunner faRunner;
	private String suggestedName = "";
	
	public SearchBox(String title, FaRunner faRunner){
		super(title);
		this.faRunner = faRunner;
		
		setFrameProperty();
		addElements();
		pack();
	}
	public void actionPerformed(ActionEvent e) {
		runApp();
	}

	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			runApp();
		}
		else{
			if(e.getKeyCode() < 32 || e.getKeyCode() > 126 
					|| e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_DOWN
					){
				return;
			}
			String searchField = this.searchBoxField.getText();
			
			if(searchField.trim().isEmpty()){
				return;
			}
			
			while(searchField.charAt(0) == ' '){
				searchField = searchField.substring(1);
			}
			
			List<String> suggestion = faRunner.getSuggestions(searchField);
			if(!suggestion.isEmpty()){
				for(String suggest : suggestion){
					if(suggest.toLowerCase().startsWith(searchField.toLowerCase())){
						suggestedName = suggestion.get(0);
						
						int selectionStart = searchField.length();
						int selectionEnd = suggestedName.length();
						
						searchField = searchField.concat(suggestedName.substring(selectionStart));
						
						this.searchBoxField.setText(searchField);
						
						searchBoxField.select(selectionStart, selectionEnd);
						break;
					}
				}
			}
		}
	}
	
	private void runApp() {
		String searchField = this.searchBoxField.getText();
		if(searchField.isEmpty()){
			return;
		}
		if(suggestedName.equalsIgnoreCase(searchField)){
			searchField = suggestedName; 
		}
		if(faRunner.loadApplicationFile(searchField)){
			faRunner.runApplication();
			faRunner.updateHistory(searchField);
		}
	}
	private void addElements() {
		searchBoxField = new JTextField(30);
		showSearchButton = new JButton("RUN");
		showSearchButton.addActionListener(this);
		searchBoxField.setFont(new Font("Arial", Font.PLAIN, 20));
		searchBoxField.addKeyListener(this);
		showSearchButton.setSize(180, 100);		
		
		add(searchBoxField);
		add(showSearchButton);
	}


	private void setFrameProperty() {
		setSize(400, 150);
		setVisible(true);
		setLayout(new FlowLayout());
		setResizable(false);
		keepWindowAtCenter();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		ImageIcon icon = new ImageIcon(FileUtils.locateFileFromResource(FaRunner.ICON_FILE_NAME).getAbsolutePath());
//		setIconImage(icon.getImage());
	}


	private void keepWindowAtCenter() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/3-this.getSize().height/2);		
	}
	public void keyTyped(KeyEvent e) {
		// No use
	}
	public void keyPressed(KeyEvent e) {
		// No use
	}
}
