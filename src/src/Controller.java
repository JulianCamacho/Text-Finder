import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
        deleteBtn.setOnMouseClicked(this::ButtonMinus);

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

    //MultipleFileChooser
    public void ButtonPlusAction(MouseEvent event){
        FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(new File("C\\users"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files", "*.PDF "),
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


    public void ButtonMinus(MouseEvent event)  {
        //TxtReader.txtReader("C:\\Users\\toshiba\\Documents\\!A -- TEC -- II Semestre -- 2019\\Algoritmos y Estructuras de Datos I\\Archivos\\fileChooserCode.txt");
        /*try{
            PdfReader.pdfReader("C:\\Users\\toshiba\\Documents\\!A -- TEC -- II Semestre -- 2019\\Algoritmos y Estructuras de Datos I\\Archivos\\Proyecto #2 - Text Finder.pdf");
        }catch (IOException ex){
            AlertBoxes.displayResultAlertBox("", "");}*/
        try{
        DocxReader.docxReader("C:\\Users\\toshiba\\Documents\\!A -- TEC -- II Semestre -- 2019\\Algoritmos y Estructuras de Datos I\\Archivos\\I.docx");
        }catch (JAXBException | Docx4JException e){
            AlertBoxes.displayResultAlertBox("", "");
        }
        }



}
