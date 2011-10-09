/*
 * Created on 2005-apr-26
 *
 */
package lab5Source;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import sun.awt.geom.AreaOp.AddOp;

/**
 * The class represent the ladybird and contains how to draw it, how it turn and
 * what happens if two ladybird collide.
 * 
 * @author Peter Sunnergren
 */

public class LadyBird {
	private double angle = 0.0;
	private int x;
	private int y;
	private int goalX;
	private int goalY;
	private double turnAngle = Math.PI / 16;

	private AbstractState state;
	private LadyBirdSettings settings;

	public LadyBird() {
		settings = new LadyBirdSettings(32, Color.red, Color.black);
		settings.addObserver(S_Mediator.instance());
		goalX = x = (int) Math.round(Math.random() * 400);
		goalY = y = (int) Math.round(Math.random() * 400);
		angle = Math.round(Math.random() * 2 * Math.PI);
		state = new NullState();

	}

	/**
	 * Makes the ladybird take the next action dependent on which state it is
	 * in.
	 */
	public void nextAction() {
		state.nextAction(this);
	}

	/**
	 * Draws the ladybird.
	 * 
	 * @param g
	 *            Graphics.
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int halfGoalSize = 4;
		if (0 != goalX && 0 != goalY) {
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.black);
			g2.drawLine(goalX - halfGoalSize, goalY - 2 * halfGoalSize, goalX
					- halfGoalSize, goalY + 2 * halfGoalSize);
			g2.drawLine(goalX, goalY - 2 * halfGoalSize, goalX, goalY + 2
					* halfGoalSize);
			g2.drawLine(goalX + halfGoalSize, goalY - 2 * halfGoalSize, goalX
					+ halfGoalSize, goalY + 2 * halfGoalSize);
			g2.setColor(Color.green);
			g2.fillOval(goalX - 2 * halfGoalSize, goalY - halfGoalSize,
					4 * halfGoalSize, 2 * halfGoalSize);

		}
		g2.setColor(settings.getColor());
		g2.fillOval(x - settings.getHalfLadyBirdSize(),
				y - settings.getHalfLadyBirdSize(),
				2 * settings.getHalfLadyBirdSize(),
				2 * settings.getHalfLadyBirdSize());

		g2.setColor(settings.getDotColor());
		double a = Math.PI / (settings.getNumberOfDots() + 1);
		for (int i = 1; i <= settings.getNumberOfDots(); i++) {
			g2.fillOval(
					x
							+ (int) (Math.round((settings.getHalfLadyBirdSize() / 1.5)
									* Math.cos(angle + i * a)))
							- settings.getHalfSizeOfSpot(),
					y
							+ (int) (Math.round((settings.getHalfLadyBirdSize() / 1.5)
									* Math.sin(angle + i * a)))
							- settings.getHalfSizeOfSpot(), 2 * settings
							.getHalfSizeOfSpot(), 2 * settings
							.getHalfSizeOfSpot());
			g2.fillOval(
					x
							+ (int) (Math.round((settings.getHalfLadyBirdSize() / 1.5)
									* Math.cos(angle - i * a)))
							- settings.getHalfSizeOfSpot(),
					y
							+ (int) (Math.round((settings.getHalfLadyBirdSize() / 1.5)
									* Math.sin(angle - i * a)))
							- settings.getHalfSizeOfSpot(), 2 * settings
							.getHalfSizeOfSpot(), 2 * settings
							.getHalfSizeOfSpot());
		}
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(x - settings.getHalfLadyBirdSize() + 1,
				y - settings.getHalfLadyBirdSize() + 1,
				2 * settings.getHalfLadyBirdSize() - 1,
				2 * settings.getHalfLadyBirdSize() - 1);
		g2.drawLine(
				x
						+ (int) (Math.round(settings.getHalfLadyBirdSize()
								* Math.cos(angle))),
				y
						+ (int) (Math.round(settings.getHalfLadyBirdSize()
								* Math.sin(angle))),
				x
						- (int) (Math.round(settings.getHalfLadyBirdSize()
								* Math.cos(angle))),
				y
						- (int) (Math.round(settings.getHalfLadyBirdSize()
								* Math.sin(angle))));
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(
				x
						+ (int) (Math.round(settings.getHalfLadyBirdSize()
								* Math.cos(angle))),
				y
						+ (int) (Math.round(settings.getHalfLadyBirdSize()
								* Math.sin(angle))),
				x
						+ (int) (Math.round(settings.getHalfLadyBirdSize()
								/ 2.0 * Math.cos(angle))),
				y
						+ (int) (Math.round(settings.getHalfLadyBirdSize()
								/ 2.0 * Math.sin(angle))));

	}

	/**
	 * This method changes the ladybird position depending on how the other
	 * ladybird collide with this one.
	 * 
	 * @param other
	 *            The colliding ladybird.
	 */
	public void collide(LadyBird other) {
		if (Point2D.distance(other.getX(), other.getY(), x, y) < (other
				.getSize() + settings.getHalfLadyBirdSize())) {
			double overLapAngle = Math.atan2(other.getX() - this.x,
					other.getY() - this.y);
			double overLap = (other.getSize() + settings.getHalfLadyBirdSize())
					- Point2D.distance(this.x, this.y, other.getX(),
							other.getY());
			x = x
					- (int) (Math.round(overLap
							* Math.cos(overLapAngle - Math.PI / 2)));
			y = y
					+ (int) (Math.round(overLap
							* Math.sin(overLapAngle - Math.PI / 2)));
		}
	}

