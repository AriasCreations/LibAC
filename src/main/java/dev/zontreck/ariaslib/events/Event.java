package dev.zontreck.ariaslib.events;


public abstract class Event {
    private boolean isCancelled=false;
    /**
     * True is the event ended early due to a unhandled exception.
     *
     * The event bus will continue processing.
     */
    public boolean exceptionThrown = false;


    public Event()
    {
    }

    public abstract boolean isCancellable();

    public boolean isCancelled()
    {
        return isCancelled;
    }

    public void setCancelled(boolean cancel)
    {
        if(!isCancellable())
        {
            return;
        }
        isCancelled=cancel;
    }
}
