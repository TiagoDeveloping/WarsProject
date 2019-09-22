package me.tiagodeveloping.wars.Generators;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.block.Sign;

import me.tiagodeveloping.wars.ConfigManager;
import me.tiagodeveloping.wars.Main;

public class GeneratorManager {

	GeneratorTypeManager gTypeManager = new GeneratorTypeManager();

	Main Main;
	
	public void enableRegisteredGenerators() {
		
	}
	
	public void registerGenerator(GeneratorType type, Location generatorLocation, String generatorName) {
		int generator = gTypeManager.selectGeneratorType(type);
		if (generator == 3) {
			throw new IllegalArgumentException();
		} else if (generator == 0) {
			IronGenerator.declareGenerator(generatorLocation);
			ConfigManager.generatorConfig.set("generators." + generatorName + ".scheduleId", IronGenerator.scheduler);
			ConfigManager.generatorConfig.set("generators." + generatorName + ".x", generatorLocation.getBlockX());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".y", generatorLocation.getBlockY());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".z", generatorLocation.getBlockZ());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".type", gTypeManager.selectGeneratorType(type));
			try {
				ConfigManager.generatorConfig.save(ConfigManager.generatorConfigFile);
			} catch (IOException e) {
				System.err.println("[Wars] was not able to save configuration file!");
				e.printStackTrace();
			}
		} else if (generator == 1) {
			GoldGenerator.declareGenerator(generatorLocation);
			ConfigManager.generatorConfig.set("generators." + generatorName + ".x", generatorLocation.getBlockX());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".y", generatorLocation.getBlockY());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".z", generatorLocation.getBlockZ());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".type", gTypeManager.selectGeneratorType(type));
			try {
				ConfigManager.generatorConfig.save(ConfigManager.generatorConfigFile);
			} catch (IOException e) {
				System.err.println("[Wars] was not able to save configuration file!");
				e.printStackTrace();
			}
		} else if (generator == 2) {
			DiamondGenerator.declareGenerator(generatorLocation);
			ConfigManager.generatorConfig.set("generators." + generatorName + ".x", generatorLocation.getBlockX());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".y", generatorLocation.getBlockY());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".z", generatorLocation.getBlockZ());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".type", gTypeManager.selectGeneratorType(type));
			try {
				ConfigManager.generatorConfig.save(ConfigManager.generatorConfigFile);
			} catch (IOException e) {
				System.err.println("[Wars] was not able to save configuration file!");
				e.printStackTrace();
			}
		}
	}
	
	public static int getGeneratorLevel(Sign sign) {
		int lvl = 1;
		String line = sign.getLine(2);
		if (line.contains("1")) {
			lvl = 1;
		} else if (line.contains("2")) {
			lvl = 2;
		} else if (line.contains("3")) {
			lvl = 3;
		} else {
			throw new IllegalArgumentException();
		}
		return lvl;
	}
	
}
