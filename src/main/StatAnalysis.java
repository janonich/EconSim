package main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import org.apache.commons.math3.stat.StatUtils;

import actors.Actor;
import actors.Rolodex;

public class StatAnalysis {

	public static HashMap<String, Integer> categoricalIndex = new HashMap<String, Integer>();
	public static HashMap<String, Integer> actorIndex = new HashMap<String, Integer>();
	private static ArrayList<ArrayList<Double>> categoricalLines = new ArrayList<ArrayList<Double>>();;
	private static ArrayList<ArrayList<Double>> actorLines = new ArrayList<ArrayList<Double>>();

	// fill the indices and line arrays
	static {

		// fill categorical index
		categoricalIndex.put("cLC", 0);
		categoricalIndex.put("cMC", 1);
		categoricalIndex.put("cHC", 2);
		categoricalIndex.put("cOC", 3);
		categoricalIndex.put("cLU", 4);
		categoricalIndex.put("cMU", 5);
		categoricalIndex.put("cHU", 6);
		categoricalIndex.put("cOU", 7);

		categoricalIndex.put("pLC", 8);
		categoricalIndex.put("pMC", 9);
		categoricalIndex.put("pHC", 10);
		categoricalIndex.put("pOC", 11);
		categoricalIndex.put("pLU", 12);
		categoricalIndex.put("pMU", 13);
		categoricalIndex.put("pHU", 14);
		categoricalIndex.put("pOU", 15);

		// fill the actor index
		actorIndex.put("cMin", 0);
		actorIndex.put("cMax", 1);
		actorIndex.put("cMean", 2);
		actorIndex.put("cStddev", 3);
		actorIndex.put("c25%", 4);
		actorIndex.put("c75%", 5);

		actorIndex.put("pMin", 6);
		actorIndex.put("pMax", 7);
		actorIndex.put("pMean", 8);
		actorIndex.put("pStddev", 9);
		actorIndex.put("p25%", 10);
		actorIndex.put("p75%", 11);

		// fill the arrays with arrays
		for (int i = 0; i < categoricalIndex.size(); i++) {
			categoricalLines.add(new ArrayList<Double>());
		}
		for (int i = 0; i < actorIndex.size(); i++) {
			actorLines.add(new ArrayList<Double>());
		}
	}

	public static final double LOW = 15;
	public static final double MED = 50;
	public static final double HIGH = 70;

