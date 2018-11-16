package com.ChazTech.SwampThang;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainClass {

	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		String input = "";
		boolean validResult = false;
		// TODO Auto-generated method stub
		boolean wonGame = false;
		int treasureXPos;
		int treasureYPos;
	    int playerXPos;
		int playerYPos;
		int gridSize = 20;
		int playerPos = gridSize/2;
	
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

		mainClass.intro(playerTile);
	    
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
	public boolean bobNorth(String input) {
		input = input.toUpperCase();
		if (input.contains("GO")) {
			if (input.contains("EAST") || input.contains("SOUTH") || input.contains("WEST")) {
				System.out.println("Bob shakes his head and again, indicates you to Go North");
				return false;
			} else if (input.contains("NORTH")) {
				System.out.println("You begin to proceed North");
				waitForTime(3);
				System.out.println("Once your back is turned, Bob smirks, knowing the doom that awaits you...");
				return true;
			} else {
				System.out.println("Bob shakes his head and again, indicates you to Go North");
				return false;
			}
		} else {
			System.out.println("Bob shakes his head and again, indicates you to Go North");
			return false;
		}
	}
	public void bobPoint() {
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"      /   \\ \\\r\n" + 
			"      ||  .||\\\r\n" + 
			"      ||  .||\\\\\r\n" + 
			"      ||__.|| \\\\ \r\n" + 
			"      {}    |  {},\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");
		waitForTime(1);
		System.out.println("After a moment, Bob points, indicating you to Go North");
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"      /   \\ -__\r\n" + 
			"      ||  .||--''--_\r\n" + 
			"      ||  .||  ''''_{}'\r\n" + 
			"      ||__.||\r\n" + 
			"      {}    |\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");

		waitForTime(1);
		System.out.println("After a moment, Bob points, indicating you to Go North");
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/_______ __\r\n" + 
			"      /   \\ \\,------ #\r\n" + 
			"      ||  .||\r\n" + 
			"      ||  .||\r\n" + 
			"      ||__.|| \r\n" + 
			"      {}    |\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");
	}
	public void bobWave() {
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"      /   \\ \\\r\n" + 
			"      ||  .||\\\r\n" + 
			"      ||  .|||\r\n" + 
			"      ||__.||| \r\n" + 
			"      {}    |}\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)\r\n");
		waitForTime(1);
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"      /   \\ \\\r\n" + 
			"     / |  .||\\\r\n" + 
			"     |||  .|||\r\n" + 
			"     |||__.||| \r\n" + 
			"     {}|    |}\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");
		waitForTime(1);
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"      /   \\ \\\r\n" + 
			"     //|  .||\\\r\n" + 
			"    < ||  .|||\r\n" + 
			"     {}|__.||| \r\n" + 
			"       |    |}\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");
		waitForTime(1);
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"      /   \\ \\\r\n" + 
			"     //|  .||\\\r\n" + 
			"    <{}|  .|||\r\n" + 
			"       |__.||| \r\n" + 
			"       |    |}\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)\r\n");
		waitForTime(1);
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"       \\_<>_/\r\n" + 
			"   ,, /   \\ \\\r\n" + 
			"   {}//|  .||\\\r\n" + 
			"    \\/ |  .|||\r\n" + 
			"       |__.||| \r\n" + 
			"       |    |}\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");
		waitForTime(1);
		System.out.println(
			"        ____\r\n" + 
			"      /      \\\r\n" + 
			"     [| o  o |]\r\n" + 
			"      |   >  |\r\n" + 
			"  ,,,  \\_<>_/\r\n" + 
			"   #'  /  \\ \\\r\n" + 
			"   \\\\//|  .||\\\r\n" + 
			"    \\/ |  .|||\r\n" + 
			"       |__.||| \r\n" + 
			"       |    |}\r\n" + 
			"       |  | |\r\n" + 
			"       |  | |\r\n" + 
			"       |__|_|_\r\n" + 
			"       (___)__)");
			waitForTime(1);
		System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				" ,,,  |   >  |\r\n" + 
				"  #'   \\_<>_/\r\n" + 
				"  \\\\ /    \\ \\\r\n" + 
				"   \\\\//|  .||\\\r\n" + 
				"    \\/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				",,,   |   >  |\r\n" + 
				" #'    \\_<>_/\r\n" + 
				" \\\\   /   \\ \\\r\n" + 
				"  \\\\ //|  .||\\\r\n" + 
				"   \\\\/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				" ,,,  |   >  |\r\n" + 
				"  #'   \\_<>_/\r\n" + 
				"  \\\\  /   \\ \\\r\n" + 
				"   \\\\//|  .||\\\r\n" + 
				"    \\/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"  ,,, |   >  |\r\n" + 
				"   #'  \\_<>_/\r\n" + 
				"   ||  /  \\ \\\r\n" + 
				"   '|//|  .||\\\r\n" + 
				"    |/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");	
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				" ,,,  |   >  |\r\n" + 
				"  #'   \\_<>_/\r\n" + 
				"  \\\\ /    \\ \\\r\n" + 
				"   \\\\//|  .||\\\r\n" + 
				"    \\/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"      |   >  |\r\n" + 
				"  ,,,  \\_<>_/\r\n" + 
				"   #' /   \\ \\\r\n" + 
				"   \\\\//|  .||\\\r\n" + 
				"    \\/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"      |   >  |\r\n" + 
				"       \\_<>_/\r\n" + 
				"   ,, /   \\ \\\r\n" + 
				"   {}//|  .||\\\r\n" + 
				"    \\/ |  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"      |   >  |\r\n" + 
				"       \\_<>_/\r\n" + 
				"      /   \\ \\\r\n" + 
				"     //|  .||\\\r\n" + 
				"    <{}|  .|||\r\n" + 
				"       |__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"      |   >  |\r\n" + 
				"       \\_<>_/\r\n" + 
				"      /   \\ \\\r\n" + 
				"     //|  .||\\\r\n" + 
				"    < ||  .|||\r\n" + 
				"     {}|__.||| \r\n" + 
				"       |    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"      |   >  |\r\n" + 
				"       \\_<>_/\r\n" + 
				"      /   \\ \\\r\n" + 
				"     / |  .||\\\r\n" + 
				"     |||  .|||\r\n" + 
				"     |||__.||| \r\n" + 
				"     {}|    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
			System.out.println(
				"        ____\r\n" + 
				"      /      \\\r\n" + 
				"     [| o  o |]\r\n" + 
				"      |   >  |\r\n" + 
				"       \\_<>_/\r\n" + 
				"      /   \\ \\\r\n" + 
				"      ||  .||\\\r\n" + 
				"      ||  .|||\r\n" + 
				"      ||__.||| \r\n" + 
				"      {}    |}\r\n" + 
				"       |  | |\r\n" + 
				"       |  | |\r\n" + 
				"       |__|_|_\r\n" + 
				"       (___)__)");
			waitForTime(1);
	}
	public boolean bobHello(String input, Player playerTile) {
		input = input.toUpperCase();
		if (input.contains("HELLO") || input.contains("HI") || input.contains("HEY")) {
			if (input.contains("SHOCKED BOB")) {
				System.out.println("Bob doesn't like you pointing out his flaws, but waves back anyway.");
				playerTile.setPlayerHealth(playerTile.getPlayerHealth()-1);
				bobWave();
				return true;
			} else if (input.contains("BOB")){
				System.out.println("Bob nods his approval of your politeness and waves at you.");
				playerTile.setPlayerHealth(playerTile.getPlayerHealth()+1);
				bobWave();
				return true;
			} else {
				System.out.println("Bob waves back.");
				bobWave();
				return true;
			}
		} else {
			System.out.println("Bob looks shocked at you... that wasn't what he expected");
			bobWave();
			return false;
		}
	}

	public void intro(Player playerTile) {
		int typicalWaitTime = 0;
		System.out.println("You awake from your slumber in a hurry, the sense of eyes peering over you.");
		waitForTime(typicalWaitTime);
		System.out.println("As the blur of your eyes settle, you begin to see the shape of someone looking shocked at you.");
		waitForTime(typicalWaitTime);
		System.out.println("You fling your head around all directions, quickly realising you have no idea where you are.");
		waitForTime(typicalWaitTime);
		System.out.println("You see nothing but blackness in all directions bar one. To the north is a door shaped light.");
		waitForTime(typicalWaitTime);
		System.out.println("To the side of the door is a sign that read;");
		waitForTime(typicalWaitTime);
		String input = "";
		boolean validResult = false;
		System.out.println("'This is Shocked Bob, say hello to Shocked Bob'");
		System.out.println(
"        ____\r\n" + 
"      /      \\\r\n" + 
"     [| o  o |]\r\n" + 
"      |   >  |\r\n" + 
"       \\_<>_/\r\n" + 
"      /   \\ \\\r\n" + 
"      ||  .||\\\r\n" + 
"      ||  .|||\r\n" + 
"      ||__.||| \r\n" + 
"      {}    |}\r\n" + 
"       |  | |\r\n" + 
"       |  | |\r\n" + 
"       |__|_|_\r\n" + 
"       (___)__)");
		Scanner scanner = new Scanner(System.in);
		do {
			input = scanner.nextLine();
			validResult = bobHello(input, playerTile);
		} while(!validResult);
		validResult = false;
		waitForTime(2);
		System.out.println("After a moment, Bob points, indicating you to Go North");
		bobPoint();

		do {
			input = scanner.nextLine();
			validResult = bobNorth(input);
		} while(!validResult);
	}	
	public void waitForTime(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
