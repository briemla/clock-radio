package de.briemla.clockradio.dabpi.result;

public class DABChannel {

    private final Integer channelId;

    public DABChannel(Integer channelId) {
        this.channelId = channelId;
    }

    public String serialize() {
        return String.valueOf(channelId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DABChannel other = (DABChannel) obj;
        if (channelId == null) {
            if (other.channelId != null)
                return false;
        } else if (!channelId.equals(other.channelId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DABChannel [channelId=" + channelId + "]";
    }

}
