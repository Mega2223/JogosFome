package objects;

public class Player {
    String name;
    public boolean canInteractNextRound = true;
    public boolean isAlive = true;
    public float sanity = 1f;


    public Player(String name){
        this.name = name;
    }

    public String getName(){return name;}

    @Override
    public String toString() {
        return getName();
    }

    public void kill(){
        isAlive = false;
    }
}
