 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.kmean;

import java.util.ArrayList;
import com.cluster.common.Record;
import com.cluster.common.Cluster;
import com.util.ClusterUtil;
import com.util.MathUtil;

/**
 *
 * @author chiru
 */

public class KMeanCluster implements Cluster{
	
	private ArrayList<Record<Double>> records;
	private Boolean justUpdated;
        private Boolean toInitUpdate;
        private Record<Double> currentMean;
        private Record<Double> oldMean;
        private void init(Record<Double> currentMean){
            this.justUpdated = true;
            this.toInitUpdate = true;
            this.currentMean = currentMean;
            this.oldMean = null;
        }
	public KMeanCluster(Record<Double> mean){
                init(mean);
		records = new ArrayList<Record<Double>>();
	}
   	public ArrayList<Record<Double>> getRecords(){
            return records;
	}
        public void addRecord(Record<Double> record){
            justUpdated = false;
            this.records.add(record);
        }
        public void setRecord(int index, Record<Double> record){
            justUpdated = false;
            this.records.set(index, record);
        }
        private void updateMean(){
            //if(!justUpdated && this.toInitUpdate){
                // update the mean
                oldMean = currentMean;
                System.out.println("mean generating..");
                currentMean = generateMean();
                System.out.println("mean generated..");
                justUpdated = true;
                this.toInitUpdate = false;
            //}else{
             //   System.out.println("not updated...");
           // }
        }
        private Record<Double> generateMean(){
                Record<Double> tempMean = this.getMean();
                System.out.println("in generate mean...");
                // update the mean
                if(records.size()>0){
                     Record<Double> curRecord = records.get(0);
                     if(curRecord!=null){
                    int attrCount = records.get(0).getSize();
                    System.out.println("in generate mean...2");
                    tempMean = new Record<Double>(attrCount);
                    int recordCount = records.size();
                    System.out.println("in generate mean...3");
                    Double[] attrCols = new Double[recordCount];
                    System.out.println("record count "+recordCount);
                    System.out.println("attr count "+attrCount);
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
        public void removeAllRcords(){
            updateMean();
            justUpdated = false;
            this.records = new ArrayList<Record<Double>>();
        }
        public Record<Double> getOldMean(){
            return oldMean;
        }
        public Record<Double> getMean(){
            return currentMean;
        }
        
        public Boolean isFullyFormed(){
            boolean fullyFormed = false;
            Record<Double> curMean = generateMean();
            System.out.println(" is fully  "+curMean+" our mean "+getMean());
            if(curMean.equals(getMean())){
                fullyFormed = true;
            }
            return fullyFormed;
        }
}
