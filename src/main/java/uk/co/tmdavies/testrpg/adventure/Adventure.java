package uk.co.tmdavies.testrpg.adventure;

import uk.co.tmdavies.testrpg.battles.Battle;
import uk.co.tmdavies.testrpg.mobs.Mob;
import uk.co.tmdavies.testrpg.player.Player;
import uk.co.tmdavies.testrpg.shop.Shop;
import uk.co.tmdavies.testrpg.utils.Utils;

import java.util.Random;
import java.util.Scanner;

public class Adventure {

	private Player player;
	private Random RANDOM = new Random();

	public Adventure(Player player) {

		this.player = player;

	}

	public void startAdventure() {

		boolean onGoing = true;

		while (onGoing) {

			int ran = RANDOM.nextInt(4) + 1;

			switch (ran) {

				case 1:
				case 4:

					walkAround();

					break;

				case 2:

					enterShop();

					break;

				case 3:

					int ran2 = RANDOM.nextInt(100);

					if (ran2 > 80) {

						enterBoss();

						break;

					}

					enterBattle();

					break;

			}

		}

	}

	public void walkAround() {

		Scanner input = new Scanner(System.in);

		int ran = RANDOM.nextInt(5) + 1;

		System.out.println("What would you like to do? (Continue or Quit)");

		String option = input.nextLine();

		if (option.equalsIgnoreCase("quit")) {

			player.savePlayer();

			System.exit(0);

		}

		procrastinate();

		switch(ran) {

			case 1:
			default:

				System.out.println("You found a odd looking house.");
				Utils.wait(3000);

				Utils.clearScreen();

				System.out.println("You knock.");

				Utils.waitSec();

				Utils.clearScreen();

				System.out.println("You knock..");

				Utils.waitSec();

				Utils.clearScreen();

				System.out.println("You knock...");

				Utils.waitSec();

				int ran2 = RANDOM.nextInt(100);

				if (ran2 < 20 && !player.getEquipments().contains(Player.Equipment.AXE)) {

					System.out.println("You hear someone coming to the door.");

					Utils.waitSec();

					Utils.clearScreen();

					System.out.println("A figure comes charging at you with an axe.");

					Utils.waitSec();

					Utils.clearScreen();

					Utils.scrollScreen(300, "You rush behind the house,", "It comes after you,",
							"You manage to trip it up and steal it's axe.", "You manage to knock it out cold.");

					Utils.wait(5000);

					procrastinate();

					Utils.scrollScreen(1000, "You managed to get away,", "You gained an Axe!", " ", "Axe Stats: +10 Strength");

					player.addEquip(Player.Equipment.AXE);

					player.recalcStats();

				} else {

					System.out.println("Nothing happened...");

				}

				input.next();

				break;

			case 2:

				Utils.scrollScreen(1000, "You found a campsite, out of nowhere.", "You decide to look around.");

				Utils.wait(5000);

				procrastinate();

				Utils.scrollScreen(1000, "The area is clear.", "You decide to stay the rest of the day.");

				Utils.wait(5000);

				procrastinate();

				System.out.println("Your HP has restored.");

				player.restoreHealth(player.getMaxHealth());

				input.next();

				break;

			case 3:

				System.out.println("You decided to make haste and run.");

				Utils.wait(5000);

				procrastinate();

				Utils.scrollScreen(300, "OH NO!", "You were wearing sunglasses...", "You couldn't see the bug on the ground.");
				Utils.wait(5000);

				procrastinate();

				Utils.scrollScreen(1000, "OUCH", "You tripped on the floor and lost 5 HP");

				player.takeDamage(5);

				input.next();

				break;

			case 4:

				System.out.println("You decided to make haste and run.");
				Utils.wait(5000);

				procrastinate();

				System.out.println("You saw a random person running across the road.");
				Utils.wait(5000);

				procrastinate();

				Utils.scrollScreen(300, "OH NO!", "They were wearing earpods...", "They cant hear you!");
				Utils.wait(5000);

				procrastinate();

				Utils.scrollScreen(1000, "OOF!", "They fell off the cliff.", " ", "Mood +1");

				input.next();

				break;

			case 5:

				System.out.println("Nothing happened.");

				input.next();

				break;

		}


	}

	public void enterShop() {

		Shop shop = new Shop(this.player);

		shop.openShop();

	}

	public void enterBattle() {

		Battle battle = new Battle(this.player, new Mob().getMobEntity());

		battle.start();

	}

	public void enterBoss() {

		// Replace with Boss

		Battle battle = new Battle(this.player, new Mob().getMobEntity());

		battle.start();

	}

	public void procrastinate() {

		Utils.clearScreen();

		System.out.println(".");

		Utils.waitSec();

		Utils.clearScreen();

		System.out.println("..");

		Utils.waitSec();

		Utils.clearScreen();

		System.out.println("...");

		Utils.waitSec();

		Utils.clearScreen();

	}

	public void debugStart() {

		Scanner input = new Scanner(System.in);

		while (true) {

			System.out.println("Please input a Debug Option: ");

			String option = input.nextLine();

			switch (option.toLowerCase()) {

				case "walkaround" -> this.walkAround();

				case "shop" -> this.enterShop();

				case "battle" -> {

					int ran2 = RANDOM.nextInt(100);

					if (ran2 > 80) {

						this.enterBoss();

						break;

					}

					this.enterBattle();

				}

				case "exit" -> System.exit(0);

			}

		}


	}

}
