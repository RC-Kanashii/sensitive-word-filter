package loader;

import com.alibaba.fastjson.JSONObject;
import filter.SensitiveWordFilter;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TextLoaderTest {
    @Test
    public void loadTest() throws IOException {
        FileLoader textLoader = TextLoader.getInstance();
        var list = textLoader.load("D:\\JetBrainsProjects\\IdeaProjects\\SensitiveWordFilter\\src\\test\\resources\\file.txt");
//        System.out.println("list = " + list);
//
//        for (String s : list) {
//            System.out.println("当前内容为：" + s);
//            System.out.println("是否包含敏感词：" + SensitiveWordFilter.containsAny(s));
//            System.out.println("包含的敏感词："    + SensitiveWordFilter.findAll(s));
//            System.out.println("替换后：" + SensitiveWordFilter.replace(s));
//        }
    }

    @Test
    public void readDocTest() throws IOException {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("docTest.doc");
        HWPFDocument doc = new HWPFDocument(fis);
        WordExtractor extractor = new WordExtractor(doc);
        List<String> str = List.of(extractor.getParagraphText());
//        String str = doc.getText().toString();
//        StringBuilder doc2 = doc.getText();
        for (var s : str) {
            System.out.println(s);
        }
        doc.close();
        fis.close();
//        System.out.println(doc2);
    }

    @Test
    public void docLoaderTest() throws IOException {
        FileLoader loader = DocLoader.getInstance();
        var list = loader.load("docTest.doc");
        System.out.println("list = " + list);

        for (String s : list) {
            System.out.println("当前内容为：" + s);
            System.out.println("是否包含敏感词：" + SensitiveWordFilter.containsAny(s));
            System.out.println("包含的敏感词："    + SensitiveWordFilter.findAll(s));
            System.out.println("替换后：" + SensitiveWordFilter.replace(s));
        }
    }

    @Test
    public void docxLoaderTest() throws IOException {
        FileLoader loader = DocxLoader.getInstance();
        var list = loader.load("docxTest.docx");
        System.out.println("list = " + list);

        for (String s : list) {
            System.out.println("当前内容为：" + s);
            System.out.println("是否包含敏感词：" + SensitiveWordFilter.containsAny(s));
            System.out.println("包含的敏感词："    + SensitiveWordFilter.findAll(s));
            System.out.println("替换后：" + SensitiveWordFilter.replace(s));
        }
    }

    @Test
    public void findWordIndexTest() {
        HashMap<String, List<Integer[]>> res = SensitiveWordFilter.findWordIndex("sbdvf123代表123人不你好会放asdf563123b65bv你好v3");
        Map<String, Object> res2 = new HashMap<>(res);
        for (String key : res.keySet()) {
            System.out.println("key = " + key);
            List<Integer[]> list = res.get(key);
            for (var pair : list) {
                System.out.println("pair = " + Arrays.toString(pair));
            }
        }
//        JSONObject json = new JSONObject(new HashMap<String, Object>());
        JSONObject json = new JSONObject(res2);
        System.out.println(json.toJSONString());
    }

    @Test
    public void generateJSONTest() throws IOException {
        SensitiveWordFilter.generateJSON("sbdvf123代表123人不你好会放asdf563123b65bv你好v3",
                "D:\\JetBrainsProjects\\IdeaProjects\\SensitiveWordFilter\\src\\test\\resources\\test.json");
    }
}