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

public class Ellipse implements PlanarShape {

	// x,y coordinates of the center of the ellipse
	protected Point2D.Double center;
	
	// horizontal axis of the ellipse
	private double hAxis;
	
	// vertical axis of the ellipse
	private double vAxis;

	public Ellipse() {

		this.center = new Point2D.Double(1.0, 1.0);
		this.hAxis = 2.0;
		this.vAxis = 1.0;
	}

	/**
	 * @param center
	 *            is a Point2D.Double value
	 * @param h_axis
	 *            is a double value
	 * @param v_axis
	 *            is a double value
	 * @throws InvalidShapeException
	 *             if the horizontal axis value or vertical axis value are
	 *             negative or equal to each other
	 */
	public Ellipse(Point2D.Double center, double h_axis, double v_axis) throws InvalidShapeException {
		if (h_axis <= 0 || v_axis <= 0 || center == null) {
			throw new InvalidShapeException("Error occured!\nInvalid axis value or null center coordinates!");
		} else {
			this.center = center;
			this.hAxis = h_axis;
			this.vAxis = v_axis;
		}
	}

	/**
	 * @return returns the center coordinates
	 */
	public Point2D.Double getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            is a Point2D.Double value
	 */
	public void setCenter(Point2D.Double center) throws InvalidShapeException {
		if (center == null) {
			throw new InvalidShapeException("Error occured!\n null center coordinates!");
		} else {
			this.center = center;
		}
	}

	/**
	 * @return returns the horizontal axis value
	 */
	public double gethAxis() {
		return hAxis;
	}

	/**
	 * @param hAxis
	 * @throws InvalidShapeException
	 *             sethAxis method throws InvalidShapeException if the value is
	 *             negative or the horizontal axis is equal to the vertical axis
	 */
	public final void sethAxis(double hAxis) throws InvalidShapeException {
		if (hAxis <= 0) {
			throw new InvalidShapeException("Horizontal axis value is not valid!");
		} else {
			this.hAxis = hAxis;
		}
	}

	/**
	 * @return returns the vertical axis value
	 */
	public double getvAxis() {
		return vAxis;
	}

	/**
	 * @param vAxis
	 * @throws InvalidShapeException
	 *             setvAxis method throws InvalidShapeException if the value is
	 *             negative or the horizontal axis is equal to the vertical axis
	 */
	public final void setvAxis(double vAxis) throws InvalidShapeException {
		if (vAxis <= 0) {
			throw new InvalidShapeException("Horizontal axis value is not valid!");
		} else {
			this.vAxis = vAxis;
		}
	}

	@Override
	public String toString() {
		return "Ellipse [center=" + center.x + "," + center.y + ", hAxis=" + hAxis + ", vAxis=" + vAxis + "]";
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return Math.PI * this.vAxis * this.hAxis;
	}

	@Override
	public boolean contains(Point2D.Double p) {

		// x,y coordinates of given point
		double x = p.x, y = p.y;
		
		// horizontal and vertical axis of current ellipse
		double a = this.hAxis, b = this.vAxis;
		
		// center coordinates of the current ellipse
		double xC = center.x, yC = center.y;
		
		// calculation of the first part of the formula
		double firstHalf = (x - xC) / a;
		
		// calculation of the second part of the formula
		double secondHalf = (y - yC) / b;
		
		// calculation of the complete formula
		double result = Math.pow(firstHalf, 2) + Math.pow(secondHalf, 2);
		
		return (result <= 1);
	}

	public static void main(String[] args) {
		
		Point2D.Double pointOne = new Point2D.Double(0.0, 0.0);
		Point2D.Double pointTwo = new Point2D.Double(2.0, 2.5);
		Point2D.Double pointThree = new Point2D.Double(3.0, 3.0);
		Ellipse e1;
		try {
			e1 = new Ellipse(pointOne, 5.0, 3.0);
			System.out.println(e1.toString());
			System.out.printf("Area of the Ellipse: %.4f\n", e1.area());
			
			// checking if the pointTwo is inside the ellipse
			System.out.println(
					"This Ellipse contains the point (" + pointTwo.x + ", " + pointTwo.y + ")? " + e1.contains(pointTwo));
			System.out.println("This Ellipse contains the point (" + pointThree.x + ", " + pointThree.y + ")? "
					+ e1.contains(pointThree));

		} catch (InvalidShapeException e) {
			System.out.println(e.getMessage());
		}

	}

}
