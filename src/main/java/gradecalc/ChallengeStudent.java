package gradecalc;

public class ChallengeStudent extends Student{
    
    private double projectGrade;
    private double finalScore;
    private static final double PERCENTAGE = 0.5;

    /**
     * Default constructor
     */
    public ChallengeStudent() {
        projectGrade = 0;
        finalScore = 0;
    }

    /**
     * Sets the project grade
     * @param newProjectGrade
     */
    public void setProjectGrade(double newProjectGrade) {
        projectGrade = newProjectGrade;
    }

    /**
     * 
     * @return returns the project grade
     */
    public double getProjectGrade() {
        return projectGrade;
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

    /**
     * Calculates the final grade
     */
    public void calcFinalGradeChallengeStudent() {
        double finalGrade = projectGrade * PERCENTAGE + finalScore * PERCENTAGE;
        int finalWithoutDecimal = (int) finalGrade;
        double finalDouble = (double) finalWithoutDecimal;
        this.setFinalGrade(finalDouble);
    }

}
