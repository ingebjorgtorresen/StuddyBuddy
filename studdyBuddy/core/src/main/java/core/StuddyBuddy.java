package core;

public class StuddyBuddy {

	private String name;
	// TODO: add password

	/**
	 * @param name
	 */
	public void setName(String name) {
		checkNotNull(name);

		if (!checkName(name)) {
			throw new IllegalArgumentException(
					"Name can not include any characthers but letters and ' ', you wrote: " + name);
		}
		this.name = name;
	}

	/**
	 * checks that the name has the correct format. The name can only consist of
	 * letters and space.
	 * 
	 * @param name the name to check
	 * @return true if the formate is correct and false if it is incorrect
	 */
	private boolean checkName(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!(Character.isLetter(c) || (c == ' '))) {
				return false;
			}
		}

		return true;
	}

	private void checkNotNull(String string) {
		if (string == null) {
			throw new IllegalArgumentException("Input can not be null/nothing.");
		}
	}

	public String getName() {
		return name;
	}

}