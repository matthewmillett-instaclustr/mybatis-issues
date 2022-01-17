package test;

import org.mybatis.guice.transactional.Transactional;

public class UserService {

    private Mapper mapper;

    @Transactional
    public User getUserById(UserPrimaryKey id) {
        return mapper.getUserById(id);
    }

    @Transactional
    public User getUserByName(String name) {
        return mapper.getUserByName(name);
    }
}
