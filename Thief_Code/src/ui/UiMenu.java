package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class UiMenu implements KeyListener {
	private ArrayList<UiButton> item;

	private int select = 0;

	private int numOfitem = 0;

	public UiMenu() {
		this.item = new ArrayList<>();
	}

	public void addItem(UiButton button) {
		this.item.add(button);
		this.numOfitem++;
	}

	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case 27:
			System.out.println("escape");
			return;
		case 37:
		case 38:
			this.select--;
			if (this.select <= 0)
				this.select = this.numOfitem;
			break;
		case 39:
		case 40:
			this.select++;
			if (this.select > this.numOfitem)
				this.select = 1;
			break;
		}
		for (int i = 0; i < this.numOfitem; i++)
			((UiButton) this.item.get(i)).setStatus(0);
		if (this.select != 0)
			((UiButton) this.item.get(this.select - 1)).setStatus(1);
	}

	public void keyReleased(KeyEvent key) {
		switch (key.getKeyCode()) {
		case 10:
		case 32:
			if (this.select != 0)
				((UiButton) this.item.get(this.select - 1)).setStatus(3);
			break;
		}
	}

	public void keyTyped(KeyEvent key) {
	}
}