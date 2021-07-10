package uk.co.tmdavies.testrpg.player;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	Player player;
	List<Item> inventory;

	public Inventory(Player player) {

		this.player = player;
		this.inventory = new ArrayList<>();

	}

	public Player getPlayer() {

		return this.player;

	}

	public void addItem(Item item) {

		this.inventory.add(item);

	}

	public void removeItem(Item item) {

		if (this.inventory.contains(item)) {

			this.inventory.remove(item);

		} else {

			throw new IllegalArgumentException("Player inventory does not contain that item.");

		}

	}

	public void removeItem(int index) {

		this.inventory.remove(index);

	}

}
