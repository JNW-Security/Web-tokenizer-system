package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User loadUser(@Param("username") String username);

    public void registerUser(@Param("user") User user);

    public void registerUserV2(@Param("user") User user);

    public void registerUserV3(@Param("user") User user);
}
