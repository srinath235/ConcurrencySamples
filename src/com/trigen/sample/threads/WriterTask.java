/**
 * File Name: 		WriterTask.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 17:01:36
 * Description:
 */
package com.trigen.sample.threads;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import com.trigen.sample.thread.object.Event;

/**
 * @author srinath
 *
 */
public class WriterTask implements Runnable {
    
    private Deque<Event> deque;
    
    public WriterTask(Deque<Event> deque)
    {
	this.deque=deque;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	for(int i=0;i<100;i++)
	{
	    Event event = new Event();
	    event.setDate(new Date());
	    event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));
	    deque.addFirst(event);
	    try {
		TimeUnit.SECONDS.sleep(1);
	    } catch (InterruptedException e) {		
		e.printStackTrace();
	    }
	}

    }

}
