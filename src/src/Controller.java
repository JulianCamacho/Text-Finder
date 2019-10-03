import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {

    @FXML
    Button indexBtn;

    @FXML
    Button refreshBtn;

    @FXML
    Button addBtn;

    //MultipleFileChooser
    public void ButtonPlusAction(MouseEvent event){
        FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(new File("C\\users"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files", "*.pdf "),
                new FileChooser.ExtensionFilter("docx files", "*.docx"),
                new FileChooser.ExtensionFilter("txt files", "*.txt"));
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if (selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); i++){
                libraryListView.getItems().add(selectedFiles.get(i).getName());
                //hacer la lista de paths
                //pathList.add(selectedFile.get(i).getAbsolutePath());
            }
        } else {
            AlertBoxes.displayResultAlertBox("Exception", "Invalid file");
        }
    }

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

    @FXML
    ListView libraryListView;




    /***
     *  The AnchorPane that contains the VBox of resuts
     */
    @FXML
    AnchorPane searchPane;

    /***
     *  The VBox that contains the names of the loaded documents
     */
    @FXML
    VBox libraryPane;

    @FXML
    Separator separator1;

    @FXML
    Separator separator2;

    @FXML
    Separator separator3;

    public Controller(){}

    @FXML
    public void initialize(){
        addBtn.setOnMouseClicked(this::ButtonPlusAction);
    }



    public void updateSearchPane(String[] text, String[] names, String[] dates, String [] sizes){
        Label textLabel;
        Label nameLabel;
        Label dateLabel;
        Label sizeLabel;

        this.clearSearchPane();

        for(int i=0; i<text.length;i++){
            textLabel = new Label(text[i]);
            nameLabel = new Label(names[i]);
            dateLabel = new Label(dates[i]);
            sizeLabel = new Label(sizes[i]);

            textPane.getChildren().add(textLabel);
            namePane.getChildren().add(nameLabel);
            datePane.getChildren().add(dateLabel);
            sizePane.getChildren().add(sizeLabel);

            VBox.setMargin(textLabel, new Insets(5, 0, 5, 0));
            VBox.setMargin(nameLabel, new Insets(5, 0, 5, 0));
            VBox.setMargin(dateLabel, new Insets(5, 0, 5, 0));
            VBox.setMargin(sizeLabel, new Insets(5, 0, 5, 0));

        }


    }

    private void clearSearchPane(){
        textPane.getChildren().clear();
        namePane.getChildren().clear();
        datePane.getChildren().clear();
        sizePane.getChildren().clear();
    }


}
