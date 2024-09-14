import db.DB;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {

        SimpleDateFormat fmt1 = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            /* ADICIONANDO APENAS UM VENDEDOR/SELLER
            st = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                            // placeHolder cada ponto de interrogação
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

                // para adicionar valor ao primeiro interrogação do prepareStatement()
                st.setString(1, "Ayslan Gadelha");
                st.setString(2, "ayslan.gadelha@gmail.com");

                // instanciando uma data no JDBC
                st.setDate(3, new java.sql.Date(fmt1.parse("06/01/2004").getTime()));
                st.setDouble(4, 3000.0);
                st.setInt(5, 4);
                */

                st = conn.prepareStatement(
                        "insert into department (Name) values ('Dev'), ('Ti') ",
                        Statement.RETURN_GENERATED_KEYS
                );

                int rowsAffected = st.executeUpdate();

                if(rowsAffected > 0){

                    ResultSet rs = st.getGeneratedKeys();
                    while(rs.next()){
                        int id = rs.getInt(1);
                        System.out.println("Done! Id = " + id);
                    }

                }
                else{
                    System.out.println("No row affected ! ");
                }

        }catch (SQLException e){
            e.printStackTrace();

        }
        //catch(ParseException e){
          //  e.printStackTrace();
        //  }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();

        }
    }
}