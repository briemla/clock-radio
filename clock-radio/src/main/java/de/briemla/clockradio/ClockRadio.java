package de.briemla.clockradio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.briemla.clockradio.controls.MainPanel;
import de.briemla.clockradio.controls.Trigger;
import de.briemla.clockradio.player.AudioFilePlayer;
import de.briemla.clockradio.player.BasePlayer;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.RadioPlayer;
import de.briemla.clockradio.player.RealPlayerFactory;

public class ClockRadio extends Application {

    private static final File storagePath = new File("alarm.storage");

    public static void main(String[] args) {
        Application.launch(ClockRadio.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TimeProvider timeProvider = new RealTimeProvider();
        Trigger trigger = new AlarmTrigger(timeProvider);
        Player availablePlayer = availablePlayer();
        PlayerFactory playerFactory = playerFactory(availablePlayer);
        AlarmFactory alarmFactory = new RealAlarmFactory(playerFactory, timeProvider);
        ExceptionHandler exceptionHandler = playMusicOnError(playerFactory, logger());
        AlarmStorage storage = new FileStorage(storagePath, alarmFactory, outputFactory(),
                exceptionHandler);
        MainPanel mainPanel = new MainPanel(availablePlayer, trigger, alarmFactory, timeProvider,
                storage);
        mainPanel.getStylesheets()
                 .add(ClockRadio.class.getResource("clock-radio.css").toExternalForm());
        watchCssFile(mainPanel);
        primaryStage.setScene(new Scene(mainPanel));
        primaryStage.show();
    }

    private Logger logger() {
        return (text, exception) -> {};
    }

    private ExceptionHandler playMusicOnError(PlayerFactory playerFactory, Logger logger) {
        return new PlayMusicOnError(playerFactory, logger);
    }

    private static OutputFactory outputFactory() {
        return (file) -> new BufferedWriter(new FileWriter(file));
    }

    private PlayerFactory playerFactory(Player availablePlayer) {
        return new RealPlayerFactory(availablePlayer);
    }

    private static void watchCssFile(MainPanel mainPanel)
            throws URISyntaxException, MalformedURLException {
        CssFile.watch(mainPanel);

    }

    private static RadioPlayer radioPlayer() {
        return Dabpi.createRadioPlayer();
    }

    /**
     * Will check which player is available. On Raspberry Pi there is no javafx.scene.media support,
     * so we need another player.
     *
     * @return
     */
    private static Player availablePlayer() {
        return new BasePlayer(new AudioFilePlayer(), radioPlayer());
        // return new JavaFxPlayer();
    }

}
