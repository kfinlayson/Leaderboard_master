| *Setting* | *Value*                      |
| Library   | keywords.LeaderboardKeywords |
| Library   | SwingLibrary                 |

| *Test Case*                      | *Action*                          | *Argument*                       | *Argument*          |
| Test Click Leaderboard Lowest    | Start Application                 | edu.jsu.mcis.Main                |                     |
|                                  | Select Window                     | Gamegogy                         |                     |
|                                  | Click Leaderboard Bar At Index    | 10                               |                     |
|                                  | Label Text Should Be              | studentId                        | 111141              |
|                                  | Label Text Should Be              | studentName                      | Forest Rasmussen    |
|                                  | Label Text Should Be              | studentEmail                     | frasmussen@jsu.edu  |
|                                  | Label Text Should Be              | studentScore                     | 381.0               |
|                                  | Close Window                      | Gamegogy                         |                     |
| Test Click Leaderboard Highest   | Start Application                 | edu.jsu.mcis.Main                |                     |
|                                  | Select Window                     | Gamegogy                         |                     |
|                                  | Click Leaderboard Bar At Index    | 0                                |                     |
|                                  | Label Text Should Be              | studentId                        | 111318              |
|                                  | Label Text Should Be              | studentName                      | Cathleen Guzman     |
|                                  | Label Text Should Be              | studentEmail                     | cguzman@jsu.edu     |
|                                  | Label Text Should Be              | studentScore                     | 925.0               |
|                                  | Close Window                      | Gamegogy                         |                     |
| Test Click Leaderboard Middle    | Start Application                 | edu.jsu.mcis.Main                |                     |
|                                  | Select Window                     | Gamegogy                         |                     |
|                                  | Click Leaderboard Bar At Index    | 6                                |                     |
|                                  | Label Text Should Be              | studentId                        | 111335              |
|                                  | Label Text Should Be              | studentName                      | Ira Richardson      |
|                                  | Label Text Should Be              | studentEmail                     | irichardson@jsu.edu |
|                                  | Label Text Should Be              | studentScore                     | 558.0               |
|                                  | Close Window                      | Gamegogy                         |                     |
