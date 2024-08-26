package com.iridium.security.mapper.user;

import com.iridium.openapi.model.RegisterRequest;
import com.iridium.openapi.model.User;
import com.iridium.security.entity.user.UserEO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Map user entity to user model.
     *
     * @param userEO user entity
     * @return user model
     */
    User toUser(UserEO userEO);

    /**
     * Map user request to user entity.
     *
     * @param registerRequest register request
     * @return user entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordConfirm", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserEO toUserEO(RegisterRequest registerRequest);
}
