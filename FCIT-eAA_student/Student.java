
package cpcs203_prog4;

import java.util.ArrayList;

public class Student extends FCITMember {
    
    private double sGPA;
    private int totalCredit;
   //private Section[] sSchedule;
    private ArrayList<Section> sSchedule = new ArrayList<Section>();
    private int sTotalCourses;
    
    @Override
    public String getStatus() {
        return super.getDepartName()+"\t"+super.getMemName()+"\t"+super.getMemID()+"\n"
                +super.getMemStartingYear()+"\t"+this.sGPA+"\t"+this.totalCredit+"\t"+this.sTotalCourses;
    }

    public Student(double sGPA, int totalCredit, int sTotalCourses, 
                    String departName, String memName, int memID, int memStartingYear) 
    {
        super(departName, memName, memID, memStartingYear);
        this.sGPA = sGPA;
        this.totalCredit = totalCredit;
        //this.sSchedule = sSchedule;
        this.sTotalCourses = sTotalCourses;
    }
    
    public Student() 
    {
        super("no departName", "No memName", 0, 0);
        this.sGPA = 0.0;
        this.totalCredit = 0;
       this.sSchedule = null;
        this.sTotalCourses = 0;
    }

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

    public ArrayList<Section> getsSchedule() {
        return sSchedule;
    }

    public void setsSchedule(ArrayList<Section> sSchedule) {
        this.sSchedule = sSchedule;
    }

    public int getsTotalCourses() {
        return sTotalCourses;
    }

    public void setsTotalCourses(int sTotalCourses) {
        this.sTotalCourses = sTotalCourses;
    }

    
    
}
