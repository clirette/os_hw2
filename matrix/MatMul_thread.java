import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Class that measures the execution time of matrix multiplication
 * must do 25 iterations of each run for threads 1-100, incremented by 10
 * DONE:
 * 	- created two arrays and methods to fill them with +/- values
 * TODO:
 * 	- determine how matrix multiplication even works multithreaded
 * 	
 */
public class MatMul_thread {
	public static void main(String[] args) {
		final int MATRIXSIZE = 5;
		int[][] arrayA = new int[MATRIXSIZE][MATRIXSIZE];
		int[][] arrayB = new int[MATRIXSIZE][MATRIXSIZE];
		int[][] result = new int[MATRIXSIZE][MATRIXSIZE];

		fillArray(arrayA);
		fillArray(arrayB);

		final int MAXTHREADS = 100;
		double[] runtimes = new double[11]; //not 0-offset
		int runtimeIndex = 1;
		for (int i=1; i<MAXTHREADS; i++) {
			ArrayList<Thread> threads = createThreads(i, arrayA, arrayB, result);
		}
		
	}

	public static void fillArray(int[][] array) {
		Random random = new Random();
		final int MAXSIZE = 100;
		int outerLength = array.length;
		int innerLength = array[0].length;
		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				int ijEntry = random.nextInt(MAXSIZE);
				if (random.nextInt(2) == 0) {
					ijEntry *= -1;
				}
				array[i][j] = ijEntry;
			}
		}
	}

	public static ArrayList<Thread> createThreads(int numberOfThreads, int[][] arrayA, int[][] arrayB, int[][] result) {
		ArrayList<Threads> myThreads = new ArrayList<>();
		for (int i=0; i<numberOfThreads; i++) {
			myThreads.add(new Thread(new Runnable() {
				//confusing matrix mult
				@Override
				public void run() {
					int outerLength = arrayA.length;
					int innerLength = arrayA[0].length;
					int resultRow = 0;
					for (int i=0; i<outerLength; i++) {
						for (int j=0; j<innerLength; j++) {
							 result[resultRow][]
						}
					}
				}
			}););
		}
		return myThreads;
	}

	public static int run(int threads) {
		Thread[] myThreads = new Thread[threads];
		for (int i=0; i<myThreads.length; i++) {
			myThreads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {

					}
				}
			})
		}
		return 1;
	}

	public static void printArray(int[][] array) {
		int outerLength = array.length;
		int innerLength = array[0].length;
		System.out.println();
		for (int i=0; i<outerLength; i++) {
			for (int j=0; j<innerLength; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
}