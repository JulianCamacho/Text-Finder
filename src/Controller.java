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

/**
 * Clase controlador que se encarga del manejo general del programa
 */
public class Controller {
    /**
     * Boton de indexar
     */
    @FXML
    Button indexBtn;
    /**
     * Boton de refrescar
     */
    @FXML
    Button refreshBtn;
    /**
     * Boton de agregar
     */
    @FXML
    Button addBtn;
    /**
     * Boton de eliminar
     */
    @FXML
    Button deleteBtn;
    /**
     * Boton de buscar
     */
    @FXML
    Button searchBtn;
    /**
     * Input del usuario
     */
    @FXML
    TextField inputField;
    /**
     * Panel donde se muestra el contexto de la palabra buscada
     */
    @FXML
    ListView textPane;
    /**
     * Panel donde se muestra el nombre del documento de la palabra encontrada
     */
    @FXML
    ListView namePane;
    /**
     * Panel donde se muestra el tamano del documento de la palabra encontrada
     */
    @FXML
    ListView sizePane;
    /**
     * Panel donde se muestra la fecha del documento de la palabra encontrada
     */
    @FXML
    ListView datePane;
    /**
     * Panel donde se muestra el contenido de la libreria
     */
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
    /**
     * Instancia del buscador
     */
    Searcher searcher;
    /**
     * Array de los documentos que estan en el panel de buscar
     */
    File[] documentsOnSearchPane;
    /**
     * Documentos en la libreria
     */
    ArrayList<File> documents;
    /**
     * Contenido de documentos en la librera, en el orden de la lista de documentos de la libreria
     */
    ArrayList<String[][]> contents;

    /**
     * Clase controlador
     */
    public Controller(){}

    /**
     * Metodo principal
     */
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


    /**
     * Metodo para actualizar el panel de palabras encontradas
     * @param documents Documentos de las palabras
     * @param text Contexto de las palabras
     * @param names Nombres de los documentos de las palabras
     * @param dates Fechas de los documentos de las palabras
     * @param sizes Tamano de los documentos de las palabras
     */
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

    /**
     * Metodo para reiniciar el panel de palabras encontradas
     */
    private void clearSearchPane(){
        textPane.getItems().clear();
        namePane.getItems().clear();
        datePane.getItems().clear();
        sizePane.getItems().clear();
    }

    /**
     * Listener del boton de agregar documentos
     * @param event
     */
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

    /**
     * Retorna la lista de documentos
     * @return Lista de documentos
     */
    public List<File> getDocuments() {
        return documents;
    }

    /**
     * Retorna la lista de contenidos
     * @return Lista de contenidos
     */
    public ArrayList<String[][]> getContents() {
        return contents;
    }

    /**
     * Listener del boton de indexar
     * @param event
     */
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

    /**
     * Listener del boton de buscar
     * @param e
     */
    private void ButtonSearch(MouseEvent e){
        searcher.search(inputField.getText());
    }

    /**
     * Listener para eventos de selecion del nombre en la listview de nombres
     * @param e
     */
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
