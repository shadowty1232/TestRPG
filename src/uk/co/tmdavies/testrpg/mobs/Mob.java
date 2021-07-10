package uk.co.tmdavies.testrpg.mobs;

import uk.co.tmdavies.testrpg.player.Player;

import java.util.Random;

public class Mob {

	private MobEntity mobEntity;
	private Random RANDOM = new Random();

	public Mob() {

		initMob();

	}

	public void initMob() {

		int ran = RANDOM.nextInt(MobEntity.values().length);

		mobEntity = MobEntity.values()[ran];

	}

	public MobEntity getMobEntity() {

		return this.mobEntity;

	}

	public enum MobEntity {

		JOHN("John", "That tutorial guy!", 20, 5, 5, 80, 10),
		JOE("Joe", "That one guy.", 20, 10, 5, 80, 20);

		private String name;
		private String subName;

		private int health;
		private int maxHealth;
		private int strength;
		private int defence;
		private int accuracy;

		private int experience;

		private Random RANDOM = new Random();

		MobEntity(String name, String subName, int health, int strength, int defence, int accuracy, int experience) {

			this.name = name;
			this.subName = subName;

			this.health = health;
			this.maxHealth = health;
			this.strength = strength;
			this.defence = defence;
			this.accuracy = accuracy;

			this.experience = experience;

		}

		public String getName() {

			return this.name;

		}

		public String getSubName() {

			return this.subName;

		}

		public int getHealth() {

			return this.health;

		}

		public int getMaxHealth() {

			return this.maxHealth;

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

		public int getExperience() {

			return this.experience;

		}

		public int calcHit(Player player) {

			int ran = RANDOM.nextInt(100);

			if (ran <= this.accuracy) {

				int defence = player.getDefence();

				int damage = this.damageCalc() - defence;

				if (damage >= -999 && damage <= 0) return -1;

				return damage;

			}

			return -1000;

		}

		public int damageCalc() {

			return RANDOM.nextInt(this.strength);

		}


		public void takeDamage(int amount) {

			this.health -= amount;

		}

		public boolean isDead() {

			return this.health < 1;

		}

		public void reviveEntity() {

			this.health = this.maxHealth;

		}

	}

}
