package uk.co.tmdavies.testrpg;

import org.bspfsystems.yamlconfiguration.configuration.ConfigurationSection;
import uk.co.tmdavies.testrpg.adventure.Adventure;
import uk.co.tmdavies.testrpg.battles.TutorialBattle;
import uk.co.tmdavies.testrpg.mobs.Mob;
import uk.co.tmdavies.testrpg.player.Item;
import uk.co.tmdavies.testrpg.player.Player;
import uk.co.tmdavies.testrpg.utils.Config;
import uk.co.tmdavies.testrpg.utils.Utils;

import java.util.*;

public class Main {

	private static Player player;
	private static Mob mob;
	private static boolean playedBefore;

	public static Config config;


	public static void main(String[] args) {

		Main.config = new Config("config");

		Utils.clearScreen();

		Utils.scrollScreen(1000, "Welcome to the TestRPG", "This game is still in testing,", "so expect errors!", " ");

		Scanner input = new Scanner(System.in);

		System.out.println("Please input a Player Name:");
		String name = input.nextLine();

		Utils.clearScreen();

		if (Main.config.getConfig().getConfigurationSection("PlayerData." + name) != null) {

			Utils.clearScreen();

			System.out.println("Found existing data with that name... Loading Player Data...");

			ConfigurationSection section = Main.config.getConfig().getConfigurationSection("PlayerData." + name);

			Main.player = new Player(section.getName(), section.getInt("Health"), section.getInt("Strength"),
					section.getInt("Strength"), section.getInt("Accuracy"), section.getInt("CritChance"), section.getInt("CritDamage"));

			List<Item> items = (List<Item>) section.getList("Inventory");

			Main.player.getInventory().setInventory(items);

			List<Player.Equipment> equips = (List<Player.Equipment>) section.getList("Equipment");

			Main.player.setEquip(equips);

			Main.playedBefore = true;

			Utils.waitSec();

			System.out.println("Loaded!");

		} else {

			System.out.println("Creating Player...");

			Main.player = new Player(name, 20, 10, 1, 50, 25, 1);

			Main.player.savePlayer();

			Main.playedBefore = false;

		}

		Utils.waitSec();

		Utils.clearScreen();

		Main.debugMode(player.getName());

		Utils.scrollScreen(500, "Player Name: " + Main.player.getName());

		Utils.showPlayerStats(Main.player);

		System.out.println(Main.config.getConfig().getCurrentPath());

		Utils.waitSec();

		Utils.clearScreen();

		System.out.println("Loading Game...");

		Main.mob = new Mob();

		Utils.waitSec();

		Utils.clearScreen();

		if (!Main.playedBefore) Main.startTutorial();

		new Adventure(Main.player).startAdventure();

	}

	public static void startTutorial() {

		Mob.MobEntity entity = Mob.MobEntity.JOHN;

		Utils.scrollScreen(1000, "Welcome to TestRPG,", "Lets get you caught up!", " ");

		Utils.scrollScreen(500, "Mob Encounter: ", " " + entity.getName(), " - " + entity.getSubName(), " ", "Good Luck!");

		Utils.waitSec();

		Utils.clearScreen();

		Utils.scrollScreen(1000, "This is the tutorial fight,", "We'll show you how it works...");

		Utils.waitSec();

		Utils.clearScreen();

		TutorialBattle battle = new TutorialBattle(Main.player, entity);

		battle.start();

	}

	public static void debugMode(String name) {

		if (name.equals("Test-Stats")) {

			Scanner input = new Scanner(System.in);

			System.out.print("Enter your HP (0 will revert to default): ");

			int hp = input.nextInt();

			System.out.print("Enter your Strength (0 will revert to default): ");

			int strength = input.nextInt();

			System.out.print("Enter your Defence (0 will revert to default): ");

			int defence = input.nextInt();

			System.out.print("Enter your Accuracy (0 will revert to default): ");

			int accuracy = input.nextInt();

			System.out.print("Enter your Critical Chance (0 will revert to default): ");

			int critChance = input.nextInt();

			System.out.print("Enter your Critical Damage (0 will revert to default): ");

			int critDamage = input.nextInt();

			Main.player = new Player("Debug", hp, strength, defence, accuracy, critChance, critDamage);

			Utils.clearScreen();

		}

		if (name.equals("Test-DamageHitCalc")) {

			while (true) {

				Scanner input = new Scanner(System.in);

				input.next();

				System.out.println("Player DamageHit Calc: " + Main.player.calcHit());

			}

		}

		if (name.equals("Test-CriticalHitCalc")) {

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

		if (name.equals("Test-Adventure")) {

			new Adventure(Main.player).debugStart();

		}

	}

}
