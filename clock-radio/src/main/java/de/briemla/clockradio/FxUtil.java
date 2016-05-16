package de.briemla.clockradio;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import javafx.fxml.FXMLLoader;

import de.briemla.fxmltemplateloader.FxmlTemplateLoader;
import de.briemla.fxmltemplateloader.template.ITemplate;

public class FxUtil {

    private static final String FXML_EXTENSION = ".fxml";

    /**
     * Loads a FXML file with the same name as the controller. IOExceptions will be thrown as
     * RuntimeExceptions to keep client code clean.
     *
     * @param controller
     * @return
     */
    public static <T> T load(Object controller) {
        URL resource = findResource(controller);
        FxmlTemplateLoader loader = new FxmlTemplateLoader();
        loader.setController(controller);
        try {
            return loader.doLoad(resource);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static URL findResource(Object controller) {
        String simpleName = controller.getClass().getSimpleName();
        String fileName = simpleName.concat(FXML_EXTENSION);
        return controller.getClass().getResource(fileName);
    }

    /**
     * Loads a FXML file with the same name as the controller. IOExceptions will be thrown as
     * RuntimeExceptions to keep client code clean.
     *
     * @param controller
     * @param root
     * @return
     */
    public static <T> T load(Object controller, Object root) {
        URL resource = findResource(controller);
        // return loadWithFXMLLoader(controller, root, resource);
        return loadWithFXMLTemplateLoader(controller, root, resource);
    }

    private static <T> T loadWithFXMLTemplateLoader(Object controller, Object root, URL resource) {
        try {
            ITemplate loadedTemplate = FxmlTemplateLoader.loadTemplate(resource);
            loadedTemplate.setRoot(root);
            return loadedTemplate.create(controller);
        } catch (IOException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Just to test against the original FXMLLoader
     *
     * @param controller
     * @param root
     * @param resource
     * @return
     */
    @SuppressWarnings("unused")
    private static <T> T loadWithFXMLLoader(Object controller, Object root, URL resource) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(resource);
            fxmlLoader.setRoot(root);
            fxmlLoader.setController(controller);
            return fxmlLoader.load();
        } catch (IOException | IllegalArgumentException exception) {
            throw new RuntimeException(exception);
        }
    }

}
