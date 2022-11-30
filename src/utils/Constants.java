package utils;

import objects.Player;

import java.util.ArrayList;
import java.util.List;

public final class Constants {

    public static final String[] NAMES = {"James","Jessica","Mauro","Carla","José Serra","Maurício","Luís","Geovana","Bianca","Carlos","Velociraptor"};
    public static final String GLOBAL_PLAYER_VARIABLE = "%s";
    public static final float GLOBAL_INSANITY_THREASHOLD = 0.3f;

    public static List<Player> genPlayers(){
        ArrayList<Player> ret = new ArrayList<>();
        for(String act : NAMES){
            ret.add(new Player(act));
        }
        return ret;
    }

}
