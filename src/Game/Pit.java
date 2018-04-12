package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Pit {
	int x;
	int y;
	Image img_pit = new ImageIcon("Resources/pit.png").getImage();
	Landscape landscape;

	public Rectangle getRect(){
		return new Rectangle(x,y, 61,24);
	}
	
	public Pit(int x, int y, Landscape landscape){
		this.x=x;
		this.y=y;
		this.landscape=landscape;
		
	}
	
}
