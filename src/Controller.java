import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
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
    @FXML
    Button addFilesBtn;
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
        addFilesBtn.setOnMouseClicked(this::addFilesBtnAction);
        deleteBtn.setOnMouseClicked(this::ButtonMinus);
        indexBtn.setOnMouseClicked(this::ButtonIndex);
        searchBtn.setOnMouseClicked(this::ButtonSearch);
        resultsTable.setOnMouseClicked(this::ListViewClic);
        refreshBtn.setOnMouseClicked(this::ButtonRefreshAction);

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

        Image img1 = new Image("imgs/dir.png");
        ImageView iv1 = new ImageView(img1);
        iv1.setFitWidth(23);
        iv1.setFitHeight(23);
        addBtn.setGraphic(iv1);

        Image img2 = new Image("imgs/document icon.png");
        ImageView iv2 = new ImageView(img2);
        iv2.setFitWidth(20);
        iv2.setFitHeight(20);
        addFilesBtn.setGraphic(iv2);
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
        this.documentsOnSearchPane = documents.toArray(new File[documents.size()]);
        for(int i=0; i<text.length;i++){
            dl.addLast(new Documents(documents.get(i).getAbsolutePath(), text[i], lowerCase(names[i]), sizes[i], dates[i].substring(0, 10), inputField.getText()));
            this.updateResultTable();
        }
    }

    /**
     * Metodo para reiniciar el panel de palabras encontradas
     */
    private void clearSearchPane(){
        resultsTable.getItems().clear();
        dl.clearList();
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
            Text dirName = new Text(selectedDirectory.getName());
            dirName.setFill(Color.DARKGOLDENROD);
            dirName.setUnderline(true);
            libraryListView.getItems().add(dirName);
            for (int i = 0; i < subDir.length; i++) {
                if (getFileExtension(subDir[i]).equals("pdf") || getFileExtension(subDir[i]).equals("docx") || getFileExtension(subDir[i]).equals("txt")){
                    libraryListView.getItems().add(subDir[i].getName());
                    this.documents.add(subDir[i]);
                }
            }
        } catch (NullPointerException e){
            AlertBoxes.displayResultAlertBox("Exception", "No directory selected");
        }
    }

    public void addFilesBtnAction(MouseEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf files", "*.pdf"),
                new FileChooser.ExtensionFilter("docx files", "*.docx"),
                new FileChooser.ExtensionFilter("txt files", "*.txt"));
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        if (selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); i++){
                libraryListView.getItems().add(selectedFiles.get(i).getName());
                this.documents.add(selectedFiles.get(i));
            }
        } else {
            AlertBoxes.displayResultAlertBox("Exception", "No file selected or invalid file");
        }
    }

    /**
     * Boton para eliminar archivos de la biblioteca
     * @param event
     */
    private void ButtonMinus(MouseEvent event) {
        try{
            int index = libraryListView.getSelectionModel().getSelectedIndex();
            if (index >= 0){
                libraryListView.getItems().remove(index);
                System.out.println(this.documents.remove(index).getName());
                this.documents.remove(index);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e){
            AlertBoxes.displayAlertBox("Exception", "Unable to delete the selected file");
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
        this.clearSearchPane();
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

    /**
     * Metodo para agregar elementos a la lista de resultados
     * @param dl lista de Documentos
     * @return
     */
    public ObservableList<Documents> getIndexedDocuments(DocumentsDoublyLinkedList dl){
        ObservableList<Documents> files = FXCollections.observableArrayList();
        for(int i = 0; i < dl.getLength(); i++){
            files.add(dl.get(i));
        }
        return files;
    }

    /**
     * Metodo de configuracion de la lista de resultados
     */
    private void updateResultTable(){
        textColumn.setCellValueFactory(new PropertyValueFactory<>("textFlow"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        textColumn.setSortable(false);
        nameColumn.setSortable(false);
        sizeColumn.setSortable(false);
        dateColumn.setSortable(false);

        resultsTable.setItems(getIndexedDocuments(dl));

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

    /**
     * Metodo para quitar las letras mayúsculas de un texto
     * @param cadena texto a modificar
     * @return texto modificado
     */
    public static String lowerCase(String cadena) {
        String limpio =null;
        if (cadena !=null) {
            String valor = cadena;
            valor = valor.toLowerCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }
}
