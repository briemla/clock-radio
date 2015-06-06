package de.briemla.clockradio.dabpi.result;

public class TuneToFrequencyResult {

    private final Integer frequency;

    public TuneToFrequencyResult(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
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
        TuneToFrequencyResult other = (TuneToFrequencyResult) obj;
        if (frequency == null) {
            if (other.frequency != null)
                return false;
        } else if (!frequency.equals(other.frequency))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TuneToFrequencyResult [frequency=" + frequency + "]";
    }

}
