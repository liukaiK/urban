package com.king.urban.core.repository.dept;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.core.entity.dept.Dept;

import java.util.Collection;

public interface DeptRepository extends BaseRepository<Dept, Long>, DeletableRepository<Dept, Long> {

    Collection<Dept> findBySystemDept(boolean isSystemDept);

    Collection<Dept> findByName(String name);

}
