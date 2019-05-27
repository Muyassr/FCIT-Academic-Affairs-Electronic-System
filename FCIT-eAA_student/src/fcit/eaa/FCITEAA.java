/*
 * CPCS 203 Programming2 Course
 * Assignment4 [Inheratance - Polymorphism - Abstract - Interfaces]
 */
package fcit.eaa;

/**
 *
 * @author Dr. E. A. Fadel
 */
import java.io.*;
import java.util.*;

public class FCITEAA {
    // Class Data
    private static Course[] deprtCourses;
    private static int numOfCourses = 0;
    private static String command;
    private static ArrayList<FCITMember> deprtMember =  new ArrayList<FCITMember>();
    private static ArrayList<Section> deprtSchedule =  new ArrayList<Section>();
    
    

    public static void main(String[] args) throws Exception{
        // Application DataEntry 
        
        //Prepare Input/Output Files
        
        File fcitData = new File("infciteaa.txt");
        File fcitReportData = new File("outfciteaa.txt");
        Scanner inputData = new Scanner(fcitData);
        PrintWriter outputData = new PrintWriter(fcitReportData);
        outputData.print("Programming Assignment 4 _______");
        outputData.println(new Date().toString());
        
        String beginingOfLine, stringItem1, stringItem2;
        int intItem1, intItem2, i, intDataItem,intItem3;
        boolean theory, lab;
        
        
        while(inputData.hasNext()){
        command = inputData.next(); //Read Command from file
        
        //Get Course Data
        if(command.equalsIgnoreCase("InputDepartmentPlan")){
        numOfCourses = inputData.nextInt();
        deprtCourses = new Course[numOfCourses];
        i = 0; 
        intDataItem = 1;
        
        while (i < numOfCourses) {     // Loop to read all courses in plan.
        beginingOfLine = inputData.next();
        if(beginingOfLine.equalsIgnoreCase("Level"))    {
        intDataItem = inputData.nextInt();
        
        stringItem1 = inputData.next();
        }
        else
        stringItem1 = beginingOfLine;
        intItem1 = inputData.nextInt();
        stringItem2 = inputData.next().replace('?', ' ');             
        intItem2 = inputData.nextInt();
        theory = inputData.next().equalsIgnoreCase("theory");
        lab = inputData.next().equalsIgnoreCase("lab");
        deprtCourses[i] = new Course(stringItem1,intItem1,stringItem2, intDataItem,
                intItem2,theory,lab);
        
        i++; // Increase Course counter.    
        }// End of while courses
        outputData.println("Department Plan(Courses) Data has been Inserted");
        outputData.println("============================================================");
        }// End of reading course data
        else if(command.equalsIgnoreCase("InputInstructorData")){
        intDataItem = inputData.nextInt(); i = 0;
        while (i < intDataItem){ //loop to read all instructor data.
        stringItem2 = inputData.next().replace('?', ' ');
        intItem1 = inputData.nextInt();
        intItem2 = inputData.nextInt(); // Read starting year.
        stringItem1 = inputData.next().replace('?', ' '); // read qualification.
        deprtMember.add(new Instructor(stringItem1, stringItem2, intItem1,
                      intItem2)); i++;} 
        
        outputData.println("Department Instructor's Data has been Inserted");
        outputData.println("============================================================");
        }// end of get instructor data
        else if(command.equalsIgnoreCase("InputStudentData")){
        intDataItem = inputData.nextInt(); i = 0; 
        double gpa; int thours;
        while (i < intDataItem){ //loop to read all student data.
        stringItem2 = inputData.next().replace('?', ' ');// Read name
        intItem1 = inputData.nextInt();// Read ID
        intItem2 = inputData.nextInt(); // Read starting year.
        gpa = inputData.nextDouble();
        thours = inputData.nextInt();
        String[][] plan = null;
        if(inputData.next().equalsIgnoreCase("N/A"))
        plan = null;
        deprtMember.add(new Student (gpa, thours, plan, stringItem2, intItem1, intItem2));        
        i++; // Next Student
        }// end of while student    
        Student t=null;
        
        outputData.println("Department Students Data has been Inserted");
        outputData.println("============================================================");            
        }// End of get student data 
        else if(command.equalsIgnoreCase("InputSectionData")){
        intDataItem = inputData.nextInt(); i = 0; 
        int s; String stringItem3, stringItem4, stringItem5, stringItem6;
        while (i < intDataItem){ //loop to read all section data.
        intItem1 = inputData.nextInt();// Read Section ID
        stringItem1 = inputData.next(); // section name.
        stringItem2 = inputData.next(); // section code.
        intItem2 = inputData.nextInt();
        s = inputData.nextInt(); // read size.
        stringItem3 = inputData.next().replace('?', ' '); //read slot
        stringItem4 = inputData.next().replace('?', ' '); // read lab slot
        stringItem5 = inputData.next(); // read room number
        stringItem6 = inputData.next(); // read lab number
        Course c=null;
        for(int j = 0; j< deprtCourses.length;j++){
          if(deprtCourses[j].getcCode().equals(stringItem2) && deprtCourses[j].getcNum() == intItem2) {
            c = deprtCourses[j]; break;}
          else
            c = null;}
        deprtSchedule.add(new Section(intItem1, stringItem1, s, c, stringItem3,
                stringItem4, stringItem5, stringItem6));
        i++;
        }// End of read all section data    
        
        outputData.println("Department Schedule (Sections) has been Inserted");
        outputData.println("============================================================");
        }// End of get section data
        
        /* 
         The following commands are the commands that process the entered data
         */        
        
         else if(command.equalsIgnoreCase("RegisterCourse")){
         // Temporary Variables.
         Student temp = null; 
         int tRegCourses = 0; i=0;
         int tRegHours = 0;
         int aSize;
         Student[] stdList;
        
         // Read the total number of registration requests
         intItem1 = inputData.nextInt();
         
         // Read and Process First Request.
         while(i < intItem1){         
         // Get Student info
         intItem2 = inputData.nextInt();
         for(int t = 0; t<deprtMember.size();t++){    
           if(deprtMember.get(t) instanceof Student){
           temp = (Student)deprtMember.get(t);
           if( temp.getMemID() == intItem2)
             break; }
         }                  
           if(temp != null){ // if a Student was found
            // Read All Allowed Section Registeration Requests
            intItem2 = inputData.nextInt();
            int j = 0;
            Section[] schedule = new Section[7];
            while(intItem2 != -1){
             for(int t = 0; t < deprtSchedule.size();t++) 
             if(j <= 7){
             if(intItem2 == deprtSchedule.get(t).getSecID()){ // Get Section Information
               schedule[j] = deprtSchedule.get(t); // Add section to Student Schedule
               tRegCourses = j; //upate total number of courses.
               tRegHours += deprtSchedule.get(t).getCourseInfo().getcCredit();
               
               // Add student to Section Student List
               stdList = deprtSchedule.get(t).getStdList();
               aSize = deprtSchedule.get(t).getSecActualSize();
               stdList[aSize] = temp;
               deprtSchedule.get(t).setStdList(stdList);
               aSize += 1;
               deprtSchedule.get(t).setSecActualSize(aSize);
               j++;                 
             }
             }
            else 
            outputData.println(deprtSchedule.get(t).getCourseInfo().getcName()+
                    "The Requested Course has exceeded the Maximum allowed");        
            // Get Next Course
            intItem2 = inputData.nextInt();
            }
            // add registered table
            
            String regReport = generateReport(temp, schedule);
            
            // Check for Contradiction
               int courseForDelete = 0;
               boolean delete = false;
               String c1 = null, c2 = null;
               for (int x = 0; x <= tRegCourses; x++) {
                   for (int y = 0; y <= tRegCourses; y++) {
                       if (x != y) {
                           if (schedule[x].slotContradicts(schedule[y].getSlotForTheory(), schedule[y].getSlotForLab())) {
                               c1 = schedule[x].getSecName();
                               c2 = schedule[y].getSecName();
                               courseForDelete = x;
                               delete = true;
                               break;
                           }
                       }
                   }
               }
        if(delete) {
        regReport = regReport + "\r\n The following two Sections Contradict"
                + " in Slots " + c1 +"  "+ c2 ;
        schedule[courseForDelete] = null;tRegCourses--; }
        
        //Record Number of courses regestered
        temp.setTotalRegCourses(tRegCourses);
        
        // Print Report to file
            outputData.println("Student: " + temp.getMemName() + "   stdID: " +
                    temp.getMemID()+"  Total Credit Hours: "+tRegHours);
            outputData.printf("%10s%10s%10s%10s%10s", "SecId",
                    "SecName", "Course", "Time", "Location" );
            outputData.println();
            for(int z=0; z<= tRegCourses;z++){
            outputData.printf("%10s%10s%5s%5d%10s%10s%5s%5s", schedule[z].getSecID(),
                    schedule[z].getSecName(),
                    schedule[z].getCourseInfo().getcCode(),
                    schedule[z].getCourseInfo().getcNum(),
                    schedule[z].getSlotForTheory(), schedule[z].getSlotForLab(),
                    schedule[z].getSecRoomNo(),schedule[z].getSecLabNo());    
            outputData.println();}
         outputData.println("__________________________________________________________");
         outputData.println("Registration Validity Report");
            
            outputData.println(regReport);
            temp.setsSchedule(schedule);
           }
            else
             outputData.println("The Member with ID: " + deprtMember.get(intItem2).memID
                     + " is not a student. Therefore Registration can not be completed.");
            i++;  tRegHours = 0;
         outputData.println("----------------------------------------------------------");
         }//End of while register requests exist
         outputData.println("==========================================================");
         }// End of Register Course
        else if(command.equalsIgnoreCase("InstructorLoadRequest")){
         outputData.println("Instructor Load Requests");
         Section[] instLoad,instActualLoad; Instructor tempInst;
         intItem1 = inputData.nextInt();         
         i=0; int loadInCourses;
         instLoad = new Section[10]; //temporary array to read load in.
         while(i < intItem1){
         intItem2 = inputData.nextInt();
         for(int k =0; k<deprtMember.size();k++)
         {
          //Find Instructor
          if(deprtMember.get(k) instanceof Instructor){
            tempInst = (Instructor) deprtMember.get(k);
          if(tempInst.getInstructorID() == intItem2){
            intItem3 = inputData.nextInt();
            loadInCourses = 0;
            //Find Course and Check for more Courses
            while(intItem3 != -1){
            for(int k1=0; k1< deprtSchedule.size();k1++){
               if(deprtSchedule.get(k1).getSecID() == intItem3){
                 deprtSchedule.get(k1).setSecInstructor(tempInst);
               if(deprtSchedule.get(k1) instanceof Section)  
               instLoad[loadInCourses] = (Section)deprtSchedule.get(k1);
               loadInCourses++; break;}
            }
            // Get Next Section
            intItem3 = inputData.nextInt();
            }
            instActualLoad = new Section[loadInCourses];
            for(int k2=0; k2 < loadInCourses;k2++)
                instActualLoad[k2] = instLoad[k2];
            tempInst.setInstSchedule(instActualLoad);
            // Print to file instructor assignement
        outputData.println();
        outputData.println("Instructor: " + tempInst.getInstructorName() + 
                " Instructor ID: " + tempInst.getInstructorID() +
                " Total Number of Courses: "+ loadInCourses);
        outputData.printf("%10s%10s%10s%10s%10s", "SecId",
                    "SecName", "Course", "Time", "Location" );
        outputData.println();
        Section[] tempSchd;
        tempSchd = tempInst.getInstSchedule();
        for(int z1=0; z1< instActualLoad.length;z1++){
        outputData.printf("%10s%10s%5s%5d%10s%10s%5s%5s",tempSchd[z1].getSecID(),
                    tempSchd[z1].getSecName(),
                    tempSchd[z1].getCourseInfo().getcCode(),
                    tempSchd[z1].getCourseInfo().getcNum(),
                    tempSchd[z1].getSlotForTheory(), tempSchd[z1].getSlotForLab(),
                    tempSchd[z1].getSecRoomNo(),tempSchd[z1].getSecLabNo());    
        outputData.println();}
        outputData.println("------------------------------------------------------------");
          }// end of found instructor                
          }// check if element is Instructor
         }
         //Next Request             
         i++;
         }   
        outputData.println("==========================================================");
        }
        else if(command.equalsIgnoreCase("PrintAllInstructorsLoads")){
        
        outputData.println();
        outputData.println("All Instructors Sorted Load List ");
        outputData.println("_________________________________");
        outputData.println();
        
         Instructor inst;
         Collections.sort(deprtMember);
         for(int o = 0; o < deprtMember.size(); o++)
            if(deprtMember.get(o) instanceof Instructor){
            inst = (Instructor)deprtMember.get(o);
        outputData.println("Instructor: " + inst.getInstructorName() + 
                " Instructor ID: " + inst.getStartingYear());
        outputData.printf("%10s%10s%10s%10s%10s", "SecId",
                    "SecName", "Course", "Time", "Location" );
        outputData.println();
        Section[] tempSchd;
        tempSchd = inst.getInstSchedule();
        if(tempSchd != null){
        for(int z1=0; z1 < tempSchd.length  ;z1++){
        outputData.printf("%10s%10s%8s%5d%12s%10s%5s%5s",tempSchd[z1].getSecID(),
              tempSchd[z1].getSecName(),
              tempSchd[z1].getCourseInfo().getcCode(),
              tempSchd[z1].getCourseInfo().getcNum(),
              tempSchd[z1].getSlotForTheory(), tempSchd[z1].getSlotForLab(),
              tempSchd[z1].getSecRoomNo(),tempSchd[z1].getSecLabNo());    
        outputData.println();}}
        
            
            } //end of print to file one instructor

            
        outputData.println("==========================================================");
         
        }// End of Print All instructors
        else if(command.equalsIgnoreCase("PrintSectionStudentList")){
            Section tempSec = null;
            Student[] tempStd = null; 
            int totalStdNum = 0;
            
            intItem1 = inputData.nextInt(); 
            while(intItem1 != -1){
             
             //Get Section info.
             for(int n=0;n < deprtSchedule.size();n++ ){
              if(intItem1 == deprtSchedule.get(n).getSecID()){
                tempSec =  deprtSchedule.get(n); break;
             }//End of if
             }// End of find section information.  
             
             tempStd = tempSec.getStdList();
             if(tempStd != null){
             //Print Student List
             outputData.println();
             outputData.print("Course: " + tempSec.getCourseInfo().getcName()
                               +" "+ tempSec.getCourseInfo().getcNum()
                               +"                 ");
             outputData.println("Section: " + tempSec.getSecName());
             outputData.println("Day and Time: " + tempSec.getSlotForTheory()
                              +" "+ tempSec.getSlotForLab());
             outputData.println("____________________________________________________");
             totalStdNum = tempSec.getSecActualSize();
             for(int n=0; n < totalStdNum ;n++)
             outputData.println(tempStd[n].getMemID() +"  "+ tempStd[n].getMemName());
             }//End of print data
             else
                 outputData.println("No Students Found");
             
             intItem1 = inputData.nextInt(); 
            }//End of While != -1
            outputData.println("==========================================================");
        }//End of PrintSectionStudentList
        }//End of reading inputfile.        
        // Closing Data Files
        inputData.close();
        outputData.close();
    }// End of Main
    
