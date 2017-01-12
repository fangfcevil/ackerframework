package com.ackerframework.admin.filem.base.service;

import com.ackerframework.admin.filem.base.dao.RatingDao;
import com.ackerframework.admin.filem.base.entity.Rating;
import com.ackerframework.base.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends BaseService<RatingDao, Rating> {
}
