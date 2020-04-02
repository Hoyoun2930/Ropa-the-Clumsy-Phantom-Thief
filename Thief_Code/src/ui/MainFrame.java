package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ui.container.GamePlayContainer;
import ui.container.MainMenuContainer;
import ui.container.MyContainer;
import ui.container.StageSelectContainer;
import ui.key.KeyboardInput;
import ui.mouse.MouseInput;

public class MainFrame extends JFrame {
	static final int width = 1280;

	static final int height = 720;

	KeyboardInput keyboardInput;

	MouseInput mouseInput;

	private Container mainWindow;

	private MyContainer nowContainer;

	private MainMenuContainer mainMenuContainer;

	private StageSelectContainer stageSelectContainer;

	private GamePlayContainer gamePlayContainer;

	private Image backgroundImage = null;

	private Image backgroundImage1 = null;

	private Image backgroundImage2 = null;

	static Toolkit tk = Toolkit.getDefaultToolkit();

	private Image offscreen;

	private Graphics offgraphics;

	private Dimension actualSize;

	private int rx;

	private int ry;

	public MainFrame(String title, KeyboardInput keyboardInput, MouseInput mouseInput) {
		super(title);
		this.rx = 0;
		this.ry = 0;
		this.keyboardInput = keyboardInput;
		this.mouseInput = mouseInput;
		addKeyListener((KeyListener) keyboardInput);
		addMouseListener((MouseListener) mouseInput);
		addMouseMotionListener((MouseMotionListener) mouseInput);
		this.mainWindow = getContentPane();
		setLocation(200, 200);
		setVisible(true);
		setWindow();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		requestFocus();
		setFocusable(true);
		this.rx = (getWidth() - 1280) / 2;
		this.ry = getHeight() - 720 - this.rx;
		this.mainMenuContainer = new MainMenuContainer(this);
		this.mainMenuContainer.setRxy(this.rx, this.ry);
		this.mainMenuContainer.addButton(new UiButton(this, 0, 40, 300, 207, 56, "start"));
		this.mainMenuContainer.addButton(new UiButton(this, 1, 40, 400, 207, 56, "option"));
		this.mainMenuContainer.addButton(new UiButton(this, 2, 40, 500, 207, 56, "end"));
		this.mainMenuContainer.setMenu(0, 2);
		this.mainMenuContainer.setListenr(this);
		setContentPane((Container) this.mainMenuContainer);
		this.nowContainer = (MyContainer) getContentPane();
		this.backgroundImage1 = tk.getImage(".\\resource\\main.png");
		this.backgroundImage = this.backgroundImage1;
		this.stageSelectContainer = new StageSelectContainer(this);
		this.stageSelectContainer.setRxy(this.rx, this.ry);
		this.stageSelectContainer.addButton(new UiButton(this, 4, 70, 89, 110, 77, "back"));
		this.stageSelectContainer.addButton(new UiButton(this, 1001, 500, 300, 300, 300, "1"));
		this.stageSelectContainer.addButton(new UiButton(this, 1002, 900, 300, 300, 300, "2"));
		this.stageSelectContainer.addButton(new UiButton(this, 1003, 1300, 300, 300, 300, "3"));
		this.stageSelectContainer.addButton(new UiButton(this, 1004, 1700, 300, 300, 300, "4"));
		this.stageSelectContainer.addButton(new UiButton(this, 1005, 2200, 300, 300, 300, "5"));
		this.stageSelectContainer.addButton(new UiButton(this, 1006, 2600, 300, 300, 300, "6"));
		this.stageSelectContainer.addButton(new UiButton(this, 1007, 2600, 300, 300, 300, "7"));
		this.stageSelectContainer.addButton(new UiButton(this, 1008, 2600, 300, 300, 300, "8"));
		this.stageSelectContainer.addButton(new UiButton(this, 1009, 2600, 300, 300, 300, "9"));
		this.stageSelectContainer.addButton(new UiButton(this, 1010, 2600, 300, 300, 300, "10"));
		this.stageSelectContainer.addButton(new UiButton(this, 1011, 2600, 300, 300, 300, "11"));
		this.stageSelectContainer.addButton(new UiButton(this, 1012, 2600, 300, 300, 300, "12"));
		this.stageSelectContainer.addButton(new UiButton(this, 1013, 2600, 300, 300, 300, "13"));
		this.stageSelectContainer.addButton(new UiButton(this, 1014, 2600, 300, 300, 300, "14"));
		this.stageSelectContainer.addButton(new UiButton(this, 1100, 20, 400, 80, 80, "leftb"));
		this.stageSelectContainer.addButton(new UiButton(this, 1101, 1200, 400, 80, 80, "rightb"));
		this.stageSelectContainer.setScroll(0, 1, 14, 15, 16);
		this.backgroundImage2 = tk.getImage(".\\resource\\stage select1.png");
		this.gamePlayContainer = new GamePlayContainer(this);
		this.gamePlayContainer.setRxy(this.rx, this.ry);
		this.gamePlayContainer.addButton(new UiButton(this, 2000, 0, 0, 0, 0, ""));
		this.gamePlayContainer.setBackButton(0);
	}

	private void setWindow() {
		Dimension d = new Dimension(1280, 720);
		this.mainWindow.setPreferredSize(d);
		pack();
		setResizable(false);
		setVisible(true);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics graph) {
		if (this.offscreen == null)
			this.offscreen = createImage(getWidth() - this.rx, getHeight() - this.rx);
		if (this.offgraphics == null)
			this.offgraphics = this.offscreen.getGraphics();
		this.offgraphics.clearRect(0, 0, getWidth(), getHeight());
		this.offgraphics.setColor(Color.BLACK);
		this.offgraphics.fillRect(0, 0, getWidth(), getHeight());
		this.offgraphics.setColor(Color.BLACK);
		this.offgraphics.drawRect(this.rx + 0, this.ry + 0, 1280, 720);
		this.offgraphics.drawImage(this.backgroundImage, this.rx, this.ry, this);
		if (this.nowContainer == null)
			return;
		this.nowContainer.paint(this.offgraphics);
		graph.drawImage(this.offscreen, 0, 0, this);
	}

	public void running() {
		int n, id = -1;
		id = this.nowContainer.running();
		switch (id) {
		case 0:
			this.nowContainer.removeListenr(this);
			this.nowContainer = (MyContainer) this.stageSelectContainer;
			this.backgroundImage = this.backgroundImage2;
			this.nowContainer.setListenr(this);
			break;
		case 2:
			n = JOptionPane.showConfirmDialog(this, "게임을 종료하겠습니까?", "게임 종료", 0, 3, null);
			if (n == 0)
				System.exit(0);
		case 4:
			this.nowContainer.removeListenr(this);
			this.nowContainer = (MyContainer) this.mainMenuContainer;
			this.backgroundImage = this.backgroundImage1;
			this.nowContainer.setListenr(this);
			break;
		case 2000:
			this.nowContainer.removeListenr(this);
			this.nowContainer = (MyContainer) this.stageSelectContainer;
			this.backgroundImage = this.backgroundImage2;
			this.nowContainer.setListenr(this);
			break;
		}
		if (id > 1000 && id < 1007) {
			this.nowContainer.removeListenr(this);
			this.nowContainer = (MyContainer) this.gamePlayContainer;
			((GamePlayContainer) this.nowContainer).setMap(id - 1000, this.rx, this.ry);
			this.backgroundImage = null;
			this.nowContainer.setListenr(this);
		}
	}
}
