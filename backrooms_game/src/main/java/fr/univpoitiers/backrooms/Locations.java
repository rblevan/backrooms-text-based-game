package fr.univpoitiers.backrooms;

import java.util.List;

public class Locations {

	private final String Description;
    private final List<Exits> Exits;

	public String getDescription() {
		throw new UnsupportedOperationException();
        return this.Description;
	}
    public List<Exits> getExits(){
        throw new UnsupportedOperationException();
        return this.Exits;
    }
    /**
     *
     * @param Description
     */
    public Locations(String Description) {
        throw new UnsupportedOperationException();
        this.Description = Description;
    }
    /**
     * @param Description
     * @param Exits
     */
    public Locations(String Description, List<Exits> Exits){
        throw new UnsupportedOperationException();
        this.Description = Description;
        this.Exits = Exits;
    }

	/**
	 * 
	 * @param Description
	 */
	public Locations(String Description) {
		// TODO - implement Locations.Locations
		throw new UnsupportedOperationException();
	}
}