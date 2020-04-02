package map;

import java.util.ArrayList;

import type.Position;

public class Map {
	private int mapNum;

	private int row;

	private int col;

	private int[][] map;

	private int numOfPolice;

	private int numOfSecuA;

	private int numOfSecuB;

	private int numOfSecuC;

	private ArrayList<Position> positionP;

	private ArrayList<Position> positionA;

	private ArrayList<Position> positionB;

	private ArrayList<Position> positionC;

	private ArrayList<Character>[] patternP = null, patternA = null, patternB = null, patternC = null;

	public Map(int mapNum, int mapRow, int mapCol, int numOfPolice, int numOfSecuA, int numOfSecuB, int numOfSecuC) {
		this.mapNum = mapNum;
		this.row = mapRow;
		this.col = mapCol;
		this.numOfPolice = numOfPolice;
		this.numOfSecuA = numOfSecuA;
		this.numOfSecuB = numOfSecuB;
		this.numOfSecuC = numOfSecuC;
		this.map = new int[this.row][this.col];
		this.positionP = new ArrayList<>();
		this.positionA = new ArrayList<>();
		this.positionB = new ArrayList<>();
		this.positionC = new ArrayList<>();
	}

	public int getMapNum() {
		return this.mapNum;
	}

	public void changeMap(int x, int y, int tile) {
		this.map[y][x] = tile;
	}

	public void setMapNum(int mapNum) {
		this.mapNum = mapNum;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return this.col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getNumOfPolice() {
		return this.numOfPolice;
	}

	public void setNumOfPolice(int numOfPolice) {
		this.numOfPolice = numOfPolice;
	}

	public int getNumOfSecuA() {
		return this.numOfSecuA;
	}

	public void setNumOfSecuA(int numOfSecuA) {
		this.numOfSecuA = numOfSecuA;
	}

	public int getNumOfSecuB() {
		return this.numOfSecuB;
	}

	public void setNumOfSecuB(int numOfSecuB) {
		this.numOfSecuB = numOfSecuB;
	}

	public int getNumOfSecuC() {
		return this.numOfSecuC;
	}

	public void setNumOfSecuC(int numOfSecuC) {
		this.numOfSecuC = numOfSecuC;
	}

	public void setMap(int[][] map) {
		this.map = (int[][]) map.clone();
	}

	public int[][] getMap() {
		return this.map;
	}

	public void addPositionP(Position p) {
		this.positionP.add(p);
	}

	public Position getPositionP(int index) {
		return this.positionP.get(index);
	}

	public void addPositionA(Position p) {
		this.positionA.add(p);
	}

	public Position getPositionA(int index) {
		return this.positionA.get(index);
	}

	public void addPositionB(Position p) {
		this.positionB.add(p);
	}

	public Position getPositionB(int index) {
		return this.positionB.get(index);
	}

	public void addPositionC(Position p) {
		this.positionC.add(p);
	}

	public Position getPositionC(int index) {
		return this.positionC.get(index);
	}

	public char getPatternP(int num, int index) {
		return ((Character) this.patternP[num].get(index)).charValue();
	}

	public void setPatternP(ArrayList[] pattern) {
		this.patternP = (ArrayList<Character>[]) pattern.clone();
	}

	public char getPatternA(int num, int index) {
		return ((Character) this.patternA[num].get(index)).charValue();
	}

	public void setPatternA(ArrayList[] pattern) {
		this.patternA = (ArrayList<Character>[]) pattern.clone();
	}

	public char getPatternB(int num, int index) {
		return ((Character) this.patternB[num].get(index)).charValue();
	}

	public void setPatternB(ArrayList[] pattern) {
		this.patternB = (ArrayList<Character>[]) pattern.clone();
	}

	public char getPatternC(int num, int index) {
		return ((Character) this.patternC[num].get(index)).charValue();
	}

	public void setPatternC(ArrayList[] pattern) {
		this.patternC = (ArrayList<Character>[]) pattern.clone();
	}

	public ArrayList<Character> getPatternP(int num) {
		return this.patternP[num];
	}

	public ArrayList<Character> getPatternA(int num) {
		return this.patternA[num];
	}

	public ArrayList<Character> getPatternB(int num) {
		return this.patternB[num];
	}

	public ArrayList<Character> getPatternC(int num) {
		return this.patternC[num];
	}
}
