package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import actors.Actor;

public class StatAnalysis {

	static List<Double> line1 = new ArrayList<Double>();
	static List<Double> line2 = new ArrayList<Double>();
	static List<Double> line3 = new ArrayList<Double>();
	static List<Double> line4 = new ArrayList<Double>();

	static Grapher graph1;
	static Grapher graph2;
	static Grapher graph3;
	static Grapher graph4;

	public static void statistics() {
		double cashlowcount = 0;
		double cashmedcount = 0;
		double cashhighcount = 0;
		double cashonepercount = 0;
		double productlowcount = 0;
		double productmedcount = 0;
		double producthighcount = 0;
		double productonepercount = 0;

		// double lowcashthresh = 0;
		// double medcashthresh = 0;
		// double highcashthresh = 0;
		// double onepercashthresh = 0;
		//
		// double lowproductthresh = 0;
		// double medproductthresh = 0;
		// double highproductthresh = 0;
		// double oneperproductthresh = 0;

		double lowcashutility = 0;
		double medcashutility = 0;
		double highcashutility = 0;
		double onepercashutility = 0;
		double lowproductutility = 0;
		double medproductutility = 0;
		double highproductutility = 0;
		double oneperproductutility = 0;

		for (Actor temp : Rolodex.actors) {
			if (temp.getCash() < Runner.LOW) {
				cashlowcount += 1;
				lowcashutility += temp.getCashUtility();
				// lowcashthresh += temp.getThresh();
			} else if (temp.getCash() < Runner.MED) {
				cashmedcount += 1;
				medcashutility += temp.getCashUtility();
				// medcashthresh += temp.getThresh();
			} else if (temp.getCash() < Runner.HIGH) {
				cashhighcount += 1;
				highcashutility += temp.getCashUtility();
				// highcashthresh += temp.getThresh();
			} else {
				cashonepercount += 1;
				onepercashutility += temp.getCashUtility();
				// onepercashthresh += temp.getThresh();
			}

			if (temp.getProduct() < Runner.LOW) {
				productlowcount += 1;
				lowproductutility += temp.getProductUtility();
				// lowproductthresh += temp.getThresh();
			} else if (temp.getProduct() < Runner.MED) {
				productmedcount += 1;
				medproductutility += temp.getProductUtility();
				// medproductthresh += temp.getThresh();
			} else if (temp.getProduct() < Runner.HIGH) {
				producthighcount += 1;
				highproductutility += temp.getProductUtility();
				// highproductthresh += temp.getThresh();
			} else {
				productonepercount += 1;
				oneperproductutility += temp.getProductUtility();
				// oneperproductthresh += temp.getThresh();
			}

		}

		lowcashutility /= cashlowcount;
		medcashutility /= cashmedcount;
		highcashutility /= cashhighcount;
		onepercashutility /= cashonepercount;
		lowproductutility /= productlowcount;
		medproductutility /= productmedcount;
		highproductutility /= producthighcount;
		oneperproductutility /= productonepercount;

		System.out.println(cashlowcount + "\t,\t" + cashmedcount + "\t,\t"
				+ cashhighcount + "\t,\t" + cashonepercount);
		System.out.println(productlowcount + "\t,\t" + productmedcount
				+ "\t,\t" + producthighcount + "\t,\t" + productonepercount);
		System.out.println(lowcashutility + " " + medcashutility + " "
				+ highcashutility + " " + onepercashutility + " ");
		System.out.println(lowproductutility + " " + medproductutility + " "
				+ highproductutility + " " + oneperproductutility + " \n");

		// System.out.println(lowcashthresh + " " + medcashthresh + " "
		// + highcashthresh + " " + onepercashthresh + " ");
		// System.out.println(lowproductthresh + " " + medproductthresh + " "
		// + highproductthresh + " " + oneperproductthresh + "\n");

		line1.add(cashlowcount);
		line2.add(lowcashutility);
		line3.add(cashhighcount);
		line4.add(cashonepercount);
	}

	static public void graph() {
		graph1 = new Grapher(line1, new Color(255, 0, 0, 180));
		// graph2 = new Grapher(line2, new Color(0, 0, 255, 180));
		// graph3 = new Grapher(line3, new Color(0, 255, 0, 180));
		// graph4 = new Grapher(line4, new Color(0, 0, 0, 180));

		graph1.graphWindow("Graph");
	}

}