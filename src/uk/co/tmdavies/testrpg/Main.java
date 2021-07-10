package uk.co.tmdavies.testrpg;

import uk.co.tmdavies.testrpg.adventure.Adventure;
import uk.co.tmdavies.testrpg.battles.Battle;
import uk.co.tmdavies.testrpg.battles.TutorialBattle;
import uk.co.tmdavies.testrpg.mobs.Mob;
import uk.co.tmdavies.testrpg.player.Player;

import java.util.*;

public class Main {

	private static Player player;
	public static Mob mob;

	public static void main(String[] args) {

		Main.clearScreen();

		Main.scrollScreen(1000, "Welcome to the TestRPG", "This game is still in testing,", "so expect errors!", " ");

		Scanner input = new Scanner(System.in);

		System.out.println("Please input a Player Name:");
		String name = input.nextLine();

		Main.clearScreen();

		System.out.println("Creating Player...");

		Main.player = new Player(name, 20, 10, 1, 50, 25, 1);

		Main.waitSec();

		Main.clearScreen();

		if (player.getName().equals("DebugOn") || player.getName().equals("DebugOn2")) Main.debugMode(player.getName());

		Main.scrollScreen(500, "Player Name: " + Main.player.getName(), " ", "Player Stats:");

		List<String> statValues = new ArrayList<>();

		for (Map.Entry entry : Main.player.statValues().entrySet())
			statValues.add(entry.getKey() + ": " + entry.getValue());

		Main.scrollScreen(500, statValues.toArray(new String[0]));

		Main.waitSec();

		Main.clearScreen();

		System.out.println("Loading Game...");

		Main.mob = new Mob();

		Main.waitSec();

		Main.clearScreen();

		Main.startTutorial();

		while (true) {

			Main.adventureStart();

		}

	}

	public static void clearScreen() {

		for (int i = 0; i < 100; i++) System.out.println(" ");

	}

	public static void scrollScreen(long howLong, String... strings) {

		for (String s : strings) {

			System.out.println(s);

			try { Thread.sleep(howLong); } catch (InterruptedException e) {
				System.err.println("Error Waiting");
				e.printStackTrace();
			}

		}

	}

	public static void waitSec() {

		try { Thread.sleep(1000); } catch (InterruptedException e) {
			System.err.println("Error Waiting");
			e.printStackTrace();
		}

	}

	public static void startTutorial() {

		Mob.MobEntity entity = Mob.MobEntity.TEST;

		Main.scrollScreen(1000, "Welcome to TestRPG,", "Lets get you caught up!", " ");

		Main.scrollScreen(500, "Mob Encounter: ", " " + entity.getName(), " - " + entity.getSubName(), " ", "Good Luck!");

		Main.waitSec();

		Main.clearScreen();

		Main.scrollScreen(1000, "This is the tutorial fight,", "We'll show you how it works...");

		Main.waitSec();

		Main.clearScreen();

		TutorialBattle battle = new TutorialBattle(Main.player, entity);

		battle.start();

	}

	public static void adventureStart() {

		new Adventure(Main.player);

	}


	public static void debugMode(String name) {

		if (name.equals("DebugOn")) {

			while (true) {

				Scanner input = new Scanner(System.in);

				input.next();

				System.out.println("Player DamageHit Calc: " + Main.player.calcHit());

			}

		}

		if (name.equals("DebugOn2")) {

			while (true) {

				Scanner input = new Scanner(System.in);

				System.out.println("Strength:");
				int amount = new Random().nextInt(input.nextInt());

				System.out.println(amount);

				System.out.println("Critical Damage:");
				int critDamage = input.nextInt();

				amount = (int) (amount * (1 + ((double) critDamage / 100)));

				System.out.println("Post-Crit Calc: " + amount);

			}

		}

	}

}
