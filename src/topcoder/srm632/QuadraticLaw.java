package topcoder.srm632;

public class QuadraticLaw {

	public static void main(String[] args) {
		QuadraticLaw ql = new QuadraticLaw();
		System.out.println(ql.getTime(999999998999999999L));
	}

	public long getTime(long d){
		long val = (4*d)+1;
		long guess = (long) Math.sqrt(val);
		long guessSquared = guess * guess;
        guess = (val < guessSquared) ? guess - 1 : guess;
		long minT = ((long)guess - 1)/2;
		return minT;
	}
}
