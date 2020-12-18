<template>
    <div style="margin-top: 25px" v-loading="isLoading">
    <download-excel :data="selectionList" :fields="excelFields" style="display: inline;">
      <el-button type="warning" size="medium" :disabled="selectionList.length === 0">导出信息</el-button>
    </download-excel>

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
            <template slot-scope="scope">
              <el-link type="primary" @click="showPaperInfoDialog(scope.row.pe_id)">{{scope.row.paper_name}}</el-link>
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
              <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini" value-format="yyyy-MM-dd 00:00:00"
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

    <el-dialog title="论文成果详情" :visible.sync="paperInfoDialogVisible" width="50%">
      <el-form class="dialog" label-width="90px" label-position="left">
      <el-row :gutter="20">
        <el-col :span="16">
          <el-row>
            <el-form-item label="论文题目：">{{ paperInfo.paper_name }}</el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="论文编号：">{{ paperInfo.pe_id }}</el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="论文类型：">{{ paperInfo.pt_name}}</el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="负责人：">{{ paperInfo.user_name }}</el-form-item>
            </el-col>
            <el-col :span="12"
              ><el-form-item label="发表期刊：">{{ paperInfo.periodical_name}}</el-form-item></el-col
            >
          </el-row>
          <el-row>
            <el-col :span="12"
              ><el-form-item label="发表时间：">{{ paperInfo.publish_time}}</el-form-item></el-col
            >
            <el-col :span="12"
              ><el-form-item label="收录号：">{{ paperInfo.include_number }}</el-form-item></el-col
            >
          </el-row>
          <el-row>
            <el-col :span="12"
              ><el-form-item label="学科门类：">{{ paperInfo.sc_name}}</el-form-item></el-col
            >
            <el-col :span="12"
              ><el-form-item label="一级学科：">{{ paperInfo.subject_name}}</el-form-item></el-col
            >
          </el-row>
          <el-row>
            <el-col :span="12"
              ><el-form-item label="归属单位：">{{ paperInfo.aod_name }}</el-form-item></el-col
            >
            <el-col :span="12"
              ><el-form-item label="来源单位：">{{ paperInfo.sd_name }}</el-form-item></el-col
            >
          </el-row>
          <el-row>
            <el-form-item label="备注">{{paperInfo.remark}}</el-form-item>
          </el-row>
          <el-form-item label="作者:" prop="author" size="mini">
          </el-form-item>
          <el-table :data="memberList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
            :header-cell-style="{ background: '#f5f7fa' }" :default-sort="{prop: 'contribution', order: 'descending'}">
            <!-- 序号列 -->
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
        </el-col>
        <el-col :span="8">
          <el-table  :data="ppList" style="width: 100%; margin: 0" height="550px" border
            size="mini":header-cell-style="{ background: '#f5f7fa' }">
            <el-table-column label="#" type="index"></el-table-column>
            <el-table-column label="收录情况" prop="periodical_name">
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      </el-form>


      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="paperInfoDialogVisible = false">确 定</el-button>
        <el-button @click="paperInfoDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { stringify } from 'qs'
