// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: limit.proto

package cn.mayu.yugioh.common.dto.sync.home;

public final class LimitProto {
  private LimitProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LimitEntityOrBuilder extends
      // @@protoc_insertion_point(interface_extends:cn.mayu.yugioh.common.dto.sync.home.LimitEntity)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string name = 1;</code>
     */
    boolean hasName();
    /**
     * <code>optional string name = 1;</code>
     */
    java.lang.String getName();
    /**
     * <code>optional string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>repeated string forbidden = 2;</code>
     */
    com.google.protobuf.ProtocolStringList
        getForbiddenList();
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    int getForbiddenCount();
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    java.lang.String getForbidden(int index);
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    com.google.protobuf.ByteString
        getForbiddenBytes(int index);

    /**
     * <code>repeated string limited = 3;</code>
     */
    com.google.protobuf.ProtocolStringList
        getLimitedList();
    /**
     * <code>repeated string limited = 3;</code>
     */
    int getLimitedCount();
    /**
     * <code>repeated string limited = 3;</code>
     */
    java.lang.String getLimited(int index);
    /**
     * <code>repeated string limited = 3;</code>
     */
    com.google.protobuf.ByteString
        getLimitedBytes(int index);

    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    com.google.protobuf.ProtocolStringList
        getSemiLimitedList();
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    int getSemiLimitedCount();
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    java.lang.String getSemiLimited(int index);
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    com.google.protobuf.ByteString
        getSemiLimitedBytes(int index);
  }
  /**
   * Protobuf type {@code cn.mayu.yugioh.common.dto.sync.home.LimitEntity}
   */
  public static final class LimitEntity extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:cn.mayu.yugioh.common.dto.sync.home.LimitEntity)
      LimitEntityOrBuilder {
    // Use LimitEntity.newBuilder() to construct.
    private LimitEntity(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LimitEntity(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LimitEntity defaultInstance;
    public static LimitEntity getDefaultInstance() {
      return defaultInstance;
    }

    public LimitEntity getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LimitEntity(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              name_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                forbidden_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000002;
              }
              forbidden_.add(bs);
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                limited_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000004;
              }
              limited_.add(bs);
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
                semiLimited_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000008;
              }
              semiLimited_.add(bs);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          forbidden_ = forbidden_.getUnmodifiableView();
        }
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          limited_ = limited_.getUnmodifiableView();
        }
        if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
          semiLimited_ = semiLimited_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cn.mayu.yugioh.common.dto.sync.home.LimitProto.internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cn.mayu.yugioh.common.dto.sync.home.LimitProto.internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.class, cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.Builder.class);
    }

    public static com.google.protobuf.Parser<LimitEntity> PARSER =
        new com.google.protobuf.AbstractParser<LimitEntity>() {
      public LimitEntity parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LimitEntity(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LimitEntity> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private java.lang.Object name_;
    /**
     * <code>optional string name = 1;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FORBIDDEN_FIELD_NUMBER = 2;
    private com.google.protobuf.LazyStringList forbidden_;
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getForbiddenList() {
      return forbidden_;
    }
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    public int getForbiddenCount() {
      return forbidden_.size();
    }
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    public java.lang.String getForbidden(int index) {
      return forbidden_.get(index);
    }
    /**
     * <code>repeated string forbidden = 2;</code>
     */
    public com.google.protobuf.ByteString
        getForbiddenBytes(int index) {
      return forbidden_.getByteString(index);
    }

    public static final int LIMITED_FIELD_NUMBER = 3;
    private com.google.protobuf.LazyStringList limited_;
    /**
     * <code>repeated string limited = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getLimitedList() {
      return limited_;
    }
    /**
     * <code>repeated string limited = 3;</code>
     */
    public int getLimitedCount() {
      return limited_.size();
    }
    /**
     * <code>repeated string limited = 3;</code>
     */
    public java.lang.String getLimited(int index) {
      return limited_.get(index);
    }
    /**
     * <code>repeated string limited = 3;</code>
     */
    public com.google.protobuf.ByteString
        getLimitedBytes(int index) {
      return limited_.getByteString(index);
    }

    public static final int SEMILIMITED_FIELD_NUMBER = 4;
    private com.google.protobuf.LazyStringList semiLimited_;
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getSemiLimitedList() {
      return semiLimited_;
    }
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    public int getSemiLimitedCount() {
      return semiLimited_.size();
    }
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    public java.lang.String getSemiLimited(int index) {
      return semiLimited_.get(index);
    }
    /**
     * <code>repeated string semiLimited = 4;</code>
     */
    public com.google.protobuf.ByteString
        getSemiLimitedBytes(int index) {
      return semiLimited_.getByteString(index);
    }

    private void initFields() {
      name_ = "";
      forbidden_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      limited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      semiLimited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getNameBytes());
      }
      for (int i = 0; i < forbidden_.size(); i++) {
        output.writeBytes(2, forbidden_.getByteString(i));
      }
      for (int i = 0; i < limited_.size(); i++) {
        output.writeBytes(3, limited_.getByteString(i));
      }
      for (int i = 0; i < semiLimited_.size(); i++) {
        output.writeBytes(4, semiLimited_.getByteString(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getNameBytes());
      }
      {
        int dataSize = 0;
        for (int i = 0; i < forbidden_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(forbidden_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getForbiddenList().size();
      }
      {
        int dataSize = 0;
        for (int i = 0; i < limited_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(limited_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getLimitedList().size();
      }
      {
        int dataSize = 0;
        for (int i = 0; i < semiLimited_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(semiLimited_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getSemiLimitedList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code cn.mayu.yugioh.common.dto.sync.home.LimitEntity}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:cn.mayu.yugioh.common.dto.sync.home.LimitEntity)
        cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntityOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return cn.mayu.yugioh.common.dto.sync.home.LimitProto.internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return cn.mayu.yugioh.common.dto.sync.home.LimitProto.internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.class, cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.Builder.class);
      }

      // Construct using cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        forbidden_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        limited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        semiLimited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return cn.mayu.yugioh.common.dto.sync.home.LimitProto.internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_descriptor;
      }

      public cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity getDefaultInstanceForType() {
        return cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.getDefaultInstance();
      }

      public cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity build() {
        cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity buildPartial() {
        cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity result = new cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.name_ = name_;
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          forbidden_ = forbidden_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.forbidden_ = forbidden_;
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          limited_ = limited_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.limited_ = limited_;
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          semiLimited_ = semiLimited_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.semiLimited_ = semiLimited_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity) {
          return mergeFrom((cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity other) {
        if (other == cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity.getDefaultInstance()) return this;
        if (other.hasName()) {
          bitField0_ |= 0x00000001;
          name_ = other.name_;
          onChanged();
        }
        if (!other.forbidden_.isEmpty()) {
          if (forbidden_.isEmpty()) {
            forbidden_ = other.forbidden_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureForbiddenIsMutable();
            forbidden_.addAll(other.forbidden_);
          }
          onChanged();
        }
        if (!other.limited_.isEmpty()) {
          if (limited_.isEmpty()) {
            limited_ = other.limited_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureLimitedIsMutable();
            limited_.addAll(other.limited_);
          }
          onChanged();
        }
        if (!other.semiLimited_.isEmpty()) {
          if (semiLimited_.isEmpty()) {
            semiLimited_ = other.semiLimited_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureSemiLimitedIsMutable();
            semiLimited_.addAll(other.semiLimited_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object name_ = "";
      /**
       * <code>optional string name = 1;</code>
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            name_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList forbidden_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureForbiddenIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          forbidden_ = new com.google.protobuf.LazyStringArrayList(forbidden_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getForbiddenList() {
        return forbidden_.getUnmodifiableView();
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public int getForbiddenCount() {
        return forbidden_.size();
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public java.lang.String getForbidden(int index) {
        return forbidden_.get(index);
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public com.google.protobuf.ByteString
          getForbiddenBytes(int index) {
        return forbidden_.getByteString(index);
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public Builder setForbidden(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureForbiddenIsMutable();
        forbidden_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public Builder addForbidden(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureForbiddenIsMutable();
        forbidden_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public Builder addAllForbidden(
          java.lang.Iterable<java.lang.String> values) {
        ensureForbiddenIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, forbidden_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public Builder clearForbidden() {
        forbidden_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string forbidden = 2;</code>
       */
      public Builder addForbiddenBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureForbiddenIsMutable();
        forbidden_.add(value);
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList limited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureLimitedIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          limited_ = new com.google.protobuf.LazyStringArrayList(limited_);
          bitField0_ |= 0x00000004;
         }
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getLimitedList() {
        return limited_.getUnmodifiableView();
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public int getLimitedCount() {
        return limited_.size();
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public java.lang.String getLimited(int index) {
        return limited_.get(index);
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public com.google.protobuf.ByteString
          getLimitedBytes(int index) {
        return limited_.getByteString(index);
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public Builder setLimited(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureLimitedIsMutable();
        limited_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public Builder addLimited(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureLimitedIsMutable();
        limited_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public Builder addAllLimited(
          java.lang.Iterable<java.lang.String> values) {
        ensureLimitedIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, limited_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public Builder clearLimited() {
        limited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string limited = 3;</code>
       */
      public Builder addLimitedBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureLimitedIsMutable();
        limited_.add(value);
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList semiLimited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureSemiLimitedIsMutable() {
        if (!((bitField0_ & 0x00000008) == 0x00000008)) {
          semiLimited_ = new com.google.protobuf.LazyStringArrayList(semiLimited_);
          bitField0_ |= 0x00000008;
         }
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getSemiLimitedList() {
        return semiLimited_.getUnmodifiableView();
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public int getSemiLimitedCount() {
        return semiLimited_.size();
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public java.lang.String getSemiLimited(int index) {
        return semiLimited_.get(index);
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public com.google.protobuf.ByteString
          getSemiLimitedBytes(int index) {
        return semiLimited_.getByteString(index);
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public Builder setSemiLimited(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSemiLimitedIsMutable();
        semiLimited_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public Builder addSemiLimited(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSemiLimitedIsMutable();
        semiLimited_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public Builder addAllSemiLimited(
          java.lang.Iterable<java.lang.String> values) {
        ensureSemiLimitedIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, semiLimited_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public Builder clearSemiLimited() {
        semiLimited_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string semiLimited = 4;</code>
       */
      public Builder addSemiLimitedBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSemiLimitedIsMutable();
        semiLimited_.add(value);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:cn.mayu.yugioh.common.dto.sync.home.LimitEntity)
    }

    static {
      defaultInstance = new LimitEntity(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:cn.mayu.yugioh.common.dto.sync.home.LimitEntity)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013limit.proto\022\037cn.mayu.yugioh.facade.syn" +
      "c.home\"T\n\013LimitEntity\022\014\n\004name\030\001 \001(\t\022\021\n\tf" +
      "orbidden\030\002 \003(\t\022\017\n\007limited\030\003 \003(\t\022\023\n\013semiL" +
      "imited\030\004 \003(\tB\014B\nLimitProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_cn_mayu_yugioh_facade_sync_home_LimitEntity_descriptor,
        new java.lang.String[] { "Name", "Forbidden", "Limited", "SemiLimited", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
