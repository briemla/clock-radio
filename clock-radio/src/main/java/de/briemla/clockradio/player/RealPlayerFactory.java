package de.briemla.clockradio.player;

import de.briemla.clockradio.Media;

public class RealPlayerFactory implements PlayerFactory {

    private final Player player;

    public RealPlayerFactory(Player player) {
        super();
        this.player = player;
    }

    @Override
    public PlayerWorker create(Media media) {
        return new BackgroundPlayer(player, media.create());
    }

    @Override
    public String toString() {
        return "RealPlayerFactory [player=" + player + "]";
    }

}
