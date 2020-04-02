package ui.container;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import map.Map;
import map.MapManager;
import type.Enemy;
import type.Thief;
import ui.UiButton;

public class GamePlayContainer extends MyContainer implements KeyListener {
	private UiButton back;

	private boolean isloaded = false;

	private Image[] tile;

	private Image ropa;

	private Image ropa1;

	private Image ropa2;

	private Image ropa3;

	private Image police;

	private Image det1;

	private Image det2;

	private Image det3;

	private Image[] see;

	private Map map;

	private int mapName;

	private Thief player;

	private ArrayList<Enemy> objectsList;

	private int rx;

	private int ry;

	private int mx;

	private int my;

	private boolean upKeyPressed = false;

	private boolean downKeyPressed = false;

	private boolean leftKeyPressed = false;

	private boolean rightKeyPressed = false;

	static final int STEP = 5;

	static final int ENEMY_STEP = 12;

	static final int FRAME = 10;

	static final int ENEMY_FRAME = 24;

	static Toolkit tk = Toolkit.getDefaultToolkit();

	private int ROWS;

	private int COLS;

	public GamePlayContainer(JFrame parent) {
		super(parent);
		this.ROWS = 9;
		this.COLS = 16;
		this.tile = new Image[30];
		this.objectsList = new ArrayList<>();
		int i;
		for (i = 0; i <= 9; i++)
			this.tile[i] = tk.getImage(".\\resource\\tile\\" + i + ".png");
		for (i = 10; i <= 19; i++)
			this.tile[i] = tk.getImage(".\\resource\\tile\\10.png");
		for (i = 20; i <= 21; i++)
			this.tile[i] = tk.getImage(".\\resource\\tile\\" + i + ".png");
		this.ropa = tk.getImage(".\\resource\\Ropa.png");
		this.ropa1 = tk.getImage(".\\resource\\Ropa - 1.png");
		this.ropa2 = tk.getImage(".\\resource\\Ropa - 2.png");
		this.ropa3 = tk.getImage(".\\resource\\Ropa - 3.png");
		this.police = tk.getImage(".\\resource\\police.png");
		this.det1 = tk.getImage(".\\resource\\detective1.png");
		this.det2 = tk.getImage(".\\resource\\detective2.png");
		this.det3 = tk.getImage(".\\resource\\detective3.png");
		this.see = new Image[4];
		for (i = 0; i < 4; i++)
			this.see[i] = tk.getImage(".\\resource\\see" + (i + 1) + ".png");
	}

	public void setRxy(int x, int y) {
		this.rx = x;
		this.ry = y;
	}

	public void setMap(int mapName, int x, int y) {
		MapManager mm = new MapManager();
		this.map = mm.getMap(mapName);
		this.mapName = mapName;
		setRxy(x, y);
		int i;
		for (i = 0; i < this.map.getRow(); i++) {
			for (int j = 0; j < this.map.getCol(); j++) {
				if (this.map.getMap()[i][j] == 20) {
					this.mx = 80 * j + 40;
					this.my = 80 * i + 40;
				}
			}
		}
		this.player = new Thief(this.mx, this.my, 11, 20, 2);
		this.player.setStepSize(20);
		this.objectsList.clear();
		int numOfPolice = this.map.getNumOfPolice();
		for (i = 0; i < numOfPolice; i++) {
			int px = this.map.getPositionP(i).getX();
			int py = this.map.getPositionP(i).getY();
			this.objectsList.add(new Enemy(px, py, 13, 20, 2, this.map.getPatternP(i)));
		}
		int numOfdetective = this.map.getNumOfSecuA();
		for (i = 0; i < numOfdetective; i++) {
			int px = this.map.getPositionA(i).getX();
			int py = this.map.getPositionA(i).getY();
			this.objectsList.add(new Enemy(px, py, 14, 20, 8, this.map.getPatternA(i)));
		}
		numOfdetective = this.map.getNumOfSecuB();
		for (i = 0; i < numOfdetective; i++) {
			int px = this.map.getPositionB(i).getX();
			int py = this.map.getPositionB(i).getY();
			this.objectsList.add(new Enemy(px, py, 15, 20, 4, this.map.getPatternB(i)));
		}
		numOfdetective = this.map.getNumOfSecuC();
		for (i = 0; i < numOfdetective; i++) {
			int px = this.map.getPositionC(i).getX();
			int py = this.map.getPositionC(i).getY();
			this.objectsList.add(new Enemy(px, py, 16, 20, 6, this.map.getPatternC(i)));
		}
		this.isloaded = true;
	}

