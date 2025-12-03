package fr.univpoitiers.backrooms.classes;

public class Spells extends Items {

	private String Action;

	public void getAction() {
		// TODO - implement Spells.getAction
		throw new UnsupportedOperationException();
	}

    public Spells(String name, int volume, String description) {
        super(name, volume, description);
    }

	/**
	 * 
	 * @param Action
	 */
	public void setAction(String Action) {
		// TODO - implement Spells.setAction
		throw new UnsupportedOperationException();
	}

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getVolume() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }



}