	// categorize and add values of actor statistics to the lines
	public static void addValues() {

		double[] tempvars = new double[categoricalIndex.size()];

		for (int i = 0; i < categoricalIndex.size(); i++) {
			tempvars[i] = 0;
		}

		// categorize actors and add values
		for (Actor temp : Rolodex.list()) {
			if (temp.getKey("cash") < LOW) {
				tempvars[categoricalIndex.get("cLC")] += 1;
				tempvars[categoricalIndex.get("cLU")] += temp
						.getKey("cashUtility");
			} else if (temp.getKey("cash") < MED) {
				tempvars[categoricalIndex.get("cMC")] += 1;
				tempvars[categoricalIndex.get("cMU")] += temp
						.getKey("cashUtility");
			} else if (temp.getKey("cash") < HIGH) {
				tempvars[categoricalIndex.get("cHC")] += 1;
				tempvars[categoricalIndex.get("cHU")] += temp
						.getKey("cashUtility");
			} else {
				tempvars[categoricalIndex.get("cOC")] += 1;
				tempvars[categoricalIndex.get("cOU")] += temp
						.getKey("cashUtility");
			}

			if (temp.getKey("product") < LOW) {
				tempvars[categoricalIndex.get("pLC")] += 1;
				tempvars[categoricalIndex.get("pLU")] += temp
						.getKey("productUtility");
			} else if (temp.getKey("product") < MED) {
				tempvars[categoricalIndex.get("pMC")] += 1;
				tempvars[categoricalIndex.get("pMU")] += temp
						.getKey("productUtility");
			} else if (temp.getKey("product") < HIGH) {
				tempvars[categoricalIndex.get("pHC")] += 1;
				tempvars[categoricalIndex.get("pHU")] += temp
						.getKey("productUtility");
			} else {
				tempvars[categoricalIndex.get("pOC")] += 1;
				tempvars[categoricalIndex.get("pOU")] += temp
						.getKey("productUtility");
			}
		}

		// normalize utility sums to the count
		tempvars[categoricalIndex.get("cLU")] /= (int) tempvars[categoricalIndex
				.get("cLC")];
		tempvars[categoricalIndex.get("cMU")] /= (int) tempvars[categoricalIndex
				.get("cMC")];
		tempvars[categoricalIndex.get("cHU")] /= (int) tempvars[categoricalIndex
				.get("cHC")];
		tempvars[categoricalIndex.get("cOU")] /= (int) tempvars[categoricalIndex
				.get("cOC")];
		tempvars[categoricalIndex.get("pLU")] /= (int) tempvars[categoricalIndex
				.get("pLC")];
		tempvars[categoricalIndex.get("pMU")] /= (int) tempvars[categoricalIndex
				.get("pMC")];
		tempvars[categoricalIndex.get("pHU")] /= (int) tempvars[categoricalIndex
				.get("pHC")];
		tempvars[categoricalIndex.get("pOU")] /= (int) tempvars[categoricalIndex
				.get("pOC")];

		// send to outstream
		System.out.println(tempvars[categoricalIndex.get("cLC")] + "\t,\t"
				+ tempvars[categoricalIndex.get("cMC")] + "\t,\t"
				+ tempvars[categoricalIndex.get("cHC")] + "\t,\t"
				+ tempvars[categoricalIndex.get("cOC")]);
		System.out.println(tempvars[categoricalIndex.get("pLC")] + "\t,\t"
				+ tempvars[categoricalIndex.get("pMC")] + "\t,\t"
				+ tempvars[categoricalIndex.get("pHC")] + "\t,\t"
				+ tempvars[categoricalIndex.get("pOC")]);
		System.out.println(tempvars[categoricalIndex.get("cLU")] + " "
				+ tempvars[categoricalIndex.get("cMU")] + " "
				+ tempvars[categoricalIndex.get("cHU")] + " "
				+ tempvars[categoricalIndex.get("cOU")] + " ");
		System.out.println(tempvars[categoricalIndex.get("pLU")] + " "
				+ tempvars[categoricalIndex.get("pMU")] + " "
				+ tempvars[categoricalIndex.get("pHU")] + " "
				+ tempvars[categoricalIndex.get("pOU")] + " \n");

		// add the values to lines
		categoricalLines.get(categoricalIndex.get("cLC")).add(
				tempvars[categoricalIndex.get("cLC")]);
		categoricalLines.get(categoricalIndex.get("cMC")).add(
				tempvars[categoricalIndex.get("cMC")]);
		categoricalLines.get(categoricalIndex.get("cHC")).add(
				tempvars[categoricalIndex.get("cHC")]);
		categoricalLines.get(categoricalIndex.get("cOC")).add(
				tempvars[categoricalIndex.get("cOC")]);

		categoricalLines.get(categoricalIndex.get("pLC")).add(
				tempvars[categoricalIndex.get("pLC")]);
		categoricalLines.get(categoricalIndex.get("pMC")).add(
				tempvars[categoricalIndex.get("pMC")]);
		categoricalLines.get(categoricalIndex.get("pHC")).add(
				tempvars[categoricalIndex.get("pHC")]);
		categoricalLines.get(categoricalIndex.get("pOC")).add(
				tempvars[categoricalIndex.get("pOC")]);

		categoricalLines.get(categoricalIndex.get("cLU")).add(
				tempvars[categoricalIndex.get("cLU")]);
		categoricalLines.get(categoricalIndex.get("cMU")).add(
				tempvars[categoricalIndex.get("cMU")]);
		categoricalLines.get(categoricalIndex.get("cHU")).add(
				tempvars[categoricalIndex.get("cHU")]);
		categoricalLines.get(categoricalIndex.get("cOU")).add(
				tempvars[categoricalIndex.get("cOU")]);

		categoricalLines.get(categoricalIndex.get("pLU")).add(
				tempvars[categoricalIndex.get("pLU")]);
		categoricalLines.get(categoricalIndex.get("pMU")).add(
				tempvars[categoricalIndex.get("pMU")]);
		categoricalLines.get(categoricalIndex.get("pHU")).add(
				tempvars[categoricalIndex.get("pHU")]);
		categoricalLines.get(categoricalIndex.get("pOU")).add(
				tempvars[categoricalIndex.get("pOU")]);

	}