	public void setBackButton(int index) {
		this.back = this.buttons.get(index);
	}

	public int running() {
		if (!this.isloaded)
			return -1;
		int wall_x = 0, wall_y = 0;
		int color = this.player.getCloth();
		this.player.run();
		int player_x = this.player.getX();
		int player_y = this.player.getY();
		int move = this.player.getStepSize();
		int tile = this.map.getMap()[player_y / 80][player_x / 80];
		int next_x = 0, next_y = 0;
		if (this.player.isMoving())
			this.player.increCounter();
		if (this.player.isMoving() && this.player.getCounter() % 5 == 0) {
			if (this.upKeyPressed) {
				next_y = -move;
				this.player.setDirection(2);
			}
			if (this.leftKeyPressed) {
				next_x = -move;
				this.player.setDirection(4);
			}
			if (this.downKeyPressed) {
				next_y = move;
				this.player.setDirection(8);
			}
			if (this.rightKeyPressed) {
				next_x = move;
				this.player.setDirection(6);
			}
			int point_x = (player_x + next_x) / 80;
			int point_y = player_y / 80;
			wall_x = (player_x + next_x) % 80;
			wall_y = player_y % 80;
			int next_tile = this.map.getMap()[point_y][point_x];
			if (next_tile != 0)
				switch (next_tile) {
				case 1:
				case 11:
					if (wall_x > 0 && wall_y > 0)
						player_x += next_x;
					break;
				case 2:
				case 12:
					if (wall_y > 0)
						player_x += next_x;
					break;
				case 3:
				case 13:
					if (wall_x < 60 && wall_y > 0)
						player_x += next_x;
					break;
				case 4:
				case 14:
					if (wall_x > 0)
						player_x += next_x;
					break;
				case 5:
				case 15:
					player_x += next_x;
					break;
				case 6:
				case 16:
					if (wall_x < 60)
						player_x += next_x;
					break;
				case 7:
				case 17:
					if (wall_x > 0 && wall_y < 60)
						player_x += next_x;
					break;
				case 8:
				case 18:
					if (wall_y < 60)
						player_x += next_x;
					break;
				case 9:
				case 19:
					if (wall_x < 60 && wall_y < 60)
						player_x += next_x;
					break;
				default:
					player_x += next_x;
					break;
				}
			point_x = player_x / 80;
			point_y = (player_y + next_y) / 80;
			wall_x = player_x % 80;
			wall_y = (player_y + next_y) % 80;
			next_tile = this.map.getMap()[point_y][point_x];
			if (next_tile != 0)
				switch (next_tile) {
				case 1:
				case 11:
					if (wall_x > 0 && wall_y > 0)
						player_y += next_y;
					break;
				case 2:
				case 12:
					if (wall_y > 0)
						player_y += next_y;
					break;
				case 3:
				case 13:
					if (wall_x < 60 && wall_y > 0)
						player_y += next_y;
					break;
				case 4:
				case 14:
					if (wall_x > 0)
						player_y += next_y;
					break;
				case 5:
				case 15:
					player_y += next_y;
					break;
				case 6:
				case 16:
					if (wall_x < 60)
						player_y += next_y;
					break;
				case 7:
				case 17:
					if (wall_x > 0 && wall_y < 60)
						player_y += next_y;
					break;
				case 8:
				case 18:
					if (wall_y < 60)
						player_y += next_y;
					break;
				case 9:
				case 19:
					if (wall_x < 60 && wall_y < 60)
						player_y += next_y;
					break;
				default:
					player_y += next_y;
					break;
				}
			this.player.setX(player_x);
			this.player.setY(player_y);
			System.out
					.println("(" + (player_x % 80) + "," + (player_y % 80) + ") counter : " + this.player.getCounter());
		}
		if (tile >= 11 && tile < 20)
			this.map.changeMap(player_x / 80, player_y / 80, tile - 10);
		if (tile == 21 && this.mapName < 6)
			setMap(this.mapName + 1, this.rx, this.ry);
		for (int i = 0; i < this.objectsList.size(); i++) {
			Enemy enemy = this.objectsList.get(i);
			enemy.increCounter();
			if (enemy.getCounter() % 12 == 0)
				switch (((Character) enemy.pattern.get(enemy.getCounter() / 12 / 4 % enemy.pattern.size()))
						.charValue()) {
				case 'U':
					enemy.moveUp();
					break;
				case 'D':
					enemy.moveDown();
					break;
				case 'L':
					enemy.moveLeft();
					break;
				case 'R':
					enemy.moveRight();
					break;
				}
		}
		float distance = 200.0F, dx = 200.0F, dy = 200.0F;
		float angle = 0.0F;
		int j;
		for (j = 0; j < this.objectsList.size(); j++) {
			Enemy enemy = this.objectsList.get(j);
			dx = (enemy.getX() - this.player.getX() + 1);
			dy = (enemy.getY() - this.player.getY() + 1);
			distance = (int) Math.sqrt((dx * dx + dy * dy));
			angle = dy / dx;
			if (enemy.getType() == 13 || enemy.getType() - 13 != this.player.getCloth())
				if (distance <= 150.0F)
					if (enemy.getDirection() == 6 && dx < 0.0F && Math.abs(angle) < 0.57D) {
						setMap(this.mapName, this.rx, this.ry);
					} else if (enemy.getDirection() == 4 && dx > 0.0F && Math.abs(angle) < 0.57D) {
						setMap(this.mapName, this.rx, this.ry);
					} else if (enemy.getDirection() == 2 && dy > 0.0F && Math.abs(angle) > 1.7D) {
						setMap(this.mapName, this.rx, this.ry);
					} else if (enemy.getDirection() == 8 && dy < 0.0F && Math.abs(angle) > 1.7D) {
						setMap(this.mapName, this.rx, this.ry);
					}
		}
		int id = -1;
		for (j = 0; j < this.buttons.size(); j++) {
			id = ((UiButton) this.buttons.get(j)).run();
			if (id != -1)
				break;
		}
		switch (id) {
		case 2000:
			System.out.println("\uD074\uB9AD 2000");
			break;
		}
		return id;
	}

