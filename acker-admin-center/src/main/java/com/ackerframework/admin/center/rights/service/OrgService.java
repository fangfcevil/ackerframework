package com.ackerframework.admin.center.rights.service;

import com.ackerframework.admin.center.rights.dao.OrgDao;
import com.ackerframework.admin.center.rights.entity.Org;
import com.ackerframework.base.entity.Result;
import com.ackerframework.base.service.BaseTreeService;
import com.ackerframework.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrgService extends BaseTreeService<OrgDao, Org> {

    @Override
    public Result preInsert(Org org) {
        return validateOrg(org);
    }

    @Override
    public Result preUpdate(Org org) {
        return validateOrg(org);
    }

    private Result validateOrg(Org org) {
        if (StringUtils.isBlank(org.getText())) {
            return new Result(false, "导航名称不能为空!");
        }
        if (StringUtils.isBlank(org.getTypes())) {
            return new Result(false, "导航类型不能为空!");
        }
        return new Result();
    }

    //初始化父节点下拉框
    public List<Org> initComboParentTree(Integer id) {
        List<Org> orgs = new ArrayList<>();
        Org org = new Org();
        org.setId(0);
        org.setText("无");
        org.setChildren(this.initComboParentTree(0, id));
        orgs.add(org);
        return orgs;
    }
}
