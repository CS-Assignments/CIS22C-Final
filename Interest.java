/**
 * Interest.java
 * @author Michael Lin 
 * CIS 22C, Final Project
 */

public class Interest {
	private String name;
	private int ID;

	public Interest(String name){
		this.name = name;
	}
	
	public Interest(String name, int ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	/** ADDITIONAL OPERATIONS */

	/**
	 * Creates a String of Interest information in the form of: ID: <ID> Interest
	 * Name: <name>
	 */

	@Override
	public String toString() {
		return "ID: " + this.ID + "\n" + "Interest Name: " + this.name;
	}

	/**
	 * Compares this Interest to another Object for equality
	 * 
	 * @param o another Object
	 * @return true if o is a Interest and has a matching name and ID to this
	 *         Interest
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Interest)) {
			return false;
		} else {
			Interest temp = (Interest) o;
			if (temp.getName().equals(this.name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a consistent hash code for each Interest by summing the Unicode
	 * values of each character in the key Key = name
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		String key = name;
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += (int) key.charAt(i);
		}
		return sum;
	}
}
