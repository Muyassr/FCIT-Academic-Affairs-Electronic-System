
package cpcs203_prog4;

public class Course {
    private String cName;
    private String cCode;
    private int cNum;
    private int cLevel;
    private int cCredit;
    private boolean hasTheroy;
    private boolean hasLab;

    public Course(String cName, String cCode, int cNum, int cLevel, int cCredit, boolean hasTheroy, boolean hasLab) {
        this.cName = cName;
        this.cCode = cCode;
        this.cNum = cNum;
        this.cLevel = cLevel;
        this.cCredit = cCredit;
        this.hasTheroy = hasTheroy;
        this.hasLab = hasLab;
    }
    
    public Course() {
        this.cName =" ";
        this.cCode = " ";
        this.cNum = 0;
        this.cLevel = 0;
        this.cCredit = 0;
        this.hasTheroy = false;
        this.hasLab = false;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public int getcNum() {
        return cNum;
    }

    public void setcNum(int cNum) {
        this.cNum = cNum;
    }

    public int getcLevel() {
        return cLevel;
    }

    public void setcLevel(int cLevel) {
        this.cLevel = cLevel;
    }

    public int getcCredit() {
        return cCredit;
    }

    public void setcCredit(int cCredit) {
        this.cCredit = cCredit;
    }

    public boolean isHasTheroy() {
        return hasTheroy;
    }

    public void setHasTheroy(boolean hasTheroy) {
        this.hasTheroy = hasTheroy;
    }

    public boolean isHasLab() {
        return hasLab;
    }

    public void setHasLab(boolean hasLab) {
        this.hasLab = hasLab;
    }

 
    
    
    
}
