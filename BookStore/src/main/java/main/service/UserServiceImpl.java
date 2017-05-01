package main.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import main.entities.Role;
import main.entities.User;
import main.repository.RoleRepository;
import main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
    	System.out.println("save user begining");
    	System.out.println(user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> hs=new HashSet<Role>();
        hs.add(roleRepository.findById(1l));
        user.setRoles(hs);
        
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public List<User> readUsers(){
		List<User> users=new ArrayList<User>();		
		users=userRepository.findAll();
		return  users;
	}
	
	public User readUser(String name){
		User user=userRepository.findByUsername(name);
		if (user==null) return null;
		return user;
	}
	
	public void updateUser(String name,User newUser){
		User userToSave=userRepository.findByUsername(name);	
		userToSave.setPassword(newUser.getPassword());
		userToSave.setSalary(newUser.getSalary());
		userToSave.setName(newUser.getName());
		userRepository.save(userToSave);
	}
	
	public void deleteUser(String name){
		userRepository.deleteByUsername(name);		
	}
	
}
