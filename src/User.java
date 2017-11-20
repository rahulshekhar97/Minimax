import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class User {
	int score;
	int id;
	int moves;
	JLabel lscore;
	String username;
	
	public User(int c, String x){
		username = x;
		score = 0;
		id = c;
		moves = 0;
		lscore = new JLabel(username + ": " + score);
	}
	
	public void updateScore(String num){
		int add = Integer.parseInt(num);
		score += add;
		lscore.setText(username + ": " + score);
	}
	
	public boolean gridCellClicked(int x, int y, JButton cell, String cellValue){
		if(checkIfChoiceIsValid(x,y)){
			if(id == 1){
				cell.setBackground(Color.red);
				cell.setForeground(Color.red);
			}
			else if(id == 2){
				cell.setBackground(Color.blue);
				cell.setForeground(Color.blue);
			}
			updateScore(cellValue);
			GameBoard.updateGameBoard(x, y, id);
			GameBoard.whoseturn = !GameBoard.whoseturn;
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getId(){
		return id;
	}
	
	public boolean checkIfChoiceIsValid(int x, int y){
		cellState g[][] = GameBoard.gb;
		if(moves == 0){
			if(x == 0){
				if(y == 0 || y == 4){	
					moves++;
					return true;
				}
				else
					return false;
			}
			else if(x == 4){
				if(y == 0 || y == 4){
					moves++;
					return true;
				}
				else {
					return false;
				}
			}
			else 
				return false;
		}
		else {
			if(GameBoard.gb[x][y].getOwner() == id)
				return true;
			else
				return false;
		}
	}
	
	public int getScore(){
		return score;
	}
	
	public int getMoves(){
		return moves;
	}
	
}
