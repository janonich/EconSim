package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import actors.*;

public class Runner {

	static Grapher graph1;
	static Grapher graph2;
	static Grapher graph3;
	static Grapher graph4;

	static List<Double> line1 = new ArrayList<Double>();
	static List<Double> line2 = new ArrayList<Double>();
	static List<Double> line3 = new ArrayList<Double>();
	static List<Double> line4 = new ArrayList<Double>();

	static List<Double> lowProduct = new ArrayList<Double>();
	static List<Double> medProduct = new ArrayList<Double>();
	static List<Double> highProduct = new ArrayList<Double>();

	static List<Double> favorList = new ArrayList<Double>();

	public static final double LOW = 25;
	public static final double MED = 50;
	public static final double HIGH = 75;

	static Random rand = new Random();
	public static Rolodex rolodex = new Rolodex();

	public static void main(String[] args) {

		initgraph();
		fill();

		// rolodex.actors.get(1).makeDeal(2, new Offer(1, 1.0, 0.0));
		// System.out.print("\n");
		// rolodex.actors.get(1).say();
		// System.out.print("\n");
		// rolodex.actors.get(2).say();

		for (int i = 0; i < 10000; i++) {
			System.out.println(i);
			makeDeals();
			calc();

			// try {
			// Thread.sleep(10, 0);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }

		}

		graph();

	}

	private static void initgraph() {
		line1.add(0.0);
		line2.add(0.0);
		line3.add(0.0);
		line4.add(0.0);
	}

	private static void graph() {
		graph1 = new Grapher(line1, new Color(255, 0, 0, 180));
		graph2 = new Grapher(line2, new Color(0, 0, 255, 180));
		graph3 = new Grapher(line3, new Color(0, 255, 0, 180));
		graph4 = new Grapher(line4, new Color(0, 0, 0, 180));

		graph1.setPreferredSize(new Dimension(800, 600));
		graph2.setPreferredSize(new Dimension(800, 600));
		graph3.setPreferredSize(new Dimension(800, 600));
		graph4.setPreferredSize(new Dimension(800, 600));

		JFrame frame = new JFrame("Graph1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(graph1);
		// frame.getContentPane().add(graph2);
		// frame.getContentPane().add(graph3);
		// frame.getContentPane().add(graph4);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void calc() {
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

		double lowcashweight = 0;
		double medcashweight = 0;
		double highcashweight = 0;
		double onepercashweight = 0;
		double lowproductweight = 0;
		double medproductweight = 0;
		double highproductweight = 0;
		double oneperproductweight = 0;

		for (Actor temp : rolodex.actors) {
			if (temp.getCash() < LOW) {
				cashlowcount += 1;
				lowcashweight += temp.getCashWeight();
				// lowcashthresh += temp.getThresh();
			} else if (temp.getCash() < MED) {
				cashmedcount += 1;
				medcashweight += temp.getCashWeight();
				// medcashthresh += temp.getThresh();
			} else if (temp.getCash() < HIGH) {
				cashhighcount += 1;
				highcashweight += temp.getCashWeight();
				// highcashthresh += temp.getThresh();
			} else {
				cashonepercount += 1;
				onepercashweight += temp.getCashWeight();
				// onepercashthresh += temp.getThresh();
			}

			if (temp.getProduct() < LOW) {
				productlowcount += 1;
				lowproductweight += temp.getProductWeight();
				// lowproductthresh += temp.getThresh();
			} else if (temp.getProduct() < MED) {
				productmedcount += 1;
				medproductweight += temp.getProductWeight();
				// medproductthresh += temp.getThresh();
			} else if (temp.getProduct() < HIGH) {
				producthighcount += 1;
				highproductweight += temp.getProductWeight();
				// highproductthresh += temp.getThresh();
			} else {
				productonepercount += 1;
				oneperproductweight += temp.getProductWeight();
				// oneperproductthresh += temp.getThresh();
			}

		}

		lowcashweight /= cashlowcount;
		medcashweight /= cashmedcount;
		highcashweight /= cashhighcount;
		onepercashweight /= cashonepercount;
		lowproductweight /= productlowcount;
		medproductweight /= productmedcount;
		highproductweight /= producthighcount;
		oneperproductweight /= productonepercount;

		System.out.println(cashlowcount + "\t,\t" + cashmedcount + "\t,\t"
				+ cashhighcount + "\t,\t" + cashonepercount);
		System.out.println(productlowcount + "\t,\t" + productmedcount
				+ "\t,\t" + producthighcount + "\t,\t" + productonepercount);
		System.out.println(lowcashweight + " " + medcashweight + " "
				+ highcashweight + " " + onepercashweight + " ");
		System.out.println(lowproductweight + " " + medproductweight + " "
				+ highproductweight + " " + oneperproductweight + " \n");

		// System.out.println(lowcashthresh + " " + medcashthresh + " "
		// + highcashthresh + " " + onepercashthresh + " ");
		// System.out.println(lowproductthresh + " " + medproductthresh + " "
		// + highproductthresh + " " + oneperproductthresh + "\n");

		line1.add(highcashweight);
		line2.add(lowcashweight);
		line3.add(cashhighcount);
		line4.add(cashonepercount);
	}

	public static void fill() {
		for (int id = 0; id < 1000; id++) {
			rolodex.add(new Actor(id, rand.nextDouble(), rand.nextInt(100),
					100 * rand.nextDouble(), 0, new double[] {
							rand.nextDouble(), rand.nextDouble() }));
		}
	}

	public static void makeDeals() {
		for (Actor temp : rolodex.actors) {
			temp.makeDeal(rand.nextInt((rolodex.actors.size() - 1)), new Offer(
					rand.nextInt(100), 100 * rand.nextDouble(), 0));
		}
	}
}