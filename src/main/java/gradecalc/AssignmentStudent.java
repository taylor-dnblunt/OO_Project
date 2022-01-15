package gradecalc;

import java.util.ArrayList;

public class AssignmentStudent extends Student implements ResearchSubject {
    
    private boolean researching;
    private ArrayList<Double> quizList;
    private ArrayList<Double> assignmentList;
    private double finalScore;
    private static final double EXAM_PERCENTAGE = 0.2;
    private static final double ASSIGNMENT_PERCENTAGE = 0.05;
    private static final double MAX_GRADE = 60;
    private static final double QUIZ_PERCENTAGE = 0.1;
    private double bonus;

    /**
     * Default constructor
     */
    public AssignmentStudent() {
        researching = false;
        quizList = new ArrayList<Double>();
        assignmentList = new ArrayList<Double>();
        finalScore = 0;
    }

    /**
     * Sets if the student is participating in research
     * @param isParticipating
     */
    public void setParticipating(boolean isParticipating) {
        researching = isParticipating;
    }

    /**
     * 
     * @return returns the participating boolean
     */
    public boolean getParticipating() {
        return researching;
    }

    /**
     * @return returns the bonus earned
     */
    public double researchPerk() { //FIXME: currently unused
        return bonus;
    }

    /**
     * Sets the bonus earned
     * @param finalgrade
     */
    public void setResearchPerk(double finalgrade) {
        bonus = finalgrade * ASSIGNMENT_PERCENTAGE;
    }

    /**
     * Sets the quiz list
     * @param newList
     */
    public void setQuizList(ArrayList<Double> newList) {
        quizList = newList;
    }

    /**
     * 
     * @return returns the quiz list
     */
    public ArrayList<Double> getQuizList() {
        return quizList;
    }

    /**
     * Adds to the quiz list
     * @param newQuizGrade
     */
    public void addToQuizList(double newQuizGrade) {
        quizList.add(newQuizGrade);
    }

    /**
     * Sets the assignment list
     * @param newAssignmentList
     */
    public void setAssignmentList(ArrayList<Double> newAssignmentList) { //FIXME: 10 assignments allowded max
        assignmentList = newAssignmentList;
    }

    /**
     * 
     * @return returns the assignment list 
     */
    public ArrayList<Double> getAssignmentList() {
        return assignmentList;
    }

    /**
     * adds to the assignment list
     * @param newAssignmentGrade
     */
    public void addToAssignmentList(double newAssignmentGrade) {
        assignmentList.add(newAssignmentGrade);
    }

    /**
     * Sets the final exam grade
     * @param newFinalScore
     */
    public void setFinalScore(double newFinalScore) {
        finalScore = newFinalScore;
    }

    /**
     * 
     * @return returns the final exam grade
     */
    public double getFinalScore() {
        return finalScore;
    }

    private double dealWithMaxGrade(double finalgrade) {
        if (finalgrade > MAX_GRADE) {
            finalgrade = MAX_GRADE;
        }
        return finalgrade;
    }

    /**
     * calculates the assignment grade
     */
    public void calcFinalGradeAssignmentStudent() {
        double finalgrade = finalScore * EXAM_PERCENTAGE + calcFinalQuizGrade() + calcFinalAssignmentGrade();
        if (researching) {
            //Adds the bonus 5%
            setResearchPerk(finalgrade);
            finalgrade = finalgrade + researchPerk();
        }
        finalgrade = dealWithMaxGrade(finalgrade);
        int finalWithoutDecimal = (int) finalgrade;
        double finalDouble = (double) finalWithoutDecimal;
        //Deals with assignment students only getting max 60%
        this.setFinalGrade(finalDouble);
    }

    /**
     * 
     * @return returns the total quiz grade
     */
    private double calcFinalQuizGrade() {
        double totQuizGrade = 0;
        for (double currentQuiz : quizList) {
            totQuizGrade = totQuizGrade + currentQuiz * QUIZ_PERCENTAGE;
        }
        return totQuizGrade;
    }

    /**
     * 
     * @return returns the total assignment grade
     */
    private double calcFinalAssignmentGrade() {
        double totAssignmentGrade = 0;
        for (double currentAssignment : assignmentList) {
            totAssignmentGrade = totAssignmentGrade + currentAssignment * ASSIGNMENT_PERCENTAGE;
        }
        return totAssignmentGrade;
    }
}
