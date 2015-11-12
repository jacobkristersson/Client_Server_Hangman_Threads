import java.awt.*;







public class guiTest {

	public static void main(String args[]) {
		// Create new frame
		Frame j = new Frame();
		
		// Sets layout of frame
		j.setLayout(new FlowLayout());
		
		// Construct and add child components to frame
		Panel p = new Panel();
		p.add(new Label("Input you're guess:"));
		TextField tffn = new TextField("", 30); 
		Button guess = new Button("ok");
	
		
	
		p.add(tffn);
		p.add(guess);
		j.add(p);
		
		
		
		// Constructs a panel that holds the current gussed words
		Panel guessHolder = new Panel();
		p.add(new Label("Gussed characters:"));
		TextField gussed = new TextField("a, b, c, d, e");
		gussed.setEditable(false);
		
		guessHolder.add(gussed);
		j.add(guessHolder);
		
		
		// New game panel
		Panel newGame = new Panel();
		Button newGame2 = new Button("New game");
		
		newGame.add(newGame2);
		
		
		j.add(newGame);
		
		
		//Pack the frame and make it visible
		j.pack();
		j.setVisible(true);
	}




}