import java.util.Random;

/**
 * resulting matrix will be rowsAxcolsB
 */
public class MultiMatrix {
	public static void main(String[] args) {
		int[][] arrayA = new int[3][3];
		int[][] arrayB = new int[3][3];
		int[][] result = new int[3][3];
		
		fillArrays(arrayA);
		fillArrays(arrayB);
		
		System.out.println("Array A:");
		printArray(arrayA);
		System.out.println("Array B:");
		printArray(arrayB);

		multiply(arrayA, arrayB, result);

		// System.out.println("Result:");
		// printArray(result);

	}

	public static void fillArrays(int[][] array) {
		Random random = new Random();
		int outerLength = array.length;
		int innerLength = array[0].length;
		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				array[i][j] = random.nextInt(5)+1;
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
		int accumulator = 0;
		int resultRowIndex = 0;
		int resultColIndex = 0;
		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				accumulator += arrayA[i][j] * arrayB[j][i];
			}
			System.out.println(accumulator);
			/**
			 * need to figure out how to index into result array
			 */
		// 	if (resultRowIndex % outerLength == 0) {
		// 		resultRowIndex = 0;
		// 	}
		// 	if (resultColIndex % innerLength == 0) {
		// 		resultColIndex = 0;
		// 	}
		// 	result[resultRowIndex][resultColIndex] = accumulator;
		// 	resultRowIndex++;
		// 	resultColIndex++;
		}
	}
}