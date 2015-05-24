/**
 * File Name: 		FactorialCalculator.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	24 May 2015 00:03:02
 * Description:
 */
package com.trigen.sample.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author srinath
 *
 */
public class FactorialCalculator implements Callable<Integer> {
    
    int number;
     /**
     * 
     */
    public FactorialCalculator(Integer number) {
	this.number = number;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Integer call() throws Exception {
	int returnValue = 1;
	if(number==0||number==1)
	{
	    returnValue=1;
	}else{
	    for(int i=2;i<=number;i++)
	    {
		returnValue*=i;
		TimeUnit.MILLISECONDS.sleep(20);
	    }
	}
	System.out.printf("%s: %d\n",Thread.currentThread().getName(),returnValue);
	return returnValue;
    }

}