export default {
  data () {
    return {
    // 标签页默认打开
      tabActiveName: 'first',
      // 科研项目信息列表对象
      papersList: [],
      // 搜索条件表单
      queryInfo: {
      pe_id:'',
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
      paperInfoDialogVisible: false,
      paperInfo: {},
      ppList: [],
      isLoading: false,
      memberList: [],
      selectionList: [],
      excelFields: this.$excelFields.paper
    }
  },
  async created() {
    console.log(this.excelFields)
    this.isLoading = true
    await Promise.all([
      this.getDepartmentList(), this.getScList(), this.getSubjectList(), this.getPeriodicalList(), this.getPtList()
    ])
    this.isLoading = false
    this.getPaperList();
  },
  methods: {
    async getDepartmentList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/department/findAllDepartment').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取单位数据失败')) 
          this.departmentList = resp.data.data
          this.departmentList.forEach(item => this.departmentObj[item.department_id] = item.department_name)
          resolve()
        })
      })
    },
    async getScList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/category/findAllSubjectCategory').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取学科门类数据失败')) 
          this.scList = resp.data.data
          this.scList.forEach(item => this.scObj[item.sc_id] = item.sc_name)
          resolve()
        })
      })
    },
    async getSubjectList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/subject/findAllSubject').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取一级学科列表失败')) 
          this.subjectList = resp.data.data
          this.subjectList.forEach(item => this.subjectObj[item.subject_id] = item.subject_name)
          resolve()
        })
      })
    },
    // 获取期刊列表，构造期刊对象
    async getPeriodicalList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/periodical/findAllPeriodical').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取期刊列表失败')) 
          this.periodicalList = resp.data.data
          this.periodicalList.forEach(item => this.periodicalObj[item.periodical_id] = item.periodical_name)
          resolve()
        })
      })
    },
    // 获取论文类型列表
    async getPtList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/paperType/findAllPaperType').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取论文类型列表失败')) 
          this.ptList = resp.data.data
          this.ptList.forEach(item => this.ptObj[item.pt_id] = item.pt_name)
          resolve()
        })
      })
    },
    // 获取论文成果列表
    async getPaperList() {
      this.isLoading = true
      if(this.queryInfo.publish_time !== ''){
        this.queryInfo.publish_time_start = this.queryInfo.publish_time[0]
        this.queryInfo.publish_time_end = this.queryInfo.publish_time[1]
      }else this.queryInfo.publish_time_start = this.queryInfo.publish_time_end = ''
      this.queryInfo.apply_time_start = this.queryInfo.apply_time_end = ''
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post('/paperExamine/selectPaperExamineByCondition', this.$qs.stringify(this.queryInfo))
      if( res.status === '404' ) {
        return this.$router.push('/home')
      }
      this.total=res.data[0].total
      this.papersList = res.data[1]
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.papersList.forEach(item => {
        item.aod_name = this.departmentObj[item.aod_id]
        item.sc_name = this.scObj[item.sc_id]
        item.subject_name = this.subjectObj[item.subject_id]
        item.sd_name = this.departmentObj[item.sd_id]
        item.periodical_name= this.periodicalObj[item.periodical_id]
        item.publish_time = item.publish_time.substring(0, 10)
        item.pt_name = this.ptObj[item.pt_id]
      })
      this.isLoading = false
    },
    // 多选框条件发生变化
    selectionChange(val) {
      this.selectionList = val
    },
    // 导出
    print() {
      console.log('now print!!!')
    },
    // 根据查询条件获取项目列表
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
    },
    async showPaperInfoDialog(paperId) {
      console.log(paperId)
      const { data: res } = await this.$http.post("/paperExamine/findPaperExamineById", this.$qs.stringify({ pe_id: paperId }));
      if (res.status !== "200") return this.$message.error("获取待审核论文成果信息失败");
      this.paperInfo = res.data;
      const { data: res2 } = await this.$http.post('/teamExamine/selectPaperTeamExamineUser', stringify({ pe_id: paperId }))
      if( res2.status !== '200' ) return this.$message.error('获取待审核论文成果团队失败')
      this.memberList = res2.data
      const { data: res3 } = await this.$http.post('/periodicalExamine/findPeriodicalExamineByPaperId', stringify({ pe_id: paperId }))
      if( res3.status !== '200' ) return this.$message.error('获取待审核论文收录期刊列表失败')
      this.ppList = []
      this.periodicalList.forEach(item => {
        if(res3.data.indexOf(item.periodical_id) !== -1) this.ppList.push(item)
      })
      this.paperInfo.aod_name = this.departmentObj[this.paperInfo.aod_id];
      this.paperInfo.sc_name = this.scObj[this.paperInfo.sc_id];
      this.paperInfo.subject_name = this.subjectObj[this.paperInfo.subject_id];
      this.paperInfo.sd_name = this.departmentObj[this.paperInfo.sd_id];
      this.paperInfo.periodical_name = this.periodicalObj[this.paperInfo.periodical_id];
      this.paperInfo.pt_name = this.ptObj[this.paperInfo.pt_id];
      if(this.paperInfo.publish_time !== null) this.paperInfo.publish_time = this.paperInfo.publish_time.substring(0, 10);
      this.paperInfoDialogVisible = true;
    },
  }
}
</script>

<style lang="less" scoped>
</style>
