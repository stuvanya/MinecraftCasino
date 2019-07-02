package ru.stuvanya.casino;

import org.bukkit.entity.Player;

public class Messages {

	public static void mashineInGame(Player p) {
		p.sendMessage("Слот-машина занята другим игроком!");
	}

	public static void mashineDisabled(Player p) {
		p.sendMessage("Слот-машина выключена!");
	}

}
