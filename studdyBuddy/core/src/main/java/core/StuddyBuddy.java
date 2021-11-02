package core;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class StuddyBuddy {

	private StuddyBuddies buddies;
	private String name;
	//TODO: add password

 	private List<StuddyBuddyRegistration> registrations= new ArrayList<>();

	public void setStuddyBuddies(StuddyBuddies buddies) {
		this.buddies = buddies;
	}

	public StuddyBuddies getStuddyBuddies() {
		return this.buddies;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		StuddyBuddyValidation.checkNotNull(name);

		if (!StuddyBuddyValidation.checkName(name)) {
			throw new IllegalArgumentException(
					"Name can not include any characthers but letters and ' ', you wrote: " + name);
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Iterator<StuddyBuddyRegistration> iterator() {
		return registrations.iterator();
	}

	public void addRegistration(StuddyBuddyRegistration registration) {
		registrations.add(0, registration);
	}

	public void removeRegistration(StuddyBuddyRegistration registration) {
		registrations.remove(registration);
	}

	public List<StuddyBuddyRegistration> getRegistrations() {
		return registrations;
	}

}