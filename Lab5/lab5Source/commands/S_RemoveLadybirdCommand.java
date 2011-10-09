package lab5Source.commands;

import lab5Source.AbstractCommand;
import lab5Source.LadyBird;
import lab5Source.LadyBirdManager;
import lab5Source.S_Mediator;

public class S_RemoveLadybirdCommand extends AbstractCommand {

	private LadyBird bird;

	@Override
	public void Execute() {
		bird = LadyBirdManager.instance().getMarkedLadyBird();

		if (bird != null) {
			LadyBirdManager.instance().removeLadyBird(bird);
			S_Mediator.instance().removeLadyBird(bird);
		}

	}

	@Override
	public void Unexecute() {
		if (bird != null) {
			LadyBirdManager.instance().addLadyBird(bird);
			S_Mediator.instance().addLadyBird(bird);
		}
	}

}
