package com.example.validat.Controller;


import com.example.validat.ApiResponse.Api;
import com.example.validat.UserModel.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/p2")
public class UserC {
    ArrayList<User> users=new ArrayList<>();

    //display all project
    @GetMapping("/get")
    public ArrayList<User>displayCompany(){
        return users;}


 //add new project
    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid User company, Errors e) {
        if (e.hasErrors()) {
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        users.add(company);

        return ResponseEntity.status(200).body(new Api("add success"));
    }

    //update
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index,@RequestBody @Valid User company, Errors e){
        if (e.hasErrors()){
            String message=e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       users.set(index,company);
        return ResponseEntity.status(200).body(new Api("update success"));
    }


    //remove
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deletProject(@PathVariable int index) {
        if (index >= 0 && index < users.size()) {
            users.remove(0);
            return ResponseEntity.status(200).body(new Api(" delet success"));
        }
        return ResponseEntity.status(400).body(new Api("delete unsuccess you should enter right index"));

    }




    // change project statuse
@PutMapping("st/{index}/{status}")
    public ResponseEntity changeStatuse(@PathVariable int index,@PathVariable String status){
        if (index>=0&&index<users.size()){
            User u=users.get(index);
            u.setStatus(status);
            return ResponseEntity.status(200).body("chang success");
        }


        return ResponseEntity.status(400).body(new Api("CHANGE  unsuccess you should enter right index"));

    }



    @GetMapping("/search/{title}")
    public ResponseEntity search(@PathVariable String title){
        for (User p:users){
            if (p.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(new Api("done from searc"+users));
            }

        }
        return ResponseEntity.status(400).body("titel not found");

    }


    @GetMapping("/co/{companyName}")
    public ResponseEntity getProjectsByCompany(@PathVariable String companyName) {
        ArrayList<User> trackers1=new ArrayList<>();
        for (User project : users) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                trackers1.add(project);
                return ResponseEntity.status(200).body(new Api("done from searc"+trackers1));
            }

        }

        return ResponseEntity.status(400).body("titel not found");
    }








}


















