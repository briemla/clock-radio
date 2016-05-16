package de.briemla.clockradio;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileStorage implements AlarmStorage {

    private static final String closing = "closing";
    private static final String backupExtension = ".backup";
    private final File storagepath;
    private final AlarmFactory alarmFactory;
    private final Path backupFile;
    private final OutputFactory outputFactory;
    private final ExceptionHandler exceptionHandler;

    public FileStorage(File storagepath, AlarmFactory alarmFactory, OutputFactory outputFactory,
            ExceptionHandler exceptionHandler) {
        super();
        this.storagepath = storagepath;
        this.alarmFactory = alarmFactory;
        this.outputFactory = outputFactory;
        this.exceptionHandler = exceptionHandler;
        backupFile = backupFileFrom(storagepath);
    }

    private static Path backupFileFrom(File storagepath) {
        File folder = storagepath.getParentFile();
        String storageFileName = storagepath.getName();
        String backupFileName = storageFileName + backupExtension;
        return new File(folder, backupFileName).toPath();
    }

    @Override
    public void save(List<Alarm> alarms) {
        if (backupFile()) {
            return;
        }
        try (Writer file = outputFactory.create(storagepath)) {
            for (Alarm alarm : alarms) {
                alarm.storeTo(file);
            }
            addClosingTo(file);
        } catch (IOException exception) {
            exceptionHandler.handle(exception);
        }
    }

    private void addClosingTo(Writer file) throws IOException {
        file.write(closing);
    }

    private boolean backupFile() {
        if (!storagepath.exists()) {
            return false;
        }
        try {
            Files.copy(storagepath.toPath(), backupFile);
            return false;
        } catch (IOException exception) {
            return true;
        }
    }

    @Override
    public List<Alarm> load() {
        if (!storagepath.exists()) {
            return Collections.emptyList();
        }
        restoreFromBackupIfNeeded();
        return loadUndamagedFile();
    }

    private void restoreFromBackupIfNeeded() {
        try {
            if (fileIsUndamaged()) {
                return;
            }
            restoreFromBackup();
        } catch (IOException exception) {
            // TODO log when backup could not be restored
        }
    }

    private boolean fileIsUndamaged() throws IOException {
        List<String> fileContent = fileContent();
        if (fileContent.isEmpty()) {
            // TODO is this really allowed???
            return true;
        }
        String lastElement = fileContent.get(fileContent.size() - 1);
        return closing.equals(lastElement);
    }

    private void restoreFromBackup() throws IOException {
        if (backupFile.toFile().exists()) {
            Files.copy(backupFile, storagepath.toPath(), REPLACE_EXISTING);
        }
        // TODO log when backup could not be restored
    }

    private List<Alarm> loadUndamagedFile() {
        try {
            return fileContent().stream()
                                .filter(line -> !closing.equals(line))
                                .map(alarmFactory::fromStorage)
                                .collect(toList());
        } catch (IOException exception) {
            throw new RuntimeException("Could not load alarms", exception);
        }
    }

    private List<String> fileContent() throws IOException {
        return Files.readAllLines(storagepath.toPath());
    }

}
