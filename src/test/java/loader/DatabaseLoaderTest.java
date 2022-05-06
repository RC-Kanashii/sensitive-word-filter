package loader;

import filter.SensitiveWordFilter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseLoaderTest {

    @Test
    public void getTablesTest() throws SQLException {
        DatabaseLoader loader = MySQLLoader.getInstance();
        List<String> tables = loader.getTables();
        System.out.println(tables);
    }

    @Test
    public void getColumnsTest() throws SQLException {
        DatabaseLoader loader = MySQLLoader.getInstance();
        List<String> tables = loader.getTables();
        for (String table : tables) {
            System.out.println(loader.getColumns(table));
        }
    }

    @Test
    public void getContentTest() throws IOException, SQLException {
        DatabaseLoader loader = MySQLLoader.getInstance();

        for (String table : loader.getTables()) {
            for (String column : loader.getColumns(table)) {
                for (String s : loader.getContent(table, column)) {
                    System.out.println("当前内容为：" + s);
                    System.out.println("是否包含敏感词：" + SensitiveWordFilter.containsAny(s));
                    System.out.println("包含的敏感词："    + SensitiveWordFilter.findAll(s));
                    System.out.println("替换后：" + SensitiveWordFilter.replace(s));
                }
            }
        }
    }
}