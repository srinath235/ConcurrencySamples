/**
 * File Name: 		ParticipantTask.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 18:26:07
 * Description:
 */
package com.trigen.sample.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author srinath
 *
 */
public class ParticipantTask implements Runnable {

    private String name;
    
    private VideoConferenceTask videoConference;
    
    /**
     * 
     */
    public ParticipantTask(VideoConferenceTask conferenceTask,String name) {
	this.videoConference=conferenceTask;
	this.name=name;
    }
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	long duration = (long)(Math.random()*10);	
	try {
	    TimeUnit.SECONDS.sleep(duration);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	videoConference.arrive(name);
    }

}
