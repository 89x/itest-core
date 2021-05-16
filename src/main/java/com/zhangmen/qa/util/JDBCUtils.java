package com.zhangmen.qa.util;

import com.zhangmen.qa.common.Utils;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/***
 * 数据库查询类
 * barry
 */

public  class JDBCUtils {

    private static Properties properties = new Properties();
    static {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/jdbc.properties"));
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
     * 查询工具类，只能单个查询sql，多数据源，传入需要用到的数据 库
     * @param sql
     * @param values
     * @throws SQLException
     * @throws IOException
     */

    public  static Map<String,Object> query(String datasoures, String sql , Object ... values) {
        Map<String,Object> ColumnLabelAndValues =new HashMap();

        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getConnection(datasoures).prepareStatement(sql);

            //传递参数
            for (int i = 0;i<values.length;i++) {
                preparedStatement.setObject(i+1,values[i]);
            }
            //执行sql
            Utils.info("执行sql======="+preparedStatement);
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
        Utils.info("执行sql结果 Map"+ColumnLabelAndValues);
        return ColumnLabelAndValues;

    }
    /**
     * 查询工具类，只能单个查询sql，查询forge 主表
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
            Utils.info("执行sql======="+preparedStatement);
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
        Utils.info("执行sql结果 Map"+ColumnLabelAndValues);
        return ColumnLabelAndValues;

    }

    /***
     * 增删改,多数据源
     * @param sql
     * @param values
     * @return
     */

    public  static int executeUpdate(String datasoures,String sql , Object ... values) {
        int num = 0;

        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getConnection(datasoures).prepareStatement(sql);

            //传递参数
            for (int i = 0;i<values.length;i++) {
                preparedStatement.setObject(i+1,values[i]);
            }
            Utils.info("执行sql======="+preparedStatement);

            //执行sql
            num  =  preparedStatement.executeUpdate();
            Utils.info("执行成功行数======="+num);




            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }
    /***
     * 增删改，查询
     * @param sql
     * @param values
     * @return
     */

    public  static int executeUpdate(String sql , Object ... values) {
        int num = 0;

        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getConnection().prepareStatement(sql);

            //传递参数
            for (int i = 0;i<values.length;i++) {
                preparedStatement.setObject(i+1,values[i]);
            }
            Utils.info("执行sql======="+preparedStatement);

            //执行sql
            num  =  preparedStatement.executeUpdate();
            Utils.info("执行成功行数======="+num);




            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }


    /**
     * 获取数据库链接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String datasoures) throws SQLException {
        String url = properties.getProperty("jdbc."+datasoures);
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url,user,password);
        Utils.info("链接数据库成功");
        return connection;
    }
    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("jdbc.forge");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url,user,password);
        Utils.info("链接数据库成功");
        return connection;
    }

}
