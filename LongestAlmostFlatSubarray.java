/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 2
 * 
 * Student Full Name:     MD OMOR FARUQUE
 * Student eecs account:  faruque1
 * Student ID number:     214801278 
 **********************************************************/

package A1;

/**
 * The purpose of this class is to find the longest almost flat contiguous
 * subarray of an input array of ints. A subarray is almost flat if no two
 * elements of it differ by more than 1.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class LongestAlmostFlatSubarray {

	/**
	 * longestAFS() returns the longest almost flat subarray of an input array
	 * of ints.
	 * 
	 * This method does not alter its input array.
	 * 
	 * @return an array int[2] of the form {start, len} representing the
	 *         longestAFS of ints[] as a contiguous subarray of length len
	 *         starting at index start.
	 * 
	 *         For example, on the input array {7, 7, 2, 8, 7, 7, 8, 8, 7, 1, 2,
	 *         1, 7, 8}, it returns {3, 6}, indicating the longest AFS of this
	 *         array is the subarray {8, 7, 7, 8, 8, 7} which starts at index 3
	 *         and has length 6.
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static int[] longestAFS(int[] ints) {

		/*
		 * For full credit, your solution should be in-place and take linear
		 * time.
		 */

		int arrLength = ints.length;

		// max and min value of the current sub array
		int max = ints[0], min = ints[0];

		int count = 1, maxCount = 1;

		// start index of the longest flat subarray
		int startIndex = 0;

		// back index is to keep track of the value before the loop index
		int backIndex = 0;

		for (int i = 1; i < arrLength; i++) {

			// checking if the two values are equal, if yes increase the counter
			if (ints[i] == ints[i - 1]) {
				count++;

			}
			// checking if the difference between two elements are 1 or -1
			else if (Math.abs(ints[i] - ints[i - 1]) == 1) {

				// checking the min and max value between the current two values
				int tmp_min = (ints[i] > ints[i - 1]) ? ints[i - 1] : ints[i];
				int tmp_max = (ints[i] > ints[i - 1]) ? ints[i] : ints[i - 1];

				// checking the min value of our current sub array
				min = (tmp_min < min) ? tmp_min : min;

				// checking the max value of the current sub array
				max = (tmp_max > max) ? tmp_max : max;

				// if the difference between max and min is greater than 1 then
				// we stop counting
				if ((max - min) > 1) {
					min = max - 1;
					count = i - backIndex + 1;
					continue;
				} else {
					backIndex = i;
				}
				count++;
			}
			// checking if the difference is more than one. if yes then stop
			// counting current sub array and restart counting again
			else if (Math.abs(ints[i] - ints[i - 1]) > 1) {
				max = ints[i];
				min = ints[i];
				count = 1;
			}

			// checking if the current count is greater than the maxCount
			if (count > maxCount) {
				maxCount = count;
				startIndex = i - maxCount + 1;
			}
		}
		// returning the start index and total count
		return new int[] { startIndex, maxCount };
	}

	/**
	 * testDrive tests longestAFS by comparing its returned result with the
	 * correct answer.
	 * 
	 * @param testArray
	 *            the test array
	 * @param answer
	 *            the correct answer to the test array
	 * 
	 */
	public static void testDrive(int[] testArray, String answer) {

		System.out.println("Longest almost flat subarray of " + TestHelper.stringInts(testArray));
		String result = TestHelper.stringInts(longestAFS(testArray));
		System.out.println("is the subarray specified as [ start index , length ] = " + result + ". \n");
		TestHelper.verify(result.equals(answer), "Wrong!!!  No brownies!");
	}

	/**
	 * main() runs test cases on your longestAFS() method. Prints summary
	 * information on basic operations and halts with an error (and a stack
	 * trace) if any of the tests fail.
	 */

	public static void main(String[] args) {

		System.out.println("Let's test longestAFS on some arrays: \n");

		// TEST 1:
		testDrive(new int[] { 7, 7, 2, 8, 7, 7, 8, 8, 7, 1, 2, 1, 7, 8 }, "[ 3 , 6 ]");

		// TEST 2:
		testDrive(new int[] { 7, 7, 2, 3, 8, 8, 8, 8, 8, 6, 1, 2, 1, 7, 8 }, "[ 4 , 5 ]");

		// TEST 3:
		testDrive(new int[] { 7, 7, 2, 8, 7, 7, 8, 8, 8, 9, 9, 6, 1, 2, 1, 7, 8 }, "[ 3 , 6 ]");

		// TEST 4:
		testDrive(new int[] { 7, 7, 2, 8, 7, 7, 8, 8, 8, 9, 9, 8, 9, 9, 6, 1, 2, 1, 7, 8 }, "[ 6 , 8 ]");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.

		// TEST 5:
		testDrive(new int[] { 7, 7, 7, 8, 7, 7, 8, 8, 8, 9, 9, 9, 8, 9, 8, 9, 8, 9 }, "[ 6 , 12 ]");

		// TEST 6:
		testDrive(new int[] { 7, 7, 7, 6, 6, 5, 5, 5, 5 }, "[ 3 , 6 ]");

		// TEST 7:
		testDrive(new int[] { 4, 5, 6, 3, 4, 8, 9, 1, 2, 4, 6, 8, 7, 8, 8, 7, 8, 9, 1, 2 }, "[ 11 , 6 ]");

		// TEST 8:
		testDrive(new int[] { 4, 5, 6, 3, 4, 3, 4, 3, 4, 4, 4, 4, 4, 4, 8, 9, 1, 2, 4, 6, 8, 7, 8, 8, 7, 8, 9, 1, 2 },
				"[ 3 , 11 ]");
	}
}