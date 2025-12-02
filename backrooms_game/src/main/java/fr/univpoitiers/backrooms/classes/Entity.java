package fr.univpoitiers.backrooms.classes;

public abstract class Entity {
    private static final int DEFAULT_HP = 100;
    private static final int DEFAULT_ATTACK = 30;
    private static final String DEFAULT_DESCRIPTION = "Vous ne savez plus exactement qui vous êtes, ni comment vous êtes arrivé ici. Votre mémoire est un voile " +
            "flou de quotidianité : des courses, un trajet en bus, le vague souvenir d'une conversation inachevée. Votre apparence est tout aussi commune : " +
            "des jeans et un t-shirt sans logo distinctif, des chaussures usées. Vous n'avez aucune compétence particulière, aucun passé héroïque, juste la " +
            "sensation écrasante d'être un anonyme pris au piège, une statistique dans le vide.";

	private int PV;
	private String description;
	private int attack;
	private String name;

    public Entity(String name) {
        if (name == null) {
            throw new UnsupportedOperationException("Entity(1) error : Parameter cannot be null");
        }
        this.PV = DEFAULT_HP;
        this.name = name;
        this.attack = DEFAULT_ATTACK;
        this.description = DEFAULT_DESCRIPTION;
    }

    public Entity(String description, String name) {
        if (description == null || name == null) {
            throw new UnsupportedOperationException("Entity(2) error : Parameters cannot be null");
        }
        this.PV = DEFAULT_HP;
        this.name = name;
        this.attack = DEFAULT_ATTACK;
        this.description = description;
    }

    public Entity(int PV, String name, int attack, String description) {
        if (description == null || name == null || PV < 0 || attack < 0) {
            throw new UnsupportedOperationException("Entity(4) error : Parameters cannot be null or negative");
        }
        this.PV = PV;
        this.name = name;
        this.attack = attack;
        this.description = description;
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
        if (this.attack < 0){
            throw new UnsupportedOperationException("Value attack cannot be negative");
        }
		this.attack = attack;
	}

	public void setName(String name) {
        if (this.name == null) {
            throw new UnsupportedOperationException("Name cannot be null");
        }
		this.name = name;
	}

    public void setPV(int PV) {
        if (this.PV < 0) {
            throw new UnsupportedOperationException("PV cannot be negative");
        }
        this.PV = PV;
    }
}