package com.example.restfulwebservoce.domain;

import java.util.Iterator;
import java.util.List;

public class UserList {
    private List<User> users;

    //list로 순회하며 삭제하면 에러발생
    private User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
