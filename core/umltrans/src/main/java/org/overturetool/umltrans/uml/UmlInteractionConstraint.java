


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
public class UmlInteractionConstraint extends IUmlInteractionConstraint {

// ***** VDMTOOLS START Name=vdmComp KEEP=NO
  static UTIL.VDMCompare vdmComp = new UTIL.VDMCompare();
// ***** VDMTOOLS END Name=vdmComp

// ***** VDMTOOLS START Name=ivMinint KEEP=NO
  private IUmlValueSpecification ivMinint = null;
// ***** VDMTOOLS END Name=ivMinint

// ***** VDMTOOLS START Name=ivMaxint KEEP=NO
  private IUmlValueSpecification ivMaxint = null;
// ***** VDMTOOLS END Name=ivMaxint


// ***** VDMTOOLS START Name=vdm_init_UmlInteractionConstraint KEEP=NO
  private void vdm_init_UmlInteractionConstraint () throws CGException {
    try {

      ivMinint = null;
      ivMaxint = null;
    }
    catch (Exception e){

      e.printStackTrace(System.out);
      System.out.println(e.getMessage());
    }
  }
// ***** VDMTOOLS END Name=vdm_init_UmlInteractionConstraint


// ***** VDMTOOLS START Name=UmlInteractionConstraint KEEP=NO
  public UmlInteractionConstraint () throws CGException {
    vdm_init_UmlInteractionConstraint();
  }
// ***** VDMTOOLS END Name=UmlInteractionConstraint


// ***** VDMTOOLS START Name=identity KEEP=NO
  public String identity () throws CGException {
    return new String("InteractionConstraint");
  }
// ***** VDMTOOLS END Name=identity


// ***** VDMTOOLS START Name=accept#1|IUmlVisitor KEEP=NO
  public void accept (final IUmlVisitor pVisitor) throws CGException {
    pVisitor.visitInteractionConstraint((IUmlInteractionConstraint) this);
  }
// ***** VDMTOOLS END Name=accept#1|IUmlVisitor


// ***** VDMTOOLS START Name=UmlInteractionConstraint#2|IUmlValueSpecification|IUmlValueSpecification KEEP=NO
  public UmlInteractionConstraint (final IUmlValueSpecification p1, final IUmlValueSpecification p2) throws CGException {

    vdm_init_UmlInteractionConstraint();
    {

      setMinint((IUmlValueSpecification) p1);
      setMaxint((IUmlValueSpecification) p2);
    }
  }
// ***** VDMTOOLS END Name=UmlInteractionConstraint#2|IUmlValueSpecification|IUmlValueSpecification


// ***** VDMTOOLS START Name=UmlInteractionConstraint#4|IUmlValueSpecification|IUmlValueSpecification|Long|Long KEEP=NO
  public UmlInteractionConstraint (final IUmlValueSpecification p1, final IUmlValueSpecification p2, final Long line, final Long column) throws CGException {

    vdm_init_UmlInteractionConstraint();
    {

      setMinint((IUmlValueSpecification) p1);
      setMaxint((IUmlValueSpecification) p2);
      setPosition(line, column);
    }
  }
// ***** VDMTOOLS END Name=UmlInteractionConstraint#4|IUmlValueSpecification|IUmlValueSpecification|Long|Long


// ***** VDMTOOLS START Name=init#1|HashMap KEEP=NO
  public void init (final HashMap data) throws CGException {

    {

      String fname = new String("minint");
      Boolean cond_4 = null;
      cond_4 = new Boolean(data.containsKey(fname));
      if (cond_4.booleanValue()) 
        setMinint((IUmlValueSpecification) data.get(fname));
    }
    {

      String fname = new String("maxint");
      Boolean cond_13 = null;
      cond_13 = new Boolean(data.containsKey(fname));
      if (cond_13.booleanValue()) 
        setMaxint((IUmlValueSpecification) data.get(fname));
    }
  }
// ***** VDMTOOLS END Name=init#1|HashMap


// ***** VDMTOOLS START Name=getMinint KEEP=NO
  public IUmlValueSpecification getMinint () throws CGException {
    return (IUmlValueSpecification) ivMinint;
  }
// ***** VDMTOOLS END Name=getMinint


// ***** VDMTOOLS START Name=hasMinint KEEP=NO
  public Boolean hasMinint () throws CGException {
    return new Boolean(!UTIL.equals(ivMinint, null));
  }
// ***** VDMTOOLS END Name=hasMinint


// ***** VDMTOOLS START Name=setMinint#1|IUmlValueSpecification KEEP=NO
  public void setMinint (final IUmlValueSpecification parg) throws CGException {
    ivMinint = (IUmlValueSpecification) UTIL.clone(parg);
  }
// ***** VDMTOOLS END Name=setMinint#1|IUmlValueSpecification


// ***** VDMTOOLS START Name=getMaxint KEEP=NO
  public IUmlValueSpecification getMaxint () throws CGException {
    return (IUmlValueSpecification) ivMaxint;
  }
// ***** VDMTOOLS END Name=getMaxint


// ***** VDMTOOLS START Name=hasMaxint KEEP=NO
  public Boolean hasMaxint () throws CGException {
    return new Boolean(!UTIL.equals(ivMaxint, null));
  }
// ***** VDMTOOLS END Name=hasMaxint


// ***** VDMTOOLS START Name=setMaxint#1|IUmlValueSpecification KEEP=NO
  public void setMaxint (final IUmlValueSpecification parg) throws CGException {
    ivMaxint = (IUmlValueSpecification) UTIL.clone(parg);
  }
// ***** VDMTOOLS END Name=setMaxint#1|IUmlValueSpecification

}
;