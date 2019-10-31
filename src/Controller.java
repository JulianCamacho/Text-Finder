import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
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

    /**
     * Tabla donde se muestran los resultados
     */
    @FXML
    TableView<Documents> resultsTable;

    /**
     * Columnas de la tabla de resultados
     */
    @FXML
    TableColumn textColumn;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn sizeColumn;
    @FXML
    TableColumn dateColumn;

    /**
     * Botones para ordenamiento
     */

    @FXML
    Button textUpBtn;
    @FXML
    Button textDownBtn;
    @FXML
    Button nameUpBtn;
    @FXML
    Button nameDownBtn;
    @FXML
    Button sizeUpBtn;
    @FXML
    Button sizeDownBtn;
    @FXML
    Button dateUpBtn;
    @FXML
    Button dateDownBtn;

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

    DocumentsDoublyLinkedList dl = new DocumentsDoublyLinkedList();

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
        resultsTable.setOnMouseClicked(this::ListViewClic);
        refreshBtn.setOnMouseClicked(this::ButtonRefreshAction);

        textUpBtn.setOnMouseClicked(this::buttonTextUp);
        textDownBtn.setOnMouseClicked(this::buttonTextDown);

        nameUpBtn.setOnMouseClicked(this::buttonNameUp);
        nameDownBtn.setOnMouseClicked(this::buttonNameDown);

        dateUpBtn.setOnMouseClicked(this::buttonDateUp);
        dateDownBtn.setOnMouseClicked(this::buttonDateDown);

        sizeUpBtn.setOnMouseClicked(this::buttonSizeUp);
        sizeDownBtn.setOnMouseClicked(this::buttonSizeDown);

        searcher=new Searcher(this);
        inputField.setPromptText("Insert a word or phrase");

        textColumn.setResizable(false);
        nameColumn.setResizable(false);
        sizeColumn.setResizable(false);
        dateColumn.setResizable(false);
    }


    /**
     * Metodo para actualizar el panel de palabras encontradas
     * @param documents Documentos de las palabras
     * @param text Contexto de las palabras
     * @param names Nombres de los documentos de las palabras
     * @param dates Fechas de los documentos de las palabras
     * @param sizes Tamano de los documentos de las palabras
     */
    public void updateSearchPane(ArrayList<File> documents,String[] text, String[] names, String[] dates, String [] sizes) {
        this.clearSearchPane();
        this.documentsOnSearchPane=documents.toArray(new File[documents.size()]);
        for(int i=0; i<text.length;i++){
            dl.addLast(new Documents(documents.get(i).getAbsolutePath(), text[i], names[i], sizes[i], dates[i].substring(0, 10)));
            this.updateResultTable();
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
        DirectoryChooser dc = new DirectoryChooser();
        try{
            File selectedDirectory = new File(dc.showDialog(null).getAbsolutePath());
            File[] subDir = selectedDirectory.listFiles();
            libraryListView.getItems().add(selectedDirectory.getName());
            for (int i = 0; i < subDir.length; i++) {
                if (getFileExtension(subDir[i]).equals("pdf") || getFileExtension(subDir[i]).equals("docx") || getFileExtension(subDir[i]).equals("txt")){
                    /*System.out.println(documents.size());
                    if (this.documents.size() != 0){
                        for (int h = 0; h < this.documents.size(); h++) {
                            System.out.println(subDir[i].getName());
                            System.out.println(documents.get(h).getName());
                            if (!subDir[i].getName().equals(this.documents.get(h).getName())){
                                libraryListView.getItems().add(subDir[i].getName());
                                this.documents.add(subDir[i]);
                            }
                        }
                    } else{*/
                        libraryListView.getItems().add(subDir[i].getName());
                        this.documents.add(subDir[i]);
                    //}
                }
            }
        } catch (NullPointerException e){
            AlertBoxes.displayAlertBox("Exception", "No directory selected");
        }

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf files", "*.pdf"),
                new FileChooser.ExtensionFilter("docx files", "*.docx"),
                new FileChooser.ExtensionFilter("txt files", "*.txt"));
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        if (selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); i++){
                /*if (this.documents.size() != 0){
                    for (int h = 0; h < this.documents.size(); h++) {
                        if (!selectedFiles.get(i).getName().equals(this.documents.get(h).getName())){
                            libraryListView.getItems().add(selectedFiles.get(i).getName());
                            this.documents.addAll(selectedFiles);
                        }
                    }
                } else{*/
                    libraryListView.getItems().add(selectedFiles.get(i).getName());
                    this.documents.addAll(selectedFiles);
                //}
            }
        } else {
            AlertBoxes.displayResultAlertBox("Exception", "No file selected or invalid file");
        }
    }


    private void ButtonMinus(MouseEvent event) {
        int index = libraryListView.getSelectionModel().getSelectedIndex();
        if (index >= 0){
            libraryListView.getItems().remove(index);
            this.documents.remove(index);
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
     * Listener para eventos de seleccion del nombre en la listview de nombres
     * @param e
     */
    private void ListViewClic(MouseEvent e) {
        Documents current = resultsTable.getSelectionModel().getSelectedItem();
        try {
            Desktop.getDesktop().open(current.getFile());
            System.out.println(current.getFile());
            System.out.println(current.getAbsPath());
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
            AlertBoxes.displayAlertBox("Error", "File not found");
        }
    }

    public ObservableList<Documents> getIndexedDocuments(DocumentsDoublyLinkedList dl){
        ObservableList<Documents> files = FXCollections.observableArrayList();
        for(int i = 0; i < dl.getLength(); i++){
            files.add(dl.get(i));
        }
        return files;
    }

    private void updateResultTable(){
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        textColumn.setSortable(false);
        nameColumn.setSortable(false);
        sizeColumn.setSortable(false);
        dateColumn.setSortable(false);

        resultsTable.setItems(getIndexedDocuments(dl));

    }

    private void buttonTextUp(MouseEvent e){
        resultsTable.getItems().clear();
        QuickSort.textQuickSort(dl, 0, dl.getLength()-1);
        this.updateResultTable();
    }

    private void buttonTextDown(MouseEvent e){
        resultsTable.getItems().clear();
        QuickSort.textQuickSort(dl, 0, dl.getLength()-1);
        dl.reverseList();
        this.updateResultTable();
    }

    private void buttonNameUp(MouseEvent e){
        resultsTable.getItems().clear();
        QuickSort.quickSort(dl, 0, dl.getLength()-1);
        this.updateResultTable();
    }

    private void buttonNameDown(MouseEvent e){
        resultsTable.getItems().clear();
        QuickSort.quickSort(dl, 0, dl.getLength()-1);
        dl.reverseList();
        this.updateResultTable();
    }

    private void buttonDateUp(MouseEvent e){
        resultsTable.getItems().clear();
        BubbleSort.bubbleSort(dl);
        this.updateResultTable();
    }

    private void buttonDateDown(MouseEvent e) {
        resultsTable.getItems().clear();
        BubbleSort.bubbleSort(dl);
        dl.reverseList();
        this.updateResultTable();
    }

    private void buttonSizeUp(MouseEvent e){
        if (dl.isEmpty()){
            AlertBoxes.displayAlertBox("Empty", "Empty library");
        } else{
            resultsTable.getItems().clear();
            dl.printList();
            RadixSort.myRadixsort(dl, dl.getLength()-1);
            dl.printList();
            this.updateResultTable();
        }
    }

    private void buttonSizeDown(MouseEvent e) {
        if (dl.isEmpty()){
            AlertBoxes.displayAlertBox("Empty", "Empty library");
        } else{
            resultsTable.getItems().clear();
            dl.printList();
            RadixSort.myRadixsort(dl, dl.getLength()-1);
            dl.reverseList();
            dl.printList();
            this.updateResultTable();
        }
    }

    /**
     * Listener del boton de refrescar la libreria
     * @param event
     */
    public void ButtonRefreshAction(MouseEvent event){
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
     * Metodo para obtener la extension de un archivo
     * @param file Archivo
     * @return Extension del archivo
     */
    private static String getFileExtension(File file){
        int extensionStart = file.getName().lastIndexOf(".");
        return file.getName().substring(extensionStart+1);
    }
}
