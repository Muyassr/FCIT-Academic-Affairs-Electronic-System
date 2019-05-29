
package cpcs203_prog4;

import java.util.ArrayList;

public class Section {
    
    private int secID;
    private String secName;
    private Course courseInfo;
    private int secSize;
    private String slotForTheory;
    private String slotForLab;
    private String secRoomNo;
    private String secLabNo;
    private Instructor secInstructor;
    private ArrayList<Student> StudentList = new ArrayList<Student>();
//private static ArrayList<FCITMember> departMem = new ArrayList<FCITMember>();
    public Section(int secID, String secName, Course courseInfo, int secSize, String slotForTheory, 
            String slotForLab, String secRoomNo, String secLabNo, Instructor secInstructor, Student[] StudentList)
    {
        this.secID = secID;
        this.secName = secName;
        this.courseInfo = courseInfo;
        this.secSize = secSize;
        this.slotForTheory = slotForTheory;
        this.slotForLab = slotForLab;
        this.secRoomNo = secRoomNo;
        this.secLabNo = secLabNo;
        this.secInstructor = secInstructor;
        //this.StudentList = StudentList;
    }
    
    
    public Section(int secID, String secName, Course courseInfo, int secSize, String slotForTheory, 
            String slotForLab, String secRoomNo, String secLabNo, Instructor secInstructor, Student sec)
    {
        this.secID = secID;
        this.secName = secName;
        this.courseInfo = courseInfo;
        this.secSize = secSize;
        this.slotForTheory = slotForTheory;
        this.slotForLab = slotForLab;
        this.secRoomNo = secRoomNo;
        this.secLabNo = secLabNo;
        this.secInstructor = secInstructor;
        this.StudentList.add(sec);
    }
     public Section() {
        this.secID = 0;
        this.secName = "NoSecName";
        this.courseInfo = null;
        this.secSize = 0;
        this.slotForTheory = "No slotForTheory";
        this.slotForLab = "No slotForLab";
        this.secRoomNo = "No secRoomNo";
        this.secLabNo = "No secLabNo";
        this.secInstructor = null;
        this.StudentList.add(null);
    }

    public int getSecID() {
        return secID;
    }

    public void setSecID(int secID) {
        this.secID = secID;
    }

    public ArrayList<Student> getStudentList() {
        return StudentList;
    }

    public void setStudentList(ArrayList<Student> StudentList) {
        this.StudentList = StudentList;
    }

   
    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public Course getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(Course courseInfo) {
        this.courseInfo = courseInfo;
    }

    public int getSecSize() {
        return secSize;
    }

    public void setSecSize(int secSize) {
        this.secSize = secSize;
    }

    public String getSlotForTheory() {
        return slotForTheory;
    }

    public void setSlotForTheory(String slotForTheory) {
        this.slotForTheory = slotForTheory;
    }

    public String getSlotForLab() {
        return slotForLab;
    }

    public void setSlotForLab(String slotForLab) {
        this.slotForLab = slotForLab;
    }

    public String getSecRoomNo() {
        return secRoomNo;
    }

    public void setSecRoomNo(String secRoomNo) {
        this.secRoomNo = secRoomNo;
    }

    public String getSecLabNo() {
        return secLabNo;
    }

    public void setSecLabNo(String secLabNo) {
        this.secLabNo = secLabNo;
    }

    public Instructor getSecInstructor() {
        return secInstructor;
    }

    public void setSecInstructor(Instructor secInstructor) {
        this.secInstructor = secInstructor;
    }

   
    
    
    
    
}
