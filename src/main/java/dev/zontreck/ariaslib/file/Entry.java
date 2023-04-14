package dev.zontreck.ariaslib.file;

import dev.zontreck.ariaslib.terminal.Terminal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * An entry in the serialized file
 */
public class Entry<K>
{
    public static final byte YES = 1;
    public static final byte NO = 0;

    public EntryType type;
    public String name;
    public K value;

    public Entry(K v, String name)
    {
        value=v;
        this.name=name;
        if(v instanceof String) {
            type = EntryType.STRING;
        }else if(v instanceof Integer)
        {
            type=EntryType.INT;
        } else if(v instanceof List<?>)
        {
            type = EntryType.FOLDER;
        } else if(v instanceof Boolean)
        {
            type = EntryType.BOOL;
        } else if(v instanceof Long)
        {
            type = EntryType.LONG;
        }else if(v instanceof Short)
        {
            type = EntryType.SHORT;
        }else if(v instanceof Byte)
        {
            type = EntryType.BYTE;
        }else if(v instanceof Double)
        {
            type = EntryType.DOUBLE;
        }else if(v instanceof Float)
        {
            type = EntryType.FLOAT;
        }else if(v instanceof int[]){
            type = EntryType.INT_ARRAY;
        }else if(v instanceof String[])
        {
            type = EntryType.STRING_ARRAY;
        }else if(v instanceof byte[])
        {
            type = EntryType.BYTE_ARRAY;
        }else if(v instanceof long[])
        {
            type = EntryType.LONG_ARRAY;
        }

        else{
            type = EntryType.INVALID;
        }
    }

    private Entry(){}


    public void write(DataOutputStream dos) throws IOException {

        dos.writeByte((int)type.value);
        byte[] nameBytes = name.getBytes();
        dos.writeInt(nameBytes.length);
        dos.write(nameBytes);

        switch(type)
        {
            case FOLDER:
            {
                List<Entry<?>> entries = (List<Entry<?>>) value;
                dos.writeInt(entries.size());
                for (Entry<?> x:
                     entries) {
                    x.write(dos);
                }

                break;
            }
            case STRING:
            {
                String s = (String)value;
                byte[] bS = s.getBytes();
                dos.writeInt(bS.length);
                dos.write(bS);

                break;
            }
            case INT:
            {
                dos.writeInt((int)value);

                break;
            }
            case BOOL:
            {
                boolean X = (boolean)value;
                if(X) dos.writeByte(YES);
                else dos.writeByte(NO);

                break;
            }
            case LONG:
            {
                long lng = (long)value;
                dos.writeLong(lng);

                break;
            }
            case SHORT:
            {
                short s = (short)value;
                dos.writeShort(s);

                break;
            }
            case BYTE:
            {
                dos.write((byte)value);
                break;
            }
            case DOUBLE:
            {
                dos.writeDouble((double)value);
                break;
            }
            case FLOAT:
            {
                dos.writeFloat((float)value);
                break;
            }
            case INT_ARRAY:
            {
                int[] arr = (int[])value;
                dos.writeInt(arr.length);
                for (int x: arr
                     ) {
                    dos.writeInt(x);
                }
                break;
            }
            case STRING_ARRAY:
            {
                String[] arr = (String[]) value;
                dos.writeInt(arr.length);
                for(String s : arr)
                {
                    byte[] bArr = s.getBytes();
                    dos.writeInt(bArr.length);
                    dos.write(bArr);
                }
                break;
            }
            case BYTE_ARRAY:
            {
                byte[] arr = (byte[])value;
                dos.writeInt(arr.length);
                for(byte b : arr)
                {
                    dos.write(b);
                }
                break;
            }
            case LONG_ARRAY:
            {
                long[] arr = (long[]) value;
                dos.writeInt(arr.length);
                for(long L : arr)
                {
                    dos.writeLong(L);
                }

                break;
            }
        }
    }

    public static Entry read(DataInputStream dis) throws IOException {
        EntryType et = EntryType.of(dis.readByte());
        int nameLen = dis.readInt();
        byte[] nm = dis.readNBytes(nameLen);
        Entry work = new Entry<>();
        work.type = et;
        work.name = new String(nm);
        System.out.println("Read start: " + work.name + " [ " + work.type.toString() + " ]");

        switch(et)
        {
            case FOLDER:
            {
                Entry<List<Entry>> entries = (Entry<List<Entry>>) work;
                entries.value = new ArrayList<>();

                int numEntries = dis.readInt();
                for(int i = 0; i<numEntries; i++)
                {
                    entries.value.add(Entry.read(dis));
                }

                break;
            }
            case STRING:
            {
                Entry<String> w = (Entry<String>) work;
                int vLen = dis.readInt();
                w.value = new String(dis.readNBytes(vLen));
                break;
            }
            case INT:
            {
                Entry<Integer> w = (Entry<Integer>) work;
                w.value = dis.readInt();
                break;
            }
            case BOOL:
            {
                Entry<Boolean> w = (Entry<Boolean>) work;
                byte b = dis.readByte();
                if(b == YES)w.value = true;
                else w.value=false;

                break;
            }
            case LONG:
            {
                Entry<Long> w = (Entry<Long>) work;
                w.value = dis.readLong();

                break;
            }
            case SHORT:
            {
                Entry<Short> w = (Entry<Short>) work;
                w.value = dis.readShort();

                break;
            }

            case BYTE:
            {
                Entry<Byte> w = (Entry<Byte>) work;
                w.value = dis.readByte();
                break;
            }
            case DOUBLE:
            {
                Entry<Double> w = (Entry<Double>) work;
                w.value = dis.readDouble();
                break;
            }
            case FLOAT:
            {
                Entry<Float> w = (Entry<Float>) work;
                w.value = dis.readFloat();
                break;
            }
            case INT_ARRAY:
            {
                Entry<int[]> w = (Entry<int[]>) work;
                int num = dis.readInt();
                w.value = new int[num];

                for(int i=0;i<num;i++)
                {
                    w.value[i] = dis.readInt();
                }
                break;
            }
            case STRING_ARRAY:
            {
                Entry<String[]> w = (Entry<String[]>) work;
                int num = dis.readInt();
                w.value = new String[num];
                for(int i=0;i<num;i++)
                {
                    int len = dis.readInt();
                    byte[] bStr = dis.readNBytes(len);
                    w.value[i] = new String(bStr);
                }
                break;
            }
            case BYTE_ARRAY:
            {
                Entry<byte[]> w = (Entry<byte[]>) work;
                int num = dis.readInt();
                w.value = new byte[num];
                for(int i=0;i<num;i++)
                {
                    w.value[i] = dis.readByte();
                }
                break;
            }
            case LONG_ARRAY:
            {
                Entry<long[]> w = (Entry<long[]>) work;
                int num = dis.readInt();
                w.value = new long[num];
                for(int i=0;i<num;i++)
                {
                    w.value[i] = dis.readLong();
                }
                break;
            }
        }

        System.out.println("Read finished: " + work.name + " [ " + work.type.toString() + " ]");

        return work;
    }

}