package de.briemla.clockradio.controls;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngleTest {

	@Test
	public void zeroOClock() throws Exception {
		Integer topRightQuadrant = Angle.toHour(91d, 20d, 180d, 180d);
		assertThat(topRightQuadrant, is(equalTo(0)));
		Integer topLeftQuadrant = Angle.toHour(89d, 20d, 180d, 180d);
		assertThat(topLeftQuadrant, is(equalTo(0)));
	}

	@Test
	public void oneOClock() throws Exception {
		Integer hour = Angle.toHour(100d, 73d, 180d, 180d);

		assertThat(hour, is(equalTo(1)));
	}

	@Test
	public void twoOClock() throws Exception {
		Integer hour = Angle.toHour(150d, 49d, 180d, 180d);

		assertThat(hour, is(equalTo(2)));
	}

	@Test
	public void threeOClock() throws Exception {
		Integer topRightQuadrant = Angle.toHour(160d, 89d, 180d, 180d);
		assertThat(topRightQuadrant, is(equalTo(3)));
		Integer bottomRightQuadrant = Angle.toHour(160d, 91d, 180d, 180d);
		assertThat(bottomRightQuadrant, is(equalTo(3)));
	}

	@Test
	public void fourOClock() throws Exception {
		Integer hour = Angle.toHour(150d, 131d, 180d, 180d);

		assertThat(hour, is(equalTo(4)));
	}

	@Test
	public void fiveOClock() throws Exception {
		Integer hour = Angle.toHour(100d, 107d, 180d, 180d);

		assertThat(hour, is(equalTo(5)));
	}

	@Test
	public void sixOClock() throws Exception {
		Integer bottomRightQuadrant = Angle.toHour(91d, 140d, 180d, 180d);
		assertThat(bottomRightQuadrant, is(equalTo(6)));
		Integer bottomLeftQuadrant = Angle.toHour(89d, 140d, 180d, 180d);
		assertThat(bottomLeftQuadrant, is(equalTo(6)));
	}

	@Test
	public void sevenOClock() throws Exception {
		Integer hour = Angle.toHour(80d, 107d, 180d, 180d);

		assertThat(hour, is(equalTo(7)));
	}

	@Test
	public void eightOClock() throws Exception {
		Integer hour = Angle.toHour(30d, 131d, 180d, 180d);

		assertThat(hour, is(equalTo(8)));
	}

	@Test
	public void nineOClock() throws Exception {
		Integer bottomLeftQuadrant = Angle.toHour(20d, 91d, 180d, 180d);
		assertThat(bottomLeftQuadrant, is(equalTo(9)));
		Integer topLeftQuadrant = Angle.toHour(20d, 89d, 180d, 180d);
		assertThat(topLeftQuadrant, is(equalTo(9)));
	}

	@Test
	public void tenOClock() throws Exception {
		Integer hour = Angle.toHour(30d, 49d, 180d, 180d);

		assertThat(hour, is(equalTo(10)));
	}

	@Test
	public void elevenOClock() throws Exception {
		Integer hour = Angle.toHour(80d, 73d, 180d, 180d);

		assertThat(hour, is(equalTo(11)));
	}
	//
	// @Test
	// public void twelveOClock() throws Exception {
	// Integer topRightQuadrant = Angle.toHour(91d, 10d, 180d, 180d);
	// assertThat(topRightQuadrant, is(equalTo(12)));
	// Integer topLeftQuadrant = Angle.toHour(89d, 10d, 180d, 180d);
	// assertThat(topLeftQuadrant, is(equalTo(12)));
	// }
	//
	// @Test
	// public void thirteenOClock() throws Exception {
	// Integer hour = Angle.toHour(130d, 22d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(13)));
	// }
	//
	// @Test
	// public void fourteenOClock() throws Exception {
	// Integer hour = Angle.toHour(171d, 35d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(14)));
	// }
	//
	// @Test
	// public void fivteenOClock() throws Exception {
	// Integer topRightQuadrant = Angle.toHour(170d, 89d, 180d, 180d);
	// assertThat(topRightQuadrant, is(equalTo(15)));
	// Integer bottomRightQuadrant = Angle.toHour(170d, 91d, 180d, 180d);
	// assertThat(bottomRightQuadrant, is(equalTo(15)));
	// }
	//
	// @Test
	// public void sixteenOClock() throws Exception {
	// Integer hour = Angle.toHour(171d, 145d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(16)));
	// }
	//
	// @Test
	// public void seventeenOClock() throws Exception {
	// Integer hour = Angle.toHour(130d, 158d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(17)));
	// }
	//
	// @Test
	// public void eighteenOClock() throws Exception {
	// Integer bottomRightQuadrant = Angle.toHour(91d, 170d, 180d, 180d);
	// assertThat(bottomRightQuadrant, is(equalTo(18)));
	// Integer bottomLeftQuadrant = Angle.toHour(89d, 170d, 180d, 180d);
	// assertThat(bottomLeftQuadrant, is(equalTo(18)));
	// }
	//
	// @Test
	// public void nineteenOClock() throws Exception {
	// Integer hour = Angle.toHour(50d, 158d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(19)));
	// }
	//
	// @Test
	// public void twentyOClock() throws Exception {
	// Integer hour = Angle.toHour(9d, 145d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(20)));
	// }
	//
	// @Test
	// public void twentyOneOClock() throws Exception {
	// Integer bottomLeftQuadrant = Angle.toHour(10d, 91d, 180d, 180d);
	// assertThat(bottomLeftQuadrant, is(equalTo(21)));
	// Integer topLeftQuadrant = Angle.toHour(10d, 89d, 180d, 180d);
	// assertThat(topLeftQuadrant, is(equalTo(21)));
	// }
	//
	// @Test
	// public void twentyTwoOClock() throws Exception {
	// Integer hour = Angle.toHour(9d, 35d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(22)));
	// }
	//
	// @Test
	// public void TwentyThreeOClock() throws Exception {
	// Integer hour = Angle.toHour(50d, 22d, 180d, 180d);
	//
	// assertThat(hour, is(equalTo(23)));
	// }
}
