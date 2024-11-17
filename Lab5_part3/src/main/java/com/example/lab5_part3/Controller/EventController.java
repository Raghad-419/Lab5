package com.example.lab5_part3.Controller;

import com.example.lab5_part3.ApiResponse.ApiResponse;
import com.example.lab5_part3.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event){
        events.add(event);
        return new ApiResponse("Event added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index ,@RequestBody Event event){
        if(!events.isEmpty()){
        events.set(index, event);
        return new ApiResponse("Event updated");}
        else return new ApiResponse("Event not found");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        if(!events.isEmpty()){
            events.remove(index);
            return new ApiResponse("Event deleted");
        }
        else return new ApiResponse("Event not found");
    }


    @PutMapping("/changeCapacity/{index}")
    public ApiResponse changeCapacity(@PathVariable int index){
        if(!events.isEmpty()){
            if(events.get(index).getCapacity()>0){
            events.get(index).setCapacity(events.get(index).getCapacity()-1);}
            return new ApiResponse("Event capacity updated to "+events.get(index).getCapacity());
        }
        else return new ApiResponse("Event not found");
    }
    
@GetMapping("/searchById/{id}")
    public Event searchById(@PathVariable String id){
            for(Event e : events){
                if(e.getId().equals(id))
                    return e;
            }
        return null;
    }

}
