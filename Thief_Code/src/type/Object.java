package type;

public class Object {

	public Object() {
		x = 0;
		y = 0;
		type = 10;
		stepSize = 0;
		direction = 5;
		counter = 0;
		isMoving = false;
	}

	public Object(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		stepSize = 0;
		direction = 5;
		counter = 0;
		isMoving = false;
	}

	public Object(int x, int y, int type, int stepSize, int direction) {
		this.x = x;
		this.y = y;
		this.stepSize = stepSize;
		this.direction = direction;
		counter = 0;
		this.type = type;
		isMoving = false;
	}

	public String toString() {
		switch (type) {
		case 10: // '\n'
			return "미정-("+this.x+", "+this.y+"), 방향: "+this.direction+", 카운터: "+this.counter+", isMoving="+this.isMoving;

		case 11: // '\013'
			return "괴도-("+this.x+", "+this.y+"), 방향: "+this.direction+", 카운터: "+this.counter+", isMoving="+this.isMoving;

		case 12: // '\f'
			return "보석-("+this.x+", "+this.y+"), 방향: "+this.direction+", 카운터: "+this.counter+", isMoving="+this.isMoving;

		case 13: // '\r'
			return "경찰-("+this.x+", "+this.y+"), 방향: "+this.direction+", 카운터: "+this.counter+", isMoving="+this.isMoving;

		case 14: // '\016'
			return "탐정-("+this.x+", "+this.y+"), 방향: "+this.direction+", 카운터: "+this.counter+", isMoving="+this.isMoving;
		}
		return "오류";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStepSize() {
		return stepSize;
	}

	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
		if (!isMoving)
			counter = 0;
		if (!isMoving && type == 11)
			counter = 7;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void moveUp() {
		setDirection(2);
		setY(getY() - stepSize);
	}

	public void moveDown() {
		setDirection(8);
		setY(getY() + stepSize);
	}

	public void moveLeft() {
		setDirection(4);
		setX(getX() - stepSize);
	}

	public void moveRight() {
		setDirection(6);
		setX(getX() + stepSize);
	}

	public void increCounter() {
		setCounter(getCounter() + 1);
	}

	public void decreCounter() {
		setCounter(getCounter() - 1);
	}

	public static final int upLeft = 1;
	public static final int up = 2;
	public static final int upRight = 3;
	public static final int left = 4;
	public static final int center = 5;
	public static final int right = 6;
	public static final int downLeft = 7;
	public static final int down = 8;
	public static final int downRight = 9;
	public static final int undefined = 10;
	public static final int thief = 11;
	public static final int gem = 12;
	public static final int police = 13;
	public static final int detective1 = 14;
	public static final int detective2 = 15;
	public static final int detective3 = 16;
	private int stepSize;
	private int x;
	private int y;
	private int direction;
	private boolean isMoving;
	private int counter;
	private int type;
}