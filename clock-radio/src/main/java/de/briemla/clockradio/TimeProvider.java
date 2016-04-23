package de.briemla.clockradio;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.ReadOnlyObjectProperty;

public interface TimeProvider {

    ReadOnlyObjectProperty<LocalTime> timeProperty();

    LocalTime nextMinute();

    LocalDateTime now();

    LocalDateTime today();

}