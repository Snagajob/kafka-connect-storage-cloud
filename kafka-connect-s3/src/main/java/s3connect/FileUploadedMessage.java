/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package s3connect;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class FileUploadedMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6532990973054494947L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FileUploadedMessage\",\"namespace\":\"s3connect\",\"fields\":[{\"name\":\"topic\",\"type\":\"string\"},{\"name\":\"bucket\",\"type\":\"string\"},{\"name\":\"key\",\"type\":\"string\"},{\"name\":\"partition\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence topic;
  @Deprecated public java.lang.CharSequence bucket;
  @Deprecated public java.lang.CharSequence key;
  @Deprecated public java.lang.CharSequence partition;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FileUploadedMessage() {}

  /**
   * All-args constructor.
   * @param topic The new value for topic
   * @param bucket The new value for bucket
   * @param key The new value for key
   * @param partition The new value for partition
   */
  public FileUploadedMessage(java.lang.CharSequence topic, java.lang.CharSequence bucket, java.lang.CharSequence key, java.lang.CharSequence partition) {
    this.topic = topic;
    this.bucket = bucket;
    this.key = key;
    this.partition = partition;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return topic;
    case 1: return bucket;
    case 2: return key;
    case 3: return partition;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: topic = (java.lang.CharSequence)value$; break;
    case 1: bucket = (java.lang.CharSequence)value$; break;
    case 2: key = (java.lang.CharSequence)value$; break;
    case 3: partition = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'topic' field.
   * @return The value of the 'topic' field.
   */
  public java.lang.CharSequence getTopic() {
    return topic;
  }

  /**
   * Sets the value of the 'topic' field.
   * @param value the value to set.
   */
  public void setTopic(java.lang.CharSequence value) {
    this.topic = value;
  }

  /**
   * Gets the value of the 'bucket' field.
   * @return The value of the 'bucket' field.
   */
  public java.lang.CharSequence getBucket() {
    return bucket;
  }

  /**
   * Sets the value of the 'bucket' field.
   * @param value the value to set.
   */
  public void setBucket(java.lang.CharSequence value) {
    this.bucket = value;
  }

  /**
   * Gets the value of the 'key' field.
   * @return The value of the 'key' field.
   */
  public java.lang.CharSequence getKey() {
    return key;
  }

  /**
   * Sets the value of the 'key' field.
   * @param value the value to set.
   */
  public void setKey(java.lang.CharSequence value) {
    this.key = value;
  }

  /**
   * Gets the value of the 'partition' field.
   * @return The value of the 'partition' field.
   */
  public java.lang.CharSequence getPartition() {
    return partition;
  }

  /**
   * Sets the value of the 'partition' field.
   * @param value the value to set.
   */
  public void setPartition(java.lang.CharSequence value) {
    this.partition = value;
  }

  /**
   * Creates a new FileUploadedMessage RecordBuilder.
   * @return A new FileUploadedMessage RecordBuilder
   */
  public static s3connect.FileUploadedMessage.Builder newBuilder() {
    return new s3connect.FileUploadedMessage.Builder();
  }

  /**
   * Creates a new FileUploadedMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FileUploadedMessage RecordBuilder
   */
  public static s3connect.FileUploadedMessage.Builder newBuilder(s3connect.FileUploadedMessage.Builder other) {
    return new s3connect.FileUploadedMessage.Builder(other);
  }

  /**
   * Creates a new FileUploadedMessage RecordBuilder by copying an existing FileUploadedMessage instance.
   * @param other The existing instance to copy.
   * @return A new FileUploadedMessage RecordBuilder
   */
  public static s3connect.FileUploadedMessage.Builder newBuilder(s3connect.FileUploadedMessage other) {
    return new s3connect.FileUploadedMessage.Builder(other);
  }

  /**
   * RecordBuilder for FileUploadedMessage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FileUploadedMessage>
    implements org.apache.avro.data.RecordBuilder<FileUploadedMessage> {

    private java.lang.CharSequence topic;
    private java.lang.CharSequence bucket;
    private java.lang.CharSequence key;
    private java.lang.CharSequence partition;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(s3connect.FileUploadedMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.topic)) {
        this.topic = data().deepCopy(fields()[0].schema(), other.topic);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bucket)) {
        this.bucket = data().deepCopy(fields()[1].schema(), other.bucket);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.key)) {
        this.key = data().deepCopy(fields()[2].schema(), other.key);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.partition)) {
        this.partition = data().deepCopy(fields()[3].schema(), other.partition);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing FileUploadedMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(s3connect.FileUploadedMessage other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.topic)) {
        this.topic = data().deepCopy(fields()[0].schema(), other.topic);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bucket)) {
        this.bucket = data().deepCopy(fields()[1].schema(), other.bucket);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.key)) {
        this.key = data().deepCopy(fields()[2].schema(), other.key);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.partition)) {
        this.partition = data().deepCopy(fields()[3].schema(), other.partition);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'topic' field.
      * @return The value.
      */
    public java.lang.CharSequence getTopic() {
      return topic;
    }

    /**
      * Sets the value of the 'topic' field.
      * @param value The value of 'topic'.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder setTopic(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.topic = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'topic' field has been set.
      * @return True if the 'topic' field has been set, false otherwise.
      */
    public boolean hasTopic() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'topic' field.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder clearTopic() {
      topic = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'bucket' field.
      * @return The value.
      */
    public java.lang.CharSequence getBucket() {
      return bucket;
    }

    /**
      * Sets the value of the 'bucket' field.
      * @param value The value of 'bucket'.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder setBucket(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.bucket = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'bucket' field has been set.
      * @return True if the 'bucket' field has been set, false otherwise.
      */
    public boolean hasBucket() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'bucket' field.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder clearBucket() {
      bucket = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'key' field.
      * @return The value.
      */
    public java.lang.CharSequence getKey() {
      return key;
    }

    /**
      * Sets the value of the 'key' field.
      * @param value The value of 'key'.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder setKey(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.key = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'key' field has been set.
      * @return True if the 'key' field has been set, false otherwise.
      */
    public boolean hasKey() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'key' field.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder clearKey() {
      key = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'partition' field.
      * @return The value.
      */
    public java.lang.CharSequence getPartition() {
      return partition;
    }

    /**
      * Sets the value of the 'partition' field.
      * @param value The value of 'partition'.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder setPartition(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.partition = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'partition' field has been set.
      * @return True if the 'partition' field has been set, false otherwise.
      */
    public boolean hasPartition() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'partition' field.
      * @return This builder.
      */
    public s3connect.FileUploadedMessage.Builder clearPartition() {
      partition = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public FileUploadedMessage build() {
      try {
        FileUploadedMessage record = new FileUploadedMessage();
        record.topic = fieldSetFlags()[0] ? this.topic : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.bucket = fieldSetFlags()[1] ? this.bucket : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.key = fieldSetFlags()[2] ? this.key : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.partition = fieldSetFlags()[3] ? this.partition : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
