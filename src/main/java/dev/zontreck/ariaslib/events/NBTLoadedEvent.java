package dev.zontreck.ariaslib.events;

import java.io.File;

import dev.zontreck.ariaslib.nbt.CompoundTag;

/**
 * This event cannot be cancelled.
 */
public class NBTLoadedEvent extends Event
{
    public CompoundTag loadedTag;
    public String fileName;

    @Override
    public boolean isCancellable() {
        return false;
    }

    public NBTLoadedEvent(CompoundTag tag, String fileName)
    {
        loadedTag=tag;
        this.fileName = fileName;
    }
        
}
