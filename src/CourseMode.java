import java.util.ArrayList;
public class CourseMode {

    // This is how to initialize a 2D Array
    // Corresponding Course Levels for each Phase to check if the song has the right level
    private final String[][] courseLevels = {
            {"4", "5", "5", "6"},   // Course 1
            {"7", "7+", "7+", "8"},   // Course 2
            {"7+", "8", "8", "8+"},  // Course 3
            {"8", "8+", "9", "9"},   // Course 4
            {"9", "9", "9", "9+"},  // Course 5
            {"9", "9+", "9+", "9+"},  // Course 6
            {"9+", "9+", "10", "10"},  // Course 7
            {"9+", "10", "10", "10"},  // Course 8
            {"10", "10", "10", "10"},  // Course 9
            {"10+", "10+", "10+", "11"},  // Course 10
            {"10+", "11", "11", "11"},  // Course 11
            {"11", "11", "11", "11"},   // Course 12
            {"?", "?", "?", "?"} // Random (courseID: "13")
    };

    private String[] randomRoster; // This is used with variable r to get the songData
    private int randomIndex; // random number and is placed here for scope
    private int courseRosterCounter = 0; // A counter on how many songs have been added in courseRoster

    // After randomRoster gets the data from index r of songList, the index is added in the songsChosen array
    private ArrayList<Integer> songsChosen = new ArrayList<>();

    public CourseMode(ArrayList<String[]> songList, String courseID) {

        // This for-loop runs until courseRoster has all four songs that
        // are not duplicates and in the difficulty range.

        String[][] courseRoster = new String[4][];

        // Main for-loop that assigns the Course Songs
        for (int i = 0; i < 4; i++) {

            // This if-statement is randomly picking the level number for the roster
            if (courseID.equalsIgnoreCase("13")){
                boolean addPlus = false;

                for (int j = 0; j < 4; j++){
                    int randomLevels = (int) (1 + (Math.random() * 12));

                    if (randomLevels >= 7 && randomLevels < 12){
                        addPlus = (int) (Math.random() * 1) == 1;
                    }

                    courseLevels[12][j] = Integer.toString(randomLevels);

                    if (addPlus) {
                        courseLevels[12][j] = courseLevels[12][j] + "+";
                    }
                }
            }

            // Selecting the song and keeps running until it finds a valid song
            do {
                randomIndex = (int) (Math.random() * 506);
                randomRoster = songList.get(randomIndex);
                songsChosen.add(randomIndex);

            } while (!diffCheck(courseLevels, randomRoster, courseID, courseRosterCounter, randomIndex, songsChosen));

            courseRoster[i] = randomRoster;

            System.out.println();
            courseRosterCounter++;
        }
    }

    public static boolean diffCheck(String[][] courseLevels, String[] roster, String courseID,
                                    int courseRosterCounter, int randomIndex, ArrayList<Integer> songsChosen) {

        // This code runs a for-loop if the song has the matching difficulty level.
        // The reason why it is capped at 5 because it is comparing the Past, Present, Future, Eternal, and Beyond
        for (int i = 0; i < 5; i++) {


            // If the song has the corresponding difficulty level, it runs another function if it has already
            // been checked with the songChosen index
            if (roster[(i + 1) * 3].equals(courseLevels[Integer.parseInt(courseID) - 1][courseRosterCounter])) {

                // If the song is not a dupe then it is added to the list
                if (!isDupe(randomIndex, songsChosen)){

                    System.out.println("Song " + (courseRosterCounter + 1) + ": " + roster[1] + " - " + roster[0]);
                    System.out.println("Difficulty: " + roster[((i + 1) * 3)] + " (" + roster[((i + 1) * 3) + 1] + ")");
                    return true;
                } else {
                    return false;
                }
            }
        }
        // None of the difficulties in the song have the level needed so it runs the while loop again
        return false;

    }
    public static boolean isDupe (int r, ArrayList<Integer> songsChosen){
        // Goes through the ArrayList of index and checks if r (the randomly selected roster is in the ArrayList)
        // if it is true, there is a dupe
        for (int i = 0; i < songsChosen.size()-1; i++){
            if (r == songsChosen.get(i)) return true;
        }
        return false;
    }

}