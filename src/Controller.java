import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.*;
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

    File[] documentsOnSearchPane;
    ArrayList<File> documents;
    ArrayList<String[][]> contents;

    public Controller(){}

    @FXML
    public void initialize(){
        documents = new ArrayList<>();
        addBtn.setOnMouseClicked(this::ButtonPlusAction);
        deleteBtn.setOnMouseClicked(this::ButtonMinus);
        indexBtn.setOnMouseClicked(this::ButtonIndex);
        searchBtn.setOnMouseClicked(this::ButtonSearch);
        namePane.setOnMouseClicked(this::ListViewClic);
        searcher=new Searcher(this);
    }



    public void updateSearchPane(ArrayList<File> documents,String[] text, String[] names, String[] dates, String [] sizes){
        this.clearSearchPane();
        this.documentsOnSearchPane=documents.toArray(new File[documents.size()]);
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
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf files", "*.pdf"),
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
        try {
            DocxReader.docxReader("C:\\Users\\toshiba\\Documents\\!A -- TEC -- II Semestre -- 2019\\Algoritmos y Estructuras de Datos I\\Archivos\\I.docx");
        } catch (IOException e) {
            e.printStackTrace();
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
        Tree tree= Tree.getInstance();
        tree.clear();
        for(File doc:this.documents){
            try {
                contents.add(ParserFacade.parse(doc));
            } catch ( IOException e) {
                AlertBoxes.displayAlertBox("Error", "An error has ocurred while reading "+doc.getName());
                String[][] result={{""}};
                contents.add(result);
            }
        }
    }

    private void ButtonSearch(MouseEvent e){
        searcher.search(inputField.getText());
    }

    private void ListViewClic(MouseEvent e) {
        int index =namePane.getSelectionModel().getSelectedIndex();
        try {
            Desktop.getDesktop().open(documentsOnSearchPane[index]);
        } catch (IOException ex) {
            AlertBoxes.displayAlertBox("Error", "File not found");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException error){

        }
    }

}
