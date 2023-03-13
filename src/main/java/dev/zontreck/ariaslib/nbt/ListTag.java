package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ListTag implements Tag
{
    public static final TagType<ListTag> TYPE = new TagType<ListTag>() {

        @Override
        public ListTag load(DataInput input) throws IOException {
            ListTag newTag = new ListTag();
            byte type = input.readByte();
            int count = input.readInt();
            if(type == 0)
            {
                return newTag;
            }
            else{
                // Read the entries
                TagType<?> typ = TagTypes.getType(type);
                while(count>0)
                {
                    Tag entry = typ.load(input);
                    newTag.add(entry);

                    count--;
                }
            }

            return newTag;
        }

        @Override
        public void skip(DataInput input) throws IOException {
            load(input);
        }

        @Override
        public String getName() {
            return "List";
        }

        @Override
        public String getPrettyName() {
            return "TAG_List";
        }

        @Override
        public boolean hasValue() {
            return true;
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        // Nothing to write here! We are the end tag
    }

    @Override
    public int getId() {
        return Tag.TAG_LIST;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return this;
    }

    @Override
    public String getAsString(int indents) {

        String indent = makeIndent(indents);
        String indentInside = makeIndent(indents+1);

        String ret = "\n" + indent +"[\n";
        for (int i = 0; i< list.size(); i++)
        {
            Tag b = list.get(i);
            ret += indentInside+b.getAsString(indents+1);

            if((i+1) != list.size())
            {
                ret += ", ";
            }
            ret += "\n";

        }

        ret +=indent+ "]";
        return ret;
    }

    @Override
    public String toString()
    {
        // Return the values of getAsString
        return getAsString(0);
    }

    private List<Tag> list;
    private byte type = TAG_END;

    public ListTag()
    {
        list = new ArrayList<>();
        type = TAG_END;
    }

    public int size()
    {
        return list.size();
    }

    public boolean add(Tag value)
    {
        if(type == TAG_END || type == (byte)value.getId())
        {
            type = (byte)value.getId();
            list.add(value);

            return true;
        } else return false;
    }

    public Tag get(int num)
    {
        return list.get(num);
    }

    public void remove(int num)
    {
        list.remove(num);
    }

    public int indexOf(Tag tag)
    {
        return list.indexOf(tag);
    }

    public void clear()
    {
        list= Lists.newArrayList();
        type=TAG_END;
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
