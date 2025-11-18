package fr.univpoitiers.backrooms;

public class Locations {

	private String Description;
    protected final List<Exits> Exits ;

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


}