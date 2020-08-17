 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.cluster.common.Record;
import com.cluster.common.Cluster;
import java.util.*;

/**
 *
 * @author chiru
 */
public class ClusterUtil {
   
    public static Double getDistance(Record<Double> r1, Record<Double> r2){
        Double distance = new Double(0);
        Double sqrSum = new Double(0);
        for(int i=0;i<r1.getSize();i++){
            sqrSum += (r1.getAttribute(i)-r2.getAttribute(i)) * (r1.getAttribute(i)-r2.getAttribute(i));
        }
        distance = Math.sqrt(sqrSum);
        return distance;
    }
    
    public static Record<Double> getCentroid(Cluster<Double> cluster){
        
        Record<Double> centroid = new Record<Double>(cluster.getRecords().get(0).getSize());
        Double curDis = null;
        int recordCount = cluster.getRecords().size();
        for(int i=0;i<centroid.getSize();i++){
            curDis = new Double("0.0");
            for(int j=0;j<recordCount;j++){
                curDis += cluster.getRecords().get(j).getAttribute(i);
            }
            centroid.setAttribute(i, curDis/recordCount);
        }
      //  System.out.println("centroid :"+centroid);
        return centroid;
    }
    
    public static ArrayList<Double> findMinMax(com.cluster.common.DataSet<Record<Double>> data){
        ArrayList<Double> ranges = new ArrayList<Double>();
        
        List<Record<Double>> d = data.getAll();
        Record<Double> curRecord = d.get(0);
        
        // for first record set as defalut for min and max
        
        for(int i=0;i<curRecord.getSize();i++){
            ranges.add(curRecord.getAttribute(i));
            ranges.add(curRecord.getAttribute(i));
        }
        
        for(int i=1;i<d.size();i++){
            curRecord = d.get(i);
            
            for(int j=0;j<curRecord.getSize();j++){
                if(ranges.get(j*2) > curRecord.getAttribute(j)){
                    ranges.set(j*2,curRecord.getAttribute(j));
                }
                if(ranges.get(j*2+1) < curRecord.getAttribute(j)){
                    ranges.set(j*2+1,curRecord.getAttribute(j));
                }
            }
        }
        return ranges;
    }
}