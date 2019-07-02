package ru.stuvanya.casino.presenter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ru.stuvanya.casino.MinecraftCasino;
import ru.stuvanya.casino.model.GameState;
import ru.stuvanya.casino.model.Model;

public class SlotMashine {
	private GameState state;
	public Location buttonPos;
	public Player currentPlayer;
	public Location slotTopLocation;
	public Location slotBottomLocation;
	public int bet;
	private Model gameModel;

	public SlotMashine (Location _buttonPos, Location _slotTopLocation, Location _slotBottomLocation, int _bet) {
		this.buttonPos = _buttonPos;
		this.slotTopLocation = _slotTopLocation;
		this.slotBottomLocation = _slotBottomLocation;
		this.state = GameState.WAITING;
		this.gameModel = new Model();
		this.bet = _bet;
	}

	public GameState getCurrentState () {
		return this.state;
	}

	public void newSpin (Player p) {
		if (state != GameState.WAITING)
			return;
		currentPlayer = p;
		state = GameState.IN_GAME;
		double win = gameModel.getNewSpin(this.bet);
		Material[][] matrix = gameModel.getItemMatrix();
		long dur = spin();
		new BukkitRunnable()
		{
			@Override
			public void run() {
				setMatrix(matrix);
				if (win > 0)
					takeWin(win);
				else
					loose();
			}
		}.runTaskLater(MinecraftCasino.plugin, dur);

	}

	private long spin() {
		return 20L;
	}

	private void takeWin(double win) {
		finishGame();
	}

	private void loose() {
		finishGame();
	}

	private void finishGame() {
		currentPlayer = null;
		state = GameState.WAITING;
	}

	private void setMatrix(Material[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				//пихаем блоки по координатам
			}
		}
	}


}
