package com.github.agadar.nsapi.event;

import java.util.EventObject;

/**
 * Event fired when a telegram has been sent.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class TelegramSentEvent extends EventObject
{
    /** Name of the nation the telegram was sent to. */
    public final String Addressee;
    
    /** Whether or not the telegram was succesfully queued. */
    public final boolean Queued;
    
    /** 
     * The position of the sent telegram in the query. 
     * Starts at 0 and increments by 1 for each telegram sent. 
     */
    public final int PositionInQuery;
    
    /**
     * Constructs a new TelegramSentEvent.
     * 
     * @param source the object that fired the event
     * @param addressee the name of the nation the telegram was sent to
     * @param queued whether or not the telegram was succesfully queued
     * @param positionInQuery the position of the sent telegram in the query
     */
    public TelegramSentEvent(Object source, String addressee, boolean queued, int positionInQuery)
    {
        super(source);
        this.Addressee = addressee;
        this.Queued = queued;
        this.PositionInQuery = positionInQuery;
    }
}
