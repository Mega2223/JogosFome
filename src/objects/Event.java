package objects;

import utils.Constants;

public interface Event {

    String doEvent(Game game, Player[] players);

    String getDescription();

    default int playersNeeded(){return getDescription().split(Constants.GLOBAL_PLAYER_VARIABLE).length-1;}
    default boolean isDuringTheDay(){return true;}

    default boolean canDo(Player[] players){return true;}

}
