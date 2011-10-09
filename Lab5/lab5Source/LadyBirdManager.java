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
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JApplet;

/**
 * The class managers the ladybirds on the farm.
 * 
 * @author Peter Sunnergren
 */

public class LadyBirdManager extends Thread {
	private Vector ladyBirds;
	private LadyBird markedLadyBird;
	private static JApplet applet;
	private static LadyBirdManager instance;

	private LadyBirdManager() {
		ladyBirds = new Vector();
	}

	/**
	 * Gets an instance of the manager.
	 * 
	 * @return The manager.
	 */
	public static LadyBirdManager instance() {
		if (null != instance) {
			return instance;
		} else {
			if (null != applet) {
				instance = new LadyBirdManager();
				return instance;
			} else {
				System.out.println("Please init with an applet");
				return null;
			}
		}
	}

	/**
	 * Sets the applet and MUST be called before the manager is used for the
	 * first time.
	 * 
	 * @param a
	 *            The applet.
	 */
	public static void setApplet(JApplet a) {
		applet = a;
	}

	/**
	 * The tread method that repaints the ladybirds every half second.
	 */
	public void run() {
		while (true) {
			Iterator iter = ladyBirds.iterator();
			while (iter.hasNext()) {
				LadyBird l = (LadyBird) iter.next();
				l.nextAction();
			}
			applet.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception caught");
			}
		}
	}

	/**
	 * Creates a ladybird.
	 * 
	 * @return The new ladybird so it can be saved outside the manager.
	 */
	public LadyBird createLadyBird() {
		LadyBird l = new LadyBird();
		/**
		 * Please add code here to remove overlap between them at creation
		 */
		//TODO fixme

		ladyBirds.add(l);
		return l;
	}

	/**
	 * Removes a ladybird.
	 * 
	 * @param l
	 *            The ladybird to be removed.
	 */
	public void removeLadyBird(LadyBird l) {
		ladyBirds.removeElement(l);
	}

	/**
	 * Adds a ladybird to the farm.
	 * 
	 * @param l
	 *            The ladybird.
	 */
	public void addLadyBird(LadyBird l) {
		ladyBirds.add(l);
	}

	/**
	 * Marks the ladybird at the position described by x and y. If there is not
	 * any ladybird at position or the ladybird is the same as the old marked
	 * ladybird the marked ladybird variable is set to null.
	 * 
	 * @param x
	 *            X coordinate of position.
	 * @param y
	 *            Y coordinate of position.
	 */
	public void markLadyBirdAt(int x, int y) {
		Iterator iter = ladyBirds.iterator();
		while (iter.hasNext()) {
			LadyBird l = (LadyBird) iter.next();
			if (Point2D.distance(l.getX(), l.getY(), x, y) < l.getSize()) {
				if (markedLadyBird != l) {
					markedLadyBird = l;
					return;
				}
			}
		}
		markedLadyBird = null;
	}

	/**
	 * 
	 * Decides if there is a ladybird at the position described by x and y.
	 * 
	 * @param x
	 *            X coordinate of position.
	 * @param y
	 *            Y coordinate of position.
	 * @return True if it is one there.
	 */
	public boolean isLadyBirdAt(int x, int y) {
		Iterator iter = ladyBirds.iterator();
		while (iter.hasNext()) {
			LadyBird l = (LadyBird) iter.next();
			if (Point2D.distance(l.getX(), l.getY(), x, y) < l.getSize()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the marked ladybird.
	 * 
	 * @return The marked ladybird.
	 */
	public LadyBird getMarkedLadyBird() {
		return markedLadyBird;
	}

	/**
	 * Draws the ladybirds and a white ring around the marked ladybird.
	 * 
	 * @param g
	 *            Graphics.
	 */
	public void paint(Graphics g) {
		Iterator iter = ladyBirds.iterator();
		while (iter.hasNext()) {
			((LadyBird) iter.next()).paint(g);
		}

		if (null != markedLadyBird) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(2));
			g2.drawOval(markedLadyBird.getX() - markedLadyBird.getSize() + 1,
					markedLadyBird.getY() - markedLadyBird.getSize() + 1,
					2 * markedLadyBird.getSize() - 1,
					2 * markedLadyBird.getSize() - 1);
		}
	}
}
