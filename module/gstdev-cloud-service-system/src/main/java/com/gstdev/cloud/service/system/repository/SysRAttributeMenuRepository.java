package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
public interface SysRAttributeMenuRepository extends BaseRepository<SysRAttributeMenu, String> {

    void deleteAllByMenuId(String menuId);
    void deleteAllByAttributeAttributeId(String attributeId);
    void deleteAllByMenuIdAndAttributeAttributeId(String menuId, String attributeId);
    void deleteAllByMenuIdIn(List<String> menuIds);
    void deleteAllByAttributeAttributeIdIn(List<String> attributeIds);
    void deleteAllByMenuIdAndAttributeAttributeIdIn(String menuId, List<String> attributeIds);
    void deleteAllByMenuIdInAndAttributeAttributeIdIn(List<String> menuIds, List<String> attributeIds);
    default SysRAttributeMenu saveAndFlush(SysMenu menu, SysAttribute attribute) {
        SysRAttributeMenu sysRAttributeMenu = new SysRAttributeMenu();
        sysRAttributeMenu.setMenu(menu);
        sysRAttributeMenu.setAttribute(attribute);
        return saveAndFlush(sysRAttributeMenu);
    }
    default List<SysRAttributeMenu> saveAndFlush(SysMenu menu, List<SysAttribute> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return null;
        }
        return attributes.stream().map(attribute -> saveAndFlush(menu, attribute)).collect(Collectors.toList());
    }

    default List<SysRAttributeMenu> saveAndFlush(List<SysMenu> menus, SysAttribute attribute) {
        if (menus == null || menus.isEmpty()) {
            return null;
        }
        return menus.stream().map(menu -> saveAndFlush(menu, attribute)).collect(Collectors.toList());
    }


}
