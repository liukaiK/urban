package com.king.urban.component.component;

import com.king.urban.component.pojo.dto.component.ComponentDTO;
import com.king.urban.component.pojo.vo.ComponentVO;
import com.king.urban.component.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentRepository componentRepository;


    @Override
    public Page<ComponentVO> search(Pageable pageable) {
        Page<Component> page = componentRepository.findAll(pageable);

        return null;
    }

    @Override
    public void create(ComponentDTO componentDTO) {
        Component component = new Component();
        component.updateCode(componentDTO.getCode());
        component.updateName(componentDTO.getName());
        component.updateSource(componentDTO.getSource());
        component.updateForm(componentDTO.getForm());
        component.updateLocate(componentDTO.getLocate());
        component.updateMainDeptCode(componentDTO.getMainDeptCode());
        component.updateMainDeptName(componentDTO.getMainDeptName());
        component.updateKeepDeptCode(componentDTO.getKeepDeptCode());
        component.updateKeepDeptName(componentDTO.getKeepDeptName());
        component.updateOwnerDeptCode(componentDTO.getOwnerDeptCode());
        component.updateOwnerDeptName(componentDTO.getOwnerDeptName());
        component.updateMaterial(componentDTO.getMaterial());
        component.updateState(componentDTO.getState());
        component.updatePoint(componentDTO.getPoint());
        componentRepository.save(component);
    }

    @Override
    public void create(List<ComponentDTO> componentDTOs) {
        for (ComponentDTO componentDTO : componentDTOs) {
            create(componentDTO);
        }
    }

}
