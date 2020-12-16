<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>科研考核</el-breadcrumb-item>
      <el-breadcrumb-item>考核方案</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="dataList" style="width: 100%" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="cname" label="字典名称"></el-table-column>
        <el-table-column label="简单说明" prop="describe"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" icon="el-icon-edit" @click="preShowDialog(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 字典数据项详情对话框 -->
    <el-dialog :title="dictInfo.cname" :visible.sync="editDictDialogVisible" width="50%" style="min-width: 1270px;">
      <el-button type="primary" icon="el-icon-plus" @click="showAddDialog">添加</el-button>
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="dictDataList" style="width: 100%;margin-top: 15px" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column :prop="dictInfo.id" label="编号"></el-table-column>
        <el-table-column :prop="dictInfo.name" label="名称"></el-table-column>
        <el-table-column :prop="dictInfo.score" label="考核分数"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button icon="el-icon-edit" size="mini" type="primary" @click="showEditDialog(scope.row)">编辑</el-button>
            <el-button icon="el-icon-delete" size="mini" type="danger" @click="deleteDataById(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDictDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDictDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 添加字典数据项对话框 -->
    <el-dialog :title="dictInfo.cname + ' —— 添加'" :visible.sync="addDialogVisible" width="40%" @closed="addDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="名称：" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="考核分数：" prop="score">
          <el-input v-model="addForm.score" onkeyup="value=value.replace(/[^\d]/g,'')"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addData">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑字典数据对话框 -->
    <el-dialog :title="dictInfo.cname + ' —— 编辑'" :visible.sync="editDialogVisible" width="40%">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <el-form-item label="名称：" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="考核分数：" prop="score">
          <el-input v-model="editForm.score" onkeyup="value=value.replace(/[^\d]/g,'')"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editData">确 定</el-button>
      </span>
    </el-dialog>


    <!-- 项目经费对话框 -->
    <el-dialog title="项目经费" :visible.sync="outlayDialogVisible" width="50%" style="min-width: 1270px;">
      <el-button type="primary" icon="el-icon-plus" @click="addOutlayDialogVisible = true">添加</el-button>
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="outlayList" style="width: 100%;margin-top: 15px" border>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="开始" prop="outlay_start"></el-table-column>
        <el-table-column label="结束" prop="outlay_end"></el-table-column>
        <el-table-column label="分数" prop="outlay_score"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditOutlayDialog(scope.row.outlay_id)">编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteOutlay(scope.row.outlay_id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="outlayDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="outlayDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 添加经费区间对话框 -->
    <el-dialog title="添加" :visible.sync="addOutlayDialogVisible" width="40%" @closed="addOutlayDialogClosed">
      <el-form :model="addOutlayForm" :rules="addOutlayFormRules" ref="addOutlayFormRef" label-width="100px">
        <el-form-item label="开始额度：" prop="outlay_start">
          <el-input v-model="addOutlayForm.outlay_start" onkeyup="value=value.replace(/[^\d]/g,'')">
          </el-input>
        </el-form-item>
        <el-form-item label="结束额度：" prop="outlay_end">
          <el-input v-model="addOutlayForm.outlay_end" onkeyup="value=value.replace(/[^\d]/g,'')">
          </el-input>
        </el-form-item>
        <el-form-item label="考核分数：" prop="outlay_score">
          <el-input v-model="addOutlayForm.outlay_score" onkeyup="value=value.replace(/[^\d]/g,'')">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addOutlayDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOutlay">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑经费区间对话框 -->
    <el-dialog title="添加" :visible.sync="editOutlayDialogVisible" width="40%" @closed="editOutlayDialogClosed">
      <el-form :model="editOutlayForm" :rules="addOutlayFormRules" ref="editOutlayFormRef" label-width="100px">
        <el-form-item label="开始额度：" prop="outlay_start">
          <el-input v-model="editOutlayForm.outlay_start" onkeyup="value=value.replace(/[^\d]/g,'')" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="结束额度：" prop="outlay_end">
          <el-input v-model="editOutlayForm.outlay_end" onkeyup="value=value.replace(/[^\d]/g,'')" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="考核分数：" prop="outlay_score">
          <el-input v-model="editOutlayForm.outlay_score" onkeyup="value=value.replace(/[^\d]/g,'')">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editOutlayDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editOutlay">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { stringify } from 'qs'
export default {
  data() {
    var validOutlayEnd = (rule, value, callback) => {
      if(parseInt(this.addOutlayForm.outlay_start)  > parseInt(value)) {
        callback(new Error('请输入正确的结束额度'))
      } else {
        callback()
      }
    }
    return {
      dataList: [{
        cname: '项目级别', id: 'level_id', name: 'level_name', score: 'pl_score',
        describe: '项目级别',
        path: {
          add: '/level/addLevel',
          delete: '/level/delLevel',
          update: '/level/updateLevel',
          get: '/level/findAllLevel'
        }
      }, {
        cname: '获奖等级', id: 'al_id', name: 'al_name', score: 'al_score',
        describe: '获奖等级',
        path: {
          add: '/awardLevel/addAwardLevel',
          delete: '/awardLevel/delAwardLevel',
          update: '/awardLevel/updateAwardLevel',
          get: '/awardLevel/findAllAwardLevel'
        }
      }, {
        cname: '获奖级别', id: 'ar_id', name: 'ar_name', score: 'ar_score',
        describe: '获奖级别',
        path: {
          add: '/awardRank/addAwardRank',
          delete: '/awardRank/delAwardRank',
          update: '/awardRank/updateAwardRank',
          get: '/awardRank/findAllAwardRank'
        }
      }, {
        cname: '出版社等级', id: 'pl_id', name: 'pl_name', score: 'bpl_score',
        describe: '出版社等级',
        path: {
          add: '/pressLevel/addPressLevel',
          delete: '/pressLevel/delPressLevel',
          get: '/pressLevel/findAllPressLevel',
          update: '/pressLevel/updatePressLevel'
        }
      }, {
        cname: '期刊', id: 'periodical_id', name: 'periodical_name', score: 'pp_score',
        describe: '期刊',
        path: {
          add: '/periodical/addPeriodical',
          delete: '/periodical/delPeriodical',
          update: '/periodical/updatePeriodical',
          get: '/periodical/findAllPeriodical'
        }
      }, {
        cname: '项目经费',
        describe: '项目经费'
      }],
      // 控制编辑对话框显示与隐藏变量
      editDictDialogVisible: false,
      // 字典数据详情列表
      dictDataList: [],
      // 字典属性
      dictInfo: {},
      // 控制添加对话框显示与隐藏变量
      addDialogVisible: false,
      // 添加数据项表单
      addForm: { name: '', score: '' },
      // 添加数据验证表单
      addFormRules: {
        name: [{ required: true, message: '请输入名称！', trigger: 'blur' }],
        score: [{ required: true, message: '请输入考核分数！', trigger: 'blur' }]
      },
      // 控制编辑对话框的显示与隐藏变量
      editDialogVisible: false,
      // 编辑数据项表单
      editForm: { name:  '', id: '', score: ''},
      // 编辑数据项表单验证规则对象
      editFormRules: {
        name: [{ required: true, message: '请输入名称！', trigger: 'blur' }],
        score: [{ required: true, message: '请输入考核分数！', trigger: 'blur' }]
      },
      // 项目经费对话框显示控制变量
      outlayDialogVisible: false,
      // 经费区间列表
      outlayList: [],
      // 添加经费区间对话框显示控制变量
      addOutlayDialogVisible: false,
      // 添加经费区间表单
      addOutlayForm: {
        outlay_start: '',
        outlay_end: '',
        outlay_score: ''
      },
      // 添加经费区间表单验证对象
      addOutlayFormRules: {
        outlay_start: [{ required: true, message: '请输入开始额度', trigger: 'blur' }],
        outlay_end: [
          { required: true, message: '请输入结束额度', trigger: 'blur' },
          { validator: validOutlayEnd, trigger: 'blur' }
        ],
        outlay_score: [{ required: true, message: '请输入考核分数！', trigger: 'blur' }]
      },
      // 编辑经费区间对话框显示控制变量
      editOutlayDialogVisible: false,
      // 编辑经费区间表单
      editOutlayForm: {},

    }
  },
  created() {

  },
  methods: {
    // 打开不同类型的对话框
    preShowDialog(row) {
      if(row.cname === '项目经费') {
        this.showOutlayDialog()
      } else {
        this.showEditDataDialog(row)
      }
    },
    // 获取字典数据项列表
    async getDictDataList() {
      const { data: res } = await this.$http.post(this.dictInfo.path.get)
      if( res.status !== '200' ) this.$message.error('获取' + dict.cname + '详细列表失败')
      this.dictDataList = res.data
    },
    // 点击按钮，显示字典详细数据对话框
    async showEditDataDialog(dict) {
      this.dictInfo = dict
      this.getDictDataList()
      this.editDictDialogVisible = true
    },
    // 点击添加按钮，显示添加对话框
    showAddDialog() { this.addDialogVisible = true },
    // 关闭添加对话框，清空表单
    addDialogClosed() { this.$refs.addFormRef.resetFields() },
    // 点击确定，添加数据项
    addData() {
      this.$refs.addFormRef.validate(async valid => {
        if(!valid) return
        var postObj = {}
        postObj[this.dictInfo.name] = this.addForm.name
        postObj[this.dictInfo.score] = this.addForm.score
        const { data: res } = await this.$http.post(this.dictInfo.path.add, this.$qs.stringify(postObj))
        if(res.status !== '200') return this.$message.error('添加数据失败')
        this.$message.success('添加数据成果')
        this.getDictDataList()
        this.addDialogVisible = false
      })
    },
    // 点击删除，删除数据项
    async deleteDataById(obj) {
      const res = await this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res === 'cancel' ) return this.$message.info('取消了本次操作')
      var postObj = {}
      postObj[ this.dictInfo.id ] = obj[ this.dictInfo.id ]
      const { data: res1 } = await this.$http.post(this.dictInfo.path.delete, this.$qs.stringify(postObj))
      if( res1.status !== '200' ) return this.$message.error('删除数据失败')
      this.$message.success('删除数据成功')
      this.getDictDataList()
    },
    // 点击编辑，显示编辑数据项对话框
    showEditDialog(editObj) {
      this.editForm.name = JSON.parse(JSON.stringify(editObj[this.dictInfo.name]))
      this.editForm.id = JSON.parse(JSON.stringify(editObj[this.dictInfo.id]))
      this.editForm.score = JSON.parse(JSON.stringify(editObj[this.dictInfo.score]))
      this.editDialogVisible = true
    },
    // 点击确定，上传编辑数据
    async editData() {
      var postObj = {}
      postObj[this.dictInfo.id] = this.editForm.id
      postObj[this.dictInfo.name] = this.editForm.name
      postObj[this.dictInfo.score] = this.editForm.score
      const { data: res } = await this.$http.post(this.dictInfo.path.update, this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('编辑数据失败')
      this.$message.success('编辑数据成功')
      this.getDictDataList()
      this.editDialogVisible = false
    },
    // 获取经费考核区间列表
    async getOutlayList() {
      const { data: res } = await this.$http.post('/outlay/selectAllOutlay')
      if( res.status !== '200' ) return this.$message.error('获取项目经费分数失败')
      this.outlayList = res.data
    },
    // 显示项目经费对话框
    async showOutlayDialog() {
      this.getOutlayList()
      this.outlayDialogVisible = true
    },
    // 添加经费区间对话框关闭清空表单
    addOutlayDialogClosed() {
      this.$refs.addOutlayFormRef.resetFields()
    },
    // 添加经费考核区间
    async addOutlay() {
      const { data: res } = await this.$http.post('/outlay/addOutlay', stringify(this.addOutlayForm))
      if( res.status !== '200' ) return this.$message.error(res.msg)
      this.$message.success('添加经费考核区间成功')
      this.getOutlayList()
      this.addOutlayDialogVisible = false
    },
    // 删除经费考核区间
    async deleteOutlay(outlay_id) {
      const res = await this.$confirm('此操作将永久删除该考核分区间，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res === 'cancel' ) return this.$message.info('取消了本次操作')
      const { data: res1 } = await this.$http.post('/outlay/delOutlayById', stringify({ outlay_id: outlay_id }))
      if( res1.status !== '200' ) return this.$message.error('删除区间失败')
      this.$message.success('删除区间成功')
      this.getOutlayList()
    },
    // 编辑经费区间对话框关闭，清空表单
    editOutlayDialogClosed() {
      this.$refs.editOutlayFormRef.resetFields()
    },
    // 点击编辑按钮，显示编辑经费区间对话框
    async showEditOutlayDialog(outlay_id) {
      const { data: res } = await this.$http.post('/outlay/selectOutlayById', stringify({ outlay_id: outlay_id }))
      if( res.status !== '200' ) return this.$message.error('获取经费区间信息失败')
      this.editOutlayForm = res.data
      this.editOutlayDialogVisible = true
    },
    // 上传编辑经费区间表单
    async editOutlay() {
      const { data: res } = await this.$http.post('/outlay/updateOutlay', stringify(this.editOutlayForm))
      if( res.status !== '200' ) return this.$message.error('更新经费区间信息失败')
      this.$message.success('更新经费区间信息成功')
      this.getOutlayList()
      this.editOutlayDialogVisible = false
    }
  }
}
</script>

<style lang="less" scoped>

</style>