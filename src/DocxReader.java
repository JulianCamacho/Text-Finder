import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Esta clase extrae el teto de documentos docx
 */
public class DocxReader {
    /**
     * Este metodo extrae el texto de archivos docx
     * @param path La ruta del archivo
     * @return Array de las lineas de texto
     */
    public static String[] docxReader(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());

        XWPFDocument document = new XWPFDocument(fis);
        XWPFWordExtractor extractor= new XWPFWordExtractor(document);
        String[] lines = extractor.getText().split("\n");

        fis.close();
        return lines;

    }
}
