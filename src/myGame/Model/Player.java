package myGame.Model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {
	private int x, y;
	private int dx, dy; // maneira de nomear 2 variaveis de uma unica vez
	private Image image;
	private int width, height;
	private List<Fire> fires;
	private boolean isVisible;
	private boolean isNitro;

	public boolean isNitro() {
		return isNitro;
	}

	private Timer timer;

	public Player() {
		this.x = 20;
		this.y = 100; // coordenada que o player ira aparecer na tela assim que o jogo iniciar
		isVisible = true;
		isNitro = false;
		fires = new ArrayList<Fire>();
		timer = new Timer(5000, this); // velocidade que o jogo ocorreria, caso precise aumentar, seria só alterar isso
		timer.start();
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void load() {
		ImageIcon reference = new ImageIcon("res\\playerImage.png");
		image = reference.getImage();
		height = image.getHeight(null);
		width = image.getWidth(null);
	}

	public void Move() {
		x += dx;
		y += dy;
	}

	public Rectangle getBounds() { // essa classe, atribui um retangulo envolta do nosso jogador, fazendo assim que
									// tenha um retangulo envolta do msm, podendo existir a interacao de atributos
									// ao cenario
		return new Rectangle(x, y, width, height);
	}

	public void simpleFire() {
		this.fires.add(new Fire(x + width, y + (height / 2)));

	}

	public void nitro() {
		isNitro = true;
		ImageIcon reference = new ImageIcon("res\\turbo.png");
		image = reference.getImage();
	}

	public void keyPressed(KeyEvent tecla) { // a classe key event é responsavel por eventos de tecla do proprio teclado
												// como entrada
		int code = tecla.getKeyCode(); // aqui, ele reconheceria uma tecla como entrada para uma determinada acao

		if (code == KeyEvent.VK_SPACE) { // essa vk, seria o reconhecimento padrao dessa classe, o vkup, seria
			nitro();
		}

		if (code == KeyEvent.VK_A) { // essa vk, seria o reconhecimento padrao dessa classe, o vkup, seria
			if (isNitro == false) {// responsavel pela seta pra cima
				simpleFire();
			}
		}

		if (code == KeyEvent.VK_UP) { // essa vk, seria o reconhecimento padrao dessa classe, o vkup, seria
			if (code > 0) { // responsavel pela seta pra cima
				dy = -3;
			}
		}

		if (code == KeyEvent.VK_DOWN) { // essa vk, seria o reconhecimento padrao dessa classe, o vkdown, seria
			if (code > 0) { // responsavel pela seta pra baixo
				dy = 3;
			}
		}

		if (code == KeyEvent.VK_LEFT) { // essa vk, seria o reconhecimento padrao dessa classe, o vkleft, seria
			if (code > 0) { // responsavel pela seta pra esquerda
				dx = -3;
			}
		}

		if (code == KeyEvent.VK_RIGHT) { // essa vk, seria o reconhecimento padrao dessa classe, o vkrigth, seria
			if (code > 0) { // responsavel pela seta pra direita
				dx = 3;
			}
		}
	}

	public void keyRelease(KeyEvent tecla) { // quando deixarmos de pressionar a tecla, o nosso keyevent sera 0
		int code = tecla.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (code == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (code == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (code == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public List<Fire> getFires() {
		return fires;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isNitro == true) {
			nitro();
			isNitro = false;
		}

		if (isNitro == false) {
			load();
		}

	}

}
