package ru.stuvanya.casino;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ru.stuvanya.casino.presenter.SlotMashine;

public class Settings {
	public static FileConfiguration mainConfig = MinecraftCasino.plugin.getConfig();
	public static FileConfiguration slotsConfig;
	public static ArrayList<SlotMashine> slotMashines;

	public static void init() {
		slotsConfig = loadFile("", "slots.yml");
	}

	public static FileConfiguration loadFile(String path, String fileName) {
		String separator = "";
		if (path != "")
			separator = File.separator;

		File file = new File(MinecraftCasino.plugin.getDataFolder() + separator + path, fileName);

		if (!file.exists()) {
			try {
				MinecraftCasino.plugin.saveResource(path + separator + fileName, false);
			} catch (Exception e) {
				e.printStackTrace();
				Bukkit.shutdown();
				return null;
			}
		}
		return YamlConfiguration.loadConfiguration(file);
	}

}
