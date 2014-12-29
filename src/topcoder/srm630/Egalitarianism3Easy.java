package topcoder.srm630;

import java.util.HashSet;
import java.util.Set;

public class Egalitarianism3Easy {
	
	public static void main(String[] args){
		Egalitarianism3Easy ee = new Egalitarianism3Easy();
		System.out.println(ee.maxCities(4, new int[]{1,1,1}, new int[]{2,3,4}, new int[]{1,1,1}));
		System.out.println(ee.maxCities(6, new int[]{1,2,3,2,3}, new int[]{2,3,4,5,6}, new int[]{2,1,3,2,3}));
		System.out.println(ee.maxCities(10, new int[]{1,1,1,1,1,1,1,1,1}, new int[]{2,3,4,5,6,7,8,9,10}, new int[]{1000,1000,1000,1000,1000,1000,1000,1000,1000}));
	}

	public int maxCities(int n, int[] a, int[] b, int[] len){
		// return 1, if there is only one city
		if(n == 1){
			return 1;
		}
		
		// sort the road so it can be chained
		sortChainingRoad(n, a, b, len);
		
		int size = (n)*(n-1)/2; 
		String[] paths = new String[size];
		int[] distance = new int[size];

		// create path from city to another city
		int count = 0;
		for(int i = 0; i<a.length; i++){
			char c1  = String.valueOf(a[i]-1).charAt(0);
			char c2  = String.valueOf(b[i]-1).charAt(0);
			int c1_to_c2_dist = len[i];

			for(int k=count-1; k>=0; k--){
				// c2..c1 + c1..cx = c2..cx
				if(paths[k].charAt(0) == c1){
					paths[count] = c2+paths[k];
					distance[count] = distance[k]+ c1_to_c2_dist;
					count++;
				}
				// cx..c1 + c1..c2 + = cx..c2
				else if(paths[k].charAt(paths[k].length()-1) == c1){
					paths[count] = paths[k]+c2;
					distance[count] = distance[k]+ c1_to_c2_dist;
					count++;
				}
				// c1..c2 + c2..cx = c1..cx
				else if(paths[k].charAt(0) == c2){
					paths[count] = c1+paths[k];
					distance[count] = distance[k]+ c1_to_c2_dist;
					count++;
				}
				// cx..c2 + c2..c1 + = cx..c1
				else if(paths[k].charAt(paths[k].length()-1) == c2){
					paths[count] = paths[k]+c1;
					distance[count] = distance[k]+ c1_to_c2_dist;
					count++;
				}
			}
			
			paths[count] = c1+""+c2;
			distance[count] = c1_to_c2_dist;
			count++;
		}
		
		// search global max number of cities which all pairs have same distance
		// pick one city as start point
		int global_max = 0;
		for(int from=0; from<n; from++ ){
			
			String[] from_to = new String[n-1];
			int[] dist_from_to = new int[n-1];
			int index = 0;
			
			// search from all over known path with current city as start point 
			for(int m=0; m<paths.length; m++){
				if(paths[m].charAt(0) == String.valueOf(from).charAt(0)){
					from_to[index] = paths[m];
					dist_from_to[index] = distance[m];
					index++;
				}else if(paths[m].charAt(paths[m].length()-1) == String.valueOf(from).charAt(0)){
					from_to[index] = new StringBuilder(paths[m]).reverse().toString();
					dist_from_to[index] = distance[m];
					index++;
				}
			}
			
			// sort known path by its distance 
			// so we can count number of path with same distance iteratively 
			sort_by_distance(from_to, dist_from_to, 0, from_to.length);
			
			Set<String> to = new HashSet<>();
			to.add(from_to[0].substring(1,2));
			int last_distance = dist_from_to[0];
			int last_max = 0;
			int max = 1;
			
			// iteratively count max number of path with same distance
			for(int i = 1; i<from_to.length; i++){
				if(dist_from_to[i] == last_distance){
					String cities = from_to[i].substring(1, 2);
					if(!to.contains(cities)){
						max++;
					}
					to.add(cities);
				}else{
					to.clear();
					String cities = from_to[i].substring(1, 2);
					to.add(cities);
					last_distance = dist_from_to[i];
					if(max > last_max){
						last_max = max;
					}
					max = 1;
				}
			}
			
			if(max > last_max){
				last_max = max;
			}
			
			// save max value
			if(last_max > global_max){
				global_max = last_max;
			}
		}
		
		return (global_max < 3) ? 2 : global_max;		
	}

	
	// sort by distance
	private void sort_by_distance(String[] fromto, int[] dist, int start, int end){
		int num = end - start;
		
		// recursive case
		if(num > 1){
			// pick pivot : just pick first element
			int pivot = dist[start];
			
			// partition around pivot 
			int pivotIndex = partition(fromto, dist, start, end, pivot);
			
			// sort left of pivot
			sort_by_distance(fromto, dist, start, pivotIndex);
			
			// sort right of pivot
			sort_by_distance(fromto, dist, pivotIndex+1, end);
		}
	}
	
	private static int partition(String[] fromto, int[] dist, int start, int end, int pivot){
		int i = start + 1;
		int j = start + 1;
		
		while(j < end){
			int d = dist[j];
			if(d < pivot){
				String temp = fromto[j];
				fromto[j] = fromto[i];
				fromto[i] = temp;
				
				int temp2 = dist[j];
				dist[j] = dist[i];
				dist[i] = temp2;
				
				i++;
				j++;
			}else{
				j++;
			}
		}
		
		// swap pivot
		String temp = fromto[i-1];
		fromto[i-1] = fromto[start];
		fromto[start] = temp;
		
		int temp2 = dist[i-1];
		dist[i-1] = dist[start];
		dist[start] = temp2;
			
		return i-1;
	}
	
	// chain city road
	private void sortChainingRoad(int n, int[] a, int[] b, int[] len){
		int i = 1;
		int j = i;
		Set<Integer> cities = new HashSet<>();
		cities.add(a[0]);
		cities.add(b[0]);
		
		while(cities.size() < n){
			while(j < a.length){
				if(cities.contains(a[j]) || cities.contains(b[j])){
					cities.add(a[j]);
					cities.add(b[j]);
					
					//swap
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					
					temp = b[i];
					b[i] = b[j];
					b[j] = temp;
					
					temp = len[i];
					len[i] = len[j];
					len[j] = temp;
					
					i++;
					j++;
				}
				j++;
			}
			
			j = i;
		}
	}
}