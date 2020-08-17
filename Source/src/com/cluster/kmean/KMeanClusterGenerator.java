 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.kmean;
import java.util.ArrayList;
import com.cluster.kmean.KMeanCluster;
import com.cluster.common.DataSet;
import com.cluster.common.Record;
import com.util.ClusterUtil;

/**
 *
 * @author chiru
 */
public class KMeanClusterGenerator {
    public ArrayList<KMeanCluster> generateClusters(DataSet<Record<Double>> dataSet, int noOfClusters){
        if(dataSet.length()<noOfClusters){
            return null;
        }
        ArrayList<KMeanCluster> clusters = new ArrayList<KMeanCluster>(noOfClusters);
        DataSet<Record<Double>> cloneSet = new DataSet<Record<Double>>(dataSet.getAll()); // will be used as global data set...
        
        // initialize the clusters
        for(int i=0;i<noOfClusters;i++){
            Record<Double> mean = dataSet.remove(new java.util.Random().nextInt(dataSet.length()));
             clusters.add(new KMeanCluster(mean));
        }
        // arrange the clusters
        dataSet =  new DataSet<Record<Double>>(cloneSet.getAll());
        arrangeClusters(dataSet,clusters);
        printClusters(clusters);
        System.out.println("clusters..printed..");
        int count =0;
        while(!testForFinal(clusters)){
           //update means
            count++;
            System.out.println("updating the means...");
            updateMeans(clusters);
            // arrange clusters
            arrangeClusters(dataSet,clusters);
            if(count>5)
                break;
        }
        System.out.println("*** clusters formed ***"+count);
        this.printClusters(clusters);
        return clusters;
    }
   private boolean testForFinal(ArrayList<KMeanCluster> clusters){
       boolean temp = true;
       for(KMeanCluster c:clusters){
           if(!c.isFullyFormed()){
               temp = false;
               break;
           }
       }
       System.out.println("testing for matching..."+temp);
       return temp;
   }
    private void arrangeClusters(DataSet<Record<Double>> dataSet,ArrayList<KMeanCluster> clusters){
        int dsLen = dataSet.length();
        Record _rec = null;
        int mostRelIndex = -1;
        for(int i=0;i<dsLen;i++){
            _rec = dataSet.get(i);
            mostRelIndex = findMostReleventClusterIndex(clusters,_rec);
            clusters.get(mostRelIndex).addRecord(_rec);
        }
    }
    private void updateMeans(ArrayList<KMeanCluster> clusters){
         System.out.println("updating the means...start..");
        int cLen = clusters.size();
        for(int i=0;i<cLen;i++){
            System.out.println("updating..."+i);
            clusters.get(i).removeAllRcords();
        }
        System.out.println("updating the means...end..");
    }
    private int findMostReleventClusterIndex(ArrayList<KMeanCluster> clusters,Record rec){
        int index = 0;
        System.out.println("############## "+clusters.get(0).getMean()+"   "+rec);
        double minDistance = ClusterUtil.getDistance(clusters.get(0).getMean(), rec);
        double curDistance = 0;
        int cLen = clusters.size();
        for(int i=1;i<cLen;i++){
            curDistance = ClusterUtil.getDistance(clusters.get(i).getMean(), rec);
            if(minDistance>curDistance){
                index = i;
                minDistance = curDistance;
            }
        }
        return index;
    }
    private void printClusters(ArrayList<KMeanCluster> clusters){
        System.out.println("  Printing the clusters ");
        
        KMeanCluster cur = null;
        for(KMeanCluster c :clusters){
            System.out.println(" Mean : "+c.getMean());
            for(int i=0;i<c.getRecords().size();i++){
                System.out.println(i+"  "+c.getRecords().get(i));
            }
        }
    }
    public static void main(String args[]){
        DataSet<Record<Double>> dSet = new DataSet<Record<Double>>();
        Record<Double> record = new Record<Double>(20);
        for(int i=0;i<10000;i++){
            record = new Record<Double>(20);
            for(int j=0;j<20;j++){
                record.setAttribute(j, Math.random());
            }
            dSet.add(record);
        }
        System.out.println("initial data set...");
        for(int i=0;i<dSet.length();i++){
            System.out.println("  "+dSet.get(i));
        }
   
        new KMeanClusterGenerator().generateClusters(dSet,30);
    }
}
