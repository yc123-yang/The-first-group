package com.sicnu.service.impl;

import com.sicnu.mapper.PatentMapper;
import com.sicnu.pojo.Patent;
import com.sicnu.service.PatentService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专利
 */
@Service
public class PatentServiceImpl implements PatentService {
    @Resource
    PatentMapper patentMapper;

    private Result rs = null;
    /**
     * 添加专利
     * @param patent:专利申请信息
     * @return
     */
    @Override
    public Result addPatent(Patent patent) {
        Patent patent1 = patentMapper.selectPatentByNumber(patent.getApplication_number(),patent.getPublic_number(),patent.getAuthorization_number());
        if (patent1!=null){
            rs = new Result("401","该专利编号已经存在",null);
        }else{
            patentMapper.addPatent(patent);
            rs = new Result("200","专利注册成功",null);
        }
        return rs;
    }

    /**
     * 根据条件查询专利
     * @param patent
     * @param application_time_start
     * @param application_time_end
     * @param public_time_start
     * @param public_time_end
     * @param authorization_time_start
     * @param authorization_time_end
     * @param pageSize
     * @param pageNum
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end,Integer pageSize,Integer pageNum) throws ParseException {
        Map<String,Object> map = new HashMap<>();
        map.put("patent_name",patent.getPatent_name());
        map.put("leader_id",patent.getLeader_id());
        map.put("pt_id",patent.getPt_id());
        map.put("pr_id",patent.getPr_id());
        map.put("ps_id",patent.getPs_id());
        map.put("aod_id",patent.getAod_id());
        map.put("application_number",patent.getApplication_number());
        map.put("public_number",patent.getPublic_number());
        map.put("authorization_number",patent.getApplication_number());
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ( !application_time_start.equals("")){
            map.put("application_time_start",sdf.parse(application_time_start));
        }
        if ( !application_time_end.equals("")){
            map.put("application_time_end",sdf.parse(application_time_end));
        }
        if ( !public_time_start.equals("")){
            map.put("public_time_start",sdf.parse(public_time_start));
        }
        if ( !public_time_end.equals("")){
            map.put("public_time_end",sdf.parse(public_time_end));
        }
        if ( !authorization_time_start.equals("")){
            map.put("authorization_time_start",sdf.parse(authorization_time_start));
        }
        if ( !authorization_time_end.equals("")){
            map.put("authorization_time_end",sdf.parse(authorization_time_end));
        }
        System.out.println(map);
        List<Patent> patents = patentMapper.selectPatentByCondition(map);
        return rs = new Result("200",null,patents);
    }

    /**
     * 更新专利
     * @param patent
     * @return
     */
    @Override
    public Result updatePatent(Patent patent) {
        patentMapper.updatePatent(patent);
        rs = new Result("200","修改成功",null);
        return rs;
    }

    /**
     * 删除
     * @param patent_id
     * @return
     */
    @Override
    public Result delPatentById(Integer patent_id) {
        patentMapper.delPatentById(patent_id);
        rs =new Result("200","删除成功",null);
        return rs;
    }


}
