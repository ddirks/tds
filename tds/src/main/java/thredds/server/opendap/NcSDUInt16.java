/*
 * Copyright (c) 1998-2018 John Caron and University Corporation for Atmospheric Research/Unidata
 * See LICENSE for license information.
 */

// $Id: NcSDUInt16.java 51 2006-07-12 17:13:13Z caron $


package thredds.server.opendap;

import opendap.servers.*;

import java.io.IOException;
import java.io.DataOutputStream;

import ucar.ma2.*;
import ucar.nc2.*;

/**
 * Wraps a netcdf scalar (unsigned) short variable.
 *
 * @author jcaron
 */
public class NcSDUInt16 extends SDUInt16 implements HasNetcdfVariable {
  private Variable ncVar;

  /**
   * Constructor
   *
   * @param v : the netcdf Variable
   */
  NcSDUInt16(Variable v) {
      super(Variable.getDAPName(v));
    this.ncVar = v;
  }

  /**
   * Read the value (parameters are ignored).
   */
  public boolean read(String datasetName, Object specialO) throws IOException {
    setData(ncVar.read());
    return (false);
  }

  public void setData(Array data) {
    setValue(data.getShort(data.getIndex()));
    setRead(true);
  }

  public Variable getVariable() { return ncVar; }

  public void serialize(DataOutputStream sink, StructureData sdata, StructureMembers.Member m) throws IOException {
    setValue( sdata.getScalarShort(m));
    externalize(sink);
  }
}
