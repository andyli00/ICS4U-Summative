package features;

import javafx.application.Platform;
import utilities.Dialog;

/**
 * A timer that reminds you to take a break from typing
 * @author Andy Li
 * @since Nov 3, 2017
 */
public class CarpalTunnelTimer {
	
	/**
	 * Creates a Thread that waits for a specified time
	 * @param timeInSeconds how long to wait, in seconds
	 */
	public CarpalTunnelTimer(int timeInSeconds) {
		//using a Thread won't work because only
		//the FX thread can modify ui elements
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(timeInSeconds * 1000);
					timerFinished();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * shows a dialog box
	 */
	private void timerFinished() {
		Dialog.showInformation("Your timer is finished");
	}
}