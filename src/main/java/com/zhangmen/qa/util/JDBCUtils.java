package com.zhangmen.qa.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public  class JDBCUtils {
    private static Logger logger = Logger.getLogger(JDBCUtils.class);

    private static Properties properties = new Properties();
    static {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/config/jdbc.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 查询工具类，只能单个查询sql
     * @param sql
     * @param values
     * @throws SQLException
     * @throws IOException
     */

    public  static Map<String,Object> query(String sql , Object ... values) {
        Map<String,Object> ColumnLabelAndValues =new HashMap();

        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getConnection().prepareStatement(sql);

            //传递参数
            for (int i = 0;i<values.length;i++) {
                preparedStatement.setObject(i+1,values[i]);
            }
            //执行sql
            ResultSet resultSet =  preparedStatement.executeQuery();

            ResultSetMetaData mateData =resultSet.getMetaData();
            int cloumnCount= mateData.getColumnCount();

            //结果获取数据
            while (resultSet.next()){
                for (int i = 1; i <= cloumnCount; i++) {
                    String ColumnLabel =mateData.getColumnLabel(i);
                    String cloumnValue =  resultSet.getObject(ColumnLabel).toString();
                    ColumnLabelAndValues.put(ColumnLabel,cloumnValue);
                }
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ColumnLabelAndValues;

    }

    /**
     * 获取数据库链接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url,user,password);
        logger.info("链接数据库成功");
        return connection;
    }

}
