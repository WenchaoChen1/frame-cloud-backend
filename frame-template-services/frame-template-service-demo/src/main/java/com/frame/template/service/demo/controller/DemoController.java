package com.frame.template.service.demo.controller;

import com.frame.template.common.base.BasePOJOController;
import com.frame.template.common.exception.CommonException;
import com.frame.template.service.demo.mapper.vo.DemoVoMapper;
import com.frame.template.service.demo.pojo.base.demo.*;
import com.frame.template.service.demo.pojo.dto.demo.DemoUpdateXXXXXXInput;
import com.frame.template.service.demo.service.DemoService;
import com.gstdev.cloud.base.definition.domain.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author: xxxxx
 * @date: 2022/12/6
 * @description:
 */
@RestController
/**
 * 包名定义-----域名/公司/组织名（项目）/service/服务名称/文件类型名如当前controller
 * 类命名规范使用驼峰命名，大部分是实体类名加当前文件夹名称  DemoController
 * 第一层路径名称就是你的实体类的名字  demo-xxx-xxx
 *
 * 方法名命名规范
 * RESTful 风格
 * 每次创建类时，默认出现五个接口，如下方前五个示例
 * @GetMapping("/page") 根据                DemoQueryCriteria 进行查询，是分页查询
 * @PostMapping 默认的新增功能       DemoInsertInput 这个类的命名规则为  实体类名+方法名+类型
 * @PutMapping 默认的修改功能       DemoUpdateInput 这个类的命名规则为  实体类名+方法名+类型
 * @GetMapping 根据id获取数据
 * @DeleteMapping 根据id获取数据
 * 以上五个为默认生成，原则上不可擅自修改（不强制，但是每个方法的中的对象以及命名须按照规范来），是公用方法，在命名和使用上注意，
 * 以上方法中的类或者变量名称等只需把demo换成你的实体类名就可----------------------------
 *
 * 除了以上的方法其他的所有的业务方法要写在我的注释下面
 * //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
 *
 *  查询用@GetMapping("/")
 *  添加  @PostMapping
 *  修改  @PutMapping
 *  删除  @DeleteMapping
 *
 *  方法命名
 *  查询
 *  直接获取数据不写业务
 *  find     如果是直接通过正常的jpa规则能查出的数据用find，使用find的方法不可以自己自定义业务，
 *  如findById,findByName,findByNameAndcodeGroupByNameAsc
 *
 *
 *  查询一条findByxxx           实体字段名称  查单条数据用find
 *  查询一条findAllxxxx         如果查询多条数据用开头
 *
 *  需要写自定义业务
 *  xxx就是你的业务操作名称不强制
 *  使用getByxxxxxx
 *  返回多条使用getAllByxxxxxx
 *
 *  新增数据用 insert开头   insertxxxx
 *  更新数据用 update开头   updatexxxx
 *
 *  删除单条 deleteByxxx
 *  删除多条 deleteAllByxxx
 *
 *
 *
 */
@RequestMapping("/demo")
public class DemoController extends BasePOJOController<DemoService, DemoVoMapper, DemoVo, DemoDto, DemoInsertInput, DemoUpdateInput, DemoPageQueryCriteria, DemoFindAllByQueryCriteria> {

    @Resource
    private DemoService demoService;

    @Resource
    private DemoVoMapper demoVoMapper;

    public DemoController(DemoService demoService, DemoVoMapper demoVoMapper) {
        super(demoService, demoVoMapper);
        this.demoService = demoService;
        this.demoVoMapper = demoVoMapper;
    }

    @PutMapping("/update-xxx-xxx")
    public Result<Object> updatexxxxxx(@RequestBody DemoUpdateXXXXXXInput demoUpdateXXXXXXInput) {
        try {
            return demoService.updatexxxxxx(demoUpdateXXXXXXInput);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }


    /**
     * 以下两个个接口demo我们平常几乎不会出现，我们使用page,或者findAll接口就可以满足，下面的接口在开发时可以删除
     *
     * @param name
     * @return
     */
    @GetMapping("/find-by-name")
    public Result<Object> findByName(String name) {
        try {
            return Result.success(demoService.findByName(name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }

    @GetMapping("/find-by-name-and-code")
    public Result<Object> findByNameAndCode(String name, String code) {
        try {
            return Result.success(demoService.findByNameAndCode(name, code));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }

    @GetMapping("/find-all-by-name")
    public Result<Object> findAllByName(String name) {
        try {
            return Result.success(demoService.findAllByName(name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }

    /**
     * 如果想抛出自己的异常内容的话写使用下面这种方式
     * <p>
     * 如果是多个表联查，你返回的dto是哪个就这对应的dto----controller写
     * 原则上一个方法一个dto
     * 实际使用中可以公用一个
     *
     * @param name
     * @return
     */
    @GetMapping("/get-current-login-information")
    public Result<Object> getCurrentLoginInformation(String name) {
        try {
            return Result.success(demoService.getCurrentLoginInformation(name));
        } catch (CommonException commonException) {
            commonException.printStackTrace();
            return Result.failure(commonException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }


}
