import objects.Event;
import objects.Game;
import objects.Player;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Event> events = EventUtils.genEvents();


        for(Event act : events){
            //System.out.println(act.getDescription() + "|" + act.isDuringTheDay()  + "|" + act.playersNeeded());
        }

        Game theGame = new Game(Constants.genPlayers(),events);

        for (int turn = 0; !theGame.ended; turn++) {
            String[] actTurn = theGame.doTurn();

            System.out.println();
            if(turn%2==0){System.out.println("DIA " + turn/2 + ":");}
            else{System.out.println("NOITE " + turn/2 + ":");}

            for (int i = 0; i < actTurn.length; i++) {
                System.out.println(actTurn[i]);
                try{Thread.sleep(200);}catch (InterruptedException ex){}
            }
        }

    }

}
