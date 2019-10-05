import javafx.scene.control.Alert;

/**
 *Clase para generar JavaFX AlertBoxes.
 */
public class AlertBoxes {

    /**
     * Método que crea un AlerBox personalizado, ideal para manejo de excepciones.
     * @param title - Título de la ventana emergente.
     * @param message - Mensaje que se incluirá en la ventana.
     */
    public static void displayAlertBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Método que crea un AlerBox personalizado, ideal para mostrar resultados.
     * @param title - Título de la ventana emergente.
     * @param message - Mensaje que se incluirá en la ventana.
     */
    public static void displayResultAlertBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

