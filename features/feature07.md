# Create a web service that can serve as the data source for Gamegogy.

The current web service based at `http://inspired.jsu.edu:7272/gamegogy/` needs to be replaced by a new web service that you create. This service should provide the information stored in the CSV files associated with this project.
To remind you, it should respond to the following HTTP GET requests:

http://web-service-base-url/studentlist
: This returns a JSON array containing the list of all student IDs as strings. For example, this might look like
  `["111111","111112","111113","111114","111115","111116","111117"]`

http://web-service-base-url/student/*id*
: This returns a JSON object containing the student information for the given ID with the following fields:

  * id -- a string representing the student ID
  * first -- a string representing the student's first name
  * last -- a string representing the student's last name
  * email -- a string representing the students email username (without the "@jsu.edu")

  For example, this might look like
  `{"id":"111383","first":"Abby","last":"Berry","email":"aberry"}`

http://web-service-base-url/courselist
: This returns a JSON array containing the list of all course IDs as strings. For example, this might look like
  `["99000", "99001", "99002", "99003", "99004"]`

http://web-service-base-url/course/*id*
: This returns a JSON object containing the course information for the given ID with the following fields:

  * id -- a string representing the course ID
  * term -- a string representing the term (Spring, Summer, or Fall)
  * year -- a string representing the year
  * size -- an integer representing the enrollment of the course
  * grades -- a JSON object containing the grades with the following fields:
      * rowHeaders -- a JSON array of strings representing the student IDs for each row
      * colHeaders -- a JSON array of strings representing the names for each column
      * data -- a JSON array of JSON arrays of floats representing the grades for each row

For example, this might look like
  
    {
        "id":"99001",
        "term":"Summer",
        "year":"2012",
        "size":9,
        "grades":
        {
            "colHeaders":["Total","Assignment 1","Assignment 2","Assignment 3",
                          "Exam 1","Exam 2","Exam 3"],
            "rowHeaders":["111291","111208","111148","111236","111326",
                          "111293","111143","111157","111254"],
            "data":[
                    [823.0,124.0,105.0,123.0,175.0,153.0,143.0],
                    [557.0,77.0,68.0,79.0,134.0,112.0,87.0],
                    [494.0,69.0,87.0,74.0,72.0,104.0,88.0],
                    [669.0,98.0,89.0,88.0,111.0,135.0,148.0],
                    [499.0,66.0,83.0,58.0,113.0,109.0,70.0],
                    [548.0,64.0,72.0,83.0,122.0,81.0,126.0],
                    [886.0,132.0,123.0,114.0,190.0,162.0,165.0],
                    [593.0,82.0,71.0,92.0,94.0,135.0,119.0],
                    [820.0,120.0,102.0,127.0,174.0,153.0,144.0]
                   ]
        }
    } 

In the event of any invalid requests (e.g., an ID that does not exist), the service should return an empty string.


## Spring Framework

