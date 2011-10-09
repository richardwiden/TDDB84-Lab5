package lab5Source.commands;

import lab5Source.AbstractCommand;
import lab5Source.LadyBird;
import lab5Source.LadyBirdManager;
import lab5Source.S_Mediator;

public class S_AddLadybirdCommand extends AbstractCommand {

	private LadyBird bird;

	@Override
	public void Execute() {
		bird = LadyBirdManager.instance().createLadyBird();
		if (bird != null)
			S_Mediator.instance().addLadyBird(bird);
	}

	@Override
	public void Unexecute() {
		LadyBirdManager.instance().removeLadyBird(bird);
		if (bird != null)
			S_Mediator.instance().removeLadyBird(bird);
	}

}
