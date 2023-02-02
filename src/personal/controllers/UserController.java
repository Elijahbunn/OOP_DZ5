package personal.controllers;

import personal.model.Fields;
import personal.model.Repository;
import personal.model.User;
import personal.utils.Validate;

import java.util.Iterator;
import java.util.List;

public class UserController {
    private final Repository repository;
    private final Validate validate;
    public UserController(Repository repository, Validate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    public void saveUser(User user) throws Exception {
        validate.checkNumber(user.getPhone());
        repository.CreateUser(user);
    }
    public void updateUser(User user, Fields field, String param) throws Exception {
        if(field == Fields.TELEPHONE) {
            validate.checkNumber(param);
        }
        repository.UpdateUser(user, field, param);

    }
    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }
    public User delUser(String userName) throws Exception {
        List<User> users = getUsers();
        int current = -1;
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getFirstName().equals(userName)) {
                current = i;
            }
        }
        if (current != -1){
            System.out.println(users.get(current));
            users.remove(users.get(current));
        }
//        Iterator<String> iterator = users.iterator();
//        while (iterator.hasNext()){
//            if (iterator.next().getId().equals(userId)) {
//                iterator.remove();
//            }
//        }
        throw new Exception("Произошла ошибка");
    }
    public List<User> getUsers() throws Exception {
        return repository.getAllUsers();
    }
}
