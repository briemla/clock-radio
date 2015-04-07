package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.dabpi.command.DABStatusCommand;
import de.briemla.clockradio.dabpi.command.FMStatusCommand;
import de.briemla.clockradio.dabpi.command.ReadDABAudioInfo;
import de.briemla.clockradio.dabpi.command.ReadDABServiceList;
import de.briemla.clockradio.dabpi.command.ReadDABSubchannelInfo;
import de.briemla.clockradio.dabpi.command.ReadFrequencyList;
import de.briemla.clockradio.dabpi.command.ReadRDS;
import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.command.ScanNextStation;
import de.briemla.clockradio.dabpi.command.SelectDABChannel;
import de.briemla.clockradio.dabpi.command.SelectDABRegion;
import de.briemla.clockradio.dabpi.command.StartDABService;
import de.briemla.clockradio.dabpi.command.SwitchToDAB;
import de.briemla.clockradio.dabpi.command.SwitchToFM;
import de.briemla.clockradio.dabpi.command.TuneToFrequency;
import de.briemla.clockradio.dabpi.result.DABAudioInfo;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;
import de.briemla.clockradio.dabpi.result.FMStatus;
import de.briemla.clockradio.dabpi.result.FrequencyList;
import de.briemla.clockradio.dabpi.result.RDSInfo;
import de.briemla.clockradio.dabpi.result.Station;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

/**
 * Implementation of {@link CommandFactory} for DABPi board:
 *
 * <pre>
 * ./dabpi_ctl
 * usage: ./dabpi_ctl [-a|-b]
 *   -a             init DAB mode
 *   -b             init fm mode
 *   -c frequency   tune frequency in FM mode
 *   -d             fm status
 *   -e             dab status
 *   -f service     start service of dab service list
 *   -g             get dab service list
 *   -i channel     tune to channel in dab frequency list
 *   -j region      set frequency list
 *                     0   Baden-Wuertemberg
 *                     1   Bayern
 *                     2   Berlin-Brandenburg
 *                     3   Bremen
 *                     4   Hamburg
 *                     5   Hessen
 *                     6   Mecklenburg-Vorpommern
 *                     7   Niedersachsen
 *                     8   Nordrhein-Westfalen
 *                     9   Rheinland-Pfalz
 *                     10  Saarland
 *                     11  Sachsen
 *                     12  Sachsen-Anhalt
 *                     13  Schleswig-Holstein
 *                     14  Thueringen
 *   -k region      scan frequency list
 *   -l up|down     fm seek next station
 *   -m             fm rds status
 *   -n             dab get audio info
 *   -o             dab get subchannel info
 *   -h             this help
 * </pre>
 *
 * @author Lars
 *
 */
public class DabpiCommandFactory implements CommandFactory {

	@Override
	public Command<Void> switchToDAB() {
		return new SwitchToDAB();
	}

	@Override
	public Command<Void> switchToFM() {
		return new SwitchToFM();
	}

	@Override
	public Command<TuneToFrequencyResult> tuneTo(Integer frequency) {
		return new TuneToFrequency(frequency);
	}

	@Override
	public Command<FMStatus> fmStatus() {
		return new FMStatusCommand();
	}

	@Override
	public Command<DABStatus> dabStatus() {
		return new DABStatusCommand();
	}

	@Override
	public Command<DABService> startDABService(DABService service) {
		return new StartDABService(service);
	}

	@Override
	public Command<DABServiceList> readDABServiceList() {
		return new ReadDABServiceList();
	}

	@Override
	public Command<DABChannel> selectDABChannel(Integer channelId) {
		return new SelectDABChannel(channelId);
	}

	@Override
	public Command<Void> selectDABRegion(Region region) {
		return new SelectDABRegion(region);
	}

	@Override
	public Command<FrequencyList> readFrequencyListFor(Integer regionId) {
		return new ReadFrequencyList(regionId);
	}

	@Override
	public Command<Station> scanNextStation(ScanDirection direction) {
		return new ScanNextStation(direction);
	}

	@Override
	public Command<RDSInfo> readRDS() {
		return new ReadRDS();
	}

	@Override
	public Command<DABAudioInfo> readDABAudioInfo() {
		return new ReadDABAudioInfo();
	}

	@Override
	public Command<DABSubchannelInfo> readDABSubchannelInfo() {
		return new ReadDABSubchannelInfo();
	}

}
