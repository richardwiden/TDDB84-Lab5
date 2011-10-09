package lab5Source;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import lab5Source.states.S_TurningState;

public class S_Mediator implements Observer {
	private static S_Mediator instance;
	private Vector<LadyBird> list;

	private S_Mediator() {
		list = new Vector<LadyBird>();
	}

	public void addLadyBird(LadyBird l) {
		list.add(l);
	}

	public void removeLadyBird(LadyBird l) {
		list.remove(l);
	}

	public void checkMovement(LadyBird l, int depth) {
		if(depth>10)
			return;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (!list.get(i).equals(l)) {
				
				if(list.get(i).S_hasCollide(l))
				{
					list.get(i).collide(l);
					list.get(i).setState(S_TurningState.instance());					
				}

				for (int j = 0; j < size; j++) {
					if (i != j && list.get(i).S_hasCollide(list.get(j))) {
						checkMovement(list.get(i),++depth);
					}
				}
			}
		}
	}

	public static S_Mediator instance() {
		if (instance == null) {
			instance = new S_Mediator();
		}
		return instance;
	}

	@Override
	public void update(Observable settings, Object arg1) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			list.get(i).setColors(((LadyBirdSettings) settings).getColor(),
					((LadyBirdSettings) settings).getDotColor(), false);
			list.get(i).setSize(
					((LadyBirdSettings) settings).getHalfLadyBirdSize(), false);

		}
	}
}
