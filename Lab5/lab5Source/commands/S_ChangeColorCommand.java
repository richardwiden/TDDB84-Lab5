package lab5Source.commands;

import java.awt.Color;

import lab5Source.AbstractCommand;
import lab5Source.LadyBird;
import lab5Source.LadyBirdManager;

public class S_ChangeColorCommand extends AbstractCommand {

	private LadyBird bird;

	@Override
	public void Execute() {
		bird = LadyBirdManager.instance().getMarkedLadyBird();

		if (bird != null) {
			Color color = bird.getColor();
			Color dotColor = bird.getDotColor();

			if (color.equals(Color.red))
				color = Color.blue;
			else if (color.equals(Color.blue))
				color = Color.red;

			if (dotColor.equals(Color.black))
				dotColor = Color.yellow;
			else if (dotColor.equals(Color.yellow))
				dotColor = Color.black;

			bird.setColors(color, dotColor, true);
		}

	}

	@Override
	public void Unexecute() {
		if (bird != null) {
			Color color = bird.getColor();
			Color dotColor = bird.getDotColor();

			if (color.equals(Color.red))
				color = Color.blue;
			else if (color.equals(Color.blue))
				color = Color.red;

			if (dotColor.equals(Color.black))
				dotColor = Color.yellow;
			else if (dotColor.equals(Color.yellow))
				dotColor = Color.black;

			bird.setColors(color, dotColor, true);
		}
	}

}
