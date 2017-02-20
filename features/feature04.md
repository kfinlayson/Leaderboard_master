# Create a graphical interface for the Leaderboard concept.

Assume GamegogyGUI.java creates a top-level window that looks like the following:

    +--------------------------------------------+
    |                  Gamegogy           - [] X |
    +--------------------------------------------+
    | Course [99011 V]     Column [Total      V] |
    |        |99012  |            |Assignment 1| |
    |        |99013  |            |Assignment 2| |
    |        |99014  |            |Assignment 3| |
    |        |99015  |            |Assignment 4| |
    |        |99016  |            |Assignment 5| |
    |        |99017  |            |Assignment 6| |
    |         -------              ------------  |
    | Term: Spring 2014    Enrollment: 7         |
    |                                            |
    +--------------------------------------------+
    |                                            |
    | **************************           722.0 |
    | **********************               617.0 |
    | **********************               616.0 |
    | ********************                 562.0 |
    | ******************                   514.0 |
    | ******************                   509.0 |
    | *****************                    484.0 |
    |                                            |
    +--------------------------------------------+
    | ID: 111345                                 |
    | Name: Alisa Shaw                           |
    | Email: ashaw@jsu.edu                       |
    | Score: 722.0                               |
    +--------------------------------------------+

This window has two dropdown lists (combo boxes). The first is populated with the available course IDs (defaulting to the first one in the list). The second one is populated with the column names from that course's grades (except for the student ID column). The first column is the default. When the course ID is changed, the column list is updated with the new names (always defaulting to the first in the list).

When a course is selected (including the initial default), its term and enrollment are displayed below the dropdown lists. When a column is selected (including the initial default), the student information for the scores in that column are used to create the Leaderboard. 

In the Leaderboard, the student scores for the selected assignment are sorted in descending order and represented as a bar graph. If a given bar is clicked (or the topmost bar by default), the student information (ID, name, email, and score) of that student is displayed in a panel at the bottom. 


## Custom Keywords

In your `keyword` library, you will need to define a way to click on individual bars of the Leaderboard. The following code could be used with little-to-no modification. This code only makes the assumption that the Leaderboard provides the following method:
    
    public Shape[] getShapes()
    
This method would return the rectangles that are actually drawn on the leaderboard in order from top to bottom (highest to lowest score). 

    package keywords;

    import org.robotframework.javalib.annotation.RobotKeyword;
    import org.robotframework.javalib.annotation.RobotKeywords;
    import org.robotframework.javalib.annotation.ArgumentNames;
    import org.robotframework.swing.context.Context;

    import org.netbeans.jemmy.operators.JComponentOperator;
    import org.netbeans.jemmy.operators.ContainerOperator;
    import org.netbeans.jemmy.ComponentChooser;
    import java.awt.Component;
    import java.awt.Shape;
    import java.awt.Rectangle;

    import edu.jsu.mcis.gamegogy.*;

    public class LeaderboardKeywords {

        @RobotKeyword("Clicks the leaderboard bar given by index.\n")
        @ArgumentNames({"index"})
        public void clickLeaderboardBarAtIndex(int index) {
            ContainerOperator context = (ContainerOperator) Context.getContext();
            ComponentChooser chooser = new LeaderboardChooser();
            JComponentOperator operator = new JComponentOperator(context, chooser);
            Leaderboard leaderboard = (Leaderboard)operator.getSource();
            Shape[] shapes = leaderboard.getShapes();
            if(index >= 0 && index < shapes.length) {
                Rectangle bounds = shapes[index].getBounds();
                operator.clickMouse(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, 1);
            }
        }

        class LeaderboardChooser implements ComponentChooser {
            public LeaderboardChooser() {}
            public boolean checkComponent(Component comp) {
                return (comp instanceof Leaderboard);
            }
            public String getDescription() {
                return "Any Leaderboard";
            }
        }    
    }

## Acceptance Tests
    
    | *Setting* | *Value*                      |
    | Library   | keywords.LeaderboardKeywords |
    | Library   | SwingLibrary                 |

    | *Test Case*                      | *Action*                          | *Argument*                       | *Argument*          |
    | Test Click Leaderboard Lowest    | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   |                     |
    |                                  | Select Window                     | Gamegogy                         |                     |
    |                                  | Click Leaderboard Bar At Index    | 10                               |                     |
    |                                  | Label Text Should Be              | studentId                        | 111141              |
    |                                  | Label Text Should Be              | studentName                      | Forest Rasmussen    |
    |                                  | Label Text Should Be              | studentEmail                     | frasmussen@jsu.edu  |
    |                                  | Label Text Should Be              | studentScore                     | 381.0               |
    |                                  | Close Window                      | Gamegogy                         |                     |
    | Test Click Leaderboard Highest   | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   |                     |
    |                                  | Select Window                     | Gamegogy                         |                     |
    |                                  | Click Leaderboard Bar At Index    | 0                                |                     |
    |                                  | Label Text Should Be              | studentId                        | 111318              |
    |                                  | Label Text Should Be              | studentName                      | Cathleen Guzman     |
    |                                  | Label Text Should Be              | studentEmail                     | cguzman@jsu.edu     |
    |                                  | Label Text Should Be              | studentScore                     | 925.0               |
    |                                  | Close Window                      | Gamegogy                         |                     |
    | Test Click Leaderboard Middle    | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   |                     |
    |                                  | Select Window                     | Gamegogy                         |                     |
    |                                  | Click Leaderboard Bar At Index    | 6                                |                     |
    |                                  | Label Text Should Be              | studentId                        | 111335              |
    |                                  | Label Text Should Be              | studentName                      | Ira Richardson      |
    |                                  | Label Text Should Be              | studentEmail                     | irichardson@jsu.edu |
    |                                  | Label Text Should Be              | studentScore                     | 558.0               |
    |                                  | Close Window                      | Gamegogy                         |                     |
