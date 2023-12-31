package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-duplicate-file-in-system/
/*
 * 	This is a HashMap problem. 
 * 
 * 	For each of the file paths, tokenize them into file path and file names by delimiter space.
 * 	Within each file names, also tokenize them into actual file name and file contents.
 * 
 * 	To check for duplicate in this problem, simply put file content as key in HashMap, and the value
 * 	will be a list of filepaths + filename string. The files in same list indicate they have the same content.
 * 
 * 	At the end, iterate over the values in HashMap and push those who have size 2 or above into result.
 * 
 * 	Time complexity is O(NL) where N is number of paths, L is length of string average
 * 	------------------------------------------------------------------------
 * 	
 * 	Follow up questions:
 * 	
 * 	1. BFS or DFS when searching in FileSystem.
 * 	
 * 	>	BFS is generally faster than DFS because BFS can take advantage of spatial locality of files inside directories.
 * 		The files inside same directory are close to each other, giving faster access time.
 * 		However, note that space taken up by BFS is dependent on width of the tree, while DFS depends on depth of the tree.
 * 		It's unlikely for a filesystem to be highly nested, so BFS takes up more memory
 * 
 * 	2. File content is large, how to compare equality?
 * 
 * 	>	Generally the solution is to compare hashes, not actual content. In real world, before comparing actual file content,
 * 		we might compare:
 * 			-	File sizes
 * 			-	Hash of smaller parts (Say, 1kb of file) and compare
 * 
 * 	3. Only able to read 1kb each file.
 * 
 * 	>	Same as above. Hash small parts and compare.
 * 
 * 	4. Make sure not false positive
 * 
 * 	>	Use several filters and only make judgement if all of them passes: File sizes, small hashes, lastly byte by byte comparison
 */

public class $0609_Find_Duplicate_File_in_System {
	
	public List<List<String>> findDuplicate(String[] paths) {
		Map<String, List<String>> duplicates = new HashMap<>();
		for (String s: paths) {
			String[] tokens = s.split(" ");
			for (String t: tokens) {
				if (t.charAt(t.length() - 1) != ')') continue;		//	Skip path
				int delimIndex = t.lastIndexOf('(');
				String fileContent = t.substring(delimIndex+1);
				
				duplicates.putIfAbsent(fileContent, new ArrayList<>() );
				duplicates.get(fileContent).add( tokens[0] + '/' + t.substring(0, delimIndex));
			}
		}
		
		List<List<String>> res = new ArrayList<>();
		for (List<String> vals: duplicates.values())
			if (vals.size() >= 2) res.add(vals);
		return res;
    }
	
}
