/**
 * File Name: 		PrintTask.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 18:02:33
 * Description:
 */
package com.trigen.sample.threads;

import com.trigen.sample.thread.object.PrintQueue;

/**
 * @author srinath
 *
 */
public class PrintTask implements Runnable {
    
    private PrintQueue printQue;
    
    public PrintTask(PrintQueue printQue)
    {
	this.printQue = printQue;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
	printQue.printJob(new Object());
	System.out.printf("%s: Document has been printed\n",Thread.currentThread().getName());
    }

}
