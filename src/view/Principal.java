package view;

import controller.ThreadPing;

public class Principal {

	public static void main(String[] args) {
		
		Thread pingUOL = new ThreadPing("www.uol.com.br", 1);
		pingUOL.start();
		Thread pingTerra = new ThreadPing("www.terra.com.br", 2);
		pingTerra.start();
		Thread pingGoogle = new ThreadPing("www.google.com.br", 3);
		pingGoogle.start();
	}
}
