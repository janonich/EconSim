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

		double[] tempvars = new double[index.size()];

		 tempvars[index.get("cLC")] = 0;
		 tempvars[index.get("cMC")] = 0;
		 tempvars[index.get("cHC")] = 0;
		 tempvars[index.get("cOC")] = 0;
		 tempvars[index.get("pLC")] = 0;
		 tempvars[index.get("pMC")] = 0;
		 tempvars[index.get("pHC")] = 0;
		 tempvars[index.get("pOC")] = 0;

		// double cashLowThresh = 0;
		// double cashMedThresh = 0;
		// double cashHighThresh = 0;
		// double cashOneperThresh = 0;
		//
		// double productLowThresh = 0;
		// double productMedThresh = 0;
		// double productHighThresh = 0;
		// double productOneperThresh = 0;

		 tempvars[index.get("cLU")] = 0;
		 tempvars[index.get("cMU")] = 0;
		 tempvars[index.get("cHU")] = 0;
		 tempvars[index.get("cOU")] = 0;
		 tempvars[index.get("pLU")] = 0;
		 tempvars[index.get("pMU")] = 0;
		 tempvars[index.get("pHU")] = 0;
		 tempvars[index.get("pOU")] = 0;

		for (Actor temp : Rolodex.actors) {
			if (temp.getCash() < LOW) {
				tempvars[index.get("cLC")] += 1;
				tempvars[index.get("cLU")] += temp.getCashUtility();
				// cashLowThresh += temp.getThresh();
			} else if (temp.getCash() < MED) {
				tempvars[index.get("cMC")] += 1;
				tempvars[index.get("cMU")] += temp.getCashUtility();
				// cashMedThresh += temp.getThresh();
			} else if (temp.getCash() < HIGH) {
				tempvars[index.get("cHC")] += 1;
				tempvars[index.get("cHU")] += temp.getCashUtility();
				// cashHighThresh += temp.getThresh();
			} else {
				tempvars[index.get("cOC")] += 1;
				tempvars[index.get("cOU")] += temp.getCashUtility();
				// cashOneperThresh += temp.getThresh();
			}

			if (temp.getProduct() < LOW) {
				tempvars[index.get("pLC")] += 1;
				tempvars[index.get("pLU")] += temp.getProductUtility();
				// productLowThresh += temp.getThresh();
			} else if (temp.getProduct() < MED) {
				tempvars[index.get("pMC")] += 1;
				tempvars[index.get("pMU")] += temp.getProductUtility();
				// productMedThresh += temp.getThresh();
			} else if (temp.getProduct() < HIGH) {
				tempvars[index.get("pHC")] += 1;
				tempvars[index.get("pHU")] += temp.getProductUtility();
				// productHighThresh += temp.getThresh();
			} else {
				tempvars[index.get("pOC")] += 1;
				tempvars[index.get("pOU")] += temp.getProductUtility();
				// productOneperThresh += temp.getThresh();
			}

		}

		tempvars[index.get("cLU")] /= tempvars[index.get("cLC")];
		tempvars[index.get("cMU")] /= tempvars[index.get("cMC")];
		tempvars[index.get("cHU")] /= tempvars[index.get("cHC")];
		tempvars[index.get("cOU")] /= tempvars[index.get("cOC")];
		tempvars[index.get("pLU")] /= tempvars[index.get("pLC")];
		tempvars[index.get("pMU")] /= tempvars[index.get("pMC")];
		tempvars[index.get("pHU")] /= tempvars[index.get("pHC")];
		tempvars[index.get("pOU")] /= tempvars[index.get("pOC")];

		System.out.println(tempvars[index.get("cLC")] + "\t,\t" + tempvars[index.get("cMC")] + "\t,\t"
				+ tempvars[index.get("cHC")] + "\t,\t" + tempvars[index.get("cOC")]);
		System.out.println(tempvars[index.get("pLC")] + "\t,\t" + tempvars[index.get("pMC")]
				+ "\t,\t" + tempvars[index.get("pHC")] + "\t,\t" + tempvars[index.get("pOC")]);
		System.out.println(tempvars[index.get("cLU")] + " " + tempvars[index.get("cMU")] + " "
				+ tempvars[index.get("cHU")] + " " + tempvars[index.get("cOU")] + " ");
		System.out.println(tempvars[index.get("pLU")] + " " + tempvars[index.get("pMU")] + " "
				+ tempvars[index.get("pHU")] + " " + tempvars[index.get("pOU")] + " \n");

		// System.out.println(cashLowThresh + " " + cashMedThresh + " "
		// + cashHighThresh + " " + cashOneperThresh + " ");
		// System.out.println(productLowThresh + " " + productMedThresh + " "
		// + productHighThresh + " " + productOneperThresh + "\n");

		lines.get(index.get("cLU")).add(tempvars[index.get("cLU")]);

	}

	static void graph() {
		graph1 = new Grapher(lines.get(index.get("cLU")), new Color(255, 0, 0,
				180));
		graph1.graphWindow("Graph");
	}
}