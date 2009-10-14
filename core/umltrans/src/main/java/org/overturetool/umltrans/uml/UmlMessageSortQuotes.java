


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
public class UmlMessageSortQuotes {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp

// ***** VDMTOOLS START Name=qmap KEEP=NO
  private static HashMap qmap = new HashMap();
// ***** VDMTOOLS END Name=qmap

// ***** VDMTOOLS START Name=IQSYNCHCALL KEEP=NO
  public static final Long IQSYNCHCALL = new Long(0);
// ***** VDMTOOLS END Name=IQSYNCHCALL

// ***** VDMTOOLS START Name=IQASYNCHCALL KEEP=NO
  public static final Long IQASYNCHCALL = new Long(1);
// ***** VDMTOOLS END Name=IQASYNCHCALL


// ***** VDMTOOLS START Name=static KEEP=NO
  static {
    try {

      UmlMessageSortQuotes.qmap = new HashMap();
      UmlMessageSortQuotes.qmap.put(IQSYNCHCALL, new String("<SYNCHCALL>"));
      UmlMessageSortQuotes.qmap.put(IQASYNCHCALL, new String("<ASYNCHCALL>"));
    }
    catch (Throwable e){

      e.printStackTrace(System.out);
      System.out.println(e.getMessage());
    }
  }
// ***** VDMTOOLS END Name=static


// ***** VDMTOOLS START Name=vdm_init_UmlMessageSortQuotes KEEP=NO
  private void vdm_init_UmlMessageSortQuotes () throws CGException {}
// ***** VDMTOOLS END Name=vdm_init_UmlMessageSortQuotes


// ***** VDMTOOLS START Name=UmlMessageSortQuotes KEEP=NO
  public UmlMessageSortQuotes () throws CGException {
    vdm_init_UmlMessageSortQuotes();
  }
// ***** VDMTOOLS END Name=UmlMessageSortQuotes


// ***** VDMTOOLS START Name=getQuoteName#1|Long KEEP=NO
  static public String getQuoteName (final Long pid) throws CGException {
    return UTIL.ConvertToString(qmap.get(pid));
  }
// ***** VDMTOOLS END Name=getQuoteName#1|Long


// ***** VDMTOOLS START Name=validQuote#1|Long KEEP=NO
  static public Boolean validQuote (final Long pid) throws CGException {

    Boolean rexpr_2 = null;
    rexpr_2 = new Boolean(qmap.containsKey(pid));
    return rexpr_2;
  }
// ***** VDMTOOLS END Name=validQuote#1|Long

}
;