/**
 * File Name: 		CleanerTask.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 17:10:55
 * Description:
 */
package com.trigen.sample.threads;

import java.util.Date;
import java.util.Deque;

import com.trigen.sample.thread.object.Event;

/**
 * @author srinath
 *
 */
public class CleanerTask extends Thread {
    
    private Deque<Event> deque;
    
    public CleanerTask(Deque<Event> deque)
    {
	this.deque=deque;
	setDaemon(true);
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	while(true)
	{
	    clean(new Date());
	}

    }
    
    private void clean(Date date)
    {
	if(deque.size()==0)
	    return;
	long difference=0;
	boolean delete=false;
	do
	{
	    Event event = deque.getLast();
	    difference = date.getTime()-event.getDate().getTime();
	    if(difference>1000)
	    {
		System.out.printf("Cleaner: %s\n",event.getEvent());
		deque.removeLast();
		delete=true;
	    }
	}while(difference>1000);
	if(delete)
	    System.out.printf("Cleaner Size: %d\n",deque.size());
    }

}
