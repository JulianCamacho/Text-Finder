import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
/**
 * Esta clase extrae el teto de documentos pdf
 */
public class PdfReader {
    /**
     * Este metodo extrae el texto de archivos pdf
     * @param path La ruta del archivo
     * @return Array de las lineas de texto
     */
    public static String[] pdfReader (String path) throws IOException {
        PDDocument document = PDDocument.load(new File(path));
        if (!document.isEncrypted()) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            PDFTextStripper tStripper = new PDFTextStripper();
            String pdfFileInText = tStripper.getText(document);
            String[] lines = pdfFileInText.split("\\r?\\n");
            return lines;
        }
        return null;
    }
}
