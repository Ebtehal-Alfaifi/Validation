package com.example.validat.UserModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {


    @NotEmpty(message = " you should enter ID")
    @Size(min = 3,max = 10,message = " The ID should be between 3 and 10 ")
    private String id;


    @NotEmpty(message = " you should enter  Title")
    @Size(min = 9,message = " The length of title at least should has 9 ")
    private String title;


    @NotEmpty(message = " you should enter status")
    @Pattern(
            regexp = "^(Not Started|In Progress|Completed)$",
            message = "Status must be 'Not Started', 'In Progress', or 'Completed'"
    )
     private String status;


    @NotEmpty(message = "You should enter company name")
    @Size(min = 7,message = " The length of title at least should has 7")
    private String companyName;





}
