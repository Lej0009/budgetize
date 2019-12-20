package co.budgetize.budgetize.service;

import co.budgetize.budgetize.dao.RoleDao;
import co.budgetize.budgetize.dao.UserDao;
import co.budgetize.budgetize.models.Role;
import co.budgetize.budgetize.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleDao.findByRole("APP_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userDao.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        boolean isUserAlreadyExists = false;
        User existingUser = userDao.findByEmail(user.getEmail());
        // If user is found in database, then the user already exists.
        if(existingUser != null){
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }

    @Override
    public void changeUserPassword(User user, @RequestParam String password) {
        user.setPassword(encoder.encode(password));
        userDao.save(user);
    }

}
