package de.briemla.clockradio.dabpi;

import java.io.IOException;

import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABAudioInfo;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;
import de.briemla.clockradio.dabpi.result.FMStatus;
import de.briemla.clockradio.dabpi.result.DABChannelList;
import de.briemla.clockradio.dabpi.result.RDSInfo;
import de.briemla.clockradio.dabpi.result.Station;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

/**
 * This class generates {@link Command}s and executes them on the given {@link RadioExecutor}
 *
 * @author Lars
 *
 */
public class DabpiController implements RadioController {

	private final RadioExecutor executor;
	private final CommandFactory factory;

	public DabpiController(RadioExecutor executor, CommandFactory factory) {
		this.executor = executor;
		this.factory = factory;
	}

	@Override
	public Void switchToDAB() throws IOException {
		return executor.execute(factory.switchToDAB());
	}

	@Override
	public Void switchToFM() throws IOException {
		return executor.execute(factory.switchToFM());
	}

	public TuneToFrequencyResult tuneTo(Integer frequency) throws IOException {
		return executor.execute(factory.tuneTo(frequency));
	}

	public FMStatus fmStatus() throws IOException {
		return executor.execute(factory.fmStatus());
	}

	public DABStatus dabStatus() throws IOException {
		return executor.execute(factory.dabStatus());
	}

	public DABService startDABService(DABService service) throws IOException {
		return executor.execute(factory.startDABService(service));
	}

	public DABServiceList readDABServiceList() throws IOException {
		return executor.execute(factory.readDABServiceList());
	}

	public DABChannel selectDABChannel(Integer channelId) throws IOException {
		return executor.execute(factory.selectDABChannel(channelId));
	}

	public Void selectDABRegion(Region region) throws IOException {
		return executor.execute(factory.selectDABRegion(region));
	}

	public DABChannelList readFrequencyListFor(Region region) throws IOException {
		return executor.execute(factory.readFrequencyListFor(region));
	}

	public Station scanNextStation(ScanDirection direction) throws IOException {
		return executor.execute(factory.scanNextStation(direction));
	}

	public RDSInfo readRDS() throws IOException {
		return executor.execute(factory.readRDS());
	}

	public DABAudioInfo readDABAudioInfo() throws IOException {
		return executor.execute(factory.readDABAudioInfo());
	}

	public DABSubchannelInfo readDABSubchannelInfo() throws IOException {
		return executor.execute(factory.readDABSubchannelInfo());
	}

}
