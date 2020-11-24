<template>
  <div style="margin-top: 25px">
    <el-button type="primary" size="medium" @click="addBooksDialogVisible = true">录入著作</el-button>
    <el-button type="danger" size="medium" :disabled="selection.length === 0">删除著作</el-button>
    <el-button type="warning" size="medium">导出信息</el-button>

    <el-button type="warning" size="medium" @click="print">导出信息</el-button>

    <el-table :data="booksList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange" :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>
      <el-table-column prop="book_name" label="著作题目" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">著作题目</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.book_name" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="authorName" label="著作作者" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">著作作者</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="press" label="出版社" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">出版社</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.press" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="pl_name" label="出版社级别" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">出版社级别</div>
          <el-select class="columnInput" v-model="queryInfo.pl" size="mini" placeholder="请选择" @change="QueryBookList">
            <el-option v-for="item in plList" :key="item.pl_id" :label="item.pl_name" :value="item.pl_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="bt_name" label="著作类型" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">著作类型</div>
          <el-select class="columnInput" v-model="queryInfo.bt" size="mini" placeholder="请选择" @change="QueryBookList">
            <el-option v-for="item in btList" :key="item.bt_id" :label="item.bt_name" :value="item.bt_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="pp_name" label="出版地" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">出版地</div>
          <el-select class="columnInput" v-model="queryInfo.pp_id" size="mini" placeholder="请选择" @change="QueryBookList">
            <el-option v-for="item in ppList" :key="item.pp_id" :label="item.pp_name" :value="item.pp_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">发表日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" @change="QueryBookList" v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"> </el-date-picker>
        </template>
      </el-table-column>

      <el-table-column prop="isbn" label="ISBN号" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">ISBN号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.isbn" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="word_number" label="总字数" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">总字数</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.word_number" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="trans" label="是否翻译" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">总字数</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.trans" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="language_name" label="翻译语种" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">翻译语种</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.language_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in languageList" :key="item.language_id" :label="item.language_name" :value="item.language_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">学科门类</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.sc_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">一级学科</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.subject" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">归属单位</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="sd_name" label="项目来源" width="250px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">项目来源</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.sd_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="rt_name" label="研究类别" width="250px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">研究类别</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.rt_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in rtList" :key="item.rt_id" :label="item.rt_name" :value="item.rt_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pagenum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pagesize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    <!-- 录入著作成果对话框 -->
    <el-dialog title="录入著作成果" :visible.sync="addBooksDialogVisible" width="60%" @close="addBooksDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 著作题目 -->
            <el-form-item label="著作题目:" prop="book_name">
              <el-input v-model="addForm.title" size="mini"></el-input>
            </el-form-item>
            <!-- 出版社、出版社级别 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="出版社:" prop="press">
                  <el-input v-model="addForm.publishUnit" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出版社级别:" prop="pl">
                  <el-select v-model="addForm.pl" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in plList" :label="item.pl_name" :value="item.pl_id" :key="item.pl_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 著作类别，出版地 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="著作类别:" prop="bt">
                  <el-select v-model="addForm.bt" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in btList" :label="item.bt_name" :value="item.bt_id" :key="item.bt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出版地:" prop="pp">
                  <el-select v-model="addForm.pp" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ppList" :label="item.pp_name" :value="item.pp_id" :key="item.pp_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- ISBN、总字数 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="ISBN号:" prop="isbn">
                  <el-input v-model="addForm.isbn" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="总字数:" prop="word_number">
                  <el-input v-model="addForm.word_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 是否翻译，翻译语种 -->
            <el-row :gutter="20">
                <el-col :span="12">
                <el-form-item label="是否翻译:" prop="trans">
                  <el-input v-model="addForm.trans" size="mini"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="翻译语种:" prop="language">
                  <el-select v-model="addForm.language" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in languageList" :label="item.language_name" :value="item.language_id" :key="item.language_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 学科门类，发表时间 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="学科门类:" prop="sc">
                  <el-select v-model="addForm.sc" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in scList" :label="item.sc_name" :value="item.sc_id" :key="item.sc_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="发表时间:" prop="publish_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.publish_time" style="width: 100%" size="mini"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 一级学科,成果归属 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="一级学科:" prop="subject">
                  <el-select v-model="addForm.subject" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in subjectList" :label="item.subject_name" :value="item.subject_id" :key="item.subject_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod">
                  <el-select v-model="addForm.aod" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 项目来源,研究类别 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="项目来源:" prop="sd">
                  <el-select v-model="addForm.sd" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="研究类别:" prop="rt">
                  <el-select v-model="addForm.rt" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in rtList" :label="item.rt_name" :value="item.rt_id" :key="item.rt_id"> </el-option>
                  </el-select>
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
                    <el-option v-for="item in attributionList" :key="item" :label="item" :value="item"> </el-option>
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
        <el-button @click="addBooksDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addBooks">确 定</el-button>
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
      // 出版社
      pressObj: {},
      pressList: [],
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
      addForm: {
        book_name: "",
        leader: "",
        press: "",
        pl: "",
        bt: "",
        pp: "",
        isbn: "",
        word_number: "",
        trans: "",
        language: "",
        sc: "",
        subject: "",
        publish_time: "",
        aod: "",
        sd: "",
        ry: "",
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
        book_name: [
          {
            required: true,
            message: "请输入著作题目",
            trigger: "blur",
          },
        ],
        learder: [
          {
            required: true,
            message: "请输入著作作者",
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
        rt: [
          {
            required: true,
            message: "请选择研究类别",
            trigger: "change",
          },
        ],
        press: [
          {
            required: true,
            message: "请输入出版社",
            trigger: "blur",
          },
        ],
        word_number: [
          {
            required: true,
            message: "请输入总字数",
            trigger: "blur",
          },
        ],
        trans: [
          {
            required: true,
            message: "请选择是否翻译",
            trigger: "change",
          },
        ],
        language: [
          {
            required: true,
            message: "请输入是或者否",
            trigger: "blur",
          },
        ],
        pl: [
          {
            required: true,
            message: "请选择出版社等级",
            trigger: "change",
          },
        ],
        bt: [
          {
            required: true,
            message: "请选择著作类型",
            trigger: "change",
          },
        ],
        publish_time: [
          {
            type: "date",
            required: true,
            message: "请选择发表日期",
            trigger: "change",
          },
        ],
        pp: [
          {
            required: true,
            message: "请选择出版地",
            trigger: "change",
          },
        ],
        isbn: [
          {
            required: true,
            message: "请输入ISBN号",
            trigger: "blur",
          },
        ],
        sc: [
          {
            required: true,
            message: "请选择学科门类",
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
      // // 控制录入论文成果对话框显示与隐藏变量
      addBooksDialogVisible: false,
    };
  },
  async created() {
    await this.getBookData();
    this.getBookList();
  },
  methods: {
    // 获取论文成果列表
    async getBookData() {
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
      // 获取出版社列表
      const { data: res4 } = await this.$http.post("/press/findAllpress");
      this.pressList = res4.data;
      // 构造 出版社 对象
      this.pressList.forEach((item) => (this.pressObj[item.press_id] = item.press_name));
      // 获取出版社等级列表
      const { data: res5 } = await this.$http.post("/pl/findAllpl");
      this.plList = res5.data;
      // 构造 出版社等级对象
      this.plList.forEach((item) => (this.plObj[item.pl_id] = item.pl_name));
      // 构造 著作类型
      const { data: res6 } = await this.$http.post("/bt/findAllbt");
      this.btList = res6.data;
      this.btList.forEach((item) => (this.btObj[item.bt_id] = item.bt_name));
      // 构造 出版地
      const { data: res7 } = await this.$http.post("/pp/findAllpp");
      this.ppList = res7.data;
      this.ppList.forEach((item) => (this.ppObj[item.pp_id] = item.pp_name));
      // 构造语种
      const { data: res8 } = await this.$http.post("/language/findAlllanguage");
      this.languageList = res8.data;
      this.languageList.forEach((item) => (this.languageObj[item.language_id] = item.language_name));
      // 构造研究类型
      const { data: res9 } = await this.$http.post("/rt/findAllrt");
      this.rtList = res9.data;
      this.rtList.forEach((item) => (this.rtObj[item.rt_id] = item.rt_name));
    },

    // 获取论文成果列表
    async getBookList() {
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post("book/selectBookByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }
      this.total = res.data[0].total;
      this.booksList = res.data[1];
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.booksList.forEach((item) => {
        item.aod_name = this.departmentObj[item.aod_id];
        item.sc_name = this.scObj[item.sc_id];
        item.subject_name = this.subjectObj[item.subject_id];
        item.sd_name = this.departmentObj[item.sd_id];
        item.publish_time = item.publish_time.substring(0, 10);
        item.press_name = this.pressObj[item.press_id];
        item.pl_name = this.plObj[item.pl_id];
        item.pp_name = this.ppObj[item.pp_id];
        item.bt_name = this.btObj[item.bt_id];
        item.rt_name = this.rtObj[item.rt_id];
        item.language_name = this.languageObj[item.language_id];
      });
      console.log(this.booksList);
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
    QueryBookList() {
      console.log(this.queryInfo);
      this.queryInfo.pageNum = 1;
      this.getBookList();
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
    // 录入著作成果对话框中作者表格选中项改变
    addAuthorSelectionChange(val) {
      this.authorSelectionList = val;
    },
    // 选择著作收录列表发生变化
    periodicalSelectionChange(val) {
      this.addForm.periodicalSelectionList = val;
    },
    // 关闭录入著作对话框，清空相应数据
    addBooksDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.authorTableRef.clearSelection();
      this.$refs.periodicalTableRef.clearSelection();
    },
    // 点击确定，录入著作
    addBooks() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return this.$message.error("请正确填写著作成果信息");
        this.$message.success("收录著作成果成功");
      })
    }
  }
}
</script>

<style lang="less" scoped></style>