	public void paint(Graphics graph) {
		int direction = 0;
		int frame = 0;
		Image nowRopa = this.ropa;
		if (this.player == null)
			return;
		switch (this.player.getCloth()) {
		case 1:
			nowRopa = this.ropa1;
			break;
		case 2:
			nowRopa = this.ropa2;
			break;
		case 3:
			nowRopa = this.ropa3;
			break;
		}
		int player_x = this.player.getX();
		int player_y = this.player.getY();
		int point_x = player_x / 80;
		int point_y = player_y / 80;
		int remain_x = player_x % 80;
		int remain_y = player_y % 80;
		int x = this.COLS / 2;
		int y = this.ROWS / 2;
		int index_x = point_x + x - this.COLS / 2;
		int index_y = point_y + y - this.ROWS / 2;
		int player_print_x = this.rx + x * 80;
		int player_print_y = this.ry + y * 80;
		if (!this.isloaded)
			return;
		for (y = 0; y < this.map.getRow() * 2; y++) {
			for (x = 0; x < this.map.getCol() * 2; x++) {
				index_x = point_x + x - this.COLS / 2;
				index_y = point_y + y - this.ROWS / 2;
				graph.setColor(Color.BLACK);
				int print_x = this.rx + x * 80 - remain_x;
				int print_y = this.ry + y * 80 - remain_y;
				if (index_x < 0 || index_x >= this.map.getCol() || index_y < 0 || index_y >= this.map.getRow()) {
					graph.setColor(Color.BLACK);
					graph.fillRect(print_x, print_y, 80, 80);
				} else {
					int tmp = this.map.getMap()[index_y][index_x];
					if (tmp >= 11 && tmp <= 19)
						graph.drawImage(this.tile[tmp - 10], print_x, print_y, this.parent);
					graph.drawImage(this.tile[tmp], print_x, print_y, this.parent);
				}
			}
		}
		for (int i = 0; i < this.objectsList.size(); i++) {
			Image objectsImage = this.police;
			int objects_direction = ((Enemy) this.objectsList.get(i)).getDirection();
			int print_direction = 3;
			switch (objects_direction) {
			case 2:
				print_direction = 3;
				break;
			case 4:
				print_direction = 1;
				break;
			case 6:
				print_direction = 2;
				break;
			case 8:
				print_direction = 0;
				break;
			}
			int objects_x = ((Enemy) this.objectsList.get(i)).getX() - player_x + player_print_x;
			int objects_y = ((Enemy) this.objectsList.get(i)).getY() - player_y + player_print_y;
			switch (((Enemy) this.objectsList.get(i)).getType()) {
			case 13:
				objectsImage = this.police;
				break;
			case 14:
				objectsImage = this.det1;
				break;
			case 15:
				objectsImage = this.det2;
				break;
			case 16:
				objectsImage = this.det3;
				break;
			}
			switch (objects_direction) {
			case 2:
				graph.drawImage(this.see[0], objects_x - 70, objects_y - 150, this.parent);
				break;
			case 4:
				graph.drawImage(this.see[1], objects_x - 150, objects_y - 70, this.parent);
				break;
			case 6:
				graph.drawImage(this.see[2], objects_x, objects_y - 70, this.parent);
				break;
			case 8:
				graph.drawImage(this.see[3], objects_x - 70, objects_y + 10, this.parent);
				break;
			}
			graph.drawImage(objectsImage, objects_x - 30, objects_y - 30, objects_x + 50, objects_y + 50, 0,
					80 * print_direction, 80, 80 * print_direction + 80, this.parent);
		}
		switch (this.player.getDirection()) {
		case 2:
			direction = 3;
			break;
		case 4:
			direction = 1;
			break;
		case 6:
			direction = 2;
			break;
		case 8:
			direction = 0;
			break;
		}
		if (this.player.isMoving())
			frame = this.player.getCounter() / 10 % 4;
		graph.drawImage(nowRopa, player_print_x - 30, player_print_y - 30, player_print_x + 50, player_print_y + 50,
				80 * frame, 80 * direction, 80 * frame + 80, 80 * direction + 80, this.parent);
	}

