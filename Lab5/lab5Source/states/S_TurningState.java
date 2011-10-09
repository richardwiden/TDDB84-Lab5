package lab5Source.states;

import lab5Source.AbstractState;
import lab5Source.LadyBird;
import lab5Source.LadyBirdManager;

public class S_TurningState extends AbstractState {
	private static S_TurningState instance;

	private S_TurningState() {

	}

	public static S_TurningState instance() {
		if (instance == null) {
			instance = new S_TurningState();
		}
		return instance;
	}

	@Override
	public void nextAction(LadyBird l) {		
		if (l.turn()) {
			l.setState(S_GoingState.instance());
		}
	}

}
