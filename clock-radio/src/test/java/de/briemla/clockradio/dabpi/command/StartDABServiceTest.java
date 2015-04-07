package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABService;

public class StartDABServiceTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void parseCorrectOutput() throws Exception {
		Integer serviceNumber = 2;
		String serviceId = "d3a3";
		String serviceName = "SWR3";
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_get_digital_service_list()");
		output.addStandard("List size:     314");
		output.addStandard("List version:  109");
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
		output.addStandard("Num:  5  Service ID:     d804  Service Name: SWR4 KA           Component ID: 9");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  6  Service ID:     dc04  Service Name: SWR4 MA           Component ID: 10");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  7  Service ID:     dd04  Service Name: SWR4 HN           Component ID: 8");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  8  Service ID:     de04  Service Name: SWR4 UL           Component ID: 12");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  9  Service ID: e0d010c9  Service Name: EPG               Component ID: 49152");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num: 10  Service ID: e0d020eb  Service Name:   Component ID: 49153");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Starting service SWR3             d3a3 4");
		output.addStandard("");

		DABService service = new DABService(serviceNumber, serviceId, serviceName);
		StartDABService command = new StartDABService(service);
		DABService dabService = command.parse(output);

		assertThat(dabService, is(equalTo(new DABService(serviceNumber, serviceId, serviceName))));
	}

	@Test
	public void parseUnavailableServiceStarted() throws Exception {
		Integer serviceNumber = 12;
		String serviceId = "d3a3";
		String serviceName = "SWR3";
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_get_digital_service_list()");
		output.addStandard("List size:     314");
		output.addStandard("List version:  109");
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
		output.addStandard("Num:  5  Service ID:     d804  Service Name: SWR4 KA           Component ID: 9");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  6  Service ID:     dc04  Service Name: SWR4 MA           Component ID: 10");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  7  Service ID:     dd04  Service Name: SWR4 HN           Component ID: 8");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  8  Service ID:     de04  Service Name: SWR4 UL           Component ID: 12");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  9  Service ID: e0d010c9  Service Name: EPG               Component ID: 49152");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num: 10  Service ID: e0d020eb  Service Name:   Component ID: 49153");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Starting service 0 0");
		output.addStandard("");

		DABService service = new DABService(serviceNumber, serviceId, serviceName);
		StartDABService command = new StartDABService(service);

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Wrong service started: Starting service 0 0");
		command.parse(output);
	}

	@Test
	public void parseWrongServiceStarted() throws Exception {
		Integer serviceNumber = 22;
		String serviceId = "d3a3";
		String serviceName = "SWR3";
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_get_digital_service_list()");
		output.addStandard("List size:     314");
		output.addStandard("List version:  109");
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
		output.addStandard("Num:  5  Service ID:     d804  Service Name: SWR4 KA           Component ID: 9");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  6  Service ID:     dc04  Service Name: SWR4 MA           Component ID: 10");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  7  Service ID:     dd04  Service Name: SWR4 HN           Component ID: 8");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  8  Service ID:     de04  Service Name: SWR4 UL           Component ID: 12");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num:  9  Service ID: e0d010c9  Service Name: EPG               Component ID: 49152");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Num: 10  Service ID: e0d020eb  Service Name:   Component ID: 49153");
		output.addStandard("                                                               Component ID: 0");
		output.addStandard("Starting service SWR1             d301 2");
		output.addStandard("");

		DABService service = new DABService(serviceNumber, serviceId, serviceName);
		StartDABService command = new StartDABService(service);

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Wrong service started: SWR1 d301 expected: " + service.toString());
		command.parse(output);
	}

	@Test
	public void serialize() throws Exception {
		DABService service = new DABService(0, "d3a3", "SWR3");
		StartDABService command = new StartDABService(service);
		String serializedCommand = command.serialize();

		assertThat(serializedCommand, is(equalTo(" -f 0")));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(StartDABService.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}
