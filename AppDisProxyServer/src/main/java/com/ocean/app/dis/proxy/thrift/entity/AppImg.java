/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ocean.app.dis.proxy.thrift.entity;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-5-12")
public class AppImg implements org.apache.thrift.TBase<AppImg, AppImg._Fields>, java.io.Serializable, Cloneable, Comparable<AppImg> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AppImg");

  private static final org.apache.thrift.protocol.TField FORMATE_FIELD_DESC = new org.apache.thrift.protocol.TField("formate", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField HEIGHT_FIELD_DESC = new org.apache.thrift.protocol.TField("height", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField WIDTH_FIELD_DESC = new org.apache.thrift.protocol.TField("width", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField SRC_FIELD_DESC = new org.apache.thrift.protocol.TField("src", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ALT_FIELD_DESC = new org.apache.thrift.protocol.TField("alt", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField REF_FIELD_DESC = new org.apache.thrift.protocol.TField("ref", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AppImgStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AppImgTupleSchemeFactory());
  }

  public String formate; // optional
  public int height; // optional
  public int width; // optional
  public String src; // optional
  public String alt; // optional
  public String ref; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FORMATE((short)1, "formate"),
    HEIGHT((short)2, "height"),
    WIDTH((short)3, "width"),
    SRC((short)4, "src"),
    ALT((short)5, "alt"),
    REF((short)6, "ref");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FORMATE
          return FORMATE;
        case 2: // HEIGHT
          return HEIGHT;
        case 3: // WIDTH
          return WIDTH;
        case 4: // SRC
          return SRC;
        case 5: // ALT
          return ALT;
        case 6: // REF
          return REF;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __HEIGHT_ISSET_ID = 0;
  private static final int __WIDTH_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.FORMATE,_Fields.HEIGHT,_Fields.WIDTH,_Fields.SRC,_Fields.ALT,_Fields.REF};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FORMATE, new org.apache.thrift.meta_data.FieldMetaData("formate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HEIGHT, new org.apache.thrift.meta_data.FieldMetaData("height", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.WIDTH, new org.apache.thrift.meta_data.FieldMetaData("width", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SRC, new org.apache.thrift.meta_data.FieldMetaData("src", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ALT, new org.apache.thrift.meta_data.FieldMetaData("alt", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REF, new org.apache.thrift.meta_data.FieldMetaData("ref", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AppImg.class, metaDataMap);
  }

  public AppImg() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AppImg(AppImg other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFormate()) {
      this.formate = other.formate;
    }
    this.height = other.height;
    this.width = other.width;
    if (other.isSetSrc()) {
      this.src = other.src;
    }
    if (other.isSetAlt()) {
      this.alt = other.alt;
    }
    if (other.isSetRef()) {
      this.ref = other.ref;
    }
  }

  public AppImg deepCopy() {
    return new AppImg(this);
  }

  
  public void clear() {
    this.formate = null;
    setHeightIsSet(false);
    this.height = 0;
    setWidthIsSet(false);
    this.width = 0;
    this.src = null;
    this.alt = null;
    this.ref = null;
  }

  public String getFormate() {
    return this.formate;
  }

  public AppImg setFormate(String formate) {
    this.formate = formate;
    return this;
  }

  public void unsetFormate() {
    this.formate = null;
  }

  /** Returns true if field formate is set (has been assigned a value) and false otherwise */
  public boolean isSetFormate() {
    return this.formate != null;
  }

  public void setFormateIsSet(boolean value) {
    if (!value) {
      this.formate = null;
    }
  }

  public int getHeight() {
    return this.height;
  }

  public AppImg setHeight(int height) {
    this.height = height;
    setHeightIsSet(true);
    return this;
  }

  public void unsetHeight() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HEIGHT_ISSET_ID);
  }

  /** Returns true if field height is set (has been assigned a value) and false otherwise */
  public boolean isSetHeight() {
    return EncodingUtils.testBit(__isset_bitfield, __HEIGHT_ISSET_ID);
  }

  public void setHeightIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HEIGHT_ISSET_ID, value);
  }

  public int getWidth() {
    return this.width;
  }

  public AppImg setWidth(int width) {
    this.width = width;
    setWidthIsSet(true);
    return this;
  }

  public void unsetWidth() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WIDTH_ISSET_ID);
  }

  /** Returns true if field width is set (has been assigned a value) and false otherwise */
  public boolean isSetWidth() {
    return EncodingUtils.testBit(__isset_bitfield, __WIDTH_ISSET_ID);
  }

  public void setWidthIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WIDTH_ISSET_ID, value);
  }

  public String getSrc() {
    return this.src;
  }

  public AppImg setSrc(String src) {
    this.src = src;
    return this;
  }

  public void unsetSrc() {
    this.src = null;
  }

  /** Returns true if field src is set (has been assigned a value) and false otherwise */
  public boolean isSetSrc() {
    return this.src != null;
  }

  public void setSrcIsSet(boolean value) {
    if (!value) {
      this.src = null;
    }
  }

  public String getAlt() {
    return this.alt;
  }

  public AppImg setAlt(String alt) {
    this.alt = alt;
    return this;
  }

  public void unsetAlt() {
    this.alt = null;
  }

  /** Returns true if field alt is set (has been assigned a value) and false otherwise */
  public boolean isSetAlt() {
    return this.alt != null;
  }

  public void setAltIsSet(boolean value) {
    if (!value) {
      this.alt = null;
    }
  }

  public String getRef() {
    return this.ref;
  }

  public AppImg setRef(String ref) {
    this.ref = ref;
    return this;
  }

  public void unsetRef() {
    this.ref = null;
  }

  /** Returns true if field ref is set (has been assigned a value) and false otherwise */
  public boolean isSetRef() {
    return this.ref != null;
  }

  public void setRefIsSet(boolean value) {
    if (!value) {
      this.ref = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FORMATE:
      if (value == null) {
        unsetFormate();
      } else {
        setFormate((String)value);
      }
      break;

    case HEIGHT:
      if (value == null) {
        unsetHeight();
      } else {
        setHeight((Integer)value);
      }
      break;

    case WIDTH:
      if (value == null) {
        unsetWidth();
      } else {
        setWidth((Integer)value);
      }
      break;

    case SRC:
      if (value == null) {
        unsetSrc();
      } else {
        setSrc((String)value);
      }
      break;

    case ALT:
      if (value == null) {
        unsetAlt();
      } else {
        setAlt((String)value);
      }
      break;

    case REF:
      if (value == null) {
        unsetRef();
      } else {
        setRef((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FORMATE:
      return getFormate();

    case HEIGHT:
      return Integer.valueOf(getHeight());

    case WIDTH:
      return Integer.valueOf(getWidth());

    case SRC:
      return getSrc();

    case ALT:
      return getAlt();

    case REF:
      return getRef();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FORMATE:
      return isSetFormate();
    case HEIGHT:
      return isSetHeight();
    case WIDTH:
      return isSetWidth();
    case SRC:
      return isSetSrc();
    case ALT:
      return isSetAlt();
    case REF:
      return isSetRef();
    }
    throw new IllegalStateException();
  }

  
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AppImg)
      return this.equals((AppImg)that);
    return false;
  }

  public boolean equals(AppImg that) {
    if (that == null)
      return false;

    boolean this_present_formate = true && this.isSetFormate();
    boolean that_present_formate = true && that.isSetFormate();
    if (this_present_formate || that_present_formate) {
      if (!(this_present_formate && that_present_formate))
        return false;
      if (!this.formate.equals(that.formate))
        return false;
    }

    boolean this_present_height = true && this.isSetHeight();
    boolean that_present_height = true && that.isSetHeight();
    if (this_present_height || that_present_height) {
      if (!(this_present_height && that_present_height))
        return false;
      if (this.height != that.height)
        return false;
    }

    boolean this_present_width = true && this.isSetWidth();
    boolean that_present_width = true && that.isSetWidth();
    if (this_present_width || that_present_width) {
      if (!(this_present_width && that_present_width))
        return false;
      if (this.width != that.width)
        return false;
    }

    boolean this_present_src = true && this.isSetSrc();
    boolean that_present_src = true && that.isSetSrc();
    if (this_present_src || that_present_src) {
      if (!(this_present_src && that_present_src))
        return false;
      if (!this.src.equals(that.src))
        return false;
    }

    boolean this_present_alt = true && this.isSetAlt();
    boolean that_present_alt = true && that.isSetAlt();
    if (this_present_alt || that_present_alt) {
      if (!(this_present_alt && that_present_alt))
        return false;
      if (!this.alt.equals(that.alt))
        return false;
    }

    boolean this_present_ref = true && this.isSetRef();
    boolean that_present_ref = true && that.isSetRef();
    if (this_present_ref || that_present_ref) {
      if (!(this_present_ref && that_present_ref))
        return false;
      if (!this.ref.equals(that.ref))
        return false;
    }

    return true;
  }

  
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_formate = true && (isSetFormate());
    list.add(present_formate);
    if (present_formate)
      list.add(formate);

    boolean present_height = true && (isSetHeight());
    list.add(present_height);
    if (present_height)
      list.add(height);

    boolean present_width = true && (isSetWidth());
    list.add(present_width);
    if (present_width)
      list.add(width);

    boolean present_src = true && (isSetSrc());
    list.add(present_src);
    if (present_src)
      list.add(src);

    boolean present_alt = true && (isSetAlt());
    list.add(present_alt);
    if (present_alt)
      list.add(alt);

    boolean present_ref = true && (isSetRef());
    list.add(present_ref);
    if (present_ref)
      list.add(ref);

    return list.hashCode();
  }

  
  public int compareTo(AppImg other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFormate()).compareTo(other.isSetFormate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFormate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.formate, other.formate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHeight()).compareTo(other.isSetHeight());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeight()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.height, other.height);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWidth()).compareTo(other.isSetWidth());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWidth()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.width, other.width);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSrc()).compareTo(other.isSetSrc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSrc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.src, other.src);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAlt()).compareTo(other.isSetAlt());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAlt()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.alt, other.alt);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRef()).compareTo(other.isSetRef());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRef()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ref, other.ref);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  
  public String toString() {
    StringBuilder sb = new StringBuilder("AppImg(");
    boolean first = true;

    if (isSetFormate()) {
      sb.append("formate:");
      if (this.formate == null) {
        sb.append("null");
      } else {
        sb.append(this.formate);
      }
      first = false;
    }
    if (isSetHeight()) {
      if (!first) sb.append(", ");
      sb.append("height:");
      sb.append(this.height);
      first = false;
    }
    if (isSetWidth()) {
      if (!first) sb.append(", ");
      sb.append("width:");
      sb.append(this.width);
      first = false;
    }
    if (isSetSrc()) {
      if (!first) sb.append(", ");
      sb.append("src:");
      if (this.src == null) {
        sb.append("null");
      } else {
        sb.append(this.src);
      }
      first = false;
    }
    if (isSetAlt()) {
      if (!first) sb.append(", ");
      sb.append("alt:");
      if (this.alt == null) {
        sb.append("null");
      } else {
        sb.append(this.alt);
      }
      first = false;
    }
    if (isSetRef()) {
      if (!first) sb.append(", ");
      sb.append("ref:");
      if (this.ref == null) {
        sb.append("null");
      } else {
        sb.append(this.ref);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AppImgStandardSchemeFactory implements SchemeFactory {
    public AppImgStandardScheme getScheme() {
      return new AppImgStandardScheme();
    }
  }

  private static class AppImgStandardScheme extends StandardScheme<AppImg> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AppImg struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FORMATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.formate = iprot.readString();
              struct.setFormateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HEIGHT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.height = iprot.readI32();
              struct.setHeightIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // WIDTH
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.width = iprot.readI32();
              struct.setWidthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SRC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.src = iprot.readString();
              struct.setSrcIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ALT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.alt = iprot.readString();
              struct.setAltIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // REF
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ref = iprot.readString();
              struct.setRefIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, AppImg struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.formate != null) {
        if (struct.isSetFormate()) {
          oprot.writeFieldBegin(FORMATE_FIELD_DESC);
          oprot.writeString(struct.formate);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetHeight()) {
        oprot.writeFieldBegin(HEIGHT_FIELD_DESC);
        oprot.writeI32(struct.height);
        oprot.writeFieldEnd();
      }
      if (struct.isSetWidth()) {
        oprot.writeFieldBegin(WIDTH_FIELD_DESC);
        oprot.writeI32(struct.width);
        oprot.writeFieldEnd();
      }
      if (struct.src != null) {
        if (struct.isSetSrc()) {
          oprot.writeFieldBegin(SRC_FIELD_DESC);
          oprot.writeString(struct.src);
          oprot.writeFieldEnd();
        }
      }
      if (struct.alt != null) {
        if (struct.isSetAlt()) {
          oprot.writeFieldBegin(ALT_FIELD_DESC);
          oprot.writeString(struct.alt);
          oprot.writeFieldEnd();
        }
      }
      if (struct.ref != null) {
        if (struct.isSetRef()) {
          oprot.writeFieldBegin(REF_FIELD_DESC);
          oprot.writeString(struct.ref);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AppImgTupleSchemeFactory implements SchemeFactory {
    public AppImgTupleScheme getScheme() {
      return new AppImgTupleScheme();
    }
  }

  private static class AppImgTupleScheme extends TupleScheme<AppImg> {

    
    public void write(org.apache.thrift.protocol.TProtocol prot, AppImg struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFormate()) {
        optionals.set(0);
      }
      if (struct.isSetHeight()) {
        optionals.set(1);
      }
      if (struct.isSetWidth()) {
        optionals.set(2);
      }
      if (struct.isSetSrc()) {
        optionals.set(3);
      }
      if (struct.isSetAlt()) {
        optionals.set(4);
      }
      if (struct.isSetRef()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetFormate()) {
        oprot.writeString(struct.formate);
      }
      if (struct.isSetHeight()) {
        oprot.writeI32(struct.height);
      }
      if (struct.isSetWidth()) {
        oprot.writeI32(struct.width);
      }
      if (struct.isSetSrc()) {
        oprot.writeString(struct.src);
      }
      if (struct.isSetAlt()) {
        oprot.writeString(struct.alt);
      }
      if (struct.isSetRef()) {
        oprot.writeString(struct.ref);
      }
    }

    
    public void read(org.apache.thrift.protocol.TProtocol prot, AppImg struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.formate = iprot.readString();
        struct.setFormateIsSet(true);
      }
      if (incoming.get(1)) {
        struct.height = iprot.readI32();
        struct.setHeightIsSet(true);
      }
      if (incoming.get(2)) {
        struct.width = iprot.readI32();
        struct.setWidthIsSet(true);
      }
      if (incoming.get(3)) {
        struct.src = iprot.readString();
        struct.setSrcIsSet(true);
      }
      if (incoming.get(4)) {
        struct.alt = iprot.readString();
        struct.setAltIsSet(true);
      }
      if (incoming.get(5)) {
        struct.ref = iprot.readString();
        struct.setRefIsSet(true);
      }
    }
  }

}
