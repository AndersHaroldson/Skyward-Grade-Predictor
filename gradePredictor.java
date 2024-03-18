import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.println("Please enter the class name: ");
    String className = input.next();

    System.out.println("Please enter the number of sections in class (assessments, daily work, etc): ");
    int numSections = input.nextInt();

    // Iterate over each section of the class
    double total = 0;
    for (int i = 0; i < numSections; i++) {
      System.out.println("Section #" + i);
      total += subSection();
    }
    // Output the final predicted result for the class
    System.out.println("---------------------------------------------------------");
    System.out.println("Your grade for " + className + " will be approximately " + total * 100 + "%");
    System.out.println("---------------------------------------------------------");
    input.close();
  }

  public static double subSection() {
    double sectionTotal = 0;
    Scanner input = new Scanner(System.in);

    System.out.println("Enter the current grade in this section as a decimal (100% = 1, 92% = .92): ");
    double currentGrade = input.nextDouble();

    System.out.println("Enter the total weight for this section as a decimal (15% = .15): ");
    double totalWeight = input.nextDouble();

    System.out.println("Do you want to predict a grade in this section? (yes/no): ");
    String answer = input.next();
    // If the user wants to predict a grade, then calculate appropriately based on assignment inputs.
    // If the user does not want to predict a grade, it will simply be the current grade and its weight on the overall grade.
    if (answer.contains("yes")) {
      System.out.println("Enter the total number of points possible in this section: ");
      double maxPoints = input.nextDouble();
      double currentPoints = currentGrade * maxPoints;

      System.out.println("Enter the total number of points possible on the future assignment: ");
      double maxPointsOfAssignment = input.nextDouble();
      
      System.out.println(
          "  > Type 1 if you want to give predicted points on assignment \n  > Type 2 if you want to give predicted percentage on assignment");
      int choiceType = input.nextInt();
      // Choice #1 will allow the user to input the predicted/desired points on the assignment & calculate grade appropirately
      if (choiceType == 1) {
        System.out.println("Enter the points you want to get on the assignment: ");
        double desiredGradePoints = input.nextDouble();
        System.out.println("**" + desiredGradePoints + "/" + maxPointsOfAssignment + " on the assignment is "
            + (desiredGradePoints / maxPointsOfAssignment)*100 + "%");
        double predictedOverallPercent = (currentPoints + desiredGradePoints) / (maxPoints + maxPointsOfAssignment);
        return predictedOverallPercent * totalWeight;
      } 
      // Choice #2 will allow the user to input the predicted/desired percentage on the assignment & calculate grade appropirately
      else if (choiceType == 2) {
        System.out.println("Enter the percentage you want on the assignment (100% = 1, 92% = .92): ");
        double desiredGradePercentage = input.nextDouble();
        double desiredGradePoints = desiredGradePercentage * maxPointsOfAssignment;
        System.out.println("**Here would be the points: " + desiredGradePoints + "/" + maxPointsOfAssignment
            + " to get " + desiredGradePercentage + "%");

        double predictedOverallPercent = (currentPoints + desiredGradePoints) / (maxPoints + maxPointsOfAssignment);
        return predictedOverallPercent * totalWeight;
      }

    }

    sectionTotal = currentGrade * totalWeight;
    return sectionTotal;
  }

}
