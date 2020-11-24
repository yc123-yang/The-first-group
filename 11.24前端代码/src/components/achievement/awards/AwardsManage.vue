<template>
  <div style="margin-top: 25px">
    <el-button type="primary" size="medium">录入项目</el-button>
    <el-button type="danger" size="medium">删除项目</el-button>
    <el-button type="warning" size="medium">导出信息</el-button>

    <el-button type="warning" size="medium" @click="print">导出信息</el-button>

    <el-table :data="awardsList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange" :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>

      <el-table-column prop="achievement_name" label="成名名字" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">成果名字</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.achievement_name" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="authorName" label="著作作者" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">著作作者</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="award_name" label="获奖名字" width="300px" align="center" >
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

      <el-table-column prop="issuing_authority" label="发证机关" width="300px" align="center" >
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
    </el-table>

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pagenum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pagesize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 标签页默认打开
      tabActiveName: "first",
      // 科研项目信息列表对象
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
        pagenum: 1,
        // 当前页大小
        pagesize: 10,
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

      // // 表格多选记录
      // selection: [],
      // // 控制录入论文成果对话框显示与隐藏变量
      // addPaperDialogVisible: false,
    };
  },
  async created() {
    await this.getAwardData();
    this.getAwardList();
  },
  methods: {
    // 获取论文成果列表
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

    // 获取论文成果列表
    async getAwardList() {
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post("award/selectAwardByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }
      this.total = res.data[0].total;
      this.awardsList = res.data[1];
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
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
    // 根据查询条件获取项目列表
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
  },
};
</script>

<style lang="less" scoped></style>
