import java.util.ArrayList;
import java.util.Random;
public class MatMul_thread2 {

	public static ArrayList<Long> runtimes = new ArrayList<>();
	public static ArrayList<Double> averages = new ArrayList<>();

	public static void main(String[] args) {
		int[][] arrayA = new int[100][100];
		int[][] arrayB = new int[100][100];
		int[][] arrayResult = new int[100][100];

		fillArray(arrayA);
		fillArray(arrayB);
		runThreads(arrayA, arrayB, arrayResult);


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

	public static void runThreads(int[][] arrayA, int[][] arrayB, int[][] arrayR) {
		// ArrayList<Double> averages = new ArrayList<>();
		for (int threadcount=1; threadcount<=100; threadcount+=9) {


			for (int runner=0; runner<25; runner++) {
				ArrayList<Thread> threads = new ArrayList<>();
				//needed to add n number of threads into list
				for (int counter=0; counter<threadcount; counter++) {
					threads.add(new Thread(new MatrixMultiplier(arrayA, arrayB, arrayR, counter, threadcount)));
				}
				//System.out.println("ThreadCount: " + threadcount + " Runner: " + runner + " Threads: " + threads.size());
				for (Thread thread : threads) {
					thread.start();
				}
				for (Thread thread : threads) {
					try {
						thread.join();
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}//end 25 iterations
			long accumulator = 0;
			
			

			// System.out.println(threadcount);
			if (threadcount != 1) {
				threadcount++;
			}
		}
		// System.out.println(averages);
	}

	public static class MatrixMultiplier implements Runnable {

		private int[][] arrayA;
		private int[][] arrayB;
		private int[][] arrayR;
		private int threadPosition;
		private int threadcount;

		public MatrixMultiplier(int[][] arrayA, int[][] arrayB, int[][] arrayR, int threadPosition, int threadcount) {
			this.arrayA = arrayA;
			this.arrayB = arrayB;
			this.arrayR = arrayR;
			this.threadPosition = threadPosition;
			this.threadcount = threadcount;
		}

		@Override
		public void run() {
			// int threadNumber = threadPosition + 1;
			// System.out.println("Thread number: " + threadNumber + " of " + threadcount);
			double floatNumberCompute = (double) 100 / threadcount;
			// System.out.println(floatNumberCompute);
			// System.out.println("Thread Position: " + threadPosition + ", Range " + threadPosition*floatNumberCompute + "-" + (threadPosition+1)*floatNumberCompute);
			
			long startTime = System.currentTimeMillis();
			// for (int i=threadPosition*floatNumberCompute; i<(threadPosition+1)*floatNumberCompute; i++) {
			// 	for (int j=0; j<innerLength; j++) {
			// 		for (int k=0; k<innerLength; k++) {
			// 			arrayResult[i][j] += arrayA[i][k] * arrayB[k][j];
			// 		}
			// 	}
			// }
			long endTime = System.currentTimeMillis() - startTime;
			runtimes.add(endTime);
		}
	}
}