<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理字典数据</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="dataList" style="width: 100%" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="cname" label="字典名称"></el-table-column>
        <el-table-column label="简单说明"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" icon="el-icon-edit" @click="showEditDataDialog(scope.row)">
              编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 字典数据项详情对话框 -->
    <el-dialog :title="dictInfo.cname + ' —— 字典数据项'" :visible.sync="editDictDialogVisible" width="50%" style="min-width: 1270px;">
      <el-button type="primary" icon="el-icon-plus" @click="showAddDialog">添加</el-button>
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="dictDataList" style="width: 100%;margin-top: 15px" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column :prop="dictInfo.id" label="编号"></el-table-column>
        <el-table-column :prop="dictInfo.name" label="名称"></el-table-column>
        <el-table-column label="操作">
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
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 字典列表
      dataList: [{
        cname: '成果类型', id: 'at_id', name: 'at_name',
        path: {
          add: '/achievementType/addAchievementType',
          delete: '/achievementType/delAchievementType',
          update: '/achievementType/updateAchievementType',
          get: '/achievementType/findAllAchievementType'
        }
      }, {
        cname: '获奖等级', id: 'al_id', name: 'al_name',
        path: {
          add: '/awardLevel/addAwardLevel',
          delete: '/awardLevel/delAwardLevel',
          update: '/awardLevel/updateAwardLevel',
          get: '/awardLevel/findAllAwardLevel'
        }
      }, {
        cname: '获奖级别', id: 'ar_id', name: 'ar_name',
        path: {
          add: '/awardRank/addAwardRank',
          delete: '/awardRank/delAwardRank',
          update: '/awardRank/updateAwardRank',
          get: '/awardRank/findAllAwardRank'
        }
      }, {
        cname: '著作类型', id: 'bt_id', name: 'bt_name',
        path: {
          add: '/bookType/addBookType',
          delete: '/bookType/delBookType',
          update: '/bookType/updateBookType',
          get: '/bookType/findAllBookType'
        }
      }, {
        cname: '结题形式', id: 'ct_id', name: 'ct_name',
        path: {
          add: '/conclusionType/addConclusionType',
          delete: '/conclusionType/delConclusionType',
          update: '/conclusionType/updateConclusionType',
          get: '/conclusionType/findAllConclusionType'
        }
      }, {
        cname: '归属单位', id: 'department_id', name: 'department_name',
        path: {
          add: '/department/addDepartment',
          delete: '/department/delDepartment',
          update: '/department/updateDepartment',
          get: '/department/findAllDepartment'
        }
      }, {
        cname: '项目级别', id: 'level_id', name: 'level_name',
        path: {
          add: '/level/addLevel',
          delete: '/level/delLevel',
          update: '/level/updateLevel',
          get: '/level/findAllLevel'
        }
      }, {
        cname: '翻译语种', id: 'language_id', name: 'language_name',
        path: {
          add: '/language/addLanguage',
          delete: '/language/delLanguage',
          update: '/language/updateLanguage',
          get: '/language/findAllLanguage'
        }
      }, {
        cname: '项目性质', id: 'nature_id', name: 'nature_name',
        path: {
          add: '/nature/addNature',
          delete: '/nature/delNature',
          update: '/nature/updateNature',
          get: '/nature/findAllNature'
        }
      }, {
        cname: '论文类型', id: 'pt_id', name: 'pt_name',
        path: {
          add: '/paperType/addPaperType',
          delete: '/paperType/delPaperType',
          update: '/paperType/updatePaperType',
          get: '/paperType/findAllPaperType'
        }
      }, {
        cname: '专利范围', id: 'pr_id', name: 'pr_name',
        path: {
          add: '/patentRange/addPatentRange',
          delete: '/patentRange/delPatentRange',
          update: '/patentRange/updatePatentRange',
          get: '/patentRange/findAllPatentRange'
        }
      }, {
        cname: '专利状态', id: 'ps_id', name: 'ps_name',
        path: {
          add: '/patentStatus/addPatentStatus',
          delete: '/patentStatus/delPatentStatus',
          update: '/patentStatus/updatePatentStatus',
          get: '/patentStatus/findAllPatentStatus'
        }
      }, {
        cname: '专利类型', id: 'pt_id', name: 'pt_name',
        path: {
          add: '/patentType/addPatentType',
          delete: '/patentType/delPatentType',
          update: '/patentType/updatePatentType',
          get: '/patentType/findAllPatentType'
        }
      }, {
        cname: '期刊', id: 'periodical_id', name: 'periodical_name',
        path: {
          add: '/periodical/addPeriodical',
          delete: '/periodical/delPeriodical',
          update: '/periodical/updatePeriodical',
          get: '/periodical/findAllPeriodical'
        }
      }, {
        cname: '出版社等级', id: 'pl_id', name: 'pl_name',
        path: {
          add: '/pressLevel/addPressLevel',
          delete: '/pressLevel/delPressLevel',
          get: '/pressLevel/findAllPressLevel',
          update: '/pressLevel/updatePressLevel'
        }
      }, {
        cname: '出版地', id: 'pp_id', name: 'pp_name',
        path: {
          add: '/publicationPlace/addPublicationPlace',
          delete: '/publicationPlace/delPublicationPlace',
          get: '/publicationPlace/findAllPublicationPlace',
          update: '/publicationPlace/updatePublicationPlace'
        }
      }, {
        cname: '研究类型', id: 'rt_id', name: 'rt_name',
        path: {
          add: '/researchType/addResearchType',
          delete: '/researchType/delResearchType',
          get: '/researchType/findAllResearchType',
          update: '/researchType/updateResearchType'
        }
      }, {
        cname: '项目状态', id: 'status_id', name: 'status_name',
        path: {
          add: '/status/addStatus',
          delete: '/status/delStatus',
          get: '/status/findAllStatus',
          update: '/status/updateStatus'
        }
      }, {
        cname: '学科门类', id: 'sc_id', name: 'sc_name',
        path: {
          add: '/category/addSubjectCategory',
          delete: '/category/delSubjectCategory',
          get: '/category/findAllSubjectCategory',
          update: '/category/updateSubjectCategory'
        }
      }, {
        cname: '一级学科', id: 'subject_id', name: 'subject_name',
        path: {
          add: '/subject/addSubject',
          delete: '/subject/delSubject',
          get: '/subject/findAllSubject',
          update: '/subject/updateSubject'
        }
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
      addForm: { name: '' },
      // 添加数据验证表单
      addFormRules: { name: [{ required: true, message: '请输入名称！', trigger: 'blur' }] },
      // 控制编辑对话框的显示与隐藏变量
      editDialogVisible: false,
      // 编辑数据项表单
      editForm: { name: '', id: ''},
      // 编辑数据项表单验证规则对象
      editFormRules: { name: [{ required: true, message: '请输入名称！', trigger: 'blur' }] }
    }
  },
  created() {

  },
  methods: {
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
      this.editForm.name = JSON.parse(JSON.stringify(editObj))[this.dictInfo.name]
      this.editForm.id = JSON.parse(JSON.stringify(editObj))[this.dictInfo.id]
      this.editDialogVisible = true
    },
    // 点击确定，上传编辑数据
    async editData() {
      var postObj = {}
      postObj[this.dictInfo.id] = this.editForm.id
      postObj[this.dictInfo.name] = this.editForm.name
      const { data: res } = await this.$http.post(this.dictInfo.path.update, this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('编辑数据失败')
      this.$message.success('编辑数据成功')
      this.getDictDataList()
      this.editDialogVisible = false
    }
  }
}
</script>

<style lang="less" scoped>

</style>