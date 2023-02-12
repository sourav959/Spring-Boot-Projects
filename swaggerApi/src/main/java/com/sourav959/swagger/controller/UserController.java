package com.sourav959.swagger.controller;

import com.sourav959.swagger.constants.Constants;
import com.sourav959.swagger.entity.User;
import com.sourav959.swagger.exception.ErrorResponse;
import com.sourav959.swagger.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * This class is used to perform save, update and delete operations on user.
 *
 * @author sourav959
 */
@Validated
@RestController
@RequestMapping("/users")
@OpenAPIDefinition(
        tags = @Tag(name = Constants.USER_TAG, description = "These APIs fetches, updates or deletes the user related data."),
        info = @Info(
                title = "REST API endpoints for user operations.",
                version = "1.0.0",
                description = "REST API endpoints for user related operations like fetching, updating and deletion.",
                contact = @Contact(url = "mailto:souravgupta959@gmail.com", name = "Sourav Gupta", email = "souravgupta959@gmail.com"))
)
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * getAllUsers is used to fetch all the available users from the db.
     *
     * @return : List of users available.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful."),
            @ApiResponse(responseCode = "400", description = "Bad request.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping()
    @Operation(summary = "Fetching all users", description = "Fetching all the existing users.",
            tags = Constants.USER_TAG)
    ResponseEntity<List<User>> getAllUsers() {
        log.info("Request for getAllUsers started.");
        var response = userService.getAllUsers();
        log.info("Request for getAllUsers completed with fetching {} records.", response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * getUser is used to fetch user by id if it exists.
     *
     * @param id : id of user to be fetched.
     * @return : User data fetched from the db.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful."),
            @ApiResponse(responseCode = "400", description = "Bad request.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    @Operation(summary = "Fetching user by id", description = "Fetching user by id if it exists.",
            tags = Constants.USER_TAG)
    ResponseEntity<User> getUser(
            @Parameter(example = "1", required = true, description = "Id of user to be fetched.")
            @PathVariable @Min(1) int id) {
        log.info("Request for getUserById started for userId::{}.", id);
        var response = userService.getUserById(id);
        log.info("Request for getUserById completed for userId::{}.", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * saveUser is used to save a new user in the db.
     *
     * @param user : user data to be saved.
     * @return : user saved in the db.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request successful."),
            @ApiResponse(responseCode = "400", description = "Bad request.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Saving new user", description = "Saving new user",
            tags = Constants.USER_TAG)
    ResponseEntity<User> saveUser(
            @Parameter(example = "{'name': 'Sourav Gupta', 'email': 'souravgupta959@gmail.com', 'gender': 'm', 'status': 'A'}",
                    required = true, description = "New user data to be added.")
            @Valid @RequestBody User user) {
        log.info("Request for saveUser started.");
        var response = userService.saveUser(user);
        log.info("Request for saveUser completed for userId::{}.", response.getId());
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    /**
     * deleteUser is used to delete the user by id, if it exists.
     *
     * @param id : id of user to be deleted.
     * @return : 204 deletion successful.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Request successful."),
            @ApiResponse(responseCode = "400", description = "Bad request.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting user by id", description = "Deleting user by id if it exists",
            tags = Constants.USER_TAG)
    ResponseEntity<Void> deleteUser(
            @Parameter(example = "1", required = true, description = "Id of user to be deleted.")
            @PathVariable @Min(1) int id) {
        log.info("Request for deleteUserById started for id::{}.", id);
        userService.deleteUserById(id);
        log.info("Request for deleteUserById completed for id::{}.", id);
        return ResponseEntity.noContent().build();
    }

}
