package com.king.urban.main;

import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.main.core.entity.employee.Employee;
import com.king.urban.main.core.entity.employee.Username;
import com.king.urban.main.core.entity.menu.Menu;
import com.king.urban.main.core.entity.post.Post;
import com.king.urban.main.core.repository.employee.EmployeeRepository;
import com.king.urban.main.core.repository.menu.MenuRepository;
import com.king.urban.main.core.repository.post.PostRepository;
import com.king.urban.main.security.util.CurrentPrincipalUtil;
import com.king.urban.main.security.web.authentication.WebSessionAuthenticationStrategy;
import com.king.urban.main.security.web.principal.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;


public class BaseTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private PostRepository postRepository;

    @Resource
    private MenuRepository menuRepository;

    public void login(String username) {
        Employee employee = employeeRepository.getByUsername(new Username(username)).orElseThrow(IllegalArgumentException::new);
        Collection<Post> posts = postRepository.findByEmployeesIn(Collections.singletonList(employee));
        Collection<Menu> menus = menuRepository.findByPostsIn(posts);

        StpUtil.login(employee.getId(), "junit");

        WebSessionAuthenticationStrategy.saveCache(Principal.fromEmployee(employee), posts, menus);

        logger.debug("登录成功，当前登录人信息:{}", CurrentPrincipalUtil.getCurrentPrincipal());

    }


}
