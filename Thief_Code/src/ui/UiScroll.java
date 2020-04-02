package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class UiScroll implements KeyListener {
	private ArrayList<UiButton> item;

	private UiButton back;

	private UiButton right;

	private UiButton left;

	private int select = 1;

	private int numOfitem = 0;

	private boolean isMove = false;

	public UiScroll() {
		this.item = new ArrayList<>();
	}

	public void addItem(UiButton button) {
		this.item.add(button);
		this.numOfitem++;
	}

	public void setBackButton(UiButton button) {
		this.back = button;
	}

	public void setLeftRightButton(UiButton l, UiButton r) {
		this.left = l;
		this.right = r;
	}

	public void selectLeft() {
		this.select--;
		if (this.select <= 0)
			this.select = 1;
		setButtonPosition();
	}

	public void selectRight() {
		this.select++;
		if (this.select > this.numOfitem)
			this.select = this.numOfitem;
		setButtonPosition();
	}

	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case 8:
			this.back.setStatus(2);
			return;
		case 32:
			if (this.select != 0)
				((UiButton) this.item.get(this.select - 1)).setStatus(2);
			break;
		case 37:
			selectLeft();
			break;
		case 39:
			selectRight();
			break;
		}
		for (int i = 0; i < this.numOfitem; i++)
			((UiButton) this.item.get(i)).setStatus(0);
		if (this.select != 0)
			((UiButton) this.item.get(this.select - 1)).setStatus(1);
	}

	private void setButtonPosition() {
		for (int i = 0; i < this.item.size(); i++)
			((UiButton) this.item.get(i)).setX(500 + 400 * i - 400 * (this.select - 1));
	}

	public void keyReleased(KeyEvent key) {
		switch (key.getKeyCode()) {
		case 10:
		case 32:
			if (this.select != 0)
				((UiButton) this.item.get(this.select - 1)).setStatus(3);
			break;
		case 8:
			this.back.setStatus(3);
			break;
		}
	}

	public void keyTyped(KeyEvent key) {
	}
}