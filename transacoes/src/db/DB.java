package db;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Statement;


import java.util.Properties;

public class DB {

    // objeto de conexão  com o banco de dados no jdbc
    private static Connection coon = null;

    //metodo para conectar o banco de dados
    public static Connection getConnection(){
        if(coon == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                coon = DriverManager.getConnection(url, props);
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return coon;
    }

    // metodo para fechar conexão com o Banco de Dados
    public static void closeConnection(){
        if(coon != null){
            try {
                coon.close();
            }
            catch (SQLException e ){
                throw new DbException(e.getMessage());
            }
        }
    }

    // metodo para carregar e tratar o banco de dados
    private static Properties loadProperties(){

        try(FileInputStream fs = new FileInputStream("db.properties")){

            Properties props = new Properties();
            props.load(fs);
            return props;

        }
        catch (IOException e){

            throw new DbException(e.getMessage());
        }


    }

    public static void closeStatement(Statement st){
        if(st != null){
            try{

                st.close();

            }catch(SQLException e){

                throw new DbException(e.getMessage());

            }

        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{

                rs.close();

            }catch(SQLException e){

                throw new DbException(e.getMessage());

            }

        }
    }

}