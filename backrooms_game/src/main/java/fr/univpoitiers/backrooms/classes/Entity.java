package fr.univpoitiers.backrooms.classes;

/**
 * Abstract base class representing any living entity in the game (Player, NPCs, Monsters).
 * It manages common attributes such as Health Points (PV), Attack power, Name, and Description.
 */
public abstract class Entity {
    private static final int DEFAULT_HP = 100;
    private static final int DEFAULT_ATTACK = 30;

	private int PV;
	private String description;
	private int attack;
	private String name;
    private final int max_hp;

    /**
     * Constructs an entity with specific name and description, but default statistics.
     * Sets HP to 100 and Attack to 30 by default.
     *
     * @param description A brief description of the entity.
     * @param name        The name of the entity.
     */
    public Entity(String description, String name) {
        if (description == null || name == null) {
            throw new UnsupportedOperationException("Entity(3) error : Parameters cannot be null");
        }
        this.PV = DEFAULT_HP;
        this.name = name;
        this.attack = DEFAULT_ATTACK;
        this.description = description;
        this.max_hp = DEFAULT_HP;
    }

    /**
     * Constructs an entity with fully custom statistics.
     *
     * @param PV          Initial Health Points (PV).
     * @param name        The name of the entity.
     * @param attack      The attack power value.
     * @param description A brief description of the entity.
     */
    public Entity(int PV, String name, int attack, String description) {
        if (description == null || name == null || PV < 0 || attack < 0) {
            throw new UnsupportedOperationException("Entity(5) error : Parameters cannot be null or negative");
        }
        this.PV = PV;
        this.name = name;
        this.attack = attack;
        this.description = description;
        this.max_hp = PV;
    }

    /**
     * Returns the maximum Health Points this entity can have.
     * Used to calculate health percentage or limit healing.
     *
     * @return The maximum HP.
     */
    public int getMax_hp() {
        return max_hp;
    }

    /**
     * Returns the current Health Points.
     *
     * @return Current PV.
     */
    public int getPV() {
        return this.PV;
    }

    /**
     * Returns the name of the entity.
     *
     * @return The name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the attack power of the entity.
     *
     * @return The attack power.
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Returns a brief description of the entity.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description for the entity.
     *
     * @param description The new description.
     */
	public void setDescription(String description) {
        if (description == null) {
            throw new UnsupportedOperationException("Description cannot be null");
        }
		this.description = description;
	}

    /**
     * Sets a new attack power for the entity.
     *
     * @param attack
     */
	public void setAttack(int attack) {
        if (attack < 0){
            throw new UnsupportedOperationException("Value attack cannot be negative");
        }
		this.attack = attack;
	}

    /**
     * Sets a new name for the entity.
     *
     * @param name The new name.
     */
	public void setName(String name) {
        if (name == null) {
            throw new UnsupportedOperationException("Name cannot be null");
        }
		this.name = name;
	}

    /**
     * Sets the current Health Points.
     *
     * @param PV The new amount of HP (cannot be negative).
     */
    public void setPV(int PV) {
        if (PV < 0) {
            throw new UnsupportedOperationException("PV cannot be negative");
        }
        this.PV = PV;
    }

    /**
     * Reduces the entity's health points by a specified amount.
     * Usually called during combat when the entity is attacked.
     *
     * @param damage The amount of damage to inflict.
     */
    public void takeDamage(int damage) {
        this.PV -= damage;
    }
}