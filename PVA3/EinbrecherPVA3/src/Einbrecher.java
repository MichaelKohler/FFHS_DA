import java.util.ArrayList;
import java.util.Collections;

public class Einbrecher {

	public class Item implements Comparable<Item> {
		private int weight = 0;
		private int value = 0;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public int getWeight() {
			return this.weight;
		}

		public int getValue() {
			return this.value;
		}

		public int compareTo(Item arg0) {
			return this.value - arg0.getValue();
		}
	}

	ArrayList<Item> items = new ArrayList();
	int maxWeight;
	String maxBitmask = new String();
	int resultValue = 0;
	String resultBitmask = "";
	long runTime = 0;

	public Einbrecher(String[] args) {
		this.maxWeight = Integer.parseInt(args[0]);

		for (int i = 1; i < args.length; i += 2) {
			this.items.add(new Item(Integer.parseInt(args[i]), Integer
					.parseInt(args[i + 1])));
		}

		for (int i = 0; i < this.items.size(); i++) {
			this.maxBitmask += "1";
		}
	}

	private void calculateBestPermutation() {
		long startTime = System.currentTimeMillis();
		int maxRange = (int) (Math.pow(2, this.items.size()) - 1);
		for (int i = 0; i < maxRange; i++) {
			String currentBitmask = Integer.toBinaryString(i);
			while (currentBitmask.length() < this.maxBitmask.length()) {
				currentBitmask = "0" + currentBitmask;
			}
			int currentValue = 0;
			int currentWeight = 0;
			int index = 0;
			while (index < this.items.size()) {
				if (currentBitmask.charAt(index) == '1') {
					currentWeight += this.items.get(index).getWeight();
					currentValue += this.items.get(index).getValue();
				}
				index++;
			}
			if (currentWeight <= this.maxWeight
					&& currentValue > this.resultValue) {
				this.resultBitmask = currentBitmask;
				this.resultValue = currentValue;
			}
		}
		long endTime = System.currentTimeMillis();
		this.runTime = endTime - startTime;
	}

	private void calculateGreedyCombination() {
		long startTime = System.currentTimeMillis();

		// sort items
		ArrayList<Item> sortedItems = new ArrayList<Item>(items);
		Collections.sort(sortedItems);

		// pick most expensive Item, as long as it fits
		int currentWeight = 0;
		int currentValue = 0;
		ArrayList<Item> pickedItems = new ArrayList<Item>();
		while (currentWeight < this.maxWeight) {
			int lastItemIndex = sortedItems.size() - 1;
			for (int i = lastItemIndex; i >= 0; i--) {
				Item mostValuableAndFitting = sortedItems.get(i);
				currentWeight += mostValuableAndFitting.getWeight();
				if (currentWeight > this.maxWeight) {
					// greedy solution:
					// stop looking
					break;
					// better solution:
					// do nothing and try if other items fit
					// currentWeight -= mostValuableAndFitting.getWeight();
				} else {
					pickedItems.add(mostValuableAndFitting);
					sortedItems.remove(mostValuableAndFitting);
					currentValue += mostValuableAndFitting.getValue();
					break;
				}
			}
		}

		// create Bitmask
		for (Item theItem : items) {
			if (pickedItems.contains(theItem))
				this.resultBitmask += "1";
			else
				this.resultBitmask += "0";
		}

		this.resultValue = currentValue;
		long endTime = System.currentTimeMillis();
		this.runTime = endTime - startTime;
	}

	@Override
	public String toString() {
		return "Bitmask: " + this.resultBitmask + "\nValue: "
				+ this.resultValue + "\nTime needed: " + this.runTime + " ms";
	}

	public static void main(String[] args) {
		if (args.length % 2 != 1) {
			System.out.println("Wrong usage, please input:");
			System.out
					.println("[max weight] [weight item1] [value item1] [weight item2] [value item2] ...");
			return;
		}

		Einbrecher bruteForceGuy = new Einbrecher(args);
		bruteForceGuy.calculateBestPermutation();
		System.out.println("BruteForce");
		System.out.println(bruteForceGuy.toString() + "\n");

		Einbrecher greedyGuy = new Einbrecher(args);
		greedyGuy.calculateGreedyCombination();
		System.out.println("Greedy");
		System.out.println(greedyGuy);

	}

}
