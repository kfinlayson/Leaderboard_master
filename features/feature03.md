# Create a graphical interface for the student and course data.

Assume GamegogyGUI.java creates a top-level window that looks like the following:

    +--------------------------------------------+
    |                  Gamegogy           - [] X |
    +--------------------------------------------+
    | Course [99012 V]     Column [Total      V] |
    |        |99013  |            |Assignment 1| |
    |        |99014  |            |Assignment 2| |
    |        |99015  |            |Assignment 3| |
    |        |99016  |            |Exam 1      | |
    |        |99017  |            |Exam 2      | |
    |        |99018  |            |Exam 3      | |
    |         -------              ------------  |
    | Term: Fall 2015      Enrollment: 22        |
    |                                            |
    |                                            |
    |                                            |
    |                                            |
    +--------------------------------------------+
    | ID: 111119                                 |
    | Name: Roseann Townsen                      |
    | Email: rtownsend@jsu.edu                   |
    | Score: 964.0                               |
    +--------------------------------------------+

This window has two dropdown lists (combo boxes). The first is populated with the available course IDs (defaulting to the first one in the list). The second one is populated with the column names from that course's grades (except for the student ID column). The first column is the default. When the course ID is changed, the column list is updated with the new names (always defaulting to the first in the list).

When a course is selected (including the initial default), its term and enrollment are displayed below the dropdown lists. When a column is selected (including the initial default), the student information (ID, name, email, and score) of the highest score in that column is displayed in a panel at the bottom. 



## Acceptance Tests
    
    | *Setting* | *Value*                   |
    | Library   | SwingLibrary              |

    | *Test Case*                      | *Action*                          | *Argument*                       | *Argument*          |
    | Test Gamegogy Default Values     | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   |                     |
    |                                  | Select Window                     | Gamegogy                         |                     |
    |                                  | ${courseId}=                      | Get Selected Item From Combo Box | courseComboBox      |
    |                                  | Should Be Equal                   | 99000                            | ${courseId}         |
    |                                  | ${columnName}=                    | Get Selected Item From Combo Box | columnComboBox      |
    |                                  | Should Be Equal                   | Total                            | ${columnName}       |
    |                                  | Label Text Should Be              | courseTerm                       | Spring 2013         |
    |                                  | Label Text Should Be              | courseEnrollment                 | 11                  |
    |                                  | Label Text Should Be              | studentId                        | 111318              |
    |                                  | Label Text Should Be              | studentName                      | Cathleen Guzman     |
    |                                  | Label Text Should Be              | studentEmail                     | cguzman@jsu.edu     |
    |                                  | Label Text Should Be              | studentScore                     | 925.0               |
    |                                  | Close Window                      | Gamegogy                         |                     |
    | Test Gamegogy Course Select      | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   |                     |
    |                                  | Select Window                     | Gamegogy                         |                     |
    |                                  | Select From Combo Box             | courseComboBox                   | 7                   |
    |                                  | ${columnName}=                    | Get Selected Item From Combo Box | columnComboBox      |
    |                                  | Should Be Equal                   | Total                            | ${columnName}       |
    |                                  | Label Text Should Be              | courseTerm                       | Spring 2012         |
    |                                  | Label Text Should Be              | courseEnrollment                 | 7                   |
    |                                  | Label Text Should Be              | studentId                        | 111382              |
    |                                  | Label Text Should Be              | studentName                      | Mauricio Harrington |
    |                                  | Label Text Should Be              | studentEmail                     | mharrington@jsu.edu |
    |                                  | Label Text Should Be              | studentScore                     | 803.0               |
    |                                  | Close Window                      | Gamegogy                         |                     |
    | Test Gamegogy Column Select      | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   |                     |
    |                                  | Select Window                     | Gamegogy                         |                     |
    |                                  | Select From Combo Box             | courseComboBox                   | 9                   |
    |                                  | Select From Combo Box             | columnComboBox                   | Assignment 7        |
    |                                  | Label Text Should Be              | courseTerm                       | Spring 2012         |
    |                                  | Label Text Should Be              | courseEnrollment                 | 25                  |
    |                                  | Label Text Should Be              | studentId                        | 111122              |
    |                                  | Label Text Should Be              | studentName                      | Vance McClain       |
    |                                  | Label Text Should Be              | studentEmail                     | vmcclain@jsu.edu    |
    |                                  | Label Text Should Be              | studentScore                     | 59.0                |
    |                                  | Close Window                      | Gamegogy                         |                     |
