
public class cellState {
	int owner;
	boolean clicked;
	int v;
	public cellState(int x){
		owner = 0;
		clicked = false;
		v = x;
	}
	
	public void updateOwner(int id){
		owner = id;
	}
	
	public void updateClicked(boolean x){
		clicked = x;
	}
	
	public int getOwner(){
		int o = owner;
		return o;
	}
	
	public boolean isClicked(){
		boolean c = clicked;
		return c;
	}
	public int getValue(){
		int x = v;
		return x;
	}
}
