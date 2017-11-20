import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScores extends JFrame{
	public HighScores(){
			final JFrame hs = new JFrame("Scores");
		    hs.setSize(570, 570);
		    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			hs.setLocation(dim.width/2-hs.getSize().width/2, dim.height/2-hs.getSize().height/2);
			JPanel mainP = new JPanel(new GridBagLayout());
			mainP.setOpaque(true);
			mainP.setBackground(Color.CYAN);
		    GridBagConstraints constraints = new GridBagConstraints();

		    constraints.gridx = 0;
		    constraints.gridy = 0;
		    constraints.anchor = GridBagConstraints.LAST_LINE_START;

		for(Map.Entry<String,Integer> entry : Users.wins.entrySet()) {
			  String key = entry.getKey();
			  Integer value = entry.getValue();
			  String v = "UserName: " + key + ", Wins: " + value.toString();
			  JLabel j = new JLabel();
			  j.setText(v);
			  j.setFont(new Font("Courier New", Font.BOLD, 15));
			  j.setOpaque(true);
			  j.setBackground(Color.ORANGE);
			  j.setMinimumSize(new Dimension(250, 40));
			  j.setPreferredSize(new Dimension(260, 50));
			  j.setMaximumSize(new Dimension(270, 60));
			  constraints.gridy++;
			  mainP.add(j, constraints);
	    }
		hs.add(mainP, BorderLayout.NORTH);
		JButton back = new JButton("Home");
		back.setOpaque(true);
		back.setForeground(Color.BLACK);
		constraints.gridy++;
		 back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hs.dispose();
					new HomeScreen();
				}
			});
		hs.add(back, BorderLayout.SOUTH);
		hs.setBackground(Color.ORANGE);
		hs.setVisible(true);
		hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

