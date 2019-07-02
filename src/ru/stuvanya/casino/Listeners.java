package ru.stuvanya.casino;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import ru.stuvanya.casino.presenter.SlotMashine;

public class Listeners implements Listener {

	@EventHandler
	public void onButtonClick(PlayerInteractEvent e) {
		Location loc = e.getClickedBlock().getLocation();
		for (SlotMashine m : Settings.slotMashines) {
			if (m.getButtonLocation().distance(loc) < 1)
				m.newSpin(e.getPlayer());
		}
	}
}
