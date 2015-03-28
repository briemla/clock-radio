package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.dabpi.result.DABAudioInfo;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABRegion;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;
import de.briemla.clockradio.dabpi.result.FMStatus;
import de.briemla.clockradio.dabpi.result.FrequencyList;
import de.briemla.clockradio.dabpi.result.RDSInfo;
import de.briemla.clockradio.dabpi.result.Station;
import de.briemla.clockradio.dabpi.result.SwitchToDABResult;
import de.briemla.clockradio.dabpi.result.SwitchToFMResult;
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
	public SwitchToDABResult switchToDAB() {
		return executor.execute(factory.switchToDAB());
	}

	@Override
	public SwitchToFMResult switchToFM() {
		return executor.execute(factory.switchToFM());
	}

	public TuneToFrequencyResult tuneTo(Integer frequency) {
		return executor.execute(factory.tuneTo(frequency));
	}

	public FMStatus fmStatus() {
		return executor.execute(factory.fmStatus());
	}

	public DABStatus dabStatus() {
		return executor.execute(factory.dabStatus());
	}

	public DABService startDABService(Integer serviceId) {
		return executor.execute(factory.startDABService(serviceId));
	}

	public DABServiceList readDABServiceList() {
		return executor.execute(factory.readDABServiceList());
	}

	public DABChannel selectDABChannel(Integer channelId) {
		return executor.execute(factory.selectDABChannel(channelId));
	}

	public DABRegion selectDABRegion(Integer regionId) {
		return executor.execute(factory.selectDABRegion(regionId));
	}

	public FrequencyList readFrequencyListFor(Integer regionId) {
		return executor.execute(factory.readFrequencyListFor(regionId));
	}

	public Station scanNextStation(ScanDirection direction) {
		return executor.execute(factory.scanNextStation(direction));
	}

	public RDSInfo readRDS() {
		return executor.execute(factory.readRDS());
	}

	public DABAudioInfo readDABAudioInfo() {
		return executor.execute(factory.readDABAudioInfo());
	}

	public DABSubchannelInfo readDABSubchannelInfo() {
		return executor.execute(factory.readDABSubchannelInfo());
	}

}