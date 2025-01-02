package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Users;
import com.example.crudOperations.demoCrudOperation.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JwtService jwtService;
private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


public Users addusers(Users users)
{
users.setPassword(encoder.encode(users.getPassword()));
return usersRepo.save(users);
}
//####VERIFY USER
public String verifyUser(Users users)
{
    Authentication authentication=manager
            .authenticate(new UsernamePasswordAuthenticationToken
                    (users.getEmail(),users.getPassword()));
    if (authentication.isAuthenticated())
    {
        Users existinguser = usersRepo.existsByEmail(users.getEmail());
if (existinguser!=null){

return jwtService.generateToken(existinguser.getEmail());}
else {throw new RuntimeException("User not found.");
    }
}return "Authentication Failed";
}
}
