package loader;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DocxLoader extends FileLoader {
    private static DocxLoader instance = null;

    private DocxLoader() {};

    public static DocxLoader getInstance() {
        if (instance == null) { // 延迟加载
            synchronized (DocxLoader.class) { // 同步锁
                if (instance == null)
                    instance = new DocxLoader();
            }
        }
        return instance;
    }

    @Override
    public List<String> load(String fileName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        XWPFDocument docx = new XWPFDocument(inputStream);
//        XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
        List<XWPFParagraph> paragraphs = docx.getParagraphs();
        List<String> res = new ArrayList<>();
        for (XWPFParagraph paragraph : paragraphs) {
            res.add(paragraph.getText());
        }
        return res;
    }
}
