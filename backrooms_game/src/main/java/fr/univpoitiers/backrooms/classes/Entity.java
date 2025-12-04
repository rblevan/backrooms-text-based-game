package fr.univpoitiers.backrooms.classes;

public abstract class Entity {
    private static final int DEFAULT_HP = 100;
    private static final int DEFAULT_ATTACK = 30;

	private int PV;
	private String description;
	private int attack;
	private String name;
    private final int max_hp;

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



    public int getMax_hp() {
        return max_hp;
    }


    public int getPV() {
        return this.PV;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        if (description == null) {
            throw new UnsupportedOperationException("Description cannot be null");
        }
		this.description = description;
	}

	public void setAttack(int attack) {
        if (attack < 0){
            throw new UnsupportedOperationException("Value attack cannot be negative");
        }
		this.attack = attack;
	}

	public void setName(String name) {
        if (name == null) {
            throw new UnsupportedOperationException("Name cannot be null");
        }
		this.name = name;
	}

    public void setPV(int PV) {
        if (PV < 0) {
            throw new UnsupportedOperationException("PV cannot be negative");
        }
        this.PV = PV;
    }

    public void takeDamage(int damage) {
        this.PV -= damage;
    }
}