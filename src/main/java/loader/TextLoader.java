package loader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextLoader extends FileLoader {
    private static TextLoader instance = null;

    private TextLoader() {};  // 单例模式

    public static TextLoader getInstance() {
        if (instance == null) { // 延迟加载
            synchronized (TextLoader.class) { // 同步锁
                if (instance == null)
                    instance = new TextLoader();
            }
        }
        return instance;
    }
    @Override
    public List<String> load(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        String res = Files.readString(path);
        System.out.println(res);
        return null;
//        InputStream inputStream = TextLoader.class.getClassLoader().getResourceAsStream(filePath);
//        Reader reader = new InputStreamReader(inputStream);
//        BufferedReader bReader = new BufferedReader(reader);
//
//        List<String> res = new ArrayList<>();
//        String line;
//        while ((line = bReader.readLine()) != null) {
//            res.add(line);
//        }
//        return res;
    }
}
