package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.service.system.domain.base.menu.MenuDto;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsDto;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsQO;
import com.gstdev.cloud.service.system.mapper.SysMenuMapper;
import com.gstdev.cloud.service.system.repository.SysAccountRepository;
import com.gstdev.cloud.service.system.repository.SysMenuRepository;
import com.gstdev.cloud.service.system.repository.SysRoleRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysMenuServiceImpl extends BaseTreeServiceImpl<SysMenu, String, SysMenuRepository, SysMenuMapper, MenuDto> implements SysMenuService {

    @Resource
    private SysRoleRepository roleRepository;
    @Resource
    private SysAccountRepository accountRepository;
    @Resource
    private SysMenuRepository menuRepository;

    public SysMenuServiceImpl(SysMenuRepository menuRepository, SysMenuMapper menuMapper) {
        super(menuRepository, menuMapper);
        this.menuRepository = menuRepository;
    }

    @Override
    public SysMenuRepository getRepository() {
        return menuRepository;
    }


//    @Override
//    public Result<List<MenuDto>> getAllByRoleMenuToTree(String roleId) {
//        Optional<SysRole> byId = roleRepository.findById(roleId);
//        List<SysMenu> menus = byId.get().getRTenantMenus().stream().map(RTenantMenu::getMenu).toList();
//        return Result.success(new TreeFactory<String, MenuDto>().buildTree(getMapper().toDto(menus)));
//    }

//    @Override
//    public Result<MenuDto> getAllTenantMenuIds(String tenantId) {
//        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
//        menuFindAllByQueryCriteria.setTenantId(tenantId);
//        List<MenuDto> allByQueryCriteriaToDto = findAllByQueryCriteriaToDtoToTree(menuFindAllByQueryCriteria);
//        return Result.success(getMenuParentChildren(allByQueryCriteriaToDto));
//    }

//    public MenuDto getMenuParentChildren(List<MenuDto> menuDtos) {
//        MenuDto menu = new MenuDto();
//        List<String> checkedMenuIds = new ArrayList<>();
//        List<String> halfCheckedMenuIds = new ArrayList<>();
//        for (MenuDto menuDto : menuDtos) {
//            if (menuDto.getChildren() == null || menuDto.getChildren().size() == 0) {
//                checkedMenuIds.add(menuDto.getId());
//            } else {
//                halfCheckedMenuIds.add(menuDto.getId());
//                MenuDto menuParentChildren = getMenuParentChildren(menuDto.getChildren());
//                checkedMenuIds.addAll(menuParentChildren.getCheckedMenuId());
//                halfCheckedMenuIds.addAll(menuParentChildren.getHalfCheckedMenuId());
//            }
//        }
//        checkedMenuIds.addAll(menu.getCheckedMenuId());
//        halfCheckedMenuIds.addAll(menu.getHalfCheckedMenuId());
//        menu.setCheckedMenuId(checkedMenuIds);
//        menu.setHalfCheckedMenuId(halfCheckedMenuIds);
//        return menu;
//    }

//    List<MenuDto> findAllByQueryCriteriaToDtoToTree(MenuFindAllByQueryCriteria menuFindAllByQueryCriteria) {
//        return findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, menuFindAllByQueryCriteria, criteriaBuilder));
//    }
//
//    List<AccountMenuPermissionsDto> findAllByQueryCriteriaToDtoToTree(MenuFindAllByQueryCriteria menuFindAllByQueryCriteria) {
//        return findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, menuFindAllByQueryCriteria, criteriaBuilder));
//    }


    @Override
    public List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId) {
        SysAccount account = accountRepository.findById(accountId).get();
        AccountMenuPermissionsQO accountMenuPermissionsQO = new AccountMenuPermissionsQO();
        List<SysMenu> sysMenuList=null;
        if (account.getType().equals(SysAccountType.SUPER)) {
            sysMenuList = findAll();
        } else if (account.getType().equals(SysAccountType.ADMIN)) {
            accountMenuPermissionsQO.setTenantId(account.getTenantId());
            sysMenuList = findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, accountMenuPermissionsQO, criteriaBuilder));
        } else if (account.getType().equals(SysAccountType.USER)) {
            Map<String, SysMenu> collect = new HashMap<>();
            for (SysRole role : account.getRoles()) {
                List<SysMenu> menus = role.getTenantMenus().stream().map(SysTenantMenu::getMenu).toList();
                Map<String, List<SysMenu>> collect2 = menus.stream().collect(Collectors.groupingBy(SysMenu::getId));
                Map<String, SysMenu> collect1 = role.getTenantMenus().stream().map(SysTenantMenu::getMenu).collect(Collectors.groupingBy(SysMenu::getId,
                    Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
                collect.putAll(collect1);
            }
            sysMenuList = collect.values().stream().toList();
        }
        return getMapper().toAccountMenuPermissionsDtoToTree(sysMenuList);
    }

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
