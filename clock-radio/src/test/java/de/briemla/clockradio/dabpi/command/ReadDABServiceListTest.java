package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;

public class ReadDABServiceListTest {

	@Test
	public void serialize() throws Exception {
		ReadDABServiceList command = new ReadDABServiceList();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -g")));
	}

	@Test
	public void parseCorrectOutput() throws Exception {
		DABServiceList expectedServices = new DABServiceList();
		expectedServices.add(new DABService(0, "d301", "SWR1 BW"));
		expectedServices.add(new DABService(1, "d3a2", "SWR2"));
		expectedServices.add(new DABService(2, "d3a3", "SWR3"));
		expectedServices.add(new DABService(3, "d3a5", "DASDING"));
		expectedServices.add(new DABService(4, "d3a6", "SWRinfo"));
		expectedServices.add(new DABService(5, "d804", ""));
		expectedServices.add(new DABService(6, "dc04", ""));
		expectedServices.add(new DABService(7, "dd04", ""));
		expectedServices.add(new DABService(8, "de04", "SWR4 UL"));
		expectedServices.add(new DABService(9, "e0d010c9", "EPG"));
		expectedServices.add(new DABService(10, "e0d020eb", ""));

		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_get_digital_service_list()");
		output.addStandard("List size:     314");
		output.addStandard("List version:  106");
		output.addStandard("Services:      11");
		output.addStandard("Num:  0  Service ID:     d301  Service Name: SWR1 BW           Component ID: 2");
		output.addStandard("                                                               Component ID: 2");
		output.addStandard("Num:  1  Service ID:     d3a2  Service Name: SWR2              Component ID: 3");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  2  Service ID:     d3a3  Service Name: SWR3              Component ID: 4");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  3  Service ID:     d3a5  Service Name: DASDING           Component ID: 13");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  4  Service ID:     d3a6  Service Name: SWRinfo           Component ID: 1");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  5  Service ID:     d804  Service Name:   Component ID: 9");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  6  Service ID:     dc04  Service Name:   Component ID: 10");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  7  Service ID:     dd04  Service Name:   Component ID: 8");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  8  Service ID:     de04  Service Name: SWR4 UL           Component ID: 12");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  9  Service ID: e0d010c9  Service Name: EPG               Component ID: 49152");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num: 10  Service ID: e0d020eb  Service Name:   Component ID: 49153");
		output.addStandard("                                                               Component ID: 0");

		ReadDABServiceList command = new ReadDABServiceList();
		DABServiceList dabServiceList = command.parse(output);

		assertThat(dabServiceList, is(equalTo(expectedServices)));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(ReadDABServiceList.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}
