package com.ackerframework.admin.filem.base.service;

import com.ackerframework.admin.filem.base.dao.PerformerDao;
import com.ackerframework.admin.filem.base.entity.Performer;
import com.ackerframework.base.service.BaseService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformerService extends BaseService<PerformerDao, Performer> {

    public JSONArray getFilemPerformer(Integer filemId) {
        return this.dao.getFilemPerformers(filemId);
    }
}
