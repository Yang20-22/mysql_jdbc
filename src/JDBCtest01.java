import java.sql.*;
import java.util.ResourceBundle;

public class JDBCtest01 {
    public static void main(String[] args) {
        Connection con = null;
        Statement state = null;
        //使用资源绑定器绑定配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        //1、注册驱动
        try {
            Class.forName(driver);
        //2、建立连接
            con = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接对象"+con);
        //3、获取数据库操作对象(statement专门执行sql语句)
            state = con.createStatement();
        //4、执行操作
            String sql = "insert into user(uname,upass) values('456','1234')";
            int count = state.executeUpdate(sql);
            System.out.println(count==1?"添加成功":"添加失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            if(state != null){
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
