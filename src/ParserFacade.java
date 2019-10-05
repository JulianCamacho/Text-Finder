import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta clase obtiene el contenido de un documento
 */
public class ParserFacade {
    /**
     * Metodo para obtener el contenido de un documento
     * @param file Archivo donde se extraera la informacion
     * @return Array bidimensional con el contenido del documento
     * @throws IOException
     */
    public static String[][] parse(File file) throws  IOException {
        String extension = getExtension(file);
        String[] lines;
        String[][] content;
        switch (extension){
            case "pdf":
                lines=PdfReader.pdfReader(file.getPath());
                content= toBidemensionalArray(lines);
                updateTree(file,content);
                return content;
            case "txt":
                lines=TxtReader.txtReader(file.getPath());
                content= toBidemensionalArray(lines);
                updateTree(file,content);
                return content;
            case "docx":
                lines=DocxReader.docxReader(file.getPath());
                content= toBidemensionalArray(lines);
                updateTree(file,content);
                return content;
            default:
                return null;
        }

    }

    /**
     * Metodo para convertir una lista de lineas en un array bidimensional de palabras
     * @param lines Lista de lineas
     * @return Array bidimensional de palabras
     */
    private static String[][] toBidemensionalArray(String[] lines){
        String[][] content = new String[lines.length][1];
        for(int i =0;i<lines.length;i++) {
            String phrase= lines[i];
            String[] split = phrase.split(" ");
            ArrayList<String> noSpacesSplit = new ArrayList<>();

            for (String word : split) {
                word=word.replaceAll("\n","");
                if (!word.equals("")) {
                    noSpacesSplit.add(word);
                }
            }

            split = noSpacesSplit.toArray(split);
            content[i]=split;
        }

        return content;
    }

    /**
     * Metodo para agrager el contenido del documento al arbol
     * @param doc Documento
     * @param content Contenido del documento
     */
    private static void updateTree(File doc, String[][] content){
        Tree tree= Tree.getInstance();
        for(int i = 0; i< content.length; i++){
            for(int j = 0; j< content[i].length; j++) {
                if (content[i][j] != null) {
                    System.out.print(content[i][j]+" ");
                    tree.add(content[i][j], doc, i, j);
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Metodo para obtener la extension de un archivo
     * @param file Archivo
     * @return Extension del archivo
     */
    private static String getExtension(File file){
        int extensionStart = file.getName().lastIndexOf(".");
        return file.getName().substring(extensionStart+1);
    }
}
