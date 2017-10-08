/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 3
 * 
 * Student Full Name:     MD OMOR FARUQUE
 * Student eecs account:  faruque1
 * Student ID number:     214801278 
 **********************************************************/
package A1;
/**
 * This is an interface. The class will 
 * implement the methods.
 * 
 */

import java.awt.geom.Point2D;

public interface PlanarShape {
	public double area();
	public boolean contains(Point2D.Double p);
}