	// create statistics of the lines tracking average statistics
	static double[] lineStatistics(String key) {

		ArrayList<Double> templist = categoricalLines.get(categoricalIndex
				.get(key));

		double[] temparray = new double[templist.size()];
		for (int i = 0; i < templist.size(); i++) {
			temparray[i] = templist.get(i);
		}

		double[] out = new double[6];

		out[0] = StatUtils.min(temparray);
		out[1] = StatUtils.max(temparray);
		out[2] = StatUtils.mean(temparray);
		out[3] = Math.sqrt(StatUtils.variance(temparray));
		out[4] = StatUtils.percentile(temparray, 25);
		out[5] = StatUtils.percentile(temparray, 75);

		return out;
	}

	// create statistical analysis of the population at a specific time
	static double[] actorStatistics(String key) {

		double[] temparray = new double[Rolodex.size()];
		for (int i = 0; i < temparray.length; i++) {
			temparray[i] = Rolodex.get(i).getKey(key);
		}

		double[] out = new double[6];

		out[0] = StatUtils.min(temparray);
		out[1] = StatUtils.max(temparray);
		out[2] = StatUtils.mean(temparray);
		out[3] = Math.sqrt(StatUtils.variance(temparray));
		out[4] = StatUtils.percentile(temparray, 25);
		out[5] = StatUtils.percentile(temparray, 75);

		return out;
	}

	static List<ArrayList<Double>> actorConcentration(String key, int samplesize) {

		List<ArrayList<Double>> out = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> inlist = new ArrayList<Double>();
		ArrayList<Double> outlist = new ArrayList<Double>();

		for (int i = 0; i < Rolodex.size(); i++) {
			inlist.add(Rolodex.get(i).getKey(key));
		}

		Collections.sort(inlist);

		for (int i = 0; i < inlist.size() - samplesize; i++) {
			double temp = samplesize // / inlist.size()
					/ (inlist.get(i + samplesize) - inlist.get(i));

			outlist.add(Math.log10(temp));
		}

		for (int i = 0; i < 1 + (int) Math
				.round(0.5 * (double) samplesize + 0.5); i++) {
			inlist.remove(i);
		}
		for (int i = inlist.size()
				- (int) Math.round(0.5 * (double) samplesize - 0.5); i < inlist
				.size(); i++) {
			inlist.remove(i);
		}

		// double outlisttotal = 0;
		// for (int i = 0; i < outlist.size(); i++) {
		// outlisttotal += outlist.get(i);
		// }
		// System.out.println(outlisttotal);

		out.add(inlist);
		out.add(outlist);

		return out;
	}

	static private String arrayToString(double[] in, String key) {
		String outstring = "";
		outstring += key + ": ";
		outstring += "\nmin: " + in[0];
		outstring += "\nmax: " + in[1];
		outstring += "\nmean: " + in[2];
		outstring += "\nstdev: " + in[3];
		outstring += "\n25%: " + in[4];
		outstring += "\n75%: " + in[5];
		return outstring;
	}

	static void graph(String key, double[] makeup) {

		Grapher graph = new Grapher(categoricalLines.get(categoricalIndex
				.get(key)), new Color(255, 0, 0, 180));

		graph.setPreferredSize(new Dimension(800, 600));

		JFrame frame = new JFrame("Results");
		JPanel graphPanel = new JPanel();
		JPanel statPanel = new JPanel();
		frame.getContentPane().add(graphPanel, "South");
		frame.getContentPane().add(statPanel, "North");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		graphPanel.add(graph);
		List<ArrayList<Double>> temparray = actorConcentration("cash", 5);
		Grapher graphactor = new Grapher(temparray.get(0), temparray.get(1),
				new Color(0, 255, 0, 180));
		graphactor.setPreferredSize(new Dimension(800, 600));
		graphPanel.add(graphactor);

		JTextArea actormakeup = new JTextArea(arrayToString(makeup,
				"Actor Makeup"));
		statPanel.add(actormakeup);

		JTextArea linestats = new JTextArea(arrayToString(lineStatistics(key),
				key));
		statPanel.add(linestats);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}