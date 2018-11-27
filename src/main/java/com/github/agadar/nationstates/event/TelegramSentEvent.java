package com.github.agadar.nationstates.event;

import java.util.EventObject;

import lombok.Getter;

/**
 * Event fired when a telegram has been sent.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
public class TelegramSentEvent extends EventObject {

    private static final long serialVersionUID = -4487722764290395846L;

    /**
     * Name of the nation the telegram was sent to.
     */
    private final String recipient;

    /**
     * Whether or not the telegram was successfully queued.
     */
    private final boolean queued;

    /**
     * Contains a message elaborating on why the telegram failed to be sent, if
     * applicable.
     */
    private final String errorMessage;

    /**
     * The position of the sent telegram in the query. Starts at 0 and increments by
     * 1 for each telegram sent.
     */
    private final int positionInQuery;

    /**
     * Constructs a new TelegramSentEvent.
     *
     * @param source          the object that fired the event
     * @param recipient       the name of the nation the telegram was sent to
     * @param queued          whether or not the telegram was successfully queued
     * @param errorMessage    a message elaborating on why the telegram failed to be
     *                        sent, if applicable
     * @param positionInQuery the position of the sent telegram in the query
     */
    public TelegramSentEvent(Object source, String recipient, boolean queued, String errorMessage,
            int positionInQuery) {
        super(source);
        this.recipient = recipient;
        this.queued = queued;
        this.errorMessage = errorMessage;
        this.positionInQuery = positionInQuery;
    }
}
