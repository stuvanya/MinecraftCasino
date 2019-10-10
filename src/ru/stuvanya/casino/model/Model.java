package ru.stuvanya.casino.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;

public class Model {
	final static Random random = new Random();

	private static int _rows = 3;
	private static int _cols = 3;

	private static int[][] _slotLents = {
			{2, 2, 2, 0, 2, 0, 2, 2, 7, 1, 1, 1, 4, 4, 6, 6, 3, 3, 3, 1, 8, 5, 5, 0, 0, 0, 0},
			{2, 2, 6, 3, 3, 3, 8, 4, 1, 7, 1, 1, 1, 0, 6, 0, 0, 0, 5, 5, 0, 2, 2, 0, 4, 2, 0, 5, 2, 4, 0},
			{2, 0, 0, 0, 0, 4, 4, 7, 6, 3, 3, 3, 5, 5, 1, 1, 1, 1, 8, 1, 0}
	};

	private static int[][] _payTable =
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
	private static Map<Integer, Material> _items = new HashMap<Integer, Material>() {{
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

	private int[][] _curMatrix;
	private Material[][] _curItemMatrix;
	private double _win;

	public Model() {
		_win = 0;
		_curMatrix = new int[_rows][_cols];
		_curItemMatrix = new Material[_rows][_cols];
	}

	public double getNewSpin(int bet) {
		System.out.println("Get new spin, bet: " + bet);
		_win = 0;
		generateRandomMatrix();
		calculateWin(bet);
		return _win;
	}

	public int[][] getMatrix () {
		return _curMatrix;
	}

	public Material[][] getItemMatrix() {
		return _curItemMatrix;
	}

	public Material getMaterial(int symbol) {
		return _items.get(symbol);
	}

	private void generateRandomMatrix () {
		int[] indexes = {random.nextInt(_slotLents[0].length), random.nextInt(_slotLents[1].length), random.nextInt(_slotLents[2].length)};

		for (int col = 0; col < _curMatrix[0].length; col++) {
			for (int row = 0; row < _curMatrix.length; row ++) {
				int index = indexes[col] + row - 1;

				while (index < 0)
					index += _slotLents[col].length;
				while (index >= _slotLents[col].length)
					index -= _slotLents[col].length;

				_curMatrix[row][col] = _slotLents[col][index];
				_curItemMatrix[row][col] = _items.get(_curMatrix[row][col]);
			}
		}
		System.out.println("GeneratedMatrix: " + _curMatrix);
	}

	private void calculateWin(int bet) {
		for (int row = 0; row < _curMatrix.length; row ++) {
			int symbol = _curMatrix[row][0];
			for (int row2 = 0; row2 < _curMatrix.length; row2 ++) {
				//KOSTILL, переделать под матрицы разного размера
				if (_curMatrix[row2][1] == symbol && _curMatrix[row2][2] == symbol) {
					_win += (double)_payTable[symbol][3] * ((double)bet / 10.0);
				}
			}
		}
		System.out.println("Calculated win: " + _win);
	}

}
