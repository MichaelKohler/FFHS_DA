import java.util.ArrayList;

public class Einbrecher {
	
	public class Item {
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
	}

	ArrayList<Item> items = new ArrayList();
	int maxWeight;
	String maxBitmask = new String();
	int bestValue = 0;
	String bestBitmask = "";
	long runTime = 0;
	
	public Einbrecher(String[] args) {
		this.maxWeight = Integer.parseInt(args[0]);

		for (int i = 1; i < args.length; i += 2) {
			this.items.add(new Item(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1])));
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
			if (currentWeight <= this.maxWeight && currentValue > this.bestValue) {
				this.bestBitmask = currentBitmask;
				this.bestValue = currentValue;
			}
		}
		long endTime = System.currentTimeMillis();
		this.runTime = endTime - startTime;
	}
	
	@Override
	public String toString() {
		return this.bestBitmask + "\nValue " + this.bestValue + "\nTime needed: " + this.runTime + " ms";
	}

	public static void main(String[] args) {
		if (args.length % 2 != 1) {
			System.out.println("Wrong usage, please input:");
			System.out.println("[max weight] [weight item1] [value item1] [weight item2] [value item2] ...");
			return;
		}
		
		Einbrecher hans = new Einbrecher(args);
		hans.calculateBestPermutation();
		System.out.println(hans.toString());
	}

}
