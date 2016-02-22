package de.briemla.clockradio.controls;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Settings;

public class AlarmCell extends AnchorPane {

    private static enum State {
        ACTIVE("add.png"), INACTIVE("inactive.png");

        private ImageView image;

        private State(String fileName) {
            image = new ImageView(image(fileName));
        }

        private ImageView image() {
            return image;
        }

        private static Image image(String fileName) {
            return new Image(State.class.getResourceAsStream(fileName));
        }
    }

    private static final String activeImage = "active.png";
    @FXML
    private AnchorPane container;
    @FXML
    private ToggleButton active;
    @FXML
    private Label time;
    @FXML
    private Label weekdays;
    @FXML
    private Label mediaDescription;
    @FXML
    private Button settings;

    private final BooleanProperty activeProperty;

    public AlarmCell(Alarm alarm, Settings settings) {
        super();
        FXUtil.load(this, this);
        activeProperty = new ActivePseudoClassProperty(this);
        // TODO maybe create timeProperty in Alarm class
        time.textProperty().bind(alarm.wakeUpTimeProperty().asString());
        mediaDescription.textProperty().bind(alarm.mediaProperty().asString());
        weekdays.textProperty().bind(alarm.activeDaysProperty().asString());
        activeProperty.bindBidirectional(alarm.activatedProperty());
        active.selectedProperty().bindBidirectional(activeProperty);
        // active.addEventHandler(MouseEvent.MOUSE_CLICKED, this::switchIcon);
        setOnMouseClicked(event -> settings.select(alarm));
    }

    public void unbind() {
        time.textProperty().unbind();
        weekdays.textProperty().unbind();
        mediaDescription.textProperty().unbind();
    }

    public BooleanProperty activatedProperty() {
        return activeProperty;
    }

    private void switchIcon(MouseEvent event) {
        ImageView icon = active.isSelected() ? State.ACTIVE.image() : State.INACTIVE.image();
        active.setGraphic(icon);
    }
}
