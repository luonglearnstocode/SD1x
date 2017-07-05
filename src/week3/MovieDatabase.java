package week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MovieDatabase {
	private ArrayList<Movie> movieList;
	private ArrayList<Actor> actorList;
	
	public MovieDatabase() {
		movieList = new ArrayList<>();
		actorList = new ArrayList<>();
	}
	
	public ArrayList<Movie> getMovieList() {
		return this.movieList;
	}
	
	public ArrayList<Actor> getActorList() {
		return this.actorList;
	}
	
	public void addMovie(String name, String[] actors) {
		Movie newMovie = new Movie(name);
		if (!movieList.contains(newMovie)) {
			movieList.add(newMovie);
			for (String actorName : actors) {
				Actor actor = new Actor(actorName);
				if (!actorList.contains(actor)) {
					actorList.add(actor);
				} else {
					actor = actorList.get(actorList.indexOf(actor));
				}
				
				newMovie.getActors().add(actor);
				actor.getMovies().add(newMovie);
				
			}
		}
	}
	
	public void addRating(String name, double rating) {
		if (movieList.indexOf(new Movie(name)) != -1) {
			movieList.get(movieList.indexOf(new Movie(name))).setRating(rating);
		}
	}
	
	public void updateRating(String name, double newRating) {
		movieList.get(movieList.indexOf(new Movie(name))).setRating(newRating);
	}
	
	public void printDatabase() {
//		for (Movie movie : movieList) {
//			System.out.println(movie);
//			for (Actor actor : movie.getActors()) {
//				System.out.println("\t" + actor);
//			}
//		}
		for (Actor actor : actorList) {
			System.out.println(actor);
			for (Movie movie : actor.getMovies()) {
				System.out.println("\t" + movie);
			}
		}
	}
	
	public String getBestMovie() {
		Collections.sort(movieList);
		return movieList.get(movieList.size() - 1).getName();
	}
	
	public String getBestActor() {
		Collections.sort(actorList);
		return actorList.get(actorList.size() - 1).getName();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		MovieDatabase mdb = new MovieDatabase();
//		String[] actors = new String[] {"a", "b", "c"};
//		mdb.addMovie("m1", actors);
//		mdb.addRating("m1", 5.0);
//		mdb.updateRating("m1", 8.0);
//		String[] actors2 = new String[] {"a", "b", "d"};
//		mdb.addMovie("m2", actors2);
//		mdb.addRating("m2", 9.0);
//		mdb.printDatabase();
		
		/*
		 * Read movies
		 */
		Map<String, List<String>> movies = new HashMap<>();
		Scanner sc = new Scanner(new File("movies.txt"));
        while(sc.hasNextLine()) {
        	String[] actors = sc.nextLine().split(", ");
        	for (int i = 1; i < actors.length; i++) {
        		if (!movies.containsKey(actors[i])) {
        			movies.put(actors[i], new ArrayList<String>());
        		}
        		movies.get(actors[i]).add(actors[0]);
        	}
        }
        for (String movie : movies.keySet()) {
        	List<String> actors = movies.get(movie);
        	mdb.addMovie(movie, actors.toArray(new String[actors.size()]));
        }

        
        /*
		 * Read ratings
		 */
        sc = new Scanner(new File("ratings.txt"));
        sc.nextLine(); // skip header
        while(sc.hasNextLine()){
        	String[] ratings = sc.nextLine().split("\t");
        	mdb.addRating(ratings[0], Double.parseDouble(ratings[1]));
        }

//		mdb.printDatabase();
		System.out.println("Best movie: " + mdb.getBestMovie());
		System.out.println("Best actor: " + mdb.getBestActor());
	}
	
	
	
}
