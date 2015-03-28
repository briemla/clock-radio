package de.briemla.clockradio.dabpi;


public interface CommandFactory {

	Command<SwitchToDABResult> switchToDAB();

	Command<SwitchToFMResult> switchToFM();

	Command<TuneToFrequencyResult> tuneTo(Integer frequency);

	Command<FMStatus> fmStatus();

	Command<DABStatus> dabStatus();

	Command<DABService> startDABService(Integer serviceId);

	Command<DABServiceList> readDABServiceList();

	Command<DABChannel> selectDABChannel(Integer channelId);

	Command<DABRegion> selectDABRegion(Integer regionId);

	Command<FrequencyList> readFrequencyListFor(Integer regionId);

	Command<Station> scanNextStation(ScanDirection direction);

	Command<RDSInfo> readRDS();

	Command<DABAudioInfo> readDABAudioInfo();

	Command<DABSubchannelInfo> readDABSubchannelInfo();

}
