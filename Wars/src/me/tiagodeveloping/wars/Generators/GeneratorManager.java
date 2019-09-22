package me.tiagodeveloping.wars.Generators;

import org.bukkit.Location;

import me.tiagodeveloping.wars.Main;

public class GeneratorManager {

	GeneratorTypeManager gTypeManager = new GeneratorTypeManager();
	
	public void registerGenerator(GeneratorType type, Location generatorLocation, String generatorName) {
		int generator = gTypeManager.selectGeneratorType(type);
		if (generator == 3) {
			throw new IllegalArgumentException();
		} else if (generator == 0) {
			new IronGenerator().declareGenerator(generatorLocation);
			Main.mainClass.getConfig().set("generators." + generatorName + ".x", generatorLocation.getBlockX());
			Main.mainClass.getConfig().set("generators." + generatorName + ".y", generatorLocation.getBlockY());
			Main.mainClass.getConfig().set("generators." + generatorName + ".z", generatorLocation.getBlockZ());
			Main.mainClass.getConfig().set("generators." + generatorName + ".type", gTypeManager.selectGeneratorType(type));
		}
	}
	
}
