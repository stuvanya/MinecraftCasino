package ru.stuvanya.casino.presenter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ru.stuvanya.casino.MinecraftCasino;
import ru.stuvanya.casino.Utils;
import ru.stuvanya.casino.model.GameState;
import ru.stuvanya.casino.model.Model;

public class SlotMashine {
	private GameState _state;
	private Location _buttonPos;
	private Player _currentPlayer;
	private Location _slotTopLocation;
	private Location _slotBottomLocation;
	private int _bet;
	private Model _gameModel;

	public SlotMashine (Location buttonPos, Location slotTopLocation, Location slotBottomLocation, int bet) {
		this._buttonPos = buttonPos;
		this._slotTopLocation = slotTopLocation;
		this._slotBottomLocation = slotBottomLocation;
		this._state = GameState.WAITING;
		this._gameModel = new Model();
		this._bet = bet;
	}

	public GameState getCurrentState () {
		return this._state;
	}

	public void setCurrentState (GameState state) {
		this._state = state;
	}

	public void newSpin (Player p) {
		if (_state == GameState.IN_GAME) {
			Utils.mashineInGame(p);
			return;
		} else if (_state == GameState.DISABLED) {
			Utils.mashineDisabled(p);
			return;
		}
		if (!Utils.canGetNewSpin(p, _bet)) {
			Utils.notEnoughtMoney(p, _bet);
		}
		Utils.makedBet(p, _bet);

		_currentPlayer = p;
		setCurrentState(GameState.IN_GAME);

		double win = _gameModel.getNewSpin(_bet);
		Material[][] matrix = _gameModel.getItemMatrix();

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

	public Player getCurrentPlayer () {
		return _currentPlayer;
	}

	public Location getButtonLocation() {
		return _buttonPos;
	}

	private long spin() {
		return 20L;
	}

	private void takeWin(double win) {
		Utils.takeWin(_currentPlayer, win);
		finishGame();
	}

	private void loose() {
		Utils.loose(_currentPlayer);
		finishGame();
	}

	private void finishGame() {
		_currentPlayer = null;
		setCurrentState(GameState.WAITING);
	}

	private void setMatrix(Material[][] matrix) {
		Location startLoc = _slotBottomLocation;
		Location addLoc;

		if (_slotTopLocation.getBlockX() == _slotBottomLocation.getBlockX())
			addLoc = new Location (null, 0, 1, 1);
		else
			addLoc = new Location (null, 1, 1, 0);

		for (int row = 0; row < matrix.length; row++)
			for (int col = 0; col < matrix[0].length; col++)
				_slotBottomLocation.getWorld().getBlockAt(startLoc.add(addLoc)).setType(matrix[row][col]);
	}


}
