package loader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLLoader extends DatabaseLoader{
    private static MySQLLoader instance = null;

    private static Connection conn = null;

    private MySQLLoader() {};

    public static MySQLLoader getInstance() {
        if (instance == null) { // 延迟加载
            synchronized (MySQLLoader.class) { // 同步锁
                if (instance == null)
                    instance = new MySQLLoader();
            }
        }
        return instance;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String  url  =  "jdbc:mysql://localhost:3306/test1?useSSL=false";
            String  user  =  "root";
            String  pass  =  "123456abc";
            conn  =  DriverManager.getConnection(url,  user,  pass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getTables() throws SQLException {
        String sql = "SHOW TABLE STATUS";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<String> tables = new ArrayList<>();
        while (rs.next()) {
            String tname =rs.getString("Name");
            tables.add(tname);
        }
        rs.close();
        return tables;
    }

    @Override
    public List<String> getColumns(String tableName) throws SQLException {
        String sql = "select * from " + tableName;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();
        int columeCount = meta.getColumnCount();

        List<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= columeCount; i++) {
            if (meta.getColumnType(i) == Types.VARCHAR) {
                columnNames.add(meta.getColumnName(i));
            }
        }
        return columnNames;
    }

    @Override
    public List<String> getContent(String tableName, String columnName) throws SQLException {
        String sql = String.format("select %s from %s", columnName, tableName);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<String> res = new ArrayList<>();
        while (rs.next()) {
            res.add(rs.getString(1));
        }
        rs.close();
        return res;
    }
}
