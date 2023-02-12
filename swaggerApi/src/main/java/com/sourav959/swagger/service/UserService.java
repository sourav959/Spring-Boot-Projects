package com.sourav959.swagger.service;

import com.sourav959.swagger.entity.User;

import java.util.List;

/**
 * This is the service layer for user.
 *
 * @author sourav959
 */
public interface UserService {

    /**
     * getAllUsers is used to fetch all the available users from the db.
     *
     * @return : List of users available.
     */
    List<User> getAllUsers();

    /**
     * getUserById is used to fetch user by id if it exists.
     *
     * @param id : id of user to be fetched.
     * @return : User data fetched from the db.
     */
    User getUserById(int id);

    /**
     * saveUser is used to save a new user in the db.
     *
     * @param user: user data to be saved.
     * @return : user saved in the db.
     */
    User saveUser(User user);

    /**
     * deleteUser is used to delete the user by id, if it exists.
     *
     * @param id : id of user to be deleted.
     */
    void deleteUserById(int id);
}
