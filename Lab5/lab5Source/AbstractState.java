/*
 * Created on 2005-apr-26
 *
 */
package lab5Source;

/**
 * Abstract super class to all the states.
 * @author Peter Sunnergren
 */
public abstract class AbstractState {
	protected AbstractState nextState;
	
	/**
	 * Sets what the states after this one should be.
	 * @param s The next state.
	 */
	public void setNextState(AbstractState s) {
		nextState = s;
	}
	/**
	 * Does the next action decided by the state.
	 * @param l The ladybird that does the next action.
	 */
	public abstract void nextAction(LadyBird l);	
}
