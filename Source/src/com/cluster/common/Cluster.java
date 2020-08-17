 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.common;

/**
 *
 * @author chiru
 */

import java.util.ArrayList;
public interface Cluster<T>{
	public ArrayList<Record<T>> getRecords();
}
