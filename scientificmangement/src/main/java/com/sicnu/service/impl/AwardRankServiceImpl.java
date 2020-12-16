package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.AwardRankMapper;
import com.sicnu.pojo.AwardRank;
import com.sicnu.service.AwardRankService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *项目等级字典数据操作
 */
@Service
@Slf4j
public class AwardRankServiceImpl implements AwardRankService {
    @Resource
    AwardRankMapper awardRankMapper;

    private Result rs;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result addAwardRank(String ar_name,Integer ar_score) {
        try {
            AwardRank awardRank = awardRankMapper.selectAwardRankByName(ar_name);
            if (awardRank == null){
                awardRankMapper.addAwardRank(ar_name,ar_score);
                redisUtil.del("awardRanks");
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
            redisUtil.del("awardRanks");
            rs = new Result("200","字典数据删除成功",null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllAwardRank() {
        try {
            //            rs = new Result("200",null,awardRanks);
            //            List awardLevels = new ArrayList<>();
            List awardRanks = new ArrayList<>();

            if (redisUtil.hasKey("awardRanks")) {
                log.warn("从redis中获取数据.");
                awardRanks = redisList.get("awardRanks", 0, -1);
            } else {
                awardRanks = awardRankMapper.findAllAwardRank();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("awardRanks", awardRanks);
                log.info("成功存入redis.");
            }

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
                redisUtil.del("awardRanks");
                rs = new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
