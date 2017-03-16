| *Setting* | *Value*                   |
| Library   | SwingLibrary              |

| *Variable*                | *Value*                                |
| ${service_url_with_slash} | http://inspired.jsu.edu:7272/gamegogy/ |
| ${service_url_no_slash}   | http://inspired.jsu.edu:7272/gamegogy  |

| *Test Case*                      | *Action*                          | *Argument*                       | *Argument*                |
| Test Gamegogy Default Values     | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   | ${service_url_with_slash} |
|                                  | Select Window                     | Gamegogy                         |                           |
|                                  | ${courseId}=                      | Get Selected Item From Combo Box | courseComboBox            |
|                                  | Should Be Equal                   | 99000                            | ${courseId}               |
|                                  | ${columnName}=                    | Get Selected Item From Combo Box | columnComboBox            |
|                                  | Should Be Equal                   | Total                            | ${columnName}             |
|                                  | Label Text Should Be              | courseTerm                       | Spring 2013               |
|                                  | Label Text Should Be              | courseEnrollment                 | 11                        |
|                                  | Label Text Should Be              | studentId                        | 111318                    |
|                                  | Label Text Should Be              | studentName                      | Cathleen Guzman           |
|                                  | Label Text Should Be              | studentEmail                     | cguzman@jsu.edu           |
|                                  | Label Text Should Be              | studentScore                     | 925.0                     |
|                                  | Close Window                      | Gamegogy                         |                           |
| Test Gamegogy Course Select      | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   | ${service_url_no_slash}   |
|                                  | Select Window                     | Gamegogy                         |                           |
|                                  | Select From Combo Box             | courseComboBox                   | 7                         |
|                                  | ${columnName}=                    | Get Selected Item From Combo Box | columnComboBox            |
|                                  | Should Be Equal                   | Total                            | ${columnName}             |
|                                  | Label Text Should Be              | courseTerm                       | Spring 2012               |
|                                  | Label Text Should Be              | courseEnrollment                 | 7                         |
|                                  | Label Text Should Be              | studentId                        | 111382                    |
|                                  | Label Text Should Be              | studentName                      | Mauricio Harrington       |
|                                  | Label Text Should Be              | studentEmail                     | mharrington@jsu.edu       |
|                                  | Label Text Should Be              | studentScore                     | 803.0                     |
|                                  | Close Window                      | Gamegogy                         |                           |
| Test Gamegogy Column Select      | Start Application                 | edu.jsu.mcis.gamegogy.Gamegogy   | ${service_url_with_slash} |
|                                  | Select Window                     | Gamegogy                         |                           |
|                                  | Select From Combo Box             | courseComboBox                   | 9                         |
|                                  | Select From Combo Box             | columnComboBox                   | Assignment 7              |
|                                  | Label Text Should Be              | courseTerm                       | Spring 2012               |
|                                  | Label Text Should Be              | courseEnrollment                 | 25                        |
|                                  | Label Text Should Be              | studentId                        | 111122                    |
|                                  | Label Text Should Be              | studentName                      | Vance McClain             |
|                                  | Label Text Should Be              | studentEmail                     | vmcclain@jsu.edu          |
|                                  | Label Text Should Be              | studentScore                     | 59.0                      |
|                                  | Close Window                      | Gamegogy                         |                           |
