package uk.co.tmdavies.testrpg.battles;

import uk.co.tmdavies.testrpg.mobs.Mob;
import uk.co.tmdavies.testrpg.player.Player;
import uk.co.tmdavies.testrpg.utils.Utils;

import java.util.Random;
import java.util.Scanner;

public class Battle {

	private Player player;
	private Mob.MobEntity entity;
	private boolean playerTurn = true;

	public Battle(Player player, Mob.MobEntity entity) {

		this.player = player;

		this.entity = entity;

	}

	public void start() {

		while (!this.entity.isDead()) {

			showStatus();

			if (!this.playerTurn) {

				int damage = this.entity.calcHit(player);

				if (damage == -1000) {

					System.out.println("Whew! " + this.entity.getName() + " missed you!");

					this.playerTurn = true;

					Utils.waitSec();

				} else if (damage == -1) {

					Utils.scrollScreen(1000, this.getRandomOverDefenceMessage());

					this.playerTurn = true;

					Utils.waitSec();

				} else {

					System.out.println("Ouch! " + this.entity.getName() + " hit you for " + damage + " HP.");

					this.player.takeDamage(damage);

					this.playerTurn = true;

					Utils.waitSec();

				}

				continue;

			}

			Scanner input = new Scanner(System.in);

			String option = input.next();

			if (option.equalsIgnoreCase("hit")) {

				Utils.clearScreen();

				int damage = this.player.calcHit();

				if (damage != -1) {

					System.out.println("You hit " + this.entity.getName() + " for " + damage);

					this.entity.takeDamage(damage);

					Utils.wait(3000);

				} else {

					System.out.println("Oh no! You've missed, Bad luck.");

					Utils.wait(3000);

				}

				this.playerTurn = false;

			} else if (option.equalsIgnoreCase("heal")) {

				Utils.clearScreen();

				System.out.println("You healed your self for 10 HP.");

				this.player.restoreHealth(10);

				Utils.wait(3000);

				this.playerTurn = false;

			}

		}

		if (this.player.isDead()) {

			Utils.scrollScreen(1000, "Game Over", "You have died", "Good luck on your next run!");
			System.exit(1);

		}

		if (this.entity.isDead()) {

			Utils.scrollScreen(1000, "Well done!", "You have gained " + this.entity.getExperience() + " XP.");

			this.player.addExperience(this.entity.getExperience());

			this.entity.reviveEntity();

		}

	}

	public void showStatus() {

		Utils.clearScreen();

		Utils.scrollScreen(500, "Your Health: " + this.player.getHealth(), this.entity.getName() + " Health: " + this.entity.getHealth(), " ");

		if (!this.playerTurn) return;

		Utils.scrollScreen(500, "What do you want to do?", " ", "Hit (Deals Damage to Mob)", "Heal (Heals you for 10 HP)");

	}

	public static void startBattle(Player player, Mob.MobEntity entity) {

		Utils.scrollScreen(500, "Mob Encounter: ", " " + entity.getName(), " - " + entity.getSubName(), " ", "Good Luck!");

		System.out.println(entity.getHealth());

		Battle battle = new Battle(player, entity);

		battle.start();

	}

	public String[] getRandomOverDefenceMessage() {

		int ran = new Random().nextInt(2);

		if (ran == 1) {

			return new String[]{"Wow! That felt like nothing.", "Your defence out-classed " + this.entity.getName() + "'s damage."};

		} else {

			return new String[]{"'Tis but a scratch.", "Your defence out-classed " + this.entity.getName() + "'s damage."};

		}

	}

}
