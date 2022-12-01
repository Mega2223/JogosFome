package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public boolean ended = false;
    List<Player> players;
    List<Event> dayLightEvents = new ArrayList<>();
    List<Event> nightlyEvents = new ArrayList<>();
    boolean currentlyDay = true;
    double activePercentageRequired = 15;

    public Game(List<Player> players, List<Event> events) {
        this.players = players;
        for (Event act : events) {
            if (act.isDuringTheDay()) {
                dayLightEvents.add(act);
            } else {
                nightlyEvents.add(act);
            }
        }
    }

    public String[] doTurn() {

        if (currentlyDay) {
            currentlyDay = false;
            return doLogic(dayLightEvents);
        }
        currentlyDay = true;
        return doLogic(nightlyEvents);
    }

    protected String[] doLogic(List<Event> events) {

        if (players.size() == 1) {
            ended = true;
            return new String[]{"Player " + players.get(0) + " ganhou :)"};
        }

        List<String> eventsDone = new ArrayList<>();
        List<Player> eventedPlayers = new ArrayList<>();
        while (eventedPlayers.size() < players.size() * (activePercentageRequired / 100.0)) {
            Random r = new Random();
            Event randomEvent = events.get(r.nextInt(events.size()));
            if (randomEvent.playersNeeded() > players.size()) {
                continue;
            }
            List<Player> random = new ArrayList<>();
            List<Player> avail = new ArrayList<>(players);
            avail.removeAll(eventedPlayers);
            if (avail.size() + random.size() < randomEvent.playersNeeded()) {
                continue;
            }
            while (random.size() < randomEvent.playersNeeded()) {
                Player randomPlayer = avail.get(r.nextInt(avail.size()));
                if (random.contains(randomPlayer)) {
                    continue;
                }

                random.add(randomPlayer);

            }
            Player[] playersToEvent = new Player[random.size()];
            if (!randomEvent.canDo(playersToEvent)) {
                continue;
            }
            playersToEvent = random.toArray(playersToEvent);
            eventsDone.add(randomEvent.doEvent(this, playersToEvent));
            eventedPlayers.addAll(random);
        }


        String[] ret = new String[eventsDone.size()];
        ret = eventsDone.toArray(ret);

        for (int i = 0; i < players.size(); i++) {
            Player act = players.get(i);
            if (!act.isAlive) {
                players.remove(act);
            }
        }

        return ret;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player[] samplePlayers(int size) {
        Player[] sample = new Player[size];
        Random r = new Random();

        for (int i = 0; i < sample.length; i++) {
            Player randPlayer = players.get(r.nextInt(players.size()));
            int tries = 0;
            while (arrayContains(sample, randPlayer)) {
                randPlayer = players.get(r.nextInt(players.size()));
                if (tries > players.size()) {
                    return null;
                }
                tries++;
            }
            sample[i] = randPlayer;

        }

        return sample;
    }

    boolean arrayContains(Object[] array, Object object) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == object) {
                return true;
            }
        }
        return false;
    }

}
