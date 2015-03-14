package actors;

import main.*;

public class Actor {

	int id;
	private double threshhold;
	private int product;
	private double cash;
	private double favor;
	private double[] weight;

	public Actor() {
		this(0, 0.0, 0, 0.0, 0.0, new double[] { 0.0, 0.0 });
	}

	public Actor(int id, double threshhold, int product, double cash,
			double favor, double[] weight) {
		this.id = id;
		this.threshhold = threshhold / threshhold;
		this.product = product;
		this.cash = cash;
		this.favor = favor;
		this.weight = weight;
		weight[0] /= threshhold;
		weight[1] /= threshhold;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void say() {
		System.out.print(id + "(" + product + ",$" + (int) cash + ")");
	}

	private boolean weigh(Offer offer) {
		double weigh = weight[0] * offer.cash/* + weight[1] * offer.favor */
				- weight[1] * offer.product;

		return weigh > threshhold;
	}

	public boolean considerDeal(Offer offer) {
		if (offer.product > this.product) {
			return false;
		}

		if (weigh(offer)) {

			// System.out.print("\tto ");
			// this.say();

			product -= offer.product;
			// favor += offer.favor;
			cash += offer.cash;

			return true;
		} else {
			return false;
		}
	}

	public void makeDeal(int id, Offer offer) {

		if (offer.cash > this.cash /* || offer.favor > this.favor */) {
			return;
		}

		if (!weigh(offer)) {
			return;
		}

		if (Runner.rolodex.actors.get(id).considerDeal(offer)) {

			// System.out.print(" from ");
			// this.say();
			// System.out.print(" || ");
			// offer.say();
			// System.out.print("\n");

			product += offer.product;
			cash -= offer.cash;
			// favor -= favor;
		}
	}

	public double getCash() {
		return cash;
	}

	public double getFavor() {
		return favor;
	}

	public int getProduct() {
		return product;
	}

	public double getCashWeight() {
		return weight[0];
	}

	public double getProductWeight() {
		return weight[1];
	}

	public double getThresh() {
		return threshhold;
	}
}