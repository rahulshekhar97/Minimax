import javax.swing.JLabel;

public class Computer extends User{
	User p1; 
	int movex;
	int movey;
	int cscore;
	int mmode;
	
	public Computer(int idn, User one, String x){
		super(idn, x);
		p1 = one;
		lscore.setText("Computer" + ": " + score);
		movex = -1;
		movey = -1;
		cscore = -10000;
	}
	
	public int getcscore(){
		return cscore;
	}
	
	public int getMode(){
		return mmode;
	}
	
	public JLabel returnLabel()
	{
		return lscore;
	}
	
	public void reset(){
		movex = -1;
		movey = -1;
		cscore = -10000;
		mmode = -1;
	}
	
	public void firstMove(cellState t[][]){
		int m = -1;
		int v;
		cellState g[][] = getCurrentGameState(t);
		if(g[0][0].isClicked() == false){
			v = g[0][0].getValue();
			if(v > m){
				m = v;
				movex = 0;
				movey = 0;
			}
		}
		if(g[0][4].isClicked() == false){
			v = g[0][4].getValue();
			if(v > m){
				m = v;
				movex = 0;
				movey = 4;
			}
		}
		if(g[4][0].isClicked() == false){
			 v = g[4][0].getValue();
			if(v > m){
				m = v;
				movex = 4;
				movey = 0;
			}
		}
		if(g[4][4].isClicked() == false){
			v = g[4][4].getValue();
			if(v > m){
				m = v;
				movex = 4;
				movey = 4;
			}
		}
		moves = 1;
	}
	
	private cellState[][] getCurrentGameState(cellState t[][]){
		cellState g[][] = new cellState[5][5];
		for(int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				g[i][j] = new cellState(t[i][j].getValue());
				g[i][j].updateOwner(t[i][j].getOwner());
				g[i][j].updateClicked(t[i][j].isClicked());
			}
		}
		return g;
	}
	
	/* AI METHOD */
	public void minimax(int id, int score, cellState t[][], int level, int fx, int fy, int depth, int mode){
		if(level == depth){
			if(score > cscore){
				movex = fx;
				movey = fy;
				cscore = score;
				mmode = mode;
			}
		}
		else {
		cellState g[][] = new cellState[5][5];
		g = getCurrentGameState(t);
		for(int i = 0; i < 5; i++){
			for (int j = 0; j < 5;j++){
				if(g[i][j].getOwner() == id){
					if(g[i][j].isClicked() == false){
			
						cellState choose[][] = new cellState[5][5];
						
						choose = getCurrentGameState(g);
						
						int v = choose[i][j].getValue();
						updateGameBoard(i, j, id, choose);
						
						if(id == 1){
							if(level == 0)
							{
								minimax(2, score - v, choose, level + 1, i, j, depth, 1);
								
							}
							else {
								minimax(2, score - v, choose, level + 1, fx, fy, depth, mode);
							}
						}
						else if(id == 2){
							if(level == 0)
								minimax(1, score + v, choose, level + 1, i, j, depth, 1);
							else 
								minimax(1, score + v, choose, level + 1, fx, fy, depth, mode);
						}
					}
				}
				if(g[i][j].isClicked() == false){
					cellState remove[][] = new cellState[5][5];
					remove = getCurrentGameState(g);
					remove[i][j].updateClicked(true);
					remove[i][j].updateOwner(-1);
					if(id == 2){
						if(level == 0){
							minimax(1, score, remove, level + 1, i, j, depth, 2);
						}
						else {
							minimax(1, score, remove, level + 1, fx, fy, depth, mode);
						}
					}
				}
			}
		}
		}
	}
	
	/* AI METHOD */
	
	public int getMoveX(){
		return movex;
	}
	public int getMoveY(){
		return movey;
	}
	
	private void updateGameBoard(int x, int y, int id, cellState gb[][]){
		gb[x][y].updateOwner(id);
		gb[x][y].updateClicked(true);
		int up = y - 1, down = y + 1, 
			  left = x - 1, right = x + 1;
	if(up >= 0){
		if(gb[x][up].getOwner() == 0){
			gb[x][up].updateOwner(id);
			}
		else if(gb[x][up].getOwner() == id){
			if(gb[x][up].isClicked() == true){
				gb[x][up].updateOwner(-1);
			}
		}
		else {
			gb[x][up].updateOwner(-1);
		}
		
	}

	if(down < 5){
		if(gb[x][down].getOwner() == 0){
			gb[x][down].updateOwner(id);
			}
		else if(gb[x][down].getOwner() == id){
			if(gb[x][down].isClicked() == true){
				gb[x][down].updateOwner(-1);
			}
		}
		else {
			gb[x][down].updateOwner(-1);
		}
	}
	
	if(left >= 0){
		if(gb[left][y].getOwner() == 0){
			gb[left][y].updateOwner(id);
			}
		else if(gb[left][y].getOwner() == id){
			if(gb[left][y].isClicked() == true){
				gb[left][y].updateOwner(-1);
			}
		}
		else {
			gb[left][y].updateOwner(-1);
		}
	}
	if(right < 5){
		if(gb[right][y].getOwner() == 0){
			gb[right][y].updateOwner(id);
			}
		else if(gb[right][y].getOwner() == id){
			if(gb[right][y].isClicked() == true){
				gb[right][y].updateOwner(-1);
			}
		}
		else {
			gb[right][y].updateOwner(-1);
		}
	}
}
	public void updateScore(String num){
		int add = Integer.parseInt(num);
		score += add;
		lscore.setText("Computer" + id + ": "  + score);
	}
}
