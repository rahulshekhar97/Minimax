import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Print {
	
	
	static public void getHighScores(){
		File f;
		BufferedReader r;
		try {
			f= new File("Minimaxwork.txt");
		    r = new BufferedReader(new FileReader(f));
		    
		    String line, line2;
		    while ((line = r.readLine()) != null) {
		        line2 = r.readLine();
		        Users.addUser(line, Integer.parseInt(line2));
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	}
	
	static public void reWriteFile(){
		try {
		File f = new File("Minimaxwork.txt");
		FileWriter writer = new FileWriter(f, false); 
		if(Users.wins != null){
		
		for(Map.Entry<String,Integer> entry : Users.wins.entrySet()) {
			  String key = entry.getKey();
			  Integer value = entry.getValue();
			  if(key != null){
			  writer.write(key + "\n");
			  writer.write((value.toString()) + "\n");
			  }
			}
		}
		writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
