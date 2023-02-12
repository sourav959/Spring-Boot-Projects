package com.sourav959.swagger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sourav959.swagger.validation.MaleFemale;
import com.sourav959.swagger.validation.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @Size(min = 1, max = 20, message = "Invalid name. The length of name should be between 1 to 20 characters.")
    @Column
    @Schema(example = "Sourav Gupta", description = "Name of user to be saved.")
    private String name;


    @Column
    @Size(min = 1, message = "Invalid email, please correct the email.")
    @Schema(example = "souravgupta959@gmail.com", description = "Email of user to be saved.")
    private String email;

    @Column
    @Schema(example = "m", description = "Gender of user to be saved.")
    @MaleFemale
    private String gender;


    @Column
    @Schema(description = "Status of user to be saved.", example = "Y")
    @YesNo
    private String status;

}
