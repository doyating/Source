/**
 * project name:LunchOrder
 * package name: com.application.api.util.algorithms
 * file name:QuickSorter.java
 *@date 2021年2月2日
 * 
 */
package com.application.api.util.algorithms;
import com.application.api.util.DateUtil;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedHashMap;
/**
 * @author Yu Chen Yen
 *
 */
public class QuickSorter extends Sorter {

	@Override
	@SuppressWarnings("rawtypes")
	public void sort(Comparable[] array, int from, int len) {
		q_sort(array, from, from + len - 1);

	}
	/*
	 * created by:Yu Chen Yen
	 * created date:2021年2月2日
	 * created time:下午1:30:57
	 * drescription:TODO
	 */

	 @SuppressWarnings("rawtypes")
	    public void sortObject(Object[] array, int from, int len) throws Exception {
	        Comparable[] tempComparable = new Comparable[array.length];
	        for (int i = 0; i < array.length; i++) {
	            try {
	                tempComparable[i] = (Comparable) array[i];
	            } catch (Exception e) {
	                throw new Exception(e.getMessage());
	            }
	        }
	        q_sort(tempComparable, from, from + len - 1);
	        System.arraycopy(tempComparable, 0, array, 0, tempComparable.length);
	    }

	    public LinkedHashMap<Object, String> sortObjectMap(LinkedHashMap<Timestamp, String> map) throws Exception {
	        Object[] array = map.keySet().toArray();
	        sortObject(array, 0, array.length);
	        LinkedHashMap<Object, String> resutlMap = new LinkedHashMap<Object, String>();
	        for(int i = 0;i<array.length;i++){
	           resutlMap.put(array[i],map.get(array[i]));
	        }
	        return resutlMap;
	    }

	    public void sortObjectArray(Object[] array) throws Exception {
	        sortObject(array, 0, array.length);
	    }

	    @SuppressWarnings("rawtypes")
	    public void sortArray(Comparable[] array) throws Exception {
	        sort(array, 0, array.length);
	    }

	    @SuppressWarnings("rawtypes")
	    private final void q_sort(Comparable[] array, int from, int to) {
	        if (to - from < 1) return;
	        int pivot = selectPivot(array, from, to);
	        pivot = partion(array, from, to, pivot);
	        q_sort(array, from, pivot - 1);
	        q_sort(array, pivot + 1, to);
	    }

	    @SuppressWarnings({"rawtypes", "unchecked"})
	    private int partion(Comparable[] array, int from, int to, int pivot) {
	        Comparable< ? > tmp = array[pivot];
	        array[pivot] = array[to];//now to's position is available

	        while (from != to) {
	            while (from < to && array[from].compareTo(tmp) <= 0) from++;
	            if (from < to) {
	                array[to] = array[from];//now from's position is available
	                to--;
	            }
	            while (from < to && array[to].compareTo(tmp) >= 0) to--;
	            if (from < to) {
	                array[from] = array[to];//now to's position is available now
	                from++;
	            }
	        }
	        array[from] = tmp;
	        return from;
	    }

	    @SuppressWarnings("rawtypes")
	    private int selectPivot(Comparable[] array, int from, int to) {
	        return (from + to) / 2;
	    }
	/**
	 * @param args
	 * created date:2021年2月2日
	 * created time:下午1:30:57
	 * return type:void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
