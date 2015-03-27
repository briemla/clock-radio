package de.briemla.clockradio;

/**
 * This class generates {@link Command}s and executes them on the given {@link RadioExecutor}
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

	public FMStatusResult fmStatus() {
		return executor.execute(factory.fmStatus());
	}

	public DABStatusResult dabStatus() {
		return executor.execute(factory.dabStatus());
	}

	public StartDABServiceResult startDABService(Integer serviceId) {
		return executor.execute(factory.startDABService(serviceId));
	}

	public ReadDABServiceListResult readDABServiceList() {
		return executor.execute(factory.readDABServiceList());
	}

	public SelectDABChannelResult selectDABChannel(Integer channelId) {
		return executor.execute(factory.selectDABChannel(channelId));
	}

	public SelectDABRegionResult selectDABRegion(Integer regionId) {
		return executor.execute(factory.selectDABRegion(regionId));
	}

	public ReadFrequencyListForRegionResult readFrequencyListFor(Integer regionId) {
		return executor.execute(factory.readFrequencyListFor(regionId));
	}

	public ScanNextStationResult scanNextStation(ScanDirection direction) {
		return executor.execute(factory.scanNextStation(direction));
	}

}
