package dbTest;

import com.example.ServletProject.model.dao.DBManager;
import com.example.ServletProject.model.entity.Fields;
import org.junit.jupiter.api.Test;
import org.apache.ibatis.jdbc.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManagerTest {

    @Test
    public void connectionTest(){
        DBManager dbManager = DBManager.getInstance();

        try {
            Connection con = DBManager.getInstance().getConnection();
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader("./sql/db_create.sql"));
            //Running the script
            sr.runScript(reader);
        } catch (SQLException | FileNotFoundException e){
            e.printStackTrace();
        }

        try {
            Connection con = dbManager.getConnection();
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs = stmt.executeQuery("SELECT * from users");
            System.out.println("Tables in the current database: ");
            while(rs.next()) {
                System.out.print(rs.getString(Fields.USER__EMAIL));
                System.out.println();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
