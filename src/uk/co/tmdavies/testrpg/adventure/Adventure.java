package uk.co.tmdavies.testrpg.adventure;

import uk.co.tmdavies.testrpg.player.Player;

import java.util.Random;

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

			while (ran < 4) {

				ran = RANDOM.nextInt(4) + 1;

			}

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



	}

	public void enterShop() {



	}

	public void enterBattle() {



	}

	public void enterBoss() {



	}

}
