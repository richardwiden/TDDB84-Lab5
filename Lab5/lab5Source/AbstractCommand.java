/*
 * Created on 2005-apr-26
 *
 */
package lab5Source;

/**
 * An abstract class that is a super class to all the commands.
 * @author Peter Sunnergren
 */
public abstract class AbstractCommand {
	/**
	 * Executes the command.
	 */
	public abstract void Execute();
	/**
	 * Unexecutes the command, make sure to have saved enough in Execute() to be able to unexecute.
	 */
	public abstract void Unexecute();
}
