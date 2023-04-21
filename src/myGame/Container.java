package myGame;

import javax.swing.JFrame;

import myGame.Model.Stage;

public class Container extends JFrame {

	public Container() {
		add(new Stage()); // adicionando a classe java a essa classe criada
		setTitle("Rocket Game for Enzo Rodrigues"); // titulo da tela
		setSize(1500, 800); // tamanho da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quando clicado no x, ele encerraria o programa
		setLocationRelativeTo(null); // seria aonde a tela se inicializaria
		this.setResizable(false); // seria para aumentar ou diminuir o tamanho da tela, o false, nao permitiria
									// isso
		setVisible(true); // definicao que esses comandos sejam visiveis
	}

	public static void main(String[] args) {
		new Container();
	}

}
