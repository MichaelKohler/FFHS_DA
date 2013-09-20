public class BinSearch {

	public static void main(String[] args) {
		int search = 4;
		int[] array = new int[] { 0, 1, 4, 6, 8 };
		int result = binSearch(array, search, 0, array.length);
		System.out.println(result);
	}

	static int binSearch(int[] array, int item, int min, int max)
	{
	  if (min > max) {
	    return -1;
	}
	  else {
	      int pivot = Math.round((min+max)/2);
	      if (array[pivot] > item)
	        return binSearch(array, item, min, pivot - 1);
	      else if (array[pivot] < item)
	        return binSearch(array, item, pivot+1, max);
	      else
	        return pivot;
	    }
	}
}
