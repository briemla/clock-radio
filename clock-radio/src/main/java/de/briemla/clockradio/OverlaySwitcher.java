package de.briemla.clockradio;

import java.util.HashMap;

import javafx.scene.Node;

public class OverlaySwitcher {

	private final HashMap<Class<?>, Node> views;
	private Node currentView;
	private final Node overlay;

	public OverlaySwitcher(Node overlay) {
		super();
		this.overlay = overlay;
		views = new HashMap<>();
	}

	private void disableCurrentView() {
		disable(overlay);
		disable(currentView);
	}

	private static void disable(Node view) {
		if (view == null) {
			return;
		}
		view.setVisible(false);
		view.setManaged(false);
	}

	private static void enable(Node view) {
		view.setVisible(true);
		view.setManaged(true);
	}

	@SuppressWarnings("unchecked")
	public <T> T show(Class<?> clazz) {
		disableCurrentView();
		Node node = views.get(clazz);
		currentView = node;
		enable(node);
		enable(overlay);
		return (T) node;
	}

	public void addView(Class<Alarm> clazz, Node node) {
		views.put(clazz, node);
		disable(node);
	}

}
