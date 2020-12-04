<template>
    <div style="margin-top: 25px">
    <el-button type="warning" size="medium" @click="print">导出信息</el-button>

    <el-table :data="papersList" style="width: 100%; margin-top: 15px;" border
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
                placeholder="请输入" @change="QueryPaperList">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="authorName" label="论文作者" width="150px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">论文作者</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName"
                placeholder="请输入" @change="QueryPaperList">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="periodical_name" label="发表期刊" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">发表期刊</div>
              <el-select class="columnInput" v-model="queryInfo.periodical_id" size="mini" clearable
                placeholder="请选择" @change="QueryPaperList">
                <el-option v-for="item in periodicalList" :key="item.periodical_id" :label="item.periodical_name" :value="item.periodical_id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">发表日期</div>
              <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
                v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期"
                end-placeholder="结束日期" @change="QueryPaperList">
              </el-date-picker>
            </template>
          </el-table-column>
          
          <el-table-column prop="pt_name" label="论文类型" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">论文类型</div>
              <el-select class="columnInput" v-model="queryInfo.pt_id" clearable size="mini"
                collapse-tags placeholder="请选择" @change="QueryPaperList">
                <el-option v-for="item in ptList" :key="item.pt_id" :label="item.pt_name" :value="item.pt_id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="include_number" label="收录号" width="300px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">收录号</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.include_number"
                placeholder="请输入" @change="QueryPaperList">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">学科门类</div>
              <el-select class="columnInput" v-model="queryInfo.sc_id" clearable size="mini"
                collapse-tags placeholder="请选择" @change="QueryPaperList">
                <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">一级学科</div>
              <el-select class="columnInput" v-model="queryInfo.subject_id" clearable size="mini"
                collapse-tags placeholder="请选择" @change="QueryPaperList">
                <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
            <template slot="header" slot-scope="scope">{{scope.haha}}
              <div style="line-height: 14px;">归属单位</div>
              <el-select class="columnInput" v-model="queryInfo.aod_id" clearable size="mini"
                collapse-tags placeholder="请选择" @change="QueryPaperList">
                <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
           <el-table-column prop="sd_name" label="来源单位" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">来源单位</div>
          <el-select class="columnInput" v-model="queryInfo.sd_id"  size="mini" @change="QueryPaperList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
              :value="item.department_id">
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
    <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope"
          >{{ scope.haha }}
          <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNum"
      :page-sizes="[1, 2, 5, 10]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data () {
    return {
    // 标签页默认打开
      tabActiveName: 'first',
      // 列表对象
      papersList: [],
      // 搜索条件表单
      queryInfo: {
      paper_id:'',
      paper_name:'',
      pt_id:'',
      periodical_id:'',
      publish_time:'',
      include_number:'',
      sc_id:'',
      subject_id:'',
      aod_id:'',
      sd_id:'',
      remark:'',

        // 当前页码
        pagenum: 1,
        // 当前页大小
        pagesize: 2
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
      periodicalObj:{},
      // 期刊列表
      periodicalList:[],
      //论文类型 id对象
      ptObj:{},
      //论文类型 列表
      ptList:[],


      // // 表格多选记录
      // selection: [],
      // // 控制录入论文成果对话框显示与隐藏变量
      // addPaperDialogVisible: false,
    }
  },
  async created() {
      await this.getPaperData()
      this.getPaperList()
  },
  methods: {
    // 获取论文成果列表
    async getPaperData () {
       // 获取单位列表
      const { data: res1 } = await this.$http.post('/department/findAlldepartment')
      this.departmentList = res1.data
      // 构造单位 id:name 对象
      this.departmentList.forEach(item => this.departmentObj[item.department_id] = item.department_name)
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post('/sc/findAllsc')
      this.scList = res2.data
      // 构造学科门类 id:name 对象
      this.scList.forEach(item => this.scObj[item.sc_id] = item.sc_name)
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post('/subject/findAllsubject')
      this.subjectList = res3.data
      // 构造一级学科 id:name 对象
      this.subjectList.forEach(item => this.subjectObj[item.subject_id] = item.subject_name)
      // 获取期刊列表
      const { data: res4 } = await this.$http.post('/periodical/findAllperiodical')
      this.periodicalList = res4.data
      // 构造 期刊 对象
      this.periodicalList.forEach(item => this.periodicalObj[item.periodical_id] = item.periodical_name)
      // 获取论文类型列表
      const { data: res5 } = await this.$http.post('/pt/findAllpt')
      this.ptList = res5.data
      // 构造 论文类型对象
      this.ptList.forEach(item => this.ptObj[item.pt_id] = item.pt_name)
    },

       // 获取论文成果列表
    async getPaperList() {
      // 通过 post 请求获取列表
      const { data: res } = await this.$http.post('paper/selectPaperByCondition', this.$qs.stringify(this.queryInfo))
      if( res.status === '404' ) {
        return this.$router.push('/home')
      }
      this.total=res.data[0].total
      this.papersList = res.data[1]
      // 通过 id:name 对象处理列表，为其加上各name属性，截取时间日期
      this.papersList.forEach(item => {
        item.aod_name = this.departmentObj[item.aod_id]
        item.sc_name = this.scObj[item.sc_id]
        item.subject_name = this.subjectObj[item.subject_id]
        item.sd_name = this.departmentObj[item.sd_id]
        item.periodical_name= this.periodicalObj[item.periodical_id]
        item.publish_time = item.publish_time.substring(0, 10)
        item.pt_name = this.ptObj[item.pt_id]
      })
      console.log(this.papersList)
    },
    // 多选框条件发生变化
    selectionChange() {
      console.log('selection change!!!')
    },
    // 导出
    print() {
      console.log('now print!!!')
    },
    // 根据查询条件获取列表
    QueryPaperList() {
      console.log(this.queryInfo)
      this.queryInfo.pageNum = 1
      this.getPaperList()
    },
    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getPaperList()
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getPaperList()
    }
  }
}
</script>

<style lang="less" scoped>
</style>
