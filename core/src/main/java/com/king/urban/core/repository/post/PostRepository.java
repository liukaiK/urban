package com.king.urban.core.repository.post;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.post.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends BaseRepository<Post, Long>, DeletableRepository<Post, Long> {

    Collection<Post> findByDept(Dept dept);

    Collection<Post> findByDeptIn(Collection<Dept> depts);

    Collection<Post> findByEmployeesIn(Collection<Employee> employees);

    boolean existsByNameAndDept(String name, Dept dept);

}
