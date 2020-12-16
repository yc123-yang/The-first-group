<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>审核申请</el-breadcrumb-item>
      <el-breadcrumb-item>审核专利成果申请</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 待审核专利成果表格 -->
      <el-table :data="patentsList" style="width: 100%" border :header-cell-style="{ background: '#f5f7fa' }">
        <!-- 序号列 -->
        <el-table-column type="index" label="#" align="center" fixed></el-table-column>
        <!-- 多选列 -->
        <el-table-column type="selection" align="center" fixed></el-table-column>

        <el-table-column prop="patent_name" label="专利名字" width="300px" align="center" fixed>
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">专利名字</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.patent_name" placeholder="请输入" @change="getPatentList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="authorName" label="负责人" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">负责人</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="getPatentList"> </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="pt_name" label="专利类型" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">专利类型</div>
            <el-select @change="getPatentList" class="columnInput" v-model="queryInfo.pt_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in ptList" :key="item.pt_id" :label="item.pt_name" :value="item.pt_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="pr_name" label="专利范围" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">专利范围</div>
            <el-select @change="getPatentList" class="columnInput" v-model="queryInfo.pr_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in prList" :key="item.pr_id" :label="item.pr_name" :value="item.pr_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="ps_name" label="专利状态" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">专利状态</div>
            <el-select @change="getPatentList" class="columnInput" v-model="queryInfo.ps_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in psList" :key="item.ps_id" :label="item.ps_name" :value="item.ps_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">归属单位</div>
            <el-select @change="getPatentList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="application_number" label="申请编号" width="300px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">申请编号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.application_number" placeholder="请输入" @change="getPatentList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="application_time" label="申请日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">申请日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.application_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="getPatentList"> </el-date-picker>
          </template>
        </el-table-column>

        <el-table-column prop="public_number" label="公开编号" width="300px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">公开编号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.public_number" placeholder="请输入" @change="getPatentList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="public_time" label="公开日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">公开日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.public_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="getPatentList"> </el-date-picker>
          </template>
        </el-table-column>

        <el-table-column prop="authorization_number" label="授权编号" width="300px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">授权编号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorization_number" placeholder="请输入" @change="getPatentList"> </el-input>
          </template>
        </el-table-column>

        <el-table-column prop="authorization_time" label="授权日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">授权日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.authorization_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="getPatentList"> </el-date-picker>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showCheckPatentDialog(scope.row.pe_id)">审核</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </el-card>

    <el-dialog title="审核专利成果申请" :visible.sync="checkPatentDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="专利名字：">{{ patentInfo.patent_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="专利范围：">{{ patentInfo.pr_name}}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="专利状态：">{{ patentInfo.ps_name }}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="归属单位：">{{ patentInfo.aod_name }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="专利类型：">{{ patentInfo.pt_name }}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="申请编号：">{{ patentInfo.application_number }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申请日期：">{{ patentInfo.application_time }}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="公开编号：">{{ patentInfo.public_number }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公开日期：">{{ patentInfo.public_time }}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="授权编号：">{{ patentInfo.authorization_number }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="授权日期：">{{ patentInfo.authorization_time }}</el-form-item>
          </el-col>
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
        <el-button type="danger" @click="denyPatentDialogVisible = true">驳 回</el-button>
        <el-button type="primary" @click="approvePatent">通 过</el-button>
        <el-button @click="checkPatentDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <el-dialog title="填写驳回理由" :visible.sync="denyPatentDialogVisible" width="30%" @closed="denyPatentDialogClosed">
      <el-form :model="denyInfo" :rules="denyInfoRules" ref="denyInfoRef" label-width="100px" label-position="top">
        <el-form-item label="驳回理由：" prop="reason">
          <el-input type="textarea" v-model="denyInfo.reason"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="denyPatentDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="denyPatent">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //待审核专利成果目信息列表对象
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
        pageNum: 1,
        // 当前页大小
        pageSize: 10,
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

      // 审核对话框的显示与隐藏控制变量
      checkPatentDialogVisible: false,
      // 专利成果具体信息
      patentInfo: {},
      // 填写驳回理由对话框的显示与隐藏控制变量
      denyPatentDialogVisible: false,
      // 驳回理由表单对象
      denyInfo: {
        reason: "",
      },
      // 驳回理由表单验证对象
      denyInfoRules: {
        reason: [{ required: true, message: "请填写驳回理由！", trigger: "blur" }],
      },
      memberList: []
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

    // 获取专利成果列表
    async preGetPatentList() {
      // 通过 post 请求获取专利成果列表
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
      this.queryInfo.apply_time_start = this.queryInfo.apply_time_end = ''
      const { data: res } = await this.$http.post("/patentExamine/selectAllPatentExamineByCondition", this.$qs.stringify(this.queryInfo));
      if(res.status !== '200') return this.$message.error('获取待审核专利成果列表失败')
      console.log(res)
      this.total = res.data[0].total;
      this.patentsList = res.data[1];
      // 通过 id:name 对象处理专利成果列表，为其加上各name属性，截取时间日期
      this.patentsList.forEach((item) => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.application_time = item.application_time.substring(0, 10);
        item.public_time = item.public_time.substring(0, 10);
        item.authorization_time = item.authorization_time.substring(0, 10);
        item.pt_name = this.ptObj[item.pt_id];
        item.pr_name = this.prObj[item.pr_id];
        item.ps_name = this.psObj[item.ps_id];
      });
    },
    async getPatentList() {
      await this.preGetPatentList()
      this.patentsList = JSON.parse(JSON.stringify(this.patentsList))
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
    // 点击审核按钮，显示审核专利成果对话框
    async showCheckPatentDialog(patentId) {
      const { data: res } = await this.$http.post("/patentExamine/findPatentExamineById", this.$qs.stringify({ pe_id: patentId }));
      if (res.status !== "200") return this.$message.error("获取待审核专利成果信息失败");
      this.patentInfo = res.data;
      const { data: res2 } = await this.$http.post('/teamExamine/selectPatentTeamExamineUser', this.$qs.stringify({ pe_id: patentId }))
      if( res2.status !== '200' ) return this.$message.error('获取待审核专利成果团队信息失败')
      this.memberList = res2.data
      this.patentInfo.aod_name = this.departmentObj[this.patentInfo.aod_id];
      this.patentInfo.application_time = this.patentInfo.application_time.substring(0, 10);
      this.patentInfo.public_time = this.patentInfo.public_time.substring(0, 10);
      this.patentInfo.authorization_time = this.patentInfo.authorization_time.substring(0, 10);
      this.patentInfo.pt_name = this.ptObj[this.patentInfo.pt_id];
      this.patentInfo.pr_name = this.prObj[this.patentInfo.pr_id];
      this.patentInfo.ps_name = this.psObj[this.patentInfo.ps_id];

      this.checkPatentDialogVisible = true;
    },
    // 点击通过按钮，通过该待审核专利成果的申请
    async approvePatent() {
      const res = await this.$confirm("是否确定批准该专利成果申请？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (res === "cancel") return this.$message.info("取消了本次操作");
      var postObj = JSON.parse(JSON.stringify(this.patentInfo))
      postObj.checkMessage = '未通过'
      const { data: res2 } = await this.$http.post("/patent/addPatent", this.$qs.stringify(postObj));
      if (res2.status !== "200") return this.$message.error("批准专利成果申请失败");
      this.$message.success("批准专利成果申请成功");
      console.log(res2)
      this.getPatentList();
      this.checkPatentDialogVisible = false;
    },
    // 点击驳回按钮，驳回该专利成果申请
    denyPatent() {
      this.$refs.denyInfoRef.validate(async (valid) => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "/check/patent/deny",
          this.$qs.stringify({
            patent_id: this.patentInfo.patent_id,
            reason: this.denyInfo.reason,
          })
        );
        if (res.status !== "200") return this.$message.error("驳回专利成果申请失败");
        this.denyPatentDialogVisible = false;
        this.$message.success("驳回专利成果申请成功");
        this.getPatentList();
        this.checkPatentDialogVisible = false;
      });
    },
    // 关闭驳回理由填写对话框，清空表单
    denyPatentDialogClosed() {
      this.$refs.denyInfoRef.resetFields();
    },
  },
};
</script>

<style lang="less" scoped></style>
