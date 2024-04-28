package com.frame.template.service.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictDto;
import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictModifyInput;
import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictSaveInput;
import com.frame.template.service.system.pojo.entity.TenantDict;
import com.frame.template.service.system.mapper.TenantDictMapper;
import com.frame.template.service.system.repository.TenantDictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhucy
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class TenantDictServiceImpl implements TenantDictService {
    private final TenantDictMapper tenantDictMapper;
    private final TenantDictRepository tenantDictRepository;

    /**
     * 新增保存
     *
     * @param tenantDictSaveInput
     * @return
     */
    @Override
    public TenantDictDto save(TenantDictSaveInput tenantDictSaveInput) {
        List<TenantDict> tenantDicts = tenantDictRepository.findByCode(tenantDictSaveInput.getCode());
        if (!CollectionUtils.isEmpty(tenantDicts)) {
            throw new RuntimeException("Code reuse");
        }
        TenantDict tenantDict = tenantDictMapper.toEntitySave(tenantDictSaveInput);
        tenantDict = tenantDictRepository.save(tenantDict);

        return tenantDictMapper.toDto(tenantDict);
    }

    /**
     * 编辑
     *
     * @param tenantDictModifyInput
     * @return
     */
    @Override
    public TenantDictDto modify(TenantDictModifyInput tenantDictModifyInput) {
        TenantDict tenantDict = tenantDictRepository.findById(tenantDictModifyInput.getId()).orElseGet(null);
        if (null == tenantDict) {
            throw new RuntimeException("数据异常");
        }
        tenantDictMapper.copyModify(tenantDictModifyInput, tenantDict);
        tenantDict = tenantDictRepository.save(tenantDict);
        return tenantDictMapper.toDto(tenantDict);
    }

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    @Override
    public TenantDictDto findById(String id) {
        TenantDict tenantDict = tenantDictRepository.findById(id).orElseGet(TenantDict::new);
        return tenantDictMapper.toDto(tenantDict);
    }

    /**
     * 主键删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        List<TenantDict> tenantDictList = tenantDictRepository.findAllByParentIdOrderBySort(id);
        if (ObjectUtil.isNotEmpty(tenantDictList)) {
            tenantDictRepository.deleteAll(tenantDictList);
        }
        tenantDictRepository.deleteById(id);
    }

    /**
     * 查询树结构
     *
     * @return
     */
    @Override
    public List<TenantDictDto> getAllTree(String tenantDictId) {
        if (ObjectUtil.isNotEmpty(tenantDictId)) {
            List<TenantDict> firstLevel = tenantDictRepository.findAllByParentIdAndTenantIdOrderBySort("0", tenantDictId);
            if (ObjectUtil.isNotEmpty(firstLevel)) {
                List<TenantDictDto> tenantDictDtos = tenantDictMapper.toDto(firstLevel);
                tenantDictDtos.forEach(tenantDictDto -> {
                    tenantDictDto.setChildren(findChildren(tenantDictDto, tenantDictId));
                });
                return tenantDictDtos;
            }
        }
        return null;
    }

    /**
     * 递归查询
     *
     * @param tenantDictDto
     * @return
     */
    public List<TenantDictDto> findChildren(TenantDictDto tenantDictDto, String tenantDictId) {
        List<TenantDict> tenantDicts = tenantDictRepository.findAllByParentIdAndTenantIdOrderBySort(tenantDictDto.getId(), tenantDictId);
        if (ObjectUtil.isNotEmpty(tenantDicts)) {
            List<TenantDictDto> tenantDictDtos = tenantDictMapper.toDto(tenantDicts);
            tenantDictDtos.forEach(tenantDictDto1 -> {
                tenantDictDto1.setChildren(findChildren(tenantDictDto1, tenantDictId));
            });
            return tenantDictDtos;
        }
        return null;
    }
}
