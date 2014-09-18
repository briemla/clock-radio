package de.briemla.clockradio;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import de.briemla.fxmltemplateloader.FXMLTemplateLoader;
import de.briemla.fxmltemplateloader.ITemplate;

public class FXUtil {

	private static final String FXML_EXTENSION = ".fxml";

	/**
	 * Loads a FXML file with the same name as the controller. IOExceptions will be thrown as RuntimeExceptions to keep client code clean.
	 *
	 * @param controller
	 * @return
	 */
	static <T> T load(Object controller) {
		URL resource = findResource(controller);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(resource);
		loader.setController(controller);
		try {
			return loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private static URL findResource(Object controller) {
		String simpleName = controller.getClass().getSimpleName();
		String fileName = simpleName.concat(FXML_EXTENSION);
		return FXUtil.class.getResource(fileName);
	}

	/**
	 * Loads a FXML file with the same name as the controller. IOExceptions will be thrown as RuntimeExceptions to keep client code clean.
	 *
	 * @param controller
	 * @param root
	 * @return
	 */
	static <T> T load(Object controller, Object root) {
		URL resource = findResource(controller);
		try {
			ITemplate loadedTemplate = FXMLTemplateLoader.loadTemplate(resource);
			loadedTemplate.setRoot(root);
			return loadedTemplate.create(controller);
		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			throw new RuntimeException(exception);
		}
	}

}
