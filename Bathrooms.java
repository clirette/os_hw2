import java.util.Random;
import java.util.concurrent.Semaphore;
public class Bathrooms {

	public static void main(String[] args) {

		Random generator = new Random();
		BathroomControl bsem = new BathroomControl();
		
		Thread man = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						int choice = generator.nextInt(2);
						int tracker = generator.nextInt(1000);
						//man wants to enter
						if (choice == 0) {
							System.out.println(tracker + ": trying man wants to enter");
							bsem.manWantsToEnter(tracker);
						} else if (choice == 1) {	//man leaves
							System.out.println(tracker + ": trying man leaves");
							bsem.manLeaves(tracker);
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread woman = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						int choice = generator.nextInt(2);
						int tracker = generator.nextInt(1000);
						if (choice == 0) {
							System.out.println(tracker + ": trying woman wants to enter");
							bsem.womanWantsToEnter(tracker);
						} else if (choice == 1) {
							System.out.println(tracker + ": trying woman leaves");
							bsem.womanLeaves(tracker);
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		man.start();
		woman.start();
	}

	public static class BathroomControl {
		
		private enum Status {EMPTY, WOMEN_PRESENT, MEN_PRESENT};
		private static Status state;
		private static int menInside;
		private static int womenInside;

		public BathroomControl() {
			state = Status.EMPTY;
			menInside = 0;
			womenInside = 0;
		}

		public void womanWantsToEnter(int tracker) {
			if (state == Status.EMPTY) {
				womenInside++;
				state = Status.WOMEN_PRESENT;
				System.out.println(tracker + ": woman enters, was empty " + "(" + womenInside + ")");
			} else if (menInside > 0) {
				//nothing
				System.out.println(tracker + ": can't enter, men present " + "(" + menInside + ")");
			} else if (womenInside > 0) {
				womenInside++;
				System.out.println(tracker + ": woman enters, only women " + "(" + womenInside + ")");
			}
		}

		public static void womanLeaves(int tracker) {
			if (womenInside > 0) {
				womenInside--;
				System.out.println(tracker + ": woman leaves " + "(" + womenInside + ")");
				if (womenInside == 0) {
					state = Status.EMPTY;
					System.out.println(tracker + ": now empty");
				}
			} else {
				System.out.println(tracker + ": there were no women");
			}
		}

		public static void manWantsToEnter(int tracker) {
			if (state == Status.EMPTY) {
				menInside++;
				state = Status.MEN_PRESENT;
				System.out.println(tracker + ": man enters, was empty " + "(" + menInside + ")");
			} else if (womenInside > 0) {
				//nothing
				System.out.println(tracker + ": can't enter, women present " + "(" + womenInside + ")");
			} else if (menInside > 0) {
				menInside++;
				System.out.println(tracker + ": man enters, only men " + "(" + menInside + ")");
			}
		}

		public static void manLeaves(int tracker) {
			if (menInside > 0) {
				menInside--;
				System.out.println(tracker + ": man leaves " + "(" + menInside + ")");
				if (menInside == 0) {
					state = Status.EMPTY;
					System.out.println(tracker + ": now empty");
				}
			} else {
				System.out.println(tracker + ": there were no men");
			}
		}
	}
}