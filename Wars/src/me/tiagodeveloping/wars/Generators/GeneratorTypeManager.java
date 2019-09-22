package me.tiagodeveloping.wars.Generators;

public class GeneratorTypeManager {

	/**
	 * 
	 * iron generator = 0
	 * gold generator = 1
	 * diamond generator = 2
	 * 
	 */
	
	public int selectGeneratorType(GeneratorType type) {
		int typeId = 3;
		try {
			switch(type) {
			case DIAMOND:
				typeId = 2;
				break;
			case GOLD:
				typeId = 1;
				break;
			case IRON:
				typeId = 0;
			break;
			default:
				break;
			}
		} catch (NullPointerException e) {
			return 3;
		}
		return typeId;
	}
	
}
