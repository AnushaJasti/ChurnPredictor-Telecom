/*
 * ChurnPredictor_TelecomApp.java
 */

package churnpredictor_telecom;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ChurnPredictor_TelecomApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ChurnPredictor_TelecomView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ChurnPredictor_TelecomApp
     */
    public static ChurnPredictor_TelecomApp getApplication() {
        return Application.getInstance(ChurnPredictor_TelecomApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ChurnPredictor_TelecomApp.class, args);
    }
}
