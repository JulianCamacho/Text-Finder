import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    Button indexBtn;

    @FXML
    Button refreshBtn;

    @FXML
    Button addBtn;

    @FXML
    Button deleteBtn;

    @FXML
    Button searchBtn;

    @FXML
    TextField inputField;

    @FXML
    VBox textPane;

    @FXML
    VBox namePane;

    @FXML
    VBox sizePane;


    @FXML
    VBox datePane;

    /***
     *  The Scrollpane that contains the VBox of resuts
     */
    @FXML
    ScrollPane searchPane;

    /***
     *  The VBox that contains the names of the loaded documents
     */
    @FXML
    VBox libraryPane;


    public Controller(){}

    @FXML
    public void initialize(){

    }
}
