/*
* @Author Chase Lirette
* Class that synchonizes the 3-person operation of putting order into oven,
* taking order out of oven, then handing pizza to customer.
* NOTE It's possible for the print statements to appear out of order
* 	   but the order of execution should be correct.
*/

import java.util.concurrent.Semaphore;
public class Pizzas {
	public static void main(String[] args) {

		final int TOTAL_ORDERS = 100;

		//Create two binary semaphores. One to control
		//whether pizzas can be put into the oven,
		//another to control whether pizzas can go to QCD
		BinarySemaphore oven = new BinarySemaphore();
		BinarySemaphore desk = new BinarySemaphore();

		//Each person is a thread
		Thread personA = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						//Use for loops so that each thread can work independently
						//The print statements confirm that they operate on the same order
						for (int order = 1; order <= TOTAL_ORDERS; order++) {
							oven.release();	
							System.out.println("Person A read order number " + order + " and put order in oven.");
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread personB = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						for (int order = 1; order <= TOTAL_ORDERS; order++) {
							oven.acquire();
							desk.release();
							System.out.println("Person B moved order number " + order + " from oven to QCD.");
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread personC = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						for (int order = 1; order <= TOTAL_ORDERS; order++) {
							desk.acquire();
							System.out.println("Person C moved order number " + order + " from QCD to customer.");
							Thread.sleep(1000);
						} 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		personA.start();
		personB.start();
		personC.start();
		try {
			personA.join();
			personB.join();
			personC.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	* BinarySemaphore class to represent the states for both
	* the oven and QCD. 
	*/
	public static class BinarySemaphore {
		private Semaphore semaphore;

		public BinarySemaphore() {
			this.semaphore = new Semaphore(0);
		}

		public void acquire() {
			try {
				this.semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void release() {
			//Very important to busy-wait if there is 1 permit.
			//This ensures the permits stay between 0 and 1.
			while (this.semaphore.availablePermits() == 1) {
			}
			this.semaphore.release();
		}
	}
}