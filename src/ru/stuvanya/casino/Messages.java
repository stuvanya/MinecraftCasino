package ru.stuvanya.casino;

import org.bukkit.entity.Player;

public class Messages {

	public static void mashineInGame(Player p) {
		p.sendMessage("Слот-машина занята другим игроком!");
	}

	public static void mashineDisabled(Player p) {
		p.sendMessage("Слот-машина выключена!");
	}

	public static void notEnoughtMoney(Player p, int bet) {
		p.sendMessage("Не хватает денег!");
	}

	public static void alertWin(Player p, double win) {
		p.sendMessage("Вы выиграли " + win + " монет!");
	}

	public static void alertLoose(Player p) {
		p.sendMessage("Вы проиграли!");
	}

	public static void makedBet(Player p, int bet) {
		p.sendMessage("Вы поставили " + bet + " монет в казино!");
	}

}
