import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    ListView textPane;

    @FXML
    ListView namePane;

    @FXML
    ListView sizePane;

    @FXML
    ListView datePane;

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

    Searcher searcher;


    List<File> documents;
    ArrayList<String[][]> contents;

    public Controller(){}

    @FXML
    public void initialize(){
        addBtn.setOnMouseClicked(this::ButtonPlusAction);
        deleteBtn.setOnMouseClicked(this::ButtonMinus);
        indexBtn.setOnMouseClicked(this::ButtonIndex);
        searchBtn.setOnMouseClicked(this::ButtonSearch);
        searcher=new Searcher(this);
    }



    public void updateSearchPane(String[] text, String[] names, String[] dates, String [] sizes){
        this.clearSearchPane();

        for(int i=0; i<text.length;i++){
            textPane.getItems().add(text[i]);
            namePane.getItems().add(names[i]);
            datePane.getItems().add(dates[i]);
            sizePane.getItems().add(sizes[i]);
        }


    }

    private void clearSearchPane(){
        textPane.getItems().clear();
        namePane.getItems().clear();
        datePane.getItems().clear();
        sizePane.getItems().clear();
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
                this.documents.addAll(selectedFiles);
            }
        } else {
            AlertBoxes.displayResultAlertBox("Exception", "Invalid file");
        }
    }


    private void ButtonMinus(MouseEvent event)  {
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

    public List<File> getDocuments() {
        return documents;
    }

    public ArrayList<String[][]> getContents() {
        return contents;
    }

    private void ButtonIndex(MouseEvent event){
        contents=new ArrayList<>();
        for(File doc:this.documents){
            // Se agregan los 2d arrays a contents 
        }
    }

    private void ButtonSearch(MouseEvent e){
        searcher.search(inputField.getText());
    }
}
