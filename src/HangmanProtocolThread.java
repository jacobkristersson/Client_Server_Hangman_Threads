//
//	HangmanProtocolThread.java
//	After the server has a socket it handles that socket over to a new thread 
//	that runs the HangmanGame, this is the file that runs the hangman game.
//	
//


import java.net.*;
import java.io.*;







public class HangmanProtocolThread extends Thread{
	
	
	private Socket connection;
	
	//	Constructor that takes the parameter of type Socket
	HangmanProtocolThread(Socket connection) {
		this.connection = connection;
	}


	// This runs the thread
	@Override
	public void run() {
		try {
			// out is for writing out to the client 
			PrintWriter out =
					new PrintWriter(connection.getOutputStream(), true);
			// in is for reading what the client has written to the server
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			
			//Two strings used for representing input and output to client
			String input, output;


			
			// Constructs new hangman game hg
			HangmanGame hg = new HangmanGame();
			//	Setus a random word
			hg.randomWord();
			// Setups the word user should guess
			hg.setupWord();
			//	Prints start game message
			out.println(hg.startGame());

			//	Handles input for users
			while (((input = in.readLine()) != null)) {
				input = input.trim(); // remove leading and trailing whitespace
				
			
				hg.saveGuesses(input);

				char userInput = input.charAt(0);

				out.println(hg.processInput(userInput));
				if (hg.youWon()) {
					output = "Congratulations you won!" + hg.orginalWord + " Youre guesses was: " + hg.printGuesses();
					out.println(output);
					break;
				}
				if (hg.gameOver()) {
					output = "You lost! The secret word was: " + hg.orginalWord + ". Youre guesses was: " + hg.printGuesses();
					out.println(output);
					break;
				}

			} 



		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
				// Ignore;
			}
		}

	}





}