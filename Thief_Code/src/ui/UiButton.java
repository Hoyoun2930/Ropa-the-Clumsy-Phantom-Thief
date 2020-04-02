package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class UiButton implements MouseListener, MouseMotionListener {
	private int x1;

	private int y1;

	private int x2;

	private int y2;

	private int width;

	private int height;

	private int rx;

	private int ry;

	private int id;

	private int status = 0;

	private Graphics graph;

	public static final int off = 0;

	public static final int on = 1;

	public static final int click = 2;

	public static final int release = 3;

	private Image onImage = null;

	private Image offImage = null;

	private JFrame parent;

	static Toolkit tk = Toolkit.getDefaultToolkit();

	public UiButton(JFrame parent, int id, int x1, int y1, int width, int height, String imageName) {
		this.id = id;
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height = height;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		this.parent = parent;
		this.onImage = tk.getImage(".\\resource\\" + imageName + "2.png");
		this.offImage = tk.getImage(".\\resource\\" + imageName + "1.png");
	}

	public void setX(int x1) {
		this.x1 = x1;
		this.x2 = x1 + this.width;
	}

	public int getX() {
		return this.x1;
	}

	public void setRxy(int x, int y) {
		this.rx = x;
		this.ry = y;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setGraphic(Graphics graph) {
		if (this.graph == null)
			this.graph = graph;
	}

	public int run() {
		if (this.status == 3) {
			paint();
			this.status = 1;
			return this.id;
		}
		return -1;
	}

	public void paint() {
		if (this.graph == null)
			return;
		int x = this.x1 + this.rx;
		int y = this.y1 + this.ry;
		switch (this.status) {
		case 0:
		case 3:
			this.graph.drawImage(this.offImage, x, y, this.parent);
			break;
		case 1:
			this.graph.drawImage(this.onImage, x, y, this.parent);
			break;
		case 2:
			this.graph.drawImage(this.onImage, x, y, this.parent);
			break;
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (this.status == 1) {
			this.status = 2;
			paint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (this.status == 2)
			this.status = 3;
		paint();
	}

	public void mouseDragged(MouseEvent mouse) {
	}

	public void mouseMoved(MouseEvent mouse) {
		int x = mouse.getX() - this.rx;
		int y = mouse.getY() - this.ry;
		if (this.x1 <= x && x <= this.x2 && this.y1 <= y && y <= this.y2) {
			this.status = 1;
			paint();
		} else {
			this.status = 0;
			paint();
		}
	}
}