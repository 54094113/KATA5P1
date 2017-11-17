
package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KATA5P1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\usuario\\Desktop\\KATA5.DB");
        
        Statement st = con.createStatement();
        
        String query = "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        /*while (rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
        }*/
        
        query = "CREATE TABLE IF NOT EXISTS MAIL('id' INTEGER"
                + " PRIMARY KEY AUTOINCREMENT , 'Mail' TEXT NOT NULL);";
        
        st.execute(query);
        
        String filename = "C:\\Users\\usuario\\Desktop\\emails.txt";
        
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(new File(filename)));
        String mail;
        
        while((mail = reader.readLine()) != null){
            if(!mail.contains("@")){
                continue;
            }
            query = "INSERT INTO MAIL (Mail) VALUES ('" + mail + "');";
            st.executeUpdate(query);
        }
        
        rs.close();
        st.close();
        con.close();
    }
}
