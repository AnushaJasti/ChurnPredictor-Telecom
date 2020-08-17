/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.*;
import com.cluster.common.DataSet;
import com.cluster.common.Record;

/**
 *
 * @author VAIO
 */
public class InputDataBean {
    private DataSet<Record<Double>> dataSet;
    private ArrayList<String> headers;
    
  public void setDataSet(DataSet<Record<Double>> dataSet){
      this.dataSet = dataSet;
  }  
  public DataSet<Record<Double>> getDataSet(){
      return this.dataSet;
  }
  public void setHeaders(ArrayList<String> headers){
      this.headers = headers;
  }
  public ArrayList<String> getHeaders(){
      return this.headers;
  }
}
