package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.core.utils.treeUtils.TreeFactory;
import com.gstdev.cloud.service.system.constants.AccountTypeConstants;
import com.gstdev.cloud.service.system.mapper.MenuMapper;
import com.gstdev.cloud.service.system.pojo.base.menu.*;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.Menu;
import com.gstdev.cloud.service.system.pojo.entity.RTenantMenu;
import com.gstdev.cloud.service.system.pojo.entity.Role;
import com.gstdev.cloud.service.system.repository.AccountRepository;
import com.gstdev.cloud.service.system.repository.MenuRepository;
import com.gstdev.cloud.service.system.repository.RoleRepository;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseTreeServiceImpl<Menu, String, MenuRepository, MenuMapper, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> implements MenuService {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private AccountRepository accountRepository;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        super(menuRepository, menuMapper);
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public Result<List<MenuDto>> getAllByRoleMenuToTree(String roleId) {
        Optional<Role> byId = roleRepository.findById(roleId);
        List<Menu> menus = byId.get().getRTenantMenus().stream().map(RTenantMenu::getMenu).toList();
        return Result.success(new TreeFactory<String, MenuDto>().buildTree(getMapper().toDto(menus)));
    }

    @Override
    public Result<MenuDto> getAllTenantMenuIds(String tenantId) {
        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
        menuFindAllByQueryCriteria.setTenantId(tenantId);
        List<MenuDto> allByQueryCriteriaToDto = findAllByQueryCriteriaToDtoToTree(menuFindAllByQueryCriteria);
        return Result.success(getMenuParentChildren(allByQueryCriteriaToDto));
    }

    public MenuDto getMenuParentChildren(List<MenuDto> menuDtos) {
        MenuDto menu = new MenuDto();
        List<String> checkedMenuIds = new ArrayList<>();
        List<String> halfCheckedMenuIds = new ArrayList<>();
        for (MenuDto menuDto : menuDtos) {
            if (menuDto.getChildren() == null || menuDto.getChildren().size() == 0) {
                checkedMenuIds.add(menuDto.getId());
            } else {
                halfCheckedMenuIds.add(menuDto.getId());
                MenuDto menuParentChildren = getMenuParentChildren(menuDto.getChildren());
                checkedMenuIds.addAll(menuParentChildren.getCheckedMenuId());
                halfCheckedMenuIds.addAll(menuParentChildren.getHalfCheckedMenuId());
            }
        }
        checkedMenuIds.addAll(menu.getCheckedMenuId());
        halfCheckedMenuIds.addAll(menu.getHalfCheckedMenuId());
        menu.setCheckedMenuId(checkedMenuIds);
        menu.setHalfCheckedMenuId(halfCheckedMenuIds);
        return menu;
    }

    public List<MenuDto> getAccountPermissions(String accountId) {
        SysAccount account = accountRepository.findById(accountId).get();
        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
        if (account.getType().equals(AccountTypeConstants.SUPER.getCode())) {
            return findAllByQueryCriteriaToDtoToTree(menuFindAllByQueryCriteria);
        } else if (account.getType().equals(AccountTypeConstants.ADMIN.getCode())) {
            menuFindAllByQueryCriteria.setTenantId(account.getTenantId());
            return findAllByQueryCriteriaToDtoToTree(menuFindAllByQueryCriteria);
        } else if (account.getType().equals(AccountTypeConstants.USER.getCode())) {
            Map<String, Menu> collect = new HashMap<>();
            for (Role role : account.getRoles()) {
                List<Menu> menus = role.getRTenantMenus().stream().map(RTenantMenu::getMenu).toList();
                Map<String, List<Menu>> collect2 = menus.stream().collect(Collectors.groupingBy(Menu::getId));
                Map<String, Menu> collect1 = role.getRTenantMenus().stream().map(RTenantMenu::getMenu).collect(Collectors.groupingBy(Menu::getId,
                    Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
                collect.putAll(collect1);
            }
            List<Menu> menus1 = collect.values().stream().toList();
            return new TreeFactory<String, MenuDto>().buildTree(getMapper().toDto(menus1));
        }
        return null;
    }

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
