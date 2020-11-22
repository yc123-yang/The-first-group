<template>
  <div style="margin-top: 25px">
    <el-button type="primary" size="medium">录入项目</el-button>
    <el-button type="danger" size="medium">删除项目</el-button>
    <el-button type="warning" size="medium">导出信息</el-button>

    <el-table :data="projectsList" style="width: 100%; margin-top: 15px;" border
      @selection-change="selectionChange" 
      :header-cell-style="{background: '#f5f7fa'}">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>
      <el-table-column prop="project_name" label="项目名称" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目名称</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.project_name"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="project_id" label="项目编号" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目编号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.project_id"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="department_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">归属单位</div>
          <el-select class="columnInput" v-model="queryInfo.department_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
              :value="item.department_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="sc_name" label="学科门类" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">学科门类</div>
          <el-select class="columnInput" v-model="queryInfo.sc_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">一级学科</div>
          <el-select class="columnInput" v-model="queryInfo.subject_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name"
              :value="item.subject_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="nature_name" label="项目性质" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目性质</div>
          <el-select class="columnInput" v-model="queryInfo.nature_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in natureList" :key="item.nature_id" :label="item.nature_name"
              :value="item.nature_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="level_name" label="项目级别" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目级别</div>
          <el-select class="columnInput" v-model="queryInfo.level_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in levelList" :key="item.level_id" :label="item.level_name"
              :value="item.level_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="status_name" label="项目状态" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目状态</div>
          <el-select class="columnInput" v-model="queryInfo.status_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in statusList" :key="item.status_id" :label="item.status_name"
              :value="item.status_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="sd_name" label="来源单位" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">来源单位</div>
          <el-select class="columnInput" v-model="queryInfo.sd_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
              :value="item.department_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="approval_number" label="批准文号" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">批准文号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.approval_number"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="start_time" label="立项日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">立项日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
            v-model="start_time" type="daterange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" value-format="yyyy-MM-dd" @change="QueryProjectList">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column prop="plan_time" label="计划完成日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">计划完成日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
            v-model="plan_time" type="daterange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" value-format="yyyy-MM-dd" @change="QueryProjectList">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column prop="complete_time" label="结项日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">结项日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
            v-model="complete_time" type="daterange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" value-format="yyyy-MM-dd" @change="QueryProjectList">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column prop="outlay" label="合同经费" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">合同经费</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.outlay"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="ct_name" label="结题形式" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">结题形式</div>
          <el-select class="columnInput" v-model="queryInfo.ct_id"  size="mini"
            collapse-tags placeholder="请选择" clearable @change="QueryProjectList">
            <el-option v-for="item in ctList" :key="item.ct_id" :label="item.ct_name" :value="item.ct_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope">{{scope.haha}}
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
  data() {
    return{
      // 标签页默认打开
      tabActiveName: 'first',
      // 科研项目信息列表对象
      projectsList: [],
      // 查询表单
      queryInfo: {
        project_name: '',
        project_id: '',
        department_id: '',
        sc_id: '',
        subject_id: '',
        nature_id: '',
        level_id: '',
        status_id: '',
        sd_id: '',
        approval_number: '',
        start_time_start: '',
        start_time_end: '',
        plan_time_start: '',
        plan_time_end: '',
        complete_time_start: '',
        complete_time_end: '',
        outlay: '',
        ct_id: '',
        pageNum: 1,
        pageSize: 5,
      },
      // 总的数据条数
      total: 2,
      // 查询条件：立项日期
      start_time: null,
      // 查询条件：计划完成日期
      plan_time: null,
      // 查询条件：结项日期
      complete_time: null,
      // 单位 id:name 对象
      departmentObj: {},
      // 单位对象列表
      departmentList: [{ department_id: 1, department_name: '单位1' }, { department_id: 2, department_name: '单位2' }],
      // 学科门类 id:name 对象
      scObj: {},
      // 学科门类列表
      scList: [{ sc_id: 1, sc_name: '学科门类1' },{ sc_id: 2, sc_name: '学科门类2' }],
      // 一级学科 id:name 对象
      subjectObj: {},
      // 一级学科列表
      subjectList: [{ subject_id: 1, subject_name: '一级学科1' }, { subject_id: 2, subject_name: '一级学科2' }],
      // 项目性质 id:name 对象
      natureObj: {},
      // 项目性质列表
      natureList: [{ nature_id: 1, nature_name: '项目性质1' }, { nature_id: 2, nature_name: '项目性质2' }],
      // 项目级别 id:name 对象
      levelObj: {},
      // 项目级别列表
      levelList: [{ level_id: 1, level_name: '项目级别1' }, { level_id: 2, level_name: '项目级别2' }],
      // 项目状态 id:name 对象
      statusObj: {},
      // 项目状态列表
      statusList: [{ status_id: 1, status_name: '项目状态1' }, { status_id: 2, status_name: '项目状态2' }],
      // 结题形式 id:name 对象
      ctObj: {},
      // 结题形式列表
      ctList: [{ ct_id: 1, ct_name: '结题形式1' }, { ct_id: 2, ct_name: '结题形式2' }]
    }
  },
  async created() {
    await this.getProjectData()
    this.getProjectList()
  },
  methods: {
    // 获取科研项目页面所需的全部数据项，构造id:name字典
    async getProjectData() {
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
      // 获取项目性质列表
      const { data: res4 } = await this.$http.post('/nature/findAllnature')
      this.natureList = res4.data
      // 构造项目性质 id:name 对象
      this.natureList.forEach(item => this.natureObj[item.nature_id] = item.nature_name)
      // 获取项目级别列表
      const { data: res5 } = await this.$http.post('/level/findAlllevel')
      this.levelList = res5.data
      // 构造项目级别 id:name 对象
      this.levelList.forEach(item => this.levelObj[item.level_id] = item.level_name)
      // 获取项目状态列表
      const { data: res6 } = await this.$http.post('status/findAllstatus')
      this.statusList = res6.data
      // 构造项目状态 id:name 对象
      this.statusList.forEach(item => this.statusObj[item.status_id] = item.status_name)
      // 获取结题形式列表
      const { data: res7 } = await this.$http.post('ct/findAllct')
      this.ctList = res7.data
      // 构造结题形式 id:name 对象
      this.ctList.forEach(item => this.ctObj[item.ct_id]=item.ct_name)
    },
    // 获取科研项目列表
    async getProjectList() {
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post('/project/selectProjectByCondition', this.$qs.stringify(this.queryInfo))
      if( res.status === '404' ) {
        return this.$router.push('/home')
      }
      this.total=res.data[0].total
      this.projectsList = res.data[1]
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.projectsList.forEach(item => {
        item.department_name = this.departmentObj[item.department_id]
        item.sc_name = this.scObj[item.sc_id]
        item.subject_name = this.subjectObj[item.subject_id]
        item.nature_name = this.natureObj[item.nature_id]
        item.level_name = this.levelObj[item.level_id]
        item.status_name = this.statusObj[item.status_id]
        item.sd_name = this.departmentObj[item.sd_id]
        item.start_time = item.start_time.substring(0, 10)
        item.plan_time = item.plan_time.substring(0, 10)
        item.complete_time = item.complete_time.substring(0, 10)
        item.ct_name = this.ctObj[item.ct_id]
      })
      console.log(this.projectsList)
    },
    // 多选框条件发生变化
    selectionChange() {
      console.log('selection change!!!')
    },
    // 导出
    print() {
      console.log('now print!!!')
    },
    // 根据查询条件获取项目列表
    QueryProjectList() {
      if( this.start_time !== null ) {
        this.queryInfo.start_time_start = this.start_time[0] + ' 00:00:00'
        this.queryInfo.start_time_end = this.start_time[1] + ' 00:00:00'
      } else this.queryInfo.start_time_start = this.queryInfo.start_time_end = ''
      if( this.plan_time !== null ) {
        this.queryInfo.plan_time_start = this.plan_time[0] + ' 00:00:00'
        this.queryInfo.plan_time_end = this.plan_time[1] + ' 00:00:00'
      } else this.queryInfo.plan_time_start = this.queryInfo.plan_time_end = ''
      if( this.complete_time !== null ) {
        this.queryInfo.complete_time_start = this.complete_time[0] + ' 00:00:00'
        this.queryInfo.complete_time_end = this.complete_time[1] + ' 00:00:00'
      } else this.queryInfo.complete_time_start = this.queryInfo.complete_time_end = ''
      console.log(this.queryInfo)
      this.queryInfo.pageNum = 1
      this.getProjectList()
    },
    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getProjectList()
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getProjectList()
    }
  }
}
</script>

<style lang="less" scoped>

</style>