package actors;

import java.util.ArrayList;

public class Rolodex {

	private static ArrayList<Actor> actors = new ArrayList<Actor>();

	public static void add(Actor actor) {
		actor.setID((actors == new ArrayList<Actor>()) ? 0 : actors.size());
		actors.add(actor);
	}
	
	public static Actor get(int id) {
		return actors.get(id);
	}
	
	public static int size() {
		return actors.size();
	}
	
	public static ArrayList<Actor> list() {
		return actors;
	}
}