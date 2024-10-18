package org.mydemo.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mydemo.dto.UserCreateDTO;
import org.mydemo.dto.UserEmailPwdDTO;
import org.mydemo.dto.UserIdentityDTO;
import org.mydemo.dto.UserUsernamePwdDTO;
import org.mydemo.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "dtoUsername")
    @Mapping(target = "password", source = "dtoPassword")
    UserEntity toUserEntity(UserUsernamePwdDTO userUsernamePwdDTO);

    @InheritInverseConfiguration
    UserUsernamePwdDTO toUserUsernamePwdDTO(UserEntity userEntity);

    @Mapping(target = "email", source = "dtoEmail")
    @Mapping(target = "password", source = "dtoPassword")
    UserEntity toUserEntity(UserEmailPwdDTO userEmailPwdDTO);

    @InheritInverseConfiguration
    UserEmailPwdDTO toUserEmailPwdDTO(UserEntity userEntity);

    @Mapping(target = "name", source = "dtoName")
    @Mapping(target = "age", source = "dtoAge")
    @Mapping(target = "birthDate", source = "dtoBirthDate")
    UserEntity toUserEntity(UserIdentityDTO userIdentityDTO);

    @InheritInverseConfiguration
    UserIdentityDTO toUserIdentityDTO(UserEntity userEntity);

    @Mapping(target = "name", source = "dtoName")
    @Mapping(target = "age", source = "dtoAge")
    @Mapping(target = "birthDate", source = "dtoBirthDate")
    @Mapping(target = "username", source = "dtoUsername")
    @Mapping(target = "password", source = "dtoPassword")
    @Mapping(target = "email", source = "dtoEmail")
    UserEntity toUserEntity(UserCreateDTO userCreateDTO);

    @InheritInverseConfiguration
    UserCreateDTO toUserCreateDTO(UserEntity userEntity);
}
