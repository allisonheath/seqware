package com.github.seqware.queryengine.model;

import com.github.seqware.queryengine.factory.CreateUpdateManager;
import com.github.seqware.queryengine.model.impl.AtomImpl;
import com.github.seqware.queryengine.model.interfaces.BaseBuilder;
import com.github.seqware.queryengine.util.InMemoryIterable;
import com.github.seqware.queryengine.util.LazyReference;
import com.github.seqware.queryengine.util.SGID;
import com.github.seqware.queryengine.util.SeqWareIterable;
import java.util.ArrayList;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A Tag an instance of a TagSpec which contains all three of the values
 * necessary for a GVF (ex: ID=ID_1 or Variant_seq=A,G or Reference_seq=G ).
 *
 * Tags always have subjects, they have a default predicate "=" and they may or
 * may not have objects. A non-specified value is represented as null
 *
 * Tags themselves are immutable (and thus do not need {@link Versionable} but
 * adding and removing tags on objects is of course possible given the right
 * permissions
 *
 * @author dyuen
 */
public class Tag extends AtomImpl<Tag> {

    public final static String prefix = "Tag";
    private LazyReference<TagSpecSet> tagSet = new LazyReference<TagSpecSet>(TagSpecSet.class);
    private String key = null;
    private String predicate = "=";
    private Object value = null;
    private ValueType vType = null;

    public enum ValueType {

        STRING, BYTEARR, SGID, FLOAT, DOUBLE, LONG, INTEGER
    };

    /**
     * Create a new tag
     */
    private Tag() {
        super();
        vType = ValueType.STRING;
    }

    /**
     * Get the key. Examples include ID, Variant_seq, or Reference_seq
     *
     * @return String key
     */
    public String getKey() {
        return key;
    }

    /**
     * Get a reference to the parent TagSpecSet
     *
     * @return parent TagSpecSet
     */
    public TagSpecSet getTagSet() {
        return tagSet.get();
    }
    
    /**
     * Get a reference to the parent TagSpecSet
     *
     * @return parent TagSpecSet
     */
    public SGID getTagSetSGID() {
        return tagSet.getSGID();
    }

    /**
     * Get the value. Examples include ID_1, A, or G
     *
     * @return String value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Get the predicate. Examples include "="
     *
     * @return String predicate
     */
    public String getPredicate() {
        return predicate;
    }

    /**
     * Set the TagSpecSet for this particular tag, should not be called outside
     * of the back-end. This is used primary to keep track of which TagSet a tag 
     * came from. 
     * @param sgid
     * @return 
     */
    public Tag setTagSpecSet(SGID sgid) {
        this.tagSet.setSGID(sgid);
        return this;
    }
    
    /**
     * Set the TagSpecSet for this particular tag, should not be called outside
     * of the back-end. This is used primary to keep track of which TagSet a tag 
     * came from. 
     * @param sgid
     * @return 
     */
    public Tag setTagSpecSet(TagSpecSet set) {
        this.tagSet.set(set);
        return this;
    }

    /**
     * Get the type of value for the tag value
     *
     * @return
     */
    public ValueType getvType() {
        return vType;
    }

    @Override
    public Class getHBaseClass() {
        return Tag.class;
    }

    @Override
    public String getHBasePrefix() {
        return Tag.prefix;
    }

    @Override
    public boolean equals(Object obj) {
        // will cause recursion
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Create a new ACL builder
     *
     * @return
     */
    public static Tag.Builder newBuilder() {
        return new Tag.Builder();
    }

    /**
     * Create an ACL builder started with a copy of this
     *
     * @return
     */
    @Override
    public Tag.Builder toBuilder() {
        Tag.Builder b = new Tag.Builder();
        b.tag = (Tag) this.copy(true);
        return b;
    }

    /**
     * Tags are not taggable, but it is actually easier to have the capability
     * via inheritance and just turn it off
     *
     * @param tag new tag to associate with this
     * @return whether the subject was successfully tagged
     */
    @Override
    public boolean associateTag(Tag tag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Tags are not taggable, but it is actually easier to have the capability
     * via inheritance and just turn it off
     *
     * @param tag tag to be removed
     * @return whether the tag was successfully removed
     */
    @Override
    public boolean dissociateTag(Tag tag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Tags are not taggable, but it is actually easier to have the capability
     * via inheritance and just turn it off
     *
     * @return Iterable of tags
     */
    @Override
    public SeqWareIterable<Tag> getTags() {
        return new InMemoryIterable(new ArrayList<Tag>());
    }

    public static class Builder implements BaseBuilder {

        private Tag tag = new Tag();

        /**
         * '
         * Should not be called outside of the back-end
         *
         * @param key
         * @return
         */
        public Tag.Builder setKey(String key) {
            assert (tag.key == null);
            tag.key = key;
            return this;
        }

        public Tag.Builder setPredicate(String predicate) {
            tag.predicate = predicate;
            return this;
        }

        public Tag.Builder setTagSpecSet(TagSpecSet set) {
            this.tag.tagSet.set(set);
            return this;
        }

        public Tag.Builder setTagSpecSet(SGID sgid) {
            this.tag.tagSet.setSGID(sgid);
            return this;
        }

        /**
         * Set the value to one of ValueType
         *
         * @param value
         * @return
         */
        public Tag.Builder setValue(Object value) {
            tag.value = value;
            if (value instanceof byte[]) {
                tag.vType = ValueType.BYTEARR;
            } else if (value instanceof Double) {
                tag.vType = ValueType.DOUBLE;
            } else if (value instanceof Float) {
                tag.vType = ValueType.FLOAT;
            } else if (value instanceof Integer) {
                tag.vType = ValueType.INTEGER;
            } else if (value instanceof Long) {
                tag.vType = ValueType.LONG;
            } else if (value instanceof SGID) {
                tag.vType = ValueType.SGID;
            } else if (value instanceof String) {
                tag.vType = ValueType.STRING;
            }
            return this;
        }

        @Override
        public Tag build() {
            if (tag.key == null) {
                throw new RuntimeException("Invalid build of Tag");
            }
            if (tag.getManager() != null) {
                tag.getManager().objectCreated(tag);
            }
            return tag;
        }

        @Override
        public Builder setManager(CreateUpdateManager aThis) {
            tag.setManager(aThis);
            return this;
        }
    }
}