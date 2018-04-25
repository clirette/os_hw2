import java.util.Random;

/**
 * resulting matrix will be rowsAxcolsB
 */
public class MultiMatrix {
	public static void main(String[] args) {
		int[][] arrayA = new int[100][100];
		int[][] arrayB = new int[100][100];
		int[][] result = new int[100][100];
		
		fillArrays(arrayA);
		fillArrays(arrayB);
		
		System.out.println("Array A:");
		printArray(arrayA);
		System.out.println("Array B:");
		printArray(arrayB);

		multiply(arrayA, arrayB, result);

		System.out.println("Result:");
		printArray(result);

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