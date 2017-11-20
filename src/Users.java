import java.util.TreeMap;

public class Users {
	static TreeMap<String, Integer> wins = new TreeMap<String, Integer>();

	static public void addWin(String x, int c){
		if(wins.containsKey(x)){
			int v = wins.get(x);
			wins.remove(x);
			wins.put(x, v + 1);
			System.out.println(x);
		}
		else{
			wins.put(x, c);
		}
	}
	static public void addUser(String x, int c){
		if(x != null){
			wins.put(x,c);
		}
			
	}
	
}
