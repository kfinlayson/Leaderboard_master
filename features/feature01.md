# Allow student and course data to be retrieved from the command-line.

The student and course data have been provided as a compressed archive of CSV files.

## Use Cases

Assume Gamegogy.java allows for two positional arguments, `type` and `id`, respectively.

    java Gamegogy student 111128

This means that `type` is "student" and `id` is "111128". Similarly, the following usage would be for a course:

    java Gamegogy course 99018

If anything other than two arguments are given, the program should exit with no output.

The usage presented above for "student" should produce the following output:

    [111128] Maritza Abbott mabbott@jsu.edu

And the usage above for "course" should produce the following output:

    [99018] Spring 2014 (16 students)


## Acceptance Tests

    | *Setting* | *Value*                   |
    | Library   | keywords.GamegogyKeywords |
    
    | *Variable*         | *Value*                                 |
    | ${f01stu_expected} | [111128] Maritza Abbott mabbott@jsu.edu |
    | ${f01crs_expected} | [99018] Spring 2014 (16 students)       |

    | *Test Case*                      | *Action*                          | *Argument*              | *Argument* |
    | Test Gamegogy Load Student Data  | Start Gamegogy CLI With Arguments | student                 | 111128     |
    |                                  | ${output}=                        | Get Command Line Output |            |
    |                                  | Should Be Equal                   | ${f01stu_expected}      | ${output}  |
    | Test Gamegogy Load Course Data   | Start Gamegogy CLI With Arguments | course                  | 99018      |
    |                                  | ${output}=                        | Get Command Line Output |            |
    |                                  | Should Be Equal                   | ${f01crs_expected}      | ${output}  |
    | Test Gamegogy With No Arguments  | Start Gamegogy CLI With Arguments |                         |            |
    |                                  | ${output}=                        | Get Command Line Output |            |
    |                                  | Should Be Equal                   |                         | ${output}  |
