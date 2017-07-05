package week3;

import java.util.ArrayList;

public class Actor implements Comparable<Actor>{
	private String name;
	private ArrayList<Movie> movies;
	
	public Actor(String name) {
		this.name = name;
		movies = new ArrayList<>();
	}
	
	public Actor() {
		this("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	public boolean equals(Object object) {
		return ((Actor) object).getName().equals(name);
	}
	
	public String toString() {
		return name + " average rating: " + getActorRating();
	}
	
	public double getActorRating() {
		double ratingSum = 0;
		for (Movie movie : movies) {
			ratingSum += movie.getRating();
		}
		return ratingSum / movies.size();
	}
	
	@Override
	public int compareTo(Actor o) {
		// TODO Auto-generated method stub
		Double rating = getActorRating();
		if (rating > o.getActorRating()) {
			return 1;
		} else if (rating < o.getActorRating()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
