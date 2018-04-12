package Game;


import javax.swing.JFrame;

public class GameM {


		public static void main(String[] args) {
			JFrame n = new JFrame("Game");
			n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			n.setSize(1024, 704);
			n.add(new Landscape());
			n.setVisible(true);
			Sound.playSound("Resources/policiyazvukas.aiff").join();
			
			
		}
		
	}


