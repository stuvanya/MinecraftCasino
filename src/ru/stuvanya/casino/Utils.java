package ru.stuvanya.casino;

import org.bukkit.entity.Player;
import ru.elliot789.eapi.economy.EconomyAPI;

public class Utils {

	public static void takeWin(Player p, double win) {
		EconomyAPI.addCoins(p.getName(), (int)win);
		Messages.alertWin(p, win);
	}

	public static void loose(Player p) {
		Messages.alertLoose(p);
	}

	public static boolean canGetNewSpin(Player p, int bet) {
		return EconomyAPI.getCoins(p) >= bet;
	}

	public static void mashineInGame(Player p) {
		Messages.mashineInGame(p);
	}

	public static void mashineDisabled(Player p) {
		Messages.mashineDisabled(p);
	}

	public static void notEnoughtMoney(Player p, int bet) {
		Messages.notEnoughtMoney(p, bet);
	}

	public static void makedBet(Player p, int bet) {
		EconomyAPI.takeCoins(p.getName(), bet);
		Messages.makedBet(p, bet);
	}

}
