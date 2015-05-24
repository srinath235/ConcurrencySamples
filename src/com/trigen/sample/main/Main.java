/**
 * File Name: 		Main.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 16:49:20
 * Description:
 */
package com.trigen.sample.main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.trigen.sample.thread.object.Event;
import com.trigen.sample.thread.object.PrintQueue;
import com.trigen.sample.threads.CalculatorThread;
import com.trigen.sample.threads.CleanerTask;
import com.trigen.sample.threads.FactorialCalculator;
import com.trigen.sample.threads.ParticipantTask;
import com.trigen.sample.threads.PrintTask;
import com.trigen.sample.threads.VideoConferenceTask;
import com.trigen.sample.threads.WriterTask;

/**
 * @author srinath
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	//runCalculatorThread();
	//daemonThreadShown();
	//semaphoreShown();
	//countDownLatchShown();
	callableShown();

    }
    
    private static void runCalculatorThread()
    {
	for(int i=1;i<=10;i++)
	{
	    Thread thread = new Thread(new CalculatorThread(i));
	    thread.start();
	}
    }
    
    private static void daemonThreadShown()
    {
	Deque<Event> deque = new ArrayDeque<Event>();
	WriterTask writeTask = new WriterTask(deque);
	for(int i=0;i<3;i++)
	{
	    Thread thread = new Thread(writeTask);
	    thread.start();
	}
	CleanerTask cleaner = new CleanerTask(deque);
	cleaner.start();
    }
    
    private static void semaphoreShown()
    {
	PrintQueue printQue = new PrintQueue();
	Thread[] threads = new Thread[10];
	for(int i=0;i<10;i++)
	{
	    threads[i] = new Thread(new PrintTask(printQue),"Thread"+i); 
	}
	for(int i=0;i<10;i++)
	{
	    threads[i].start(); 
	}
    }
    
    private static void countDownLatchShown()
    {
	VideoConferenceTask videoTask = new VideoConferenceTask(10);
	Thread videoThread = new Thread(videoTask);
	videoThread.start();
	for(int i=0;i<10;i++)
	{
	    Thread thread = new Thread(new ParticipantTask(videoTask, "Thread"+i));
	    thread.start();
	}
    }
    
    private static void callableShown()
    {
	ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
	Random random = new Random();
	List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();
	for(int i=0;i<10;i++)
	{
	    Integer number = random.nextInt(10);
	    FactorialCalculator calculator = new FactorialCalculator(number);
	    Future<Integer> result = executor.submit(calculator);
	    resultList.add(result);
	}
	
	do{
	    System.out.printf("Main: Number of completed tasks %d\n",executor.getCompletedTaskCount());
	    for(int i=0;i<resultList.size();i++)
	    {
		Future<Integer> result = resultList.get(i);
		System.out.printf("Main: Task %d: %s\n",i,result.isDone());
	    }
	    
	    try {
		TimeUnit.MILLISECONDS.sleep(500);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	}while(executor.getCompletedTaskCount()<resultList.size());
	
	System.out.println("Main : Result at the end");
	for(int i=0;i<resultList.size();i++)
	{
	    Integer number = null;
	    Future<Integer> result = resultList.get(i);
	    try {
		number = result.get();
	    } catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    System.out.printf("Main: Task %d: %d\n",i,number);
	}
	executor.shutdown();
    }

}
