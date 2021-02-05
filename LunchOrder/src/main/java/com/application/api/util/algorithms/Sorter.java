/**
 * project name:LunchOrder
 * package name: com.application.api.util.algorithms
 * file name:Sorter.java
 *@date 2021年2月2日
 * 
 */
package com.application.api.util.algorithms;

/**
 * @author Yu Chen Yen
 *
 */
public abstract class Sorter {
	/*
	 * created by:Yu Chen Yen
	 * created date:2021年2月2日
	 * created time:上午11:22:46
	 * drescription:TODO
	 */
	 @SuppressWarnings("rawtypes")
	    public abstract void sort(Comparable[] array,int from ,int len);

	    @SuppressWarnings("rawtypes")
	    public final void sort(Comparable[] array)
	    {
	        sort(array,0,array.length);
	    }

	    @SuppressWarnings("rawtypes")
	    protected final void swap(Comparable[] array,int from ,int to)
	    {
	        Comparable tmp=array[from];
	        array[from]=array[to];
	        array[to]=tmp;
	    }
}
