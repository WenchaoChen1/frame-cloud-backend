package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRRoleBusinessPermissionRepository extends BaseRepository<SysRRoleBusinessPermission, SysRRoleBusinessPermissionEmbeddablePK> {

//    List<SysRAttributeMenu> findAllByMenuId(String menu);
//
//    List<SysRAttributeMenu> findAllByAttributeId(String attributeId);
//
//    void deleteAllByMenuId(String menuId);
//
//    void deleteAllByAttributeId(String attributeId);
//
//    void deleteAllByMenuIdAndAttributeId(String menuId, String attributeId);
//
//    void deleteAllByMenuIdIn(List<String> menuIds);
//
//    void deleteAllByAttributeIdIn(List<String> attributeIds);
//
//    void deleteAllByMenuIdAndAttributeIdIn(String menuId, List<String> attributeIds);
//
//    void deleteAllByMenuIdInAndAttributeIdIn(List<String> menuIds, List<String> attributeIds);
//
//
//    default void saveAndFlush(String menu, String attribute) {
//        if (ObjectUtils.isEmpty(menu) || ObjectUtils.isEmpty(attribute)) {
//            return;
//        }
//        SysRAttributeMenu sysRAttributeMenu = new SysRAttributeMenu();
//        sysRAttributeMenu.setMenuId(menu);
//        sysRAttributeMenu.setAttributeId(attribute);
//        saveAndFlush(sysRAttributeMenu);
//    }
//
//    default void saveAndFlush(String menu, Set<String> attributes) {
//        if (ObjectUtils.isEmpty(menu) || ObjectUtils.isEmpty(attributes)) {
//            return;
//        }
//        attributes.forEach(attribute -> saveAndFlush(menu, attribute));
//    }
//
//    default void saveAndFlush(Set<String> menus, String attribute) {
//        if (ObjectUtils.isEmpty(menus) || ObjectUtils.isEmpty(attribute)) {
//            return;
//        }
//        menus.forEach(menu -> saveAndFlush(menu, attribute));
//    }


}


