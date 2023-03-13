package dev.zontreck.ariaslib.events;

import dev.zontreck.ariaslib.nbt.CompoundTag;

/**
 * This event is cancellable. Cancelling this event will result in the saving process being interrupted, and the tag to be saved, replaced right before saving completes
 */
public class NBTSavingEvent extends Event
{
    public CompoundTag tag;
    public String file;

    @Override
    public boolean isCancellable() {
        return true;
    }

    public NBTSavingEvent(CompoundTag current, String fileName)
    {
        tag=current;
        file=fileName;
    }
    
}
