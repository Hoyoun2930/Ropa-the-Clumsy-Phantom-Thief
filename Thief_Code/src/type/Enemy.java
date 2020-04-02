package type;

import java.util.ArrayList;

public class Enemy extends Object {
	public ArrayList<Character> pattern;
	public Enemy(int x, int y, int type, int stepSize, int direction, ArrayList<Character> pattern) {
		super(x, y, type, stepSize, direction);
		this.pattern = pattern;
	}	
}