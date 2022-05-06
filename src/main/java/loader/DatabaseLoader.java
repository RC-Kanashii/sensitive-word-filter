package loader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseLoader{

    public abstract List<String> getTables() throws SQLException;

    public abstract List<String> getColumns(String tableName) throws SQLException;

    public abstract List<String> getContent(String tableName, String columnName) throws SQLException;

}
