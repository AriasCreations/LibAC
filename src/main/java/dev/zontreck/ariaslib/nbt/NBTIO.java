package dev.zontreck.ariaslib.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import dev.zontreck.ariaslib.events.EventBus;
import dev.zontreck.ariaslib.events.NBTLoadFailedEvent;
import dev.zontreck.ariaslib.events.NBTLoadedEvent;
import dev.zontreck.ariaslib.events.NBTSavingEvent;

public class NBTIO {
    public static CompoundTag read(File file) throws FileNotFoundException, IOException
    {
        try{

            FileInputStream fis = new FileInputStream(file);
            CompoundTag tag;
    
            DataInputStream dis = new DataInputStream(fis);
            // Start loading
            
            // We can only be a compound tag
            dis.skipBytes(1); // Skip the compound tag type bit
            String name = dis.readUTF();
            tag = (CompoundTag) TagTypes.Compound.type.load(dis);
            tag.setName(name);
    
            EventBus.BUS.post(new NBTLoadedEvent(tag, file.getName()));
    
            // Complete
            return tag;
        }catch(Exception e) {
            EventBus.BUS.post(new NBTLoadFailedEvent(file.getName()));
            return new CompoundTag();
        }
        
        
    }

    public static void write(File file, CompoundTag tag) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);

        NBTSavingEvent event = new NBTSavingEvent(tag, file.getName());
        if(EventBus.BUS.post(event)){
            tag=event.tag;
        }

        // Write a unnamed tag
        dos.writeByte(Tag.TAG_COMPOUND);
        tag.write(dos);

        dos.flush();
        fos.close();
    }
}
