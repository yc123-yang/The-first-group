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
        <template slot-scope="scope">
          <el-link type="primary" @click="showPaperInfoDialog(scope.row.paper_id)">{{scope.row.paper_name}}</el-link>
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

      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditPaperDialog(scope.row.paper_id)"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deletePaperById(scope.row.paper_id)"></el-button>
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
                <el-form-item label="论文类型:" prop="pt_id">
                  <el-select v-model="addForm.pt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ptList" :label="item.pt_name" :value="item.pt_id" :key="item.pt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="发表期刊:" prop="periodical_id">
                  <el-select v-model="addForm.periodical_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in periodicalList" :label="item.periodical_name" :value="item.periodical_id" :key="item.periodical_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 发表时间 -->
            <el-form-item label="发表时间:" prop="publish_time">
              <el-date-picker placeholder="请选择日期" v-model="addForm.publish_time" style="width: 100%" size="mini"
                value-format="yyyy-MM-dd 00:00:00">
              </el-date-picker>
            </el-form-item>
            <!-- 收录号 -->
            <el-form-item label="收录号:" prop="include_number">
              <el-input v-model="addForm.include_number" size="mini"></el-input>
            </el-form-item>
            <!-- 学科门类、一级学科 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="学科门类:" prop="sc_id">
                  <el-select v-model="addForm.sc_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in scList" :label="item.sc_name" :value="item.sc_id" :key="item.sc_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="一级学科:" prop="subject_id">
                  <el-select v-model="addForm.subject_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in subjectList" :label="item.subject_name" :value="item.subject_id" :key="item.subject_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 归属单位 -->
            <el-form-item label="归属单位:" prop="aod_id">
              <el-select v-model="addForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
              </el-select>
            </el-form-item>
            <!-- 项目来源 -->
            <el-form-item label="项目来源:" prop="sd_id">
              <el-select v-model="addForm.sd_id" placeholder="请选择" size="mini" style="width: 100%">
                <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
              </el-select>
            </el-form-item>
            <!-- 备注 -->

            <el-form-item label="备注:" prop="remark">
              <el-input size="mini" v-model="addForm.remark"></el-input>
            </el-form-item>
           
            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini" @click="addAuthorDialogVisible = true">添加作者</el-button>
            </el-form-item>
            <el-table :data="addForm.authorList" style="width: 100%" ref="authorTableRef" @selection-change="addAuthorSelectionChange"
              size="mini" border height="250px" :header-cell-style="{ background: '#f5f7fa' }" :default-sort="{prop: 'author_rate', order: 'descending'}">
              <!-- 序号列 -->
              <el-table-column type="index" label="#" align="center"></el-table-column>
              <el-table-column prop="author_info.user_name" label="作者姓名" align="center"></el-table-column>
              <el-table-column prop="author_type" label="成员类型" align="center"></el-table-column>
              <el-table-column prop="author_department" label="归属单位" align="center"></el-table-column>
              <el-table-column prop="author_rate" label="贡献率" align="center">
                <template slot-scope="scope">{{scope.row.author_rate + '%'}}</template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="120px">
                <template slot-scope="scope">
                  <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditAuthorDialog(scope)"></el-button>
                  <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteAuthor(scope.$index)"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
          <!-- 表单右侧 -->
          <el-col :span="8">
            <el-table  :data="periodicalList" style="width: 100%; margin: 0" height="670px" border @selection-change="periodicalSelectionChange" size="mini" ref="periodicalTableRef" :header-cell-style="{ background: '#f5f7fa' }">
              <!-- 多选列 -->
              <el-table-column type="selection" align="center"></el-table-column>
              <el-table-column label="收录情况" prop="periodical_name">
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


    <el-dialog title="添加作者" :visible.sync="addAuthorDialogVisible" width="40%" @closed="addAuthorDialogClosed">
      <el-form :model="addAuthorForm" :rules="addAuthorFormRules" ref="addAuthorFormRef" label-width="100px">
        <el-form-item label="作者姓名：" prop="author_info">
          <el-select v-model="addAuthorForm.author_info" remote placeholder="请输入作者姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="addAuthorAuthorInfoSelectorChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者类型：" prop="author_type">
          <el-input v-model="addAuthorForm.author_type" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="author_department">
          <el-input v-model="addAuthorForm.author_department" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="贡献率：" prop="author_rate">
          <el-input-number v-model="addAuthorForm.author_rate" controls-position="right" :min="1" :max="100" style="width: 100px">
          </el-input-number>
          <span>  %</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAuthorDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAuthor">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="编辑作者" :visible.sync="editAuthorDialogVisible" width="40%" @closed="editAuthorDialogClosed">
      <el-form :model="editAuthorForm" :rules="editAuthorFormRules" ref="editAuthorFormRef" label-width="100px">
        <el-form-item label="作者姓名：" prop="author_info">
          <el-select v-model="editAuthorForm.author_info" remote placeholder="请输入作者姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="editAuthorAuthorInfoSelectorChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者类型：" prop="author_type">
          <el-input v-model="editAuthorForm.author_type" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="author_department">
          <el-input v-model="editAuthorForm.author_department" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="贡献率：" prop="author_rate">
          <el-input-number v-model="editAuthorForm.author_rate" controls-position="right" :min="1" :max="100" style="width: 100px">
          </el-input-number>
          <span>  %</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editAuthorDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editAuthor">确 定</el-button>
      </span>
    </el-dialog>
  
    <!-- 编辑论文成果对话框 -->
    <el-dialog title="编辑论文成果" :visible.sync="editPaperDialogVisible" width="70%" @close="editPaperDialogClosed">
      <el-form :model="editForm" :rules="addFormRules" ref="editFormRef" label-width="90px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col :span="16">
            <!-- 论文题目 -->
            <el-form-item label="论文题目:" prop="paper_name">
              <el-input v-model="editForm.paper_name" size="mini"></el-input>
            </el-form-item>
            <!-- 论文类型、发表期刊 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="论文类型:" prop="pt_id">
                  <el-select v-model="editForm.pt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ptList" :label="item.pt_name" :value="item.pt_id" :key="item.pt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="发表期刊:" prop="periodical_id">
                  <el-select v-model="editForm.periodical_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in periodicalList" :label="item.periodical_name" :value="item.periodical_id" :key="item.periodical_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 发表时间 -->
            <el-form-item label="发表时间:" prop="publish_time">
              <el-date-picker placeholder="请选择日期" v-model="editForm.publish_time" style="width: 100%" size="mini"
                value-format="yyyy-MM-dd 00:00:00">
              </el-date-picker>
            </el-form-item>
            <!-- 收录号 -->
            <el-form-item label="收录号:" prop="include_number">
              <el-input v-model="editForm.include_number" size="mini"></el-input>
            </el-form-item>
            <!-- 学科门类、一级学科 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="学科门类:" prop="sc_id">
                  <el-select v-model="editForm.sc_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in scList" :label="item.sc_name" :value="item.sc_id" :key="item.sc_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="一级学科:" prop="subject_id">
                  <el-select v-model="editForm.subject_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in subjectList" :label="item.subject_name" :value="item.subject_id" :key="item.subject_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 归属单位 -->
            <el-form-item label="归属单位:" prop="aod_id">
              <el-select v-model="editForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
              </el-select>
            </el-form-item>
            <!-- 项目来源 -->
            <el-form-item label="项目来源:" prop="sd_id">
              <el-select v-model="editForm.sd_id" placeholder="请选择" size="mini" style="width: 100%">
                <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
              </el-select>
            </el-form-item>
            <!-- 备注 -->

            <el-form-item label="备注:" prop="remark">
              <el-input size="mini" v-model="editForm.remark"></el-input>
            </el-form-item>
           
            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini" @click="addMemberDialogVisble = true">添加作者</el-button>
            </el-form-item>
            <el-table :data="editForm.authorList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
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
              <el-table-column label="操作" align="center" width="120px">
                <template slot-scope="scope">
                  <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditMemberDialog(scope.row)"></el-button>
                  <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteMember(scope.row.user_id)"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
          <!-- 表单右侧 -->
          <el-col :span="8">
            <el-table  :data="periodicalList" style="width: 100%; margin: 0" height="670px" border @selection-change="editPeriodicalSelectionChange"
              size="mini" ref="editPeriodicalTableRef" :header-cell-style="{ background: '#f5f7fa' }">
              <!-- 多选列 -->
              <el-table-column type="selection" align="center"></el-table-column>
              <el-table-column label="收录情况" prop="periodical_name">
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editPaperDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editPaper">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑论文成果对话框中添加作者对话框 -->
    <el-dialog title="添加作者" :visible.sync="addMemberDialogVisble" width="40%" @closed="addMemberDialogClosed">
      <el-form :model="addMemberForm" :rules="addMemberFormRules" ref="addMemberFormRef" label-width="100px">
        <el-form-item label="作者姓名：" prop="author_info">
          <el-select v-model="addMemberForm.user_id" remote placeholder="请输入作者姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="addMemberSelectionChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="item.user_id" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者类型：" prop="author_type">
          <el-input v-model="addMemberForm.role_name" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="author_department">
          <el-input v-model="addMemberForm.department_name" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="贡献率：">
          <el-input-number v-model="addMemberForm.contribution" controls-position="right" :min="1" :max="100" style="width: 100px">
          </el-input-number>
          <span>  %</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addMemberDialogVisble = false">取 消</el-button>
        <el-button type="primary" @click="addMember">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑论文对话框中的编辑作者对话框 -->
    <el-dialog title="编辑作者" :visible.sync="editMemberDialogVisible" width="40%" @closed="editMemberDialogClosed">
      <el-form :model="editMemberForm" :rules="editMemberFormRules" ref="editMemberFormRef" label-width="100px">
        <el-form-item label="作者姓名：" prop="user_id">
          <el-select v-model="editMemberForm.user_id" remote placeholder="请输入作者姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="editMemberSelectionChanged" disabled>
            <el-option v-for="item in leaderList" :key="item.user_id" :value="item.user_id" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者类型：" prop="role_name">
          <el-input v-model="editMemberForm.role_name" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="department_name">
          <el-input v-model="editMemberForm.department_name" disabled placeholder="请输入作者姓名"></el-input>
        </el-form-item>
        <el-form-item label="贡献率：" prop="contribution">
          <el-input-number v-model="editMemberForm.contribution" controls-position="right" :min="1" :max="100" style="width: 100px">
          </el-input-number>
          <span>  %</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editMemberDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editMember">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="论文成果详情" :visible.sync="paperInfoDialogVisible" width="50%">
      <el-form class="dialog" label-width="90px" label-position="left">
      <el-row :gutter="20">
        <el-col :span="16">
          <el-row>
            <el-form-item label="论文题目：">{{ paperInfo.paper_name }}</el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="论文编号：">{{ paperInfo.paper_id }}</el-form-item>
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
import { parse, stringify } from 'qs';
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
        periodical_id: '',
        publish_time: '',
        pt_id: '',
        include_number: '',
        sc_id: '',
        subject_id: '',
        aod_id: '',
        sd_id: '',
        remark: '',
        authorList: [],
        periodicalSelectionList: []
      },
      // 录入、修改论文数据表单验证对象
      addFormRules: {
        paper_name: [{ required: true, message: "请输入论文题目", trigger: "blur" }],
        include_number: [{ required: true, message: "请输入收录号", trigger: "blur" }],
        pt_id: [{ required: true, message: "请选择论文类型", trigger: "change" }],
        periodical_id: [{ required: true, message: "请选择发表期刊", trigger: "change" }],
        publish_time: [{ required: true, message: "请选择发表日期", trigger: "change" }],
        sc_id: [{ required: true, message: "请选择学科门类", trigger: "change" }],
        subject_id: [{ required: true, message: "请选择一级学科", trigger: "change" }],
        sd_id: [{ required: true, message: "请选择来源单位", trigger: "change" }],
        aod_id: [{ required: true, message: "请选择成果归属单位", trigger: "change" }],
      },

      // 成员类型列表
      authorTypeList: ["成员类型1", "成员类型2"],
      // 添加作者对话框显示与隐藏控制变量
      addAuthorDialogVisible: false,
      // 添加作者信息表单
      addAuthorForm: {
        author_info: '',
        author_type: '',
        author_department: '',
        author_rate: ''
      },
      // 添加作者表单验证规则对象
      addAuthorFormRules: {
        author_info: [{ required: true, message: '请输入作者姓名', trigger: 'change' }],
        author_type: [{ required: true, message: '请选择作者类型', trigger: 'change' }]
      },
      addMemberFormRules: {
        user_id: [{ required: true, message: '请输入作者姓名', trigger: 'change' }],
      },
      // 是否正在加载
      loadingLeaderList: false,
      // 用户列表
      leaderList: [],
      // 编辑作者对话框显示与隐藏
      editAuthorDialogVisible: false,
      // 编辑作者信息表单
      editAuthorForm: {
        author_info: '',
        author_type: '',
        author_department: '',
        author_rate: ''
      },
      // 正在编辑的位置
      editAuthorIndex: '',
      // 编辑作者表单验证对象
      editAuthorFormRules: {},
      // 编辑论文对话框显示与隐藏变量
      editPaperDialogVisible: false,
      // 编辑论文表单对象
      editForm: {},
      // 编辑论文对话框中添加作者对话框的显示与隐藏控制变量
      addMemberDialogVisble: false,
      // 编辑论文对话框中添加作者表单对象
      addMemberForm: {
        user_id: '',
        contribution: 1
      },
      // 编辑论文对话框中的编辑作者对话框的显示与隐藏控制变量
      editMemberDialogVisible: false,
      // 编辑论文对话框中的编辑作者表单对象
      editMemberForm: {},
      // 编辑论文编辑作者表单验证规则对象
      editMemberFormRules: {
        user_id: [{ required: true, message: '请输入作者姓名', trigger: 'change' }]
      },
      paperInfoDialogVisible: false,
      paperInfo: {},
      ppList: []
    }
  },

  async created() {
    await this.getPaperData();
    await this.getPaperList();
    this.papersList = JSON.parse(JSON.stringify(this.papersList))
  },
  methods: {
    // 获取论文成果列表
    async getPaperData() {
       // 获取单位列表
      const { data: res1 } = await this.$http.post('/department/findAllDepartment')
      this.departmentList = res1.data
      // 构造单位 id:name 对象
      this.departmentList.forEach(item => this.departmentObj[item.department_id] = item.department_name)
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post('/category/findAllSubjectCategory')
      this.scList = res2.data
      // 构造学科门类 id:name 对象
      this.scList.forEach(item => this.scObj[item.sc_id] = item.sc_name)
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post('/subject/findAllSubject')
      this.subjectList = res3.data
      // 构造一级学科 id:name 对象
      this.subjectList.forEach(item => this.subjectObj[item.subject_id] = item.subject_name)
      // 获取期刊列表
      const { data: res4 } = await this.$http.post('/periodical/findAllPeriodical')
      this.periodicalList = res4.data
      // 构造 期刊 对象
      this.periodicalList.forEach(item => this.periodicalObj[item.periodical_id] = item.periodical_name)
      // 获取论文类型列表
      const { data: res5 } = await this.$http.post('/paperType/findAllPaperType')
      this.ptList = res5.data
      // 构造 论文类型对象
      this.ptList.forEach(item => this.ptObj[item.pt_id] = item.pt_name)
    },

    // 获取论文成果列表
    async getPaperList() {
      if(this.queryInfo.publish_time !== ''){
        this.queryInfo.publish_time_start = this.queryInfo.publish_time[0]
        this.queryInfo.publish_time_end = this.queryInfo.publish_time[1]
      }else this.queryInfo.publish_time_start = this.queryInfo.publish_time_end = ''
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post("/paper/selectPaperByCondition", this.$qs.stringify(this.queryInfo));
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
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return this.$message.error("请正确填写论文成果信息");
        var getObj = JSON.parse(JSON.stringify(this.addForm))
        var user_id = []
        var contribution = []
        var periodicalIds = []
        this.addForm.authorList.forEach(item => {
          user_id.push(item.author_info.user_id)
          contribution.push(item.author_rate)
        })
        this.addForm.periodicalSelectionList.forEach(item => periodicalIds.push(item.periodical_id))
        getObj.leader_id = user_id[0]
        getObj.examine_status = '未通过'
        const { data: res } = await this.$http.get(`/paperExamine/addPaperExamine?${this.$qs.stringify(getObj)}
          &user_id=${user_id}
          &contribution=${contribution}
          &periodicalIds=${periodicalIds}
        `)
        if( res.status !== '200' ) return this.$message.error('提交论文申请失败')
        this.$message.success("提交论文申请成功");
        this.addPaperDialogVisible = false
        this.getPaperList()
      })
    },
    // 根据用户输入，获取用户列表
    async getLeaderList(query) {
      console.log(query)
      if(query === null || query === '') return
      this.loadingLeaderList = true
      const { data: res } = await this.$http.post('/user/findNameId', this.$qs.stringify({ user_name: query }))
      this.leaderList = res.data
      this.loadingLeaderList = false
    },
    // 添加作者作者选项卡变化事件
    async addAuthorAuthorInfoSelectorChanged() {
      this.addAuthorForm.author_info = JSON.parse(this.addAuthorForm.author_info)
      this.addAuthorForm.author_department = this.departmentObj[this.addAuthorForm.author_info.department_id]
      const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.addAuthorForm.author_info.role_id }))
      if( res.status !== '200' ) return this.$message.error('获取用户类型失败')
      this.addAuthorForm.author_type = res.data
      console.log(this.addAuthorForm)
    },
    // 点击确定，添加作者
    addAuthor() {
      var authorObj = JSON.parse(JSON.stringify(this.addAuthorForm))
      this.addForm.authorList.push(authorObj)
      console.log(this.addForm.authorList)
      this.$message.success('添加作者成功')
      this.addAuthorDialogVisible = false
    },
    // 关闭添加作者对话框，清空表单
    addAuthorDialogClosed() {
      this.$refs.addAuthorFormRef.resetFields()
    },
    // 点击删除按钮，删除作者
    async deleteAuthor(index) {
      const res = await this.$confirm('此操作将永久删除该作者，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res === 'cancel' ) return this.$message.info('取消了本次操作')
      this.addForm.authorList.splice(index, 1)
      this.$message.success('成功删除了作者')
      console.log(this.addForm.authorList)
    },
    // 关闭编辑作者对话框，清空表单
    editAuthorDialogClosed() {
      this.$refs.editAuthorFormRef.resetFields()
    },
    // 编辑作者作者选项卡变化事件
    async editAuthorAuthorInfoSelectorChanged() {
      this.editAuthorForm.author_info = JSON.parse(this.editAuthorForm.author_info)
      this.editAuthorForm.author_department = this.departmentObj[this.editAuthorForm.author_info.department_id]
      const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.editAuthorForm.author_info.role_id }))
      if( res.status !== '200' ) return this.$message.error('获取用户类型失败')
      this.editAuthorForm.author_type = res.data
      console.log(this.editAuthorForm)
    },
    // 点击编辑按钮，显示编辑作者对话框
    showEditAuthorDialog(scope){
      this.editAuthorForm = JSON.parse(JSON.stringify(scope.row))
      this.editAuthorIndex = JSON.parse(JSON.stringify(scope.$index))
      this.editAuthorDialogVisible = true
    },
    // 点击确定，上传编辑作者信息
    editAuthor() {
      this.addForm.authorList[this.editAuthorIndex] = JSON.parse(JSON.stringify(this.editAuthorForm))
      this.addForm.authorList = JSON.parse(JSON.stringify(this.addForm.authorList))
      this.$message.success('成功编辑了作者信息')
      this.editAuthorDialogVisible = false
    },
    // 点击按钮，删除论文
    async deletePaperById(paperId) {
      const res = await this.$confirm('此操作将永久删除该著作，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/paper/delPaper', this.$qs.stringify({ paper_id: paperId }))
      if(res2.status !== '200') return this.$message.error('删除著作失败')
      this.$message.success('删除著作成功')
      this.getPaperList()
    },
    // 点击编辑论文成果按钮，显示编辑论文成果对话框
    async showEditPaperDialog(paper_id){
      const { data: res } = await this.$http.post('/paper/findPaperById', this.$qs.stringify({ paper_id: paper_id }))
      if(res.status !== '200') return this.$message.error('获取论文信息失败')
      this.editForm = res.data
      await this.getMemberList()
      this.editPaperDialogVisible = true
      await this.getPeriodicalList()
    },
    // 获取编辑论文对话框中的成员列表
    async getMemberList() {
      const { data: res } = await this.$http.post('/team/selectPaperTeam', this.$qs.stringify({ paper_id: this.editForm.paper_id }))
      if( res.status !== '200' ) return this.$message.error('获取论文团队成员信息失败')
      this.$set(this.editForm, "authorList", res.data)
      // this.editForm.authorList = res.data
    },
    // 获取收录期刊列表
    async getPeriodicalList() {
      console.log(this.editForm.paper_id)
      const { data: res } = await this.$http.post('/periodicalPaper/findPeriodicalByPaperId', this.$qs.stringify({ paper_id: this.editForm.paper_id }))
      if( res.status !== '200' ) return this.$message.error('获取收录期刊列表失败')
      this.$refs.editPeriodicalTableRef.clearSelection()
      this.periodicalList.forEach(item => {
        if(res.data.indexOf(item.periodical_id) !== -1)
          this.$refs.editPeriodicalTableRef.toggleRowSelection(item)
      })
    },
    // 编辑对话框中期刊列表选择状态改变
    editPeriodicalSelectionChange(val) { this.editForm.editPeriodicalSelectionList = val },
    // 编辑论文对话框关闭事件
    editPaperDialogClosed() {
      this.$refs.editPeriodicalTableRef.clearSelection()
      this.$refs.editFormRef.resetFields()
    },
    // 编辑论文对话框中添加成员对话框的关闭事件
    addMemberDialogClosed() {
      this.$refs.addMemberFormRef.resetFields()
    },
    // 添加论文成员选项发生变化
    addMemberSelectionChanged(user_id) {
      this.leaderList.forEach(item => {
        if(item.user_id === user_id) {
          this.addMemberForm.role_name = item.role_name
          this.addMemberForm.department_name = this.departmentObj[item.department_id]
          return
        }
      })
    },
    // 点击确定，添加论文成员
    async addMember() {
      var postObj = JSON.parse(JSON.stringify(this.addMemberForm))
      postObj.paper_id = this.editForm.paper_id
      const { data: res } = await this.$http.post('/team/addPaperTeamUser', this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('添加团队成员失败')
      this.$message.success('添加团队成员成功')
      await this.getMemberList()
      this.addMemberDialogVisble = false
    },
    // 点击删除按钮，删除成员
    async deleteMember(user_id) {
      const res = await this.$confirm('此操作将永久删除该作者，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res === 'cancel' ) return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/team/delPaperTeamUser', this.$qs.stringify({ paper_id: this.editForm.paper_id, user_id: user_id }))
      console.log(res2)
      if( res2.status !== '200' ) return this.$message.error('删除团队成员失败')
      this.$message.success('删除团队成员成功')
      await this.getMemberList()
    },
    // 编辑论文对话框中，点击编辑按钮，显示编辑作者对话框 
    async showEditMemberDialog(val) {
      this.editMemberForm = JSON.parse(JSON.stringify(val))
      await this.getLeaderList(this.editMemberForm.name)
      await this.editMemberSelectionChanged(this.editMemberForm.user_id)
      this.editMemberDialogVisible = true
    },
    // 关闭编辑论文对话框中编辑作者对话框关闭事件，清空编辑作者表单
    editMemberDialogClosed() {
      this.$refs.editMemberFormRef.resetFields()
    },
    // 编辑论文编辑作者成员选项发生变化事件
    editMemberSelectionChanged(user_id) {
      this.leaderList.forEach(item => {
        if(item.user_id === user_id) {
          this.editMemberForm.user_name = item.user_name
          this.editMemberForm.department_name = this.departmentObj[item.department_id]
          return
        }
      })
    },
    // 点击确定按钮，上传编辑作者表单信息
    async editMember() {
      var postObj = JSON.parse(JSON.stringify( this.editMemberForm ))
      postObj.paper_id = this.editForm.paper_id
      const { data: res } = await this.$http.post('/team/updatePaperTeamUser', this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('编辑作者信息失败')
      this.$message.success('编辑作者信息成功')
      await this.getMemberList()
      this.editMemberDialogVisible = false  
    },
    // 点击确定按钮，上传编辑论文数据
    async editPaper() {
      var plist = []
      this.editForm.editPeriodicalSelectionList.forEach(item => plist.push(item.periodical_id))
      console.log(plist)
      const { data: res } = await this.$http.get(`/periodicalPaper/updatePeriodicalPaper?paper_id=${this.editForm.paper_id}&periodical_ids=${plist}`)
      if( res.status !== '200' ) return this.$message.error('更新收录期刊失败')
      const { data: res2 } = await this.$http.post('/paper/updatePaper', this.$qs.stringify(this.editForm))
      if( res2.status !== '200' ) return this.$message.error('更新论文信息失败')
      this.$message.success('更新论文信息成功')
      this.getPaperList()
      this.editPaperDialogVisible = false
    },
    async showPaperInfoDialog(paperId) {
      console.log(paperId)
      const { data: res } = await this.$http.post("/paper/findPaperById", this.$qs.stringify({ paper_id: paperId }));
      if (res.status !== "200") return this.$message.error("获取待审核论文成果信息失败");
      this.paperInfo = res.data;
      const { data: res2 } = await this.$http.post('/team/selectPaperTeam', stringify({ paper_id: paperId }))
      if( res2.status !== '200' ) return this.$message.error('获取待审核论文成果团队失败')
      this.memberList = res2.data
      const { data: res3 } = await this.$http.post('/periodicalPaper/findPeriodicalByPaperId', stringify({ paper_id: paperId }))
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

<style lang="less" scoped></style>
