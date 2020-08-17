 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

/**
 *
 * @author chiru
 */
public class MathUtil {
     public static Double getMean(Double[] items){
        Double total = new Double(0);
        for(Double item:items){
            total += item;
        }
        return total/items.length;
    }
     public static Double truncate(Double d,int len){
         String dStr = new String(d.toString());
         int decLen = dStr.length() - dStr.indexOf('.') -1;
         if(decLen>len){
             dStr = dStr.substring(0,len+dStr.indexOf('.')+1);
         }
         return new Double(dStr);
     }
     public static void main(String args[]){
         System.out.println("  "+truncate(3.34677644,10));
     }
}
