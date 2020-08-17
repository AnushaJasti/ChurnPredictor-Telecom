 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cluster.common;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author chiru
 */
public class DataSet<T> {
    	ArrayList<T> _rows;

	public DataSet(){
		_rows = new ArrayList<T>();
	}
        public DataSet(List<T> _a){
            this._rows = new ArrayList<T>(_a);
        }
	public int length(){
		return _rows.size();
	}

	public void add(T element){
		_rows.add(element);
	}
	public T get(int index){
		return _rows.get(index);
	}
        public String toString(){
            return _rows.toString();
        }
        public List<T> getAll(){
            return _rows;
        }
        public T remove(int index){
            return _rows.remove(index);
        }
        public boolean remove(T item){
            return _rows.remove(item);
        }
}