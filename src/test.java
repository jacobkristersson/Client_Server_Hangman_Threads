//
//	test.java
//	This is a small test file i use to test stuff in
//	Here i have tested input word file to arraylist and then shuffle it


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;








public class test{


	public static void main (String args[]) throws IOException {

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
	
		System.out.println(list.get(0));

		
		



	}




}