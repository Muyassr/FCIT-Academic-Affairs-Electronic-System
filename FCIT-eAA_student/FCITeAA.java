package cpcs203_prog4;

import com.sun.org.apache.bcel.internal.generic.Instruction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class FCITeAA {

    public static void main(String[] args) throws FileNotFoundException {

        File fileInput = new File("infciteaa.txt");
        if (!fileInput.exists()) {
            System.out.println("Sorry!! File does not exist");
            System.exit(0);
        }
        Scanner read = new Scanner(fileInput);

        File fileOutput = new File("outfciteaa.txt");
        PrintWriter write = new PrintWriter(fileOutput);

        java.util.Date date = new java.util.Date();
        write.print("Programming Assignment 4 _______" + date + "\n");

        while (read.hasNext()) {

            String command = read.next();
            System.out.println("\t\tcom: " + command);
            if (command.equals("InputDepartmentPlan")) {

                FCITeAA.departCourses = new Course[read.nextInt()];
                for (int i = 0; i < FCITeAA.departCourses.length; i++) {
                    departCourses[i] = GetCourse(read);
                    //System.out.println(departCourses[i].getcName());
                }
                //System.out.println("");
                write.println("Department Plan(Courses) Data has been Inserted\n"
                        + "============================================================");

            } else if (command.equals("InputInstructorData")) {

                int numOfInstructors = read.nextInt();
                for (int i = 0; i < numOfInstructors; i++) {
                    FCITMember instructor = new Instructor();
                    departMem.add(GetInstrucorAndStudent(read, instructor));
                    //System.out.println(instructor.getStatus());
                }

                write.println("Department Instructor's Data has been Inserted\n"
                        + "============================================================");

            } else if (command.equals("InputStudentData")) {

                int numOfStudents = read.nextInt();
                for (int i = 0; i < numOfStudents; i++) {
                    FCITMember student = new Student();
                    departMem.add(GetInstrucorAndStudent(read, student));
                    //System.out.println(student.getStatus());
                }

                write.println("Department Students Data has been Inserted\n"
                        + "============================================================");

            } else if (command.equals("InputSectionData")) {

                int numOfSections = read.nextInt();
                for (int i = 0; i < numOfSections; i++) {
                    Section sec = new Section();
                    departSchedule.add(GetSection(read, sec));
                }

                write.println("Department Schedule (Sections) has been Inserted\n"
                        + "============================================================");

            } else if (command.equals("RegisterCourse")) {

                int numOfStudentsRigstring = read.nextInt();
                read.nextLine();
                for (int i = 0; i < numOfStudentsRigstring; i++) {
                    String line = read.nextLine();
                    String temp[] = line.split("\\s+");
                    int id = FindMem(Integer.parseInt(temp[0]));
                    boolean p = isAllSectionsAvailable(temp);
                    boolean o = isAllSectionsAvailable(temp);
                    if (p && o) {
                        //private ArrayList<Section> sSchedule = new ArrayList<Section>();
                        ArrayList se = new ArrayList();
                        ((Student) departMem.get(id)).setsSchedule(se);
                        // int leng = ((Student) departMem.get(id)).getsSchedule().size();
                        int counterTemp = 1;

                        while (counterTemp < temp.length - 1) {
                            int secID = FindSecByID(Integer.parseInt(temp[counterTemp]));
                            ((Student) departMem.get(id)).getsSchedule().add(departSchedule.get(secID));
                            departSchedule.get(secID).getStudentList().add(((Student) departMem.get(id)));
                            counterTemp++;
                        }
//                        
////                        //conditoin#1
                        if (isAllSameLevel(temp)) {
                        } else {
                            System.out.println(" Student has registered course from two different levels");
                        }
//                        //condition#2
                        if ((isStuentGraduate(temp)) && !(isCPCSC498(temp))) {
                            System.out.println(" Student is a Graduate and did not Register CPCS 498");
                        }
//                        //condition#3
                        if (!isMore12Hours(temp)) {
                            System.out.println(" Student Registered Credit is less than 12 Credits Must ADD courses");
                        }
//                        //condition#4
                        if (isFourthYearStud(temp)) {
                            System.out.println(" The Student is a  Fourth Year Delayed");
                        }
//                        //condition#5  
                        int g = 9;
                        boolean w = isContradict(temp);

                        if (isContradict(temp)) {
                            DeletContradict(temp);
                        }
//                   
                    }

                }

       // ShowMeSchArrInsidStd();
                //ShowMeStudentListInsideSec();
            } else if (command.equals("InstructorLoadRequest")) {
                System.out.println("Instructor Load Requests");
                int num = read.nextInt();
                read.nextLine();
                for (int i = 0; i < num; i++) {
                    String line = read.nextLine();

                    String temp[] = line.split(" ");
                    int instID = Integer.parseInt(temp[0]);
                    int secOne = Integer.parseInt(temp[1]);
                    int secTwo = Integer.parseInt(temp[2]);
                    int indexInst = FindMem(instID);
                    int indSc1 = FindSecByID(secOne);
                    int indSc2 = FindSecByID(secTwo);
                    ArrayList<Section> sec1 = new ArrayList<Section>();
                    ArrayList<Section> sec2 = new ArrayList<Section>();
                    ((Instructor) departMem.get(indexInst)).setInstSchedule(sec1);
                    ((Instructor) departMem.get(indexInst)).setInstSchedule(sec2);
                    departSchedule.get(indSc1).setSecInstructor((Instructor) departMem.get(indexInst));
                    departSchedule.get(indSc2).setSecInstructor((Instructor) departMem.get(indexInst));
                    ((Instructor) departMem.get(indexInst)).getInstSchedule().add(departSchedule.get(indSc1));
                    ((Instructor) departMem.get(indexInst)).getInstSchedule().add(departSchedule.get(indSc2));
                    // ((Student) departMem.get(id)).getsSchedule()
                    write.println("\nInstructor: " + ((Instructor) departMem.get(indexInst)).getMemName() + " Instructor ID: "
                            + ((Instructor) departMem.get(indexInst)).getMemID() + " Total Number of Courses: 2");
                    write.println(" SecId   SecName    Course      Time  Location");
                    write.println(departSchedule.get(indSc1).getSecID() + "\t" + departSchedule.get(indSc1).getSecName() + "  "
                            + departSchedule.get(indSc1).getCourseInfo().getcCode() + "  " + departSchedule.get(indSc1).getSlotForTheory() + " "
                            + departSchedule.get(indSc1).getSlotForLab() + " " + departSchedule.get(indSc1).getSecRoomNo() + " "
                            + " " + departSchedule.get(indSc1).getSecLabNo());
                    write.println(departSchedule.get(indSc2).getSecID() + "\t" + departSchedule.get(indSc2).getSecName() + "  "
                            + departSchedule.get(indSc2).getCourseInfo().getcCode() + "  " + departSchedule.get(indSc1).getSlotForTheory() + " "
                            + departSchedule.get(indSc2).getSlotForLab() + " " + departSchedule.get(indSc2).getSecRoomNo() + " "
                            + " " + departSchedule.get(indSc2).getSecLabNo());
                    write.println("------------------------------------------------------------");

                }
                write.println("==========================================================\n");
            } else if (command.equals("PrintAllInstructorsLoads")) {
                write.println("All Instructors Sorted Load List \n"
                        + "_________________________________\n");
                for (int i = 0; i < 7; i++) {
                    int counter = 0;
                    write.println("Instructor: " + ((Instructor) departMem.get(i)).getMemName() + " Instructor ID: "
                            + ((Instructor) departMem.get(i)).getMemID() + "");
                    write.println("     SecId   SecName    Course      Time  Location");
                    while (counter < 2) {

                        write.printf("%10d %9s %4s %9s %9s %4s %4s %4s\r\n", ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getSecID(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getSecName(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getCourseInfo().getcCode(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getCourseInfo().getcNum(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getSlotForTheory(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getSlotForLab(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getSecRoomNo(),
                                ((Instructor) departMem.get(i)).getInstSchedule().get(counter).getSecLabNo());

                        counter++;
                    }

                }

                write.println("==========================================================\n");

            } else if (command.equals("PrintSectionStudentList")) {
                int secID = 0;
                while (secID != -1) {
                    secID = read.nextInt();
                    if (secID != -1) {
                        write.println("Course: " + departSchedule.get(FindSecByID(secID)).getCourseInfo().getcName() + "\t\t\t"
                                + "Section: " + departSchedule.get(FindSecByID(secID)).getSecName() + "\n"
                                + "Day and Time: " + departSchedule.get(FindSecByID(secID)).getSlotForTheory() + " "
                                + departSchedule.get(FindSecByID(secID)).getSecRoomNo()
                                + "\n____________________________________________________");

                        for (int i = 0; i < departSchedule.get(FindSecByID(secID)).getStudentList().size(); i++) {
                            write.println(departSchedule.get(FindSecByID(secID)).getStudentList().get(i).getMemID() + "  "
                                    + departSchedule.get(FindSecByID(secID)).getStudentList().get(i).getMemName());

                        }

                        write.println("");
                    }

                }

            }
        }

        write.flush();
        write.close();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Course GetCourse(Scanner read) {
        String history;
        int number;
        history = read.next();
        Course w = new Course();

        if (history.equals("Level")) {
            number = read.nextInt();
            w.setcLevel(number);
            history = read.next();
        }

        w.setcCode(history);
        int cNum = read.nextInt();
        w.setcNum(cNum);
        String cName = read.next().replaceAll("[?]", " ");
        w.setcName(cName);
        int cCredit = read.nextInt();
        w.setcCredit(cCredit);
        history = read.next();
        if (history.equals("theory")) {
            w.setHasTheroy(true);
        }
        history = read.next();
        if (history.equals("lab")) {
            w.setHasLab(true);
        }

        return w;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static FCITMember GetInstrucorAndStudent(Scanner read, FCITMember fcit) {

        String memName = read.next().replaceAll("[?]", " ");
        fcit.setMemName(memName);
        int memID = read.nextInt();
        fcit.setMemID(memID);
        int memStartingYear = read.nextInt();
        fcit.setMemStartingYear(memStartingYear);

        if (fcit instanceof Student) {
            double sGPA = read.nextDouble();
            ((Student) fcit).setsGPA(sGPA);
            int totalCredit = read.nextInt();
            ((Student) fcit).setTotalCredit(totalCredit);
            String IDONTKNOWWHAT = read.next();
        } else if (fcit instanceof Instructor) {
            String instQualification = read.next().replaceAll("[?]", " ");
            ((Instructor) fcit).setInstQualification(instQualification);
        }

        return fcit;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int GetIndexCourse(Object[] obj, String cCode, int cNum) {

        for (int j = 0; j < departCourses.length; j++) {

            if ((departCourses[j].getcCode().equals(cCode)) && (departCourses[j].getcNum() == cNum)) {
                return j;
            }

        }

        return -1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Section GetSection(Scanner read, Section sec) {

        int secID = read.nextInt();
        sec.setSecID(secID);
        String secName = read.next();
        sec.setSecName(secName);
        String cCode = read.next();
        int cNum = read.nextInt();
        sec.setCourseInfo(departCourses[GetIndexCourse(departCourses, cCode, cNum)]);
        int secSize = read.nextInt();
        sec.setSecSize(secSize);
        String slotForTheory = read.next().replaceAll("[?]", " ");
        sec.setSlotForTheory(slotForTheory);
        String slotForLab = read.next().replaceAll("[?]", " ");
        sec.setSlotForLab(slotForLab);
        String secRoomNo = read.next();
        sec.setSecRoomNo(secRoomNo);
        String secLabNo = read.next();
        sec.setSecLabNo(secLabNo);
//        Student[] std = new Student[sec.getSecSize()];
        ArrayList StudentList = new ArrayList();
        sec.setStudentList(StudentList);
        Instructor inst = new Instructor();
        sec.setSecInstructor(inst);
        return sec;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean CheckID(ArrayList<FCITMember> departMem, String temp[], Scanner read, int id) {
        for (int i = 0; i < departMem.size(); i++) {
            if (id == (departMem.get(i)).getMemID()) {
                //if (Integer.parseInt(temp[0])== (departMem.get(i)).getMemID()) {
                return true;
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean CheckCourses(Course[] departCourses, Scanner read, String temp[]) {

        for (int i = 0; i < departCourses.length; i++) {
            for (int j = 0;; j++) {
                if (Integer.parseInt(temp[j]) == departCourses[i].getcNum()) {
                    return true;
                }

                if (temp[j].equals("-1")) {
                    break;
                }
            }

        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int FindMem(int id) {
        for (int j = 0; j < departMem.size(); j++) {

            if (departMem.get(j).getMemID() == id) {
                return j;
            }

        }
        return -1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int FindCourseByNum(int num) {
        for (int k = 0; k < departCourses.length; k++) {
            if (num == departCourses[k].getcNum()) {
                return k;
            }
        }

        return -1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int FindCourseByNumInSecArray(int num) {
        for (int k = 0; k < departSchedule.size(); k++) {
            if (num == departSchedule.get(k).getCourseInfo().getcNum()) {
                return k;
            }
        }

        return -1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int FindSecByID(int secID) {
        for (int k = 0; k < departSchedule.size(); k++) {
            if (secID == departSchedule.get(k).getSecID()) {
                return k;
            }
        }

        return -1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isStudAviliable(int idStd) {

        for (int j = 0; j < departMem.size(); j++) {
            if (departMem.get(j) instanceof Student) {
                if ((idStd == departMem.get(j).getMemID())) {
                    return true;
                }
            }

        }

        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isSectionAvailable(int secID) {

        for (int k = 0; k < departSchedule.size(); k++) {
            if (departSchedule.get(k).getSecID() == secID) {
                return true;
            }
        }
        return false;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isAllSectionsAvailable(String temp[]) {
        int counter = 0;
        for (int j = 1; j < temp.length - 1; j++) {
            //System.out.print(Integer.parseInt(temp[j])+" ");
            if (isSectionAvailable(Integer.parseInt(temp[j]))) {
                counter++;
            } else {
                System.out.println("this SecID: " + temp[j] + " is not Avilable.");
            }

            if (counter == temp.length - 2) {

                return true;
            }

        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void FillStdSchedule(String temp[]) {
//         Section se[] = new Section[temp.length - 2];
//         ((Student) departMem.get(FindMem(Integer.parseInt(temp[0])))).setsSchedule(se);
//        for (int j = 0; j < ((Student) departMem.get(FindMem(Integer.parseInt(temp[0])))).getsSchedule().length; j++) {
//            ((Student) departMem.get(FindMem(Integer.parseInt(temp[0])))).getsSchedule()[j]
//                    = departSchedule.get(FindSecByID(Integer.parseInt(temp[j + 1])));
//        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void FillStdListInsidSchdule() {
//           for (int j = 0; j < departSchedule.size(); j++) {
//                    for (int k = 0; k < departMem.size(); k++) {
//                        if (departMem.get(k) instanceof Student) {
//                            int counter = 0;
//                            for (int l = 0; l < ((Student) departMem.get(k)).getsSchedule().length; l++) {
//                                if (((Student) departMem.get(k)).getsSchedule()[l].getSecID() == departSchedule.get(j).getSecID()) {
//                                    //departSchedule.get(j).getStudentList()[counter] = ((Student) departMem.get(k));
//                                    counter++;
//                                    }
//
//                                }
//
//                            }
//
//                        }
//
//                    }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void ShowMeSchArrInsidStd() {
        System.out.println("\nshow me Schedule inside Student: \n"
                + "((Student) departMem.get(j)).getsSchedule()[k].getSecID():");

        for (int j = 0; j < departMem.size(); j++) {
            if (departMem.get(j) instanceof Student) {
                System.out.print(j + "> ");
                for (int k = 0; k < ((Student) departMem.get(j)).getsSchedule().size(); k++) {

                    System.out.print(k + "[" + ((Student) departMem.get(j)).getsSchedule().get(k).getSecID() + "] ");
                }
                System.out.println("");
            } else {
                System.out.print(j + ": Instrutor||  ");
            }

            if (j == 6) {
                System.out.println("");
            }
        }
        System.out.println("");
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void ShowMeStudentListInsideSec() {
        System.out.println("show me STudentList: \n");
        for (int i = 0; i < departSchedule.size(); i++) {
            System.out.println(i + ")Sec (" + departSchedule.get(i).getSecName() + ") Students:");
            for (int j = 0; j < departSchedule.get(i).getStudentList().size(); j++) {
                System.out.print("\t" + j + "[" + departSchedule.get(i).getStudentList().get(j).getMemName() + "] ");
            }
            System.out.println("");
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isAllSameLevel(String temp[]) {
        int counter = 0;
        int indexStd = FindMem(Integer.parseInt(temp[0]));
        for (int i = 0; i < temp.length - 1; i++) {
            int index = FindSecByID(Integer.parseInt(temp[i]));
            if (index != -1) {
                for (int j = 0; j < temp.length - 2; j++) {
                    if ((departSchedule.get(j).getCourseInfo().getcLevel()
                            != departSchedule.get(index).getCourseInfo().getcLevel())
                            && j != index) {
                        counter++;
                    }
                }
            }

            if (counter == temp.length - 2) {
                return true;
            }
        }
        return false;
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isStuentGraduate(String temp[]) {
        int indexStd = FindMem(Integer.parseInt(temp[0]));
        System.out.println("name: " + ((Student) departMem.get(indexStd)).getMemName());
        int totalhours = ((Student) departMem.get(indexStd)).getTotalCredit();
        for (int i = 1; i < temp.length - 1; i++) {
            int indexSec = FindSecByID(Integer.parseInt(temp[i]));
            totalhours += departSchedule.get(indexSec).getCourseInfo().getcCredit();
        }

        int studyingYear = 2016 - ((Student) departMem.get(indexStd)).getMemStartingYear();
        int length = ((Student) departMem.get(indexStd)).getsSchedule().size();
        if (totalhours > 100 && studyingYear >= 4) {
            return true;
        }

        return false;
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isCPCSC498(String temp[]) {
        int indexStd = FindMem(Integer.parseInt(temp[0]));
        int length = ((Student) departMem.get(indexStd)).getsSchedule().size();
        for (int i = 0; i < length; i++) {
            String code = ((Student) departMem.get(indexStd)).getsSchedule().get(i).getCourseInfo().getcCode();
            int cNum = ((Student) departMem.get(indexStd)).getsSchedule().get(i).getCourseInfo().getcNum();
            if (code.contains("CPCS") && (cNum == 498)) {

                return true;

            }
        }

        return false;
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isMore12Hours(String temp[]) {
        int indexStd = FindMem(Integer.parseInt(temp[0]));
        System.out.println("name: " + ((Student) departMem.get(indexStd)).getMemName());
        int totalhours = 0;
        for (int i = 1; i < temp.length - 1; i++) {
            int indexSec = FindSecByID(Integer.parseInt(temp[i]));
            totalhours += departSchedule.get(indexSec).getCourseInfo().getcCredit();
        }

        if (totalhours > 12) {
            return true;
        }

        return false;
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isFourthYearStud(String temp[]) {
        int indexStd = FindMem(Integer.parseInt(temp[0]));
        System.out.println("name: " + ((Student) departMem.get(indexStd)).getMemName());
        int totalhours = ((Student) departMem.get(indexStd)).getTotalCredit();
        for (int i = 1; i < temp.length - 1; i++) {
            int indexSec = FindSecByID(Integer.parseInt(temp[i]));
            totalhours += departSchedule.get(indexSec).getCourseInfo().getcCredit();
        }

        int studyingYear = 2016 - ((Student) departMem.get(indexStd)).getMemStartingYear();
        int length = ((Student) departMem.get(indexStd)).getsSchedule().size();
        if (totalhours < 100 && studyingYear >= 4) {
            return true;
        }

        return false;
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isContradict(String temp[]) {
        int std = FindMem(Integer.parseInt(temp[0]));
        int counter = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            int index = FindSecByID(Integer.parseInt(temp[i]));
            if (index != -1) {
                String oneTheory = departSchedule.get(index).getSlotForTheory();
                String oneLab = departSchedule.get(index).getSlotForLab();

                for (int j = 0; j < temp.length - 1; j++) {
                    if (j != 0) {
                        String twoTheory = departSchedule.get(j).getSlotForTheory();
                        String twoLab = departSchedule.get(j).getSlotForLab();
                        if ((((oneTheory.contains(twoTheory) || oneTheory.contains(twoLab) || oneLab.contains(twoTheory) || oneLab.contains(twoLab))))
                                && (j != index)) {
                            counter++;
                        }
                    }
                }
            }
            if (counter == temp.length - 2) {
                return true;
            }
        }
        return false;
    }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void DeletContradict(String temp[]) {
        int counter = 0;
        int std = FindMem(Integer.parseInt(temp[0]));

        for (int i = 0; i < temp.length - 1; i++) {
            int index = FindSecByID(Integer.parseInt(temp[i]));
            if (index != -1) {
                int cOneLevel = departSchedule.get(index).getCourseInfo().getcLevel();
                String oneTheory = departSchedule.get(index).getSlotForTheory();
                String oneLab = departSchedule.get(index).getSlotForLab();
                int cTowLevel = departSchedule.get(index).getCourseInfo().getcLevel();

                for (int j = 0; j < temp.length - 2; j++) {
                    String twoTheory = departSchedule.get(j).getSlotForTheory();
                    String twoLab = departSchedule.get(j).getSlotForLab();
                    if ((oneTheory.contains(twoTheory) || oneTheory.contains(twoLab) || oneLab.contains(twoTheory) || oneLab.contains(twoLab))
                            && (j != index)) {
                        if (cOneLevel < cTowLevel) {
                            departSchedule.remove(j);
                        } else {
                            departSchedule.remove(i);
                        }

                    }
                }
            }

        }

    }

    private static Course[] departCourses;
    private static ArrayList<FCITMember> departMem = new ArrayList<FCITMember>();
    private static ArrayList<Section> departSchedule = new ArrayList<Section>();

    public FCITeAA(ArrayList<FCITMember> departMem, Course[] departCourses, ArrayList<Section> departSchedule) {
        this.departMem = departMem;
        this.departCourses = departCourses;
        this.departSchedule = departSchedule;
    }

    public ArrayList<FCITMember> getDepartMem() {
        return departMem;
    }

    public void setDepartMem(ArrayList<FCITMember> departMem) {
        this.departMem = departMem;
    }

    public Course[] getDepartCourses() {
        return departCourses;
    }

    public void setDepartCourses(Course departCourses, int index) {
        this.departCourses[index] = departCourses;
    }

    public ArrayList<Section> getDepartSchedule() {
        return departSchedule;
    }

    public void setDepartSchedule(ArrayList<Section> departSchedule) {
        this.departSchedule = departSchedule;
    }

}
