package de.briemla.clockradio.controls;

import static org.junit.Assert.assertThat;
import static org.loadui.testfx.controls.Commons.hasText;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class TimeEditorTest extends GuiTest {

	@Override
	protected Parent getRootNode() {
		return new TimeEditor();
	}

	@Test
	public void clickAtZeroOClock() {
		checkHour(0);
	}

	@Test
	public void clickAtOneOClock() {
		checkHour(1);
	}

	@Test
	public void clickAtTwoOClock() {
		checkHour(2);
	}

	@Test
	public void clickAtThreeOClock() {
		checkHour(3);
	}

	@Test
	public void clickAtFourOClock() {
		checkHour(4);
	}

	@Test
	public void clickAtFiveOClock() {
		checkHour(5);
	}

	@Test
	public void clickAtSixOClock() {
		checkHour(6);
	}

	@Test
	public void clickAtSevenOClock() {
		checkHour(7);
	}

	@Test
	public void clickAtEightOClock() {
		checkHour(8);
	}

	@Test
	public void clickAtNineOClock() {
		checkHour(9);
	}

	@Test
	public void clickAtTenOClock() {
		checkHour(10);
	}

	@Test
	public void clickAtElevenOClock() {
		checkHour(11);
	}

	@Test
	public void clickAtTwelveOClock() {
		checkHour(12);
	}

	@Test
	public void clickAtThirteenOClock() {
		checkHour(13);
	}

	@Test
	public void clickAtFourteenOClock() {
		checkHour(14);
	}

	@Test
	public void clickAtFivteenOClock() {
		checkHour(15);
	}

	@Test
	public void clickAtSixtennOClock() {
		checkHour(16);
	}

	@Test
	public void clickAtSeventeenOClock() {
		checkHour(17);
	}

	@Test
	public void clickAtEighteenOClock() {
		checkHour(18);
	}

	@Test
	public void clickAtNineteenOClock() {
		checkHour(19);
	}

	@Test
	public void clickAtTwentyOClock() {
		checkHour(20);
	}

	@Test
	public void clickAtTwentyOneOClock() {
		checkHour(21);
	}

	@Test
	public void clickAtTwentyTwoOClock() {
		checkHour(22);
	}

	@Test
	public void clickAtTwentyThreeOClock() {
		checkHour(23);
	}

	private void checkHour(int hour) {
		click("#hour" + hour);
		assertThat("#time", hasText(String.format("%02d:00", hour)));
	}

	@Test
	public void clickAtFiveMinutes() {
		checkMinute(5);
	}

	@Test
	public void clickAtTenMinutes() {
		checkMinute(10);
	}

	@Test
	public void clickAtThirteenMinutes() {
		checkMinute(15);
	}

	@Test
	public void clickAtTwentyMinutes() {
		checkMinute(20);
	}

	@Test
	public void clickAtTwentyFiveMinutes() {
		checkMinute(25);
	}

	@Test
	public void clickAtThirtyMinutes() {
		checkMinute(30);
	}

	@Test
	public void clickAtThirtyFiveMinutes() {
		checkMinute(35);
	}

	@Test
	public void clickAtFourtyMinutes() {
		checkMinute(40);
	}

	@Test
	public void clickAtFourtyFiveMinutes() {
		checkMinute(45);
	}

	@Test
	public void clickAtFivtyMinutes() {
		checkMinute(50);
	}

	@Test
	public void clickAtFivtyFiveMinutes() {
		checkMinute(55);
	}

	private void checkMinute(int minutes) {
		click("#minute" + minutes);
		assertThat("#time", hasText(String.format("00:%02d", minutes)));
	}

}
