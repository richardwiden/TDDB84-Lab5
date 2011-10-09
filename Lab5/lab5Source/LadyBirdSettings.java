/*
 * Created on 2005-apr-28
 */
package lab5Source;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Observable;

/**
 * Contains the settings that can be done on a ladybird.
 * 
 * @author x05petsu
 */
public class LadyBirdSettings extends Observable {
	private int halfLadyBirdSize;
	private int halfSizeOfSpot;
	private int stepSize;
	private int numberOfDots;
	private Color color;
	private Color dotColor;

	/**
	 * Calculates and sets values depending on the input.
	 * 
	 * @param halfLadyBirdSize
	 *            The radius of the ladybird.
	 * @param color
	 *            The base color.
	 * @param dotColor
	 *            The color of the dots and lines.
	 */
	public LadyBirdSettings(int halfLadyBirdSize, Color color, Color dotColor) {
		this.halfLadyBirdSize = halfLadyBirdSize;
		this.numberOfDots = halfLadyBirdSize / 10;
		this.halfSizeOfSpot = (int) (Math.round(Point2D.distance(
				(halfLadyBirdSize / 1.5)
						* Math.cos(Math.PI / (numberOfDots + 1)),
				(halfLadyBirdSize / 1.5)
						* Math.sin(Math.PI / (numberOfDots + 1)),
				(halfLadyBirdSize / 1.5)
						* Math.cos(Math.PI / (numberOfDots + 1) * 2),
				(halfLadyBirdSize / 1.5)
						* Math.sin(Math.PI / (numberOfDots + 1) * 2))) / 2.2);
		this.stepSize = halfLadyBirdSize / 3;
		this.color = color;
		this.dotColor = dotColor;
	}

	/**
	 * Gets the color.
	 * 
	 * @return Returns the color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Gets the color of the dots.
	 * 
	 * @return Returns the color of the dots.
	 */
	public Color getDotColor() {
		return dotColor;
	}

	/**
	 * Gets the ladybird radius.
	 * 
	 * @return Returns ladybird size.
	 */
	public int getHalfLadyBirdSize() {
		return halfLadyBirdSize;
	}

	/**
	 * Gets the spot radius.
	 * 
	 * @return Returns size of spots.
	 */
	public int getHalfSizeOfSpot() {
		return halfSizeOfSpot;
	}

	/**
	 * Gets the number of dots on a ladybird.
	 * 
	 * @return Returns the number of dots.
	 */
	public int getNumberOfDots() {
		return numberOfDots;
	}

	/**
	 * Gets the size of each step.
	 * 
	 * @return Returns size of step.
	 */
	public int getStepSize() {
		return stepSize;
	}

	public void S_setSize(int size, boolean notifyObservers) {
		if (halfLadyBirdSize != size) {
			this.halfLadyBirdSize = size;
			this.numberOfDots = halfLadyBirdSize / 10;
			this.halfSizeOfSpot = (int) (Math.round(Point2D.distance(
					(halfLadyBirdSize / 1.5)
							* Math.cos(Math.PI / (numberOfDots + 1)),
					(halfLadyBirdSize / 1.5)
							* Math.sin(Math.PI / (numberOfDots + 1)),
					(halfLadyBirdSize / 1.5)
							* Math.cos(Math.PI / (numberOfDots + 1) * 2),
					(halfLadyBirdSize / 1.5)
							* Math.sin(Math.PI / (numberOfDots + 1) * 2))) / 2.2);
			this.stepSize = halfLadyBirdSize / 3;

			setChanged();
		}

		if (hasChanged() && notifyObservers)		
			notifyObservers();
		
		clearChanged();
	}

	public void S_setColors(Color color, Color dotColor, boolean notifyObservers) {
		if (!this.color.equals(color)) {
			this.color = color;
			setChanged();
		}
		if (!this.dotColor.equals(dotColor)) {
			this.dotColor = dotColor;
			setChanged();
		}

		if (hasChanged() && notifyObservers)
			notifyObservers();

		clearChanged();
	}
}
