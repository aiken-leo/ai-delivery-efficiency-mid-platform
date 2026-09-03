package com.leo.ai.delivery.service.impl;

import com.leo.ai.delivery.mapper.AppMapper;
import com.leo.ai.delivery.model.entity.App;
import com.leo.ai.delivery.service.AppService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 应用 服务层实现。
 *
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

}