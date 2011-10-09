package lab5Source.commands;

import lab5Source.AbstractCommand;
import lab5Source.LadyBird;
import lab5Source.LadyBirdManager;

public class S_MakeLadyBirdSmallerCommand extends AbstractCommand {

	private LadyBird bird;

	@Override
	public void Execute() {
		bird = LadyBirdManager.instance().getMarkedLadyBird();

		if (bird != null && bird.getSize() > 1) {

			bird.setSize(bird.getSize() - 1, true);
		}
	}

	@Override
	public void Unexecute() {
		if (bird != null && bird.getSize() != 1)
			bird.setSize(bird.getSize() + 1, true);
	}

}
