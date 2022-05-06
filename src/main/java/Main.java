import filter.SensitiveWordFilter;
import loader.TextLoader;

public class Main {
    public static void main(String[] args) throws Exception {
//        String test = "我是中国人";
//        List<String> list=new ArrayList<String>();
//        list.add("中国");
//        list.add("国人");
//        list.add("zg人");
//        System.out.println("StringSearch run Test.");
//
//        StringSearch iwords = new StringSearch();
//        iwords.SetKeywords(list);
//
//        boolean b = iwords.ContainsAny(test);
//        System.out.println("b = " + b);
//        if(b==false){
//            System.out.println("ContainsAny is Error.");
//        }
//
//        String f = iwords.FindFirst(test);
//        System.out.println("f = " + f);
//        if(f!="中国"){
//            System.out.println("FindFirst is Error.");
//        }
//
//        List<String> all = iwords.FindAll(test);
//        System.out.println("all = " + all);
//        if(all.get(0)!="中国"){
//            System.out.println("FindAll is Error.");
//        }
//        if(all.get(1)!="国人"){
//            System.out.println("FindAll is Error.");
//        }
//        if(all.size()!=2){
//            System.out.println("FindAll is Error.");
//        }
//
//        String str = iwords.Replace(test, '*');
//        System.out.println("str = " + str);
//        if(str.equals("我是***")==false ){
//            System.out.println("Replace is Error.");
//        }

//        InputStream inputStream = Main.class.getResourceAsStream("sensitiveWords.txt");
//        Reader reader = new InputStreamReader(inputStream);
//        BufferedReader bReader = new BufferedReader(reader);
//        String line;
//        while ((line = bReader.readLine()) != null) {
//            System.out.println(line);
//        }
        var textLoader = TextLoader.getInstance();
        var list = textLoader.load("file.txt");
        System.out.println("list = " + list);
        System.out.println(SensitiveWordFilter.containsAny("11123"));
        System.out.println(SensitiveWordFilter.findAll("11123456789fbdaskbkjasdfjf"));
    }
}
