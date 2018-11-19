package com.ChazTech.SwampThang;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainClass {

	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		Animations animations = new Animations();
		String input = "";
		boolean validResult = false;
//		System.out.println("");
		
		boolean wonGame = false;
		int treasureXPos;
		int treasureYPos;
	    int playerXPos;
		int playerYPos;
		int gridSize = 20;
		int playerPos = gridSize/2;
		animations.titleScreen();
		Random rand = new Random();
		
	    treasureXPos = rand.nextInt(gridSize) + 1;
	    treasureYPos = rand.nextInt(gridSize) + 1;
	    if (treasureXPos == 50) {
	    	treasureXPos = treasureXPos + 1;
	    }
	    if (treasureYPos == 50) {
	    	treasureYPos = treasureYPos - 1;
	    }
	    SwampTile treasureTile = new SwampTile(treasureXPos, treasureYPos,true);
//	    SwampTile treasureTile = new SwampTile(49, 49,true);
		playerXPos = playerPos;
		playerYPos = playerPos;
	    Player playerTile = new Player(playerXPos, playerYPos,false);

		mainClass.intro(playerTile, animations);
	    
	    // MAIN GAME LOOP		
//	    System.out.println("tx:" + treasureTile.getX() + " ty:" + treasureTile.getY());
//	    System.out.println("px:" + playerTile.getX() + " py:" + playerTile.getY());
		Scanner scanner = new Scanner(System.in);
	    do {
		    System.out.println("The treasure is " + (mainClass.showCompassInfo(playerTile.getX(), playerTile.getY(), treasureTile.getX(), treasureTile.getY())) + " away");
			input = scanner.nextLine();
			wonGame = mainClass.movePlayer(input, playerTile, treasureTile);
	    } while (!wonGame);
	    // ---------------
	}
	
	public boolean randomEvent(String directionTraveled, Player playerTile) {
		Random rand = new Random();
		System.out.println("You travel " + directionTraveled + "ward ");
		int randNo = rand.nextInt(5);
		if (randNo == 0) {
			System.out.println("As you go " + directionTraveled + "ward ");
			System.out.print("you have a heart attack and die instantly...");
			return true;
		} else if (randNo > 66) {
			System.out.println("As you go " + directionTraveled + "ward ");
			if (randNo == 67) {
				System.out.println(" you stub your toe and lose 1 health");
				playerTile.setPlayerHealth(playerTile.getPlayerHealth()-1);
			}
			if (randNo == 68) {
//				System.out.println(" you see a clear water spring in the middle of the swamp");
			}
		}
		if (playerTile.getPlayerHealth() <= 0 ) {
			System.out.println("You have taken too much damage, and have bled out...");
			return true;
		} else {
			return false;			
		}
	}
	
	public boolean movePlayer(String input, Player playerTile, SwampTile treasureTile) {
		input = input.toUpperCase();
		boolean gameOver = false;
		if (input.contains("GO")) {
			if (input.contains("NORTH") || input.equals("GO N")) {
				playerTile.setY(playerTile.getY()+1);
				gameOver = randomEvent("North", playerTile);
			} else if (input.contains("SOUTH") || input.equals("GO S")) {
				playerTile.setY(playerTile.getY()-1);
				gameOver = randomEvent("South", playerTile);
			} else if (input.contains("EAST") || input.equals("GO E")) {
				playerTile.setX(playerTile.getX()+1);
				gameOver = randomEvent("East", playerTile);
			} else if (input.contains("WEST") || input.equals("GO W")) {
				playerTile.setX(playerTile.getX()-1);
				gameOver = randomEvent("West", playerTile);
			} else {
				if (input.equals("GO"));
				gameOver = randomEvent("GoNoWhere", playerTile);
				System.out.println("You cannot just 'Go'");
			}
//		    System.out.println("tx:" + treasureTile.getX() + " ty:" + treasureTile.getY());
//		    System.out.println("px:" + playerTile.getX() + " py:" + playerTile.getY());
		} else {
			System.out.println("You cannot '" + input + "'");
		}
		if (gameOver == true) {
			return true;
		} else {
			if (playerTile.getX() == treasureTile.getX() && playerTile.getY() == treasureTile.getY()) {
				System.out.println("Congratulations!! You found the treasure");
				return true;
			} else {
				return false;
			}
		}
	}
	public String showCompassInfo(int playerX, int playerY, int treasureX, int treasureY) {
		DecimalFormat df = new DecimalFormat("###.##");
		double xSq = Math.pow(playerX-treasureX, 2);
		double ySq = Math.pow(playerY-treasureY, 2);
		double xySqAdd = xSq + ySq;
		String zSqR = df.format((Math.sqrt(xySqAdd)*10));
		return zSqR;
	}
	public boolean bobNorth(String input, Animations animations) {
		input = input.toUpperCase();
		String message;
		if (input.contains("GO")) {
			if (input.contains("EAST") || input.contains("SOUTH") || input.contains("WEST")) {
				message = "Bob shakes his head and again, indicates you to Go North";
				animations.bobPoint(message, true);
				return false;
			} else if (input.contains("NORTH")) {
				animations.printToDisplay("You begin to proceed North through the door of light.");
				animations.waitForTime(3);
				animations.bobLaugh("Once your back is turned, Bob smirks, knowing the doom that awaits you...");
				return true;
			} else {
				message = "Bob shakes his head and again, indicates you to Go North";
				animations.bobPoint(message, true);
				return false;
			}
		} else {
			message = "Bob looks at you shocked, that wasn't what he expected...";
			animations.bobPoint(message, true);
			return false;
		}
	}
	public boolean bobHello(String input, Player playerTile, Animations animations) {
		input = input.toUpperCase();
		if (input.contains("HELLO") || input.contains("HI") || input.contains("HEY")) {
			if (input.contains("SHOCKED BOB")) {
				playerTile.setPlayerHealth(playerTile.getPlayerHealth()-1);
				animations.bobWave("Bob doesn't like you pointing out his flaws, but waves back anyway.");
				return true;
			} else if (input.contains("BOB")){
				playerTile.setPlayerHealth(playerTile.getPlayerHealth()+1);
				animations.bobWave("Bob nods his approval of your politeness and waves at you.");
				return true;
			} else {
				animations.bobWave("Bob waves back.");
				return true;
			}
		} else {
			System.out.println("Bob looks shocked at you... that wasn't what he expected.");
			System.out.println(
					"|        ____\r\n" + 
					"|      /      \\\r\n" + 
					"|     [| o  o |]\r\n" + 
					"|      |___>__|\r\n" + 
					"|       \\_<>_/\r\n" + 
					"|      /   \\ \\\r\n" + 
					"|      ||  .||\\\r\n" + 
					"|      ||  .|||\r\n" + 
					"|      ||__.||| \r\n" + 
					"|      {}    |}\r\n" + 
					"|       |  | |\r\n" + 
					"|       |  | |\r\n" + 
					"|       |__|_|_\r\n" + 
					"|       (___)__)" +
					"|                                                                                     |\r\n" + 
					"|                                                                                     |\r\n" + 
					"|                                                                                     |\r\n" + 
					"|                                                                                     |\r\n" + 
					"|                                                                                     |\r\n" + 
					"|                                                                                     |\r\n" + 
					"|                                                                                     |\r\n" + 
					"+-------------------------------------------------------------------------------------+");
			return false;
		}
	}
	
	public void intro(Player playerTile, Animations animations) {
		int typicalWaitTime = 500;
		String message = "You awake from your slumber in a hurry, the sense of eyes peering over you. ";
		animations.printToDisplay(message);
		animations.waitForTime(typicalWaitTime);
		message = message + "As the blur of your eyes settle, you begin to see the shape of someone looking shocked at you. ";
		animations.printToDisplay(message);
		animations.waitForTime(typicalWaitTime);
		message = message + "You fling your head around all directions, quickly realising you have no idea where you are. ";
		animations.printToDisplay(message);
		animations.waitForTime(typicalWaitTime);
		message = message + "You see nothing but blackness in all directions bar one. To the north is a door shaped light. ";
		animations.printToDisplay(message);
		animations.waitForTime(typicalWaitTime);
		message = message + "To the side of the door is a sign that read; ";
		animations.printToDisplay(message);
		animations.waitForTime(typicalWaitTime);
		String input = "";
		boolean validResult = false;
		System.out.println(
				"+-------------------------------------------------------------------------------------+\r\n" +
				"| 'This is Shocked Bob, say hello to Shocked Bob'                                     |\r\n" +
				"|        ____                                                                         |\r\n" + 
				"|      /      \\                                                                       |\r\n" + 
				"|     [| o  o |]                                                                      |\r\n" + 
				"|      |___>__|                                                                       |\r\n" + 
				"|       \\_<>_/                                                                        |\r\n" + 
				"|      /   \\ \\                                                                        |\r\n" + 
				"|      ||  .|||                                                                       |\r\n" + 
				"|      ||  .|||                                                                       |\r\n" + 
				"|      ||__.|||                                                                       |\r\n" + 
				"|      {}    |}                                                                       |\r\n" + 
				"|       |  | |                                                                        |\r\n" + 
				"|       |  | |                                                                        |\r\n" + 
				"|       |__|_|_                                                                       |\r\n" + 
				"|       (___)__)                                                                      |\r\n" +
				"|                                                                                     |\r\n" + 
				"|                                                                                     |\r\n" + 
				"|                                                                                     |\r\n" + 
				"|                                                                                     |\r\n" + 
				"|                                                                                     |\r\n" + 
				"|                                                                                     |\r\n" + 
				"|                                                                                     |\r\n" + 
				"+-------------------------------------------------------------------------------------+");
		Scanner scanner = new Scanner(System.in);
		do {
			input = scanner.nextLine();
			validResult = bobHello(input, playerTile, animations);
		} while(!validResult);
		validResult = false;
		animations.waitForTime(2);
		message = "After a moment, Bob indicates you to Go North";
		animations.bobPoint(message, false);

		do {
			input = scanner.nextLine();
			validResult = bobNorth(input, animations);
		} while(!validResult);
	}	
}
