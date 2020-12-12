package com.sicnu.service;

import com.sicnu.pojo.Outlay;
import com.sicnu.util.Result;

import java.util.List;

public interface OutlayService {
    Result addOutlay(Outlay outlay);
    Result selectAllOutlay();
    Result delOutlayById(Integer outlay_id);
    Result updateOutlay(Outlay outlay);
}
