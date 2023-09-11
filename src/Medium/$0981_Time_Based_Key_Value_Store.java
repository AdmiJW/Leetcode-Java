package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/time-based-key-value-store/
/*
 * 	This is a Map + binary search problem.
 * 
 * 
 * 	Saving the values into a hash map isn't a problem. However, each key in the hashmap has different values
 * 	at different times. Therefore we need a way to save the different versions of the values at different timestamp.
 * 
 * 	We will have a HashMap that maps key to a collection of values. The collection stores the timestamp and value
 * 	encapsulated together, and must be designed such that we can easily get the correct value based on correct timestamp given.
 * 
 * 	The problem constraint states that the set() function is always called in increasing timestamp. Therefore, we have no need
 * 	to sort the collection of values every time we add a new value - It is sorted naturally if we insert in natural order.
 * 	And since it is sorted, we can easily apply binary search to reduce the time complexity of a search from O(V) to O(log V)
 * 	(V being size of collection of values of a key)
 */

public class $0981_Time_Based_Key_Value_Store {}



class TimeMap {

	class TimeValue {
		int timestamp;
		String value;
		
		public TimeValue(String value, int timestamp) {
			this.timestamp = timestamp;
			this.value = value;
		}
        
        public String toString() {
            return this.value + " " + this.timestamp;
        }
	}
	
	
	private Map<String, List<TimeValue>> map = new HashMap<>();
	
	
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
        	map.put(key, new ArrayList<>());
        	map.get(key).add( new TimeValue("", -1));
        }
        map.get(key).add( new TimeValue(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        return search( map.get(key), timestamp );
    }
    
    
    
    String search(List<TimeValue> values, int timestamp) {
        if (values == null) return "";
    	int l = 0, r = values.size() - 1;
    	
    	while (l < r) {
    		int m = l + (r - l) / 2;
    		int t = values.get(m).timestamp;
    		
    		if (t < timestamp) l = m + 1;
    		else r = m;
    	}
    	
        if (values.get(l).timestamp <= timestamp) return values.get(l).value;
        return values.get(l - 1).value;
    }
}
