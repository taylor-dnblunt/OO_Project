package gradecalc;

public class Student {
    
    private String studentName;
    private double finalGrade;
    private static final double INITIALIZE = -99;

    /**
     * Default constructor
     */
    public Student() {
        studentName = "None";
        finalGrade = INITIALIZE;
    }

    /**
     * overriden toString() to get student name and grade
     */
    @Override
    public String toString() {
        String outputString = studentName + " " + finalGrade;
        return outputString;
    }

    /**
     * 
     * @return returns the final grade of the student
     */
    public double finalGrade() {
        return finalGrade;
    }

    /**
     * Sets the final grade
     * @param grade
     */
    public void setFinalGrade(double grade) {
        finalGrade = grade;
    }

    /**
     * sets the student name
     * @param name
     */
    public void setStudentName(String name) {
        studentName = name;
    }

    /**
     * 
     * @return returns the student name
     */
    public String getStudentName() {
        return studentName;
    }


}
