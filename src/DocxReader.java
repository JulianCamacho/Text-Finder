import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DocxReader {

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
