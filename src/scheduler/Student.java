package scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student extends User{
    String name;
    String programme;
    
    protected int checkAccount(int studId){
        Scanner studP = new Scanner(System.in);
        System.out.println("Enter password: ");
        String studPass = studP.nextLine();
        int rowcount = 0;
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=UTC", "root", "");
           Statement state = con.createStatement();
           ResultSet rs = state.executeQuery("select count(*) from student where studID = " + studId + " AND studPass = '" + studPass + "';");
           rs.next();
           rowcount = rs.getInt(1);
           if(rowcount == 0){
               System.out.println("Invalid ID or password.\n");
           }
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rowcount;
    }
    
    protected void createMeeting(int id){
        System.out.println("\nCREATE MEETING\n--------------");
        Scanner studDateIn = new Scanner(System.in);
        Scanner studTimeIn = new Scanner(System.in);
        Scanner loc = new Scanner(System.in);
        System.out.println("Enter Meeting Date (yyyy-mm-dd): ");
        String studDate = studDateIn.nextLine();
        System.out.println("Enter Meeting Time (hh:mm): ");
        String studTime = studTimeIn.nextLine();
        Scanner studId = new Scanner(System.in);
        System.out.println("Enter Location: ");
        String location = loc.nextLine();
        System.out.println("Enter Participant ID: ");
        int id2 = studId.nextInt();
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=UTC", "root", "");
           Statement state = con.createStatement();
           state.executeUpdate("insert into meeting values(NULL, '"+ studDate + "','" + studTime + ":00','" + location + "'," + id2 + ", NULL, " + id + ");");
           state.executeUpdate("insert into meeting values(NULL, '"+ studDate + "','" + studTime + ":00','" + location + "'," + id + ", NULL, " + id + ");");
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    protected void removeMeeting(int id){
       System.out.println("\nREMOVE MEETING\n--------------");
        Scanner pId = new Scanner(System.in);
        Scanner RDate = new Scanner(System.in);
        System.out.println("Enter date (YYYY-MM-DD): ");
        String removeDate = RDate.nextLine();
        System.out.println("Enter Participant ID: ");;
        int partID = pId.nextInt();
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=UTC", "root", "");
           Statement state = con.createStatement();
           state.executeUpdate("delete from meeting where meetDate = '"+ removeDate + "' AND orgID = " + id + " AND studID = " + partID + ";");
           state.executeUpdate("delete from meeting where meetDate = '"+ removeDate + "' AND orgID = " + id + " AND studID = " + id + ";");
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
