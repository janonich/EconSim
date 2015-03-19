package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import actors.Actor;
import actors.Rolodex;

public class StatAnalysis {

	public static HashMap<String, Integer> index = new HashMap<String, Integer>();
	private static ArrayList<ArrayList<Double>> lines;

	static {
		index.put("cLC", 0);
		index.put("cMC", 1);
		index.put("cHC", 2);
		index.put("cOC", 3);
		index.put("cLU", 4);
		index.put("cMU", 5);
		index.put("cHU", 6);
		index.put("cOU", 7);

		index.put("pLC", 8);
		index.put("pMC", 9);
		index.put("pHC", 10);
		index.put("pOC", 11);
		index.put("pLU", 12);
		index.put("pMU", 13);
		index.put("pHU", 14);
		index.put("pOU", 15);

		lines = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < index.size(); i++) {
			lines.add(new ArrayList<Double>());
		}
	}

	private static Grapher graph1;

	public static final double LOW = 25;
	public static final double MED = 50;
	public static final double HIGH = 75;

	public static void addValues() {

		double cashLowCount = 0;
		double cashMedCount = 0;
		double cashHighCount = 0;
		double cashOneperCount = 0;
		double productLowCount = 0;
		double productMedCount = 0;
		double productHighCount = 0;
		double productOneperCount = 0;

		// double cashLowThresh = 0;
		// double cashMedThresh = 0;
		// double cashHighThresh = 0;
		// double cashOneperThresh = 0;
		//
		// double productLowThresh = 0;
		// double productMedThresh = 0;
		// double productHighThresh = 0;
		// double productOneperThresh = 0;

		double cashLowUtility = 0;
		double cashMedUtility = 0;
		double cashHighUtility = 0;
		double cashOneperUtility = 0;
		double productLowUtility = 0;
		double productMedUtility = 0;
		double productHighUtility = 0;
		double productOneperUtility = 0;

		for (Actor temp : Rolodex.actors) {
			if (temp.getCash() < LOW) {
				cashLowCount += 1;
				cashLowUtility += temp.getCashUtility();
				// cashLowThresh += temp.getThresh();
			} else if (temp.getCash() < MED) {
				cashMedCount += 1;
				cashMedUtility += temp.getCashUtility();
				// cashMedThresh += temp.getThresh();
			} else if (temp.getCash() < HIGH) {
				cashHighCount += 1;
				cashHighUtility += temp.getCashUtility();
				// cashHighThresh += temp.getThresh();
			} else {
				cashOneperCount += 1;
				cashOneperUtility += temp.getCashUtility();
				// cashOneperThresh += temp.getThresh();
			}

			if (temp.getProduct() < LOW) {
				productLowCount += 1;
				productLowUtility += temp.getProductUtility();
				// productLowThresh += temp.getThresh();
			} else if (temp.getProduct() < MED) {
				productMedCount += 1;
				productMedUtility += temp.getProductUtility();
				// productMedThresh += temp.getThresh();
			} else if (temp.getProduct() < HIGH) {
				productHighCount += 1;
				productHighUtility += temp.getProductUtility();
				// productHighThresh += temp.getThresh();
			} else {
				productOneperCount += 1;
				productOneperUtility += temp.getProductUtility();
				// productOneperThresh += temp.getThresh();
			}

		}

		cashLowUtility /= cashLowCount;
		cashMedUtility /= cashMedCount;
		cashHighUtility /= cashHighCount;
		cashOneperUtility /= cashOneperCount;
		productLowUtility /= productLowCount;
		productMedUtility /= productMedCount;
		productHighUtility /= productHighCount;
		productOneperUtility /= productOneperCount;

		System.out.println(cashLowCount + "\t,\t" + cashMedCount + "\t,\t"
				+ cashHighCount + "\t,\t" + cashOneperCount);
		System.out.println(productLowCount + "\t,\t" + productMedCount
				+ "\t,\t" + productHighCount + "\t,\t" + productOneperCount);
		System.out.println(cashLowUtility + " " + cashMedUtility + " "
				+ cashHighUtility + " " + cashOneperUtility + " ");
		System.out.println(productLowUtility + " " + productMedUtility + " "
				+ productHighUtility + " " + productOneperUtility + " \n");

		// System.out.println(cashLowThresh + " " + cashMedThresh + " "
		// + cashHighThresh + " " + cashOneperThresh + " ");
		// System.out.println(productLowThresh + " " + productMedThresh + " "
		// + productHighThresh + " " + productOneperThresh + "\n");

		lines.get(index.get("cLC")).add(cashLowCount);

	}

	static void graph() {
		graph1 = new Grapher(lines.get(index.get("cLC")), new Color(255, 0, 0,
				180));
		graph1.graphWindow("Graph");
	}
}