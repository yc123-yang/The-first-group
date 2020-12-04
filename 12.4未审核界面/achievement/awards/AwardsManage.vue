<template>
  <div style="margin-top: 25px">
   <el-button type="primary" size="medium" @click="addAwardsDialogVisible = true">录入获奖成果</el-button>
    <el-button type="danger" size="medium" :disabled="selection.length === 0">删除获奖成果</el-button>
    <el-button type="warning" size="medium" @click="print">导出信息</el-button>


    <el-table :data="awardsList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange" :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>

      <el-table-column prop="achievement_name" label="成果名字" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">成果名字</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.achievement_name" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="authorName" label="获奖成果作者" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">获奖成果作者</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="award_name" label="获奖名字" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">获奖名字</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.award_name" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="award_time" label="获奖日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发表日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.award_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="QueryAwardList"> </el-date-picker>
        </template>
      </el-table-column>

      <el-table-column prop="issuing_authority" label="发证机关" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发证机关</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.issuing_authority" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="approval_number" label="奖励批准号" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">奖励批准号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.approval_number" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="dr_id" label="单位排名" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">单位排名</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.dr_id" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="ar_name" label="获奖级别" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">获奖级别</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.ar_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in arList" :key="item.ar_id" :label="item.ar_name" :value="item.ar_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="al_name" label="获奖等级" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">获奖等级</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.al_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in alList" :key="item.al_id" :label="item.al_name" :value="item.al_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">学科门类</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.sc_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">一级学科</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.subject" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">归属单位</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="sd_name" label="项目来源" width="250px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">项目来源</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.sd_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="at_name" label="成果形式" width="250px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">成果形式</div>
          <el-select @change="QueryAwardList" class="columnInput" v-model="queryInfo.at_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in atList" :key="item.at_id" :label="item.at_name" :value="item.at_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

    <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope"
          >{{ scope.haha }}
          <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    <!-- 录入获奖成果对话框 -->
    <el-dialog title="录入获奖成果" :visible.sync="addAwardsDialogVisible" width="60%" @close="addAwardsDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 成果名字 -->
            <el-form-item label="成果名字:" prop="achievement_name">
              <el-input v-model="addForm.achievement_name" size="mini"></el-input>
            </el-form-item>
            <!-- 获奖名称、获奖时间 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="获奖名称:" prop="award_name">
                  <el-input v-model="addForm.award_name" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="获奖时间:" prop="award_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.award_time" style="width: 100%" size="mini"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 发证机关，奖励批准号 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="发证机关:" prop="issuing_authority">
                  <el-input v-model="addForm.issuing_authority" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="奖励批准号:" prop="approval_number">
                  <el-input v-model="addForm.approval_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 成果归属单位、来源单位 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod">
                  <el-select v-model="addForm.aod" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="项目来源:" prop="sd">
                  <el-select v-model="addForm.sd" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 获奖级别，获奖等级 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="获奖级别:" prop="ar">
                  <el-select v-model="addForm.ar" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in arList" :label="item.ar_name" :value="item.ar_id" :key="item.ar_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="获奖等级:" prop="al">
                  <el-select v-model="addForm.al" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in alList" :label="item.al_name" :value="item.al_id" :key="item.al_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 学科门类，一级学科 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="学科门类:" prop="sc">
                  <el-select v-model="addForm.sc" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in scList" :label="item.sc_name" :value="item.sc_id" :key="item.sc_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="一级学科:" prop="subject">
                  <el-select v-model="addForm.subject" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in subjectList" :label="item.subject_name" :value="item.subject_id" :key="item.subject_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 单位排名,成果形式 -->
            <el-row :gutter="20">

              <el-col :span="12">
                <el-form-item label="成果形式:" prop="at">
                  <el-select v-model="addForm.aod" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in atList" :label="item.at_name" :value="item.at_id" :key="item.at_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="单位排名:" prop="dr_id">
                  <el-input v-model="addForm.dr_id" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>


            <!-- 备注 -->
            <el-form-item label="备注:" prop="remark">
              <el-input v-model="addForm.remark" size="mini"></el-input>
            </el-form-item>
            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini">添加作者</el-button>
              <el-button type="danger" size="mini" :disabled="authorSelectionList.length === 0"> 批量删除 </el-button>
            </el-form-item>

            <el-table :data="addForm.authorList" style="width: 100%" ref="authorTableRef" @selection-change="addAuthorSelectionChange" size="mini" border height="250px" :header-cell-style="{ background: '#f5f7fa' }">
              <!-- 序号列 -->
              <el-table-column type="index" label="#" align="center"></el-table-column>
              <!-- 多选列 -->
              <el-table-column type="selection" align="center"></el-table-column>
              <el-table-column prop="authorName" label="作者姓名" width="120px" align="center">
                <template slot="header" slot-scope="scope"
                  >{{ scope.haha }}
                  <div style="line-height: 14px">作者姓名</div>
                  <el-input class="columnInput" size="mini" clearable v-model="authorQueryInfo.authorName" placeholder="请输入"> </el-input>
                </template>
              </el-table-column>
              <el-table-column prop="authorType" label="成员类型" width="180px" align="center">
                <template slot="header" slot-scope="scope"
                  >{{ scope.haha }}
                  <div style="line-height: 14px">成员类型</div>
                  <el-select class="columnInput" v-model="authorQueryInfo.authorType" multiple size="mini" collapse-tags placeholder="请选择">
                    <el-option v-for="item in authorTypeList" :key="item" :label="item" :value="item"> </el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column prop="employer" label="归属单位" width="200px" align="center">
                <template slot="header" slot-scope="scope"
                  >{{ scope.haha }}
                  <div style="line-height: 14px">归属单位</div>
                  <el-select class="columnInput" v-model="authorQueryInfo.employer" multiple size="mini" collapse-tags placeholder="请选择">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column prop="rank" label="排名" align="center"></el-table-column>
              <el-table-column prop="rate" label="贡献率" align="center"></el-table-column>
              <!-- 操作列 -->
              <el-table-column label="操作" align="center" width="120" fixed="right">
                <template slot-scope="scope"
                  >{{ scope.haha }}
                  <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
                  <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAwardsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAwards">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 标签页默认打开
      tabActiveName: "first",
      // 获奖成果信息列表对象
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

      addForm: {
        achievement_name: "",
        award_name: "",
        award_time: "",
        issuing_authority: "",
        approval_number: "",
        aod: "",
        dr_id: "",
        ar: "",
        al: "",
        sc: "",
        subject: "",
        sd: "",
        at: "",
        authorList: [
          {
            authorName: "谢承洋",
            authorType: "老师",
            employer: "计算机科学学院",
            rank: 1,
            rate: "100%",
          },
        ],
        periodicalSelectionList: [],
      },
      // 录入、修改论文数据表单验证对象
      addFormRules: {
        achievement_name: [
          {
            required: true,
            message: "请输入成果名称",
            trigger: "blur",
          },
        ],
        award_name: [
          {
            required: true,
            message: "请输入获奖名称",
            trigger: "blur",
          },
        ],
        sd: [
          {
            required: true,
            message: "请选择项目来源",
            trigger: "change",
          },
        ],
        dr_id: [
          {
            required: true,
            message: "请输入单位排名",
            trigger: "change",
          },
        ],
        approval_number: [
          {
            required: true,
            message: "请输入奖励批准号",
            trigger: "blur",
          },
        ],
        issuing_authority: [
          {
            required: true,
            message: "请输入发证机关",
            trigger: "blur",
          },
        ],

        award_time: [
          {
            type: "date",
            required: true,
            message: "请选择获奖日期",
            trigger: "change",
          },
        ],
        sc: [
          {
            required: true,
            message: "请选择学科门类",
            trigger: "change",
          },
        ],
        ar: [
          {
            required: true,
            message: "请选择获奖级别",
            trigger: "change",
          },
        ],
        at: [
          {
            required: true,
            message: "请选择成果形式",
            trigger: "change",
          },
        ],
        al: [
          {
            required: true,
            message: "请选择获奖等级",
            trigger: "change",
          },
        ],
        subject: [
          {
            required: true,
            message: "请选择一级学科",
            trigger: "change",
          },
        ],
        aod: [
          {
            required: true,
            message: "请选择成果归属单位",
            trigger: "change",
          },
        ],
      },
      // 作者列表查询表单
      authorQueryInfo: {
        authorName: "",
        authorType: [],
        employer: [],
      },
      // 选中的作者列表
      authorSelectionList: [],
      // 成员类型列表
      authorTypeList: ["成员类型1", "成员类型2"],
      // 表格多选记录
      selection: [],
      // // 控制录入获奖成果对话框显示与隐藏变量
      addAwardsDialogVisible: false,
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
      const { data: res1 } = await this.$http.post("/department/findAlldepartment");
      this.departmentList = res1.data;
      // 构造单位 id:name 对象
      this.departmentList.forEach((item) => (this.departmentObj[item.department_id] = item.department_name));
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post("/sc/findAllsc");
      this.scList = res2.data;
      // 构造学科门类 id:name 对象
      this.scList.forEach((item) => (this.scObj[item.sc_id] = item.sc_name));
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post("/subject/findAllsubject");
      this.subjectList = res3.data;
      // 构造一级学科 id:name 对象
      this.subjectList.forEach((item) => (this.subjectObj[item.subject_id] = item.subject_name));
      // 构造 获奖级别
      const { data: res4 } = await this.$http.post("/ar/findAllar");
      this.arList = res4.data;
      this.arList.forEach((item) => (this.arObj[item.ar_id] = item.ar_name));
      // 构造 获奖等级
      const { data: res5 } = await this.$http.post("/al/findAllal");
      this.alList = res5.data;
      this.alList.forEach((item) => (this.alObj[item.al_id] = item.al_name));
      // 构造 成果形式
      const { data: res6 } = await this.$http.post("/at/findAllat");
      this.atList = res6.data;
      this.atList.forEach((item) => (this.atObj[item.at_id] = item.at_name));
    },

    // 获取获奖成果列表
    async getAwardList() {
      // 通过 post 请求获取获奖成果列表
      const { data: res } = await this.$http.post("award/selectAwardByCondition", this.$qs.stringify(this.queryInfo));
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
      console.log(this.awardsList);
    },
    // 多选框条件发生变化
    selectionChange() {
      console.log("selection change!!!");
    },
    // 导出
    print() {
      console.log("now print!!!");
    },
    // 根据查询条件获取列表
    QueryAwardList() {
      console.log(this.queryInfo);
      this.queryInfo.pageNum = 1;
      this.getAwardList();
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
    // 录入获奖成果对话框中作者表格选中项改变
    addAuthorSelectionChange(val) {
      this.authorSelectionList = val;
    },
    // 选择获奖成果收录列表发生变化
    periodicalSelectionChange(val) {
      this.addForm.periodicalSelectionList = val;
    },
    // 关闭录入获奖成果对话框，清空相应数据
    addAwardsDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.authorTableRef.clearSelection();
      this.$refs.periodicalTableRef.clearSelection();
    },
    // 点击确定，录入获奖成果
    addAwards() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return this.$message.error("请正确填写获奖成果信息");
        this.$message.success("收录获奖成果成功");
      });
    },
  },
};
</script>

<style lang="less" scoped></style>
