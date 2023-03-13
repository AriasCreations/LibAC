package dev.zontreck.ariaslib.events;


public abstract class Event {
    private boolean isCancelled=false;

    public Event()
    {
    }

    public abstract boolean isCancellable();

    public boolean isCancelled()
    {
        return isCancelled;
    }

    public void setCancelled(boolean cancel) throws Exception
    {
        if(!isCancellable())
        {
            throw new Exception("This event cannot be cancelled");
        }
        isCancelled=cancel;
    }
}
