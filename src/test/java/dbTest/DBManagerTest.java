package dbTest;

import com.example.ServletProject.model.db.DBManager;
import com.example.ServletProject.model.entity.Fields;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManagerTest {

    @Test
    public void connectionTest(){
        DBManager dbManager = DBManager.getInstance();
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
