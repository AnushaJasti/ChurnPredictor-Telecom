 package com.cluster.common;

public class Record<T>{
	private T[] _record;
	public Record(){
		_record = (T[])new Object[0];
	}

	public Record(int size){
		_record = (T[])new Object[size];
	}

	public Record(T[] _record){
		setRecord(_record);
	}

	private void setRecord(T[] _record){
		this._record = _record;
	}
	public T[] getRecord(){
		return this._record;
	}

	public int getSize(){
		int size = -1;
		if( _record !=null){
			size = _record.length;
		}
		return size;
	}
	public void setAttribute(int index,T attribute){
		if(_record!=null){
			if(index>=0 && index<_record.length){
				_record[index] = attribute;
			}
		}
	}
	public T getAttribute(int index){
		T attr = null;
		if(_record!=null){
			attr = _record[index];
		}
		return attr;
	}
        public boolean equals(Record<T> obj){
            boolean equals= true;
            for(int i=0;i<this.getSize();i++){
                if(!this.getAttribute(i).equals(obj.getAttribute(i))){
                    equals = false;
                    break;
                }
            }
            return equals;
        }
        public String toString(){
            String to = "";
            for(T item:_record){
                to += item +"  ";
            }
            return to;
        }
        public static void main(String args[]){
            Record<Double> r = new Record<Double>(4);
            r.setAttribute(0,1.0);
            r.setAttribute(1,1.0);
            r.setAttribute(2,4.0);
            r.setAttribute(4,new Double(10));
            for(int i=0;i<r.getSize();i++){
                System.out.println(" "+r.getAttribute(i));
            }
            System.out.println("ok");
        }
}