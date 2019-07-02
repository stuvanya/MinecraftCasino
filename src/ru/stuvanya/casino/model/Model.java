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
				{0, 0, 0,   10},//cherry0
				{0, 0, 0,   10},//lemon1
				{0, 0, 0,   10},//Peach2
				{0, 0, 0,   20},//plum3
				{0, 0, 0,   20},//Strawberry4
				{0, 0, 0,   20},//Raspberry5
				{0, 0, 0,   40},//Bell6
				{0, 0, 0,   40},//watermelon7
				{0, 0, 0,   40},//Grape8
				{0, 0, 0,   80},//Seven9
				{0, 0, 0,   80},//Star10
				{0, 0, 0,  400},//Sun11
				{0, 0, 0,  800},//Coin12
				{0, 0, 0, 1600},//Fenix13
		};

	@SuppressWarnings("serial")
	private static Map<Integer, Material> items = new HashMap<Integer, Material>() {{
		put(0, Material.DIRT);
		put(1, Material.SAND);
		put(2, Material.STONE);
		put(3, Material.COBBLESTONE);
		put(4, Material.STONE);
		put(5, Material.STONE);
		put(6, Material.STONE);
		put(7, Material.STONE);
		put(8, Material.STONE);
		put(9, Material.STONE);
		put(10, Material.STONE);
		put(11, Material.STONE);
		put(12, Material.STONE);
		put(13, Material.STONE);
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
	}

	private void calculateWin(int bet) {
		for (int row = 0; row < curMatrix.length; row ++) {
			int symbol = curMatrix[row][0];
			for (int row2 = 0; row2 < curMatrix.length; row2 ++) {
				if (curMatrix[row2][1] == symbol && curMatrix[row2][2] == symbol) {
					win += (double)payTable[symbol][3] * ((double)bet / 10.0);
				}
			}
		}
	}

}
