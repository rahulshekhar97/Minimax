import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameBoard extends JPanel { 
	static public boolean whoseturn;
	static User p1, p2;
	static public cellState gb[][];
	static public SquareButton buttons[][];
	static int mode; // 1p or 2p
	static JFrame gboard;
	static public int buttonsClicked;
	
	static String name1;
	static String name2;

	/* Constructor Starts */
	
	public GameBoard(User one, User two, int type, String x1, String x2){
		name1 = x1;
		name2 = x2;
		buttonsClicked = 0;
		p1 = one;
		p2 = two;
		mode = type;
		whoseturn = true;
		buttons = new SquareButton[5][5];
		gb = new cellState[5][5];
		gboard = new JFrame();
		gboard.setLayout(new BorderLayout());
		gboard.setTitle("MINIMAX");
		gboard.setSize(570,570);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 gboard.setLocation(dim.width/2-gboard.getSize().width/2, dim.height/2-gboard.getSize().height/2);
		JPanel grid = new JPanel(new GridLayout(5, 5));
		
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				SquareButton sb = new SquareButton(p1, p2, i, j);
				buttons[i][j] = sb;
				gb[i][j] = new cellState(sb.getV());
				grid.add(sb.returnButton());
			}	
		}
		gboard.add(grid, BorderLayout.NORTH);
		grid.setOpaque(true);
		grid.setBackground(Color.ORANGE);
		final JPanel fscore = new JPanel();
		final JPanel sscore = new JPanel();
		final JPanel rButton = new JPanel();
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		final JPanel resignButton = new JPanel();
		final JLabel l1 = ((Player) p1).returnLabel();
		fscore.add(l1);
		if(mode == 2){
		final JLabel l2 = ((Player) p2).returnLabel();
		sscore.add(l2);
		}
		else {
			final JLabel l2 = ((Computer) p2).returnLabel();
			sscore.add(l2);
		};

		bottomPanel.add(Player.removeButton);
		bottomPanel.add(Player.resignButton);
		gboard.add(fscore, BorderLayout.WEST);
		gboard.add(sscore, BorderLayout.EAST);
		gboard.add(bottomPanel);
		gboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gboard.setVisible(true);
		gboard.pack();
		
	}
	
	/* Constructor Ends */
	
	static void killWindow(){
		gboard.dispose();
	}
	
	static void checkIfGameIsOver(){
		if(buttonsClicked == 25){
			if(p1.getScore() < p2.getScore()){
				if(((Computer)p2).getMode() == 2){
					JOptionPane.showMessageDialog(null, name2 + " Wins!");
					Users.addWin(name2, 1);;
				}
				else 
					JOptionPane.showMessageDialog(null, "Computer wins!");
			}
			else if(p1.getScore() > p2.getScore()){
				JOptionPane.showMessageDialog(null, name1 + " Wins!");
				Users.addWin(name1, 1);
			}
			else {
				JOptionPane.showMessageDialog(null, "The Game is a tie");
			}
			Print.reWriteFile();
			gboard.dispose();
			new HomeScreen();
		}
	}
	
	static public void findComputerMove(){
		if(p2.getMoves() == 0){
			((Computer)p2).firstMove(GameBoard.gb);
			int mx = ((Computer)p2).getMoveX();
			int my = ((Computer)p2).getMoveY();
			buttons[mx][my].returnButton().setBackground(Color.blue);
			buttons[mx][my].returnButton().setForeground(Color.blue);
			p2.updateScore(buttons[mx][my].getNum());
			updateGameBoard(mx, my, 2);
			whoseturn = !whoseturn;
		}
		else{
			cellState g[][] = new cellState[5][5];
			for(int i = 0; i < 5; i++){
				for (int j = 0; j < 5; j++){
					g[i][j] = new cellState(gb[i][j].getValue());
					g[i][j].updateOwner(gb[i][j].getOwner());
					g[i][j].updateClicked(gb[i][j].isClicked());
				}
			}
			((Computer)p2).reset();
			((Computer)p2).minimax(2, 0, g, 0, -1, -1, 4, -1);
			if(((Computer)p2).getcscore() == -10000){
				((Computer)p2).reset();
				((Computer)p2).minimax(2, 0, g, 0, -1, -1, 2,-1);
				if(((Computer)p2).getcscore() == -10000){
					((Computer)p2).reset();
					((Computer)p2).minimax(2, 0, g, 0, -1, -1, 1,-1);
				}
			}
			int mx = ((Computer)p2).getMoveX();
			int my = ((Computer)p2).getMoveY();
			if(((Computer)p2).getMode() == 1){
				buttons[mx][my].returnButton().setBackground(Color.blue);
				buttons[mx][my].returnButton().setForeground(Color.blue);
				p2.updateScore(buttons[mx][my].getNum());
				updateGameBoard(mx, my, 2);
			}
			else {
				if(mx == -1){
					buttonsClicked = 25;
					checkIfGameIsOver();
				}
				else {
				buttons[mx][my].returnButton().setBackground(Color.black);
				buttons[mx][my].returnButton().setForeground(Color.black);
				gb[mx][my].updateClicked(true);
				gb[mx][my].updateOwner(-1);
				}
			}
			
			whoseturn = !whoseturn;
		}
	}
	
	
	static public void updateGameBoard(int x, int y, int id){
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
}
