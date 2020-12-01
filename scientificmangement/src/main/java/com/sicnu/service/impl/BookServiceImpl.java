package com.sicnu.service.impl;

import com.sicnu.mapper.*;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.User;
import com.sicnu.service.BookService;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.sicnu.pojo.Book;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{


    @Resource
    BookMapper bookMapper;

    @Resource
    UserMapper userDao;

    @Resource
    BookExamineMapper bookExamineMapper;
    @Resource
    JavaMailSenderImpl mailSender;
    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;
    @Override
    public Result addBook(Book book,String checkMessage,String message) {
        try {
            //获取项目id 返给用户
            Integer awardId = bookMapper.selectBookId(book.getLeader_id(), book.getBook_name());
            //获取项目负责人信息
            User user = userDao.findUserById(book.getLeader_id());
            //创建邮件环境，反馈信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
//            Award award1 = bookMapper.selectBookByNumber(book.getApproval_number());

            //如果不通过审核反馈
            if (checkMessage.equals("fail")) {
                helper.setSubject("高校科研管理系统注册验证码");
                helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>" + message + "</span>,请您解决之后重新申请。</p>", true);
                //负责人邮箱
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核里面删除
                bookExamineMapper.delBookExamine(book.getLeader_id(),book.getBook_name());
                rs = new Result("400", "审核结果已反馈", null);
            } else {
                bookMapper.addBook(book);
                helper.setSubject("高校科研管理系统注册验证码");
                helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>" + awardId + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核删除
                bookExamineMapper.delBookExamine(book.getLeader_id(),book.getBook_name());
                rs = new Result("200", "审核结果已反馈", null);
            }
//            Book book1 = bookMapper.selectBookByNumber(book.getPublish_time());
//            if (book1 != null) {
//
//            } else {
//                bookMapper.addBook(book);
//                rs = new Result("200", "插入成功", null);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


    @Override
    public Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;

        try {
            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();

            Map<String, Object> map = new HashMap<>();
            map.put("book_name", book.getBook_name());
            map.put("leader_id", uid);
            map.put("press", book.getPress());
            map.put("pl_id", book.getPl_id());
            map.put("bt_id", book.getBt_id());
            map.put("pp_id", book.getPp_id());
            map.put("isbn", book.getIsbn());
            map.put("word_number", book.getWord_number());
            map.put("translate", book.getTrans());
            map.put("language_id", book.getLanguage_id());
            map.put("sc_id", book.getSc_id());
            map.put("subject_id", book.getSubject_id());
            map.put("aod_id", book.getAod_id());
            map.put("sd_id", book.getSd_id());
            map.put("rt_id", book.getRt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            List<Book> books = bookMapper.selectBookByCondition(map);
            Integer total = bookMapper.selectTotalBook(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(books);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result delBook(Integer book_id) {
        try {
            bookMapper.delBook(book_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateBook(Book book) {
        try {
            bookMapper.updateBook(book);
            rs = new Result("200","更新成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAllBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("book_name", book.getBook_name());
            map.put("press", book.getPress());
            map.put("pl_id", book.getPl_id());
            map.put("bt_id", book.getBt_id());
            map.put("pp_id", book.getPp_id());
            map.put("isbn", book.getIsbn());
            map.put("word_number", book.getWord_number());
            map.put("translate", book.getTrans());
            map.put("language_id", book.getLanguage_id());
            map.put("sc_id", book.getSc_id());
            map.put("subject_id", book.getSubject_id());
            map.put("aod_id", book.getAod_id());
            map.put("sd_id", book.getSd_id());
            map.put("rt_id", book.getRt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            List<Book> books = bookMapper.selectBookByCondition(map);
            Integer total = bookMapper.selectTotalBook(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(books);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }
}
