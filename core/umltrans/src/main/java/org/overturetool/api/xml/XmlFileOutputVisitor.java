


//
// THIS FILE IS AUTOMATICALLY GENERATED!!
//
// Generated at 2009-10-11 by the VDM++ to JAVA Code Generator
// (v8.2.1b - Wed 15-Jul-2009 14:09:22)
//
// Supported compilers: jdk 1.4/1.5/1.6
//

// ***** VDMTOOLS START Name=HeaderComment KEEP=NO
// ***** VDMTOOLS END Name=HeaderComment

// ***** VDMTOOLS START Name=package KEEP=NO
package org.overturetool.api.xml;

// ***** VDMTOOLS END Name=package

// ***** VDMTOOLS START Name=imports KEEP=NO

import jp.co.csk.vdm.toolbox.VDM.*;
import java.util.*;
import org.overturetool.ast.itf.*;
import org.overturetool.ast.imp.*;
import org.overturetool.api.io.*;
import org.overturetool.api.io.*;
import org.overturetool.api.xml.*;
import org.overturetool.umltrans.api.*;
import org.overturetool.umltrans.*;
import org.overturetool.umltrans.uml.*;
import org.overturetool.umltrans.uml2vdm.*;
import org.overturetool.umltrans.vdm2uml.*;
// ***** VDMTOOLS END Name=imports



