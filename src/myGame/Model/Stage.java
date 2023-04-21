package myGame.Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage extends JPanel implements ActionListener { // sistema de janelas do proprio java

	private Image background; // objeto do tipo imagem, sempre que necessario a insercao de uma imagem, esse
								// objeto seria necessario
	private Player player;
	private Timer timer; // biblioteca do java swing
	private List<Enemy1> enemy1;
	private List<Enemy2> enemy2;
	private List<Stars> stars;
	private boolean inGame;
	private boolean restart;

	public Stage() {
		setFocusable(true);
		setDoubleBuffered(true);// metodos para melhorar desempenho do jogo

		ImageIcon reference = new ImageIcon("res\\spaceImage.png"); // icone da pagina
		background = reference.getImage(); // pegando a imagem da instancia de referencia que criamos

		player = new Player(); // instancia do objeto criado fazendo ascensao a classe que ja possuimos
		player.load();// imagem do player

		addKeyListener(new TecladoAdapter());
		timer = new Timer(30, this); // velocidade que o jogo ocorreria, caso precise aumentar, seria só alterar isso
		timer.start();
		InitialEnemies();
		inGame = true;
		InitialStars();
		restart();
	}

	public void InitialEnemies() {

		int coord[] = new int[30];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < coord.length; i++) {
			int x = (int) (Math.random() * 4000 + 1500); // esse mathrando escolheria um numero entre 8000 e 1024, entao
															// ele gera um inimigo que vem vindo e vindo e vindo ate que
															// chega em nosso personagem, isso faz com que ele apareca
															// em lugares diferentes no x na tela
			int y = (int) (Math.random() * 500 + 30);
			enemy1.add(new Enemy1(x, y));
		}

		int coorde[] = new int[30];
		enemy2 = new ArrayList<Enemy2>();

		for (int i = 0; i < coorde.length; i++) {
			int x = (int) (Math.random() * 4000 + 1500); // esse mathrando escolheria um numero entre 8000 e 1024, entao
															// ele gera um inimigo que vem vindo e vindo e vindo ate que
															// chega em nosso personagem, isso faz com que ele apareca
															// em lugares diferentes no x na tela
			int y = (int) (Math.random() * 500 + 30);
			enemy2.add(new Enemy2(x, y));
		}

	}

	public void InitialStars() {

		int coord[] = new int[40];
		stars = new ArrayList<Stars>();

		for (int i = 0; i < coord.length; i++) {
			int x = (int) (Math.random() * 1500 + 0); // esse mathrando escolheria um numero entre 8000 e 1024, entao
														// ele gera um inimigo que vem vindo e vindo e vindo ate que
														// chega em nosso personagem, isso faz com que ele apareca
														// em lugares diferentes no x na tela
			int y = (int) (Math.random() * 1098 + 0);
			stars.add(new Stars(x, y));
		}

	}

	public void paint(Graphics g) { // essa seria uma classe do java tambem, utilizada bastante para desenvolvimento
									// de jogos
		Graphics2D graphic = (Graphics2D) g; // uma conversao de graphics para graphics 2d de maneira direta
		if (inGame == true) {

			graphic.drawImage(background, 0, 0, null); // esse seria o passo para pintar na tela msm a imagem, sendo seu
			// primeiro parametro a imagem em si, o segundo a posicao x dela, o
			// terceiro a posicao y, e o ultimo se ela aparecia no meio da tela,
			// sendo assim o meio é null

			for (int p = 0; p < stars.size(); p++) {
				Stars q = stars.get(p);
				q.load();
				graphic.drawImage(q.getImage(), q.getX(), q.getY(), this);
			}

			graphic.drawImage(player.getImage(), player.getX(), player.getY(), this);
			List<Fire> fires = player.getFires();
			for (int i = 0; i < fires.size(); i++) {
				Fire m = fires.get(i);
				m.load();
				graphic.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < enemy1.size(); i++) {

				Enemy1 enem = enemy1.get(i);
				enem.load();
				graphic.drawImage(enem.getImage(), enem.getX(), enem.getY(), this);
			}

			for (int f = 0; f < enemy2.size(); f++) {

				Enemy2 enemi = enemy2.get(f);
				enemi.load();
				graphic.drawImage(enemi.getImage(), enemi.getX(), enemi.getY(), this);

			}
		} else {

			ImageIcon endGame = new ImageIcon("res\\gameOver.png");
			graphic.drawImage(endGame.getImage(), 0, 0, null);

		}

		g.dispose(); // disponibilizando essas configuracoes para serem rodadas
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int p = 0; p < stars.size(); p++) {
			Stars om = stars.get(p);
			if (om.isVisible()) {
				om.update();
			} else {
				stars.remove(p);
			}

		}

		player.Move(); // metodo para atualizar a tela

		if (player.isNitro() == true) {
			timer.setDelay(1);
		}

		if (player.isNitro() == false) {
			timer.setDelay(10);
		}

		List<Fire> fires = player.getFires();
		for (int i = 0; i < fires.size(); i++) {
			Fire m = fires.get(i);
			if (m.isVisible()) {
				m.update();

				if (player.isNitro()) {
					fires.get(i).setVELOCITY(-5);
				}

				if (player.isNitro() == false) {
					fires.get(i).setVELOCITY(2);
				}

			} else {
				fires.remove(i);
			}
		}

		for (int i = 0; i < enemy1.size(); i++) {

			Enemy1 enem = enemy1.get(i);
			if (enem.isVisible()) {
				enem.update();
			} else {
				enemy1.remove(i);
			}
		}
		for (int t = 0; t < enemy2.size(); t++) {

			Enemy2 eni = enemy2.get(t);
			if (eni.isVisible()) {
				eni.update();
			} else {
				enemy2.remove(t);
			}

		}

		checkColis();

		repaint(); // ele repintara a tela, para que nao volte a ficar como sempre

	}

	public void checkColis() {
		Rectangle formatNav = player.getBounds();
		Rectangle formatEnemy1;
		Rectangle formatEnemy2;
		Rectangle formatFire;

		for (int i = 0; i < enemy1.size(); i++) { // criacao do metodo de colisao do personagem com o inimigo
			Enemy1 tempEnemy1 = enemy1.get(i);
			formatEnemy1 = tempEnemy1.getBounds();
			if (formatNav.intersects(formatEnemy1)) {
				if (player.isNitro()) {
					tempEnemy1.setVisible(false);
				} else {
					player.setVisible(false);
					tempEnemy1.setVisible(false);
					inGame = false;
				}
			}

		}

		for (int i = 0; i < enemy2.size(); i++) { // criacao do metodo de colisao do personagem com o inimigo
			Enemy2 tempEnemy2 = enemy2.get(i);
			formatEnemy2 = tempEnemy2.getBounds();
			if (formatNav.intersects(formatEnemy2)) {
				if (player.isNitro()) {
					tempEnemy2.setVisible(false);
				} else {
					player.setVisible(false);
					tempEnemy2.setVisible(false);
					inGame = false;
				}
			}

		}

		List<Fire> fires = player.getFires();
		for (int j = 0; j < fires.size(); j++) {
			Fire tempTiro = fires.get(j);
			formatFire = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formatEnemy1 = tempEnemy1.getBounds();
				if (formatFire.intersects(formatEnemy1)) {

					tempEnemy1.setVisible(false);
					tempTiro.setVisible(false);

				}
			}

			for (int h = 0; h < enemy2.size(); h++) {
				Enemy2 tempEnemy2 = enemy2.get(h);
				formatEnemy1 = tempEnemy2.getBounds();
				if (formatFire.intersects(formatEnemy1)) {

					tempEnemy2.setVisible(false);
					tempTiro.setVisible(false);

				}
			}
		}

	}

	public void restart() {
		inGame = true;
	}

	private class TecladoAdapter extends KeyAdapter { // metodo de entrada de teclado

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}

}
