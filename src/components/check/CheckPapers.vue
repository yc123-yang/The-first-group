<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>审核申请</el-breadcrumb-item>
      <el-breadcrumb-item>审核论文成果申请</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 待审核论文成果表格 -->
    <el-table :data="papersList" style="width: 100%" border  :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>
      <el-table-column prop="paper_name" label="论文题目" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">论文题目</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.paper_name" placeholder="请输入" @change="getPaperList"> </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="authorName" label="论文作者" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">论文作者</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="getPaperList"> </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="periodical_name" label="发表期刊" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发表期刊</div>
          <el-select class="columnInput" v-model="queryInfo.periodical_id" size="mini" clearable placeholder="请选择" @change="getPaperList">
            <el-option v-for="item in periodicalList" :key="item.periodical_id" :label="item.periodical_name" :value="item.periodical_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发表日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="getPaperList"> </el-date-picker>
        </template>
      </el-table-column>

      <el-table-column prop="pt_name" label="论文类型" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">论文类型</div>
          <el-select class="columnInput" v-model="queryInfo.pt_id" clearable size="mini" collapse-tags placeholder="请选择" @change="getPaperList">
            <el-option v-for="item in ptList" :key="item.pt_id" :label="item.pt_name" :value="item.pt_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="include_number" label="收录号" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">收录号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.include_number" placeholder="请输入" @change="getPaperList"> </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">学科门类</div>
          <el-select class="columnInput" v-model="queryInfo.sc_id" clearable size="mini" collapse-tags placeholder="请选择" @change="getPaperList">
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">一级学科</div>
          <el-select class="columnInput" v-model="queryInfo.subject_id" clearable size="mini" collapse-tags placeholder="请选择" @change="getPaperList">
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">归属单位</div>
          <el-select class="columnInput" v-model="queryInfo.aod_id" clearable size="mini" collapse-tags placeholder="请选择" @change="getPaperList">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="sd_name" label="来源单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">来源单位</div>
          <el-select class="columnInput" v-model="queryInfo.sd_id" size="mini" @change="getPaperList" collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">备注</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.remark" placeholder="请输入"> </el-input>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="120" fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showCheckPaperDialog(scope.row.pe_id)">审核</el-button>
          </template>
        </el-table-column>

    </el-table>
    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </el-card>

    <el-dialog title="审核论文成果申请" :visible.sync="checkPaperDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="论文题目：">{{ paperInfo.paper_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="论文编号：">{{ paperInfo.pe_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="论文类型：">{{ paperInfo.pt_name}}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="负责人：">{{ paperInfo.leader_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="发表期刊：">{{ paperInfo.periodical_name}}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="发表时间：">{{ paperInfo.publish_time}}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="收录号：">{{ paperInfo.include_number }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="学科门类：">{{ paperInfo.sc_name}}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="一级学科：">{{ paperInfo.subject_name}}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="归属单位：">{{ paperInfo.aod_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="来源单位：">{{ paperInfo.sd_name }}</el-form-item></el-col
          >
        </el-row>



      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="denyPaperDialogVisible = true">驳 回</el-button>
        <el-button type="primary" @click="approvePaper">通 过</el-button>
        <el-button @click="checkPaperDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <el-dialog title="填写驳回理由" :visible.sync="denyPaperDialogVisible" width="30%" @closed="denyPaperDialogClosed">
      <el-form :model="denyInfo" :rules="denyInfoRules" ref="denyInfoRef" label-width="100px" label-position="top">
        <el-form-item label="驳回理由：" prop="reason">
          <el-input type="textarea" v-model="denyInfo.reason"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="denyPaperDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="denyPaper">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //待审核论文成果目信息列表对象
      papersList: [],
      // 搜索条件表单
      queryInfo: {
        paper_id: "",
        paper_name: "",
        pt_id: "",
        periodical_id: "",
        publish_time: "",
        include_number: "",
        sc_id: "",
        subject_id: "",
        aod_id: "",
        sd_id: "",
        remark: "",
        // 当前页码
        pageNum: 1,
        // 当前页大小
        pageSize: 10,
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
      // 期刊 id:name对象
      periodicalObj: {},
      // 期刊列表
      periodicalList: [],
      //论文类型 id对象
      ptObj: {},
      //论文类型 列表
      ptList: [],
      // 审核对话框的显示与隐藏控制变量
      checkPaperDialogVisible: false,
      // 论文成果具体信息
      paperInfo: {},
      // 填写驳回理由对话框的显示与隐藏控制变量
      denyPaperDialogVisible: false,
      // 驳回理由表单对象
      denyInfo: {
        reason: "",
      },
      // 驳回理由表单验证对象
      denyInfoRules: {
        reason: [{ required: true, message: "请填写驳回理由！", trigger: "blur" }],
      },

    };
  },
  async created() {
    await this.getPaperData();
    await this.getPaperList();
    this.papersList = JSON.parse(JSON.stringify(this.papersList))
  },
  methods: {
    // 获取论文成果列表
    async getPaperData() {
       // 获取单位列表
      const { data: res1 } = await this.$http.post('/department/findAllDepartment')
      this.departmentList = res1.data
      // 构造单位 id:name 对象
      this.departmentList.forEach(item => this.departmentObj[item.department_id] = item.department_name)
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post('/category/findAllSubjectCategory')
      this.scList = res2.data
      // 构造学科门类 id:name 对象
      this.scList.forEach(item => this.scObj[item.sc_id] = item.sc_name)
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post('/subject/findAllSubject')
      this.subjectList = res3.data
      // 构造一级学科 id:name 对象
      this.subjectList.forEach(item => this.subjectObj[item.subject_id] = item.subject_name)
      // 获取期刊列表
      const { data: res4 } = await this.$http.post('/periodical/findAllPeriodical')
      this.periodicalList = res4.data
      // 构造 期刊 对象
      this.periodicalList.forEach(item => this.periodicalObj[item.periodical_id] = item.periodical_name)
      // 获取论文类型列表
      const { data: res5 } = await this.$http.post('/paperType/findAllPaperType')
      this.ptList = res5.data
      // 构造 论文类型对象
      this.ptList.forEach(item => this.ptObj[item.pt_id] = item.pt_name)
    },

    // 获取论文成果列表
    async getPaperList() {
      if(this.queryInfo.publish_time !== ''){
        this.queryInfo.publish_time_start = this.queryInfo.publish_time[0]
        this.queryInfo.publish_time_end = this.queryInfo.publish_time[1]
      }else this.queryInfo.publish_time_start = this.queryInfo.publish_time_end = ''
      this.queryInfo.apply_time_start = this.queryInfo.apply_time_end = ''
      const { data: res } = await this.$http.post("/paperExamine/selectAllPaperExamineByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status !== "200") return this.$message("查询待审核论文成果列表失败");
      this.total = res.data[0].total;
      this.papersList = res.data[1];
      // 通过 id:name 对象处理论文成果列表，为其加上各name属性，截取时间日期
      this.papersList.forEach((item) => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.sc_name = this.scObj[item.sc_id];
        item.subject_name = this.subjectObj[item.subject_id];
        item.sd_name = this.departmentObj[item.sd_id];
        item.periodical_name = this.periodicalObj[item.periodical_id];
        item.publish_time = item.publish_time.substring(0, 10);
        item.pt_name = this.ptObj[item.pt_id];
      });
      for(var i=0;i<this.papersList.length;i++) {
        const { data: res } = await this.$http.post('/user/findUserById', this.$qs.stringify({user_id: this.papersList[i].leader_id}))
        if( res.status !== '200' ) return this.$message.error('查询作者失败')
        console.log(res)
        this.papersList[i].authorName = res.data.user_name
      }
      console.log(this.papersList);
    },

    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.queryInfo.pageNum = 1;
      this.getPaperList();
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val;
      this.getPaperList();
    },
    // 点击审核按钮，显示审核论文成果对话框
    async showCheckPaperDialog(paperId) {
      console.log(paperId)
      const { data: res } = await this.$http.post("/paperExamine/findPaperExamineById", this.$qs.stringify({ pe_id: paperId }));
      if (res.status !== "200") return this.$message.error("获取待审核论文成果信息失败");
      console.log(res.data)
      this.paperInfo = res.data;
      this.paperInfo.aod_name = this.departmentObj[this.paperInfo.aod_id];
      this.paperInfo.sc_name = this.scObj[this.paperInfo.sc_id];
      this.paperInfo.subject_name = this.subjectObj[this.paperInfo.subject_id];
      this.paperInfo.sd_name = this.departmentObj[this.paperInfo.sd_id];
      this.paperInfo.periodical_name = this.periodicalObj[this.paperInfo.periodical_id];
      this.paperInfo.publish_time = this.paperInfo.publish_time.substring(0, 10);
      this.paperInfo.pt_name = this.ptObj[this.paperInfo.pt_id];

      const { data: res2 } = await this.$http.post("/user/findUserById", this.$qs.stringify({ user_id: this.paperInfo.leader_id }));
      if (res2.status !== "200") return this.$message.error("获取用户信息失败");
      this.paperInfo.leader_name = res2.data.user_name;
      this.checkPaperDialogVisible = true;
    },
    // 点击通过按钮，通过该待审核论文成果的申请
    async approvePaper() {
      const res = await this.$confirm("是否确定批准该论文成果申请？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (res === "cancel") return this.$message.info("取消了本次操作");
      var postObj = JSON.parse(JSON.stringify(this.paperInfo))
      postObj.checkMessage = 'success'
      const { data: res2 } = await this.$http.post("/paper/addPaper", this.$qs.stringify(postObj));
      if (res2.status !== "200") return this.$message.error("批准论文成果申请失败");
      this.$message.success("批准论文成果申请成功");
      this.getPaperList();
      this.checkPaperDialogVisible = false;
    },
    // 点击驳回按钮，驳回该论文成果申请
    denyPaper() {
      this.$refs.denyInfoRef.validate(async (valid) => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "/check/paper/deny",
          this.$qs.stringify({
            paper_id: this.paperInfo.paper_id,
            reason: this.denyInfo.reason,
          })
        );
        if (res.status !== "200") return this.$message.error("驳回论文成果申请失败");
        this.denyPaperDialogVisible = false;
        this.$message.success("驳回论文成果申请成功");
        this.getPaperList();
        this.checkPaperDialogVisible = false;
      });
    },
    // 关闭驳回理由填写对话框，清空表单
    denyPaperDialogClosed() {
      this.$refs.denyInfoRef.resetFields();
    },
  },
};
</script>

<style lang="less" scoped></style>
