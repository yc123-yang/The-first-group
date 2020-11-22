<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>科研成果</el-breadcrumb-item>
      <el-breadcrumb-item>论文成果</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图 -->
    <el-card>
      <el-row>
        <el-button type="primary" size="medium" @click="addPaperDialogVisible = true">录入论文</el-button>
        <el-button type="danger" size="medium" :disabled="selection.length === 0">删除论文</el-button>
        <el-button type="warning" size="medium" :disabled="selection.length === 0">导出信息</el-button>
      </el-row>
      <el-row>
        <el-table :data="paperList" style="width: 100%; margin-top: 15px" max-height="250" border
          @selection-change="selectionChange"
          :header-cell-style="{background: '#f5f7fa'}">
          <!-- 序号列 -->
          <el-table-column type="index" label="#" align="center" fixed></el-table-column>
          <!-- 多选列 -->
          <el-table-column type="selection" align="center" fixed></el-table-column>
          <el-table-column prop="paper_name" label="论文题目" width="300px" align="center" fixed>
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">论文题目</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.paper_name"
                placeholder="请输入">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="authorName" label="论文作者" width="150px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">论文作者</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName"
                placeholder="请输入">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="periodical_name" label="发表期刊" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">发表期刊</div>
              <el-select class="columnInput" v-model="queryInfo.periodical_id" size="mini" clearable
                placeholder="请选择">
                <el-option v-for="item in periodicalList" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">发表日期</div>
              <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
                v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column prop="pt_name" label="论文类型" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">论文类型</div>
              <el-select class="columnInput" v-model="queryInfo.pt_id" clearable size="mini"
                collapse-tags placeholder="请选择">
                <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="include_number" label="收录号" width="300px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">收录号</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.include_number"
                placeholder="请输入">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">学科门类</div>
              <el-select class="columnInput" v-model="queryInfo.sc_id" clearable size="mini"
                collapse-tags placeholder="请选择">
                <el-option v-for="item in subjectCateList" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="subject" label="一级学科" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">一级学科</div>
              <el-select class="columnInput" v-model="queryInfo.subject" clearable size="mini"
                collapse-tags placeholder="请选择">
                <el-option v-for="item in subjectList" :key="item" :label="item" :value="item">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="attribution" label="成果归属" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">成果归属</div>
              <el-select class="columnInput" v-model="queryInfo.attribution" clearable size="mini"
                collapse-tags placeholder="请选择">
                <el-option v-for="item in attributionList" :key="item" :label="item" :value="item">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="source" label="项目来源" width="250px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">项目来源</div>
              <el-select class="columnInput" v-model="queryInfo.source" clearable size="mini"
                collapse-tags placeholder="请选择">
                <el-option v-for="item in sourceList" :key="item" :label="item" :value="item">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="remark" label="备注" width="300px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">备注</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.remark"
                placeholder="请输入">
              </el-input>
            </template>
          </el-table-column>

          <!-- 操作列 -->
          <el-table-column label="操作" align="center" width="120" fixed="right">
            <template slot-scope="scope">{{scope.haha}}
              <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
              <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>

      <!-- 分页组件 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <!-- 录入论文成果对话框 -->
      <el-dialog title="录入论文成果" :visible.sync="addPaperDialogVisible" width="70%"
        @close="addPaperDialogClosed">
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="90px" size="mini">
          <el-row :gutter="20">
            <!-- 表单左侧 -->
            <el-col :span="16">
              <!-- 论文题目 -->
              <el-form-item label="论文题目:" prop="title">
                <el-input v-model="addForm.title" size="mini"></el-input>
              </el-form-item>
              <!-- 论文类型、发表期刊 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="论文类型:" prop="type">
                    <el-select v-model="addForm.type" placeholder="请选择" size="mini"
                    style="width: 100%;">
                      <el-option v-for="item in typeList" :label="item" :value="item" :key="item">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="发表期刊:" prop="periodical">
                    <el-select v-model="addForm.periodical" placeholder="请选择"
                      size="mini" style="width: 100%;">
                      <el-option v-for="item in periodicalList" :label="item" :value="item" :key="item">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- 发表时间 -->
              <el-form-item label="发表时间:" prop="date">
                <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.date"
                style="width: 100%;" size="mini">
                </el-date-picker>
              </el-form-item>
              <!-- 收录号 -->
              <el-form-item label="收录号:" prop="accessionNumber">
                <el-input v-model="addForm.accessionNumber" size="mini"></el-input>
              </el-form-item>
              <!-- 学科门类、一级学科 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="学科门类:" prop="subjectCate">
                    <el-select v-model="addForm.subjectCate" placeholder="请选择" size="mini"
                    style="width: 100%;">
                      <el-option v-for="item in subjectCateList" :label="item" :value="item" :key="item">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="一级学科:" prop="subject">
                    <el-select v-model="addForm.subject" placeholder="请选择"
                      size="mini" style="width: 100%;">
                      <el-option v-for="item in subjectList" :label="item" :value="item" :key="item">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- 归属单位 -->
              <el-form-item label="归属单位:" prop="attribution">
                <el-select v-model="addForm.attribution" placeholder="请选择" size="mini"
                style="width: 100%;">
                  <el-option v-for="item in attributionList" :label="item" :value="item" :key="item">
                  </el-option>
                </el-select>
              </el-form-item>
              <!-- 项目来源 -->
              <el-form-item label="项目来源:" prop="source">
                <el-select v-model="addForm.source" placeholder="请选择" size="mini"
                style="width: 100%;">
                  <el-option v-for="item in sourceList" :label="item" :value="item" :key="item">
                  </el-option>
                </el-select>
              </el-form-item>
              <!-- 备注 -->
              <el-form-item label="备注:" prop="remark">
                <el-input v-model="addForm.remark" size="mini"></el-input>
              </el-form-item>
              <el-form-item label="作者:" prop="author" size="mini">
                <el-button type="primary" size="mini">添加作者</el-button>
                <el-button type="danger" size="mini" :disabled="authorSelectionList.length === 0">
                  批量删除
                </el-button>
              </el-form-item>
              <el-table :data="addForm.authorList" style="width: 100%;" ref="authorTableRef"
                @selection-change="addAuthorSelectionChange" size="mini" border height="250px"
                :header-cell-style="{background: '#f5f7fa'}">
                <!-- 序号列 -->
                <el-table-column type="index" label="#" align="center"></el-table-column>
                <!-- 多选列 -->
                <el-table-column type="selection" align="center"></el-table-column>
                <el-table-column prop="authorName" label="作者姓名" width="120px" align="center">
                  <template slot="header" slot-scope="scope">{{scope.haha}}
                    <div style="line-height: 14px;">作者姓名</div>
                    <el-input class="columnInput" size="mini" clearable v-model="authorQueryInfo.authorName"
                      placeholder="请输入">
                    </el-input>
                  </template>
                </el-table-column>
                <el-table-column prop="authorType" label="成员类型" width="180px" align="center">
                  <template slot="header" slot-scope="scope">{{scope.haha}}
                    <div style="line-height: 14px;">成员类型</div>
                    <el-select class="columnInput" v-model="authorQueryInfo.authorType" multiple size="mini"
                      collapse-tags placeholder="请选择">
                      <el-option v-for="item in authorTypeList" :key="item" :label="item" :value="item">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column prop="employer" label="归属单位" width="200px" align="center">
                  <template slot="header" slot-scope="scope">{{scope.haha}}
                    <div style="line-height: 14px;">归属单位</div>
                    <el-select class="columnInput" v-model="authorQueryInfo.employer" multiple size="mini"
                      collapse-tags placeholder="请选择">
                      <el-option v-for="item in attributionList" :key="item" :label="item" :value="item">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column prop="rank" label="排名" align="center"></el-table-column>
                <el-table-column prop="rate" label="贡献率" align="center"></el-table-column>
                <!-- 操作列 -->
                <el-table-column label="操作" align="center" width="120" fixed="right">
                  <template slot-scope="scope">{{scope.haha}}
                    <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
                    <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-col>
            <!-- 表单右侧 -->
            <el-col :span="8">
              <el-table :data="periodicalList" style="width: 100%;margin: 0;" height="670px" border
                @selection-change="periodicalSelectionChange" size="mini" ref="periodicalTableRef"
                :header-cell-style="{background: '#f5f7fa'}">
                <!-- 多选列 -->
                <el-table-column type="selection" align="center"></el-table-column>
                <el-table-column label="收录情况">
                  <template slot-scope="scope">{{scope.row}}</template>
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
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 搜索条件表单
      queryInfo: {
        // 论文题目
        paper_name: '',
        // 论文作者
        authorName: '',
        // 发表期刊id
        periodical_id: '',
        // 发表日期区间
        publish_time: '',
        // 论文类型
        pt_id: '',
        // 收录号
        include_number: '',
        // 学科门类
        subjectCate: '',
        // 一级学科
        subject: '',
        // 归属单位
        attribution: '',
        // 项目来源
        source: '',
        // 备注
        remark: '',
        // 当前页码
        pagenum: 1,
        // 当前页大小
        pagesize: 2
      },
      // 论文信息列表
      paperList: [{
        paper_id: 0,
        paper_name: '论文题目',
        authorName: '论文作者',
        periodical_id: 1,
        publish_time: '2016-05-02',
        pt_id: 1,
        include_number: '收录号',
        sc_id: 1,
        subject: '一级学科',
        attribution: '归属单位',
        source: '项目来源',
        remark: '备注'
      }//, {
      //   paper_name: '论文题目',
      //   authorName: '论文作者',
      //   periodical: '发表期刊',
      //   date: '2016-05-02',
      //   type: '论文类型',
      //   accessionNumber: '收录号',
      //   subjectCate: '学科门类',
      //   subject: '一级学科',
      //   attribution: '归属单位',
      //   source: '项目来源',
      //   remark: '备注'
      // }
      ],
      // 期刊转换对象
      periodicalObj: { 1: '期刊1', 2: '期刊2' },
      // 期刊列表
      periodicalList: [
        { value: 1, label: '期刊1'},
        { value: 2, label: '期刊2'}
      ],
      // 论文转换对象
      typeObj: { 1: '类型1', 2: '类型2' },
      // 论文类型列表
      typeList: [
        { value: 1, label: '类型1'},
        { value: 2, label: '类型2'}
      ],
      // 学科门类转换对象
      subjectCateObj: { 1: '学科门类1', 2: '学科门类2' },
      // 学科门类列表
      subjectCateList: [
        { value: 1, label: '学科门类1'}, 
        { value: 2, label: '学科门类2'}
      ],
      // 一级学科列表
      subjectList: ['一级学科1', '一级学科2'],
      // 归属单位列表
      attributionList: ['单位1', '单位2'],
      // 项目来源列表
      sourceList: ['项目来源1', '项目来源2'],
      // 总共数据条数
      total: 2,
      // 表格多选记录
      selection: [],
      // 控制录入论文成果对话框显示与隐藏变量
      addPaperDialogVisible: false,
      // 录入论文数据表单
      addForm: {
        title: '',
        authorName: '',
        periodical: '',
        date: '',
        type: '',
        accessionNumber: '',
        subjectCate: '',
        subject: '',
        attribution: '',
        source: '',
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
        title: [
          { required: true, message: '请输入论文题目', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择论文类型', trigger: 'change' }
        ],
        periodical: [
          { required: true, message: '请选择发表期刊', trigger: 'change' }
        ],
        date: [
          { type: 'date', required: true, message: '请选择发表日期', trigger: 'change' }
        ],
        subjectCate: [
          { required: true, message: '请选择学科门类', trigger: 'change' }
        ],
        attribution: [
          { required: true, message: '请选择成果归属单位', trigger: 'change' }
        ]
      },
      // 作者列表查询表单
      authorQueryInfo: {
        authorName: '',
        authorType: [],
        employer: []
      },
      // 选中的作者列表
      authorSelectionList: [],
      // 成员类型列表
      authorTypeList: ['成员类型1', '成员类型2']
    }
  },
  created () {
    this.getPaperList()
  },
  methods: {
    // 获取论文成果列表
    getPaperList () {
      this.paperList.forEach(item => {
        item.periodical_name = this.periodicalObj[item.periodical_id]
        item.pt_name = this.typeObj[item.pt_id]
        item.sc_name = this.subjectCateObj[item.sc_id]
      })
    },
    // 表格多选选中项改变
    selectionChange (val) {
      this.selection = val
    },
    // 分页大小发生变化
    handleSizeChange (val) {
      this.queryInfo.pagesize = val
      this.queryInfo.pagenum = 1
      this.getPaperList()
    },
    // 页数发生变化
    handleCurrentChange (val) {
      this.queryInfo.pagenum = val
      this.getPaperList()
    },
    // 录入论文成果对话框中作者表格选中项改变
    addAuthorSelectionChange (val) {
      this.authorSelectionList = val
    },
    // 选择论文收录列表发生变化
    periodicalSelectionChange (val) {
      this.addForm.periodicalSelectionList = val
    },
    // 关闭录入论文对话框，清空相应数据
    addPaperDialogClosed () {
      this.$refs.addFormRef.resetFields()
      this.$refs.authorTableRef.clearSelection()
      this.$refs.periodicalTableRef.clearSelection()
    },
    // 点击确定，录入论文
    addPaper () {
      this.$refs.addFormRef.validate(valid => {
        if (!valid) return this.$message.error('请正确填写论文成果信息')
        this.$message.success('收录论文成果成功')
      })
    }
  }
}
</script>

<style lang="less" scoped>
</style>
