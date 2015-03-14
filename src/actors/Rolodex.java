package actors;

import java.util.ArrayList;

public class Rolodex {

	public ArrayList<Actor> actors;

	public Rolodex(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	
	public Rolodex() {
		this(new ArrayList<Actor>());
	}

	public void add(Actor actor) {
		actor.setID((actors == new ArrayList<Actor>()) ? 0 : actors.size());
		actors.add(actor);
	}
}