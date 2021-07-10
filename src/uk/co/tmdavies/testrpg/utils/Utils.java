package uk.co.tmdavies.testrpg.utils;

import uk.co.tmdavies.testrpg.Main;
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

}
