package loader;

import java.io.IOException;
import java.util.List;

public abstract class FileLoader {
    public abstract List<String> load(String fileName) throws IOException;  // 读取数据
}
