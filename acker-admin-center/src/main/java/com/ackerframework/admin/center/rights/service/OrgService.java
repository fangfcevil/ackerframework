package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.OrgDao;
import com.ackerframework.admin.center.rights.entity.Org;
import com.ackerframework.base.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrgService extends BaseService<OrgDao, Org> {
}
