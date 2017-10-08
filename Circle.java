/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 3
 * 
 * Student Full Name:     MD OMOR FARUQUE
 * Student eecs account:  faruque1
 * Student ID number:     214801278 
 **********************************************************/
package A1;

import java.awt.geom.Point2D;

public class Circle extends Ellipse {

	private double radius;

	/**
	 * default constructor, zero parameter
	 */
	public Circle() {

		this.center = new Point2D.Double(1.0, 1.0);
		this.radius = 5.0;

	}

	/**
	 * @param center
	 *            is a Point2D.Double value
	 * @param radius
	 *            is a double value
	 * @throws InvalidShapeException
	 *             if the radius value is negative
	 */
	public Circle(Point2D.Double center, double radius) throws InvalidShapeException {
		// this method is inherited
		setCenter(center);
		
		setRadius(radius);
	}

	/**
	 * @return the radius value of the circle
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            is a double value
	 * @throws InvalidShapeException
	 *             if the radius value is negative or zero
	 */
	public void setRadius(double radius) throws InvalidShapeException {

		if (radius <= 0) {
			throw new InvalidShapeException("Error occurd!\nInvalid radius!");
		} else {
			this.radius = radius;
		}
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", center=" + center.x + "," + center.y + "]";
	}

	@Override
	public double area() {

		return Math.PI * this.radius * this.radius;
	}

	@Override
	public boolean contains(Point2D.Double p) {
		// x,y coordinates of the current circle
		double xC = this.center.x, yC = this.center.y;
		
		// x,y coordinates of the given points
		double x = p.x, y = p.y;
		// calculates the distance between two points
		double distance = Math.sqrt(Math.pow((x - xC), 2) + Math.pow((y - yC), 2));
		// returns true if the point is inside and false if not
		return distance < this.radius;
	}

	/**
	 * @param c
	 *            is a Circle type value
	 * @return returns true if the given circle is inside this circle or false
	 *         if not
	 */
	public boolean contains(Circle c) {
		// variable holding the value if the circle is inside or not
		boolean contains = false;
		
		// x,y coordinates of the current circle
		double xC = this.center.x, yC = this.center.y;
		
		// x,y coordinates of the given circle
		double x = c.center.x, y = c.center.y;
		
		// calculating the distance between two centers of the circle
		double distance = Math.sqrt(Math.pow((x - xC), 2) + Math.pow((y - yC), 2));
		
		double r1 = this.radius, r2 = c.radius;
		
		// checking if the given circle is inside the current circle
		if (distance <= Math.abs(r1 - r2)) {
			contains = true;
		}
		return contains;
	}

	public static void main(String[] args) {

	
		Point2D.Double pointOne = new Point2D.Double(0.0, 0.0);
		Point2D.Double pointTwo = new Point2D.Double(2.0, 2.5);
		Point2D.Double pointThree = new Point2D.Double(5.0, 6.0);
		Circle c1;
		Circle c2;
		Circle c3;
		try {
			c1 = new Circle(pointOne, 6.0);
			c2 = new Circle(new Point2D.Double(5.0, 5.0), 3.0);
			c3 = new Circle(new Point2D.Double(1.0, 1.0), 3);
			System.out.println(c1.toString());
			System.out.printf("Area of the Circle: %.4f\n", c1.area());
			System.out.println("Circle contains the point (" + pointTwo.x + ", " + pointTwo.y +")? "+c1.contains(pointTwo));
			System.out.println("Circle contains the point (" + pointThree.x + ", " + pointThree.y +")? "+c1.contains(pointThree));
			System.out.println("This Circle contains another circle with center (" + c2.center.x + ", " + c2.center.y +") and radius "+c2.getRadius()+"? "+c1.contains(c2));
			System.out.println("This Circle contains another circle with center (" + c3.center.x + ", " + c3.center.y +") and radius "+c3.getRadius()+"? "+c1.contains(c3));

		} catch (InvalidShapeException e) {
			System.out.println(e.getMessage());
		}

	}

}
