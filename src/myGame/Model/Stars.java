package myGame.Model;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {

	private Image image;
	private int x, y;
	private int width, height;
	private boolean isVisible;
	private static int VELOCITY = 7;

	public Stars(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\estrela.png");
		image = referencia.getImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = width;
			Random a = new Random();
			int m = a.nextInt(500);
			this.x = m + 1024;
			Random b = new Random();
			int r = b.nextInt(1000);
			this.y = r;
		} else {
			this.x -= VELOCITY;

		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public static int getVELOCITY() {
		return VELOCITY;
	}

	public static void setVELOCITY(int vELOCITY) {
		VELOCITY = vELOCITY;
	}

}
