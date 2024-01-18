package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.User;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.UserRepository;
import com.example.rentacarv1.services.abstracts.UserService;
import com.example.rentacarv1.services.dtos.requests.auth.LoginRequest;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.UpdateUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.UserAuthenticationRequest;
import com.example.rentacarv1.services.dtos.responses.user.GetUserListResponse;
import com.example.rentacarv1.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public DataResult<List<GetUserListResponse>> getAll() {
        List<User> users=userRepository.findAll();
        List<GetUserListResponse> userListResponse=users.stream().map(user -> this.modelMapperService.forResponse()
                .map(user,GetUserListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetUserListResponse>>(userListResponse,"Users listed");
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        GetUserResponse getUserResponse=this.modelMapperService.forResponse().map(user,GetUserResponse.class);
        return new SuccessDataResult<GetUserResponse>(getUserResponse,"User listed");
    }

    @Override
    public Result add(AddUserRequest addUserRequest) {
        User user=this.modelMapperService.forRequest().map(addUserRequest,User.class);
        this.userRepository.save(user);
        return new SuccessResult("User added");
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        User user =this.modelMapperService.forRequest().map(updateUserRequest,User.class);
        userRepository.save(user);
        return new SuccessResult("User updated");
    }

    @Override
    public Result delete(int id) {

        userRepository.deleteById(id);
        return new SuccessResult("User deleted !");
    }

    @Override
    public void register(UserAuthenticationRequest userAuthenticationRequest) {
        User user = User.builder()
                .name(userAuthenticationRequest.getName())
                .surname(userAuthenticationRequest.getSurname())
                .email(userAuthenticationRequest.getEmail())
                .gsm(userAuthenticationRequest.getGsm())
                .authorities(userAuthenticationRequest.getRoles())
                .password(passwordEncoder.encode(userAuthenticationRequest.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    public void login(LoginRequest request) {

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
