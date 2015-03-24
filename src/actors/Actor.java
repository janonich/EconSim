package actors;

public class Actor {

	int id;
	private double threshhold;
	private int product;
	private double cash;
	private double favor;
	private double[] utility;

	public Actor() {
		this(0, 0.0, 0, 0.0, 0.0, new double[] { 0.0, 0.0 });
	}

	public Actor(int id, double threshhold, int product, double cash,
			double favor, double[] utility) {
		this.id = id;
		this.threshhold = 1;
		this.product = product;
		this.cash = cash;
		this.favor = favor;
		this.utility = utility;
		utility[0] /= threshhold;
		utility[1] /= threshhold;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void say() {
		System.out.print(id + "(" + product + ",$" + (int) cash + ")");
	}

	// evaluate the offer by the utility function
	private boolean weigh(Offer offer) {
		double weigh = utility[0] * offer.cash/* + utility[1] * offer.favor */
				- utility[1] * offer.product;

		return weigh > threshhold;
	}

	// consider an offer
	public boolean considerDeal(Offer offer) {
		// make sure actor can satisfy offer
		if (offer.product > this.product) {
			return false;
		}

		// if the offer seems beneficial, take it
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

		// make sure offer can be met and is beneficial
		if (!weigh(offer) || offer.cash > this.cash /*
													 * || offer.favor >
													 * this.favor
													 */) {
			return;
		}

		// pose the deal, take it if the other party agrees
		if (Rolodex.get(id).considerDeal(offer)) {

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

	public double getKey(String key) {

		switch (key) {
		case "cash":
			return cash;
		case "product":
			return product;
		case "favor":
			return favor;
		case "cashUtility":
			return utility[0];
		case "productUtility":
			return utility[1];
		default:
			return 0;
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

	public double getCashUtility() {
		return utility[0];
	}

	public double getProductUtility() {
		return utility[1];
	}

	public double getThresh() {
		return threshhold;
	}
}