package type;

public class Thief extends Object {
	private int cloth = 0;

	private int coolTime = 0;

	static final int COOL = 70;

	public static final int normal = 0;

	public static final int A = 1;

	public static final int B = 2;

	public static final int C = 3;

	public Thief() {
	}

	public Thief(int x, int y, int type, int stepSize, int direction) {
		super(x, y, type, stepSize, direction);
	}

	public Thief(int x, int y, int type) {
		super(x, y, type);
	}

	public int getCloth() {
		return this.cloth;
	}

	public void setCloth(int cloth) {
		if (this.coolTime == 0) {
			this.cloth = cloth;
			this.coolTime = 70;
		}
	}

	public void run() {
		if (this.coolTime != 0)
			this.coolTime--;
	}
}