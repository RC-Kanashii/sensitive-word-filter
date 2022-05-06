package filter;

import com.alibaba.fastjson.JSONObject;
import toolgood.words.StringSearch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class SensitiveWordFilter {
    private static List<String> sensitiveWords = new ArrayList<>();
    private static StringSearch iwords = new StringSearch();

    // 读取敏感词，生成敏感词库
    static {
        InputStream inputStream = SensitiveWordFilter.class.getClassLoader().getResourceAsStream("sensitiveWords.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        while (true) {
            try {
                if (!((line = bReader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sensitiveWords.add(line);
        }
        iwords.SetKeywords(sensitiveWords);
    }

    public static boolean containsAny(String s) {
        return iwords.ContainsAny(s);
    }

    public static List<String> findAll(String s) {
        return iwords.FindAll(s);
    }

    public static String replace(String s, char c) {
        return iwords.Replace(s, c);
    }

    public static String replace(String s) {
        return iwords.Replace(s);
    }

    public static HashMap<String, List<Integer[]>> findWordIndex(String content) {
        Set<String> sensitiveWords = new HashSet<>(findAll(content));
        HashMap<String, List<Integer[]>> res = new HashMap<>();
        for (String word : sensitiveWords) {
            List<Integer[]> list = new ArrayList<>();
            int first = content.indexOf(word);
            while (first != -1) {
                int last = first + word.length() - 1;
                list.add(new Integer[]{first, last});
                first = content.indexOf(word, first + 1);
            }
            res.put(word, new ArrayList<>(list));
        }
        return res;
    }

    public static void generateJSON(String content, String filePath) throws IOException {
        HashMap<String, List<Integer[]>> map = SensitiveWordFilter.findWordIndex(content);
        Map<String, Object> tmp = new HashMap<>(map);
        JSONObject json = new JSONObject(tmp);
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
//        Files.write(path, json.getBytes("UTF-8"), StandardOpenOption.CREATE);
        Files.write(path, Arrays.asList(json.toJSONString()));
    }
}
