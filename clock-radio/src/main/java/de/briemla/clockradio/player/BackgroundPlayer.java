package de.briemla.clockradio.player;

import java.util.Optional;

public class BackgroundPlayer implements PlayerWorker {

    private final PlayableMedia media;
    private final Player player;
    private Optional<Thread> worker;

    public BackgroundPlayer(Player player, PlayableMedia media) {
        super();
        this.player = player;
        this.media = media;
    }

    @Override
    public void start() {
        worker = Optional.of(new Thread(() -> media.play(player), "BackgroundPlayer"));
        worker.ifPresent(Thread::start);
    }

    @Override
    public void stop() {
        media.stop(player);
        worker.ifPresent(Thread::interrupt);
        worker = Optional.empty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((media == null) ? 0 : media.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BackgroundPlayer other = (BackgroundPlayer) obj;
        if (media == null) {
            if (other.media != null) {
                return false;
            }
        } else if (!media.equals(other.media)) {
            return false;
        }
        if (player == null) {
            if (other.player != null) {
                return false;
            }
        } else if (!player.equals(other.player)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BackgroundPlayer [media=" + media + ", player=" + player + "]";
    }

}
