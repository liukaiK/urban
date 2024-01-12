package com.king.urban.main.security.web.principal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.urban.main.core.entity.dept.Dept;
import com.king.urban.main.core.entity.employee.Employee;
import com.king.urban.main.core.entity.employee.Name;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


public class Principal implements UserDetails, Serializable {

    private Long id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    private String deptName;

    private Long deptId;

    /**
     * 子部门的ID
     */
    private Collection<Long> childrenDeptIds;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    private void principal() {

    }

    @Override
    public String toString() {
        return "Principal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", childrenDeptIds=" + childrenDeptIds +
                ", authorities=" + authorities +
                '}';
    }

    public static Principal fromEmployee(Employee employee) {
        Principal principal = new Principal();
        principal.setId(employee.getId());
        principal.setName(employee.getName());
        principal.setUsername(employee.getUsername());
        principal.setDeptId(employee.getDept().getId());

        if (employee.getDept().hasChildren()) {
            principal.setChildrenDeptIds(employee.getDept().getChildren().stream().map(Dept::getId).collect(Collectors.toSet()));
        }

        principal.setDeptName(employee.getDept().getName());
        principal.setPassword(employee.getEncodedPassword());
        return principal;
    }

    public Employee convertToEmployee() {
        Employee employee = new Employee(this.id);
        employee.updateName(new Name(this.name));
        return employee;
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
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
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

    public void setChildrenDeptIds(Collection<Long> childrenDeptIds) {
        this.childrenDeptIds = childrenDeptIds;
    }

    public Collection<Long> getChildrenDeptIds() {
        return childrenDeptIds;
    }

}
