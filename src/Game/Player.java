package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
	public static final int Max_speed = 50;
	public static final int Max_Top = 90;
	public static final int Max_bottom = 560;

	Image img = new ImageIcon("Resources/Car1.png").getImage();

	public Rectangle getRect() {
		return new Rectangle(x, y, 164, 44);
	}

	int speed = 0;
	int acceleration = 0;
	int roadmap = 0;

	int x = 100;
	int y = 120;
	int move_y = 0;

	int layer1 = 0;
	int layer2 = 1024;

	public void move() {
		roadmap += speed;
		speed += acceleration;

		if (speed <= 0)
			speed = 0;

		if (speed >= Max_speed)
			speed = Max_speed;

		y -= move_y;

		if (y <= Max_Top)
			y = Max_Top;
		
		if (y >= Max_bottom)
			y = Max_bottom;

		if (layer2 - speed <= 0) {
			layer1 = 0;
			layer2 = 1024;
		} else {
			layer1 -= speed;
			layer2 -= speed;
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			acceleration = 1;
		}
		if (key == KeyEvent.VK_LEFT) {
			acceleration = -1;
		}
		if (key == KeyEvent.VK_UP) {
			move_y = 5;
		}
		if (key == KeyEvent.VK_DOWN) {
			move_y = -5;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			acceleration = 0;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			move_y = 0;
		}

	}
}
