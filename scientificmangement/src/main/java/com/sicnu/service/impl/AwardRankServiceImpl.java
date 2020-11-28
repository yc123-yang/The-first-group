package com.sicnu.service.impl;

import com.sicnu.mapper.AwardRankMapper;
import com.sicnu.pojo.AwardRank;
import com.sicnu.service.AwardRankService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *项目等级字典数据操作
 */
@Service
public class AwardRankServiceImpl implements AwardRankService {
    @Resource
    AwardRankMapper awardRankMapper;

    private Result rs;

    @Override
    public Result addAwardRank(String ar_name) {
        try {
            AwardRank awardRank = awardRankMapper.selectAwardRankByName(ar_name);
            if (awardRank == null){
                awardRankMapper.addAwardRank(ar_name);
                rs = new Result("200","插入字典数据成功",null);
            }else{
                rs = new Result("400","字典数据已经存在，切勿重复插入",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delAwardRank(Integer ar_id) {
        try {
            awardRankMapper.delAwardRank(ar_id);
            rs = new Result("200","字典数据删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllAwardRank() {
        try {
            List<AwardRank> awardRanks = awardRankMapper.findAllAwardRank();
            rs = new Result("200",null,awardRanks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }



    @Override
    public Result updateAwardRank(AwardRank awardRank) {

        try {
            if (awardRank.getAr_name().equals("")){
                rs = new Result("400","更改的字典数据不能为空",null);
            }else{
                awardRankMapper.updateAwardRank(awardRank);
                rs = new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
