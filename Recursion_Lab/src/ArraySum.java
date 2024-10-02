
public class ArraySum {
	
	public int sumofArray(Integer[] a, int index) {
		
		if(index == 0) {
			return a[0];
		}
		
		return a[index] + sumofArray(a, index - 1);
	
	}

}
