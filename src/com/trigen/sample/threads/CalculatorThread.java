/**
 * File Name: 		ClaculatorThread.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 16:40:07
 * Description:
 */
package com.trigen.sample.threads;

/**
 * @author srinath
 *
 */
public class CalculatorThread implements Runnable{
    private int number;
    
    public CalculatorThread(int number)
    {
	this.number = number;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	for(int i=1;i<=10;i++)
	{
	    System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,number*i);
	}
	
    }

}
