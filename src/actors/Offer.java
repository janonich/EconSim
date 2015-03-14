package actors;

public class Offer {

	int product;
	double cash;
	double favor;

	public Offer(int product, double cash, double favor) {
		this.product = product;
		this.cash = cash;
		this.favor = favor;
	}
	
	public void say() {
		System.out.print("(" + product + ",$" +(int) cash + ")");
	}

	public Offer() {
		this(0, 0.0, 0.0);
	}
}