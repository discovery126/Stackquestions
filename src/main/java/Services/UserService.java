package Services;

import Models.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public User getUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) return userOptional.get();
        else {
            System.out.println("ТАКОГО ЮЗЕРА НЕТ");
            return null;
        }
    }
}
