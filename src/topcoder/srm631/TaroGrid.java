package topcoder.srm631;

public class TaroGrid {

	public static void main(String[] args) {
		TaroGrid tg = new TaroGrid();
		System.out.println(tg.getNumber(new String[]{"W"}));
		System.out.println(tg.getNumber(new String[]{"WB","BW"}));
		System.out.println(tg.getNumber(new String[]{"BWW","BBB","BWB"}));
		System.out.println(tg.getNumber(new String[]{"BWBW","BBWB","WWWB","BWWW"}));
		System.out.println(tg.getNumber(new String[]{"BWB","BBW","BWB"}));
		System.out.println(tg.getNumber(new String[]{"BBWWBBWW","BBWWBBWW","WWBBWWBB","WWBBWWBB","BBWWBBWW","BBWWBBWW","WWBBWWBB","WWBBWWBB"}));
	}

	public int getNumber(String[] grid) {
		int max = 0;
		int col_size = grid[0].length();
		int row_size = grid.length;

		
		for(int col=0; col<col_size; col++){
			int row = 0;
			int count = 1;
			char current = grid[row].charAt(col);
			
			for(row=1; row<row_size; row++){
				char next = grid[row].charAt(col);
				if(current == next){
					count++;
				}else{
					
					if(max < count){
						max = count;
					}
					
					count = 1;
				}
				current = next;
			}
			
			if(max < count){
				max = count;
			}
		}
		
		return max;
	}
}
