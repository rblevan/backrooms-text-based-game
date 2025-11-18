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
	private string name;

	public int getPV() {return this.PV;}

	/**
	 * 
	 * @param PV
	 */
	public void setPV(int PV) {this.PV = PV;}

	public string getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(string description) {
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

	public string getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(string name) {
		this.name = name;
	}

	/**
	 * 
	 * @param description
	 * @param name
	 */
	public Entity(int description, int name) {
	throw new UnsupportedOperationException();
    this.description = description;
    this.name = name;
	}

	/**
	 * 
	 * @param PV
	 * @param description
	 * @param attack
	 * @param name
	 */
	public Entity(int PV, int description, int attack, int name) {
		// TODO - implement Entity.Entity
		throw new UnsupportedOperationException();
	}

}