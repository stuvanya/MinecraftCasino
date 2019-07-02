package ru.stuvanya.casino.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;

public class Model {
	final static Random random = new Random();

	private static int rows = 3;
	private static int cols = 3;

	private static int[][] slotLents = {
			{2, 2, 2, 0, 2, 0, 2, 2, 7, 1, 1, 1, 4, 4, 6, 6, 3, 3, 3, 1, 8, 5, 5, 0, 0, 0, 0},
			{2, 2, 6, 3, 3, 3, 8, 4, 1, 7, 1, 1, 1, 0, 6, 0, 0, 0, 5, 5, 0, 2, 2, 0, 4, 2, 0, 5, 2, 4, 0},
			{2, 0, 0, 0, 0, 4, 4, 7, 6, 3, 3, 3, 5, 5, 1, 1, 1, 1, 8, 1, 0}
	};

	private static int[][] payTable =
		{
			{0, 0, 0,  25}, //lemon0
	        {0, 0, 0,  25}, //cherry1
	        {0, 0, 0,  50}, //plum3
	        {0, 0, 0,  50}, //orange2
	        {0, 0, 0, 100}, //Bell4
	        {0, 0, 0, 150}, //grape
	        {0, 0, 0, 200}, //watermelon6
	        {0, 0, 0, 250}, //Seven7
	        {0, 0, 0, 375}, //star8
		};

	@SuppressWarnings("serial")
	private static Map<Integer, Material> items = new HashMap<Integer, Material>() {{
		put(0, Material.DIRT);
		put(1, Material.SAND);
		put(2, Material.WOOD);
		put(3, Material.STONE);
		put(4, Material.COBBLESTONE);
		put(5, Material.IRON_BLOCK);
		put(6, Material.GOLD_BLOCK);
		put(7, Material.DIAMOND_BLOCK);
		put(8, Material.EMERALD_BLOCK);
	}};

	private int[][] curMatrix;
	private Material[][] curItemMatrix;
	private double win;

	public Model() {
		this.win = 0;
		this.curMatrix = new int[rows][cols];
		this.curItemMatrix = new Material[rows][cols];
	}

	public double getNewSpin(int bet) {
		System.out.println("Get new spin, bet: " + bet);
		win = 0;
		generateRandomMatrix();
		calculateWin(bet);
		return win;
	}

	public int[][] getMatrix () {
		return curMatrix;
	}

	public Material[][] getItemMatrix() {
		return curItemMatrix;
	}

	public Material getMaterial(int symbol) {
		return items.get(symbol);
	}

	private void generateRandomMatrix () {
		int[] indexes = {random.nextInt(slotLents[0].length), random.nextInt(slotLents[1].length), random.nextInt(slotLents[2].length)};

		for (int col = 0; col < curMatrix[0].length; col++) {
			for (int row = 0; row < curMatrix.length; row ++) {
				int index = indexes[col] + row - 1;

				while (index < 0)
					index += slotLents[col].length;
				while (index >= slotLents[col].length)
					index -= slotLents[col].length;

				curMatrix[row][col] = slotLents[col][index];
				curItemMatrix[row][col] = items.get(curMatrix[row][col]);
			}
		}
		System.out.println("GeneratedMatrix: " + curMatrix);
	}

	private void calculateWin(int bet) {
		for (int row = 0; row < curMatrix.length; row ++) {
			int symbol = curMatrix[row][0];
			for (int row2 = 0; row2 < curMatrix.length; row2 ++) {
				//KOSTILL, переделать под матрицы разного размера
				if (curMatrix[row2][1] == symbol && curMatrix[row2][2] == symbol) {
					win += (double)payTable[symbol][3] * ((double)bet / 10.0);
				}
			}
		}
		System.out.println("Calculated win: " + win);
	}

}
