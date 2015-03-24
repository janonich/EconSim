package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import actors.*;

public class Runner {

	static Random rand = new Random();

	public static void main(String[] args) {

		fill(10000);
		
		displayDist("cash");
		
		for (int i = 0; i < 10000; i++) {

			System.out.println("Iteration: " + i);
			makeDeals();
			StatAnalysis.addValues();
			// totalcash();
		}
		double[] makeup = StatAnalysis.actorStatistics("cash");
		displayDist("cash");
		StatAnalysis.graph("cLU", makeup);
	}

	// fill the Rolodex full of actors to interact
	public static void fill(int amount) {
		for (int id = 0; id < amount; id++) {
			Rolodex.add(new Actor(id, rand.nextDouble(), rand.nextInt(100),
					100 * rand.nextDouble(), 0, new double[] {
							rand.nextDouble(), rand.nextDouble() }));
		}

		// ArrayList<Double> initial = new ArrayList<Double>();
		// for (int i = 0; i < Rolodex.size(); i++){
		// initial.add(Rolodex.get(i).getKey("cash"));
		// }
		//
		// Collections.sort(initial);
		//
		// Grapher graph = new Grapher(initial);
		// graph.display("Initial Cash");
	}

	// have actors make deals with each other
	public static void makeDeals() {
		for (Actor temp : Rolodex.list()) {
			temp.makeDeal(rand.nextInt((Rolodex.size() - 1)),
					new Offer(rand.nextInt(100), 100 * rand.nextDouble(), 0));
		}
	}

	public static void totalcash() {
		double sum = 0;
		for (Actor temp : Rolodex.list()) {
			sum += temp.getCash();
		}
		System.out.println("$$$" + sum);
	}

	public static void displayDist(String key) {

		ArrayList<Double> data = new ArrayList<Double>();
		for (int i = 0; i < Rolodex.size(); i++) {
			data.add(Rolodex.get(i).getKey(key));
		}

		Collections.sort(data);

		Grapher graph = new Grapher(data);
		graph.display(key);
	}
}