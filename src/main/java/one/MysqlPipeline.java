package one;

import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */

public class MysqlPipeline implements Pipeline {


    private static String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/me?useUnicode=true&characterEncoding=utf8";
    private static String USERNAME = "root" ;
    private static String PASSWORD = "123456" ;
    private static Connection connection = null;


    static {
        String driver = "com.mysql.jdbc.Driver" ;
        try{
            Class.forName(driver);

            connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
        }catch (Exception e){
            System.out.println("1");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void process(ResultItems var1, Task var2){
        //
            for(Map.Entry<String,?> entry : var1.getAll().entrySet()){
            //
            try {
                Beauty beauty = (Beauty)entry.getValue();

                String s = "insert into beauty(name,pictureName,url) values(%s,%s,%s)";
                String sql = "insert into beauty(name,pictureName,url) values('" + beauty.getName() + "','" + beauty.getPictureName() + "','" + beauty.getUrl() + "')";
                //String sql = String.format(s, beauty.getName(),beauty.getPictureName(),beauty.getUrl());
                System.out.println(sql);
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("2");
                System.out.println(e.getMessage());
            }
        }
    }

    public void sqlClose(){

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("3");
            System.out.println(e.getMessage());
        }
    }


    public static void main(String [] args){
        String s = "insert into beauty(name,pictureName,url) values('haha','beautyleg-1346-0028.jpg','http://www.beautylegmm.com/photo/beautyleg/2016/1346/beautyleg-1346-0027.jpg')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(s);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage() + "||" + e.getCause());
        }

    }
}
