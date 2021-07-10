package uk.co.tmdavies.testrpg.shop;

import uk.co.tmdavies.testrpg.Main;
import uk.co.tmdavies.testrpg.player.Item;
import uk.co.tmdavies.testrpg.player.Player;
import uk.co.tmdavies.testrpg.utils.Utils;

import java.util.*;

public class Shop {

	private Player player;

	private int playerBalance;

	private HashMap<Integer, HashMap<Item, Integer>> shopContents;

	private Random RANDOM = new Random();

	public Shop(Player player) {

		this.player = player;
		this.playerBalance = player.getMoney();

	}

	public void openShop() {

		boolean exit = false;

		Scanner input = new Scanner(System.in);

		while (!exit) {

			Utils.scrollScreen(1000, "Welcome to the shop", "Please look at our wears.", " ");

			HashMap<Integer, Integer> shopPrices = new HashMap<>();

			for (Map.Entry shopEntry : shopContents.entrySet()) {

				for (Map.Entry contentEntry : ((HashMap<Item, Integer>) shopEntry.getValue()).entrySet()) {

					System.out.println(shopEntry.getKey() + ": " + contentEntry.getKey() + " - £" + contentEntry.getKey());

					shopPrices.put((int) shopEntry.getKey(), (int) contentEntry.getValue());

				}

			}

			Utils.scrollScreen(1000, " ", "Buy <number> | Exit");

			String option = input.nextLine();

			switch(option) {

				case "1":

					if (this.playerBalance < shopPrices.get(1)) {

						shopContents.get(1);

						player.getInventory().addItem((Item) ((Map.Entry)shopContents.get(0).entrySet()).getKey());

					}

					break;

				case "2":

					if (this.playerBalance < shopPrices.get(2)) {

						shopContents.remove(2);

						player.getInventory().addItem((Item) ((Map.Entry)shopContents.get(1).entrySet()).getKey());

					}

					break;

				case "3":

					if (this.playerBalance < shopPrices.get(3)) {

						shopContents.remove(3);

						player.getInventory().addItem((Item) ((Map.Entry)shopContents.get(2).entrySet()).getKey());

					}

					break;

				case "4":

					if (this.playerBalance < shopPrices.get(4)) {

						shopContents.remove(4);

						player.getInventory().addItem((Item) ((Map.Entry)shopContents.get(3).entrySet()).getKey());

					}

					break;

				case "5":

					if (this.playerBalance < shopPrices.get(5)) {

						shopContents.remove(5);

						player.getInventory().addItem((Item) ((Map.Entry)shopContents.get(4).entrySet()).getKey());

					}

					break;

				case "exit":

					exit = true;

					break;

				default:

					break;
			}

		}


	}

	public void getContents() {

		int index = RANDOM.nextInt(Item.values().length);

		List<Item> itemList = new ArrayList<>();

		for (int i = 0; i < 5; i++) itemList.add(Item.values()[index]);

		for (Item item : itemList) {

			HashMap<Item, Integer> temp = new HashMap<>();

			switch (item) {

				case STRENGTH_POTION:

					temp.put(item, 10);

					this.shopContents.put(1, temp);
					break;

				default:
					break;

			}

		}

	}

	public void buyItem() {



	}

}
