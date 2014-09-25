package de.briemla.clockradio;

import java.util.List;

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
	private Button previous;
	@FXML
	private Button next;

	private Node defaultView;
	private Integer currentView;

	public ViewSwitcher() {
		super();
		FXUtil.load(this, this);
		currentView = 0;
	}

	/**
	 * Add a view to the {@link ViewSwitcher}. The first view will automatically be used as default
	 * view.
	 *
	 * @param view
	 */
	public void addView(Node view) {
		if (views().contains(view)) {
			return;
		}
		setAsDefault(view);
		views().add(view);
	}

	private void setAsDefault(Node view) {
		if (views().isEmpty()) {
			defaultView = view;
			view.setVisible(true);
			view.setManaged(true);
			return;
		}
		view.setVisible(false);
		view.setManaged(false);
	}

	/**
	 * Remove the given view from {@link ViewSwitcher}. If the given view is the default view, the
	 * default view will be replaced by the next view in the list.
	 *
	 * @param view
	 */
	public void removeView(Node view) {
		views().remove(view);
		if (defaultView != null && !views().isEmpty() && defaultView.equals(view)) {
			defaultView = views().get(0);
		}
	}

	@SuppressWarnings("unused")
	@FXML
	public void previous(ActionEvent event) {
		disableCurrentView();
		decreaseViewPointer();
		enableCurrentView();
	}

	@SuppressWarnings("unused")
	@FXML
	public void next(ActionEvent event) {
		disableCurrentView();
		increaseViewPointer();
		enableCurrentView();
	}

	private void disableCurrentView() {
		Node view = views().get(currentView);
		view.setVisible(false);
		view.setManaged(false);
	}

	private void enableCurrentView() {
		Node view = views().get(currentView);
		view.setVisible(true);
		view.setManaged(true);
	}

	private void decreaseViewPointer() {
		currentView--;
		if (currentView < 0) {
			currentView = views().size() - 1;
		}
	}

	private void increaseViewPointer() {
		currentView++;
		if (currentView >= views().size()) {
			currentView = 0;
		}
	}

	private List<Node> views() {
		return container.getChildren();
	}

}
