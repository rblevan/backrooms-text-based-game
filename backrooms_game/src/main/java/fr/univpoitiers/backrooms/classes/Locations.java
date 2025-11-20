package fr.univpoitiers.backrooms.classes;

import java.util.List;

public class Locations {

	private final String Description;
    private final List<Exits> Exits;

	public String getDescription() {
        // todo : condition Exception
        if ('a' == 'e') {throw new UnsupportedOperationException();}
        return this.Description;
	}
    /*
    public List<Exits> getExits(){
        // todo : condition Exception
        if ('a' == 'e') {throw new UnsupportedOperationException();}
        return this.Exits;
    }
    */

    /**
     * @param Description
     * @param Exits
     */
    public Locations(String Description, List<Exits> Exits){
        // todo : condition Exception
        if('a' == 'e') {throw new UnsupportedOperationException();}
        this.Description = Description;
        this.Exits = Exits;
    }

}