import java.util.Random;
import java.util.ArrayList;

/**
 * resulting matrix will be rowsAxcolsB
 * create averages array
 * for threads 1, 10, 20, 30, ..., 100
 * 		do 25 times:
 	 *		fill matrix arrays
	 * 		get start time
	 * 		multiply
	 * 		get end time
	 * 		store in runtime array
 * 	    after 25: compute average and store in averages array
 *
 * question remains: how to quicken the calculations using threads?
 */
public class MultiMatrix {
	public static void main(String[] args) {
		int[][] arrayA = new int[100][100];
		int[][] arrayB = new int[100][100];
		int[][] result = new int[100][100];
		
		
		// System.out.println("Array A:");
		// printArray(arrayA);
		// System.out.println("Array B:");
		// printArray(arrayB);
		ArrayList<Long> runtimes = new ArrayList<Long>();
		for (int i=1; i<25; i++) {
			
			fillArrays(arrayA);
			fillArrays(arrayB);
			long start = System.nanoTime();
			multiply(arrayA, arrayB, result);
			long end = System.nanoTime() - start;
			// System.out.println(""+start+", "+end);
			System.out.println("runtime iteration " + i + " is\t" + end);
			runtimes.add(end);
		}

		long total = 0;
		for (int i=0; i<runtimes.size(); i++) {
			total += runtimes.get(i);
		}
		double average = total / ((double)runtimes.size());
		System.out.println("average: " + average);

		// System.out.println("Result:");
		// printArray(result);

	}

	public static void fillArrays(int[][] array) {
		Random random = new Random();
		int outerLength = array.length;
		int innerLength = array[0].length;
		int fill = 0;
		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				fill = random.nextInt(5)+1;
				if (random.nextInt(2) == 0) {
					fill *= -1;
				}
				array[i][j] = fill;
			}
		}
	}

	public static void printArray(int[][] array) {
		int outerLength = array.length;
		int innerLength = array[0].length;
		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void multiply(int[][] arrayA, int[][] arrayB, int[][] result) {
		int outerLength = arrayA.length;
		int innerLength = arrayA[0].length;

		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				for (int k=0; k<innerLength; k++) {
					result[i][j] += arrayA[i][k] * arrayB[k][j];
				}
			}
		}
	}
}