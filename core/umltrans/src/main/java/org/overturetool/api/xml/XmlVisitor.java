


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
public abstract class XmlVisitor {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp


// ***** VDMTOOLS START Name=vdm_init_XmlVisitor KEEP=NO
  private void vdm_init_XmlVisitor () throws CGException {}
// ***** VDMTOOLS END Name=vdm_init_XmlVisitor


// ***** VDMTOOLS START Name=XmlVisitor KEEP=NO
  public XmlVisitor () throws CGException {
    vdm_init_XmlVisitor();
  }
// ***** VDMTOOLS END Name=XmlVisitor


// ***** VDMTOOLS START Name=setEncoding#1|String KEEP=NO
  abstract public void setEncoding (final String encodingType) throws CGException ;
// ***** VDMTOOLS END Name=setEncoding#1|String


// ***** VDMTOOLS START Name=VisitXmlDocument#1|XmlDocument KEEP=NO
  abstract public void VisitXmlDocument (final XmlDocument var_1_1) throws CGException ;
// ***** VDMTOOLS END Name=VisitXmlDocument#1|XmlDocument


// ***** VDMTOOLS START Name=VisitXmlEntity#1|XmlEntity KEEP=NO
  abstract public void VisitXmlEntity (final XmlEntity var_1_1) throws CGException ;
// ***** VDMTOOLS END Name=VisitXmlEntity#1|XmlEntity


// ***** VDMTOOLS START Name=VisitXmlAttribute#1|XmlAttribute KEEP=NO
  abstract public void VisitXmlAttribute (final XmlAttribute var_1_1) throws CGException ;
// ***** VDMTOOLS END Name=VisitXmlAttribute#1|XmlAttribute


// ***** VDMTOOLS START Name=VisitXmlData#1|XmlData KEEP=NO
  abstract public void VisitXmlData (final XmlData var_1_1) throws CGException ;
// ***** VDMTOOLS END Name=VisitXmlData#1|XmlData

}
;