@SuppressWarnings({"all","unchecked","unused"})
public abstract class XmlFileOutputVisitor extends XmlVisitor {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp

// ***** VDMTOOLS START Name=encoding KEEP=NO
  private String encoding = null;
// ***** VDMTOOLS END Name=encoding

// ***** VDMTOOLS START Name=indentCount KEEP=NO
  private Long indentCount = null;
// ***** VDMTOOLS END Name=indentCount

// ***** VDMTOOLS START Name=INDENT KEEP=NO
  private static final String INDENT = new String("  ");
// ***** VDMTOOLS END Name=INDENT


// ***** VDMTOOLS START Name=vdm_init_XmlFileOutputVisitor KEEP=NO
  private void vdm_init_XmlFileOutputVisitor () throws CGException {
    try {

      encoding = new String("");
      indentCount = new Long(0);
    }
    catch (Exception e){

      e.printStackTrace(System.out);
      System.out.println(e.getMessage());
    }
  }
// ***** VDMTOOLS END Name=vdm_init_XmlFileOutputVisitor


// ***** VDMTOOLS START Name=XmlFileOutputVisitor KEEP=NO
  public XmlFileOutputVisitor () throws CGException {
    vdm_init_XmlFileOutputVisitor();
  }
// ***** VDMTOOLS END Name=XmlFileOutputVisitor


// ***** VDMTOOLS START Name=setEncoding#1|String KEEP=NO
  public void setEncoding (final String encodingType) throws CGException {
    encoding = UTIL.ConvertToString(UTIL.clone(encodingType));
  }
// ***** VDMTOOLS END Name=setEncoding#1|String


// ***** VDMTOOLS START Name=print#1|String KEEP=NO
  abstract protected void print (final String text) throws CGException ;
// ***** VDMTOOLS END Name=print#1|String


// ***** VDMTOOLS START Name=printData#1|String KEEP=NO
  private void printData (final String pval) throws CGException {
    print(pval);
  }
// ***** VDMTOOLS END Name=printData#1|String


// ***** VDMTOOLS START Name=printAttribute#1|String KEEP=NO
  private void printAttribute (final String pval) throws CGException {

    String tmpArg_v_3 = null;
    String var1_4 = null;
    var1_4 = new String("\"").concat(pval);
    tmpArg_v_3 = var1_4.concat(new String("\""));
    print(tmpArg_v_3);
  }
// ***** VDMTOOLS END Name=printAttribute#1|String


// ***** VDMTOOLS START Name=XmlFileOutputVisitor#1|String KEEP=NO
  public XmlFileOutputVisitor (final String var_1_1) throws CGException {
    vdm_init_XmlFileOutputVisitor();
  }
// ***** VDMTOOLS END Name=XmlFileOutputVisitor#1|String


// ***** VDMTOOLS START Name=VisitXmlDocument#1|XmlDocument KEEP=NO
  public void VisitXmlDocument (final XmlDocument pxmld) throws CGException {

    Boolean cont = null;
    XmlEntityList obj_2 = null;
    obj_2 = pxmld.entities;
    cont = obj_2.First();
    if ((new Long(encoding.length()).intValue() == new Long(0).intValue())) 
      print(new String("<?xml version=\"1.0\"?>\n"));
    else {

      String tmpArg_v_9 = null;
      String var1_10 = null;
      var1_10 = new String("<?xml version=\"1.0\" encoding=\"").concat(encoding);
      tmpArg_v_9 = var1_10.concat(new String("\"?>\n"));
      print(tmpArg_v_9);
    }
    while ( cont.booleanValue()){

      XmlEntity ent = null;
      XmlEntityList obj_17 = null;
      obj_17 = pxmld.entities;
      ent = (XmlEntity) obj_17.getEntity();
      ent.accept((XmlVisitor) this);
      Boolean rhs_21 = null;
      XmlEntityList obj_22 = null;
      obj_22 = pxmld.entities;
      rhs_21 = obj_22.Next();
      cont = (Boolean) UTIL.clone(rhs_21);
    }
  }
// ***** VDMTOOLS END Name=VisitXmlDocument#1|XmlDocument


// ***** VDMTOOLS START Name=VisitXmlEntity#1|XmlEntity KEEP=NO
  public void VisitXmlEntity (final XmlEntity pxmle) throws CGException {

    Boolean cont = null;
    XmlAttributeList obj_2 = null;
    obj_2 = pxmle.attributes;
    cont = obj_2.First();
    indentCount = UTIL.NumberToLong(UTIL.clone(new Long(indentCount.intValue() + new Long(1).intValue())));
    String tmpArg_v_8 = null;
    String var1_9 = null;
    var1_9 = GetIndent().concat(new String("<"));
    String var2_12 = null;
    var2_12 = pxmle.name;
    tmpArg_v_8 = var1_9.concat(var2_12);
    print(tmpArg_v_8);
    while ( cont.booleanValue()){

      XmlAttribute attr = null;
      XmlAttributeList obj_15 = null;
      obj_15 = pxmle.attributes;
      attr = (XmlAttribute) obj_15.getAttribute();
      attr.accept((XmlVisitor) this);
      Boolean rhs_19 = null;
      XmlAttributeList obj_20 = null;
      obj_20 = pxmle.attributes;
      rhs_19 = obj_20.Next();
      cont = (Boolean) UTIL.clone(rhs_19);
    }
    Boolean cond_22 = null;
    Long var1_23 = null;
    XmlEntityList obj_24 = null;
    obj_24 = pxmle.entities;
    var1_23 = obj_24.Length();
    cond_22 = new Boolean((var1_23.intValue()) > (new Long(0).intValue()));
    if (cond_22.booleanValue()) {

      print(new String(">\n"));
      Boolean rhs_50 = null;
      XmlEntityList obj_51 = null;
      obj_51 = pxmle.entities;
      rhs_50 = obj_51.First();
      cont = (Boolean) UTIL.clone(rhs_50);
      while ( cont.booleanValue()){

        XmlEntity ent = null;
        XmlEntityList obj_54 = null;
        obj_54 = pxmle.entities;
        ent = (XmlEntity) obj_54.getEntity();
        ent.accept((XmlVisitor) this);
        Boolean rhs_58 = null;
        XmlEntityList obj_59 = null;
        obj_59 = pxmle.entities;
        rhs_58 = obj_59.Next();
        cont = (Boolean) UTIL.clone(rhs_58);
      }
      String tmpArg_v_62 = null;
      String var1_63 = null;
      String var1_64 = null;
      var1_64 = GetIndent().concat(new String("</"));
      String var2_67 = null;
      var2_67 = pxmle.name;
      var1_63 = var1_64.concat(var2_67);
      tmpArg_v_62 = var1_63.concat(new String(">\n"));
      print(tmpArg_v_62);
    }
    else {

      Boolean cond_42 = null;
      XmlData var1_43 = null;
      var1_43 = pxmle.data;
      cond_42 = new Boolean(UTIL.equals(var1_43, null));
      if (cond_42.booleanValue()) 
        print(new String("/>\n"));
      else {

        print(new String(">"));
        XmlData obj_30 = null;
        obj_30 = pxmle.data;
        obj_30.accept((XmlVisitor) this);
        String tmpArg_v_34 = null;
        String var1_35 = null;
        String var1_36 = null;
        var1_36 = GetIndent().concat(new String("</"));
        String var2_39 = null;
        var2_39 = pxmle.name;
        var1_35 = var1_36.concat(var2_39);
        tmpArg_v_34 = var1_35.concat(new String(">\n"));
        print(tmpArg_v_34);
      }
    }
    indentCount = UTIL.NumberToLong(UTIL.clone(new Long(indentCount.intValue() - new Long(1).intValue())));
  }
// ***** VDMTOOLS END Name=VisitXmlEntity#1|XmlEntity


// ***** VDMTOOLS START Name=VisitXmlAttribute#1|XmlAttribute KEEP=NO
  public void VisitXmlAttribute (final XmlAttribute pattr) throws CGException {

    String tmpArg_v_3 = null;
    String var1_4 = null;
    String var2_6 = null;
    var2_6 = pattr.name;
    var1_4 = new String(" ").concat(var2_6);
    tmpArg_v_3 = var1_4.concat(new String("="));
    print(tmpArg_v_3);
    String tmpArg_v_10 = null;
    tmpArg_v_10 = pattr.val;
    printAttribute(tmpArg_v_10);
  }
// ***** VDMTOOLS END Name=VisitXmlAttribute#1|XmlAttribute


// ***** VDMTOOLS START Name=VisitXmlData#1|XmlData KEEP=NO
  public void VisitXmlData (final XmlData pdata) throws CGException {

    String tmpArg_v_3 = null;
    tmpArg_v_3 = pdata.data;
    printData(tmpArg_v_3);
  }
// ***** VDMTOOLS END Name=VisitXmlData#1|XmlData


// ***** VDMTOOLS START Name=GetIndent KEEP=NO
  private String GetIndent () throws CGException {

    String ind = new String("");
    Long i = new Long(0);
    while ( ((i.intValue()) < (indentCount.intValue()))){

      i = UTIL.NumberToLong(UTIL.clone(new Long(i.intValue() + new Long(1).intValue())));
      String rhs_7 = null;
      rhs_7 = ind.concat(INDENT);
      ind = UTIL.ConvertToString(UTIL.clone(rhs_7));
    }
    return ind;
  }
// ***** VDMTOOLS END Name=GetIndent

}
;