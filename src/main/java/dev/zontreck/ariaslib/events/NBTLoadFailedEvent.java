package dev.zontreck.ariaslib.events;

public class NBTLoadFailedEvent extends Event
{
    public String fileName;

    @Override
    public boolean isCancellable() {
        return false;
    }

    public NBTLoadFailedEvent(String fileName)
    {
        this.fileName=fileName;
    }
        
}
