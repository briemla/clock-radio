package de.briemla.clockradio;

public interface CommandFactory {

	Command<SwitchToDABResult> switchToDAB();

	Command<SwitchToFMResult> switchToFM();

	Command<TuneToFrequencyResult> tuneTo(Integer frequency);

	Command<FMStatusResult> fmStatus();

	Command<DABStatusResult> dabStatus();

	Command<StartDABServiceResult> startDABService(Integer serviceId);

	Command<ReadDABServiceListResult> readDABServiceList();

	Command<SelectDABChannelResult> selectDABChannel(Integer channelId);

	Command<SelectDABRegionResult> selectDABRegion(Integer regionId);

	Command<ReadFrequencyListForRegionResult> readFrequencyListFor(Integer regionId);

	Command<ScanNextStationResult> scanNextStation(ScanDirection direction);

	Command<ReadRDSResult> readRDS();

	Command<ReadDABAudioInfoResult> readDABAudioInfo();

	Command<ReadDABSubchannelInfoResult> readDABSubchannelInfo();

}
