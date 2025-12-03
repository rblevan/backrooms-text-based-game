package fr.univpoitiers.backrooms.classes;

public abstract class  Items {

	private String Name;
	private int Volume;
	private String Description;

	public Items(String name, int volume, String description) {
		if (name == null || volume < 0 || description == null) {
			throw new UnsupportedOperationException("Items(3) error : Parameters cannot be null or negative");
		}
		this.Name = name;
		this.Volume = volume;
		this.Description = description;
	}


	public String getName()
	{
		return this.Name;
	}

	public int getVolume()
	{
		return this.Volume;
	}

	public String getDescription()
	{
		return this.Description;
	}

}