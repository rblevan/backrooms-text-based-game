package fr.univpoitiers.backrooms;

public class Items {

	private String Type;
	private String Description;

	public String getType() {
		return this.Type;
	}

	public String getDescription() {
		return this.Description;
	}

	/**
	 * 
	 * @param type
	 * @param description
	 */
	public Items(String type, String description) {
		this.Type = type;
        this.Description = description;
		throw new UnsupportedOperationException();
	}

}