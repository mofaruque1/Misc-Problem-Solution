/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 3NExt 
 * 
 * Student Full Name:     MD OMOR FARUQUE
 * Student eecs account:  faruque1
 * Student ID number:     214801278 
 **********************************************************/
package A1;

/**
 * The purpose of this class is to define our own Exception class
 * 
 */

public class InvalidShapeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor calls super method with error
	 * message
	 */
	public InvalidShapeException() {
		super("Invalid Shape Exception");
	}
	
	/**
	 * constructor with a String parameter 
	 */

	public InvalidShapeException(String message) {
		super(message);
	}
}
