import java.util.Arrays;

public class MergeSortHilfsfeld {

	public int[] sort(int[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;

			array = sort(array, left, mid);
			array = sort(array, mid + 1, right);
			array = merge(array, left, mid, right);
		}
		return array;
	}

	private int[] merge(int[] array, int left, int mid, int right) {
		int[] hilfsArray = new int[array.length];
		int i, j;
		for (i = left; i <= mid; i++) {
			hilfsArray[i] = array[i];
		}
		for (j = mid + 1; j <= right; j++) {
			hilfsArray[right + mid + 1 - j] = array[j];
		}
		i = left;
		j = right;
		for (int k = left; k <= right; k++) {
			if (hilfsArray[i] <= hilfsArray[j]) {
				array[k] = hilfsArray[i];
				i++;
			} else {
				array[k] = hilfsArray[j];
				j--;
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] randomArray = new int[10];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = (int) (Math.random() * randomArray.length + 1);
		}
		MergeSortHilfsfeld ms = new MergeSortHilfsfeld();
		int mid = randomArray.length / 2;
		int[] randArray1 = Arrays.copyOfRange(randomArray, 0, mid);
		int[] randArray2 = Arrays.copyOfRange(randomArray, mid,
				randomArray.length);
		int[] arr1 = ms.sort(randArray1, 0, randArray1.length - 1);
		int[] arr2 = ms.sort(randArray2, 0, randArray2.length - 1);
		int[] arr = finalSort(arr1, arr2);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + 1 + ": " + arr[i]);
		}
	}

	private static int[] finalSort(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int i = 0, j = 0;
		while (i < arr1.length || j < arr2.length) {
			if (i >= arr1.length) {
				result[i + j] = arr2[j++];
			}
			else if (j >= arr2.length){
				result[i + j] = arr1[i++];
			}
			else {
				if (arr1[i] <= arr2[j]) {
					result[i + j] = arr1[i++];
				} else {
					result[i + j] = arr2[j++];
				}
			}
		}
		return result;
	}
}
