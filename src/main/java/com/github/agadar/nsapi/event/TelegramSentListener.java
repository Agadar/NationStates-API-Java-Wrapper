package com.github.agadar.nsapi.event;

/**
 * Listener for telegram sent events.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public interface TelegramSentListener
{
    /**
     * Handles the event.
     * 
     * @param event the telegram sent event
     */
    void handleTelegramSent(TelegramSentEvent event);
}
