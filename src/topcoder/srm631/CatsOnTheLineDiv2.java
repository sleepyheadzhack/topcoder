package topcoder.srm631;

public class CatsOnTheLineDiv2 {
	
	public static void main(String[] args){
		CatsOnTheLineDiv2 cd = new CatsOnTheLineDiv2();
		System.out.println(cd.getAnswer(new int[]{0}, new int[]{7}, 3));
		System.out.println(cd.getAnswer(new int[]{0}, new int[]{8}, 2));
		System.out.println(cd.getAnswer(new int[]{0,1}, new int[]{3,1}, 0));
		System.out.println(cd.getAnswer(new int[]{5, 0, 2}, new int[]{2, 3, 5}, 2));
		System.out.println(cd.getAnswer(new int[]{5, 1, -10, 7, 12, 2, 10, 20}, new int[]{3, 4, 2, 7, 1, 4, 3, 4}, 6));
		System.out.println(cd.getAnswer(new int[]{-1000, -999}, new int[]{1000, 1000}, 999));
	}
	
	public String getAnswer(int[] position, int[] count, int time){
		boolean possible = true;
		
		sort_by_position(position, count, 0, position.length);
		
		int latest_position = Integer.MIN_VALUE;
		
		for(int i=0; i<position.length; i++){
			int min_scatter_time = count[i]/2;
			
			if(min_scatter_time > time){
				possible = false;
				break;
				
			}else{
				int min_first_position = position[i] - time;
				int max_first_position = position[i] + time - (count[i]-1);
				
				if(latest_position >= max_first_position){
					possible = false;
					break;
				}
				
				if(min_first_position <= latest_position){
					latest_position = latest_position+1 + (count[i]-1);
				}else{
					latest_position = min_first_position + (count[i]-1);
				}
			}
			
		}
		
		return possible ? "Possible":"Impossible";
	}
	
	
	private void sort_by_position(int[] position, int[] count, int start, int end){
		int num = end - start;
		
		if(num > 1){
			// pick pivot : just pick first element
			int pivot = position[start];
			
			// partition around pivot 
			int pivotIndex = partition(position, count, start, end, pivot);
			
			// sort left of pivot
			sort_by_position(position, count, start, pivotIndex);
			
			// sort right of pivot
			sort_by_position(position, count, pivotIndex+1, end);
		}
	}
	
	private static int partition(int[] position, int[] count, int start, int end, int pivot){
		int i = start + 1;
		int j = start + 1;
		
		while(j < end){
			int d = position[j];
			if(d < pivot){
				int temp = position[j];
				position[j] = position[i];
				position[i] = temp;
				
				temp = count[j];
				count[j] = count[i];
				count[i] = temp;
				
				i++;
				j++;
			}else{
				j++;
			}
		}
		
		// swap pivot
		int temp = position[i-1];
		position[i-1] = position[start];
		position[start] = temp;
		
		temp = count[i-1];
		count[i-1] = count[start];
		count[start] = temp;
			
		return i-1;
	}
}
