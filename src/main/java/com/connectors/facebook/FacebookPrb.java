package com.connectors.facebook;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class FacebookPrb implements Runnable {

	@Override
	public void run() {
		try {
			perform();
		} catch (Exception ex) {
			System.out.println("Error in Login Thread: " + ex.getMessage());
		}
	}

	public void perform() throws Exception {

		// wait - increase this wait period if required
		Thread.sleep(3000);

		// create robot for keyboard operations
		Robot rb = new Robot();

		Thread.sleep(1000);
		rb.mouseMove(870, 181);
		rb.mousePress(InputEvent.BUTTON1_MASK); // press the left mouse button
		rb.mouseRelease(InputEvent.BUTTON1_MASK); // release the left mouse

		Thread.sleep(1000);
		rb.mouseMove(0, 1273);
		System.out.println("end robo croco job ");

	}
}