package de.briemla.clockradio;

/**
 * This class generates {@link Command}s and executes them on the given {@link RadioExecutor}
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

}
