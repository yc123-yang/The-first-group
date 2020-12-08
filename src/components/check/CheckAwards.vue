<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>审核申请</el-breadcrumb-item>
      <el-breadcrumb-item>审核获奖成果申请</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 待审核获奖成果表格 -->
      <el-table :data="awardsList" style="width: 100%" border :header-cell-style="{ background: '#f5f7fa' }">
        <!-- 序号列 -->
        <el-table-column type="index" label="#" align="center" fixed></el-table-column>
        <!-- 多选列 -->
        <el-table-column type="selection" align="center" fixed></el-table-column>

        <el-table-column prop="achievement_name" label="成名名字" width="300px" align="center" fixed>
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">成果名字</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.achievement_name" placeholder="请输入" @change="getAwardList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="authorName" label="著作作者" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">著作作者</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="getAwardList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="award_name" label="获奖名字" width="300px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">获奖名字</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.award_name" placeholder="请输入" @change="getAwardList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="award_time" label="获奖日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">发表日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.award_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="getAwardList"> </el-date-picker>
          </template>
        </el-table-column>

        <el-table-column prop="issuing_authority" label="发证机关" width="300px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">发证机关</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.issuing_authority" placeholder="请输入" @change="getAwardList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="approval_number" label="奖励批准号" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">奖励批准号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.approval_number" placeholder="请输入" @change="getAwardList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="dr_id" label="单位排名" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">单位排名</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.dr_id" placeholder="请输入" @change="getAwardList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="ar_name" label="获奖级别" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">获奖级别</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.ar_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in arList" :key="item.ar_id" :label="item.ar_name" :value="item.ar_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="al_name" label="获奖等级" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">获奖等级</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.al_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in alList" :key="item.al_id" :label="item.al_name" :value="item.al_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">学科门类</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.sc_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">一级学科</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.subject" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">归属单位</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="sd_name" label="项目来源" width="250px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目来源</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.sd_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="at_name" label="成果形式" width="250px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">成果形式</div>
            <el-select @change="getAwardList" class="columnInput" v-model="queryInfo.at_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in atList" :key="item.at_id" :label="item.at_name" :value="item.at_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showCheckAwardDialog(scope.row.ae_id)">审核</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </el-card>

    <el-dialog title="审核获奖成果申请" :visible.sync="checkAwardDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="成果名字：">{{ awardInfo.achievement_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="成果编号：">{{ awardInfo.ae_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="获奖名字：">{{ awardInfo.award_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="负责人：">{{ awardInfo.leader_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="发表日期：">{{ awardInfo.award_time }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="发证机关：">{{ awardInfo.issuing_authority }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="奖励批准号：">{{ awardInfo.approval_number }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="单位排名：">{{ awardInfo.dr_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="获奖级别：">{{ awardInfo.ar_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="获奖等级：">{{ awardInfo.al_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="成果形式：">{{ awardInfo.at_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="学科门类：">{{ awardInfo.sc_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="一级学科：">{{ awardInfo.subject_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="归属单位:">{{ awardInfo.aod_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="项目来源：">{{ awardInfo.sd_name }}</el-form-item></el-col
          >
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="denyAwardDialogVisible = true">驳 回</el-button>
        <el-button type="primary" @click="approveAward">通 过</el-button>
        <el-button @click="checkAwardDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <el-dialog title="填写驳回理由" :visible.sync="denyAwardDialogVisible" width="30%" @closed="denyAwardDialogClosed">
      <el-form :model="denyInfo" :rules="denyInfoRules" ref="denyInfoRef" label-width="100px" label-position="top">
        <el-form-item label="驳回理由：" prop="reason">
          <el-input type="textarea" v-model="denyInfo.reason"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="denyAwardDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="denyAward">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 获奖列表信息列表对象
      awardsList: [],
      // 搜索条件表单
      queryInfo: {
        award_id: "",
        leader_id: "",
        achievement_name: "",
        award_name: "",
        award_time: "",
        issuing_authority: "",
        approval_number: "",
        aod_id: "",
        dr_id: "",
        ar_id: "",
        al_id: "",
        sc_id: "",
        subject_id: "",
        sd_id: "",
        at_id: "",
        // 当前页码
        pageNum: 1,
        // 当前页大小
        pageSize: 10,
      },

      // 总的数据条数
      total: 2,
      // 查询条件：发表日期
      award_time: null,
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
      // 获奖级别
      arObj: {},
      arList: [],
      // 获奖等级
      alObj: {},
      alList: [],
      // 成果形式
      atObj: {},
      atList: [],

      // 审核对话框的显示与隐藏控制变量
      checkAwardDialogVisible: false,
      // 获奖成果具体信息
      awardInfo: {},
      // 填写驳回理由对话框的显示与隐藏控制变量
      denyAwardDialogVisible: false,
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
    await this.getAwardData();
    this.getAwardList();
  },
  methods: {
    // 获取获奖成果列表
    async getAwardData() {
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
      // 构造 获奖级别
      const { data: res4 } = await this.$http.post("/awardRank/findAllAwardRank");
      this.arList = res4.data;
      this.arList.forEach((item) => (this.arObj[item.ar_id] = item.ar_name));
      // 构造 获奖等级
      const { data: res5 } = await this.$http.post("/awardLevel/findAllAwardLevel");
      this.alList = res5.data;
      this.alList.forEach((item) => (this.alObj[item.al_id] = item.al_name));
      // 构造 成果形式
      const { data: res6 } = await this.$http.post("/achievementType/findAllAchievementType");
      this.atList = res6.data;
      this.atList.forEach((item) => (this.atObj[item.at_id] = item.at_name));
    },



    // 获取获奖成果列表
    async preGetAwardList() {
      if(this.queryInfo.award_time !== ''){
        this.queryInfo.award_time_start = this.queryInfo.award_time[0]
        this.queryInfo.award_time_end = this.queryInfo.award_time[1]
      }else this.queryInfo.award_time_start = this.queryInfo.award_time_end = ''
      this.queryInfo.apply_time_start = this.queryInfo.apply_time_end = ''
      // 通过 post 请求获取获奖成果列表
      const { data: res } = await this.$http.post("/awardExamine/selectAllAwardExamineByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }
      this.total = res.data[0].total;
      this.awardsList = res.data[1];
      // 通过 id:name 对象处理获奖成果列表，为其加上各name属性，截取时间日期
      this.awardsList.forEach((item) => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.sc_name = this.scObj[item.sc_id];
        item.subject_name = this.subjectObj[item.subject_id];
        item.sd_name = this.departmentObj[item.sd_id];
        item.award_time = item.award_time.substring(0, 10);
        item.ar_name = this.arObj[item.ar_id];
        item.al_name = this.alObj[item.al_id];
        item.at_name = this.atObj[item.at_id];
      });
      for(var i=0;i<this.awardsList.length;i++) {
        const { data: res } = await this.$http.post('/user/findUserById', this.$qs.stringify({user_id: this.awardsList[i].leader_id}))
        if( res.status !== '200' ) return this.$message.error('查询作者失败')
        this.awardsList[i].authorName = res.data.user_name
      }
    },
    async getAwardList() {
      await this.preGetAwardList()
      this.awardsList = JSON.parse(JSON.stringify(this.awardsList))
    },
    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.queryInfo.pageNum = 1;
      this.getAwardList();
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val;
      this.getAwardList();
    },
    // 点击审核按钮，显示审核获奖成果对话框
    async showCheckAwardDialog(awardId) {
      const { data: res } = await this.$http.post("/awardExamine/findAwardExamineById", this.$qs.stringify({ ae_id: awardId }));
      if (res.status !== "200") return this.$message.error("获取待审核获奖成果信息失败");
      this.awardInfo = res.data;
      this.awardInfo.aod_name = this.departmentObj[this.awardInfo.aod_id];
      this.awardInfo.sc_name = this.scObj[this.awardInfo.sc_id];
      this.awardInfo.subject_name = this.subjectObj[this.awardInfo.subject_id];
      this.awardInfo.sd_name = this.departmentObj[this.awardInfo.sd_id];
      this.awardInfo.award_time = this.awardInfo.award_time.substring(0, 10);
      this.awardInfo.ar_name = this.arObj[this.awardInfo.ar_id];
      this.awardInfo.al_name = this.alObj[this.awardInfo.al_id];
      this.awardInfo.at_name = this.atObj[this.awardInfo.at_id];

      const { data: res2 } = await this.$http.post("/user/findUserById", this.$qs.stringify({ user_id: this.awardInfo.leader_id }));
      if (res2.status !== "200") return this.$message.error("获取用户信息失败");
      this.awardInfo.leader_name = res2.data.user_name;
      this.checkAwardDialogVisible = true;
    },
    // 点击通过按钮，通过该待审核获奖成果的申请
    async approveAward() {
      const res = await this.$confirm("是否确定批准该获奖成果申请？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (res === "cancel") return this.$message.info("取消了本次操作");
      var postObj = JSON.parse(JSON.stringify(this.awardInfo))
      postObj.checkMessage = 'success'
      const { data: res2 } = await this.$http.post("/award/addAward", this.$qs.stringify(postObj));
      if (res2.status !== "200") return this.$message.error("批准获奖成果申请失败");
      this.$message.success("批准获奖成果申请成功");
      this.getAwardList();
      this.checkAwardDialogVisible = false;
    },
    // 点击驳回按钮，驳回该获奖成果申请
    denyAward() {
      this.$refs.denyInfoRef.validate(async (valid) => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "/check/award/deny",
          this.$qs.stringify({
            award_id: this.awardInfo.award_id,
            reason: this.denyInfo.reason,
          })
        );
        if (res.status !== "200") return this.$message.error("驳回获奖成果申请失败");
        this.denyAwardDialogVisible = false;
        this.$message.success("驳回获奖成果申请成功");
        this.getAwardList();
        this.checkAwardDialogVisible = false;
      });
    },
    // 关闭驳回理由填写对话框，清空表单
    denyAwardDialogClosed() {
      this.$refs.denyInfoRef.resetFields();
    },
  },
};
</script>

<style lang="less" scoped></style>
