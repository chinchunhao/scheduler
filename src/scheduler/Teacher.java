package scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Teacher extends User{
    String name;
    String department;
    
    protected int checkAccount(int tchrID){
        Scanner tchrP = new Scanner(System.in);
        System.out.println("Enter password: ");
        String tchrPass = tchrP.nextLine();
        int rowcount = 0;
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=UTC", "root", "");
           Statement state = con.createStatement();
           ResultSet rs = state.executeQuery("select count(*) from teacher where tchrID = " + tchrID + " AND tchrPass = '" + tchrPass + "';");
           rs.next();
           rowcount = rs.getInt(1);
           if(rowcount == 0){
               System.out.println("Invalid ID or Password\n");
           }
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rowcount;
    }
    @Override
     protected void displayDetails(){
        System.out.println("\nUser Details\n------------");
        Scanner targetUserID = new Scanner(System.in);
        System.out.println("Enter Lecturer ID: ");
        int targetUser = targetUserID.nextInt();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=Singapore", "root", "");
            Statement state1 = con.createStatement();
            Statement state2 = con.createStatement();
            ResultSet rc = state1.executeQuery("Select count(*) from teacher where tchrID = " + targetUser +";");
            ResultSet rs = state2.executeQuery("select * from teacher where tchrID =" + targetUser + ";");
            rc.next();
            int rowcount = rc.getInt(1);
            if(rowcount > 0){
                while(rs.next()){
                    System.out.println("User ID: " + rs.getInt("tchrID"));
                    System.out.println("Name: " + rs.getString("tchrName"));
                    System.out.println("Department: " + rs.getString("department"));
                }
            }else{
                System.out.println("User not exist.");
            }
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    @Override
    protected void checkTime(){
        Scanner targetUserID = new Scanner(System.in);
        System.out.println("Enter Lecturer ID: ");
        int targetUser = targetUserID.nextInt();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=Singapore", "root", "");
            Statement state1 = con.createStatement();
            Statement state2 = con.createStatement();
            ResultSet rc = state1.executeQuery("Select count(*) from meeting where tchrID = " + targetUser + ";");
            ResultSet rs = state2.executeQuery("select * from meeting where tchrID =" + targetUser + ";");
            rc.next();
            int rowcount = rc.getInt(1);
            if(rowcount > 0){
                System.out.println("\nTimetable:");
                while(rs.next()){
                    System.out.println("Meeting Date: " + rs.getDate("meetDate"));
                    System.out.println("Meeting Time: "+ rs.getTime("meetTime"));
                    System.out.println("Location: " + rs.getString("meetLocation")+"\n");
                }
            }else{
                System.out.println("No meeting exists.");
            }
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    protected void createMeeting(int id){
        System.out.println("\nCREATE MEETING\n--------------");
        Scanner tchrDateIn = new Scanner(System.in);
        Scanner tchrTimeIn = new Scanner(System.in);
        Scanner loc = new Scanner(System.in);
        Scanner tchrId = new Scanner(System.in);
        System.out.println("Enter Meeting Date (yyyy-mm-dd): ");
        String tchrDate = tchrDateIn.nextLine();
        System.out.println("Enter Meeting Time (hh:mm): ");
        String tchrTime = tchrTimeIn.nextLine();
         System.out.println("Enter Location: ");
        String location = loc.nextLine();
        System.out.println("Enter Participant ID: ");
        int id2 = tchrId.nextInt();
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=UTC", "root", "");
           Statement state = con.createStatement();
           state.executeUpdate("insert into meeting values(NULL, '"+ tchrDate + "', '" + tchrTime + ":00', '" + location + "', NULL, " + id2 + "," + id + ");");
           state.executeUpdate("insert into meeting values(NULL, '"+ tchrDate + "', '" + tchrTime + ":00', '" + location + "', NULL, " + id + "," + id + ");");
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    protected void removeMeeting(int id){
       System.out.println("\nREMOVE MEETING\n--------------");
        Scanner RDate = new Scanner(System.in);
        Scanner pId = new Scanner(System.in);
        System.out.println("Enter date (YYYY-MM-DD): ");
        String removeDate = RDate.nextLine();
         System.out.println("Enter Participant ID: ");;
        int partID = pId.nextInt();
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=UTC", "root", "");
           Statement state = con.createStatement();
           state.executeUpdate("delete from meeting where meetDate = '"+ removeDate + "' AND orgID = " + id +" AND tchrID = " + id + ";");
           state.executeUpdate("delete from meeting where meetDate = '"+ removeDate + "' AND orgID = " + id +" AND tchrID = " + partID + ";");
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