	public boolean S_hasCollide(LadyBird other) {
		if (Point2D.distance(other.getX(), other.getY(), x, y) < (other
				.getSize() + settings.getHalfLadyBirdSize())) {

			return true;
		}
		return false;
	}

	/**
	 * Turns the ladybird slightly towards the goal.
	 * 
	 * @return True if the ladybird faces the goal awhich means that it is done
	 *         turning.
	 */
	public boolean turn() {
		double distToGoal = Point2D.distance(x, y, goalX, goalY);
		if (Point2D.distance(x + distToGoal * Math.cos(angle + turnAngle), y
				+ distToGoal * Math.sin(angle + turnAngle), goalX, goalY) < Point2D
				.distance(x + distToGoal * Math.cos(angle - turnAngle), y
						+ distToGoal * Math.sin(angle - turnAngle), goalX,
						goalY)) {
			angle += turnAngle;
			if (Point2D.distance(x + distToGoal * Math.cos(angle + turnAngle),
					y + distToGoal * Math.sin(angle + turnAngle), goalX, goalY) > Point2D
					.distance(x + distToGoal * Math.cos(angle), y + distToGoal
							* Math.sin(angle), goalX, goalY)) {
				return true;
			}
		} else {
			angle -= turnAngle;
			if (Point2D.distance(x + distToGoal * Math.cos(angle - turnAngle),
					y + distToGoal * Math.sin(angle - turnAngle), goalX, goalY) > Point2D
					.distance(x + distToGoal * Math.cos(angle), y + distToGoal
							* Math.sin(angle), goalX, goalY)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Makes the ladybird take one step towards the goal.
	 * 
	 * @return True if the ladybird have reached the goal which means that is
	 *         done going..
	 */
	public boolean go() {
		int newX = x
				+ (int) (Math.round(settings.getStepSize() * Math.cos(angle)));
		int newY = y
				+ (int) (Math.round(settings.getStepSize() * Math.sin(angle)));
		if (Point2D.distance(newX, newY, goalX, goalY) < Point2D.distance(x, y,
				goalX, goalY)) {
			x = newX;
			y = newY;
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Sets the state of the ladybird.
	 * 
	 * @param s
	 *            The new current state.
	 */
	public void setState(AbstractState s) {
		state = s;
	}

	/**
	 * Sets the size.
	 * 
	 * @param size
	 *            Half the ladybirds size (radius).
	 * @param notifyObservers
	 *            TODO
	 */
	public void setSize(int size, boolean notifyObservers) {
		settings.S_setSize(size, notifyObservers);
	}

	/**
	 * Gets the size.
	 * 
	 * @return Half the ladybrids size (radius).
	 */
	public int getSize() {
		return settings.getHalfLadyBirdSize();
	}

	/**
	 * Gets the X coordinate.
	 * 
	 * @return X coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y coordinate.
	 * 
	 * @return Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the colors of the ladybird.
	 * 
	 * @param color
	 *            Color of the body.
	 * @param dotColor
	 *            Color of dots and border.
	 * @param notifyObservers
	 *            TODO
	 */
	public void setColors(Color color, Color dotColor, boolean notifyObservers) {
		settings.S_setColors(color, dotColor, notifyObservers);
	}

	/**
	 * Gets the color.
	 * 
	 * @return Color of the body.
	 */
	public Color getColor() {
		return settings.getColor();
	}

	/**
	 * Gets the color of the dots.
	 * 
	 * @return Color of the dots.
	 */
	public Color getDotColor() {
		return settings.getDotColor();
	}

	/**
	 * Set the goal for the ladybird.
	 * 
	 * @param x
	 *            X coordnate of the goal.
	 * @param y
	 *            Y coordnate of the goal.
	 */
	public void setGoal(int x, int y) {
		goalX = x;
		goalY = y;
	}
}
