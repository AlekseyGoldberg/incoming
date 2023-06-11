package com.example.incoming.service;

import com.example.incoming.dto.request.UserReqDto;
import com.example.incoming.dto.response.UserRespDto;
import com.example.incoming.entity.User;
import com.example.incoming.exception.NotFoundUserException;
import com.example.incoming.exception.NotUnauthorizedException;
import com.example.incoming.exception.UserExistException;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private AuthService authService;
    private UserRepositoryImpl repository;


    public UserRespDto saveUser(UserReqDto dto) {
        if (repository.existUser(dto.getLogin()))
            throw new UserExistException(String.format("User with login: %s exist", dto.getLogin()));
        String jwt = authService.createAuthToken(dto);
        User user = new User(dto.getLogin(), authService.hashPassword(dto.getPassword()), jwt);
        repository.saveUser(user);
        return new UserRespDto(jwt);
    }

    public User getUser(String username) {
        return repository.getUser(username);
    }

    public UserRespDto getJwt(UserReqDto dto) {
        User user = repository.getUser(dto.getLogin());
        if (Objects.isNull(user))
            throw new NotFoundUserException("User not found");
        String acceptHashPassword = authService.hashPassword(dto.getPassword());
        if (!user.getHashPassword().equals(acceptHashPassword)) {
            throw new NotUnauthorizedException("Неверный логин или пароль");
        }
        String jwt = authService.createAuthToken(dto);
        user.setJwt(jwt);
        repository.saveUser(user);
        return new UserRespDto(jwt);
    }
}
