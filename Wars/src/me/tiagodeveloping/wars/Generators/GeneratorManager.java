package me.tiagodeveloping.wars.Generators;

import org.bukkit.Location;

import me.tiagodeveloping.wars.ConfigManager;

public class GeneratorManager {

	GeneratorTypeManager gTypeManager = new GeneratorTypeManager();
	
	public void registerGenerator(GeneratorType type, Location generatorLocation, String generatorName) {
		int generator = gTypeManager.selectGeneratorType(type);
		if (generator == 3) {
			throw new IllegalArgumentException();
		} else if (generator == 0) {
			new IronGenerator().declareGenerator(generatorLocation);
			ConfigManager.generatorConfig.set("generators." + generatorName + ".x", generatorLocation.getBlockX());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".y", generatorLocation.getBlockY());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".z", generatorLocation.getBlockZ());
			ConfigManager.generatorConfig.set("generators." + generatorName + ".type", gTypeManager.selectGeneratorType(type));
		}
	}
	
}
