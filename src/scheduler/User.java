package scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
    int id;
    String status;
    
    protected User(){
        id = 2;
        status = "guest";
    }
    protected User(int id, String status){
        this.id = id;
        this.status = status;
    }
    protected void displayDetails(){
        System.out.println("\nYou are not allowed to view User's Details.\n------------");
    }
    //Overloaded Method
    protected void displayDetails(int id){
        System.out.println("\nUser Details\n------------");
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=Singapore", "root", "");
            Statement state1 = con.createStatement();
            Statement state2 = con.createStatement();
            ResultSet rc = state1.executeQuery("Select count(*) from student where studID = " + id +";");
            ResultSet rs = state2.executeQuery("select * from student where studID =" + id + ";");
            rc.next();
            int rowcount = rc.getInt(1);
            if(rowcount > 0){
                while(rs.next()){
                    System.out.println("User ID: " + rs.getInt("studID"));
                    System.out.println("Name: " + rs.getString("studName"));
                    System.out.println("Programme: " + rs.getString("programme"));
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
    protected void checkTime(){
        Scanner targetUserID = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        int targetUser = targetUserID.nextInt();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler?serverTimezone=Singapore", "root", "");
            Statement state1 = con.createStatement();
            Statement state2 = con.createStatement();
            ResultSet rc = state1.executeQuery("Select count(*) from meeting where studID = " + targetUser + ";");
            ResultSet rs = state2.executeQuery("select * from meeting where studID =" + targetUser + ";");
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
                System.out.println("No Meeting exists.");
            }
        } catch(SQLException ex){
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public static void main(String[] args) {
        Scanner usrType = new Scanner(System.in);
        System.out.println("1 - Student");
        System.out.println("2 - Teacher");
        System.out.println("3 - Guest");
        System.out.println("4 - exit");
        System.out.println("Log in as: ");
        int userType = usrType.nextInt();
        while(userType != 4){
            switch(userType){
                case 1:
                     System.out.println("\nEnter ID: ");
                     Scanner studIn = new Scanner(System.in);
                     int studId = studIn.nextInt();
                     Student student = new Student();
                     int result = student.checkAccount(studId);
                     if(result > 0){
                        System.out.println("\n1 - Display User Details");
                        System.out.println("2 - Check User Timetable.");
                        System.out.println("3 - Create Meeting.");
                        System.out.println("4 - Remove Meeting.");
                        System.out.println("5 - Log out");
                        Scanner opt = new Scanner(System.in);
                        System.out.println("\nEnter option:");
                        int option = opt.nextInt();
                        while(option != 5){
                            switch(option){
                                case 1:
                                    Scanner targetUserID = new Scanner(System.in);
                                    System.out.println("Enter Student ID: ");
                                    int targetUser = targetUserID.nextInt();
                                    student.displayDetails(targetUser);
                                    break;  
                                case 2:
                                    student.checkTime();
                                    break;
                                    
                                case 3:
                                    student.createMeeting(studId);
                                    break;
                                case 4:
                                    student.removeMeeting(studId);
                                    break;
                                }
                            System.out.println("\nEnter option: ");
                            option = opt.nextInt();
                            }
                     }
                     break;
                case 2:
                    System.out.println("\nEnter ID: ");
                    Scanner tchrIn = new Scanner(System.in);
                    int teacherId = tchrIn.nextInt();
                    Teacher teacher = new Teacher();
                    int result_t = teacher.checkAccount(teacherId);
                    if(result_t > 0){
                        System.out.println("\n1 - Display User Details");
                        System.out.println("2 - Check User Timetable.");
                        System.out.println("3 - Create Meeting.");
                        System.out.println("4 - Remove Meeting.");
                        System.out.println("5 - Log out");
                        Scanner opt = new Scanner(System.in);
                        System.out.println("\nEnter option:");
                        int option = opt.nextInt();
                        while(option != 5){
                            switch(option){
                                case 1:
                                    teacher.displayDetails();
                                    break;
                                case 2:
                                    teacher.checkTime();
                                    break;
                                    
                                case 3:
                                    teacher.createMeeting(teacherId);
                                    break;
                                case 4:
                                    teacher.removeMeeting(teacherId);
                                    break;
                            }
                            System.out.println("\nEnter option: ");
                            option = opt.nextInt();
                        }
                     }
                     break;
                     
                case 3:
                    User guest = new User();
                    System.out.println("\n1 - Display User Details");
                    System.out.println("2 - Check User Timetable.");
                    System.out.println("5 - Log out");
                    Scanner opt = new Scanner(System.in);
                    System.out.println("\nEnter option:");
                    int option = opt.nextInt();
                    while(option != 5){
                        switch(option){
                            case 1:
                                guest.displayDetails();
                                break;
                            
                            case 2:
                                guest.checkTime();
                                break;
                        }
                        System.out.println("\nEnter option:");
                        option = opt.nextInt();
                    }
            }
            System.out.println("\n1 - Student");
            System.out.println("2 - Teacher");
            System.out.println("3 - Guest");
            System.out.println("4 - exit");
            System.out.println("Log in as: ");
            userType = usrType.nextInt();
        }
    }
    
}


