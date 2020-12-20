<template>
    <div style="margin-top: 25px" v-loading="isLoading">
    <download-excel :data="selectionList" :fields="excelFields" style="display: inline;">
      <el-button type="warning" size="medium" :disabled="selectionList.length === 0">导出信息</el-button>
    </download-excel>


    <el-table :data="booksList" style="width: 100%; margin-top: 15px;" border
      @selection-change="selectionChange"
      :header-cell-style="{background: '#f5f7fa'}">
          <!-- 序号列 -->
          <el-table-column type="index" label="#" align="center" fixed></el-table-column>
          <!-- 多选列 -->
          <el-table-column type="selection" align="center" fixed></el-table-column>
          <el-table-column prop="book_name" label="著作题目" width="300px" align="center" fixed>
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">著作题目</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.book_name" placeholder="请输入" @change="QueryBookList"> </el-input>
            </template>
            <template slot-scope="scope">
              <el-link type="primary" @click="showBookInfoDialog(scope.row.book_id)">{{scope.row.book_name}}</el-link>
            </template>
          </el-table-column>

          <el-table-column prop="authorName" label="著作作者" width="150px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">著作作者</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryBookList"> </el-input>
            </template>
          </el-table-column>

          <el-table-column prop="press" label="出版社" width="150px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">出版社</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.press" placeholder="请输入" @change="QueryBookList"> </el-input>
            </template>
          </el-table-column>
          
          <el-table-column prop="pl_name" label="出版社级别" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">出版社级别</div>
              <el-select class="columnInput" v-model="queryInfo.pl_id" size="mini" placeholder="请选择" @change="QueryBookList" clearable>
                <el-option v-for="item in plList" :key="item.pl_id" :label="item.pl_name" :value="item.pl_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column prop="bt_name" label="著作类型" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">著作类型</div>
              <el-select class="columnInput" v-model="queryInfo.bt_id" size="mini" placeholder="请选择" @change="QueryBookList" clearable>
                <el-option v-for="item in btList" :key="item.bt_id" :label="item.bt_name" :value="item.bt_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column prop="pp_name" label="出版地" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">出版地</div>
              <el-select class="columnInput" v-model="queryInfo.pp_id" size="mini" placeholder="请选择" @change="QueryBookList" clearable>
                <el-option v-for="item in ppList" :key="item.pp_id" :label="item.pp_name" :value="item.pp_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>



          <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">发表日期</div>
              <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini"  @change="QueryBookList"
                v-model="publish_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
                value-format="yyyy-MM-dd 00:00:00"> </el-date-picker>
            </template>
          </el-table-column>

          <el-table-column prop="isbn" label="ISBN号" width="150px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">ISBN号</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.isbn" placeholder="请输入" @change="QueryBookList"> </el-input>
            </template>
          </el-table-column>

          <el-table-column prop="word_number" label="总字数" width="150px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">总字数</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.word_number" placeholder="请输入" @change="QueryBookList"> </el-input>
            </template>
          </el-table-column>

          <el-table-column prop="trans" label="是否翻译" width="150px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">是否翻译</div>
              <el-input class="columnInput" size="mini" clearable v-model="queryInfo.trans" placeholder="请输入" @change="QueryBookList"> </el-input>
            </template>
          </el-table-column>

          <el-table-column prop="language_name" label="翻译语种" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">翻译语种</div>
              <el-select  @change="QueryBookList" class="columnInput" v-model="queryInfo.language_id" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in languageList" :key="item.language_id" :label="item.language_name" :value="item.language_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">学科门类</div>
              <el-select  @change="QueryBookList" class="columnInput" v-model="queryInfo.sc_id" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">一级学科</div>
              <el-select @change="QueryBookList"  class="columnInput" v-model="queryInfo.subject_id" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">归属单位</div>
              <el-select  @change="QueryBookList" class="columnInput" v-model="queryInfo.aod_id" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column prop="sd_name" label="项目来源" width="250px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">项目来源</div>
              <el-select  @change="QueryBookList" class="columnInput" v-model="queryInfo.sd_id" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column prop="rt_name" label="研究类别" width="250px" align="center">
            <template slot="header" slot-scope="scope">
              <div style="line-height: 14px">研究类别</div>
              <el-select  @change="QueryBookList" class="columnInput" v-model="queryInfo.rt_id" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in rtList" :key="item.rt_id" :label="item.rt_name" :value="item.rt_id"> </el-option>
              </el-select>
            </template>
          </el-table-column>

        </el-table>

      <!-- 分页组件 -->
      <el-pagination @size-change="handleSizeChange" 
      @current-change="handleCurrentChange" 
      :current-page="queryInfo.pageNum" 
      :page-sizes="[1, 2, 5, 10]" 
      :page-size="queryInfo.pageSize" 
      layout="total, sizes, prev, pager, next, jumper" 
      :total="total"> 
      </el-pagination>

    <el-dialog title="著作成果详情" :visible.sync="bookInfoDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="著作题目：">{{ bookInfo.book_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="著作编号：">{{ bookInfo.book_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="著作类别：">{{ bookInfo.bt_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="负责人：">{{ bookInfo.user_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="出版地：">{{ bookInfo.pp_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="出版社：">{{ bookInfo.press }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="出版社级别：">{{ bookInfo.pl_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="学科门类：">{{ bookInfo.sc_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="一级学科：">{{ bookInfo.subject_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="归属单位：">{{ bookInfo.aod_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="来源单位：">{{ bookInfo.sd_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="ISBN号：">{{ bookInfo.isbn }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="总字数：">{{ bookInfo.word_number }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="是否翻译：">{{ bookInfo.trans }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="翻译语种：">{{ bookInfo.language_name }}</el-form-item></el-col
          >
        </el-row>

        <el-row>
          <el-col :span="12"
            ><el-form-item label="发表时间：">{{ bookInfo.publish_time }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="研究类别：">{{ bookInfo.rt_name }}</el-form-item></el-col
          >
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
        <el-button type="primary" @click="bookInfoDialogVisible = false">确 定</el-button>
        <el-button @click="bookInfoDialogVisible = false">取 消</el-button>
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
      booksList: [],
      // 搜索条件表单
      queryInfo: {
        book_id:'',
        leader_id:'',
        book_name:'',
        press:'',
        pl_id:'',
        bt_id:'',
        pp_id:'',
        isbn:'',
        word_number:'',
        trans:'',
        language_id:'',
        sc_id:'',
        subject_id:'',
        aod_id:'',
        sd_id:'',
        rt_id:'',


        // 当前页码
        pageNum: 1,
        // 当前页大小
        pageSize: 5
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
      pressObj:{},
      pressList:[],
      // 出版社等级
      plObj:{},
      plList:[],
      // 著作类型
      btObj:{},
      btList:[],
      // 出版地
      ppObj:{},
      ppList:[],
      // 语种
      languageObj:{},
      languageList:[],
      // 研究类型
      rtObj:{},
      rtList:[],
      bookInfoDialogVisible: false,
      bookInfo: {},
      memberList: [],
      isLoading: false,
      selectionList: [],
      excelFields: this.$excelFields.book
    }
  },
  async created() {
    this.isLoading = true
    await Promise.all([
      this.getDepartmentList(), this.getScList(), this.getSubjectList(), this.getPtList(),
      this.getBtList(), this.getPpList(), this.getLanguageListList(), this.getRtList()
    ])
    this.isLoading = false
    this.getBookList()
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
    // 出版社等级列表
    async getPtList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/pressLevel/findAllPressLevel').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取出版社等级列表失败')) 
          this.plList = resp.data.data
          this.plList.forEach(item => this.plObj[item.pl_id] = item.pl_name)
          resolve()
        })
      })
    },
    // 著作类型
    async getBtList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/bookType/findAllBookType').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取著作类型列表失败')) 
          this.btList = resp.data.data
          this.btList.forEach(item => this.btObj[item.bt_id] = item.bt_name)
          resolve()
        })
      })
    },
    // 出版地
    async getPpList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/publicationPlace/findAllPublicationPlace').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取出版地列表失败')) 
          this.ppList = resp.data.data
          this.ppList.forEach(item => this.ppObj[item.pp_id] = item.pp_name)
          resolve()
        })
      })
    },
    // 语种
    async getLanguageListList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/language/findAllLanguage').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取翻译语种列表失败')) 
          this.languageList = resp.data.data
          this.languageList.forEach(item => this.ppObj[item.pp_id] = item.pp_name)
          resolve()
        })
      })
    },
    // 研究类型
    async getRtList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/researchType/findAllResearchType').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取研究类型列表失败')) 
          this.rtList = resp.data.data
          this.rtList.forEach(item => this.rtObj[item.rt_id] = item.rt_name)
          resolve()
        })
      })
    },
    // 获取论文成果列表
    async getBookList() {
      this.isLoading = true
      // 通过 post 请求获取科研项目列表
      if(this.publish_time !== null) {
        this.queryInfo.publish_time_start = this.publish_time[0]
        this.queryInfo.publish_time_end = this.publish_time[0]
      } else this.queryInfo.publish_time_start = this.queryInfo.publish_time_end = ''
      const { data: res } = await this.$http.post('/book/selectAllBookByCondition', this.$qs.stringify(this.queryInfo))
      if( res.status === '404' ) {
        return this.$router.push('/home')
      }
      this.total=res.data[0].total
      this.booksList = res.data[1]
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.booksList.forEach(item => {
        item.aod_name = this.departmentObj[item.aod_id]
        item.sc_name = this.scObj[item.sc_id]
        item.subject_name = this.subjectObj[item.subject_id]
        item.sd_name = this.departmentObj[item.sd_id]
        item.publish_time = item.publish_time.substring(0, 10)
        item.press_name = this.pressObj[item.press_id]
        item.pl_name = this.plObj[item.pl_id]
        item.pp_name = this.ppObj[item.pp_id]
        item.bt_name = this.btObj[item.bt_id]
        item.rt_name = this.rtObj[item.rt_id]
        item.language_name = this.languageObj[item.language_id]
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
    QueryBookList() {
      console.log(this.queryInfo)
      this.queryInfo.pageNum = 1
      this.getBookList()
    },
    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getBookList()
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getBookList()
    },
    async showBookInfoDialog(bookId) {
      const { data: res } = await this.$http.post("/book/findBookById", this.$qs.stringify({ book_id: bookId }));
      if (res.status !== "200") return this.$message.error("获取著作成果信息失败");
      this.bookInfo = res.data;
      const { data: res2 } = await this.$http.post('/team/selectBookTeam', stringify({ book_id: bookId }))
      if( res2.status !== '200' ) return this.$message.error('获取著作成果团队信息失败')
      this.memberList = res2.data
      this.bookInfo.aod_name = this.departmentObj[this.bookInfo.aod_id];
      this.bookInfo.sc_name = this.scObj[this.bookInfo.sc_id];
      this.bookInfo.subject_name = this.subjectObj[this.bookInfo.subject_id];
      this.bookInfo.sd_name = this.departmentObj[this.bookInfo.sd_id];
      this.bookInfo.publish_time = this.bookInfo.publish_time.substring(0, 10);
      this.bookInfo.pl_name = this.plObj[this.bookInfo.pl_id];
      this.bookInfo.pp_name = this.ppObj[this.bookInfo.pp_id];
      this.bookInfo.bt_name = this.btObj[this.bookInfo.bt_id];
      this.bookInfo.rt_name = this.rtObj[this.bookInfo.rt_id];
      this.bookInfo.language_name = this.languageObj[this.bookInfo.language_id];
      this.bookInfoDialogVisible = true;
    },
  }
}
</script>

<style lang="less" scoped>
</style>
