package topcoder.srm630;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SuffixArrayDiv1 {

	public static void main(String[] args){
		SuffixArrayDiv1 sa = new SuffixArrayDiv1();
		System.out.println(sa.minimalCharacters(new int[]{3,0,1,2}));
		System.out.println(sa.minimalCharacters(new int[]{3,2,1,0}));
		System.out.println(sa.minimalCharacters(new int[]{0,1,2,3}));
		System.out.println(sa.minimalCharacters(new int[]{7,4,8,6,1,5,2,9,3,0}));
		System.out.println(sa.minimalCharacters(new int[]{0}));
	}
	
	public int minimalCharacters(int[] SA){
		int[] char_compare = new int[SA.length-1];
		
		List<Integer> indexList = new ArrayList<Integer>();
		for(int sa :SA){
			indexList.add(SA.length-1-sa);
		}
		
		// solve character comparison
		for(int i=0; i<indexList.size()-1; i++){
			int prev = indexList.get(i);
			int next = indexList.get(i+1);
			
			if(indexList.indexOf(next-1) > indexList.indexOf(prev-1)){
				char_compare[i] = 1;
			}
		}
		
		// count character
		int count = 1;
		for(int s : char_compare){
			if(s == 0){
				count++;
			}
		}
		
		return count;
	}
	
	public Integer[] getSuffixArray(String s){
		final String[] suffix = new String[s.length()];
		Integer[] index = new Integer[s.length()];
		
		for(int i=0; i<suffix.length; i++){
			suffix[suffix.length-i-1] = s.substring(i);
			index[i] = i;
		}
		
		Arrays.sort(index, new Comparator<Integer>() {
			@Override
			public int compare(Integer index1, Integer index2){
				return suffix[index1].compareTo(suffix[index2]);
			}
		});
		
		return index;
	}
}