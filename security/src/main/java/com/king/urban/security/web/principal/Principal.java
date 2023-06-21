package com.king.urban.security.web.principal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.urban.core.entity.employee.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


public class Principal implements UserDetails, Serializable {

    private Long id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    private String deptName;

    private Long deptId;

    private Collection<? extends GrantedAuthority> authorities;

    private void principal() {

    }

    public static Principal fromEmployee(Employee employee) {
        Principal principal = new Principal();
        principal.setId(employee.getId());
        principal.setName(employee.getName());
        principal.setUsername(employee.getUsername());
        principal.setDeptId(employee.getDept().getId());
        principal.setDeptName(employee.getDept().getName());
        principal.setPassword(employee.getEncodedPassword());
        return principal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new Authority());
    }

    /**
     * 这个方法不能删除 否则redis反序列化的时候会报错
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
