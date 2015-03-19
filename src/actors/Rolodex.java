package actors;

import java.util.ArrayList;

public class Rolodex {

	public static ArrayList<Actor> actors = new ArrayList<Actor>();

	public static void add(Actor actor) {
		actor.setID((actors == new ArrayList<Actor>()) ? 0 : actors.size());
		actors.add(actor);
	}
}