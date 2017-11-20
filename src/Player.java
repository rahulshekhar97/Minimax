import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Player extends User{
	static public boolean wantsToRemove;
	static public JButton removeButton;
	static public JButton resignButton;

	public Player(int idn, String x){
		super(idn, x);
		wantsToRemove = false;
		removeButton = new JButton("Remove");
		removeButton.setOpaque(true);
		removeButton.setBackground(Color.ORANGE);
		removeButton.setForeground(Color.BLUE);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wantsToRemove == false){
					wantsToRemove = true;
					removeButton.setBackground(Color.GREEN);
					removeButton.setForeground(Color.BLUE);
				}
				else {
					wantsToRemove = false;
					removeButton.setBackground(Color.ORANGE);
					removeButton.setForeground(Color.BLUE);
				}
			}
		});
		
		
		/*
		
		*/
		 resignButton = new JButton("Resign");
		 resignButton.setOpaque(true);
		 	resignButton.setForeground(Color.GREEN	);
		 	resignButton.setBackground(Color.BLUE);
        
		 	resignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GameBoard.whoseturn){
					if(GameBoard.mode == 2){
						Users.addWin(GameBoard.name2, 1);
						JOptionPane.showMessageDialog(null, GameBoard.name2 + " Wins!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer Wins!");
					}
				}
				else{
					Users.addWin(GameBoard.name1, 1);
					JOptionPane.showMessageDialog(null, GameBoard.name1 + " Wins!");
				}
				Print.reWriteFile();
				GameBoard.killWindow();
				new HomeScreen();
			}
		});
	}
		
	public JLabel returnLabel(){
		return lscore;
	}
	
	public JButton returnRemoveButton(){
		return removeButton;
	}
	
	public JButton returnResignButton(){
		return resignButton;
	}
	
	
}
