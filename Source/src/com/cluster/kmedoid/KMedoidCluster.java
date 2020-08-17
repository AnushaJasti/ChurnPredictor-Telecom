 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.kmedoid;

import java.util.ArrayList;
import com.cluster.common.Record;
import com.cluster.common.Cluster;
import com.util.ClusterUtil;
import com.util.MathUtil;
/**
 *
 * @author chiru
 */
public class KMedoidCluster implements Cluster{
    private ArrayList<Record<Double>> records;
    
    public KMedoidCluster(){
        records = new ArrayList<Record<Double>>();
    }
    public void addRecord(Record<Double> record){
        records.add(record);
    }
    public int length(){
        return records.size();
    }
    public Record<Double> getRecord(int index){
        return records.get(index);
    }
    public ArrayList<Record<Double>> getRecords(){
        return records;
    }
}
