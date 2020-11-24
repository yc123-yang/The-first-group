<template>
  <div style="margin-top: 25px">
    <el-button type="primary" size="medium" @click="addPaperDialogVisible = true">录入论文</el-button>
    <el-button type="danger" size="medium" :disabled="selection.length === 0">删除论文</el-button>
    <el-button type="warning" size="medium" @click="print">导出信息</el-button>

    <el-table :data="papersList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange" :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>
      <el-table-column prop="paper_name" label="论文题目" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">论文题目</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.paper_name" placeholder="请输入" @change="QueryPaperList"> </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="authorName" label="论文作者" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">论文作者</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryPaperList"> </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="periodical_name" label="发表期刊" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发表期刊</div>
          <el-select class="columnInput" v-model="queryInfo.periodical_id" size="mini" clearable placeholder="请选择" @change="QueryPaperList">
            <el-option v-for="item in periodicalList" :key="item.periodical_id" :label="item.periodical_name" :value="item.periodical_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发表日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="QueryPaperList"> </el-date-picker>
        </template>
      </el-table-column>

      <el-table-column prop="pt_name" label="论文类型" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">论文类型</div>
          <el-select class="columnInput" v-model="queryInfo.pt_id" clearable size="mini" collapse-tags placeholder="请选择" @change="QueryPaperList">
            <el-option v-for="item in ptList" :key="item.pt_id" :label="item.pt_name" :value="item.pt_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="include_number" label="收录号" width="300px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">收录号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.include_number" placeholder="请输入" @change="QueryPaperList"> </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">学科门类</div>
          <el-select class="columnInput" v-model="queryInfo.sc_id" clearable size="mini" collapse-tags placeholder="请选择" @change="QueryPaperList">
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">一级学科</div>
          <el-select class="columnInput" v-model="queryInfo.subject_id" clearable size="mini" collapse-tags placeholder="请选择" @change="QueryPaperList">
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">归属单位</div>
          <el-select class="columnInput" v-model="queryInfo.aod_id" clearable size="mini" collapse-tags placeholder="请选择" @change="QueryPaperList">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="sd_name" label="来源单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">来源单位</div>
          <el-select class="columnInput" v-model="queryInfo.sd_id" size="mini" @change="QueryPaperList" collapse-tags placeholder="请选择" clearable>
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
    </el-table>

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>

    <!-- 录入论文成果对话框 -->
    <el-dialog title="录入论文成果" :visible.sync="addPaperDialogVisible" width="70%" @close="addPaperDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="90px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col :span="16">
            <!-- 论文题目 -->
            <el-form-item label="论文题目:" prop="paper_name">
              <el-input v-model="addForm.paper_name" size="mini"></el-input>
            </el-form-item>
            <!-- 论文类型、发表期刊 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="论文类型:" prop="pt">
                  <el-select v-model="addForm.pt" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ptList" :label="item.pt_name" :value="item.pt_id" :key="item.pt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="发表期刊:" prop="periodical">
                  <el-select v-model="addForm.periodical" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in periodicalList" :label="item.periodical_name" :value="item.periodical_id" :key="item.periodical_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 发表时间 -->
            <el-form-item label="发表时间:" prop="publish_time">
              <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.publish_time" style="width: 100%" size="mini"> </el-date-picker>
            </el-form-item>
            <!-- 收录号 -->
            <el-form-item label="收录号:" prop="include_number">
              <el-input v-model="addForm.include_number" size="mini"></el-input>
            </el-form-item>
            <!-- 学科门类、一级学科 -->
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
            <!-- 归属单位 -->
            <el-form-item label="归属单位:" prop="aod">
              <el-select v-model="addForm.aod" placeholder="请选择" size="mini" style="width: 100%">
                <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
              </el-select>
            </el-form-item>
            <!-- 项目来源 -->
            <el-form-item label="项目来源:" prop="sd">
              <el-select v-model="addForm.sd" placeholder="请选择" size="mini" style="width: 100%">
                <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
              </el-select>
            </el-form-item>
            <!-- 备注 -->

            <el-form-item label="备注:" prop="remark">
              <el-input size="mini"></el-input>
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
                    <el-option v-for="item in attributionList" :key="item" :label="item" :value="item"> </el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column prop="rank" label="排名" align="center"></el-table-column>
              <el-table-column prop="rate" label="贡献率" align="center"></el-table-column>
  

              <el-table-column label="操作" align="center" width="120" fixed="right">
                <template slot-scope="scope"
                  >{{ scope.haha }}
                  <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
                  <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
          <!-- 表单右侧 -->
          <el-col :span="8">
            <el-table  :data="periodicalList" style="width: 100%; margin: 0" height="670px" border @selection-change="periodicalSelectionChange" size="mini" ref="periodicalTableRef" :header-cell-style="{ background: '#f5f7fa' }">
              <!-- 多选列 -->
              <el-table-column type="selection" align="center"></el-table-column>
              <el-table-column label="收录情况">
                <template slot-scope="scope">{{ scope.row }}</template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addPaperDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPaper">确 定</el-button>
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
        pagenum: 1,
        // 当前页大小
        pagesize: 2,
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

      // 表格多选记录
      selection: [],
      // 控制录入论文成果对话框显示与隐藏变量
      addPaperDialogVisible: false,
      // 录入论文数据表单
      addForm: {
        paper_name: '',
        authorName: '',
        periodical: '',
        publish_time: '',
        pt: '',
        include_number: '',
        suc: '',
        subject: '',
        aod: '',
        sd: '',
        remark: '',
        authorList: [{
          authorName: '谢承洋',
          authorType: '老师',
          employer: '计算机科学学院',
          rank: 1,
          rate: '100%'
        }],
        periodicalSelectionList: []
      },
      // 录入、修改论文数据表单验证对象
      addFormRules: {
        paper_name: [{ required: true, message: "请输入论文题目", trigger: "blur" }],
        include_number: [{ required: true, message: "请输入收录号", trigger: "blur" }],
        pt: [{ required: true, message: "请选择论文类型", trigger: "change" }],
        periodical: [{ required: true, message: "请选择发表期刊", trigger: "change" }],
        publish_time: [{ type: "date", required: true, message: "请选择发表日期", trigger: "change" }],
        sc: [{ required: true, message: "请选择学科门类", trigger: "change" }],
        subject: [{ required: true, message: "请选择一级学科", trigger: "change" }],
        sd: [{ required: true, message: "请选择来源单位", trigger: "change" }],
        aod: [{ required: true, message: "请选择成果归属单位", trigger: "change" }],
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
    };
  },

  async created() {
    await this.getPaperData();
    this.getPaperList();
  },
  methods: {
    // 获取论文成果列表
    async getPaperData() {
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
      // 获取期刊列表
      const { data: res4 } = await this.$http.post("/periodical/findAllperiodical");
      this.periodicalList = res4.data;
      // 构造 期刊 对象
      this.periodicalList.forEach((item) => (this.periodicalObj[item.periodical_id] = item.periodical_name));
      // 获取论文类型列表
      const { data: res5 } = await this.$http.post("/pt/findAllpt");
      this.ptList = res5.data;
      // 构造 论文类型对象
      this.ptList.forEach((item) => (this.ptObj[item.pt_id] = item.pt_name));
    },

    // 获取论文成果列表
    async getPaperList() {
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post("paper/selectPaperByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }
      this.total = res.data[0].total;
      this.papersList = res.data[1];
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.papersList.forEach((item) => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.sc_name = this.scObj[item.sc_id];
        item.subject_name = this.subjectObj[item.subject_id];
        item.sd_name = this.departmentObj[item.sd_id];
        item.periodical_name = this.periodicalObj[item.periodical_id];
        item.publish_time = item.publish_time.substring(0, 10);
        item.pt_name = this.ptObj[item.pt_id];
      });
      console.log(this.papersList);
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
    QueryPaperList() {
      console.log(this.queryInfo);
      this.queryInfo.pageNum = 1;
      this.getPaperList();
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
    // 录入论文成果对话框中作者表格选中项改变
    addAuthorSelectionChange(val) {
      this.authorSelectionList = val;
    },
    // 选择论文收录列表发生变化
    periodicalSelectionChange(val) {
      this.addForm.periodicalSelectionList = val;
    },
    // 关闭录入论文对话框，清空相应数据
    addPaperDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.authorTableRef.clearSelection();
      this.$refs.periodicalTableRef.clearSelection();
    },
    // 点击确定，录入论文
    addPaper() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return this.$message.error("请正确填写论文成果信息");
        this.$message.success("收录论文成果成功");
      })
    }
  }
}
</script>

<style lang="less" scoped></style>
