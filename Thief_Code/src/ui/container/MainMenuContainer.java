package ui.container;

import javax.swing.JFrame;

import ui.UiButton;

public class MainMenuContainer extends MyContainer {
	public MainMenuContainer(JFrame parent) {
		super(parent);
	}

	public int running() {
		int id = -1;
		for (int i = 0; i < this.buttons.size(); i++) {
			id = ((UiButton) this.buttons.get(i)).run();
			if (id != -1)
				break;
		}
		switch (id) {

		}
		return id;
	}
}