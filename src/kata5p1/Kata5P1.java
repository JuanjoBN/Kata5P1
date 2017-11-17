package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Kata5P1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        Class.forName("org.sqlite.JDBC");
        Connection con= DriverManager.getConnection("jdbc:sqlite:KATA5.db");
        
        Statement st= con.createStatement();
        
        String query= "SELECT * FROM People";
        ResultSet rs= st.executeQuery(query);
        
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
        query= "CREATE TABLE IF NOT EXISTS MAIL ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'mail' TEXT NOT NULL)";
        
        st.execute(query);
        
        String mail= "r@gmail.com";
        query= "INSERT INTO MAIL (mail) VALUES ('"+ mail +"')";
        st.executeUpdate(query);
        
        
        //Bucle de actualización de datos...//
        String fileString= "emailsfile.txt";
        BufferedReader reader= new BufferedReader(new FileReader(new File(fileString)));
        
        while((mail=reader.readLine()) != null){
            if(!mail.contains("@")) continue;
            query= "INSERT INTO MAIL2 (mail) VALUES ('" + mail +"')";
            st.executeUpdate(query);
        }
        
        rs.close();
        st.close();
        con.close();
    }
}
