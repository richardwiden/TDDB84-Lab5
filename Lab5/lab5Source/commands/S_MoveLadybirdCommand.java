package lab5Source.commands;

import lab5Source.AbstractCommand;
import lab5Source.LadyBird;
import lab5Source.LadyBirdManager;
import lab5Source.states.S_TurningState;

public class S_MoveLadybirdCommand extends AbstractCommand {

	private LadyBird bird;
	private int x, xPre;
	private int y, yPre;

	public S_MoveLadybirdCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void Execute() {
		bird = LadyBirdManager.instance().getMarkedLadyBird();
		if (bird != null) {
			this.xPre = bird.getX();
			this.yPre = bird.getY();
			bird.setGoal(x, y);
			bird.setState(S_TurningState.instance());
		}

	}

	@Override
	public void Unexecute() {
		if (bird != null) {
			bird.setGoal(xPre, yPre);
			bird.setState(S_TurningState.instance());
		}

	}
}