In order to accomplish this task, you should use the Spring Framework to create and test a web service that can run as a stand-alone JAR file. Use the [Spring RESTful service tutorial](https://spring.io/guides/gs/rest-service/) as a starting point to create your own service.

    
## Acceptance Tests
    
    | *Setting* | *Value*         |
    | Library   | RequestsLibrary |

    | *Keyword*                        | *Action*           | *Argument*                         | *Argument*  |          |
    | Is Menu Item Selected            | [Arguments]        | ${menuText}                        |             |          |
    |                                  | @{menus}=          | Split String                       | ${menuText} | \|       |
    |                                  | ${lastItem}=       | Remove From List                   | ${menus}    | -1       |
    |                                  | :FOR               | ${menu}                            | IN          | @{menus} |
    |                                  |                    | Select From Menu                   | ${menu}     |          |
    |                                  | ${selected}=       | Radio Button Menu Item Is Selected | ${lastItem} |          |
    |                                  | [return]           | ${selected}                        |             |          |
    | Menu Item Should Be Selected     | [Arguments]        | ${menuText}                        |             |          |
    |                                  | ${selected}=       | Is Menu Item Selected              | ${menuText} |          |
    |                                  | Should Be True     | ${selected}                        |             |          |
    | Menu Item Should Not Be Selected | [Arguments]        | ${menuText}                        |             |          |
    |                                  | ${selected}=       | Is Menu Item Selected              | ${menuText} |          |
    |                                  | Should Not Be True | ${selected}                        |             |          |


    | *Test Case*                      | *Action*                         | *Argument*                       | *Argument*       |
    | Test Default File Source         | Start Application                | edu.jsu.mcis.gamegogy.Gamegogy   |                  |
    |                                  | Select Window                    | Gamegogy                         |                  |
    |                                  | Menu Item Should Be Selected     | Source|Resource File             |                  |
    |                                  | Menu Item Should Not Be Selected | Web Service                      |                  |
    |                                  | Close Window                     | Gamegogy                         |                  |
    | Test Web Service Source          | Start Application                | edu.jsu.mcis.gamegogy.Gamegogy   |                  |
    |                                  | Select Window                    | Gamegogy                         |                  |
    |                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
    |                                  | Should Be Equal                  | 99000                            | ${courseId}      |
    |                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
    |                                  | Should Be Equal                  | Total                            | ${columnName}    |
    |                                  | Select From Combo Box            | courseComboBox                   | 9                |
    |                                  | Select From Combo Box            | columnComboBox                   | Assignment 7     |
    |                                  | Label Text Should Be             | courseTerm                       | Spring 2012      |
    |                                  | Label Text Should Be             | courseEnrollment                 | 25               |
    |                                  | Label Text Should Be             | studentId                        | 111122           |
    |                                  | Label Text Should Be             | studentName                      | Vance McClain    |
    |                                  | Label Text Should Be             | studentEmail                     | vmcclain@jsu.edu |
    |                                  | Label Text Should Be             | studentScore                     | 59.0             |
    |                                  | Select From Menu                 | Source|Web Service               |                  |
    |                                  | Menu Item Should Not Be Selected | Source|Resource File             |                  |
    |                                  | Menu Item Should Be Selected     | Web Service                      |                  |
    |                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
    |                                  | Should Be Equal                  | 99000                            | ${courseId}      |
    |                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
    |                                  | Should Be Equal                  | Total                            | ${columnName}    |
    |                                  | Close Window                     | Gamegogy                         |                  |
    | Test Reselected File Source      | Start Application                | edu.jsu.mcis.gamegogy.Gamegogy   |                  |
    |                                  | Select Window                    | Gamegogy                         |                  |
    |                                  | Select From Menu                 | Source|Web Service               |                  |
    |                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
    |                                  | Should Be Equal                  | 99000                            | ${courseId}      |
    |                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
    |                                  | Should Be Equal                  | Total                            | ${columnName}    |
    |                                  | Select From Combo Box            | courseComboBox                   | 9                |
    |                                  | Select From Combo Box            | columnComboBox                   | Assignment 7     |
    |                                  | Label Text Should Be             | courseTerm                       | Spring 2012      |
    |                                  | Label Text Should Be             | courseEnrollment                 | 25               |
    |                                  | Label Text Should Be             | studentId                        | 111122           |
    |                                  | Label Text Should Be             | studentName                      | Vance McClain    |
    |                                  | Label Text Should Be             | studentEmail                     | vmcclain@jsu.edu |
    |                                  | Label Text Should Be             | studentScore                     | 59.0             |
    |                                  | Select From Menu                 | Source|Resource File             |                  |
    |                                  | Menu Item Should Be Selected     | Source|Resource File             |                  |
    |                                  | Menu Item Should Not Be Selected | Web Service                      |                  |
    |                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
    |                                  | Should Be Equal                  | 99000                            | ${courseId}      |
    |                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
    |                                  | Should Be Equal                  | Total                            | ${columnName}    |
    |                                  | Close Window                     | Gamegogy                         |                  |
