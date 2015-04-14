package de.briemla.clockradio.dabpi.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABChannel;

public class SelectDABChannel extends BaseCommand<DABChannel> {

	private static final Pattern PATTERN = Pattern.compile("^si46xx_dab_tune_freq\\(([0-9]+)\\):.*$");

	private final DABChannel channel;

	public SelectDABChannel(DABChannel channel) {
		super("i");
		this.channel = channel;
	}

	@Override
	public String serialize() {
		return super.serialize() + " " + channel.serialize();
	}

	@Override
	protected DABChannel parseSpecialized(Output output) {
		Optional<DABChannel> firstChannel = output.standardAsStream()
		        .filter(line -> line.startsWith("si46xx_dab_tune_freq")).map(SelectDABChannel::convert).findFirst();
		if (firstChannel.isPresent()) {
			return firstChannel.get();
		}
		throw new IllegalArgumentException("No valid channel in output found.");
	}

	private static DABChannel convert(String line) {
		Matcher matcher = PATTERN.matcher(line);
		if (matcher.matches()) {
			Integer channelId = Integer.parseInt(matcher.group(1));
			return new DABChannel(channelId);
		}
		throw new IllegalArgumentException("Could not parse channel: " + line);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectDABChannel other = (SelectDABChannel) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectDABChannel [channel=" + channel + "]";
	}

}
