package de.briemla.clockradio;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ViewSwitcher extends BorderPane {

	@FXML
	private HBox container;

	private final HashMap<Class<?>, Node> views;
	private Node defaultView;
	private Node currentView;

	public ViewSwitcher() {
		super();
		FXUtil.load(this, this);
		views = new HashMap<>();
	}

	/**
	 * Add a view to the {@link ViewSwitcher}. The first view will automatically be used as default
	 * view.
	 *
	 * @param view
	 */
	public void setDefaultView(Node view) {
		disableCurrentView();
		defaultView = view;
		container.getChildren().add(defaultView);
		enable(defaultView);
	}

	private void disableCurrentView() {
		disable(currentView);
	}

	private static void disable(Node view) {
		if (view == null) {
			return;
		}
		view.setVisible(false);
		view.setManaged(false);
	}

	private void enable(Node view) {
		currentView = view;
		view.setVisible(true);
		view.setManaged(true);
	}

	@SuppressWarnings("unchecked")
	public <T> T show(Class<?> clazz) {
		disableCurrentView();
		Node node = views.get(clazz);
		enable(node);
		return (T) node;
	}

	public void addView(Class<Alarm> clazz, Node node) {
		views.put(clazz, node);
		container.getChildren().add(node);
		disable(node);
	}

	@FXML
	public void back(ActionEvent event) {
		disableCurrentView();
		enable(defaultView);
	}

}
