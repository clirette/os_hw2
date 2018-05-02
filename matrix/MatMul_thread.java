import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class MatMul_thread {
	public static void main(String[] args) {
		int[][] arrayA = new int[100][100];
		int[][] arrayB = new int[100][100];
		int[][] arrayResult = new int[100][100];

		fillArray(arrayA);
		writeArray(arrayA, 1);
		fillArray(arrayB);
		writeArray(arrayB, 2);
		doThings(arrayA, arrayB, arrayResult);



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

	public static void writeArray(int[][] array, int number) {
		String deepstring = Arrays.deepToString(array);
		String filename = "array"+number;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(deepstring);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void doThings(int[][] arrayA, int[][] arrayB, int[][] arrayResult) {
		ArrayList<Long> runtimes = new ArrayList<Long>();
		ArrayList<Double> averages = new ArrayList<Double>();
		for (int i=1; i<=100; i+=9) {
			ExecutorService executor = Executors.newFixedThreadPool(i);
			for (int counter=0; counter<25; counter++) {
				long start = 0;
				long end = 0; 
				for (int j=0; j<i; j++) {
					Runnable worker = new MyRunnable(arrayA, arrayB, arrayResult);
					start = System.nanoTime();
					executor.execute(worker);
					end = System.nanoTime() - start;
				}
				runtimes.add(end);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
				
			}
			int total = 0;
			for (int k=0; k<runtimes.size(); k++) {
				total += runtimes.get(k);
			}
			double average = total / ((double)runtimes.size());
			averages.add(Math.ceil(average));
			System.out.println("working with " + i + " threads");
			if (i != 1) {
				i++;
			}
		}
		// System.out.println("average runtimes: ");
		// System.out.println(averages);
		writeCSV(averages);
		writeArray(arrayResult, 3);
	
	}

	public static void writeCSV(ArrayList<Double> averages) {
		System.out.println("\n|CSV|");
		System.out.println(" ---");
		String headers = "Threads,Runtimes\n";
		int count = 1;
		for (int i=0; i<averages.size(); i++) {
			headers += count+",";
			if (count != 1) {
				count +=10;
			} else {
				count += 9;
			}
			headers += averages.get(i) + "\n";
		}

		System.out.print(headers);
		try (PrintWriter out = new PrintWriter("out.csv")) {
			out.print(headers);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static class MyRunnable implements Runnable {
		int[][] arrayA;
		int[][] arrayB;
		int[][] arrayResult;

		public MyRunnable(int[][] arrayA, int[][] arrayB, int[][] arrayResult) {
			this.arrayA = arrayA;
			this.arrayB = arrayB;
			this.arrayResult = arrayResult;
		}

		@Override
		public void run() {
			int outerLength = arrayA.length;
			int innerLength = arrayA[0].length;
			for (int i=0; i<outerLength; i++) {
				for (int j=0; j<innerLength; j++) {
					for (int k=0; k<innerLength; k++) {
						arrayResult[i][j] += arrayA[i][k] * arrayB[k][j];
					}
				}
			}
		}
	}
}