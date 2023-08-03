package main.java.com.hua.myRPCVersion1.server;


import main.java.com.hua.myRPCVersion1.common.User;
import main.java.com.hua.myRPCVersion1.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"的用户");
        // 模拟从数据库中取用户的行为
        Random random = new Random();
        User user = new User(id, UUID.randomUUID().toString(),random.nextBoolean());
        return user;
    }
    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }
}
