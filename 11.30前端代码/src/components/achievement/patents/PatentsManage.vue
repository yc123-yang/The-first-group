<template>
  <div style="margin-top: 25px">
   <el-button type="primary" size="medium" @click="addPatentsDialogVisible = true">录入专利成果</el-button>
    <el-button type="danger" size="medium" :disabled="selection.length === 0">删除专利成果</el-button>
    <el-button type="warning" size="medium" @click="print">导出信息</el-button>


    <el-table :data="patentsList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange" :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>

      <el-table-column prop="patent_name" label="专利名字" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">专利名字</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.patent_name" placeholder="请输入" @change="QueryPatentList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="authorName" label="负责人" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">负责人</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryPatentList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="award_name" label="获奖名字" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">获奖名字</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.award_name" placeholder="请输入" @change="QueryPatentList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="ptz_name" label="专利类型" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">专利类型</div>
          <el-select @change="QueryPatentList" class="columnInput" v-model="queryInfo.ptz_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in ptzList" :key="item.ptz_id" :label="item.ptz_name" :value="item.ptz_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="pr_name" label="专利范围" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">专利范围</div>
          <el-select @change="QueryPatentList" class="columnInput" v-model="queryInfo.pr_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in prList" :key="item.pr_id" :label="item.pr_name" :value="item.pr_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="ps_name" label="专利状态" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">专利状态</div>
          <el-select @change="QueryPatentList" class="columnInput" v-model="queryInfo.ps_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in psList" :key="item.ps_id" :label="item.ps_name" :value="item.ps_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">归属单位</div>
          <el-select @change="QueryPatentList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="application_number" label="申请编号" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">申请编号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.application_number" placeholder="请输入" @change="QueryPatentList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="application_time" label="申请日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">申请日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.application_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="QueryPatentList"> </el-date-picker>
        </template>
      </el-table-column>

      <el-table-column prop="public_number" label="公开编号" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">公开编号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.public_number" placeholder="请输入" @change="QueryPatentList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="public_time" label="公开日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">公开日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.public_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="QueryPatentList"> </el-date-picker>
        </template>
      </el-table-column>

      <el-table-column prop="authorization_number" label="授权编号" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">授权编号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorization_number" placeholder="请输入" @change="QueryPatentList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="authorization_time" label="授权日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">授权日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.authorization_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="QueryPatentList"> </el-date-picker>
        </template>
      </el-table-column>
    </el-table>

   

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    <!-- 录入专利成果对话框 -->
    <el-dialog title="录入专利成果" :visible.sync="addPatentsDialogVisible" width="60%" @close="addPatentsDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 成果名字 -->
            <el-form-item label="专利名字:" prop="patent_name">
              <el-input v-model="addForm.patent_name" size="mini"></el-input>
            </el-form-item>
            <!-- 专利范围、专利状态 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="专利范围:" prop="pr">
                  <el-select v-model="addForm.pr" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in prList" :label="item.pr_name" :value="item.pr_id" :key="item.pr_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="专利状态:" prop="ps">
                  <el-select v-model="addForm.ps" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in psList" :label="item.ps_name" :value="item.ps_id" :key="item.ps_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 成果归属单位、专利类型 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod">
                  <el-select v-model="addForm.aod" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="专利类型:" prop="ptz">
                  <el-select v-model="addForm.ptz" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ptzList" :label="item.ptz_name" :value="item.ptz_id" :key="item.ptz_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 申请编号，申请日期 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="申请编号:" prop="application_number">
                  <el-input v-model="addForm.application_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="申请日期:" prop="application_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.application_time" style="width: 100%" size="mini"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 公开编号，公开日期 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="公开编号:" prop="public_number">
                  <el-input v-model="addForm.public_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="公开日期:" prop="public_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.public_time" style="width: 100%" size="mini"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 授权编号，授权日期 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="授权编号:" prop="authorization_number">
                  <el-input v-model="addForm.authorization_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="授权日期:" prop="authorization_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.authorization_time" style="width: 100%" size="mini"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>



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
        <el-button @click="addPatentsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPatents">确 定</el-button>
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
      // 科研项目信息列表对象
      patentsList: [],
      // 搜索条件表单
      queryInfo: {
        patent_id: "",
        leader_id: "",
        patent_name: "",
        ptz_id: "",
        pr_id: "",
        ps_id: "",
        aod_id: "",
        application_number: "",
        application_time: "",
        public_number: "",
        public_time: "",
        authorization_number: "",
        authorization_time: "",

        // 当前页码
        pagenum: 1,
        // 当前页大小
        pagesize: 10,
      },

      // 总的数据条数
      total: 2,
      // 查询条件：日期
      application_time: null,
      public_time: null,
      authorization_time: null,

      // 单位 id:name 对象
      departmentObj: {},
      // 单位对象列表
      departmentList: [],

      // 专利类型
      ptzObj: {},
      ptzList: [],
      // 专利范围
      prObj: {},
      prList: [],
      // 专利状态
      psObj: {},
      psList: [],

      addForm: {
       patent_name: "",
        ptz: "",
        pr: "",
        ps: "",
        aod: "",
        application_number: "",
        application_time: "",
        public_number: "",
        public_time: "",
        authorization_number: "",
        authorization_time: "",
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
      // 录入、修改数据表单验证对象
      addFormRules: {
        patent_name: [
          {
            required: true,
            message: "请输入专利名称",
            trigger: "blur",
          },
        ],
        ptz: [
          {
            required: true,
            message: "请选择专利类型",
            trigger: "change",
          },
        ],
        pr: [
          {
            required: true,
            message: "请选择专利范围",
            trigger: "change",
          },
        ],
        ps: [
          {
            required: true,
            message: "请选择专利状态",
            trigger: "change",
          },
        ],

        application_number: [
          {
            required: true,
            message: "请输入申请编号",
            trigger: "blur",
          },
        ],
        public_number: [
          {
            required: true,
            message: "请输入公开编号",
            trigger: "blur",
          },
        ],
        authorization_number: [
          {
            required: true,
            message: "请输入授权编号",
            trigger: "blur",
          },
        ],

        application_time: [
          {
            type: "date",
            required: true,
            message: "请选择申请日期",
            trigger: "change",
          },
        ],

        public_time: [
          {
            type: "date",
            required: true,
            message: "请选择公开日期",
            trigger: "change",
          },
        ],

        authorization_time: [
          {
            type: "date",
            required: true,
            message: "请选择授权日期",
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
      // // 控制录入专利成果对话框显示与隐藏变量
      addPatentsDialogVisible: false,
    };
  },
  async created() {
    await this.getPatentData();
    this.getPatentList();
  },
  methods: {
    // 获取专利成果列表
    async getPatentData() {
      // 获取单位列表
      const { data: res1 } = await this.$http.post("/department/findAlldepartment");
      this.departmentList = res1.data;
      // 构造单位 id:name 对象
      this.departmentList.forEach((item) => (this.departmentObj[item.department_id] = item.department_name));

      // 构造 专利类型
      const { data: res2 } = await this.$http.post("/ptz/findAllptz");
      this.ptzList = res2.data;
      this.ptzList.forEach((item) => (this.ptzObj[item.ptz_id] = item.ptz_name));
      // 构造 专利范围
      const { data: res3 } = await this.$http.post("/pr/findAllpr");
      this.prList = res3.data;
      this.prList.forEach((item) => (this.prObj[item.pr_id] = item.pr_name));
      // 构造 专利状态
      const { data: res4 } = await this.$http.post("/ps/findAllps");
      this.psList = res4.data;
      this.psList.forEach((item) => (this.psObj[item.ps_id] = item.ps_name));
    },

    // 获取论文成果列表
    async getPatentList() {
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post("patent/selectPatentByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }
      this.total = res.data[0].total;
      this.patentsList = res.data[1];
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.patentsList.forEach((item) => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.application_time = item.application_time.substring(0, 10);
        item.public_time = item.public_time.substring(0, 10);
        item.authorization_time = item.authorization_time.substring(0, 10);
        item.ptz_name = this.ptzObj[item.ptz_id];
        item.pr_name = this.prObj[item.pr_id];
        item.ps_name = this.psObj[item.ps_id];
      });
      console.log(this.patentsList);
    },
    // 多选框条件发生变化
    selectionChange() {
      console.log("selection change!!!");
    },
    // 导出
    print() {
      console.log("now print!!!");
    },
    // 根据查询条件获取项目列表
    QueryPatentList() {
      console.log(this.queryInfo);
      this.queryInfo.pageNum = 1;
      this.getPatentList();
    },
    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.queryInfo.pageNum = 1;
      this.getPatentList();
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val;
      this.getPatentList();
    },
    // 录入专利成果对话框中作者表格选中项改变
    addAuthorSelectionChange(val) {
      this.authorSelectionList = val;
    },
    // 选择专利成果收录列表发生变化
    periodicalSelectionChange(val) {
      this.addForm.periodicalSelectionList = val;
    },
    // 关闭录入专利成果对话框，清空相应数据
    addPatentsDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.authorTableRef.clearSelection();
      this.$refs.periodicalTableRef.clearSelection();
    },
    // 点击确定，录入专利成果
    addPatents() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return this.$message.error("请正确填写专利成果信息");
        this.$message.success("收录专利成果成功");
      });
    },
  },
};
</script>

<style lang="less" scoped></style>
