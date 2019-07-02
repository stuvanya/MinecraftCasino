package ru.stuvanya.casino;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftCasino  extends JavaPlugin {
	public static Plugin plugin = Bukkit.getPluginManager().getPlugin("MinecraftCasino");

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		Settings.init();
	}

	@Override
	public void onDisable() {

	}
}
