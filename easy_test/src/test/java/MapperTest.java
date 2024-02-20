import com.easytest.RunApplication;
import com.easytest.entity.po.UserInfo;
import com.easytest.entity.query.UserInfoQuery;
import com.easytest.mappers.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = RunApplication.class)
@RunWith(SpringRunner.class)
 class MapperTest {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Test
    public  void selectList(){
       List<UserInfo> userInfos = userInfoMapper.selectList(new UserInfoQuery());
       for (UserInfo userInfo : userInfos) {
          System.out.println(userInfo);
       }
    }
}
