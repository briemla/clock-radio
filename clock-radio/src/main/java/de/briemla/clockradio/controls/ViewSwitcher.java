package de.briemla.clockradio.controls;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ViewSwitcher extends HBox {

    private final HashMap<Class<?>, Node> views;
    private Node currentView;

    public ViewSwitcher() {
        super();
        views = new HashMap<>();
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
        getChildren().add(node);
        disable(node);
    }

}
