package uk.co.tmdavies.testrpg.utils;

import uk.co.tmdavies.testrpg.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

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

	public static void wait(int amount) {

		try { Thread.sleep(amount); } catch (InterruptedException e) {
			System.err.println("Error Waiting");
			e.printStackTrace();
		}

	}

	public static void showPlayerStats(Player player) {

		Utils.scrollScreen(500, " ", "Player Stats: ", " ");

		List<String> statValues = new ArrayList<>();

		for (Map.Entry entry : player.statValues().entrySet())
			statValues.add(entry.getKey() + ": " + entry.getValue());

		Utils.scrollScreen(500, statValues.toArray(new String[0]));

	}

	public static void printTitle() {

		// https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
		// Small Slant

		System.out.println(" ");
		System.out.println(" _______________________  ___  _____");
		System.out.println("/_  __/ __/ __/_  __/ _ \\/ _ \\/ ___/");
		System.out.println(" / / / _/_\\ \\  / / / , _/ ___/ (_ / ");
		System.out.println("/_/ /___/___/ /_/ /_/|_/_/   \\___/  ");
		System.out.println(" ");

	}

}
