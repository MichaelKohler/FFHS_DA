public class MaxMin {

	public static void main(String[] args) {
		int[] inputArray = new int[] { 3, 1, 4 }; // bzw. Input des Users
		int[] result = minMax(inputArray);
	}
	
	private static int[] minMax(int[] a) {
		int x = a[0];
		int y = a[0];
		for (int i = 1; i <= a.length - 1; i++) {
			if (x > a[i]) {
				x = a[i];
			}
			
			if (y < a[i]) {
				y = a[i];
			}
			System.out.println("x: " + x + ", y: " + y);
		}
		int[] result = new int[] { x, y };
		return result;
	}

}
