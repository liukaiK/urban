package com.king.urban.security.web.authentication;

import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.menu.Menu;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.repository.menu.MenuRepository;
import com.king.urban.core.repository.post.PostRepository;
import com.king.urban.security.web.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 用来存储当前登录人的信息
 *
 * @author liukai
 */
@Component
public class WebSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        Principal principal = (Principal) authentication.getPrincipal();
        Collection<Post> posts = postRepository.findByEmployeesIn(Collections.singletonList(new Employee(principal.getId())));
        Collection<Menu> menus = menuRepository.findByPostsIn(posts);
        StpUtil.login(principal.getId(), "web");
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_PRINCIPAL, principal);
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_POST, posts.stream().map(Post::getName).collect(Collectors.toSet()));
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_PERMISSION, menus.stream().map(Menu::getPermission).collect(Collectors.toSet()));
//        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_POST, );
    }

}
