package topcoder.srm630;

public class DoubleLetter {

	public static void main(String[] args) {
		DoubleLetter dl = new DoubleLetter();
		System.out.println(dl.ableToSolve("aabccb"));
		System.out.println(dl.ableToSolve("aabccbb"));
		System.out.println(dl.ableToSolve("abcddcba"));
		System.out.println(dl.ableToSolve("abab"));
		System.out.println(dl.ableToSolve("aaaaaaaaaa"));
		System.out.println(dl.ableToSolve("aababbabbaba"));
		System.out.println(dl.ableToSolve("zzxzxxzxxzzx"));
	}

	public String ableToSolve(String S){ 
		String res = reduce(S, 0, S.length());
		if(res.isEmpty()){
			return "Possible";
		}else{
			return "Impossible";
		}
	}
	
	public String reduce(String s, int start, int end){
		int count = end - start;
		if( count > 1){
			int mid = start + count/2;
			String left  = reduce(s, start, mid);
			String right = reduce(s, mid, end);
			return merge(left, right);
		}else{
			return s.substring(start, end);
		}
	}
	
	public String merge(String left, String right){
		int llength = left.length();
		int rlength = right.length();
		int count = (llength > rlength) ? rlength : llength;
		
		int i=0;
		for(; i<count; i++){
			if(left.charAt(llength-1-i) != right.charAt(i)){
				break;
			}
		}
		
		return left.substring(0, llength-i) + right.substring(i, rlength); 
	}
}