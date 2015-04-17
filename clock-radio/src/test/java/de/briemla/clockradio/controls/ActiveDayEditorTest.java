package de.briemla.clockradio.controls;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;

import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class ActiveDayEditorTest extends GuiTest {

	private CheckBox monday;
	private CheckBox tuesday;
	private CheckBox wednesday;
	private CheckBox thursday;
	private CheckBox friday;
	private CheckBox saturday;
	private CheckBox sunday;
	private CheckBox workdays;
	private CheckBox weekend;
	private CheckBox daily;

	@Override
	protected Parent getRootNode() {
		return new ActiveDayEditor();
	}

	@Before
	public void initialize() {
		monday = find("#monday");
		tuesday = find("#tuesday");
		wednesday = find("#wednesday");
		thursday = find("#thursday");
		friday = find("#friday");
		saturday = find("#saturday");
		sunday = find("#sunday");
		workdays = find("#workdays");
		weekend = find("#weekend");
		daily = find("#daily");
	}

	@Test
	public void dailyAllSelected() throws Exception {
		selectAll();
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckMonday() throws Exception {
		selectAll();
		allChecked();
		click(monday);
		assertThat(monday.isSelected(), is(false));
		assertThat(tuesday.isSelected(), is(true));
		assertThat(wednesday.isSelected(), is(true));
		assertThat(thursday.isSelected(), is(true));
		assertThat(friday.isSelected(), is(true));
		assertThat(workdays.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		weekendChecked();
		click(monday);
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckTuesday() throws Exception {
		selectAll();
		allChecked();
		click(tuesday);
		assertThat(monday.isSelected(), is(true));
		assertThat(tuesday.isSelected(), is(false));
		assertThat(wednesday.isSelected(), is(true));
		assertThat(thursday.isSelected(), is(true));
		assertThat(friday.isSelected(), is(true));
		assertThat(workdays.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		weekendChecked();
		click(tuesday);
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckWednesday() throws Exception {
		selectAll();
		allChecked();
		click(wednesday);
		assertThat(monday.isSelected(), is(true));
		assertThat(tuesday.isSelected(), is(true));
		assertThat(wednesday.isSelected(), is(false));
		assertThat(thursday.isSelected(), is(true));
		assertThat(friday.isSelected(), is(true));
		assertThat(workdays.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		weekendChecked();
		click(wednesday);
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckThursday() throws Exception {
		selectAll();
		allChecked();
		click(thursday);
		assertThat(monday.isSelected(), is(true));
		assertThat(tuesday.isSelected(), is(true));
		assertThat(wednesday.isSelected(), is(true));
		assertThat(thursday.isSelected(), is(false));
		assertThat(friday.isSelected(), is(true));
		assertThat(workdays.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		weekendChecked();
		click(thursday);
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckFriday() throws Exception {
		selectAll();
		allChecked();
		click(friday);
		assertThat(monday.isSelected(), is(true));
		assertThat(tuesday.isSelected(), is(true));
		assertThat(wednesday.isSelected(), is(true));
		assertThat(thursday.isSelected(), is(true));
		assertThat(friday.isSelected(), is(false));
		assertThat(workdays.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		weekendChecked();
		click(friday);
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckSaturday() throws Exception {
		selectAll();
		allChecked();
		click(saturday);
		assertThat(saturday.isSelected(), is(false));
		assertThat(sunday.isSelected(), is(true));
		assertThat(weekend.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		workdaysChecked();
		click(saturday);
		allChecked();
	}

	@Test
	public void dailyAllSelectedCheckSunday() throws Exception {
		selectAll();
		allChecked();
		click(sunday);
		assertThat(saturday.isSelected(), is(true));
		assertThat(sunday.isSelected(), is(false));
		assertThat(weekend.isSelected(), is(false));
		assertThat(daily.isSelected(), is(false));
		workdaysChecked();
		click(sunday);
		allChecked();
	}

	private void allChecked() {
		workdaysChecked();
		weekendChecked();
		assertThat(daily.isSelected(), is(true));
	}

	private void workdaysChecked() {
		assertThat(monday.isSelected(), is(true));
		assertThat(tuesday.isSelected(), is(true));
		assertThat(wednesday.isSelected(), is(true));
		assertThat(thursday.isSelected(), is(true));
		assertThat(friday.isSelected(), is(true));
		assertThat(workdays.isSelected(), is(true));
	}

	private void weekendChecked() {
		assertThat(saturday.isSelected(), is(true));
		assertThat(sunday.isSelected(), is(true));
		assertThat(weekend.isSelected(), is(true));
	}

	private void selectAll() {
		click(monday);
		click(tuesday);
		click(wednesday);
		click(thursday);
		click(friday);
		click(saturday);
		click(sunday);
	}
}