    public static String generateReport(Student s, Section[] ss){
        StringBuilder report = new StringBuilder();
        String stat = s.getStatus(); int[] minLevels = {0,0};
        int coursesRegistered = 0;
        
        for(int x = 0; x<ss.length;x++)
          if(ss[x] != null)
          coursesRegistered+=1;
          else break;
        
        // Check for under 12 hours
        int registeredCredit = 0; int count =0; boolean found = false;
        while(ss[count]!=null){
        registeredCredit += ss[count].getCourseInfo().getcCredit();
        count++; 
        }
       
        if(registeredCredit < 12)
        report.append("\r\n Student Registered Credit is less than 12 Credits Must ADD courses");
        
        //Check for CPCS 498
        if(stat.equals("Graduate")){
          for(int x=0; x<coursesRegistered; x++)
         if(ss[x].getCourseInfo().getcCode().equals("CPCS") && ss[x].getCourseInfo().getcNum() == 498)
               found = true;
          
          if(!found)     
            report.append("\r\n Student is a Graduate and did not Register CPCS 498");
        }
        
          //Check for cross-level
          minLevels[0] = ss[0].getCourseInfo().getcLevel();
          
        for(int x=1; x<coursesRegistered; x++){
            
        if (minLevels[0] != ss[x].getCourseInfo().getcLevel()){
           minLevels[1] = ss[x].getCourseInfo().getcLevel();
           break;}}
        
        if(minLevels[1] != 0){
            report.append("\r\n Student has registered course from two different levels");
            report.append("\r\n The Student is a " + s.getStatus());
        }

        return report.toString();
    }// End of generate report
}
