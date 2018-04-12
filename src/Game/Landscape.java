package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Landscape extends JPanel implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	Timer time = new Timer(20, this);
	Image img = new ImageIcon("Resources/Road.png").getImage();
	Player player = new Player();
	Thread policeout = new Thread(this);
	List<Police> police = new ArrayList<Police>();
	List<Pit> pit = new ArrayList<Pit>();

	public Landscape() {
		time.start();
		policeout.start();
		addKeyListener(new myKeyAdapter());
		setFocusable(true);
	}

	private class myKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		public void KeyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}

	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, player.layer1, 0, null);
		g.drawImage(img, player.layer2, 0, null);
		g.drawImage(player.img, player.x, player.y, null);
		double speed = (100 / Player.Max_speed) * player.speed;
		g.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.ITALIC, 110);
		g.setFont(getFont());
		g.drawString("Speed: " + speed + " km per hour", 150, 30);

		Iterator<Police> i = police.iterator();
		while (i.hasNext()) {
			Police e = i.next();
			g.drawImage(e.img, e.x, e.y, null);
			if (e.x >= 2000 || e.x <= -2000) {
				i.remove();
			} else {
				e.move();
				g.drawImage(e.img, e.x, e.y, null);
			}
		}

		Iterator<Pit> t = pit.iterator();
		while (t.hasNext()) {
			Pit p = t.next();
			g.drawImage(p.img_pit, p.x, p.y, null);
		}

	}

	public void actionPerformed(ActionEvent e) {
		player.move();
		repaint();
		testCollisionWithPolice();
		testCollisionWithPit();
		youWin();
	}

	private void youWin() {
		if (player.roadmap >= 30000) {
			JOptionPane.showMessageDialog(null, "You won this race!!!");
			System.exit(0);
		}
	}

	private void testCollisionWithPit() {
		Iterator<Pit> t = pit.iterator();
		while (t.hasNext()) {
			Pit p = t.next();
			if (player.getRect().intersects(p.getRect())) {
				JOptionPane.showMessageDialog(null, "You lost the game!");
				System.exit(1);
			}
		}
	}

	private void testCollisionWithPolice() {
		Iterator<Police> i = police.iterator();
		while (i.hasNext()) {
			Police e = i.next();
			if (player.getRect().intersects(e.getRect())) {
				JOptionPane.showMessageDialog(null, "You lost the game!");
				System.exit(1);
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(5000));
				pit.add(new Pit(1200, rand.nextInt(600), this));
				police.add(new Police(1200, rand.nextInt(600), rand.nextInt(30), this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
			}
		}
	}
}
