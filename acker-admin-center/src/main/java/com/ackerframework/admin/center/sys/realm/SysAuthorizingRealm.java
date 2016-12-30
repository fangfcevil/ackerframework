package com.ackerframework.admin.center.sys.realm;

import com.ackerframework.admin.center.rights.entity.User;
import com.ackerframework.admin.center.rights.service.UserService;
import com.ackerframework.admin.center.sys.entity.UserRights;
import com.ackerframework.admin.center.sys.service.SysService;
import com.ackerframework.base.entity.LoginUser;
import com.ackerframework.core.security.PassWordCredentialsMatcher;
import com.ackerframework.core.security.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SysAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private SysService sysService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * 本例中该方法的调用时机为需授权资源被访问时
     * 并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        LoginUser loginUser = (LoginUser) super.getAvailablePrincipal(principals);
        if (null != loginUser) {
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            //添加角色权限
//            simpleAuthorInfo.addRole("admin");
            //添加 权限
//            List<Menu> menus = menuService.getLoginMenu(principal.getId(), principal.getRoleId());
//            for (Menu menu : menus) {
//                if (StringUtils.isNotBlank(menu.getPermission())) {
//                    // 添加基于Permission的权限信息
//                    for (String permission : StringUtils.split(menu.getPermission(), ",")) {
//                        simpleAuthorInfo.addStringPermission(permission);
//                        System.out.println("权限：" + permission + "，");
//                    }
//                }
//            }
            return simpleAuthorInfo;
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问时都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    //本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.getByAccount(token.getUsername());

        if (user != null) {
            if (!user.getCanUse()) {
                throw new AuthenticationException("msg:该帐号已禁止登录.");
            }
            if (sysService.getUserRightses(user.getId()).size() == 0) {
                throw new AuthenticationException("msg:该帐号尚未分配组织权限，请联系管理员！.");
            }
            LoginUser loginUser = new LoginUser();
            loginUser.setId(user.getId());
            loginUser.setAccount(user.getAccount());
            loginUser.setNickname(user.getNickname());
            loginUser.setEmail(user.getEmail());
            loginUser.setMobile(user.getMobile());
            return new SimpleAuthenticationInfo(loginUser, user.getPassword(), getName());
        }
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
        System.out.println("用户：" + token.getUsername() + "登录，异常");
        return null;
    }

    //设定Password校验.
    @PostConstruct
    public void initCredentialsMatcher() {
        //重写shiro的密码验证，让shiro用自定义的验证
        setCredentialsMatcher(new PassWordCredentialsMatcher());
    }
}
