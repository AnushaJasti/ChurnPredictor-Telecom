/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.sdscm;

import com.cluster.common.DataSet;
import com.cluster.common.Record;
import com.util.ClusterUtil;
import java.util.ArrayList;

/**
 *
 * @author saisi
 */

public class  ScdmClusterGenerator  {
    public ArrayList<ScdmCluster> generateClusters(DataSet<Record<Double>> dataSet,int threshold,int balance)
    {
        if(dataSet.length()==0){
            return null;
        }
      ScdmClusterTree tree = new ScdmClusterTree(threshold,balance);
        // initialize the clusters
        for(int i=0;i<dataSet.length();i++){
            System.out.println("adding.... item...."+i);
                
                tree.add(dataSet.get(i));
        }
        
        ArrayList<ScdmCluster> clusters = tree.getCurrentClusters();
        System.out.println("clusters completed...."+clusters.size());
        
        this.printClusters(clusters);
        System.out.println("completed...."+clusters.size());
        return clusters;
    }
    private void printClusters(ArrayList<ScdmCluster> clusters){
        
        ScdmCluster cur = null;
        for(ScdmCluster c :clusters){
                System.out.println("cluster count.."+c.size());
            for(int i=0;i<c.getRecords().size();i++){
                System.out.println(i+"...  "+c.getRecords().get(i));
            }
        }
        
    }
    class ScdmClusterTree{
        int threshold;
        int balance;
        public ScdmClusterTree(int tre,int bal){
            this.threshold = tre;
            this.balance = bal;
        }
        ArrayList<ScdmCluster> clusters = new ArrayList<ScdmCluster>();
        public void add(Record e){
            //
            
            // steps...
            // 1. iterate all the clusters..
            // 2. find which cluster e fits.. based on distance of centroid using mountain function
            // 3. test for rearrange the cluster..
            // 4. rearrange if needed...
            // step 1.
            if(clusters.isEmpty()){
                 ScdmCluster c = new ScdmCluster(threshold,balance);
                    c.addRecord(e);
                    clusters.add(c);
            }else{
                ScdmCluster current = null;
                boolean added = false;
                for(int i=0;i<clusters.size();i++){
                    current = clusters.get(i);
                    if(fitForCluster(current,e)){
                        System.out.println("fitted");
                        if(addToCluster(current,e)){
                            added = true;
                        }
                        else{   // need to re arrange the leaf nodes...
                        System.out.println("need rearrange...");
                            ArrayList<ScdmCluster> Cs = rearrange(current,e);
                            clusters.remove(i);
                            clusters.add(Cs.get(0));
                            clusters.add(Cs.get(1));
                            added = true;
                        }
                        break;
                        
                    }
                }
                if(!added){
                        // create a new cluster and add to clusters..
                        ScdmCluster c = new ScdmCluster(threshold,balance);
                        c.addRecord(e);
                        clusters.add(c);
                }
                
            }
        }
        public ArrayList<ScdmCluster> getCurrentClusters(){
            return this.clusters;
        }
        public ArrayList<ScdmCluster> rearrange(ScdmCluster c,Record e){
            ArrayList<ScdmCluster> splits = new ArrayList<ScdmCluster>();
            //
            splits.add(new ScdmCluster(this.threshold,this.balance));
            splits.add(new ScdmCluster(this.threshold,this.balance));
            
            for(int i=0;i<c.size()/2;i++){
                splits.get(0).addRecord(c.getRecords().get(i));
            }
            for(int i=c.size()/2+1;i<c.size();i++){
                splits.get(1).addRecord(c.getRecords().get(i));
            }
            
            return splits;
        }
        public boolean fitForCluster(ScdmCluster c, Record e){
            boolean fit = false;
            double linearSum = 0;
            Record<Double> mean = c.generateMean();
            for(Record r:c.getRecords()){
                linearSum += ClusterUtil.getDistance(e, r);
            }
            if(linearSum<c.getThreshold()){
                fit = true;
            }
            return fit;
        }
        public boolean addToCluster(ScdmCluster c, Record e){
            boolean added = false;
            if(c.size()<c.getBalanceFactor()){
                c.addRecord(e);
                added = true;
            }
            return added;
        }
    }
    public static void main(String args[]){
        DataSet<Record<Double>> dSet = new DataSet<Record<Double>>();
        Record<Double> record = new Record<Double>(20);
        for(int i=0;i<30;i++){
            record = new Record<Double>(20);
            for(int j=0;j<20;j++){
                record.setAttribute(j, Math.random());
            }
            dSet.add(record);
        }
        for(int i=0;i<dSet.length();i++){
            System.out.println("  "+dSet.get(i));
        }
   
        new  ScdmClusterGenerator ().generateClusters(dSet,35,35);
    }
}
