
package cpcs203_prog4;

import java.util.ArrayList;

public abstract class FCITMember {
    private String departName;
    private String memName;
    private int memID;
    private int memStartingYear;
    
    public abstract String getStatus();

    public FCITMember(String departName, String memName, int memID, int memStartingYear) {
        this.departName = departName;
        this.memName = memName;
        this.memID = memID;
        this.memStartingYear = memStartingYear;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public int getMemID() {
        return memID;
    }

    public void setMemID(int memID) {
        this.memID = memID;
    }

    public int getMemStartingYear() {
        return memStartingYear;
    }

    public void setMemStartingYear(int memStartingYear) {
        this.memStartingYear = memStartingYear;
    }
    
    
    
    
}
