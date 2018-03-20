public class Bathrooms {
	public static void main(String[] args) {

		Thread man = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

				}
			}
		});

		Thread woman = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

				}
			}
		});

		man.start();
		woman.start();
	}

	public static class Man {
		public static void manWantsToEnter() {

		}

		public static void manLeaves() {
			System.out.println("man leaves");
		}
	}

	public static class Woman {
		public static void womanWantsToEnter() {

		}

		public static void womanLeaves() {
			System.out.println("woman leaves");
		}
	}
}