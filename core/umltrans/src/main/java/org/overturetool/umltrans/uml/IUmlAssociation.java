


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
public abstract class IUmlAssociation extends IUmlModelElement {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp


// ***** VDMTOOLS START Name=vdm_init_IUmlAssociation KEEP=NO
  private void vdm_init_IUmlAssociation () throws CGException {}
// ***** VDMTOOLS END Name=vdm_init_IUmlAssociation


// ***** VDMTOOLS START Name=IUmlAssociation KEEP=NO
  public IUmlAssociation () throws CGException {
    vdm_init_IUmlAssociation();
  }
// ***** VDMTOOLS END Name=IUmlAssociation


// ***** VDMTOOLS START Name=getOwnedEnds KEEP=NO
  abstract public HashSet getOwnedEnds () throws CGException ;
// ***** VDMTOOLS END Name=getOwnedEnds


// ***** VDMTOOLS START Name=getOwnedNavigableEnds KEEP=NO
  abstract public HashSet getOwnedNavigableEnds () throws CGException ;
// ***** VDMTOOLS END Name=getOwnedNavigableEnds


// ***** VDMTOOLS START Name=getName KEEP=NO
  abstract public String getName () throws CGException ;
// ***** VDMTOOLS END Name=getName


// ***** VDMTOOLS START Name=hasName KEEP=NO
  abstract public Boolean hasName () throws CGException ;
// ***** VDMTOOLS END Name=hasName


// ***** VDMTOOLS START Name=getId KEEP=NO
  abstract public String getId () throws CGException ;
// ***** VDMTOOLS END Name=getId

}
;