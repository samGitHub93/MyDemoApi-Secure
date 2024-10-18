package org.mydemo.service;

import org.mydemo.dto.UserCreateDTO;
import org.mydemo.dto.UserEmailPwdDTO;
import org.mydemo.dto.UserIdentityDTO;
import org.mydemo.dto.UserUsernamePwdDTO;
import org.mydemo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(int id);

    List<UserEntity> getByAge(int age);

    Boolean existsByUsername(String username);

    UserIdentityDTO getIdentityInfoByUsername(UserUsernamePwdDTO userUsernamePwdDTO);

    UserIdentityDTO getIdentityInfoByEmail(UserEmailPwdDTO userEmailPwdDTO);

    UserIdentityDTO register(UserCreateDTO userCreateDTO);

    Stream<UserIdentityDTO> getByAgeFlux(int age);

    UserIdentityDTO getByIdMono(int id);

    String verify(UserUsernamePwdDTO userUsernamePwdDTO);

    String verify(UserEmailPwdDTO userEmailPwdDTO);
}
