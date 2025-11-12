package fr.univpoitiers.backrooms;

public abstract class Entity {

	private int PV = 100;
	/**
	 * description de l'entité
	 */
	private String description;
	/**
	 * valeur d'attaque
	 */
	private int attack = 10;
	private String name;

	public int getPV() {
		// TODO - implement Entity.getPV
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PV
	 */
	public void setPV(int PV) {
		// TODO - implement Entity.setPV
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getAttack() {
		return this.attack;
	}

	/**
	 * 
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {this.name = name;}

	/**
	 * 
	 * @param description
	 * @param name
	 */
	public Entity(String description, String name) {
		this.description = description;
        this.name = name;
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PV
	 * @param description
	 * @param attack
	 * @param name
	 */
	public Entity(int PV, String description, int attack, String name) {
		this.PV = PV;
        this.description = description;
        this.attack = attack;
        this.name = name;
		throw new UnsupportedOperationException();
	}

}