package com.ornilabs.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Functions related to arrays.
 * 
 * @author Ornicare
 *
 */
public abstract class ArraysHelper {
	/**
	 * Try to add to Number arrays. Warning : need to use wrapper types (Integer
	 * instead of int for example)
	 * 
	 * @param t1
	 * @param t2
	 * @return The sum of the two arrays.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T[] addArrays(T[] t1, T[] t2) {
		int outSize = Math.min(t1.length, t2.length);
		if (t1.length == 0 || t2.length == 0)
			return null;
		T[] out = (T[]) new Number[outSize];
		for (int i = 0; i < outSize; ++i) {
			out[i] = (T) (Number) (t1[i].doubleValue() + t2[i].doubleValue());
		}
		return out;
	}

	/**
	 * Sort a 2d array of Number by it's <code>sortColumn</code> column
	 * 
	 * @param tab
	 * @param sortColumn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T[][] array2DArraySorter(T[][] tab,
			int sortColumn) {
		int tabLength = tab.length;
		if (tabLength == 0)
			return tab;
		for (int i = 0; i < tabLength; ++i) {
			if (sortColumn > tab[i].length - 1)
				throw new ArrayIndexOutOfBoundsException(
						"Array sort column is too high");
		}

		T[] order = (T[]) new Number[tabLength];
		for (int i = 0; i < tabLength; ++i)
			order[i] = tab[i][sortColumn];
		Arrays.sort(order);
		T[][] out = (T[][]) new Number[tabLength][];
		for (int i = 0; i < tabLength; ++i) {
			for (int j = 0; j < tabLength; ++j) {
				if (order[i] == tab[i][sortColumn])
					out[i] = tab[i];
			}
		}

		return out;
	}

	/**
	 * Sort an array of array of Number by it's <code>sortColumn</code> column
	 * 
	 * @param tab
	 * @param sortColumn
	 *            Colum which contain numbers
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] arrayOfArraysSorter(T[] tab, int sortColumn) {
		int tabLength = tab.length;
		if (tabLength == 0)
			return tab;
		for (int i = 0; i < tabLength; ++i) {
			if (!tab[i].getClass().isArray())
				throw new IllegalArgumentException(
						"Array does not contain Arrays");
			if (sortColumn > ((Object[]) tab[i]).length - 1)
				throw new ArrayIndexOutOfBoundsException(
						"Array sort column is too high");
			if (!Number.class.isAssignableFrom(((Object[]) tab[i])[sortColumn]
					.getClass()))
				throw new IllegalArgumentException(
						"Sort colum does not contain Number");
		}

		Integer[] order = new Integer[tabLength];
		for (int i = 0; i < tabLength; ++i)
			order[i] = ((Number) ((Object[]) tab[i])[sortColumn]).intValue();
		Arrays.sort(order);
		T[] out = (T[]) new Object[tabLength];
		for (int i = 0; i < tabLength; ++i) {
			for (int j = 0; j < tabLength; ++j) {
				System.out.println(order[i] + " - "
						+ ((Object[]) tab[i])[sortColumn]);
				if (order[i] == ((Object[]) tab[i])[sortColumn]) {
					out[i] = tab[i];
				}
			}
		}

		return out;
	}

	private static void DoMerge(Object[] numbers, int left, int mid, int right,
			int sortColumn) {
		int i, left_end, num_elements, tmp_pos;
		left_end = (mid - 1);
		tmp_pos = left;
		num_elements = (right - left + 1);
		Object[] temp = new Object[numbers.length];

		while ((left <= left_end) && (mid <= right)) {

			if (((Number) (((Object[]) numbers[left])[sortColumn])).intValue() <= ((Number) (((Object[]) numbers[mid])[sortColumn]))
					.intValue())
				temp[tmp_pos++] = numbers[left++];
			else
				temp[tmp_pos++] = numbers[mid++];
		}
		while (left <= left_end)
			temp[tmp_pos++] = numbers[left++];
		while (mid <= right)
			temp[tmp_pos++] = numbers[mid++];
		for (i = 0; i < num_elements; i++) {
			numbers[right] = temp[right];
			right--;
		}
	}

	/**
	 * Sort an array of array by it's sortColumn column which is a Number column
	 * (Wrapper needed).
	 * 
	 * @param numbers
	 * @param left
	 * @param right
	 * @param sortColumn
	 */
	public static void MergeSort_Recursive(Object[] numbers, int left,
			int right, int sortColumn) {
		int mid;
		if (right > left) {
			mid = (right + left) / 2;
			MergeSort_Recursive(numbers, left, mid, sortColumn);
			MergeSort_Recursive(numbers, (mid + 1), right, sortColumn);
			DoMerge(numbers, left, (mid + 1), right, sortColumn);
		}

	}

	/**
	 * Convert an array of array into a 2D array. Warning size not checked.
	 * 
	 * @param tab
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[][] convertArrayOfArrayTo2DArrays(T[] tab) {
		int tabLength = tab.length;
		if (tabLength == 0)
			return null;
		if (!tab[0].getClass().isArray())
			throw new IllegalArgumentException("Array does not contain Arrays");

		T[][] out = (T[][]) new Object[tabLength][];
		for (int i = 0; i < tabLength; ++i) {
			out[i] = (T[]) tab[i];
		}

		return out;
	}
	
	public static void copyFile(File source, File dest) throws IOException {
		
		if(!dest.exists()) {
			dest.createNewFile();
		}
        InputStream in = null;
        OutputStream out = null;
        try {
        	in = new FileInputStream(source);
        	out = new FileOutputStream(dest);
    
	        // Transfer bytes from in to out
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
        }
        finally {
        	in.close();
            out.close();
        }
        
	}
	
	public static void copyDirectory(File sourceDir, File destDir) throws IOException {
		
		if(!destDir.exists()) {
			destDir.mkdir();
		}
		
		File[] children = sourceDir.listFiles();
		System.out.println(children);
		
		for(File sourceChild : children) {
			String name = sourceChild.getName();
			File destChild = new File(destDir, name);
			if(sourceChild.isDirectory()) {
				copyDirectory(sourceChild, destChild);
			}
			else {
				copyFile(sourceChild, destChild);
			}
		}	
	}
	
	public static boolean delete(File resource) throws IOException { 
		if(resource.isDirectory()) {
			File[] childFiles = resource.listFiles();
			for(File child : childFiles) {
				delete(child);
			}
						
		}
		return resource.delete();
		
	}
}
