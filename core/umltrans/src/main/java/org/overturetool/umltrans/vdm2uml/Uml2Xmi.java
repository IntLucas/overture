




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
package org.overturetool.umltrans.vdm2uml;

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
public abstract class Uml2Xmi {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp

// ***** VDMTOOLS START Name=doc KEEP=NO
  public XmlDoc doc = null;
// ***** VDMTOOLS END Name=doc


// ***** VDMTOOLS START Name=vdm_init_Uml2Xmi KEEP=NO
  private void vdm_init_Uml2Xmi () throws CGException {
    try {
      doc = (XmlDoc) new XmlDoc();
    }
    catch (Exception e){

      e.printStackTrace(System.out);
      System.out.println(e.getMessage());
    }
  }
// ***** VDMTOOLS END Name=vdm_init_Uml2Xmi


// ***** VDMTOOLS START Name=Uml2Xmi KEEP=NO
  public Uml2Xmi () throws CGException {
    vdm_init_Uml2Xmi();
  }
// ***** VDMTOOLS END Name=Uml2Xmi


// ***** VDMTOOLS START Name=Save#3|String|IUmlModel|StatusLog KEEP=NO
  abstract public void Save (final String var_1_1, final IUmlModel var_2_2, final StatusLog var_3_3) throws CGException ;
// ***** VDMTOOLS END Name=Save#3|String|IUmlModel|StatusLog

}
;