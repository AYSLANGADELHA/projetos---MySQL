import db.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {


        //para conectar o banco
        Connection conn = null;

        //preparar uma consulta SQL para buscar todos os departamentos do banco de dados
        Statement st = null;

        //guardar o resultado da consulta Statement
        ResultSet rs = null;


        try{
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from department");

            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }


        }catch(SQLException e){

            e.printStackTrace();
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}