package de.briemla.clockradio.dabpi;

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

public interface CommandFactory {

	Command<Void> switchToDAB();

	Command<Void> switchToFM();

	Command<TuneToFrequencyResult> tuneTo(Integer frequency);

	Command<FMStatus> fmStatus();

	Command<DABStatus> dabStatus();

	Command<DABService> startDABService(DABService service);

	Command<DABServiceList> readDABServiceList();

	Command<DABChannel> selectDABChannel(Integer channelId);

	Command<Void> selectDABRegion(Region region);

	Command<DABChannelList> readFrequencyListFor(Region region);

	Command<Station> scanNextStation(ScanDirection direction);

	Command<RDSInfo> readRDS();

	Command<DABAudioInfo> readDABAudioInfo();

	Command<DABSubchannelInfo> readDABSubchannelInfo();

}
