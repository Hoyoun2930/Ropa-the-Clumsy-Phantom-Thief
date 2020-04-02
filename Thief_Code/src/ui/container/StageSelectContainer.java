package ui.container;

import javax.swing.JFrame;

import ui.UiButton;
import ui.UiScroll;

public class StageSelectContainer extends MyContainer {

	public StageSelectContainer(JFrame parent) {
		super(parent);
		scroll = new UiScroll();
	}

	public void setScroll(int back, int start, int end, int left, int right) {
		for (int i = start; i <= end; i++)
			scroll.addItem((UiButton) buttons.get(i));

		scroll.setBackButton((UiButton) buttons.get(back));
		scroll.setLeftRightButton((UiButton) buttons.get(left), (UiButton) buttons.get(right));
	}

	public void setListenr(JFrame mainf) {
		super.setListenr(mainf);
		mainf.addKeyListener(scroll);
	}

	public void removeListenr(JFrame mainf) {
		super.removeListenr(mainf);
		mainf.removeKeyListener(scroll);
	}

	public int running() {
		int id = -1;
		int i;
		for (i = 0; i < buttons.size(); i++) {
			id = ((UiButton) buttons.get(i)).run();
			if (id != -1)
				break;
		}

		switch (id) {
		case 4: // '\004'
			System.out.println((new StringBuilder("Å¬¸¯ back : ")).append(i).toString());
			break;

		case 1100:
			scroll.selectLeft();
			break;

		case 1101:
			scroll.selectRight();
			break;
		}
		return id;
	}

	private UiScroll scroll;
}