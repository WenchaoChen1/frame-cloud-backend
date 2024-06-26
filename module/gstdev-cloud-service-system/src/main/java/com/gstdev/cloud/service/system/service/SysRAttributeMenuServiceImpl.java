package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAttributeMenuRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Transactional(readOnly = true)
public class SysRAttributeMenuServiceImpl extends BaseServiceImpl<SysRAttributeMenu, SysRAttributeMenuEmbeddablePK, SysRAttributeMenuRepository> implements SysRAttributeMenuService {


    public SysRAttributeMenuServiceImpl(SysRAttributeMenuRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void updateAttributeAssignedMenus(String attributeId, Set<String> menuIds) {
        if (ObjectUtils.isEmpty(attributeId)) {
            return;
        }
        if (ObjectUtils.isEmpty(menuIds)) {
            getRepository().deleteAllByAttributeId(attributeId);
            return;
        }
        getRepository().findAllByAttributeId(attributeId).forEach(sysRAttributeMenu -> {
            if (!menuIds.contains(sysRAttributeMenu.getMenuId())) {
                delete(sysRAttributeMenu);
            }
        });
        getRepository().saveAndFlush(menuIds, attributeId);
    }

    @Override
    @Transactional
    public void updateMenuAssignedAttributes(String menuId, Set<String> attributeIds) {
        if (ObjectUtils.isEmpty(menuId)) {
            return;
        }
        if (ObjectUtils.isEmpty(attributeIds)) {
            getRepository().deleteAllByMenuId(menuId);
            return;
        }
        getRepository().findAllByMenuId(menuId).forEach(sysRAttributeMenu -> {
            if (!attributeIds.contains(sysRAttributeMenu.getAttributeId())) {
                delete(sysRAttributeMenu);
            }
        });
        getRepository().saveAndFlush(menuId, attributeIds);
    }

    @Override
    public Set<String> getAllAttributeIdByMenuId(String menuId){
        return Set.of(getRepository().findAllByMenuId(menuId).stream().map(SysRAttributeMenu::getAttributeId).toArray(String[]::new));
    }

    @Override
    public Set<String> getAllMenuIdByAttributeId(String attributeId) {
        return Set.of(getRepository().findAllByAttributeId(attributeId).stream().map(SysRAttributeMenu::getMenuId).toArray(String[]::new));
    }
}
