package hiruni.project.medicinefinderbackend.service;

import hiruni.project.medicinefinderbackend.entity.User;



import java.util.List;


public interface UserService {

    User saveUser(User user);
    User getUserById(int id);
    List<User> getUsers();
    List<User> saveUsers(List<User> users);

    String deleteUserById(int id);

    User updateUser(User user);


}
