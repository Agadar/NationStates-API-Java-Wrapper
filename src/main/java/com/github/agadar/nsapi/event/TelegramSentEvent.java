package com.github.agadar.nsapi.event;

import java.util.EventObject;

/**
 * Event fired when a telegram has been sent.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public class TelegramSentEvent extends EventObject
{
    /** Name of the nation the telegram was sent to. */
    public final String Addressee;
    
    /** Whether or not the telegram was successfully queued. */
    public final boolean Queued;
    
    /**
     * Contains a message elaborating on why the telegram failed to be sent, if applicable.
     */
    public final String ErrorMessage;
    
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
     * @param queued whether or not the telegram was successfully queued
     * @param errorMessage a message elaborating on why the telegram failed to be sent, if applicable
     * @param positionInQuery the position of the sent telegram in the query
     */
    public TelegramSentEvent(Object source, String addressee, boolean queued, 
            String errorMessage, int positionInQuery)
    {
        super(source);
        this.Addressee = addressee;
        this.Queued = queued;
        this.ErrorMessage = errorMessage;
        this.PositionInQuery = positionInQuery;
    }
}
