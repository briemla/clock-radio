package de.briemla.clockradio;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioFilePlayer {

	private final Object lineLock = new Object();
	private SourceDataLine line;

	/**
	 * Method blocks until audio input is finished or closed.
	 */
	public void play(URI uriToPlay) {
		System.out.println("Start: " + LocalTime.now());
		startAudio(new File(uriToPlay));
	}

	private static AudioFormat getOutFormat(AudioFormat inFormat) {
		int ch = inFormat.getChannels();
		float rate = inFormat.getSampleRate();
		return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
	}

	private static void stream(AudioInputStream in, SourceDataLine line) throws IOException {
		byte[] buffer = new byte[65536];
		for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
			line.write(buffer, 0, n);
		}
	}

	public void stop() {
		System.out.println("Stop: " + LocalTime.now());
		synchronized (lineLock) {
			if (line != null) {
				line.close();
				line.stop();
				line = null;
			}
		}
	}

	private void startAudio(File file) {
		try (AudioInputStream in = getAudioInputStream(file)) {

			AudioFormat outFormat = getOutFormat(in.getFormat());
			Info info = new Info(SourceDataLine.class, outFormat);

			line = (SourceDataLine) AudioSystem.getLine(info);
			if (line != null) {
				try {
					line.open(outFormat);
					line.start();
					stream(getAudioInputStream(outFormat, in), line);
				} finally {
					stop();
				}
			}
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			throw new IllegalStateException(e);
		}
	}
}