import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author ：chenyz
 * @date ：2019/12/4 14:39
 */
public class ShiroTest {

    @Test
    public void testHelloWorld(){
        // 获取SecurityManager工厂，使用配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 得到SecurityManager实例，并绑定到SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到Subject，创建用户名、密码身份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try{
            // 登录，并进行身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            // 身份验证失败
            e.printStackTrace();
        }
        // 断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());
        // 退出
        subject.logout();
    }
}
