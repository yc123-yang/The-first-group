package com.sicnu.controller;

import com.sicnu.pojo.Project;
import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.util.Result;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@CrossOrigin
@RestController
public class ExcelController {

    @Resource
    private ProjectServiceImpl projectService;

    private Result rs;

    @RequestMapping(value = "/excel/companyExcel",method = RequestMethod.POST)
    @ResponseBody
    public void exportCompanyExcel( Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize,HttpServletResponse response) {
        try {
            rs = projectService.selectAllProjectByCondition(project, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize);
            List<Project> projectLists = (List<Project>) rs.getData();
            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet = wb.createSheet("委托方信息列表");

            HSSFRow row = null;

            row = sheet.createRow(0);
            row.setHeight((short) (30 * 20));
            row.createCell(0).setCellValue("委托方信息列表");


            CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
            sheet.addMergedRegion(rowRegion);
            row = sheet.createRow(1);
            row.setHeight((short) (22.50 * 20));//设置行高
            row.createCell(0).setCellValue("项目编号");//为第一个单元格设值
            row.createCell(1).setCellValue("项目名");//为第二个单元格设值
            row.createCell(2).setCellValue("项目领导人");//为第三个单元格设值
            row.createCell(3).setCellValue("所属部门");
            row.createCell(4).setCellValue("成果归属单位");
            row.createCell(5).setCellValue("学科门类");
            row.createCell(6).setCellValue("一级学科");
            row.createCell(7).setCellValue("项目性质");
            row.createCell(8).setCellValue("项目级别");
            row.createCell(9).setCellValue("项目状态");
            row.createCell(10).setCellValue("来源部门");
            row.createCell(11).setCellValue("成果形式");
            row.createCell(12).setCellValue("开始时间");
            row.createCell(13).setCellValue("计划时间");
            row.createCell(14).setCellValue("完成时间");
            row.createCell(15).setCellValue("经费");
            row.createCell(16).setCellValue("结题形式");

            for (int i = 0; i < projectLists.size(); i++) {
                row = sheet.createRow(i + 2);
                Project project1 = projectLists.get(i);
                row.createCell(0).setCellValue(project1.getProject_name());
                row.createCell(1).setCellValue(project1.getLeader_id());
                row.createCell(2).setCellValue(project1.getDepartment_id());
                row.createCell(3).setCellValue(project1.getAod_id());
                row.createCell(4).setCellValue(project1.getSc_id());
                row.createCell(5).setCellValue(project1.getSubject_id());
                row.createCell(6).setCellValue(project1.getNature_id());
                row.createCell(7).setCellValue(project1.getLevel_id());
                row.createCell(8).setCellValue(project1.getStatus_id());
                row.createCell(9).setCellValue(project1.getSd_id());
                row.createCell(10).setCellValue(project1.getAt_id());
                row.createCell(11).setCellValue(project1.getApproval_number());
                row.createCell(12).setCellValue(project1.getStart_time());
                row.createCell(13).setCellValue(project1.getPlan_time());
                row.createCell(14).setCellValue(project1.getComplete_time());
                row.createCell(15).setCellValue(project1.getOutlay());
                row.createCell(16).setCellValue(project1.getCt_id());
            }
            sheet.setDefaultRowHeight((short) (16.5 * 20));
            //列宽自适应
            for (int i = 0; i <= 13; i++) {
                sheet.autoSizeColumn(i);
            }
            String title= "company";
            String  fileName = new String(title.getBytes("GB2312"), "ISO_8859_1");
            fileName = URLEncoder.encode(fileName,"utf-8");

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            OutputStream os = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");//默认Excel名称
            wb.write(os);
            os.flush();
            os.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}