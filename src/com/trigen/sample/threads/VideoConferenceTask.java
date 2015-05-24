/**
 * File Name: 		VideoConferenceTask.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 18:17:47
 * Description:
 */
package com.trigen.sample.threads;

import java.util.concurrent.CountDownLatch;

/**
 * @author srinath
 *
 */
public class VideoConferenceTask implements Runnable {
    
    private CountDownLatch controller;
    
    /**
     * 
     */
    public VideoConferenceTask(int number) {
	controller = new CountDownLatch(number);
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	System.out.printf("Initialization: %d participants\n",controller.getCount());
	try {
	    controller.await();
	    System.out.println("All participants has arrived, lets start");
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
    
    public void arrive(String name)
    {
	System.out.printf("%s has arrived\n",name);
	controller.countDown();
	System.out.printf("Waiting for %d participants\n",controller.getCount());
    }

}
