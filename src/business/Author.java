package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	private boolean credential;
	public String getBio() {
		return bio;
	}
	
	public Author(String f, String l, String t, Address a, boolean c,String bio) {
		super(f, l, t, a);
		this.credential = c;
		this.bio = bio;
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
