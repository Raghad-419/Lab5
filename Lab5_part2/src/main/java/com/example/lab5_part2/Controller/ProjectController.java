package com.example.lab5_part2.Controller;

import com.example.lab5_part2.ApiResponse.ApiResponse;
import com.example.lab5_part2.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse("Successfully added project");
    }

    @PutMapping("/update/{index}")
    public ApiResponse update(@PathVariable int index ,@RequestBody Project project){
        if(!projects.isEmpty()){
        projects.set(index, project);
        return new ApiResponse("Successfully updated project");}
        else return new ApiResponse("Project not found");
    }

@DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return new ApiResponse("Successfully deleted project");
    }

    @PutMapping("/change/{index}")
    public ApiResponse changeStatus(@PathVariable int index){
        projects.get(index).setStatus("Done");
        return new ApiResponse("Successfully changed project");
    }

    @GetMapping("/search/{title}")
public Project searchByTitle(@PathVariable String title){
        for(Project p :projects){
           if(p.getTitle().equalsIgnoreCase(title)){
               return p;
           }}
        return null;
}

@GetMapping("/company-projects/{companyName}")
public ArrayList<Project> companyProjects(@PathVariable String companyName){
        ArrayList<Project> projectsCompany = new ArrayList<>();
        for(Project p :projects){
            if(p.getCompanyName().equalsIgnoreCase(companyName)){
                projectsCompany.add(p);
            }
        }
        return projectsCompany;

}


}
