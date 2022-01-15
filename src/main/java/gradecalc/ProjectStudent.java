package gradecalc;

import java.util.ArrayList;

public class ProjectStudent extends Student implements ResearchSubject {

    private boolean researching;
    private double bonus;
    private double midterm;
    private double finalScore;
    private ArrayList<Double> projectList;
    private static final double EXAM_AND_MIDTERM = 0.2;
    private static final double ASSIGNMENT_PERCENTAGE = 0.05;
    private static final double MAX_GRADE = 100;
    private static final double PROJECT_PERCENTAGE = 0.2;

    /**
     * Default constructor
     */
    public ProjectStudent() {
        researching = false;
        bonus = 0;
        midterm = 0;
        finalScore = 0;
        projectList = new ArrayList<Double>();
    }

    /**
     * Sets to true if student participates in research
     * @param isParticipating 
     */
    public void setParticipating(boolean isParticipating) {
        researching = isParticipating;
    }

    /**
     * 
     * @return returns the boolean participating value
     */
    public boolean getParticipating() {
        return researching;
    }

    /**
     * Returns the bonus marks earned
     * @return returns the bonus research grade
     */
    public double researchPerk() { //FIXME: unused right now
        return bonus;
    }

    /**
     * Sets the bonus grade 
     * @param bonusGrade
     */
    public void setResearchPerk(double bonusGrade) {
        bonus = bonusGrade * ASSIGNMENT_PERCENTAGE;
    }

    /**
     * Sets the midterm grade
     * @param midtermScore
     */
    public void setMidterm(double midtermScore) {
        midterm = midtermScore;
    }

    /**
     * 
     * @return returns the midterm grade
     */
    public double getMidterm() {
        return midterm;
    }

    /**
     * Sets the final exam grade
     * @param grade
     */
    public void setFinalScore(double grade) {
        finalScore = grade;
    }

    /**
     * 
     * @return returns the final exam grade
     */
    public double getFinalScore() {
        return finalScore;
    }

    /**
     * Sets the project list
     * @param newList
     */
    public void setProjectList(ArrayList<Double> newList) {
        projectList = newList;
    }

    /**
     * 
     * @return returns the project list
     */
    public ArrayList<Double> getProjectList() {
        return projectList;
    }

    /**
     * adds to the project list
     * @param newGrade
     */
    public void addToProjectList(double newGrade) {
        projectList.add(newGrade);
    }

    private double dealWithMaxGrade(double finalgrade) {
        if (finalgrade > MAX_GRADE) {
            finalgrade = MAX_GRADE;
        }
        return finalgrade;
    }

    /**
     * calculates the final grade for the project student
     */
    public void calcFinalGradeProjectStudent() {
        //Midterm + final + projects + research if there was some
        double finalgrade = midterm * EXAM_AND_MIDTERM + finalScore * EXAM_AND_MIDTERM + calcProjectTotalGrade();
        if (researching) {
            //Adds the bonus 5%
            setResearchPerk(finalgrade);
            finalgrade = finalgrade + researchPerk();
            dealWithMaxGrade(finalgrade);
        }
        int finalWithoutDecimal = (int) finalgrade;
        double finalDouble = (double) finalWithoutDecimal;
        this.setFinalGrade(finalDouble);
    }

    /**
     * 
     * @return returns the project grade
     */
    private double calcProjectTotalGrade() {
        double totProjectGrade = 0;
        for (double currentProjectGrade : projectList) {
            totProjectGrade = totProjectGrade + currentProjectGrade * PROJECT_PERCENTAGE;
        }
        return totProjectGrade;
    }
}
