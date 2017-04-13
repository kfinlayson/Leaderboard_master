package edu.jsu.mcis.gamegogy.service;

import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;

import edu.jsu.mcis.*;

@RestController
public class GamegogyController {
    private Database database;
    
    @PostConstruct
    public void init() {
        database = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
    }

    @RequestMapping(value="/gamegogy/studentlist", method=RequestMethod.GET)
    public String[] studentlist() {
        return database.getAllStudentIDsArray();
    }
    
    @RequestMapping(value="/gamegogy/student/{id}", method=RequestMethod.GET)
    public Student student(@PathVariable String id) {
        Student s = database.getStudent(id);
        if(s != null) return s;
        else return null;
    }
    
    @RequestMapping(value="/gamegogy/courselist", method=RequestMethod.GET)
    public String[] courselist() {
        return database.getAllCourseIDsArray();
    }
    
    @RequestMapping(value="/gamegogy/course/{id}", method=RequestMethod.GET)
    public Course course(@PathVariable String id) {
        Course c = database.getCourse(id);
        if(c != null) return c;
        else return null;
    }
}
