package main;

import ui.MainFrame;
import ui.key.KeyboardInput;
import ui.mouse.MouseInput;

public class MainClass {
	static MainFrame mainFrame;

	public static void main(String[] args) {
		KeyboardInput keyboardInput = new KeyboardInput();
		MouseInput mouseInput = new MouseInput();
		mainFrame = new MainFrame("The Clumsy Phantom Thief", keyboardInput, mouseInput);
		GamePlayThread gamePlayThread = new GamePlayThread();
		gamePlayThread.start();
	}

	static class GamePlayThread extends Thread {
		public void run() {
			try {
				while (true) {
					Thread.sleep(10L);
					MainClass.mainFrame.repaint();
					MainClass.mainFrame.running();
					MainClass.mainFrame.repaint();
				}
			} catch (InterruptedException interruptedException) {
				return;
			}
		}
	}
}
