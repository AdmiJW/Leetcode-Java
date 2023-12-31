package Hard;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/course-schedule-iii/
/*
 * I struggled to solve this problem lol. This is a greedy problem.
 * Hard to come up with idea, solution is easy to implement however
 * 
 * Since it is greedy, you must realize the fact that, we should consider the courses taking
 * 	in ascending order of endtimes. If we were to take earlier ending courses, of course we
 * 	should do so, to maximize the available courses taken.
 * (Sort the courses by ascending end time)
 * 
 * Now, let's say we are considering a new course to taken. We know for the fact:
 * 		>	Current course end time must be larger than all taken courses previously
 * There can be 2 cases:
 * 		>	If taking this course doesn't make us exceed the endtime, means adequate time
 * 			to finish this course, take it
 * 
 * 		>	However, if exceeds the course's endtime, we will going to do the following:
 * 			We will try to remove a course from previously taken ones, and insert this one.
 * 			>	Of course, if we want to remove a course, we want to ensure courses once removed,
 * 				will make enough time for current one to be taken.
 * 				(removed course duration >= current one)
 * 
 * 			>	We cannot remove more than 1 courses. If we remove 2 courses to fit the current one,
 * 				it is a bad move
 * 
 * 			>	Be greedy. Try to remove the maximum duration course, so we can save the most time
 * 
 * 			If no suitable course found in previously taken courses, we have no choice but to give up on
 * 			taking the current course then
 * 		
 * 	This querying is done best via PriorityQueue. Overall the algorithm runs in O(N log N) time, taking O(N) space
 */

public class $0630_Course_Schedule_III {
	
	//	Greedy, Priority Queue solution
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (x,y)-> x[1] - y[1]);
		PriorityQueue<Integer> taken = new PriorityQueue<>((x,y)-> y-x);
		int time = 0;
		
		for (int[] course: courses) {
			if (time + course[0] <= course[1]) {
				time += course[0];
				taken.add(course[0]);
			}
			else if (!taken.isEmpty() && taken.peek() > course[0]) {
				time += course[0] - taken.poll();
				taken.add(course[0]);
			}
		}
		return taken.size();
    }
	
}
