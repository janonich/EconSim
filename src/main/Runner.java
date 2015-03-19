package main;

import java.util.Random;

import actors.*;

public class Runner {

	static Random rand = new Random();

	public static void main(String[] args) {

		fill();

		// Rolodex.actors.get(1).makeDeal(2, new Offer(1, 1.0, 0.0));
		// System.out.print("\n");
		// Rolodex.actors.get(1).say();
		// System.out.print("\n");
		// Rolodex.actors.get(2).say();

		for (int i = 0; i < 1000; i++) {
			System.out.println("Iteration: " + i);
			makeDeals();
			StatAnalysis.addValues();

			// try {
			// Thread.sleep(10, 0);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }

		}
		StatAnalysis.graph();

	}

	public static void fill() {
		for (int id = 0; id < 100000; id++) {
			Rolodex.add(new Actor(id, rand.nextDouble(), rand.nextInt(100),
					100 * rand.nextDouble(), 0, new double[] {
							rand.nextDouble(), rand.nextDouble() }));
		}
	}

	public static void makeDeals() {
		for (Actor temp : Rolodex.actors) {
			temp.makeDeal(rand.nextInt((Rolodex.actors.size() - 1)), new Offer(
					rand.nextInt(100), 100 * rand.nextDouble(), 0));
		}
	}
}