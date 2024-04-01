package com.frame.template.service.system.service;

import com.frame.template.service.system.mapper.MenuMapper;
import com.frame.template.service.system.pojo.base.menu.*;
import com.frame.template.service.system.pojo.domain.Account;
import com.frame.template.service.system.pojo.domain.Menu;
import com.frame.template.service.system.pojo.domain.RTenantMenu;
import com.frame.template.service.system.pojo.domain.Role;
import com.frame.template.service.system.repository.AccountRepository;
import com.frame.template.service.system.repository.MenuRepository;
import com.frame.template.service.system.repository.RoleRepository;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeServiceImpl;
import com.frame.template.common.constant.AccountTypeConstants;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.common.utils.treeUtils.TreeFactory;
import com.frame.template.service.system.pojo.base.menu.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseTreeServiceImpl<MenuRepository, MenuMapper, Menu, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria, RedisCurrentLoginInformation> implements MenuService {

  @Resource
  private MenuRepository menuRepository;
  @Resource
  private MenuMapper menuMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;
  @Resource
  private RoleRepository roleRepository;
  @Resource
  private AccountRepository accountRepository;

  public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(menuRepository, menuMapper, redisCurrentLoginInformation);
    this.menuRepository = menuRepository;
    this.menuMapper = menuMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
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
    Account account = accountRepository.findById(accountId).get();
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
