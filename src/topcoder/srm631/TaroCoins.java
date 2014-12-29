package topcoder.srm631;

public class TaroCoins {
	public static void main(String[] args){
		TaroCoins tc = new TaroCoins();
		System.out.println(tc.getNumber(1L));
		System.out.println(tc.getNumber(6L));
		System.out.println(tc.getNumber(47L));
		System.out.println(tc.getNumber(256L));
		System.out.println(tc.getNumber(8489289L));
		System.out.println(tc.getNumber(1000000000L));
	}
	
	public long getNumber(long N){
		
		int shift = 1;
		long T1 = 1;
		long T2 = 0;
		
		do{
			if((N & 1) == 1){
				// calculate new value
				long temp_T1 = T1;
				T1 = shift * T1 + T2;
				T2 = (shift-1) * temp_T1 + T2;
				
				// reset shift value
				shift = 1;
			}else{
				shift++;
			}
		}while((N >>= 1) > 0);
		
		return T1;
	}
}
