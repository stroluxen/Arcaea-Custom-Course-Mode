import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("strolux's Arcaea Custom Course Mode");

        // used for terminating the code if it is false
        boolean exit = false;

        int choice;

        // Loading all the Song Files from the .csv file
        ArrayList<String[]> songList = new ArrayList<>();

        File songFile = new File("Arcaea Song List (v6.10.8).csv");

        // Adding all Arcaea songs (as of v6.10.8) to an Array of arrays of Strings in this format
        // Song Title,Composer(s),BPM,Difficulty Level, Difficulty Chart Constant, Difficulty Note Count
        // Difficulty: Past (3-5), Present (6-8), Future (9-11), Eternal (12-14), Beyond (15-17)
        try (Scanner fileScan = new Scanner(songFile)){
            while (fileScan.hasNextLine()){
                String[] song = fileScan.nextLine().split(",");

                songList.add(song);
            }
        } catch (Exception e) {
            System.err.print(e);
        }

        // This is how to print an index of String Arrays.
        // Get the array from the ArrayList and assign that to a String[] variable.
        // Then, I now have access to the index of the String[]
        //String[] test = songList.get(0);
        //System.out.println(test[0]);

        // Main Menu
        while(!exit){
            System.out.println("=========================================");
            System.out.println("1) Play");
            System.out.println("2) About");
            System.out.println("3) Exit");
            System.out.print("Enter your choice. ");
            choice = Integer.parseInt(input.nextLine());
            System.out.println();

            switch(choice){
                case 1:
                    String format = "%-10s %-4s %-4s %-4s %-4s\n";
                    System.out.println("Courses: Levels");

                    // Phase Numbers, Song Difficulties
                    System.out.printf(format, "Phase 1:", "4", "5", "6", "6"); // Past/Present
                    System.out.printf(format, "Phase 2:", "7", "7+", "7+", "8"); // Present/Future
                    System.out.printf(format, "Phase 3:", "7+", "8", "8", "8+"); // Present/Future/Eternal
                    System.out.printf(format, "Phase 4:", "8", "8+", "9", "9"); // Present/Future/Eternal/Beyond
                    System.out.printf(format, "Phase 5:", "9", "9", "9", "9+"); // Future/Eternal/Beyond
                    System.out.printf(format, "Phase 6:", "9", "9+", "9+", "9+");
                    System.out.printf(format, "Phase 7:", "9+", "9+", "10", "10");
                    System.out.printf(format, "Phase 8:", "9+", "10", "10", "10");
                    System.out.printf(format, "Phase 9:", "10", "10", "10", "10");
                    System.out.printf(format, "Phase 10:", "10+", "10+", "10+", "11");
                    System.out.printf(format, "Phase 11:", "10+", "11", "11", "11");
                    System.out.printf(format, "Phase 12:", "11", "11", "11", "11");
                    System.out.println("Random"); // lol
                    System.out.println("=========================================");
                    System.out.print("Select which Course you would like to play. ");

                    String courseID = input.nextLine();

                    while (!Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "Random").contains(courseID)){
                        System.out.print("Invalid choice. Choose a number between 1 to 12, or \"Random\". ");
                        courseID = input.nextLine();

                    }

                    if (courseID.equalsIgnoreCase("Random")){
                        courseID = "13";
                    }

                    System.out.println();
                    new CourseMode(songList, courseID);
                    System.out.println("Press Enter to return back to the menu");
                    input.nextLine();

                    continue;
                case 2:
                    System.out.println("About:");
                    System.out.println("This is a small project by strolux to imitate Arcaea's Course Mode.");
                    System.out.println("All of the suggested phases are picked randomly based on the chart's level.");
                    System.out.println("Version 0.0.1");
                    System.out.println("Press Enter to return to the menu");
                    input.nextLine();

                    continue;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Option: Try Again");

            }
        }
    }
}