package com.hao.controller;//package com.gccloud.idc.model.controller;
//
//import com.gccloud.idc.model.common.dto.es.CommonSearchDto;
//import com.gccloud.idc.model.common.dto.es.UserDto;
//import com.gccloud.idc.model.common.dto.es.UserSearchDto;
//import com.gccloud.idc.model.common.entity.es.UserEntity;
//import com.gccloud.idc.model.service.service.impl.EsUserServiceImpl;
//import com.gccloud.starter.common.utils.BeanConvertUtils;
//import com.gccloud.starter.common.vo.R;
//import com.gccloud.starter.mybatis.page.PageVO;
//import com.gccloud.starter.plugins.validator.ValidatorUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//
///**
// * 用户es操作controller层
// */
//@Slf4j
//@Api(tags = "用户信息")
//@RestController
//@RequestMapping("/user")
//public class EsUserController {
//
//    @Resource
//    private EsUserServiceImpl esUserService;
//
//    /**
//     * 新增用户数据
//     *
//     * @param dto
//     * @return
//     */
//    @PostMapping("/save")
//    @ApiOperation(value = "新增es用户数据", position = 40, notes = "新增es用户数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<String> save(@RequestBody UserDto dto) {
//        // 校验索引是否存在
//        if (StringUtils.isBlank(dto.getIndexName())) {
//            return R.error("索引不能为空");
//        }
//        if (!esUserService.existUserIndex(dto.getIndexName())) {
//            return R.error("索引不存在");
//        }
//        // 传参校验
//        UserEntity user = BeanConvertUtils.convert(dto, UserEntity.class);
//        ValidatorUtils.validateEntity(user);
//        String id = esUserService.save(user, dto.getIndexName());
//        return R.success(id);
//    }
//
//    /**
//     * 分页
//     *
//     * @param dto
//     * @return
//     */
//    @GetMapping("/page")
//    @ApiOperation(value = "用户es数据分页", position = 50, notes = "用户es数据分页", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<PageVO<UserEntity>> page(UserSearchDto dto) {
//        // 校验索引名称
//        if (StringUtils.isBlank(dto.getIndexName())) {
//            return R.error("索引不能为空");
//        }
//        if (!esUserService.existUserIndex(dto.getIndexName())) {
//            return R.error("索引不存在");
//        }
//        PageVO<UserEntity> page = esUserService.page(dto);
//        return R.success(page);
//    }
//
//    /**
//     * 根据id删除
//     *
//     * @param dto
//     * @return
//     */
//    @PostMapping("/delete")
//    @ApiOperation(value = "根据id删除用户es数据", position = 80, notes = "根据id删除用户es数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<Boolean> deleteById(@RequestBody CommonSearchDto dto) {
//        // 校验索引名称
//        if (StringUtils.isBlank(dto.getIndexName())) {
//            return R.error("索引不能为空");
//        }
//        if (!esUserService.existUserIndex(dto.getIndexName())) {
//            return R.error("索引不存在");
//        }
//        Boolean deleteById = esUserService.deleteById(dto);
//        return R.success(deleteById);
//    }
//
//    /**
//     * 根据id获取
//     *
//     * @param dto
//     * @return
//     */
//    @PostMapping("/getById")
//    @ApiOperation(value = "根据id获取用户es数据", position = 70, notes = "根据id获取用户es数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<UserEntity> getById(@RequestBody CommonSearchDto dto) {
//        // 校验索引名称
//        if (StringUtils.isBlank(dto.getIndexName())) {
//            return R.error("索引不能为空");
//        }
//        if (!esUserService.existUserIndex(dto.getIndexName())) {
//            return R.error("索引不存在");
//        }
//        UserEntity byId = esUserService.getById(dto);
//        return R.success(byId);
//    }
//
//    /**
//     * 更新用户es数据
//     *
//     * @param dto
//     * @return
//     */
//    @PostMapping("/update")
//    @ApiOperation(value = "更新用户es数据", position = 60, notes = "更新用户es数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<Boolean> update(@RequestBody UserDto dto) {
//        // 校验索引名称
//        if (StringUtils.isBlank(dto.getIndexName())) {
//            return R.error("索引不能为空");
//        }
//        if (!esUserService.existUserIndex(dto.getIndexName())) {
//            return R.error("索引不存在");
//        }
//        // 传参校验
//        UserEntity user = BeanConvertUtils.convert(dto, UserEntity.class);
//        ValidatorUtils.validateEntity(user);
//        Boolean update = esUserService.update(user, dto.getIndexName());
//        return R.success(update);
//    }
//
//    /**
//     * 创建用户索引
//     */
//    @GetMapping("/createUserIndex")
//    @ApiOperation(value = "创建索引", position = 10, notes = "创建用户索引", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<Boolean> createUserIndex(String indexName) {
//        // 判断索引是否存在
//        if (esUserService.existUserIndex(indexName)) {
//            return R.error("索引已存在");
//        }
//        Boolean created = esUserService.createIndexByRest(indexName);
//        return R.success(created);
//    }
//
//    /**
//     * 判断索引是否存在
//     */
//    @GetMapping("/existIndex")
//    @ApiOperation(value = "判断索引是否存在", position = 20, notes = "判断索引是否存在", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<Boolean> existIndex(String indexName) {
//        Boolean exist = esUserService.existUserIndex(indexName);
//        return R.success(exist);
//    }
//
//    /**
//     * 删除索引
//     */
//    @PostMapping("/deleteIndex")
//    @ApiOperation(value = "删除索引", position = 30, notes = "删除索引", produces = MediaType.APPLICATION_JSON_VALUE)
//    public R<Boolean> deleteIndex(String indexName) {
//        // 判断索引是否存在
//        if (!esUserService.existUserIndex(indexName)) {
//            return R.error("索引不存在");
//        }
//        Boolean deleteIndex = esUserService.deleteIndex(indexName);
//        return R.success(deleteIndex);
//    }
//
//}
