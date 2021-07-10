package uk.co.tmdavies.testrpg.battles;

import uk.co.tmdavies.testrpg.mobs.Mob;
import uk.co.tmdavies.testrpg.player.Player;
import uk.co.tmdavies.testrpg.utils.Utils;

import java.util.Random;
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

		Utils.scrollScreen(500, "This is how a battle will look like:");

		Utils.waitSec();

		while (!this.entity.isDead()) {

			showStatus();

			if (!this.playerTurn) {

				int damage = this.entity.calcHit(player);

				if (damage == -1000) {

					System.out.println("Whew! " + this.entity.getName() + " missed you!");

					this.playerTurn = true;

					Utils.waitSec();


				} else if (damage == -1) {

					int ran = new Random().nextInt(2)+1;

					if (ran == 1) {

						Utils.scrollScreen(1000, "Wow! That felt like nothing.", "Your defence out-classed " + this.entity.getName() + "'s damage.");

					} else {

						Utils.scrollScreen(1000, "'Tis but a scratch.", "Your defence out-classed " + this.entity.getName() + "'s damage.");

					}

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

					Utils.waitSec();

				} else {

					System.out.println("Oh no! You've missed, Bad luck.");

					Utils.waitSec();

				}

				this.playerTurn = false;

			} else if (option.equalsIgnoreCase("heal")) {

				Utils.clearScreen();

				System.out.println("You healed your self for 10 HP.");

				this.player.restoreHealth(10);

				Utils.waitSec();

				this.playerTurn = false;

			}

		}

		if (this.player.isDead()) {

			Utils.scrollScreen(1000, "How the hell did you die in the tutorial?", "Are you that bad?", "Well... Good luck in your next game?");

			System.exit(1);

		}

		if (this.entity.isDead()) {

			Utils.scrollScreen(1000, "Well done!", "You've completed the tutorial, Good luck with your run!");

			Utils.waitSec();

			this.entity.reviveEntity();

		}

	}

	public void showStatus() {

		Utils.clearScreen();

		Utils.scrollScreen(500, "Your Health: " + this.player.getHealth(), this.entity.getName() + " Health: " + this.entity.getHealth(), " ");

		if (!this.playerTurn) return;

		Utils.scrollScreen(500, "What do you want to do?", " ", "Hit (Deals Damage to Mob)", "Heal (Heals you for 10 HP)");

		Utils.waitSec();

		if (this.secondTurn) {

			Utils.scrollScreen(500, " ", "Ok so you've completed your first turn,",
					"You can see the mob itself also did his turn,",
					"This will repeat till either you or the Mob is dead.", "Good luck!");

			this.secondTurn = false;
		}

		if (this.firstTurn) {

			Utils.scrollScreen(500, " ", "This is everything you would see in a fight.", "Simply type what you want to do, Hit or Heal?");

			this.firstTurn = false;

			this.secondTurn = true;

		}

	}

}
