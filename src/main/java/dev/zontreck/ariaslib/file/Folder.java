package dev.zontreck.ariaslib.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Folder
{

    public static void add(Entry e, Entry item)
    {
        ((Entry<List<Entry>>)e).value.add(item);
    }

    public static void remove(Entry e, Entry item)
    {
        ((Entry<List<Entry>>)e).value.remove(item);
    }

    public static Entry getEntry(Entry item, String name)
    {
        List<Entry> ret = ((Entry<List<Entry>>)item).value;
        if(ret.size()>0)
        {
            for (Entry ent :
                    ret) {
                if(ent.name.equals(name))return ent;
            }
        }
        return null;
    }

    public static int size(Entry e)
    {
        return ((Entry<List<Entry>>)e).value.size();
    }

    public static Entry<List<Entry>> getNew(String name)
    {
        return new Entry<>(new ArrayList<Entry>(), name);
    }
}
