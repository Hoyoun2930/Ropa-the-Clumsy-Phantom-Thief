package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import type.Position;

public class MapManager {
	public Map getMap(int mapNum) {
		Map mapInstance = null;
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setGroupingUsed(false);
		nf.setMinimumIntegerDigits(4);
		File source = new File("map\\map" + nf.format(mapNum) + ".txt");
		FileReader fr = null;
		ArrayList<Position> positionP = new ArrayList<Position>();
		ArrayList<Position> positionA = new ArrayList<Position>();
		ArrayList<Position> positionB = new ArrayList<Position>();
		ArrayList<Position> positionC = new ArrayList<Position>();
		try {
			fr = new FileReader(source);
			BufferedReader br = new BufferedReader(fr);
			int mapNumber = Integer.parseInt(br.readLine());
			int row = Integer.parseInt(br.readLine());
			int col = Integer.parseInt(br.readLine());
			int[][] map = new int[row][col];
			int i;
			for (i = 0; i < row; i++) {
				String buf = br.readLine();
				for (int j = 0; j < col; j++)
					map[i][j] = Integer.parseInt(buf.split(" ")[j]);
			}
			int numOfPolice = Integer.parseInt(br.readLine());
			ArrayList[] patternP = new ArrayList[numOfPolice];
			for (i = 0; i < numOfPolice; i++) {
				patternP[i] = new ArrayList();
				String buf = br.readLine();
				positionP.add(new Position(Integer.parseInt(buf.split(" ")[0]) * 80 + 0,
						Integer.parseInt(buf.split(" ")[1]) * 80 + 0));
				for (int j = 2; j < (buf.split(" ")).length; j++)
					patternP[i].add(Character.valueOf(buf.split(" ")[j].charAt(0)));
			}
			int numOfSecuA = Integer.parseInt(br.readLine());
			ArrayList[] patternA = new ArrayList[numOfSecuA];
			for (i = 0; i < numOfSecuA; i++) {
				patternA[i] = new ArrayList();
				String buf = br.readLine();
				positionA.add(new Position(Integer.parseInt(buf.split(" ")[0]) * 80 + 0,
						Integer.parseInt(buf.split(" ")[1]) * 80 + 0));
				for (int j = 2; j < (buf.split(" ")).length; j++)
					patternA[i].add(Character.valueOf(buf.split(" ")[j].charAt(0)));
			}
			int numOfSecuB = Integer.parseInt(br.readLine());
			ArrayList[] patternB = new ArrayList[numOfSecuB];
			for (i = 0; i < numOfSecuB; i++) {
				patternB[i] = new ArrayList();
				String buf = br.readLine();
				positionB.add(new Position(Integer.parseInt(buf.split(" ")[0]) * 80 + 0,
						Integer.parseInt(buf.split(" ")[1]) * 80 + 0));
				for (int j = 2; j < (buf.split(" ")).length; j++)
					patternB[i].add(Character.valueOf(buf.split(" ")[j].charAt(0)));
			}
			int numOfSecuC = Integer.parseInt(br.readLine());
			ArrayList[] patternC = new ArrayList[numOfSecuC];
			for (i = 0; i < numOfSecuC; i++) {
				patternC[i] = new ArrayList();
				String buf = br.readLine();
				positionC.add(new Position(Integer.parseInt(buf.split(" ")[0]) * 80 + 0,
						Integer.parseInt(buf.split(" ")[1]) * 80 + 0));
				for (int j = 2; j < (buf.split(" ")).length; j++)
					patternC[i].add(Character.valueOf(buf.split(" ")[j].charAt(0)));
			}
			br.close();
			fr.close();
			mapInstance = new Map(mapNumber, row, col, numOfPolice, numOfSecuA, numOfSecuB, numOfSecuC);
			mapInstance.setMap(map);
			for (Position tmp : positionP)
				mapInstance.addPositionP(tmp);
			for (Position tmp : positionA)
				mapInstance.addPositionA(tmp);
			for (Position tmp : positionB)
				mapInstance.addPositionB(tmp);
			for (Position tmp : positionC)
				mapInstance.addPositionC(tmp);
			mapInstance.setPatternP((ArrayList<Character>[]) patternP);
			mapInstance.setPatternA((ArrayList<Character>[]) patternA);
			mapInstance.setPatternB((ArrayList<Character>[]) patternB);
			mapInstance.setPatternC((ArrayList<Character>[]) patternC);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapInstance;
	}
}
