package com.doan.webservices.demo.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Ben", new Date()));
        users.add(new User(2, "Andy", new Date()));
        users.add(new User(3, "Megan", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User u){
        if(u.getId()==null){
            u.setId(usersCount++);
        }
        users.add(u);
        return u;
    }

    public User findUser(int id){
        for(User u:users) {
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    public User deleteUserById(int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();

            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
