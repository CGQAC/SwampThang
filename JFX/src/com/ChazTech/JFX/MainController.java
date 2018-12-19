package com.ChazTech.JFX;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class MainController {
	int animationInt = 1;
	Timeline timeline;
	ArrayList<ALObject> animationList = new ArrayList<ALObject>();
	ArrayList<String> animationSlide = new ArrayList<String>();
	ArrayList<String> backupSlide = new ArrayList<String>();
	Boolean newGame = false;
	String difficulty;
	int gridSize;

	@FXML
	private Label command;
	@FXML
	private Label displayArea;
	@FXML
	private Label disp1;
	@FXML
	private Label disp2;
	@FXML
	private Label disp3;
	@FXML
	private Label disp4;
	@FXML
	private Label disp5;
	@FXML
	private Label disp6;
	@FXML
	private Label disp7;
	@FXML
	private Label disp8;
	@FXML
	private Label disp9;
	@FXML
	private Label disp10;
	@FXML
	private Label disp11;
	@FXML
	private Label disp12;
	@FXML
	private Label disp13;
	@FXML
	private Label disp14;
	@FXML
	private Label disp15;
	@FXML
	private Label disp16;
	@FXML
	private Label disp17;
	@FXML
	private Label disp18;
	@FXML
	private Label disp19;
	@FXML
	private Label disp20;
	@FXML
	private Label disp21;
	@FXML
	private Label disp22;
	@FXML
	private Label disp23;
	@FXML
	private Label disp24;
	@FXML
	private Button btnNorth;
	@FXML
	private Button btnEast;
	@FXML
	private Button btnSouth;
	@FXML
	private Button btnWest;
	@FXML
	private TextField focusLoss;
	@FXML
	private TextField inputCommand;
	@FXML
	public void onEnter(ActionEvent event){
		System.out.println(inputCommand.getText());
		String command = inputCommand.getText().toLowerCase();
		if (command.equals("help")) {
			System.out.println("Loading Help Screen...");
			try {
				help();
			} catch (Exception e) {
			}
			displayAnimation("HelpScreen");
		}
		if (command.equals("hide help")) {
			hidehelp();
		}
		if (command.contains("start game")) {
			String[] inputs = command.split(" ");
			int i = 1;
			for (String a : inputs) {
				if (i == 3) {
					try {
						gridSize = Integer.parseInt(a);
					} catch (Exception e) {
					}
			}
				if (i == 4) {
					difficulty = a;
				}
				i++;
			}
			if (difficulty == null) {
				disp1.setText("New game started | Difficult:" + difficulty + " | Grid Size:" + gridSize);
			}
		}
		//	   focusLoss.setVisible(true);
		//	   focusLoss.requestFocus();
		//	   focusLoss.setVisible(false);
		inputCommand.clear();
	}

	//INITIATE
	public void initialize() {		
		btnNorth.setVisible(false);
		btnEast.setVisible(false);
		btnSouth.setVisible(false);
		btnWest.setVisible(false);
		inputCommand.setVisible(false);
		command.setVisible(false);
		focusLoss.setVisible(false);
		startAnimation();
	}
	public void setVisible() {
		btnNorth.setVisible(true);
		btnEast.setVisible(true);
		btnSouth.setVisible(true);
		btnWest.setVisible(true);
		inputCommand.setVisible(true);
		command.setVisible(true);
	}

	//MOVEMENT
	public void goNorth(ActionEvent event) {
		System.out.println("N");
		disp1.setText("N");
	}
	public void goEast(ActionEvent event) {
		System.out.println("E");
		displayArea.setText("E");
	}
	public void goSouth(ActionEvent event) {
		System.out.println("S");
		displayArea.setText("S");
	}
	public void goWest(ActionEvent event) {
		System.out.println("W");
		displayArea.setText("W");
	}
	
	//HELP
	public void help() {
		backupSlide.add(disp1.getText());
		backupSlide.add(disp2.getText());
		backupSlide.add(disp3.getText());
		backupSlide.add(disp4.getText());
		backupSlide.add(disp5.getText());
		backupSlide.add(disp6.getText());
		backupSlide.add(disp7.getText());
		backupSlide.add(disp8.getText());
		backupSlide.add(disp9.getText());
		backupSlide.add(disp10.getText());
		backupSlide.add(disp11.getText());
		backupSlide.add(disp12.getText());
		backupSlide.add(disp13.getText());
		backupSlide.add(disp14.getText());
		backupSlide.add(disp15.getText());
		backupSlide.add(disp16.getText());
		backupSlide.add(disp17.getText());
		backupSlide.add(disp18.getText());
		backupSlide.add(disp19.getText());
		backupSlide.add(disp20.getText());
		backupSlide.add(disp21.getText());
		backupSlide.add(disp22.getText());
		backupSlide.add(disp23.getText());
		backupSlide.add(disp24.getText());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Assets/BackupScreen"));
			writer.write("");
			for (int i = 0; i < 24; i++) {
				if (i != 23) {
					writer.write(backupSlide.get(i) + "\r\n");
				} else {
					writer.write(backupSlide.get(23));
				}
			}
		    writer.close();
		} catch (IOException e) {
			
		}
	}
	public void hidehelp() {
		displayAnimation("BackupScreen", 100);
	}
	//ANIMATIONS
	public void startAnimation() {
		displayAnimation("TitleAnimation", 100);
	}
	public void startAnimation(ActionEvent event) {
		btnNorth.setVisible(false);
		btnEast.setVisible(false);
		btnSouth.setVisible(false);
		btnWest.setVisible(false);
		displayAnimation("TitleAnimation",100);
	}
	public void startAnimation(String filename) {
		displayAnimation(filename,100);
	}
    public void changeAnimation(int animationInt, int animationLength) {
    	System.out.println("Slide:" + animationInt + "/" + animationLength);
    	for (int i = 1; i <= 24; i++) {
    		switch(i) {
    		case 1:
    			disp1.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 2:
    			disp2.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 3:
    			disp3.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 4:
    			disp4.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 5:
    			disp5.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 6:
    			disp6.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 7:
    			disp7.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 8:
    			disp8.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 9:
    			disp9.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 10:
    			disp10.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 11:
    			disp11.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 12:
    			disp12.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 13:
    			disp13.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 14:
    			disp14.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 15:
    			disp15.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 16:
    			disp16.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 17:
    			disp17.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 18:
    			disp18.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 19:
    			disp19.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 20:
    			disp20.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 21:
    			disp21.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 22:
    			disp22.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 23:
    			disp23.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		case 24:
    			disp24.setText((String) animationList.get(animationInt-1).getALObject().get(i-1));
    			break;
    		}
    	}
    	
    	if (animationInt == animationLength) {
    		animationInt = 1;
            timeline.stop();
            setVisible();
    	}
    }
	public void displayAnimation(String fileName, int frameTime) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Assets/" + fileName));
			String line = reader.readLine();
			int i = 1;
			int j = 0;
			while (line != null) {
				if (i == 25) {
					animationList.add(new ALObject(animationSlide));
					animationSlide = new ArrayList<String>();
					i = 1;
				} else {
					animationSlide.add(line);
					System.out.println(line);
					i++;
					line = reader.readLine();
				}
			}
			animationList.add(new ALObject(animationSlide));
			animationSlide = new ArrayList<String>();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	int animationLength = animationList.size();
        timeline = new Timeline(
            new KeyFrame(
                Duration.millis(frameTime),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {   
                        Platform.runLater(()->{
                        	changeAnimation(animationInt, animationLength);
                        	animationInt++;
                        });
                    }
                }
            )
        );
        timeline.setCycleCount(animationLength); 
        timeline.play();
	}

	public void displayText(String text) {
		
	}
}
