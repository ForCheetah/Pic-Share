package test;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlLink {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/whsNet?serverTimezone=UTC&rewriteBatchedStatements=true&useServerPstmts=true","root","123");
            if(!con.isClosed()){
                System.out.println("成功连接");
                con.close();
            }else{

            }
        }catch (Exception e){

        }
    }
}
