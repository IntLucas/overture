


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
package org.overturetool.umltrans.uml;

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
public abstract class IUmlMos extends IUmlInteractionFragment {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp


// ***** VDMTOOLS START Name=vdm_init_IUmlMos KEEP=NO
  private void vdm_init_IUmlMos () throws CGException {}
// ***** VDMTOOLS END Name=vdm_init_IUmlMos


// ***** VDMTOOLS START Name=IUmlMos KEEP=NO
  public IUmlMos () throws CGException {
    vdm_init_IUmlMos();
  }
// ***** VDMTOOLS END Name=IUmlMos


// ***** VDMTOOLS START Name=getName KEEP=NO
  abstract public String getName () throws CGException ;
// ***** VDMTOOLS END Name=getName


// ***** VDMTOOLS START Name=getMessage KEEP=NO
  abstract public IUmlMessage getMessage () throws CGException ;
// ***** VDMTOOLS END Name=getMessage


// ***** VDMTOOLS START Name=hasMessage KEEP=NO
  abstract public Boolean hasMessage () throws CGException ;
// ***** VDMTOOLS END Name=hasMessage


// ***** VDMTOOLS START Name=getCovered KEEP=NO
  abstract public IUmlLifeLine getCovered () throws CGException ;
// ***** VDMTOOLS END Name=getCovered


// ***** VDMTOOLS START Name=getEvent KEEP=NO
  abstract public IUmlCallEvent getEvent () throws CGException ;
// ***** VDMTOOLS END Name=getEvent


// ***** VDMTOOLS START Name=hasEvent KEEP=NO
  abstract public Boolean hasEvent () throws CGException ;
// ***** VDMTOOLS END Name=hasEvent

}
;