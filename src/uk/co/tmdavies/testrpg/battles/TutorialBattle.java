package uk.co.tmdavies.testrpg.battles;

import uk.co.tmdavies.testrpg.Main;
import uk.co.tmdavies.testrpg.mobs.Mob;
import uk.co.tmdavies.testrpg.player.Player;

import java.util.Scanner;

public class TutorialBattle {

	private Player player;
	private Mob.MobEntity entity;
	private boolean playerTurn = true;
	private boolean firstTurn = true;
	private boolean secondTurn = false;

	public TutorialBattle(Player player, Mob.MobEntity entity) {

		this.player = player;

		this.entity = entity;

	}

	public void start() {

		Main.scrollScreen(500, "This is how a battle will look like:");

		Main.waitSec();

		while (!this.entity.isDead()) {

			showStatus();

			if (!this.playerTurn) {

				int damage = this.entity.calcHit();

				if (damage != -1) {

					System.out.println("Ouch! " + this.entity.getName() + " hit you for " + damage + " HP.");

					this.player.takeDamage(damage);

					this.playerTurn = true;

					Main.waitSec();

				} else {

					System.out.println("Whew! " + this.entity.getName() + " missed you!");

					this.playerTurn = true;

					Main.waitSec();

				}

				continue;

			}

			Scanner input = new Scanner(System.in);

			if (input.next().equalsIgnoreCase("hit")) {

				Main.clearScreen();

				int damage = this.player.calcHit();

				if (damage != -1) {

					System.out.println("You hit " + this.entity.getName() + " for " + damage);

					this.entity.takeDamage(damage);

					Main.waitSec();

				} else {

					System.out.println("Oh no! You've missed, Bad luck.");

					Main.waitSec();

				}

				this.playerTurn = false;

			} else if (input.next().equalsIgnoreCase("heal")) {

				Main.clearScreen();

				System.out.println("You healed your self for 10 HP.");

				this.player.restoreHealth(10);

				Main.waitSec();

				this.playerTurn = false;

			}

		}

		if (player.isDead()) {

			Main.scrollScreen(1000, "Game Over", "You have died", "Good luck on your next run!");
			System.exit(1);

		}

		if (entity.isDead()) {

			Main.scrollScreen(1000, "Well done!", "You have gained " + this.entity.getExperience() + " XP.");

			this.player.addExperience(this.entity.getExperience());

		}

	}

	public void showStatus() {

		Main.clearScreen();

		Main.scrollScreen(500, "Your Health: " + this.player.getHealth(), this.entity.getName() + " Health: " + this.entity.getHealth(), " ");

		if (!this.playerTurn) return;

		Main.scrollScreen(500, "What do you want to do?", " ", "Hit (Deals Damage to Mob)", "Heal (Heals you for 10 HP)");

		Main.waitSec();

		if (this.secondTurn) {

			Main.scrollScreen(500, " ", "Ok so you've completed your first turn,",
					"You can see the mob itself also did his turn,",
					"This will repeat till either you or the Mob is dead.", "Good luck!");

			this.secondTurn = false;
		}

		if (this.firstTurn) {

			Main.scrollScreen(500, " ", "This is everything you would see in a fight.", "Simply type what you want to do, Hit or Heal?");

			this.firstTurn = false;

			this.secondTurn = true;

		}

	}
}
