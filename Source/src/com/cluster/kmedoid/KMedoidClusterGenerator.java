 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.kmedoid;

import java.util.List;
import java.util.ArrayList;
import com.cluster.common.DataSet;
import com.cluster.common.Record;
import com.util.ClusterUtil;

/**
 *
 * @author chiru
 */
public class KMedoidClusterGenerator {
    public ArrayList<KMedoidCluster> generateClusters(DataSet<Record<Double>> dataSet, int noOfClusters){
        ArrayList<KMedoidCluster> clusters = new ArrayList<KMedoidCluster>(noOfClusters);
        List<Record<Double>> arbitraryRecords = new ArrayList<Record<Double>>();
        
        DataSet<Record<Double>> cloneSet = new DataSet<Record<Double>>(dataSet.getAll());
        
        // initialize clusters
        Record moved = null;
        KMedoidCluster _cluster = null;
        for(int i=0;i<noOfClusters;i++){
            moved = dataSet.remove(new java.util.Random().nextInt(dataSet.length()));
            arbitraryRecords.add(moved);
        }
        int count = 0;
        do{
            // assign each object to the nearest cluster
            clusters = new ArrayList<KMedoidCluster>(noOfClusters);
            assignRecords(dataSet,clusters,arbitraryRecords);
            DataSet<Record<Double>> tempSet = new DataSet<Record<Double>>(cloneSet.getAll());
            List<Record<Double>> newArbitraryList = generateNewArbitraryList(cloneSet,clusters);
            boolean changed = testForArbitraryChange(newArbitraryList,arbitraryRecords);
            if(changed){
                arbitraryRecords = newArbitraryList;
            }else{
                break;
            }
            dataSet = new DataSet<Record<Double>>(cloneSet.getAll());
            count++;
        }while(true);
        System.out.println("count..."+count);
        printClusters(clusters);
        return clusters;
    }
    private void assignRecords(DataSet<Record<Double>> dataSet,ArrayList<KMedoidCluster> clusters,List<Record<Double>> arbiList){
        Record<Double> curRec = null;
        int mostFreqIndex = 0;
        for(int j=0;j<arbiList.size();j++){
            clusters.add(new KMedoidCluster());
        }
        for(int i=0;i<dataSet.length();i++){
            curRec = dataSet.get(i);
            mostFreqIndex = getMostFreqIndex(curRec,arbiList);
            clusters.get(mostFreqIndex).addRecord(curRec);
        }
        
    }
    private List<Record<Double>> generateNewArbitraryList(DataSet<Record<Double>> dataSet,ArrayList<KMedoidCluster> clusters){
        // to be impl
        DataSet<Record<Double>> usedSet = new DataSet<Record<Double>>(dataSet.getAll());
        List<Record<Double>> arbiList = new ArrayList<Record<Double>>(clusters.size());
        // get the most frequest record and set as  arbi for each cluster
        int clusterCount = clusters.size();
        int dataSetCount = usedSet.length();
        Record<Double> nextMedoid = null;
        Record<Double> curMedoid = null;
        
        Double cost = new Double("0");
        Double curCost = new Double("0");
        for(int i=0;i<clusterCount;i++){
            dataSetCount = usedSet.length();
            nextMedoid = usedSet.get(0);
            cost = getCost(nextMedoid,clusters.get(i));
            for(int j=0;j<dataSetCount;j++){
                curMedoid = usedSet.get(j);
                curCost = getCost(curMedoid,clusters.get(i));
                if(cost>curCost){
                    nextMedoid = curMedoid;
                }
            }
            arbiList.add(nextMedoid);
            usedSet.remove(nextMedoid);
        }
        return arbiList;
    }
    private boolean testForArbitraryChange(List<Record<Double>> newList,List<Record<Double>> oldList){
        boolean changed = false;
        int listSize = newList.size();
        for(int i=0;i<listSize;i++){
            if(!newList.get(i).equals(oldList.get(i))){
                changed = true;
                break;
            }
        }
        return changed;
    }
    private int getMostFreqIndex(Record<Double> curRec,List<Record<Double>> arbiList){
        int index = 0;
        Double minDistance = ClusterUtil.getDistance(curRec, arbiList.get(index));
        Double curDis = null;
        for(int i=1;i<arbiList.size();i++){
            curDis = ClusterUtil.getDistance(curRec, arbiList.get(i));
            if(minDistance>curDis){
                index = i;
                minDistance = curDis;
            }
        }
        return index;
    }
    private Double getCost(Record<Double> rec,KMedoidCluster cluster){
        Double cost = new Double("0");
        Record<Double> curRec = null;
        for(int i=0;i<cluster.length();i++){
            curRec = cluster.getRecord(i);
            cost += ClusterUtil.getDistance(rec, curRec);
        }
        return cost;
    }
    private void printClusters(ArrayList<KMedoidCluster> clusters){
        System.out.println("  Printing the clusters "+clusters.size());
        
        KMedoidCluster cur = null;
        for(KMedoidCluster c :clusters){
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
   
        new KMedoidClusterGenerator().generateClusters(dSet,30);
    }
}
