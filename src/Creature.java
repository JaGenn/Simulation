public abstract class Creature extends Entity {
    // Существо

    public int SPEED;
    public int HP;


    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public abstract void makeMove();
}

