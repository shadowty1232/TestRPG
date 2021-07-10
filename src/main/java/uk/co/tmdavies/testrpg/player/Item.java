package uk.co.tmdavies.testrpg.player;

public enum Item {

	STRENGTH_POTION("Strength Potion", new String[]{"This potion will buff", "your strength by 10", "for 2 turns."}, "Strength", 10, 2);

	String name;
	String[] description;
	String effect;
	int buff;
	int duration;

	Item(String name, String[] description, String effect, int buff, int duration) {

		this.name = name;
		this.description = description;
		this.effect = effect;
		this.buff = buff;
		this.duration = duration;

	}

	public String getName() {

		return this.name;

	}

	public String getEffect() {

		return this.effect;

	}

	public int getBuff() {

		return this.buff;

	}

	public int getDuration() {

		return this.duration;

	}
}
