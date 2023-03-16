package dev.zontreck.ariaslib.nbt.old;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LongArrayTag implements Tag
{
    public static final TagType<LongArrayTag> TYPE = new TagType<LongArrayTag>() {

        @Override
        public LongArrayTag load(DataInput input) throws IOException {
            List<Long> lst = new ArrayList<>();
            int count = input.readInt();
            while(count>0)
            {
                lst.add(input.readLong());
                count--;
            }

            return new LongArrayTag(toArray(lst));
        }

        @Override
        public void skip(DataInput input) throws IOException {
            load(input);
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Long_Array";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Long_Array";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);
        for (long b : value) {
            output.writeLong(b);
        }
    }

    @Override
    public int getId() {
        return TAG_LONG_ARRAY;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new LongArrayTag(value);
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    @Override
    public String getAsString(int indents) {

        String indent = makeIndent(indents);
        String indentInside = makeIndent(indents+1);

        String ret = "\n" + indent + "[\n";
        for (int i = 0; i< value.length; i++)
        {
            long b = value[i];
            ret += String.valueOf(b);

            if((i+1) != value.length)
            {
                ret += ", ";
            }

            ret += "\n";

        }

        ret +=indent+ "]";
        return ret;
    }

    private LongArrayTag(long[] value)
    {
        this.value=value;
    }
    private static long[] toArray(List<Long> entries)
    {
        long[] ret = new long[entries.size()];
        int cur=0;
        for(long b : entries)
        {
            ret[cur] = b;
            cur++;
        }

        return ret;
    }

    public static LongArrayTag valueOf(long[] b)
    {
        return new LongArrayTag(b);
    }
    private long[] value;

    @Override
    public Byte asByte() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asByte'");
    }

    @Override
    public Float asFloat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asFloat'");
    }

    @Override
    public String asString() {
        // TODO Auto-generated method stub
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
        return value;
    }


    public Tag parent;
    @Override
    public void setParent(Tag parent) {
        this.parent=parent;
    }

    @Override
    public Tag getParent() {
        return parent;
    }
    
}
