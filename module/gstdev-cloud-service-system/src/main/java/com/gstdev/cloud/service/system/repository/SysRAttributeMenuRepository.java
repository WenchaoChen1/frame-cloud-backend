package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRAttributeMenuRepository extends BaseRepository<SysRAttributeMenu, String> {

    List<SysRAttributeMenu> findAllByMenuId(String menu);

    void deleteAllByMenuId(String menuId);

    void deleteAllByAttributeId(String attributeId);

    void deleteAllByMenuIdAndAttributeId(String menuId, String attributeId);

    void deleteAllByMenuIdIn(List<String> menuIds);

    void deleteAllByAttributeIdIn(List<String> attributeIds);

    void deleteAllByMenuIdAndAttributeIdIn(String menuId, List<String> attributeIds);

    void deleteAllByMenuIdInAndAttributeIdIn(List<String> menuIds, List<String> attributeIds);


    default void saveAndFlush(String menu, String attribute) {
        SysRAttributeMenu sysRAttributeMenu = new SysRAttributeMenu();
        sysRAttributeMenu.setMenuId(menu);
        sysRAttributeMenu.setAttributeId(attribute);
        saveAndFlush(sysRAttributeMenu);
    }

    default void saveAndFlush(String menu, Set<String> attributes) {
        attributes.forEach(attribute -> saveAndFlush(menu, attribute));
    }

    default void saveAndFlush(Set<String> menus, String attribute) {
        menus.forEach(menu -> saveAndFlush(menu, attribute));
    }

    default void saveAndDeleteAndFlush(String menu, Set<String> attributes) {
        findAllByMenuId(menu).forEach(sysRAttributeMenu -> {
            if (!attributes.contains(sysRAttributeMenu.getAttributeId())) {
                delete(sysRAttributeMenu);
            }
        });
        saveAndFlush(menu, attributes);
    }

}


