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

      <el-table-column prop="pt_name" label="专利类型" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">专利类型</div>
          <el-select @change="QueryPatentList" class="columnInput" v-model="queryInfo.pt_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in ptList" :key="item.pt_id" :label="item.pt_name" :value="item.pt_id"> </el-option>
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
      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deletePatentById(scope.row.patent_id)"></el-button>
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
                <el-form-item label="专利范围:" prop="pr_id">
                  <el-select v-model="addForm.pr_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in prList" :label="item.pr_name" :value="item.pr_id" :key="item.pr_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="专利状态:" prop="ps_id">
                  <el-select v-model="addForm.ps_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in psList" :label="item.ps_name" :value="item.ps_id" :key="item.ps_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 成果归属单位、专利类型 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod_id">
                  <el-select v-model="addForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="专利类型:" prop="pt_id">
                  <el-select v-model="addForm.pt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ptList" :label="item.pt_name" :value="item.pt_id" :key="item.pt_id"> </el-option>
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
            <el-row>
              <el-form-item label="作者:" prop="author" size="mini">
                <el-button type="primary" size="mini" @click="addAuthorDialogVisible = true">添加作者</el-button>
              </el-form-item>
            </el-row>
            <el-table :data="addForm.authorList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
              :header-cell-style="{ background: '#f5f7fa' }" :default-sort="{prop: 'author_rate', order: 'descending'}">
              <!-- 序号列 -->
              <el-table-column type="index" label="#" align="center"></el-table-column>
              <el-table-column prop="author_info.user_name" label="作者姓名" align="center"></el-table-column>
              <el-table-column prop="author_type" label="成员类型" align="center"></el-table-column>
              <el-table-column prop="author_department" label="归属单位" align="center"></el-table-column>
              <el-table-column prop="author_rate" label="贡献率" align="center">
                <template slot-scope="scope">{{scope.row.author_rate + '%'}}</template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="120px">
                <template slot-scope="scope">
                  <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditAuthorDialog(scope)"></el-button>
                  <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteAuthor(scope.$index)"></el-button>
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


    <el-dialog title="添加作者" :visible.sync="addAuthorDialogVisible" width="40%" @closed="addAuthorDialogClosed">
      <el-form :model="addAuthorForm" :rules="addAuthorFormRules" ref="addAuthorFormRef" label-width="100px">
        <el-form-item label="作者姓名：" prop="author_info">
          <el-select v-model="addAuthorForm.author_info" remote placeholder="请输入作者姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="addAuthorAuthorInfoSelectorChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者类型：" prop="author_type">
          <el-input v-model="addAuthorForm.author_type" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="author_department">
          <el-input v-model="addAuthorForm.author_department" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="贡献率：" prop="author_rate">
          <el-input-number v-model="addAuthorForm.author_rate" controls-position="right" :min="1" :max="100" style="width: 100px">
          </el-input-number>
          <span>  %</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAuthorDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAuthor">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="编辑作者" :visible.sync="editAuthorDialogVisible" width="40%" @closed="editAuthorDialogClosed">
      <el-form :model="editAuthorForm" :rules="editAuthorFormRules" ref="editAuthorFormRef" label-width="100px">
        <el-form-item label="作者姓名：" prop="author_info">
          <el-select v-model="editAuthorForm.author_info" remote placeholder="请输入作者姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="editAuthorAuthorInfoSelectorChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者类型：" prop="author_type">
          <el-input v-model="editAuthorForm.author_type" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="author_department">
          <el-input v-model="editAuthorForm.author_department" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="贡献率：" prop="author_rate">
          <el-input-number v-model="editAuthorForm.author_rate" controls-position="right" :min="1" :max="100" style="width: 100px">
          </el-input-number>
          <span>  %</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editAuthorDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editAuthor">确 定</el-button>
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
        pt_id: "",
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
      ptObj: {},
      ptList: [],
      // 专利范围
      prObj: {},
      prList: [],
      // 专利状态
      psObj: {},
      psList: [],

      addForm: {
        patent_name: "",
        pt_id: "",
        pr_id: "",
        ps_id: "",
        aod_id: "",
        application_number: "",
        application_time: "",
        public_number: "",
        public_time: "",
        authorization_number: "",
        authorization_time: "",
        authorList: [],
        periodicalSelectionList: [],
      },
      // 录入、修改数据表单验证对象
      addFormRules: {
        patent_name: [{ required: true, message: "请输入专利名称", trigger: "blur" }],
        pt_id: [{ required: true, message: "请选择专利类型", trigger: "change" }],
        pr_id: [{ required: true, message: "请选择专利范围", trigger: "change" }],
        ps_id: [{ required: true, message: "请选择专利状态", trigger: "change" }],
        application_number: [{ required: true, message: "请输入申请编号", trigger: "blur" }],
        public_number: [{ required: true, message: "请输入公开编号", trigger: "blur" }],
        authorization_number: [{ required: true, message: "请输入授权编号", trigger: "blur" }],
        application_time: [{ type: "date", required: true, message: "请选择申请日期", trigger: "change" }],
        public_time: [{ type: "date", required: true, message: "请选择公开日期", trigger: "change" }],
        authorization_time: [{ type: "date", required: true, message: "请选择授权日期", trigger: "change" }],
        aod_id: [{ required: true, message: "请选择成果归属单位", trigger: "change" }]
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

      addAuthorDialogVisible: false,
      // 添加作者信息表单
      addAuthorForm: {
        author_info: '',
        author_type: '',
        author_department: '',
        author_rate: ''
      },
      // 添加作者表单验证规则对象
      addAuthorFormRules: {
        author_info: [{ required: true, message: '请输入作者姓名', trigger: 'change' }],
        author_type: [{ required: true, message: '请选择作者类型', trigger: 'change' }]
      },
      // 是否正在加载
      loadingLeaderList: false,
      // 用户列表
      leaderList: [],
      // 编辑作者对话框显示与隐藏
      editAuthorDialogVisible: false,
      // 编辑作者信息表单
      editAuthorForm: {
        author_info: '',
        author_type: '',
        author_department: '',
        author_rate: ''
      },
      // 正在编辑的位置
      editAuthorIndex: '',
      // 编辑作者表单验证对象
      editAuthorFormRules: {}
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
      const { data: res1 } = await this.$http.post("/department/findAllDepartment");
      this.departmentList = res1.data;
      // 构造单位 id:name 对象
      this.departmentList.forEach((item) => (this.departmentObj[item.department_id] = item.department_name));

      // 构造 专利类型
      const { data: res2 } = await this.$http.post("/patentType/findAllPatentType");
      this.ptList = res2.data;
      this.ptList.forEach((item) => (this.ptObj[item.pt_id] = item.pt_name));
      // 构造 专利范围
      const { data: res3 } = await this.$http.post("/patentRange/findAllPatentRange");
      this.prList = res3.data;
      this.prList.forEach((item) => (this.prObj[item.pr_id] = item.pr_name));
      // 构造 专利状态
      const { data: res4 } = await this.$http.post("/patentStatus/findAllPatentStatus");
      this.psList = res4.data;
      this.psList.forEach((item) => (this.psObj[item.ps_id] = item.ps_name));
    },

    // 获取论文成果列表
    async preGetPatentList() {
      if(this.queryInfo.public_time !== '') {
        this.queryInfo.public_time_start = this.queryInfo.public_time[0]
        this.queryInfo.public_time_end = this.queryInfo.public_time[1]
      } else this.queryInfo.public_time_start = this.queryInfo.public_time_end = ''
      if(this.queryInfo.authorization_time !== '') {
        this.queryInfo.authorization_time_start = this.queryInfo.authorization_time[0]
        this.queryInfo.authorization_time_end = this.queryInfo.authorization_time[1]
      } else this.queryInfo.authorization_time_start = this.queryInfo.authorization_time_end = ''
      if(this.queryInfo.application_time !== '') {
        this.queryInfo.application_time_start = this.queryInfo.application_time[0]
        this.queryInfo.application_time_end = this.queryInfo.application_time[1]
      } else this.queryInfo.application_time_start = this.queryInfo.application_time_end = ''
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
        item.pt_name = this.ptObj[item.pt_id];
        item.pr_name = this.prObj[item.pr_id];
        item.ps_name = this.psObj[item.ps_id];
      });
      for(var i=0;i<this.patentsList.length;i++) {
        const { data: res } = await this.$http.post('/user/findUserById', this.$qs.stringify({user_id: this.patentsList[i].leader_id}))
        if( res.status !== '200' ) return this.$message.error('查询作者失败')
        this.patentsList[i].authorName = res.data.user_name
      }
      console.log(this.patentsList);
    },
    async getPatentList() {
      await this.preGetPatentList()
      this.patentsList = JSON.parse(JSON.stringify(this.patentsList))
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
      this.addForm.authorList = []
    },
    // 获得最高贡献的用户的id
    getLeaderId() {
      var maxrate = 0
      var leader_id = -1
      this.addForm.authorList.forEach(item => {
        if(item.author_rate > maxrate) {
          maxrate = item.author_rate
          leader_id = item.author_info.user_id
        }
      })
      return leader_id
    },
    // 点击确定，录入专利成果
    addPatents() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return this.$message.error("请正确填写专利成果信息");
        var getObj = JSON.parse(JSON.stringify(this.addForm))
        var user_id = []
        var contribution = []
        this.addForm.authorList.forEach(item => {
          user_id.push(item.author_info.user_id)
          contribution.push(item.author_rate)
        })
        getObj.leader_id = this.getLeaderId()
        getObj.examine_status = '未通过'
        const { data: res } = await this.$http.get(`/patentExamine/addPatentExamine?${this.$qs.stringify(getObj)}
          &user_id=${user_id}
          &contribution=${contribution}
        `)
        if(res.status !== '200') return this.$message.error('提交专利成功申请失败')
        this.$message.success("提交专利成果申请成功");
        this.getPatentList()
        this.addPatentsDialogVisible = false
      });
    },
    // 根据用户输入，获取用户列表
    async getLeaderList(query) {
      if(query === null || query === '') return
      this.loadingLeaderList = true
      const { data: res } = await this.$http.post('/user/findNameId', this.$qs.stringify({ user_name: query }))
      this.leaderList = res.data
      this.loadingLeaderList = false
    },
    // 添加作者作者选项卡变化事件
    async addAuthorAuthorInfoSelectorChanged() {
      this.addAuthorForm.author_info = JSON.parse(this.addAuthorForm.author_info)
      this.addAuthorForm.author_department = this.departmentObj[this.addAuthorForm.author_info.department_id]
      const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.addAuthorForm.author_info.role_id }))
      if( res.status !== '200' ) return this.$message.error('获取用户类型失败')
      this.addAuthorForm.author_type = res.data
      console.log(this.addAuthorForm)
    },
    // 点击确定，添加作者
    addAuthor() {
      var authorObj = JSON.parse(JSON.stringify(this.addAuthorForm))
      this.addForm.authorList.push(authorObj)
      console.log(this.addForm.authorList)
      this.$message.success('添加作者成功')
      this.addAuthorDialogVisible = false
    },
    // 关闭添加作者对话框，清空表单
    addAuthorDialogClosed() {
      this.$refs.addAuthorFormRef.resetFields()
    },
    // 点击删除按钮，删除作者
    async deleteAuthor(index) {
      const res = await this.$confirm('此操作将永久删除该作者，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res === 'cancel' ) return this.$message.info('取消了本次操作')
      this.addForm.authorList.splice(index, 1)
      this.$message.success('成功删除了作者')
      console.log(this.addForm.authorList)
    },
    // 关闭编辑作者对话框，清空表单
    editAuthorDialogClosed() {
      this.$refs.editAuthorFormRef.resetFields()
    },
    // 编辑作者作者选项卡变化事件
    async editAuthorAuthorInfoSelectorChanged() {
      this.editAuthorForm.author_info = JSON.parse(this.editAuthorForm.author_info)
      this.editAuthorForm.author_department = this.departmentObj[this.editAuthorForm.author_info.department_id]
      const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.editAuthorForm.author_info.role_id }))
      if( res.status !== '200' ) return this.$message.error('获取用户类型失败')
      this.editAuthorForm.author_type = res.data
      console.log(this.editAuthorForm)
    },
    // 点击编辑按钮，显示编辑作者对话框
    showEditAuthorDialog(scope){
      this.editAuthorForm = JSON.parse(JSON.stringify(scope.row))
      this.editAuthorIndex = JSON.parse(JSON.stringify(scope.$index))
      this.editAuthorDialogVisible = true
    },
    // 点击确定，上传编辑作者信息
    editAuthor() {
      this.addForm.authorList[this.editAuthorIndex] = JSON.parse(JSON.stringify(this.editAuthorForm))
      this.addForm.authorList = JSON.parse(JSON.stringify(this.addForm.authorList))
      this.$message.success('成功编辑了作者信息')
      this.editAuthorDialogVisible = false
    },
    // 点击按钮，删除专利成果
    async deletePatentById(patent_id) {
      const res = await this.$confirm('此操作将永久删除该专利成果，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/patent/delPatentById', this.$qs.stringify({ patent_id: patent_id }))
      console.log(res2)
      if(res2.status !== '200') return this.$message.error('删除专利成果失败')
      this.$message.success('删除专利成果成功')
      this.getPatentList()
    }
  },
};
</script>

<style lang="less" scoped></style>
