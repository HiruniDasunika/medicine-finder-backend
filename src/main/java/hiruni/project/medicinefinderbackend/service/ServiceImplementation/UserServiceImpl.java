package hiruni.project.medicinefinderbackend.service.ServiceImplementation;

import hiruni.project.medicinefinderbackend.entity.User;
import hiruni.project.medicinefinderbackend.repository.UserRepository;
import hiruni.project.medicinefinderbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }

    @Override
    public String deleteUserById(int id) {
        repository.deleteById(id);
        return "User Deleted successfully";
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }


    public User updateUser(User user) {
        User currentUser = repository.findById(Math.toIntExact(user.getId())).orElse(null);
        if (currentUser != null) {
            currentUser.setName(user.getName());
            currentUser.setAddress(user.getAddress());
            return repository.save(currentUser);
        }
        return user;
    }

}
