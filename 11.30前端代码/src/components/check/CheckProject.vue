<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>审核申请</el-breadcrumb-item>
      <el-breadcrumb-item>审核项目申请</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 待审核项目表格 -->
      <el-table :data="projectList" style="width: 100%" border :header-cell-style="{ background: '#f5f7fa' }">
        <el-table-column type="index" label="#" align="center" fixed></el-table-column>
        <el-table-column prop="project_name" label="项目名称" width="300px" align="center" fixed>
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目名称</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.project_name" placeholder="请输入" @change="getProjectList"> </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="project_id" label="项目编号" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目编号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.project_id" placeholder="请输入" @change="getProjectList"> </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="department_name" label="归属单位" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">归属单位</div>
            <el-select class="columnInput" v-model="queryInfo.department_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="sc_name" label="学科门类" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">学科门类</div>
            <el-select class="columnInput" v-model="queryInfo.sc_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">一级学科</div>
            <el-select class="columnInput" v-model="queryInfo.subject_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="nature_name" label="项目性质" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目性质</div>
            <el-select class="columnInput" v-model="queryInfo.nature_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in natureList" :key="item.nature_id" :label="item.nature_name" :value="item.nature_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="level_name" label="项目级别" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目级别</div>
            <el-select class="columnInput" v-model="queryInfo.level_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in levelList" :key="item.level_id" :label="item.level_name" :value="item.level_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="status_name" label="项目状态" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">项目状态</div>
            <el-select class="columnInput" v-model="queryInfo.status_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in statusList" :key="item.status_id" :label="item.status_name" :value="item.status_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="sd_name" label="来源单位" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">来源单位</div>
            <el-select class="columnInput" v-model="queryInfo.sd_id" size="mini" @change="getProjectList" collapse-tags placeholder="请选择" clearable>
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="approval_number" label="批准文号" width="200px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">批准文号</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.approval_number" placeholder="请输入" @change="getProjectList"> </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="start_time" label="立项日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">立项日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="start_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd 00:00:00" @change="getProjectList"> </el-date-picker>
          </template>
        </el-table-column>
        <el-table-column prop="plan_time" label="计划完成日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">计划完成日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="plan_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd 00:00:00" @change="getProjectList"> </el-date-picker>
          </template>
        </el-table-column>
        <el-table-column prop="complete_time" label="结项日期" width="230px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">结项日期</div>
            <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="complete_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd 00:00:00" @change="getProjectList"> </el-date-picker>
          </template>
        </el-table-column>
        <el-table-column prop="outlay" label="合同经费" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">合同经费</div>
            <el-input class="columnInput" size="mini" clearable v-model="queryInfo.outlay" placeholder="请输入" @change="getProjectList"> </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="ct_name" label="结题形式" width="150px" align="center">
          <template slot="header" slot-scope="scope"
            >{{ scope.haha }}
            <div style="line-height: 14px">结题形式</div>
            <el-select class="columnInput" v-model="queryInfo.ct_id" size="mini" collapse-tags placeholder="请选择" clearable @change="getProjectList">
              <el-option v-for="item in ctList" :key="item.ct_id" :label="item.ct_name" :value="item.ct_id"> </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showCheckProjectDialog(scope.row.project_id)">审核</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </el-card>

    <el-dialog title="审核项目申请" :visible.sync="checkProjectDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="项目名称：">{{ projectInfo.project_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="项目编号：">{{ projectInfo.project_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="所在单位：">{{ projectInfo.department_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="负责人：">{{ projectInfo.leader_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="成果归属单位：">{{ projectInfo.department_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="学科门类：">{{ projectInfo.sc_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="一级学科：">{{ projectInfo.subject_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col
            ><el-form-item label="项目性质：">{{ projectInfo.nature_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col
            ><el-form-item label="项目级别：">{{ projectInfo.level_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="项目状态：">{{ projectInfo.status_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="来源单位：">{{ projectInfo.sd_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="成果形式：">{{ projectInfo.ct_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="批准文号：">{{ projectInfo.approval_number }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="立项日期：">{{ projectInfo.start_time }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="计划完成日期：">{{ projectInfo.plan_time }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="结项日期：">{{ projectInfo.complete_time }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="合同经费：">{{ projectInfo.outlay }}</el-form-item></el-col
          >
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="denyProjectDialogVisible = true">驳 回</el-button>
        <el-button type="primary" @click="approveProject">通 过</el-button>
        <el-button @click="checkProjectDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <el-dialog title="填写驳回理由" :visible.sync="denyProjectDialogVisible" width="30%" @closed="denyProjectDialogClosed">
      <el-form :model="denyInfo" :rules="denyInfoRules" ref="denyInfoRef" label-width="100px" label-position="top">
        <el-form-item label="驳回理由：" prop="reason">
          <el-input type="textarea" v-model="denyInfo.reason"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="denyProjectDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="denyProject">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 待审核项目列表
      projectList: [],
      // 待审核项目列表查询对象
      queryInfo: {
        pageNum: 1,
        pageSize: 10,
        project_name: "",
        project_id: "",
        department_id: "",
        sc_id: "",
        subject_id: "",
        nature_id: "",
        level_id: "",
        status_id: "",
        sd_id: "",
        approval_number: "",
        start_time_start: "",
        start_time_end: "",
        plan_time_start: "",
        plan_time_end: "",
        complete_time_start: "",
        complete_time_end: "",
        outlay: "",
        ct_id: "",
      },
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
      // 项目性质 id:name 对象
      natureObj: {},
      // 项目性质列表
      natureList: [],
      // 项目级别 id:name 对象
      levelObj: {},
      // 项目级别列表
      levelList: [],
      // 项目状态 id:name 对象
      statusObj: {},
      // 项目状态列表
      statusList: [],
      // 结题形式 id:name 对象
      ctObj: {},
      // 结题形式列表
      ctList: [],
      // 查询条件：立项日期
      start_time: null,
      // 查询条件：计划完成日期
      plan_time: null,
      // 查询条件：结项日期
      complete_time: null,
      // 总数据条数
      total: 10,
      // 审核项目对话框的显示与隐藏控制变量
      checkProjectDialogVisible: false,
      // 项目具体信息
      projectInfo: {},
      // 填写驳回理由对话框的显示与隐藏控制变量
      denyProjectDialogVisible: false,
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
    await this.getProjectData();
    this.getProjectList();
  },
  methods: {
    // 预先获取待审核项目所需的字典数据
    async getProjectData() {
      // 处理单位列表和 id:name 对象
      const { data: res1 } = await this.$http.post("/department/findAlldepartment");
      this.departmentList = res1.data;
      this.departmentList.forEach((item) => (this.departmentObj[item.department_id] = item.department_name));
      // 处理学科门类列表和 id:name 对象
      const { data: res2 } = await this.$http.post("/sc/findAllsc");
      this.scList = res2.data;
      this.scList.forEach((item) => (this.scObj[item.sc_id] = item.sc_name));
      // 获取一级学科列表和 id:name 对象
      const { data: res3 } = await this.$http.post("/subject/findAllsubject");
      this.subjectList = res3.data;
      this.subjectList.forEach((item) => (this.subjectObj[item.subject_id] = item.subject_name));
      // 获取项目性质列表和 id:name 对象
      const { data: res4 } = await this.$http.post("/nature/findAllnature");
      this.natureList = res4.data;
      this.natureList.forEach((item) => (this.natureObj[item.nature_id] = item.nature_name));
      // 获取项目级别列表和 id:name 对象
      const { data: res5 } = await this.$http.post("/level/findAlllevel");
      this.levelList = res5.data;
      this.levelList.forEach((item) => (this.levelObj[item.level_id] = item.level_name));
      // 获取项目状态列表和 id:name 对象
      const { data: res6 } = await this.$http.post("status/findAllstatus");
      this.statusList = res6.data;
      this.statusList.forEach((item) => (this.statusObj[item.status_id] = item.status_name));
      // 获取结题形式列表和 id:name 对象
      const { data: res7 } = await this.$http.post("ct/findAllct");
      this.ctList = res7.data;
      this.ctList.forEach((item) => (this.ctObj[item.ct_id] = item.ct_name));
    },
    // 根据条件，获取待审核项目列表
    async getProjectList() {
      // 预处理日期相关
      if (this.start_time !== null) {
        this.queryInfo.start_time_start = this.start_time[0];
        this.queryInfo.start_time_end = this.start_time[1];
      } else this.queryInfo.start_time_start = this.queryInfo.start_time_end = "";
      if (this.plan_time !== null) {
        this.queryInfo.plan_time_start = this.plan_time[0];
        this.queryInfo.plan_time_end = this.plan_time[1];
      } else this.queryInfo.plan_time_start = this.queryInfo.plan_time_end = "";
      if (this.complete_time !== null) {
        this.queryInfo.complete_time_start = this.complete_time[0];
        this.queryInfo.complete_time_end = this.complete_time[1];
      } else this.queryInfo.complete_time_start = this.queryInfo.complete_time_end = "";
      this.queryInfo.pageNum = 1;
      const { data: res } = await this.$http.post("/check/project/findAllProjectByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status !== "200") return this.$message("查询待审核项目列表失败");
      this.total = res.data[0].total;
      this.projectList = res.data[1];

      // 为列表中每个元素添加相关 name 属性
      this.projectList.forEach((item) => {
        item.department_name = this.departmentObj[item.department_id];
        item.sc_name = this.scObj[item.sc_id];
        item.subject_name = this.subjectObj[item.subject_id];
        item.nature_name = this.natureObj[item.nature_id];
        item.level_name = this.levelObj[item.level_id];
        item.status_name = this.statusObj[item.status_id];
        item.sd_name = this.departmentObj[item.sd_id];
        item.start_time = item.start_time.substring(0, 10);
        item.plan_time = item.plan_time.substring(0, 10);
        item.complete_time = item.complete_time.substring(0, 10);
        item.ct_name = this.ctObj[item.ct_id];
      });
      console.log(this.projectList);
    },
    // 一页的数据条数发生变化
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.queryInfo.pageNum = 1;
      this.getProjectList();
    },
    // 当前页数发生变化
    handleCurrentChange(newNum) {
      this.queryInfo.pageNum = newNum;
      this.getProjectList();
    },
    // 点击审核按钮，显示审核项目对话框
    async showCheckProjectDialog(projectId) {
      const { data: res } = await this.$http.post("/check/project/findProjectById", this.$qs.stringify({ project_id: projectId }));
      if (res.status !== "200") return this.$message.error("获取待审核项目信息失败");
      this.projectInfo = res.data;
      this.projectInfo.department_name = this.departmentObj[this.projectInfo.department_id];
      this.projectInfo.sc_name = this.scObj[this.projectInfo.sc_id];
      this.projectInfo.subject_name = this.subjectObj[this.projectInfo.subject_id];
      this.projectInfo.nature_name = this.natureObj[this.projectInfo.nature_id];
      this.projectInfo.level_name = this.levelObj[this.projectInfo.level_id];
      this.projectInfo.status_name = this.statusObj[this.projectInfo.status_id];
      this.projectInfo.sd_name = this.departmentObj[this.projectInfo.sd_id];
      this.projectInfo.start_time = this.projectInfo.start_time.substring(0, 10);
      this.projectInfo.plan_time = this.projectInfo.plan_time.substring(0, 10);
      this.projectInfo.complete_time = this.projectInfo.complete_time.substring(0, 10);
      this.projectInfo.ct_name = this.ctObj[this.projectInfo.ct_id];
      const { data: res2 } = await this.$http.post("/user/findUserById", this.$qs.stringify({ user_id: this.projectInfo.leader_id }));
      if (res2.status !== "200") return this.$message.error("获取用户信息失败");
      this.projectInfo.leader_name = res2.data.user_name;
      this.checkProjectDialogVisible = true;
    },
    // 点击通过按钮，通过该待审核项目的申请
    async approveProject() {
      const res = await this.$confirm("是否确定批准该项目申请？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (res === "cancel") return this.$message.info("取消了本次操作");
      const { data: res2 } = await this.$http.post("/check/project/approve", { project_id: this.projectInfo.project_id });
      if (res2.status !== "200") return this.$message.error("批准项目申请失败");
      this.$message.success("批准项目申请成功");
      this.getProjectList();
      this.checkProjectDialogVisible = false;
    },
    // 点击驳回按钮，驳回该项目申请
    denyProject() {
      this.$refs.denyInfoRef.validate(async (valid) => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "/check/project/deny",
          this.$qs.stringify({
            project_id: this.projectInfo.project_id,
            reason: this.denyInfo.reason,
          })
        );
        if (res.status !== "200") return this.$message.error("驳回项目申请失败");
        this.denyProjectDialogVisible = false;
        this.$message.success("驳回项目申请成功");
        this.getProjectList();
        this.checkProjectDialogVisible = false;
      });
    },
    // 关闭驳回理由填写对话框，清空表单
    denyProjectDialogClosed() {
      this.$refs.denyInfoRef.resetFields();
    },
  },
};
</script>

<style lang="less" scoped></style>
