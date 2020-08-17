 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.io.util;

import java.io.*;
import java.util.*;
import com.cluster.common.*;
import com.bean.InputDataBean;
/**
 *
 * @author SaiKetan
 */
public class DataReaderUtil {
    
    public InputDataBean readData(InputStream in)throws Exception{
        InputDataBean dataBean = new InputDataBean();
        ArrayList<String> headers = new ArrayList<String>();
        
        DataSet<Record<Double>> data = new DataSet<Record<Double>>();
        Record<Double> record = null;
        BufferedReader bufin = new BufferedReader(new InputStreamReader(in));
        ArrayList<String> inputStrs = new ArrayList<String>();
        
        String line = bufin.readLine();
        while(line!=null)
        {
            if(inputStrs.indexOf(line)<0)
               inputStrs.add(line);
               line = bufin.readLine();
        }
        String headerLine = inputStrs.get(0);
        
        String[] splits = headerLine.split(",");
        for(int i=0;i<splits.length;i++){
            headers.add(splits[i]);
        }
        for(int count=1;count<inputStrs.size();count++)
        {
            line = inputStrs.get(count);
           System.out.println("zzzz  "+line);
            record = readRecord(line);
            System.out.print("Record {" + count + "}----->" + line);
            System.out.println("rec "+record.getAttribute(0));
            data.add(record);
        }
        System.out.println("  length :"+data.length());
        dataBean.setHeaders(headers);
        dataBean.setDataSet(data);
        return dataBean;
    }
    
    private Record<Double> readRecord(String line){
        System.out.println("line zzz "+line);
        
        String[] splits = line.split(",");
        System.out.println("line ss "+splits.length);
        Record<Double> record = new Record<Double>(splits.length);
        
        for(int i=0;i<splits.length;i++){
            record.setAttribute(i, Double.parseDouble(splits[i]));
        }
        
   System.out.println("rec 4"+record.getAttribute(0));
        return record;
    }
}
