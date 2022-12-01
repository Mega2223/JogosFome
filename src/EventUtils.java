import utils.Constants;
import objects.Event;
import objects.Game;
import objects.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EventUtils {
    public static List<Event> genEvents() throws IOException {
        List<Event> ret = new ArrayList<>();

        String[] dayLightMurders = FileUtils.loadTextFile("assassinatos.txt").split("\n");
        for(String act : dayLightMurders){
            ret.add(new Event() {
                public String doEvent(Game game, Player[] players) {

                    for (int i = 0; i < players.length; i++) {
                        //mata exatamente a metade que fica do lado direito da array
                        if(players.length/2 <= i){
                            players[i].kill();}
                    }
                    return doDefaultEventLogic(getDescription(),players);
                }
                public String getDescription() {
                    return act;
                }

            });
        }

        String[] nightTimeEv = FileUtils.loadTextFile("eventosnoturnos.txt").split("\n");
        for(String act : nightTimeEv){
            ret.add(new Event() {
                @Override
                public String doEvent(Game game, Player[] players) {
                    return doDefaultEventLogic(getDescription(),players);
                }

                @Override
                public String getDescription() {
                    return act;
                }

                @Override
                public boolean isDuringTheDay() {
                    return false;
                }
            });
        }

        String[] dayTimeEv = FileUtils.loadTextFile("eventostriviais.txt").split("\n");
        for(String act : dayTimeEv){
            ret.add(new Event() {
                @Override
                public String doEvent(Game game, Player[] players) {
                    return doDefaultEventLogic(getDescription(),players);
                }

                @Override
                public String getDescription() {
                    return act;
                }
            });
        }

        String[] lowSanityEv = FileUtils.loadTextFile("lowsanityevents.txt").split("\n");
        for(String act : lowSanityEv){
            ret.add(new Event() {
                @Override
                public String doEvent(Game game, Player[] players) {
                    return doDefaultEventLogic(getDescription(),players);
                }

                @Override
                public boolean canDo(Player[] players) {
                    return players.length == 0 && players[0].sanity < Constants.GLOBAL_INSANITY_THREASHOLD;
                }

                @Override
                public String getDescription() {
                    return act;
                }
            });
        }

        String[] allDieEv = FileUtils.loadTextFile("mortes coletivas.txt").split("\n");
        for(String act : allDieEv){
            ret.add(new Event() {
                @Override
                public String doEvent(Game game, Player[] players) {
                    for (int i = 0; i < players.length; i++) {
                        players[i].kill();
                    }
                    return doDefaultEventLogic(getDescription(),players);
                }

                @Override
                public String getDescription() {
                    return act;
                }
            });
        }

        String[] nightTimeDeathsEv = FileUtils.loadTextFile("mortesnoturnas.txt").split("\n");
        for (String act: nightTimeDeathsEv){
            ret.add(new Event() {
                @Override
                public String doEvent(Game game, Player[] players) {
                    for (int i = 0; i < players.length; i++) {
                        players[i].kill();
                    }
                    return doDefaultEventLogic(getDescription(),players);
                }

                @Override
                public String getDescription() {
                    return act;
                }

                @Override
                public boolean isDuringTheDay() {
                    return false;
                }
            });
        }

        String[] arrestedEv = FileUtils.loadTextFile("prisao.txt").split("\n");
        for(String act : arrestedEv){
            ret.add(new Event() {
                @Override
                public String doEvent(Game game, Player[] players) {
                    for (int i = 0; i < players.length; i++) {
                        players[i].kill();
                    }
                    return doDefaultEventLogic(getDescription(),players);
                }

                @Override
                public String getDescription() {
                    return act;
                }
            });
        }

        return ret;
    }

    static String doDefaultEventLogic(String eventDescription, Player[] players){

        String[] eventSplit = eventDescription.split(Constants.GLOBAL_PLAYER_VARIABLE);
        int playerNumber = eventSplit.length-1;
        if(playerNumber != players.length){throw new IllegalStateException("Invalid number of players: " + (players.length-1));}

        String toBuild = "";
        int usedPlayers = 0;
        for (int i = 0; i < eventSplit.length; i++) {
            if(eventSplit[i].equals("")||usedPlayers >= players.length){continue;}
            toBuild += players[usedPlayers] + eventSplit[i];
            usedPlayers++;
        }

        return toBuild;

    }
}
