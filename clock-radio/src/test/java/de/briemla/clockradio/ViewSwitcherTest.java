package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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
}
