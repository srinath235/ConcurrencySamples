/**
 * File Name: 		PrintQueue.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 17:47:35
 * Description:
 */
package com.trigen.sample.thread.object;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author srinath
 *
 */
public class PrintQueue {
    
    private Semaphore semaphore;
    
    private boolean[] freePrinters;
    
    private Lock printerLock;
    
    public PrintQueue()
    {
	semaphore = new Semaphore(3);
	freePrinters = new boolean[3];
	for(int i=0;i<3;i++)
	{
	    freePrinters[i]=true;
	}
	printerLock = new ReentrantLock();
    }
    
    public void printJob(Object object)
    {
	try {
	    semaphore.acquire();
	    int printerAvail = getPrinter();
	    long duration = (long) Math.random()*10;
	    System.out.printf("%s: PrintQue: Printing a job in a printer %d during %d seconds\n",Thread.currentThread().getName(),printerAvail,duration);
	    freePrinters[printerAvail]=true;
	    TimeUnit.SECONDS.sleep(duration);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    semaphore.release();
	}
	
    }
    
    private int getPrinter()
    {
	int ret =-1;
	printerLock.lock();
	for(int i=0;i<freePrinters.length;i++)
	{
	    if(freePrinters[i])
	    {
		ret=i;
		freePrinters[i]=false;
		break;
	    }
	}
	printerLock.unlock();
	return ret;
    }

}
