import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;






@SuppressWarnings("serial")
public class HomeScreen extends JFrame{
	public HomeScreen(){
		 Users.wins = new TreeMap<String, Integer>();
		 Print.getHighScores();
		 final JFrame home = new JFrame("Home");
	     home.setSize(570, 570);
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 home.setLocation(dim.width/2-home.getSize().width/2, dim.height/2-home.getSize().height/2);
	     final JPanel mainPanel = new JPanel();
	     mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 final JPanel name = new JPanel();
		 final JPanel mycompanel = new JPanel();
		 final JPanel mytxtpanel = new JPanel();
		 final JLabel lname = new JLabel("MINIMAX");
		 final JLabel myCom = new JLabel("Powered By Cynosure");
		 myCom.setBackground(Color.LIGHT_GRAY);
		 final JLabel txt = new JLabel();
		 txt.setText("<html><body><center>DO YOU HAVE WHAT IT TAKES? <br> CHALLENGE THE COMPUTER AND <br> SEE HOW LONG YOU LAST!</center></body></html>");
		 txt.setFont(new Font("Tahoma", Font.ITALIC, 25));
		 myCom.setFont(new Font("Century Gothic", Font.ITALIC, 12));
		 lname.setBorder(new EmptyBorder(3, 0, 3, 0));
		 lname.setFont(new Font("Serif", Font.PLAIN, 45));
		 name.setBackground(new Color(155,247,161));
		 mytxtpanel.setBackground(new Color(255, 255, 153));
		 name.setPreferredSize(new Dimension(200,20));
		 mycompanel.setPreferredSize(new Dimension(200,10));
		 mytxtpanel.setPreferredSize(new Dimension(120,140));
		 name.add(lname);
		 mainPanel.add(name);
		 mycompanel.add(myCom);
		 mytxtpanel.add(txt);
		 mainPanel.add(mycompanel);
		 mainPanel.add(mytxtpanel);
	
		/* Adding buttons */
		 
		 JPanel box = new JPanel(new GridLayout(4, 1));
		 box.setPreferredSize(new Dimension(120,220));
		 box.setOpaque(true);
		 box.setBackground(new Color(135, 193, 250));
		 
		 
		 final JButton instr = new JButton("How to Play?");
		 instr.setSize(new Dimension(40, 30));
		 instr.setFont(new Font("Century", Font.PLAIN, 20));
		 instr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "You have a 5x5 grid filled with random numbers 1 - 20."
							+ " Your goal is to maximize your score.\n" + "In a certain chance a player can either choose a square " +
							  "or remove a square (no one can choose it now) from the gameboard.\n" + "Here are the invariants of the game\n" + "A square can only be chosen once.\n"
							+ "You can only click a square that is connected to square (by an adjacent edge) that has been clicked by you before.\n" +
							  "A square that has common edges to both squares clicked by both players can not be clicked by anyone.\n" 
							+ "You must choose a corner square in your first chance.\n" + "Good Luck!!"); 
				}
			});
		 box.add(instr);

		 final JButton htpb = new JButton("View Scores");
		 htpb.setSize(new Dimension(40, 30));
		 htpb.setFont(new Font("Century", Font.PLAIN, 20));
		 htpb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Print.getHighScores();
					home.dispose();
					new HighScores();
				}
			});


		 final JButton startGame = new JButton("Challenge Computer!");
		 startGame.setFont(new Font("Century", Font.PLAIN, 20));
		 startGame.setSize(new Dimension(40, 30));
		 startGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String name1 = JOptionPane.showInputDialog(home, "Enter Player1's Username");
					if(name1 != null){
						User p1 = new Player(1,name1);
						User p2 = new Computer(2, p1, "Computer");
						home.dispose();
						new GameBoard(p1, p2, 1, name1, "");
					}
				}
			});
		 box.add(startGame);


		 final JButton startGame2 = new JButton("2 Player");
		 startGame2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String name1 = JOptionPane.showInputDialog(home, "Enter Player1's Username");
					String name2 = JOptionPane.showInputDialog(home, "Enter Player2's Username");
					if(name1 != null || name2 != null){
						User p1 = new Player(1,name1);
						User p2 = new Player(2,name2);
						home.dispose();
						new GameBoard(p1, p2, 2, name1, name2);
					}
				}
			});
		 startGame2.setFont(new Font("Century", Font.PLAIN, 20));
		 startGame2.setSize(new Dimension(40, 50));
		 box.add(startGame2);
		 box.add(htpb);
	
		 mainPanel.add(box);
		 
		 home.add(mainPanel);
	 

	     home.setVisible(true);
	     home.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


	}
}