	public void setListenr(JFrame mainf) {
		super.setListenr(mainf);
		mainf.addKeyListener(this);
	}

	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case 27:
			this.back.setStatus(2);
			break;
		case 38:
			this.upKeyPressed = true;
			break;
		case 37:
			this.leftKeyPressed = true;
			break;
		case 40:
			this.downKeyPressed = true;
			break;
		case 39:
			this.rightKeyPressed = true;
			break;
		case 65:
			this.player.setCloth(0);
			break;
		case 83:
			this.player.setCloth(1);
			break;
		case 68:
			this.player.setCloth(2);
			break;
		case 70:
			this.player.setCloth(3);
			break;
		}
		if (this.upKeyPressed || this.leftKeyPressed || this.downKeyPressed || this.rightKeyPressed)
			this.player.setMoving(true);
	}

	public void keyReleased(KeyEvent key) {
		switch (key.getKeyCode()) {
		case 27:
			this.back.setStatus(3);
			break;
		case 38:
			this.upKeyPressed = false;
			break;
		case 37:
			this.leftKeyPressed = false;
			break;
		case 40:
			this.downKeyPressed = false;
			break;
		case 39:
			this.rightKeyPressed = false;
			break;
		}
		if (!this.upKeyPressed && !this.leftKeyPressed && !this.downKeyPressed && !this.rightKeyPressed)
			this.player.setMoving(false);
	}

	public void keyTyped(KeyEvent arg0) {
	}
}