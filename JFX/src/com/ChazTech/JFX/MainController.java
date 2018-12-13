package com.ChazTech.JFX;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MainController {
	int animationInt = 1;
	Timeline timeline;
	ArrayList<ALObject> animationList = new ArrayList<ALObject>();
	ArrayList<String> animationSlide = new ArrayList<String>();

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

	public void initialize() {
		System.out.println("initialised");
		startAnimation();
	}

	public void goNorth(ActionEvent event) {
		System.out.println("N");
		disp1.setText("N");
	}
	public void goEast(ActionEvent event) {
		System.out.println("E");
		disp1.setText("E");
	}
	public void goSouth(ActionEvent event) {
		System.out.println("S");
		disp1.setText("S");
	}
	public void goWest(ActionEvent event) {
		System.out.println("W");
		disp1.setText("W");
	}
	public void startAnimation() {
		displayAnimation("TitleAnimation");
	}
	public void startAnimation(ActionEvent event) {
		displayAnimation("TitleAnimation");
	}
    public void changeAnimation(int animationInt, int animationLength) {
    	System.out.println("Slide:" + animationInt + "/" + animationLength);
//    	System.out.println(animationList.get(32).getALObject().get(1));
//    	System.out.println(animationList.get(33).getALObject().get(1));
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
    	}
    }
	public void displayAnimation(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Assets/" + fileName));
			String line = reader.readLine();
			int i = 1;
			int j = 0;
			animationSlide.clear();
			while (line != null) {
//				System.out.println("" + i + line);
				if (i == 25) {
//					ALObject myALObject = new ALObject(animationSlide);
					animationList.add(new ALObject(animationSlide));
					System.out.println(animationList.get(animationList.size() - 1).getALObject().get(15));
//					System.out.println("Added:'" + animationSlide + "'");
//					System.out.println("To:'" + animationList + "'");
//					animationList.add(animationSlide);
					animationSlide.clear();
					i = 1;
				} else {
					animationSlide.add(line);
					System.out.println(line);
					i++;
					line = reader.readLine();
				}
			}
			System.out.println("FINDME" + animationList.get(32).getALObject().get(11));
//			ALObject myALObject = new ALObject(animationSlide);
//			animationList.add(new ALObject(animationSlide));
//			System.out.println("Added:'" + animationSlide + "'");
//			System.out.println("To:'" + animationList + "'");
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	int animationLength = animationList.size();
        timeline = new Timeline(
            new KeyFrame(
                Duration.seconds(1),
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
}
