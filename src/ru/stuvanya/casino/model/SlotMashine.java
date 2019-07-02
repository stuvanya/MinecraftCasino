package ru.stuvanya.casino.model;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ru.stuvanya.casino.MinecraftCasino;

public class SlotMashine {
	private GameState state;
	public Location buttonPos;
	public Player currentPlayer;
	public Location slotTopLocation;
	public Location slotBottomLocation;
	private Model gameModel;
	
	public SlotMashine (Location _buttonPos, Location _slotTopLocation, Location _slotButtonLocation) {
		this.buttonPos = _buttonPos;
		this.slotTopLocation = _slotTopLocation;
		this.slotBottomLocation = _slotButtonLocation;
		this.state = GameState.WAITING;
		this.gameModel = new Model();
	}
	
	public GameState getCurrentState () {
		return this.state;
	}
	
	public void getNewSpin (Player p) {
		if (state != GameState.WAITING)
			return;
		currentPlayer = p;
		state = GameState.IN_GAME;
		int win = gameModel.getNewSpin();
		Material[][] matrix = gameModel.getItemMatrix();
		long dur = spin();
		new BukkitRunnable()
        {
            @Override
            public void run() {
                setMatrix(matrix);
                if (win > 0)
                	takeWin();
                else
                	loose();
            }
        }.runTaskLater(MinecraftCasino.plugin, dur);

	}
	
	private long spin() {
		return 20L;
	}
	
	private void takeWin() {
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
		for (int row = 0; row < matrix.length; row++)
		{
			for (int col = 0; col < matrix[0].length; col++) {
				
			}
		}
	}
	
	
}
