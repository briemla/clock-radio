package de.briemla.clockradio.controls;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class TimeTest extends GuiTest {

	@Override
	protected Parent getRootNode() {
		return new Time();
	}

	@Test
	public void clickAtZeroOClock() {
		click("#0");
		verifyThat("#hour", hasText("00"));
	}

	@Test
	public void clickAtOneOClock() {
		click("#1");
		verifyThat("#hour", hasText("01"));
	}

	@Test
	public void clickAtTwoOClock() {
		click("#2");
		verifyThat("#hour", hasText("02"));
	}

	@Test
	public void clickAtThreeOClock() {
		click("#3");
		verifyThat("#hour", hasText("03"));
	}

	@Test
	public void clickAtFourOClock() {
		click("#4");
		verifyThat("#hour", hasText("04"));
	}

	@Test
	public void clickAtFiveOClock() {
		click("#5");
		verifyThat("#hour", hasText("05"));
	}

	@Test
	public void clickAtSixOClock() {
		click("#6");
		verifyThat("#hour", hasText("06"));
	}

	@Test
	public void clickAtSevenOClock() {
		click("#7");
		verifyThat("#hour", hasText("07"));
	}

	@Test
	public void clickAtEightOClock() {
		click("#8");
		verifyThat("#hour", hasText("08"));
	}

	@Test
	public void clickAtNineOClock() {
		click("#9");
		verifyThat("#hour", hasText("09"));
	}

	@Test
	public void clickAtTenOClock() {
		click("#10");
		verifyThat("#hour", hasText("10"));
	}

	@Test
	public void clickAtElevenOClock() {
		click("#11");
		verifyThat("#hour", hasText("11"));
	}

	@Test
	public void clickAtTwelveOClock() {
		click("#12");
		verifyThat("#hour", hasText("12"));
	}

	@Test
	public void clickAtThirteenOClock() {
		click("#13");
		verifyThat("#hour", hasText("13"));
	}

	@Test
	public void clickAtFourteenOClock() {
		click("#14");
		verifyThat("#hour", hasText("14"));
	}

	@Test
	public void clickAtFivteenOClock() {
		click("#15");
		verifyThat("#hour", hasText("15"));
	}

	@Test
	public void clickAtSixtennOClock() {
		click("#16");
		verifyThat("#hour", hasText("16"));
	}

	@Test
	public void clickAtSeventeenOClock() {
		click("#17");
		verifyThat("#hour", hasText("17"));
	}

	@Test
	public void clickAtEighteenOClock() {
		click("#18");
		verifyThat("#hour", hasText("18"));
	}

	@Test
	public void clickAtNineteenOClock() {
		click("#19");
		verifyThat("#hour", hasText("19"));
	}

	@Test
	public void clickAtTwentyOClock() {
		click("#20");
		verifyThat("#hour", hasText("20"));
	}

	@Test
	public void clickAtTwentyOneOClock() {
		click("#21");
		verifyThat("#hour", hasText("21"));
	}

	@Test
	public void clickAtTwentyTwoOClock() {
		click("#22");
		verifyThat("#hour", hasText("22"));
	}

	@Test
	public void clickAtTwentyThreeOClock() {
		click("#23");
		verifyThat("#hour", hasText("23"));
	}

}
