package de.briemla.clockradio.controls;

import java.util.HashMap;

import de.briemla.clockradio.FXUtil;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ViewSwitcher extends BorderPane {

	@FXML
	private HBox container;
	@FXML
	private Button back;

	private final HashMap<Class<?>, Node> views;
	private final SimpleBooleanProperty defaultVisisble;
	private Node defaultView;
	private Node currentView;

	public ViewSwitcher() {
		super();
		FXUtil.load(this, this);
		views = new HashMap<>();
		defaultVisisble = new SimpleBooleanProperty(false);
		back.visibleProperty().bind(defaultVisisble.not());
	}

	/**
	 * Add a view to the {@link ViewSwitcher}. The first view will automatically
	 * be used as default view.
	 *
	 * @param view
	 */
	public void setDefaultView(Node view) {
		defaultView = view;
		container.getChildren().add(defaultView);
		showDefault();
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
		currentView.setVisible(true);
		currentView.setManaged(true);
		defaultVisisble.set(currentView.equals(defaultView));
	}

	@SuppressWarnings("unchecked")
	public <T> T show(Class<?> clazz) {
		disableCurrentView();
		Node node = views.get(clazz);
		enable(node);
		return (T) node;
	}

	public void addView(Class<?> clazz, Node node) {
		views.put(clazz, node);
		container.getChildren().add(node);
		disable(node);
	}

	@FXML
	public void back(ActionEvent event) {
		showDefault();
	}

	public void showDefault() {
		disableCurrentView();
		enable(defaultView);
		defaultVisisble.set(true);
	}

	public ReadOnlyBooleanProperty defaultVisisbleProperty() {
		return defaultVisisble;
	}

}
