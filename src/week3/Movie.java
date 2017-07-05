package week3;

import java.util.ArrayList;

public class Movie implements Comparable<Movie> {
	private String name;
	private ArrayList<Actor> actors;
	private double rating;
	
	public Movie(String name) {
		this.name = name;
		this.rating = 0.0;
		actors = new ArrayList<>();
	}
	
	public Movie() {
		this("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public boolean equals(Object object) {
		return ((Movie) object).getName().equals(name);
	}
	
	public String toString() {
		return name + " Rating: " + rating;
	}

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		if (rating > o.getRating()) {
			return 1;
		} else if (rating < o.getRating()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	
}
