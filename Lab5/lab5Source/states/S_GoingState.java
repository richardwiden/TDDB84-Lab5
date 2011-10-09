package lab5Source.states;

import lab5Source.AbstractState;
import lab5Source.LadyBird;
import lab5Source.NullState;
import lab5Source.S_Mediator;

public class S_GoingState extends AbstractState {

	private static S_GoingState instance;

	private S_GoingState() {

	}

	public static S_GoingState instance() {
		if (instance == null) {
			instance = new S_GoingState();
		}
		return instance;
	}

	@Override
	public void nextAction(LadyBird l) {
		if (l.go()) {
			l.setState(new NullState());
		} else {
			S_Mediator.instance().checkMovement(l,0);
			l.setState(S_TurningState.instance());
		}
	}
}
