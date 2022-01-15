package gradecalc;

import java.util.ArrayList;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Roster {

    private ArrayList<Student> studentList;

    /**
     * Default constructor
     */
    public Roster() {
        studentList = new ArrayList<Student>();
    }

    /**
     * Constructor for JSON String
     * @param JSONFilename
     */
    public Roster(String JSONFilename) { //FIXME: This needs to initialize elements using the JSON

        studentList = new ArrayList<Student>();
        this.getDataFromJSON(JSONFilename);
    }

    /**
     * Helper method to parse challengeStudent data
     * @param thisStudent
     * @return returns the student object
     */
    private Student challengeStudent(JSONObject thisStudent) {
        ChallengeStudent holder = new ChallengeStudent();
        Number finalscore = (Number) thisStudent.get("examGrade");
        Double finalScoreDouble =  finalscore.doubleValue();
        Number projectScore = (Number) thisStudent.get("projectGrade");
        Double projectScoreDouble = projectScore.doubleValue();
        holder.setFinalScore(finalScoreDouble);
        holder.setProjectGrade(projectScoreDouble);
        Student current = (ChallengeStudent) holder;
        holder.calcFinalGradeChallengeStudent();
        return current;
    }

    private void projectMilestoneSetter(JSONObject thisStudent, ProjectStudent holder) {
        JSONArray milestoneArray = (JSONArray) thisStudent.get("milestoneGrades");
        if (milestoneArray != null) {
            for (Object milestoneGrade : milestoneArray) {
                Number newMilestoneGrade = (Number) milestoneGrade;
                holder.addToProjectList(newMilestoneGrade.doubleValue());
            }
        }
    }

    private void projectStudentResearch(JSONObject thisStudent, ProjectStudent holder) {
        if (thisStudent.get("participating").equals(true)) {
            holder.setParticipating(true);
        }
    }

    /**
     * Helper method to parse projectStudent data
     * @param thisStudent
     * @return returns a student object
     */
    private Student projectStudent(JSONObject thisStudent) {
        ProjectStudent holder = new ProjectStudent();
        Number finalExam = (Number) thisStudent.get("examGrade");
        Number midterm = (Number) thisStudent.get("midtermGrade");
        projectStudentResearch(thisStudent, holder);
        holder.setFinalScore(finalExam.doubleValue());
        holder.setMidterm(midterm.doubleValue());
        projectMilestoneSetter(thisStudent, holder);
        holder.calcFinalGradeProjectStudent();
        Student current = (ProjectStudent) holder;
        return current;
    }

    private void makeQuizList(JSONObject thisStudent, AssignmentStudent holder) {
        //Gets quizzes
        JSONArray quizArray = (JSONArray) thisStudent.get("quizGrades");
        if (quizArray != null) {
            for (Object quizGrade : quizArray) {
                Number newQuizGrade = (Number) quizGrade;
                holder.addToQuizList(newQuizGrade.doubleValue());
            }
        }
    }

    private void makeAssignmentList(JSONObject thisStudent, AssignmentStudent holder) {
        JSONArray assignmentArray = (JSONArray) thisStudent.get("assignmentGrades");
        if (assignmentArray != null) {
            for (Object assignmentGrade : assignmentArray) {
                Number newAssignmentGrade = (Number) assignmentGrade;
                holder.addToAssignmentList(newAssignmentGrade.doubleValue());
            }
        }
    }

    private void assignmentStudentResearch(JSONObject thisStudent, AssignmentStudent holder) {
        if (thisStudent.get("participating").equals(true)) {
            holder.setParticipating(true);
        }
    }

    /**
     * Helper method for assignmentStudent parsing
     * @param thisStudent
     * @return returns a student object
     */
    private Student assignmentStudent(JSONObject thisStudent) {
        AssignmentStudent holder = new AssignmentStudent();
        //Gets final score
        Number finalGrade = (Number) thisStudent.get("examGrade");
        holder.setFinalScore(finalGrade.doubleValue());
        //Gets research participation
        assignmentStudentResearch(thisStudent, holder);
        makeQuizList(thisStudent, holder);
        makeAssignmentList(thisStudent, holder);
        holder.calcFinalGradeAssignmentStudent();
        Student current = (AssignmentStudent) holder;
        return current;
    }

    private Student setStream(JSONObject thisStudent, Student current) {
        if (thisStudent.get("stream").equals("challenge")) {
            current = challengeStudent(thisStudent);
        } else if (thisStudent.get("stream").equals("project")) {
            current = projectStudent(thisStudent);
        //Deals with the assignment stream student
        } else if (thisStudent.get("stream").equals("assignment")) {
            current = assignmentStudent(thisStudent);
        } else {
            current = new Student();
        }
        return current;
    }

    private void pickAndSetStream(JSONObject studentObject, Student current) {
        JSONArray students = (JSONArray) studentObject.get("students");
        if (students != null) {
            for (Object currentStudent : students) {
                JSONObject thisStudent = (JSONObject) currentStudent;
                current = setStream(thisStudent, current);
                String currentName = (String) thisStudent.get("name");
                current.setStudentName(currentName);
                studentList.add(current);
            }
        } 
    }

    /**
     * Helper method to create and set data to different student objects
     * @param JSONFilename
     */
    private void getDataFromJSON(String JSONFilename) { 

        JSONParser studentParser = new JSONParser();
        JSONObject studentObject = null;
        try(Reader studentReader = new FileReader(JSONFilename)) {
            //Gets overall JSON Object
            studentObject = (JSONObject) studentParser.parse(studentReader);
        } catch (Exception e) {
            System.out.println("File not found. Please enter a valid file.");
        }
        //Assume the JSONObject has been made by this point
        //Starting to parse the JSON
        Student current = null;
        pickAndSetStream(studentObject, current);
        
    }

    /**
     * 
     * @return returns the arraylist of students
     */
    public ArrayList<Student> getStudents() {
        return studentList;
    }

    /**
     * Sets a new list of students
     * @param newStudentList
     */
    public void setStudentList(ArrayList<Student> newStudentList) {
        studentList = newStudentList;
    }

    /**
     * Adds a student to the list
     * @param theNewStudent
     */
    public void addToStudentList(Student theNewStudent) {
        studentList.add(theNewStudent);
    }


    public static void main(String[] args) {
        Roster list = new Roster("roster.json");
        for (Student current : list.studentList) {
            System.out.println(current.toString());
        }
    }
}
