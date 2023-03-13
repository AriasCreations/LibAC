package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class CompoundTag implements Tag
{
    public static final TagType<CompoundTag> TYPE = new TagType<CompoundTag>() {

        @Override
        public CompoundTag load(DataInput input) throws IOException {
            CompoundTag newTag = new CompoundTag();
            
            byte type;
            while((type = input.readByte()) != TAG_END)
            {
                TagType<?> v = TagTypes.getType(type);
                String tagName = input.readUTF();
                Tag finalTag = v.load(input);
                newTag.put(tagName, finalTag);
            }

            return newTag;
        }

        @Override
        public void skip(DataInput input) throws IOException {
            load(input);
        }

        @Override
        public String getName() {
            return "Compound";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Compound";
        }

        @Override
        public boolean hasValue() {
            return true;
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        for (Map.Entry<String,Tag> it : list.entrySet()) {
            // We're saving everything now
            output.writeByte(it.getValue().getId());
            output.writeUTF(it.getKey());
            it.getValue().write(output);
        }
        output.writeByte(TAG_END);
    }

    @Override
    public int getId() {
        return Tag.TAG_COMPOUND;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new CompoundTag(new HashMap<>(list));
    }

    @Override
    public String getAsString(int indents) {
        
        String indent = makeIndent(indents);
        String indentInside = makeIndent(indents+1);

        String ret = "\n"+indent+"{\n";
        Iterator<Entry<String,Tag>> entries = list.entrySet().iterator();
        while(entries.hasNext())
        {
            Entry<String,Tag> entry = entries.next();
            ret += indentInside+entry.getValue().getType().getPrettyName() + " ["+entry.getKey()+"]: ";
            ret += entry.getValue().getAsString(indents+1);

            if(entries.hasNext())
            {
                ret+=",";
            }
            ret += "\n";
        }

        ret += indent+"}";
        return ret;
    }

    @Override
    public String toString()
    {
        // Return the values of getAsString
        return getAsString(0);
    }

    private Map<String, Tag> list;

    public CompoundTag()
    {
        list = Maps.newHashMap();
    }

    protected CompoundTag(Map< String, Tag > map)
    {
        list=map;
    }

    public void put(String name, Tag entry)
    {
        list.put(name, entry);
    }

    public void putString(String name, String value)
    {
        list.put(name, StringTag.valueOf(value));
    }

    public String getString(String name)
    {
        return list.get(name).asString();
    }
    public void putByte(String name, byte value)
    {
        list.put(name, ByteTag.valueOf(value));
    }

    public byte getByte(String name)
    {
        return list.get(name).asByte();
    }
    public void putDouble(String name, double value)
    {
        list.put(name, DoubleTag.valueOf(value));
    }

    public double getDouble(String name)
    {
        return list.get(name).asDouble();
    }
    public void putFloat(String name, float value)
    {
        list.put(name, FloatTag.valueOf(value));
    }

    public float getFloat(String name)
    {
        return list.get(name).asFloat();
    }
    public void putInt(String name, int value)
    {
        list.put(name, IntTag.valueOf(value));
    }

    public int getInt(String name)
    {
        return list.get(name).asInt();
    }

    public ListTag getList(String name)
    {
        return (ListTag)list.get(name);
    }
    public void putLong(String name, long value)
    {
        list.put(name, LongTag.valueOf(value));
    }

    public long getLong(String name)
    {
        return list.get(name).asLong();
    }
    public void putShort(String name, short value)
    {
        list.put(name, ShortTag.valueOf(value));
    }

    public short getShort(String name)
    {
        return list.get(name).asShort();
    }
    public void putByteArray(String name, byte[] value)
    {
        list.put(name, ByteArrayTag.valueOf(value));
    }

    public ByteArrayTag getByteArray(String name)
    {
        return (ByteArrayTag)list.get(name);
    }
    public void putIntArray(String name, int[] value)
    {
        list.put(name, IntArrayTag.valueOf(value));
    }

    public IntArrayTag getIntArray(String name)
    {
        return (IntArrayTag)list.get(name);
    }
    public void putLongArray(String name, long[] value)
    {
        list.put(name, LongArrayTag.valueOf(value));
    }

    public LongArrayTag getLongArray(String name)
    {
        return (LongArrayTag)list.get(name);
    }
    public void putCompound(String name, CompoundTag value)
    {
        list.put(name, value);
    }

    public CompoundTag getCompound(String name)
    {
        return (CompoundTag)list.get(name);
    }

    public int size()
    {
        return list.size();
    }

    public void clear()
    {
        list.clear();
    }


    @Override
    public Byte asByte() {
        throw new UnsupportedOperationException("Unimplemented method 'asByte'");
    }

    @Override
    public Float asFloat() {
        throw new UnsupportedOperationException("Unimplemented method 'asFloat'");
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("Unimplemented method 'asString'");
    }

    @Override
    public Integer asInt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asInt'");
    }

    @Override
    public Short asShort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asShort'");
    }

    @Override
    public Long asLong() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asLong'");
    }

    @Override
    public Double asDouble() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asDouble'");
    }

    @Override
    public byte[] asByteArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asByteArray'");
    }

    @Override
    public int[] asIntArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asIntArray'");
    }

    @Override
    public long[] asLongArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asLongArray'");
    }
    
}
