package com.hao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.dao.CamelCaseTestDao;
import com.hao.entity.CamelCaseTestEntity;
import com.hao.service.CamelCaseTestService;
import org.springframework.stereotype.Service;

/**
 * @author xu.liang
 * @since 2023/6/26 09:37
 */
@Service
public class CamelCaseTestServiceImpl extends ServiceImpl<CamelCaseTestDao, CamelCaseTestEntity> implements CamelCaseTestService {
}
