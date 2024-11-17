package com.example.lab5.Controller;

import com.example.lab5.ApiResponse.ApiResponse;
import com.example.lab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("The student has been added successfully.");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        if (!students.isEmpty()) {
            students.set(index, student);
            return new ApiResponse("The student has been updated successfully.");
        } else return new ApiResponse("There are no students.");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        if (!students.isEmpty()) {
            students.remove(index);
            return new ApiResponse("The student has been deleted successfully.");
        } else return new ApiResponse("There are no students.");
    }

    @GetMapping("classify/{index}")
    public ApiResponse classifyStudent(@PathVariable int index) {
        if (!students.isEmpty()) {
            double gpa = students.get(index).getGPA();
            if (gpa >= 4.75) {
                return new ApiResponse("A+ (Exceptional)");
            } else if (gpa >= 4.50) {
                return new ApiResponse("A (Excellent)");
            } else if (gpa >= 4.0) {
                return new ApiResponse("B+ (Superior)");
            } else if (gpa >= 3.50) {
                return new ApiResponse("B (Very Good) ");
            } else if (gpa >= 3.0) {
                return new ApiResponse("C+ (Above Average)");
            } else if (gpa >= 2.50) {
                return new ApiResponse("C (Good)");
            } else if (gpa >= 2.0) {
                return new ApiResponse("D+ (High Pass)");
            } else if (gpa >= 1.0) {
                return new ApiResponse("D (Pass)");
            } else return new ApiResponse("F (Fail)");
        }
        return new ApiResponse("There are no students.");

    }

@GetMapping("/above-average")
    public ArrayList<Student> above_average(){
        double sum =0.0;
        for (Student student : students) {
            sum= sum+ student.getGPA();
        }
        double avg = sum/students.size();

        ArrayList<Student> goodStudents = new ArrayList<>();
        for (Student s :students ){
            if(s.getGPA()>avg){
                goodStudents.add(s);
            }
        }
        return goodStudents;
    }



}//end class