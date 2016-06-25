package de.briemla.clockradio.controls;

import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import de.briemla.clockradio.FxUtil;
import de.briemla.clockradio.Media;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;

public class StationSelector extends VBox {

    private final SimpleObjectProperty<Media> mediaProperty;
    private final SimpleObjectProperty<RadioMedia> radioMediaProperty;

    @FXML
    private Button refresh;
    @FXML
    private Label source;
    @FXML
    private ListView<Station> station;
    private final ObservableList<Station> stationList;
    private final SearchStation searcher;
    private final Player player;
    private final Run run;
    private final Transition progress;

    public StationSelector(SearchStation searcher, Player player, Run run) {
        super();
        this.searcher = searcher;
        this.player = player;
        this.run = run;
        FxUtil.load(this, this);
        mediaProperty = new SimpleObjectProperty<>();
        radioMediaProperty = new SimpleObjectProperty<>(defaultStation());
        stationList = FXCollections.observableArrayList();
        progress = refreshRotation();
        mediaProperty.addListener((change, oldValue, newValue) -> {
            if (newValue != null && newValue instanceof RadioMedia) {
                radioMediaProperty.set((RadioMedia) newValue);
            }
        });
        source.textProperty().bind(radioMediaProperty.asString());
        initializeContentViewer();
    }

    private Transition refreshRotation() {
        RotateTransition transition = new RotateTransition(Duration.millis(500), refresh);
        transition.setByAngle(180.0);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        return transition;
    }

    private static RadioMedia defaultStation() {
        return new RadioMedia(new NoStation());
    }

    private void initializeContentViewer() {
        station.setCellFactory(listView -> new StationCell(mediaProperty));
        station.setItems(stationList);
        updateStations();
    }

    public ObjectProperty<Media> mediaProperty() {
        return mediaProperty;
    }

    @FXML
    public void refresh(Event event) {
        run.run(this::updateStations);
    }

    @FXML
    public void play(Event event) {
        radioMediaProperty.get().create().play(player);
    }

    private void updateStations() {
        startUpdate();
        stationList.clear();
        stationList.addAll(searcher.search());
        finishUpdate();
    }

    private void startUpdate() {
        progress.play();
    }

    private void finishUpdate() {
        progress.stop();
    }
}
