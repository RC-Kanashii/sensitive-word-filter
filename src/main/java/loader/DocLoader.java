package loader;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DocLoader extends FileLoader {
    private static DocLoader instance = null;

    private DocLoader() {};

    public static DocLoader getInstance() {
        if (instance == null) { // 延迟加载
            synchronized (DocLoader.class) { // 同步锁
                if (instance == null)
                    instance = new DocLoader();
            }
        }
        return instance;
    }

    @Override
    public List<String> load(String fileName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        HWPFDocument doc = new HWPFDocument(inputStream);
        WordExtractor extractor = new WordExtractor(doc);
        List<String> res = List.of(extractor.getParagraphText());
        return res;
    }
}
