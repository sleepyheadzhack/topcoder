package topcoder.srm630;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SuffixArrayDiv2 {
	
	public static void main(String[] args){
		SuffixArrayDiv2 sa = new SuffixArrayDiv2();
		System.out.println(sa.smallerOne("abca"));
		System.out.println(sa.smallerOne("bbbb"));
		System.out.println(sa.smallerOne("aaaa"));
		System.out.println(sa.smallerOne("examplesareveryweakthinktwicebeforesubmit"));
		System.out.println(sa.smallerOne("acbca"));
	}
	
	public String smallerOne(String s){
		Integer[] index = getSuffixArray(s);
		int[] char_compare = new int[index.length-1];
		
		// solve character comparison
		// char_compare == 1 : <=
		List<Integer> indexList = Arrays.asList(index);
		for(int i=0; i<indexList.size()-1; i++){
			int prev = indexList.get(i);
			int next = indexList.get(i+1);
			
			if(indexList.indexOf(next-1) > indexList.indexOf(prev-1)){
				char_compare[i] = 1;
			}
		}
		
		char current = 'a';
		char[] smallestOne = new char[s.length()];
		smallestOne[s.length()-(index[0]+1)] = current;
		
		// build smallest one string
		for(int i=1; i<char_compare.length+1; i++){
			int pos = s.length()-(index[i]+1);
			
			if(char_compare[i-1] == 1){
				smallestOne[pos] = current;
			}else{
				current = (char)(current+1);
				smallestOne[pos] = current;
			}
		}
		
		String smallest = new String(smallestOne);
		return smallest.equals(s) ? "Does not exist" : "Exists";
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
