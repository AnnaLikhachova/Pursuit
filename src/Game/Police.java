package Game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Police {
	int x;
	int y;
	int speed;
	Image img = new ImageIcon("Resources/Police.png").getImage();
	Landscape landscape;
	
	public Rectangle getRect(){
		return new Rectangle(x,y, 161,54);
	}
	
	public Police(int x, int y, int speed, Landscape landscape){
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.landscape=landscape;
		
	}
	
	public void move(){
		x = x- landscape.player.speed+speed;
	}
}
