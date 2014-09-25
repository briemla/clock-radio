package de.briemla.clockradio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

	private View defaultView;
	private Integer currentView;
	private final ObservableList<View> views;

	public ViewSwitcher() {
		super();
		FXUtil.load(this, this);
		currentView = 0;
		views = FXCollections.observableArrayList();
	}

	/**
	 * Add a view to the {@link ViewSwitcher}. The first view will automatically be used as default
	 * view.
	 *
	 * @param view
	 */
	public void addView(View view) {
		if (views.contains(view)) {
			return;
		}
		setAsDefault(view);
		views.add(view);
	}

	private void setAsDefault(View view) {
		if (views.isEmpty()) {
			defaultView = view;
			view.show();
			return;
		}
		view.hide();
	}

	/**
	 * Remove the given view from {@link ViewSwitcher}. If the given view is the default view, the
	 * default view will be replaced by the next view in the list.
	 *
	 * @param view
	 */
	public void removeView(View view) {
		views.remove(view);
		if (defaultView != null && !views.isEmpty() && defaultView.equals(view)) {
			defaultView = views.get(0);
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
		View view = views.get(currentView);
		view.hide();
	}

	private void enableCurrentView() {
		View view = views.get(currentView);
		view.show();
	}

	private void decreaseViewPointer() {
		currentView--;
		if (currentView < 0) {
			currentView = views.size() - 1;
		}
	}

	private void increaseViewPointer() {
		currentView++;
		if (currentView >= views.size()) {
			currentView = 0;
		}
	}

}
