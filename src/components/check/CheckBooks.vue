<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>审核申请</el-breadcrumb-item>
      <el-breadcrumb-item>审核著作成果申请</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card v-loading="isLoading">
      <!-- 待审核著作成果表格 -->
      <el-table :data="booksList" style="width: 100%" border :header-cell-style="{ background: '#f5f7fa' }">
        <!-- 序号列 -->
        <el-table-column type="index" label="#" align="center" fixed></el-table-column>
        <!-- 多选列 -->
        <el-table-column type="selection" align="center" fixed></el-table-column>
        <el-table-column prop="book_name" label="著作题目" width="300px" align="center" fixed>
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">著作题目</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.book_name" placeholder="请输入" @change="getBookList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="authorName" label="著作作者" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">著作作者</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="getBookList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="press" label="出版社" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">出版社</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.press" placeholder="请输入" @change="getBookList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="pl_name" label="出版社级别" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">出版社级别</div>
            <el-select class="columnInput" v-model="queryInfo.pl" size="mini" placeholder="请选择" @change="getBookList">
              <el-option v-for="item in plList" :key="item.pl_id" :label="item.pl_name" :value="item.pl_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="bt_name" label="著作类型" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">著作类型</div>
            <el-select class="columnInput" v-model="queryInfo.bt" size="mini" placeholder="请选择" @change="getBookList">
              <el-option v-for="item in btList" :key="item.bt_id" :label="item.bt_name" :value="item.bt_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="pp_name" label="出版地" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">出版地</div>
            <el-select class="columnInput" v-model="queryInfo.pp_id" size="mini" placeholder="请选择" @change="getBookList">
              <el-option v-for="item in ppList" :key="item.pp_id" :label="item.pp_name" :value="item.pp_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">发表日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" @change="getBookList" v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"> </el-date-picker>
          </template>
        </el-table-column>

        <el-table-column prop="isbn" label="ISBN号" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">ISBN号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.isbn" placeholder="请输入" @change="getBookList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="word_number" label="总字数" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">总字数</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.word_number" placeholder="请输入" @change="getBookList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="trans" label="是否翻译" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">总字数</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.trans" placeholder="请输入" @change="getBookList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="language_name" label="翻译语种" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">翻译语种</div>
            <el-select @change="getBookList" class="columnInput" v-model="queryInfo.language_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in languageList" :key="item.language_id" :label="item.language_name" :value="item.language_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">学科门类</div>
            <el-select @change="getBookList" class="columnInput" v-model="queryInfo.sc_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">一级学科</div>
            <el-select @change="getBookList" class="columnInput" v-model="queryInfo.subject" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">归属单位</div>
            <el-select @change="getBookList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="sd_name" label="项目来源" width="250px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目来源</div>
            <el-select @change="getBookList" class="columnInput" v-model="queryInfo.sd_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="rt_name" label="研究类别" width="250px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">研究类别</div>
            <el-select @change="getBookList" class="columnInput" v-model="queryInfo.rt_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in rtList" :key="item.rt_id" :label="item.rt_name" :value="item.rt_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showCheckBookDialog(scope.row.be_id)">审核</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </el-card>

    <el-dialog title="审核著作成果申请" :visible.sync="checkBookDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="著作题目：">{{ bookInfo.book_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="著作编号：">{{ bookInfo.be_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="著作类别：">{{ bookInfo.bt_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="负责人：">{{ bookInfo.user_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="出版地：">{{ bookInfo.pp_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="出版社：">{{ bookInfo.press }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="出版社级别：">{{ bookInfo.pl_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="学科门类：">{{ bookInfo.sc_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="一级学科：">{{ bookInfo.subject_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="归属单位：">{{ bookInfo.aod_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="来源单位：">{{ bookInfo.sd_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="ISBN号：">{{ bookInfo.isbn }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="总字数：">{{ bookInfo.word_number }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="是否翻译：">{{ bookInfo.trans }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="翻译语种：">{{ bookInfo.language_name }}</el-form-item></el-col
          >
        </el-row>

        <el-row>
          <el-col :span="12"
            ><el-form-item label="发表时间：">{{ bookInfo.publish_time }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="研究类别：">{{ bookInfo.rt_name }}</el-form-item></el-col
          >
        </el-row>
        <el-form-item label="作者:" prop="author" size="mini">
        </el-form-item>
        <el-table :data="memberList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
          :header-cell-style="{ background: '#f5f7fa' }" :default-sort="{prop: 'contribution', order: 'descending'}">
          <el-table-column type="index" label="#" align="center"></el-table-column>
          <el-table-column prop="name" label="作者姓名" align="center"></el-table-column>
          <el-table-column prop="role_name" label="成员类型" align="center"></el-table-column>
          <el-table-column label="归属单位" align="center">
            <template slot-scope="scope">{{ departmentObj[scope.row.department_id] }}</template>
          </el-table-column>
          <el-table-column prop="contribution" label="贡献率" align="center">
            <template slot-scope="scope">{{scope.row.contribution + '%'}}</template>
          </el-table-column>
        </el-table>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="denyBookDialogVisible = true">驳 回</el-button>
        <el-button type="primary" @click="approveBook">通 过</el-button>
        <el-button @click="checkBookDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <el-dialog title="填写驳回理由" :visible.sync="denyBookDialogVisible" width="30%" @closed="denyBookDialogClosed">
      <el-form :model="denyInfo" :rules="denyInfoRules" ref="denyInfoRef" label-width="100px" label-position="top">
        <el-form-item label="驳回理由：" prop="reason">
          <el-input type="textarea" v-model="denyInfo.reason"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="denyBookDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="denyBook">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { stringify } from 'qs';
export default {
  data() {
    return {
      //待审核著作成果目信息列表对象
      booksList: [],
      // 搜索条件表单
      queryInfo: {
        book_id: "",
        leader_id: "",
        book_name: "",
        press: "",
        pl_id: "",
        bt_id: "",
        pp_id: "",
        isbn: "",
        word_number: "",
        trans: "",
        language_id: "",
        sc_id: "",
        subject_id: "",
        publish_time: "",
        aod_id: "",
        sd_id: "",
        rt_id: "",
        apply_time: "",

        // 当前页码
        pageNum: 1,
        // 当前页大小
        pageSize: 2,
      },

      // 总的数据条数
      total: 2,
      // 查询条件：发表日期
      publish_time: null,
      // 单位 id:name 对象
      departmentObj: {},
      // 单位对象列表
      departmentList: [],
      // 学科门类 id:name 对象
      scObj: {},
      // 学科门类列表
      scList: [],
      // 一级学科 id:name 对象
      subjectObj: {},
      // 一级学科列表
      subjectList: [],

      // 出版社等级
      plObj: {},
      plList: [],
      // 著作类型
      btObj: {},
      btList: [],
      // 出版地
      ppObj: {},
      ppList: [],
      // 语种
      languageObj: {},
      languageList: [],
      // 研究类型
      rtObj: {},
      rtList: [],

      // 审核对话框的显示与隐藏控制变量
      checkBookDialogVisible: false,
      // 著作成果具体信息
      bookInfo: {},
      // 填写驳回理由对话框的显示与隐藏控制变量
      denyBookDialogVisible: false,
      // 驳回理由表单对象
      denyInfo: {
        reason: "",
      },
      // 驳回理由表单验证对象
      denyInfoRules: {
        reason: [{ required: true, message: "请填写驳回理由！", trigger: "blur" }],
      },
      memberList: [],
      isLoading: false
    };
  },
  async created() {
    await this.getBookData();
    this.getBookList();
    console.log(this.booksList)
  },
  methods: {
    // 获取著作成果列表
    async getBookData() {
      this.isLoading = true
      // 获取单位列表
      const { data: res1 } = await this.$http.post("/department/findAllDepartment");
      this.departmentList = res1.data;
      // 构造单位 id:name 对象
      this.departmentList.forEach((item) => (this.departmentObj[item.department_id] = item.department_name));
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post("/category/findAllSubjectCategory");
      this.scList = res2.data;
      // 构造学科门类 id:name 对象
      this.scList.forEach((item) => (this.scObj[item.sc_id] = item.sc_name));
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post("/subject/findAllSubject");
      this.subjectList = res3.data;
      // 构造一级学科 id:name 对象
      this.subjectList.forEach((item) => (this.subjectObj[item.subject_id] = item.subject_name));

      // 获取出版社等级列表
      const { data: res5 } = await this.$http.post("/pressLevel/findAllPressLevel");
      this.plList = res5.data;
      // 构造 出版社等级对象
      this.plList.forEach((item) => (this.plObj[item.pl_id] = item.pl_name));
      // 构造 著作类型
      const { data: res6 } = await this.$http.post("/bookType/findAllBookType");
      this.btList = res6.data;
      this.btList.forEach((item) => (this.btObj[item.bt_id] = item.bt_name));
      // 构造 出版地
      const { data: res7 } = await this.$http.post("/publicationPlace/findAllPublicationPlace");
      this.ppList = res7.data;
      this.ppList.forEach((item) => (this.ppObj[item.pp_id] = item.pp_name));
      // 构造语种
      const { data: res8 } = await this.$http.post("/language/findAllLanguage");
      this.languageList = res8.data;
      this.languageList.forEach((item) => (this.languageObj[item.language_id] = item.language_name));
      // 构造研究类型
      const { data: res9 } = await this.$http.post("/researchType/findAllResearchType");
      this.rtList = res9.data;
      this.rtList.forEach((item) => (this.rtObj[item.rt_id] = item.rt_name));
      this.isLoading = false
    },

    // 获取著作成果列表
    async getBookList() {
      this.isLoading = true
      // 通过 post 请求获取著作成果列表
      if(this.queryInfo.publish_time !== '') {
        this.queryInfo.publish_time_start = this.queryInfo.publish_time[0]
        this.queryInfo.publish_time_end = this.queryInfo.publish_time[1]
      } else this.queryInfo.publish_time_start = this.queryInfo.publish_time_end = ''
      if(this.queryInfo.apply_time !== '') {
        this.queryInfo.apply_time_start = this.queryInfo.apply_time[0]
        this.queryInfo.apply_time_end = this.queryInfo.apply_time[1]
      } else this.queryInfo.apply_time_start = this.queryInfo.apply_time_end = ''
      const { data: res } = await this.$http.post("/bookExamine/selectAllBookExamineByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }      
      this.total = res.data[0].total;
      this.booksList = res.data[1];
      // 通过 id:name 对象处理著作成果列表，为其加上各name属性，截取时间日期
      this.booksList.forEach(item => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.sc_name = this.scObj[item.sc_id];
        item.subject_name = this.subjectObj[item.subject_id];
        item.sd_name = this.departmentObj[item.sd_id];
        item.publish_time = item.publish_time.substring(0, 10);
        item.pl_name = this.plObj[item.pl_id];
        item.pp_name = this.ppObj[item.pp_id];
        item.bt_name = this.btObj[item.bt_id];
        item.rt_name = this.rtObj[item.rt_id];
        item.language_name = this.languageObj[item.language_id];
      });
      this.isLoading = false
    },

    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.queryInfo.pageNum = 1;
      this.getBookList();
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val;
      this.getBookList();
    },
    // 点击审核按钮，显示审核著作成果对话框
    async showCheckBookDialog(bookId) {
      console.log(bookId)
      const { data: res } = await this.$http.post("/bookExamine/findBookExamineById", this.$qs.stringify({ be_id: bookId }));
      if (res.status !== "200") return this.$message.error("获取待审核著作成果信息失败");
      this.bookInfo = res.data;
      const { data: res2 } = await this.$http.post('/teamExamine/selectBookTeamExamineUser', stringify({ be_id: bookId }))
      if( res2.status !== '200' ) return this.$message.error('获取待审核著作成果团队信息失败')
      this.memberList = res2.data
      this.bookInfo.aod_name = this.departmentObj[this.bookInfo.aod_id];
      this.bookInfo.sc_name = this.scObj[this.bookInfo.sc_id];
      this.bookInfo.subject_name = this.subjectObj[this.bookInfo.subject_id];
      this.bookInfo.sd_name = this.departmentObj[this.bookInfo.sd_id];
      this.bookInfo.publish_time = this.bookInfo.publish_time.substring(0, 10);
      this.bookInfo.pl_name = this.plObj[this.bookInfo.pl_id];
      this.bookInfo.pp_name = this.ppObj[this.bookInfo.pp_id];
      this.bookInfo.bt_name = this.btObj[this.bookInfo.bt_id];
      this.bookInfo.rt_name = this.rtObj[this.bookInfo.rt_id];
      this.bookInfo.language_name = this.languageObj[this.bookInfo.language_id];
      this.checkBookDialogVisible = true;
    },
    // 点击通过按钮，通过该待审核著作成果的申请
    async approveBook() {
      const res = await this.$confirm("是否确定批准该著作成果申请？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (res === "cancel") return this.$message.info("取消了本次操作");
      var postObj = JSON.parse(JSON.stringify(this.bookInfo))
      postObj.checkMessage = 'success'
      const { data: res2 } = await this.$http.post("/book/addBook", this.$qs.stringify(postObj));
      if (res2.status !== "200") return this.$message.error("批准著作成果申请失败");
      this.$message.success("批准著作成果申请成功");
      this.getBookList();
      this.checkBookDialogVisible = false;
    },
    // 点击驳回按钮，驳回该著作成果申请
    denyBook() {
      this.$refs.denyInfoRef.validate(async (valid) => {
        if (!valid) return;
        var postObj = JSON.parse(JSON.stringify(this.bookInfo))
        postObj.checkMessage = 'fail'
        postObj.message = this.denyInfo.reason
        const { data: res } = await this.$http.post("/book/addBook", this.$qs.stringify(postObj))
        if (res.status !== "200") return this.$message.error("驳回著作成果申请失败");
        this.denyBookDialogVisible = false;
        this.$message.success("驳回著作成果申请成功");
        this.getBookList();
        this.checkBookDialogVisible = false;
      });
    },
    // 关闭驳回理由填写对话框，清空表单
    denyBookDialogClosed() {
      this.$refs.denyInfoRef.resetFields();
    },
  },
};
</script>

<style lang="less" scoped></style>
