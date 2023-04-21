package myGame.Model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {

	private Image image;
	private int x, y;
	private int width, height;
	private boolean isVisible;
	private static final int LARGE = 1400;
	private static int VELOCITY = 4;

	public Enemy1(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public Rectangle getBounds() { // essa classe, atribui um retangulo envolta do nosso jogador, fazendo assim que
									// tenha um retangulo envolta do msm, podendo existir a interacao de atributos
									// ao cenario
		return new Rectangle(x, y, width, height);
	}

	public void load() {
		ImageIcon reference = new ImageIcon("res\\inimigo2.png");
		image = reference.getImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void update() {
		this.x -= VELOCITY;
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
