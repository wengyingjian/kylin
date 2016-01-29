package com.wengyingjian.kylin.util;

import com.wengyingjian.kylin.util.model.User;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wengyingjian on 16/1/29.
 */
public class ExcelUtilTest {

    @Test
    public void testRead() throws Exception {
        System.out.println(JsonUtil.getJsonFromObject(ExcelUtil.read(this.getClass().getClassLoader().getResourceAsStream("a.xls"))));
    }

    @Test
    public void testWrite() throws Exception {

        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setDesc("aa");
        user.setName("andy");
        userList.add(user);

        User user1 = new User();
        user1.setId(2);
        user1.setName("jack");
        user1.setDesc("bb");
        userList.add(user1);


        ExcelUtil.write(new File("a.xls"), userList);
    }
}
