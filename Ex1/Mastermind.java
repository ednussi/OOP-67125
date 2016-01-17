import il.ac.huji.cs.oop.mastermind.*;

public class Mastermind {

	public static void main(String[] args) {
		// Initializing game parameters
		int gamesPlayed = 0;
		double turnsPlayed= 0;
		int gameWins = 0;
		double winsRate = 0;
		double winsAvrege = 0;
		boolean sameParameters = true;
		int codeLength = 0;
		int numberOfValues = 0;
		int maxGuesses = 0;
		MastermindUI ui = MastermindUIFactory.newMastermindUI();

		// main loop of game
		while (true){

			if (sameParameters == true) {			
				// user intialize game parameters, and prompet measege to 
				//the user - In case wrong parameters re-ask the user 

				codeLength = ui.askNumber("Enter code length:");
				while (codeLength <= 0) {
					ui.displayErrorMessage("Value must be positive:");
					codeLength = ui.askNumber("Enter code length:"); 
				}
				numberOfValues = ui.askNumber("Enter number of values:");
				while (numberOfValues <= 0) {
					ui.displayErrorMessage("Value must be positive:");
					numberOfValues = ui.askNumber("Enter number of values:"); 
				}
				maxGuesses = ui.askNumber("Enter max number of guesses:");
				while (maxGuesses <= 0) {
					ui.displayErrorMessage("Value must be positive:");
					maxGuesses = ui.askNumber("Enter max number of guesses:"); 
				}			
			}

			// a single game - runs untill correct or run out of guesses
			ui.reset(codeLength, numberOfValues, maxGuesses);
			int guessCounter = 0;
			Code code = CodeGenerator.newCode(codeLength,numberOfValues);
			while (guessCounter < maxGuesses) {
				Code userGuess = ui.askGuess("Enter guess:", codeLength);
				
				// calculate bulls amount
				int bulls = 0;
				for (int currentPin = 1; currentPin <= codeLength ; 
						currentPin++) {
					if (userGuess.getValue(currentPin) == 
							code.getValue(currentPin)) {
						bulls++; }
				}
				/* calculate cows amount by suming how many instances of each 
				number is within each code (user/comp) and reducing the 
				number of total bulls */
				int sumCows = 0;
				int cows = 0;
				for (int currentValue = 0; currentValue < numberOfValues; 
						currentValue++) {
					int userNumberAmount = 0;
					int codeNumberAmount = 0;
					for (int currentPin = 1; currentPin <= codeLength ;
							currentPin++) {
						if (userGuess.getValue(currentPin) == currentValue) {
							userNumberAmount++; 
						}
						if (code.getValue(currentPin) == currentValue) {
							codeNumberAmount++;
						}
					}
					if (codeNumberAmount > userNumberAmount) {
						sumCows += userNumberAmount; } 
					else { sumCows += codeNumberAmount;}
					if (sumCows - bulls < 0) {
						cows = 0;
					} else {
						cows = sumCows - bulls;
					}
				}
				ui.showGuessResult(userGuess, bulls, cows);
				// in case the guess was right
				if (code.equals(userGuess)) {
					ui.displayMessage("You won in " + (guessCounter+1) 
							+ " turns!");
					gameWins++;
					turnsPlayed += guessCounter + 1;
					break;
				}		
				guessCounter++;
				// in case run out of guesses
				if (guessCounter == maxGuesses) {
					ui.displayMessage(
							"You lost! You failed to find the code!");
				}
			}

			// calculates and shows games statistics
			gamesPlayed += 1;
			winsRate += gameWins / gamesPlayed;
			winsAvrege = turnsPlayed / gameWins;
			ui.showStats(gamesPlayed, gameWins, winsRate, winsAvrege);
			
			// ask user if he likes to keep playing and initialize 
			// parameters by his choice
			if (ui.askYesNo("Another game?") == true) {
				if (ui.askYesNo("Do you want to change the game options?")
						== true ) {
					sameParameters = true;
					gamesPlayed = 0;
					gameWins = 0;
					winsRate = 0;
					winsAvrege = 0;
					turnsPlayed = 0;
				} else {
					sameParameters = false;
					ui.clear();
				}
			} else {
				ui.close();
				break;
			}
		}
	}
}
