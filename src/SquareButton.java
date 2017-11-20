import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SquareButton extends JButton{
	JButton cell;
	String num;
	User p1, p2;
	int x;
	int y;
	private boolean clicked;
	
	/* Constructor */
	
	public SquareButton(User one, User two, int x1, int y1){
		p1 = one;
		p2 = two;
		x = x1;
		y = y1;
		clicked = false;
		num = getRandomNumber();
		cell = new JButton(num);
		cell.setOpaque(true);
		cell.setPreferredSize(new Dimension(100,100));
		cell.setOpaque(true);
		cell.setBackground(new Color(188,240,204));
		
		cell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clicked){
					JOptionPane.showMessageDialog(null, "This cell has already been chosen!");
				}
				else{
					if(GameBoard.mode == 2)
						buttonHasNotBeenClickedBefore();
					else{
						if(GameBoard.whoseturn == true){
							buttonHasNotBeenClickedBefore();
						}
					}
				}
			}
		});
	}
	
	/* Constructor Ends */
	
	public String getNum(){
		return num;
	}
	
	private void buttonHasNotBeenClickedBefore(){
		if(Player.wantsToRemove == true){
			Player.wantsToRemove = false;
			Player.removeButton.setBackground(Color.ORANGE);
			Player.removeButton.setForeground(Color.BLUE);
			cell.setBackground(Color.black);
			cell.setForeground(Color.black);
			GameBoard.gb[x][y].updateClicked(true);
			GameBoard.gb[x][y].updateOwner(-1);
			GameBoard.whoseturn = !GameBoard.whoseturn;
			clicked = true;
			GameBoard.buttonsClicked++;
			GameBoard.checkIfGameIsOver();
			if(GameBoard.mode == 1){
				GameBoard.findComputerMove();
			}
		}
		else{
			boolean ok = false;
			if(GameBoard.whoseturn){
				if(p1.gridCellClicked(x, y, cell, num)){
					ok = true;
				}
			}
			else{
				if(p2.gridCellClicked(x, y, cell, num)){
					ok = true;
				}
			}
			if(!ok){
				JOptionPane.showMessageDialog(null, "This is not a valid cell!");
			}
			else {
				GameBoard.buttonsClicked++;
				GameBoard.checkIfGameIsOver();
				if(GameBoard.mode == 1){
					GameBoard.findComputerMove();
				}
			}
		}
	}
	
	public int getV(){
		return Integer.parseInt(num);
	}
	
	public JButton returnButton(){
		return cell;
	}
	
	private String getRandomNumber(){
		Random rand = new Random();
		Integer num =  rand.nextInt(20) + 1;
		return num.toString();
	}
}
