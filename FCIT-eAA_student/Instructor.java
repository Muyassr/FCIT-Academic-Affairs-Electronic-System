
package cpcs203_prog4;

import java.util.ArrayList;

public class Instructor extends FCITMember {
    
    private String instQualification;
    private ArrayList<Section> instSchedule= new ArrayList<Section>();
    //    private ArrayList<Section> sSchedule = new ArrayList<Section>();

    
    @Override
    public String getStatus() {
        return super.getDepartName()+"\t"+super.getMemName()+"\t"+super.getMemID()+"\n"
                +super.getMemStartingYear()+"\t"+this.instQualification;
    }

    public Instructor(String instQualification,
            String departName, String memName, int memID, int memStartingYear) 
    {
        super(departName, memName, memID, memStartingYear);
        this.instQualification = instQualification;
        //this.instSchedule = instSchedule;
    }
    
    
     public Instructor() 
    {
        super("no departName", "No memName", 0, 0);
        this.instQualification = "No instQualification";
        this.instSchedule = null;
    }
    public String getInstQualification() {
        return instQualification;
    }

    public void setInstQualification(String instQualification) {
        this.instQualification = instQualification;
    }

    public ArrayList<Section> getInstSchedule() {
        return instSchedule;
    }

    public void setInstSchedule(ArrayList<Section> instSchedule) {
        this.instSchedule = instSchedule;
    }

   
    
    
}
