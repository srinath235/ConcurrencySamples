/**
 * File Name: 		Event.java
 * Version:		0.1
 * Module:		ConcurrencyProject
 * Developer:		srinath
 * Creation Date:	23 May 2015 17:00:41
 * Description:
 */
package com.trigen.sample.thread.object;

import java.util.Date;

/**
 * @author srinath
 *
 */
public class Event {
    
    private String event;
    
    private Date date;

    /**
     * @return the event
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    

}
