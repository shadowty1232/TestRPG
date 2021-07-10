package uk.co.tmdavies.testrpg.player;

import uk.co.tmdavies.testrpg.Main;

import java.util.*;

public class Player {

	// Player Name
	private String name;

	// Player Inventory
	private Inventory inventory;

	// Player Stats
	private int maxHealth;
	private int health;

	private int strength;
	private int defence;
	private int accuracy;

	private int critChance;
	private int critDamage;

	private int money;

	private int level;
	private int experience;

	private List<Equipment> equips;

	private Random RANDOM;

	public Player(String name, int health, int strength, int defence, int accuracy, int critChance, int critDamage) {

		this.name = name;

		this.inventory = new Inventory(this);

		this.health = health <= 19 ? 20 : health;
		this.maxHealth = this.health;

		this.strength = strength == 0 ? 10 : strength;
		this.defence = defence == 0 ? 1 : defence;
		this.accuracy = accuracy <= 59 ? 60 : accuracy;

		this.critChance = critChance == 0 ? 10 : critChance;
		this.critDamage = critDamage <= 49 ? 50 : critDamage;

		this.money = 0;

		this.level = 1;
		this.experience = 0;

		this.equips = new ArrayList<>();

		RANDOM = new Random();

	}

	public String getName() {

		return this.name;

	}

	public Inventory getInventory() {

		return this.inventory;

	}

	public int getHealth() {

		return this.health;

	}

	public int getMaxHealth() {

		return this.maxHealth;

	}

	public void restoreHealth(int amount) {

		this.health += amount;

		if (this.health > this.maxHealth) {

			this.health = this.maxHealth;

		}

	}

	public int getStrength() {

		return this.strength;

	}

	public int getDefence() {

		return this.defence;

	}

	public int getAccuracy() {

		return this.accuracy;

	}

	public int getMoney() {

		return this.money;

	}

	public void takeDamage(int amount) {

		this.health -= amount;

	}

	public void addExperience(int amount) {

		this.experience += amount;

		checkLevel();

	}

	public void addLevel(int amount) {

		this.level += amount;

	}

	public void checkLevel() {

		if (this.experience == this.level * 100) {

			this.addLevel(1);
			this.experience = 0;

			Main.scrollScreen(1000, "You have leveled up, Congratulations", "You are now Level " + this.level);

		}

	}

	public boolean isDead() {

		return this.health <= 0;

	}

	public int calcHit() {

		int ran = RANDOM.nextInt(100);

		if (ran <= this.accuracy) {

			int damage = this.damageCalc();

			if (this.calcCritHit()) {

				return this.damageCritCalc(damage);

			}

			return damage;

		}

		return -1;

	}

	public int damageCalc() {

		return RANDOM.nextInt(this.strength) + 1;

	}

	public boolean calcCritHit() {

		int ran = RANDOM.nextInt(100) + 1;

		return ran <= this.critChance;

	}

	public int damageCritCalc(int amount) {

		amount = (int) (amount * (1 + ((double) this.critDamage / 100)));

		return amount;

	}

	public void addEquip(Equipment equip) {

		if (!this.equips.contains(equip)) {

			this.equips.add(equip);

		} else {

			throw new IllegalArgumentException("Player already has that equipment.");

		}

	}

	public void removeEquip(Equipment equip) {

		if (this.equips.contains(equip)) {

			this.equips.remove(equip);

		} else {

			throw new IllegalArgumentException("Player does not have that equipment.");

		}

	}

	public void recalcStats() {

		if (this.equips.isEmpty()) {

			this.health = 10;

			this.strength = 1;
			this.defence = 1;
			this.accuracy = 60;

			this.critChance = 25;
			this.critDamage = 50;

			return;

		}

		for (Equipment equip : this.equips) {

			switch (equip.getStatBuff().toLowerCase()) {

				case "health" -> this.health = 10 + equip.getBuff();

				case "strength" -> this.strength = 1 + equip.getBuff();

				case "defence" -> this.defence = 1 + equip.getBuff();

				case "accuracy" -> this.accuracy = 60 + equip.getBuff();

				case "critchance" -> this.critChance = 25 + equip.getBuff();

				case "critdamage" -> this.critDamage = 50 + equip.getBuff();

				default -> throw new IllegalArgumentException("Equip does not have a valid stat buff.");

			}

			return;

		}

	}

	public LinkedHashMap<String, Object> statValues() {

		LinkedHashMap<String, Object> statMap = new LinkedHashMap<>();

		statMap.put("Health", this.health);
		statMap.put("Strength", this.strength);
		statMap.put("Defence", this.defence);
		statMap.put("Accuracy", this.accuracy);
		statMap.put("Critical Chance", this.critChance);
		statMap.put("Critical Damage", this.critDamage + "%");

		return statMap;

	}

	public enum Equipment {

		AXE("Axe", "Strength", 10),
		SCOPE("Scope", "Accuracy", 10);

		private String name;
		private String statBuff;
		private int buff;

		Equipment(String name, String statBuff, int buff) {

			this.name = name;
			this.statBuff = statBuff;
			this.buff = buff;

		}

		public String getName() {

			return this.name;

		}

		public String getStatBuff() {

			return this.statBuff;

		}

		public int getBuff() {

			return this.buff;

		}

	}

}
