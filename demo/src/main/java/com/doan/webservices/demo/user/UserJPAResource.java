package com.doan.webservices.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//can also be called UserController
@RestController
public class UserJPAResource {

    @Autowired
    private UserDAOService daoService;

    @Autowired
    private UserRepository userRepository;

    //get all users
    @GetMapping(path = "/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get single user
    //params used for versioning, access version 1 using "/users/{id}/param?version=1"
    @GetMapping(path = "/jpa/users/{id}", params = "version=1")
    public EntityModel<User> getSingleUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        //Optional<> class has isPresent() method
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id + " not currently in user database. Please try a valid ID");
        }

        EntityModel<User> userModel = EntityModel.of(user.get());
        //build link back to list of all users. Links to getAllUsers method of class User
        WebMvcLinkBuilder linktoUsers = linkTo(methodOn(this.getClass()).getAllUsers());
        userModel.add(linktoUsers.withRel("all-users")); // attach to user model

        return userModel;

    }

    /*testing dynamic filtering - remember to uncomment @JSONFilter in user object
    //params used for versioning, access this version using "/users/{id}/param?version=2"
    @GetMapping(path = "/users/{id}", params="version=2")
    public MappingJacksonValue getFilteredUser(@PathVariable int id){
        User user = daoService.findUser(id);
        if(user==null){
            throw new UserNotFoundException("id-" + id +" not currently in user database. Please try a valid ID");
        }

        //implement dynamic filtering at request level
        //create filter for simple beans
        SimpleBeanPropertyFilter beanFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
        //add simple bean filter to filter provider
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserDefaultFilter", beanFilter);
        //create jackson mapping to user
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        //add filter to user mapping
        mapping.setFilters(filters);

        return mapping;

    }

     */

    @DeleteMapping(path = "/jpa/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    //create user
    //input user details
    //output created user and return URI of new user as confirmation
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User u) {

        User createdUser = userRepository.save(u);

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
