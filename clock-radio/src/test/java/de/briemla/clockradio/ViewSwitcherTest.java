package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.BeforeClass;
import org.junit.Test;

import de.briemla.fxutils.FXUtils;

public class ViewSwitcherTest {

	@BeforeClass
	public static void startApplication() throws InterruptedException {
		FXUtils.startFxApplicationThread();
	}

	@Test
	public void addView() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View view = mock(View.class);

		switcher.addView(view);

		verify(view).show();
	}

	@Test
	public void addMultipleViews() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View defaultView = mock(View.class);
		View nextView = mock(View.class);

		switcher.addView(defaultView);
		switcher.addView(nextView);

		verify(defaultView).show();
		verifyNoMoreInteractions(defaultView);
		verify(nextView).hide();
		verifyNoMoreInteractions(nextView);
	}

	@Test
	public void addSameView() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View view = mock(View.class);

		switcher.addView(view);
		switcher.addView(view);

		verify(view).show();
		verifyNoMoreInteractions(view);
	}

	@Test
	public void previousOneTime() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View firstView = mock(View.class);
		View secondView = mock(View.class);
		View thirdView = mock(View.class);
		switcher.addView(firstView);
		switcher.addView(secondView);
		switcher.addView(thirdView);

		verify(firstView).show();
		verify(secondView).hide();
		verify(thirdView).hide();

		switcher.previous(null);
		verify(firstView).hide();
		verifyZeroInteractions(secondView);
		verify(thirdView).show();
	}

	@Test
	public void previousTwoTimes() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View firstView = mock(View.class);
		View secondView = mock(View.class);
		View thirdView = mock(View.class);
		switcher.addView(firstView);
		switcher.addView(secondView);
		switcher.addView(thirdView);

		switcher.previous(null);
		switcher.previous(null);
		verify(firstView).show();
		verify(firstView).hide();
		verify(secondView).hide();
		verify(secondView).show();
		verify(thirdView, times(2)).hide();
		verify(thirdView).show();
	}

	@Test
	public void nextOneTime() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View firstView = mock(View.class);
		View secondView = mock(View.class);
		View thirdView = mock(View.class);
		switcher.addView(firstView);
		switcher.addView(secondView);
		switcher.addView(thirdView);

		verify(firstView).show();
		verify(secondView).hide();
		verify(thirdView).hide();

		switcher.next(null);
		verify(firstView).hide();
		verify(secondView).show();
		verifyZeroInteractions(thirdView);
	}

	@Test
	public void nextTwoTimes() throws Exception {
		ViewSwitcher switcher = new ViewSwitcher();
		View firstView = mock(View.class);
		View secondView = mock(View.class);
		View thirdView = mock(View.class);
		switcher.addView(firstView);
		switcher.addView(secondView);
		switcher.addView(thirdView);

		switcher.next(null);
		switcher.next(null);
		verify(firstView).show();
		verify(firstView).hide();
		verify(secondView, times(2)).hide();
		verify(secondView).show();
		verify(thirdView).hide();
		verify(thirdView).show();
	}
}
