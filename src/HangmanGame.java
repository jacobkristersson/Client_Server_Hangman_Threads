//
//	HangmanGame.java
//	This file holds the logic to the Hangman game
//	The HangmanProtocolThread.java creates a new object of the hangman game
//




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;










public class HangmanGame {
	// The word the user should guess
	public String orginalWord;
	// The word the user has gussued
	public String gussedWord;
	// Keep tracks of the guesses the user has left
	public int guessesLeft;
	// ArrayList with the users guesses
	public ArrayList<String> saveGuesses = new ArrayList<String>();


	// Adds guess to the ArrayList
	public void saveGuesses(String guess){
		saveGuesses.add(guess);
	}



	// Puts all the user guesses in a String so you can print it
	public String printGuesses() {
		String savedGuesses = "";
		for (int i = 0; i < saveGuesses.size(); i++) {
			savedGuesses += saveGuesses.get(i) + ", ";
		}
		return savedGuesses;

	}




	// Start game message
	public String startGame() {
		return "Welcome to the hangman game the word is: " + gussedWord + "." + '\n';
	}

	// Randoms a word that the user should guess
	// By putting the file "resc/words.txt" in a ArrayList
	// Then shuffling that list and getting the first element in the shuffled list
	public void randomWord() throws IOException{
		ArrayList<String> list = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader("resc/words.txt"));
		try {

			String line = br.readLine();

			while (line != null) {

				line = br.readLine();
				list.add(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}

		Collections.shuffle(list);

		this.orginalWord = list.get(0);
		this.guessesLeft = orginalWord.length();

	}


	//	Makes so that the orginalword example "apple" is "_____"
	public void setupWord() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < orginalWord.length(); i++) {
			stringBuilder.append("_");
		}
		gussedWord = stringBuilder.toString();
	}




	// Procces inputed chars from the user
	// This method works well
	
	public String processInput(char guess) {
		String outPut = "";
		String savedGuesses = "";
		for (int i = 0; i < saveGuesses.size(); i++) {
			savedGuesses += saveGuesses.get(i) + ", ";
		}
		if (orginalWord.indexOf(guess) >= 0) {
			for(int index = orginalWord.indexOf(guess); index >= 0; index = orginalWord.indexOf(guess, index + 1)) {
				StringBuilder localSecret = new StringBuilder(gussedWord);
				localSecret.setCharAt(index, guess);
				gussedWord = localSecret.toString();
			}

			outPut = "Youre guess was correct. The word is now: " + gussedWord+ ". Guesses left " + guessesLeft + ". "
					+ "Youre gussed letters are: " + savedGuesses;

		} else {
			guessesLeft--;
			outPut = "Youre guess was incorrect. The word is now: " + gussedWord + ".Guesses left " + guessesLeft + ". "
					+ "Youre gussed letters are: " + savedGuesses;
		}


		return outPut;
	}

	// Processes inputed whole words from user
	// This method does not work!
	public String processInput(String guess) {
		String outPut = "";
		if (guess.equals(orginalWord)){
			outPut = "Youre guess was correct. The word was " + orginalWord + ". ";

		} else {

			guessesLeft--;
			outPut = "Youre guess was incorrect. The word is still: " + gussedWord + ". Guesses left " + guessesLeft;

		}


		return outPut;
	}

	// Checks to see if the user has won
	public boolean youWon() {
		if (gussedWord.equalsIgnoreCase(orginalWord)) {
			return true;
		} else {
			return false;
		}

	}


	// Checks to see if the user has guesses left
	public boolean gameOver(){
		if (guessesLeft == 0) {
			return true;
		} else {
			return false;
		}

	}


	// Think this is a obsolete function!
	public String guessWord(String secretString, char guess) {
		for(int index = orginalWord.indexOf(guess); index >= 0; index = orginalWord.indexOf(guess, index + 1)) {
			StringBuilder localSecret = new StringBuilder(secretString);
			localSecret.setCharAt(index, guess);
			secretString = localSecret.toString();
		}
		return secretString;
	}

}