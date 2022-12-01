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

        for (int day = 1; !theGame.ended; day++) {

            System.out.println("Começa o dia " + day + ":");
            String[] dayTurn = theGame.doTurn();
            String[] nightTurn = theGame.doTurn();


            for (int i = 0; i < dayTurn.length; i++) {
                System.out.println(dayTurn[i]);
            }
            System.out.println("O sol se pôs.");
            for (int i = 0; i < nightTurn.length; i++) {
                System.out.println(nightTurn[i]);
            }

            System.out.println("Fim do dia " + day + ".\nSobreviventes: " + theGame.getPlayers());
            try{Thread.sleep(600);}catch (InterruptedException ex){}
        }

    }

}
