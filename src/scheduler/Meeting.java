package scheduler;

public class Meeting {
    int meetingID, studentID, teacherID, organiserID;
    String meetingDate;
    String meetingTime;
   
    public Meeting(int mID, int sID, int tID, int oID, String mDate, String mTime){
        meetingID = mID;
        meetingDate = mDate;
        meetingTime = mTime;
        studentID = sID;
        teacherID = tID;
        organiserID = oID;
    }
}
