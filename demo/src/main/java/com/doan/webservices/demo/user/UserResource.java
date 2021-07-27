package com.doan.webservices.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//can also be called UserController
@RestController
public class UserResource {

    @Autowired
    private UserDAOService daoService;

    //get all users
    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers(){
        return daoService.findAll();
    }
    //get single user
    @GetMapping(path = "/getUser/{id}")
    public User getSingleUser(@PathVariable int id){
        User user = daoService.findUser(id);
        if(user==null){
            throw new UserNotFoundException("id-" + id +" not currently in user database. Please try a valid ID");
        }
        return user;
    }

    @DeleteMapping(path= "user/{id}")
    public void deleteUser(@PathVariable int id){
        User user = daoService.deleteUserById(id);
        if(user==null){
            throw new UserNotFoundException("id-" + id +" not currently in user database. Please try a valid ID");
        }
    }

    //create user
    //input user details
    //output created user and return URI of new user as confirmation
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User u){

        User createdUser = daoService.saveUser(u);

        //return status and message 'created'
        //add id of user into response message under /user/{id}

        //build from current request uri (CurrentRequest)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId()) //append id from created user from getId(id)
                .toUri(); //convert to uri

        //pass in uri and build spring response entity
        return ResponseEntity.created(location).build();
    }

}
