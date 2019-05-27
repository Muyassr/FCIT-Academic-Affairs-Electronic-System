/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcit.eaa;
import java.util.*;

/**
 *
 * @author Toshiba
 */
public class Student extends FCITMember implements Comparable<FCITMember>{
    // Data
    private double sGPA;
    private int totalCredit;
    private String[][] studentPlan;
    private Section[] sSchedule;
    private int totalRegCourses;

    public int getTotalRegCourses() {
        return totalRegCourses;
    }

    public void setTotalRegCourses(int totalRegCourses) {
        this.totalRegCourses = totalRegCourses;
    }

    // Constructor
    public Student(double sGPA, int totalCredit, String[][] studentPlan, 
            String memName, int memID, int memStartingYear) {
        super(memName, memID, memStartingYear);
        this.sGPA = sGPA;
        this.totalCredit = totalCredit;
        this.studentPlan = studentPlan;
        sSchedule = null; totalRegCourses =0;
    }

    public Student(double sGPA, int totalCredit, String[][] studentPlan, 
            Section[] sSchedule, String memName, int memID, 
            int memStartingYear, int totalRegCourses) {
        super(memName, memID, memStartingYear);
        this.sGPA = sGPA;
        this.totalCredit = totalCredit;
        this.studentPlan = studentPlan;
        this.sSchedule = sSchedule;
        this.totalRegCourses = totalRegCourses;
    }

    //Methods
    public double getsGPA() {
        return sGPA;
    }

    public void setsGPA(double sGPA) {
        this.sGPA = sGPA;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String[][] getStudentPlan() {
        return studentPlan;
    }

    public void setStudentPlan(String[][] studentPlan) {
        this.studentPlan = studentPlan;
    }

    public Section[] getsSchedule() {
        return sSchedule;
    }

    public void setsSchedule(Section[] sSchedule) {
        this.sSchedule = sSchedule;
    }

    public String getDeprtName() {
        return deprtName;
    }

    public String getMemName() {
        return memName;
    }

    public int getMemID() {
        return memID;
    }
  
    public String getStatus(){
        int years = 0;
        Calendar currentYear = new GregorianCalendar();
        years = currentYear.get(Calendar.YEAR) - memStartingYear;
        
        if(years >= 4 && totalCredit >= 100)
          return "Graduate";
        else if (years >= 4 && totalCredit < 100)
          return " Fourth Year Delayed";
        else if (years >= 3 && totalCredit >= 54)
            return "Third Year";
        else if (years >= 3 && totalCredit < 50)
            return "Third Year Delayed";
        else        
        return "Second Year Student";
    }
    
    public int compareTo(FCITMember other) {
        if(memStartingYear > other.getStartingYear())
            return 1;
          else if (memStartingYear < other.getStartingYear())
           return -1;
        return 0;}
    
    
}
