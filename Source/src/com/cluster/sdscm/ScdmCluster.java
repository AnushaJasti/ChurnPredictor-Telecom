/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.sdscm;

import com.cluster.common.Cluster;
import com.cluster.common.Record;
import com.util.MathUtil;
import java.util.ArrayList;

/**
 *
 * @author saisi
 */
public class ScdmCluster implements Cluster{
	
	private ArrayList<Record<Double>> records;
        private int balance;
        private int threshold;
   	public ScdmCluster(int threshold,int bal){
        	records = new ArrayList<Record<Double>>();
                this.balance= bal;
                this.threshold = threshold;
	}
        public int size(){
            return records.size();
        }
   	public ArrayList<Record<Double>> getRecords(){
            return records;
	}
        public void addRecord(Record<Double> record){
            this.records.add(record);
        }
        public void setRecord(int index, Record<Double> record){
            this.records.set(index, record);
        }
        public void removeAllRcords(){
            this.records = new ArrayList<Record<Double>>();
        }
        public int getBalanceFactor(){
            return this.balance;
        }
        public int getThreshold(){
            return this.threshold;
        }
        public Record<Double> generateMean(){
                Record<Double> tempMean = new Record<Double>();
                // update the mean
                if(records.size()>0){
                     Record<Double> curRecord = records.get(0);
                     if(curRecord!=null){
                    int attrCount = records.get(0).getSize();
                    tempMean = new Record<Double>(attrCount);
                    int recordCount = records.size();
                    Double[] attrCols = new Double[recordCount];
                    for(int i=0;i<attrCount;i++){
                        for(int j=0;j<recordCount;j++){
                            attrCols[j] = records.get(j).getAttribute(i);
                        }
                        tempMean.setAttribute(i, MathUtil.getMean(attrCols));
                    }
                    }
                }
            return tempMean;
        }
}
