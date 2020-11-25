package com.sicnu.service.impl;

import com.sicnu.mapper.PaperTypeMapper;
import com.sicnu.pojo.PaperType;
import com.sicnu.service.PaperTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 论文类型字典操作
 */
@Service
public class PaperTypeServiceImpl implements PaperTypeService {
    Result rs = null;
    @Resource
    PaperTypeMapper paperTypeMapper;

    @Override
    public Result addPaperType(String pt_name) {
        try {
            PaperType paperType = paperTypeMapper.selectPaperTypeByName(pt_name);
            if (paperType != null) {
                rs = new Result("401", "该字典已经存在", null);
            } else {
                paperTypeMapper.addPaperType(pt_name);
                rs = new Result("200", "字典擦汗如成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPaperType(Integer pt_id) {
        try {
            paperTypeMapper.delPaperType(pt_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPaperType() {
        try {
            List<PaperType> paperTypes = paperTypeMapper.findAllPaperType();
            rs = new Result("200", null, paperTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

}
