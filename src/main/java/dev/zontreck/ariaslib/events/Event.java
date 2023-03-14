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

    public void setCancelled(boolean cancel)
    {
        if(!isCancellable())
        {
            return;
        }
        isCancelled=cancel;
    }
}
