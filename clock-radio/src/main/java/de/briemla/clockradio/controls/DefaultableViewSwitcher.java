package de.briemla.clockradio.controls;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import de.briemla.clockradio.FXUtil;

public class DefaultableViewSwitcher extends BorderPane {

    private static final class DefaultKey {
    }

    @FXML
    private ViewSwitcher container;
    @FXML
    private Button back;

    private final SimpleBooleanProperty defaultVisisble;
    private Node defaultView;

    public DefaultableViewSwitcher() {
        super();
        FXUtil.load(this, this);
        defaultVisisble = new SimpleBooleanProperty(false);
        back.visibleProperty().bind(defaultVisisble.not());
    }

    public void setDefaultView(Node view) {
        defaultView = view;
        container.addView(DefaultKey.class, view);
        defaultVisisble.bind(defaultView.visibleProperty());
        showDefault();
    }

    public <T> T show(Class<?> clazz) {
        return container.show(clazz);
    }

    public void addView(Class<?> clazz, Node node) {
        container.addView(clazz, node);
    }

    @FXML
    public void back(ActionEvent event) {
        showDefault();
    }

    public void showDefault() {
        show(DefaultKey.class);
    }

    public ReadOnlyBooleanProperty defaultVisisbleProperty() {
        return defaultVisisble;
    }

}
