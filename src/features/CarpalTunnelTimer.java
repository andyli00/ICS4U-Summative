package features;

/**
 * A timer that reminds you to take a break from typing
 * @author Andy Li
 * @since Nov 3, 2017
 */
public class CarpalTunnelTimer {
	
	private boolean finished = false;
	
	/**
	 * Creates a Thread that waits for a specified time
	 * @param timeInSeconds how long to wait, in seconds
	 */
	public CarpalTunnelTimer(int timeInSeconds) {
		
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(timeInSeconds * 1000);
				finished = true;
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t.start();
	}
	
	/**
	 * @return whether or not the timer is done
	 */
	private boolean isFinished() {
		return finished;
	}
}