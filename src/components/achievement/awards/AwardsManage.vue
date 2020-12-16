<template>
  <div style="margin-top: 25px">
   <el-button type="primary" size="medium" @click="addAwardsDialogVisible = true">录入获奖成果</el-button>
    <el-button type="danger" size="medium" :disabled="selection.length === 0">删除获奖成果</el-button>
    <el-button type="warning" size="medium" @click="print">导出信息</el-button>


    <el-table :data="awardsList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange" :header-cell-style="{ background: '#f5f7fa' }">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>

      <el-table-column prop="achievement_name" label="成果名字" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">成果名字</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.achievement_name" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
        <template slot-scope="scope">
          <el-link type="primary" @click="showAwardInfoDialog(scope.row.award_id)">{{scope.row.award_id}}</el-link>
        </template>
      </el-table-column>

      <el-table-column prop="authorName" label="获奖成果作者" width="150px" align="center">
        <template slot="header" slot-scope="scope"
          >{{ scope.haha }}
          <div style="line-height: 14px">获奖成果作者</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.authorName" placeholder="请输入" @change="QueryAwardList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="award_name" label="获奖名字" width="300px" align="center">
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

      <el-table-column prop="issuing_authority" label="发证机关" width="300px" align="center">
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
      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditAwardDialog(scope.row.award_id)"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteAwardById(scope.row.award_id)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    <!-- 录入获奖成果对话框 -->
    <el-dialog title="录入获奖成果" :visible.sync="addAwardsDialogVisible" width="60%" @close="addAwardsDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 成果名字 -->
            <el-form-item label="成果名字:" prop="achievement_name">
              <el-input v-model="addForm.achievement_name" size="mini"></el-input>
            </el-form-item>
            <!-- 获奖名称、获奖时间 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="获奖名称:" prop="award_name">
                  <el-input v-model="addForm.award_name" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="获奖时间:" prop="award_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="addForm.award_time" style="width: 100%" size="mini"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 发证机关，奖励批准号 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="发证机关:" prop="issuing_authority">
                  <el-input v-model="addForm.issuing_authority" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="奖励批准号:" prop="approval_number">
                  <el-input v-model="addForm.approval_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 成果归属单位、来源单位 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod_id">
                  <el-select v-model="addForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="项目来源:" prop="sd_id">
                  <el-select v-model="addForm.sd_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 获奖级别，获奖等级 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="获奖级别:" prop="ar_id">
                  <el-select v-model="addForm.ar_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in arList" :label="item.ar_name" :value="item.ar_id" :key="item.ar_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="获奖等级:" prop="al_id">
                  <el-select v-model="addForm.al_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in alList" :label="item.al_name" :value="item.al_id" :key="item.al_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 学科门类，一级学科 -->
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
            <!-- 单位排名,成果形式 -->
            <el-row :gutter="20">

              <el-col :span="12">
                <el-form-item label="成果形式:" prop="at_id">
                  <el-select v-model="addForm.at_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in atList" :label="item.at_name" :value="item.at_id" :key="item.at_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="单位排名:" prop="dr_id">
                  <el-input v-model="addForm.dr_id" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini" @click="addAuthorDialogVisible = true">添加作者</el-button>
            </el-form-item>

            <el-table :data="addForm.authorList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
              :header-cell-style="{ background: '#f5f7fa' }"  :default-sort="{prop: 'author_rate', order: 'descending'}">
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
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAwardsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAwards">确 定</el-button>
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

    <!-- 编辑成果获奖对话框 -->
    <el-dialog title="编辑获奖成果" :visible.sync="editAwardsDialogVisible" width="60%" @close="editAwardsDialogClosed">
      <el-form :model="editForm" :rules="addFormRules" ref="editFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 成果名字 -->
            <el-form-item label="成果名字:" prop="achievement_name">
              <el-input v-model="editForm.achievement_name" size="mini"></el-input>
            </el-form-item>
            <!-- 获奖名称、获奖时间 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="获奖名称:" prop="award_name">
                  <el-input v-model="editForm.award_name" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="获奖时间:" prop="award_time">
                  <el-date-picker type="date" placeholder="请选择日期" v-model="editForm.award_time" style="width: 100%" size="mini"
                    value-format="yyyy-MM-dd 00:00:00">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 发证机关，奖励批准号 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="发证机关:" prop="issuing_authority">
                  <el-input v-model="editForm.issuing_authority" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="奖励批准号:" prop="approval_number">
                  <el-input v-model="editForm.approval_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 成果归属单位、来源单位 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod_id">
                  <el-select v-model="editForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="项目来源:" prop="sd_id">
                  <el-select v-model="editForm.sd_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 获奖级别，获奖等级 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="获奖级别:" prop="ar_id">
                  <el-select v-model="editForm.ar_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in arList" :label="item.ar_name" :value="item.ar_id" :key="item.ar_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="获奖等级:" prop="al_id">
                  <el-select v-model="editForm.al_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in alList" :label="item.al_name" :value="item.al_id" :key="item.al_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 学科门类，一级学科 -->
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
            <!-- 单位排名,成果形式 -->
            <el-row :gutter="20">

              <el-col :span="12">
                <el-form-item label="成果形式:" prop="at_id">
                  <el-select v-model="editForm.at_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in atList" :label="item.at_name" :value="item.at_id" :key="item.at_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="单位排名:" prop="dr_id">
                  <el-input v-model="editForm.dr_id" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini" @click="addMemberDialogVisible = true">添加作者</el-button>
            </el-form-item>

            <el-table :data="memberList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
              :header-cell-style="{ background: '#f5f7fa' }"  :default-sort="{prop: 'contribution', order: 'descending'}">
              <!-- 序号列 -->
              <el-table-column type="index" label="#" align="center"></el-table-column>
              <el-table-column prop="name" label="作者姓名" align="center"></el-table-column>
              <el-table-column prop="role_name" label="成员类型" align="center"></el-table-column>
              <el-table-column prop="department_name" label="归属单位" align="center">
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
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editAwardsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editAwards">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑成果获奖对话框中添加作者对话框 -->
    <el-dialog title="添加作者" :visible.sync="addMemberDialogVisible" width="40%" @closed="addMemberDialogClosed">
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
    <!-- 编辑著作对话框中的编辑作者对话框 -->
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

    <el-dialog title="获奖成果详情" :visible.sync="awardInfoDialogVisible" width="50%">
      <el-form class="dialog" label-width="110px">
        <el-row>
          <el-form-item label="成果名字：">{{ awardInfo.achievement_name }}</el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="成果编号：">{{ awardInfo.award_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="获奖名字：">{{ awardInfo.award_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="负责人：">{{ awardInfo.user_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="发表日期：">{{ awardInfo.award_time }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="发证机关：">{{ awardInfo.issuing_authority }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="奖励批准号：">{{ awardInfo.approval_number }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="单位排名：">{{ awardInfo.dr_id }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="获奖级别：">{{ awardInfo.ar_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="获奖等级：">{{ awardInfo.al_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="成果形式：">{{ awardInfo.at_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="学科门类：">{{ awardInfo.sc_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="一级学科：">{{ awardInfo.subject_name }}</el-form-item></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="归属单位:">{{ awardInfo.aod_name }}</el-form-item></el-col
          >
          <el-col :span="12"
            ><el-form-item label="项目来源：">{{ awardInfo.sd_name }}</el-form-item></el-col
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
        <el-button type="primary" @click="awardInfoDialogVisible = false">确 定</el-button>
        <el-button @click="awardInfoDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { stringify } from 'qs';
export default {
  data() {
    return {
      // 标签页默认打开
      tabActiveName: "first",
      // 获奖成果信息列表对象
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
        pageNum: 1,
        // 当前页大小
        pageSize: 10,
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

      addForm: {
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
        authorList: [],
        periodicalSelectionList: [],
      },
      // 录入、修改论文数据表单验证对象
      addFormRules: {
        achievement_name: [{ required: true, message: "请输入成果名称", trigger: "blur" }],
        award_name: [{ required: true, message: "请输入获奖名称", trigger: "blur" }],
        sd_id: [{ required: true, message: "请选择项目来源", trigger: "change" }],
        dr_id: [{ required: true, message: "请输入单位排名", trigger: "change" }],
        approval_number: [{ required: true, message: "请输入奖励批准号", trigger: "blur" }],
        issuing_authority: [{ required: true, message: "请输入发证机关", trigger: "blur" }],
        award_time: [{ type: "date", required: true, message: "请选择获奖日期", trigger: "change" }],
        sc_id: [{ required: true, message: "请选择学科门类", trigger: "change" }],
        ar_id: [{ required: true, message: "请选择获奖级别", trigger: "change" }],
        at_id: [{ required: true, message: "请选择成果形式", trigger: "change" }],
        al_id: [{ required: true, message: "请选择获奖等级", trigger: "change" }],
        subject_id: [{ required: true, message: "请选择一级学科", trigger: "change" }],
        aod_id: [{ required: true, message: "请选择成果归属单位", trigger: "change" }],
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
      // // 控制录入获奖成果对话框显示与隐藏变量
      addAwardsDialogVisible: false,

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
      // 编辑成果获奖对话框显示控制变量
      editAwardsDialogVisible: false,
      // 编辑成果获奖表单对象
      editForm: {},
      // 编辑成果对话框中成员信息列表
      memberList: [],
      addMemberDialogVisible: false,
      addMemberForm: {
        user_id: '',
        contribution: 1
      },
      addMemberFormRules: {
        user_id: [{ required: true, message: '请输入作者姓名', trigger: 'change' }],
      },
      editMemberDialogVisible: false,
      editMemberForm: {},
      editMemberFormRules: {
        user_id: [{ required: true, message: '请输入作者姓名', trigger: 'change' }],
      },
      awardInfoDialogVisible: false,
      awardInfo: {},
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
      const { data: res1 } = await this.$http.post("/department/findAllDepartment");
      this.departmentList = res1.data;
      // 构造单位 id:name 对象
      this.departmentList.forEach((item) => (this.departmentObj[item.department_id] = item.department_name));
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post("/category/findAllSubjectCategory");
      this.scList = res2.data;
      // 构造学科门类 id:name 对象
      this.scList.forEach((item) => (this.scObj[item.sc_id] = item.sc_name));
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post("/subject/findAllSubject");
      this.subjectList = res3.data;
      // 构造一级学科 id:name 对象
      this.subjectList.forEach((item) => (this.subjectObj[item.subject_id] = item.subject_name));
      // 构造 获奖级别
      const { data: res4 } = await this.$http.post("/awardRank/findAllAwardRank");
      this.arList = res4.data;
      this.arList.forEach((item) => (this.arObj[item.ar_id] = item.ar_name));
      // 构造 获奖等级
      const { data: res5 } = await this.$http.post("/awardLevel/findAllAwardLevel");
      this.alList = res5.data;
      this.alList.forEach((item) => (this.alObj[item.al_id] = item.al_name));
      // 构造 成果形式
      const { data: res6 } = await this.$http.post("/achievementType/findAllAchievementType");
      this.atList = res6.data;
      this.atList.forEach((item) => (this.atObj[item.at_id] = item.at_name));
    },

    // 获取获奖成果列表
    async preGetAwardList() {
      if(this.queryInfo.award_time !== ''){
        this.queryInfo.award_time_start = this.queryInfo.award_time[0]
        this.queryInfo.award_time_end = this.queryInfo.award_time[1]
      } else this.queryInfo.award_time_start = this.queryInfo.award_time_end = ''
      // 通过 post 请求获取获奖成果列表
      const { data: res } = await this.$http.post("award/selectAwardByCondition", this.$qs.stringify(this.queryInfo));
      if (res.status === "404") {
        return this.$router.push("/home");
      }
      this.total = res.data[0].total;
      this.awardsList = res.data[1];
      // 通过 id:name 对象处理获奖成果列表，为其加上各name属性，截取时间日期
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
    async getAwardList() {
      await this.preGetAwardList()
      this.awardsList = JSON.parse(JSON.stringify(this.awardsList))
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
    // 录入获奖成果对话框中作者表格选中项改变
    addAuthorSelectionChange(val) {
      this.authorSelectionList = val;
    },
    // 选择获奖成果收录列表发生变化
    periodicalSelectionChange(val) {
      this.addForm.periodicalSelectionList = val;
    },
    // 关闭录入获奖成果对话框，清空相应数据
    addAwardsDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.addForm.authorList = []
    },
    // 点击确定，录入获奖成果
    addAwards() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return this.$message.error("请正确填写获奖成果信息");
        var getObj = JSON.parse(JSON.stringify(this.addForm))
        var user_id = []
        var contribution = []
        this.addForm.authorList.forEach(item => {
          user_id.push(item.author_info.user_id)
          contribution.push(item.author_rate)
        })
        getObj.leader_id = user_id[0]
        getObj.examine_status = '未通过'
        const { data: res } = await this.$http.get(`/awardExamine/addAwardExamine?${this.$qs.stringify(getObj)}
          &user_id=${user_id}
          &contribution=${contribution}
        `)
        if(res.status !== '200') return this.$message.error('提交成果获奖申请失败')
        this.$message.success('提交成果获奖成功')
        await this.getAwardList()
        this.awardsList = JSON.parse(JSON.stringify(this.awardsList))
        this.addAwardsDialogVisible = false
      });
    },
    // 根据用户输入，获取用户列表
    async getLeaderList(query) {
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
    // 点击按钮，删除成果获奖
    async deleteAwardById(award_id) {
      const res = await this.$confirm('此操作将永久删除该成果获奖，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/award/delAward', this.$qs.stringify({ award_id: award_id }))
      if(res2.status !== '200') return this.$message.error('删除成果获奖失败')
      this.$message.success('删除著作成功')
      this.getAwardList()
    },
    // 获取团队成员列表
    async getMemberList(award_id) {
      const { data: res } = await this.$http.post('/team/selectAwardTeam', stringify({ award_id: award_id }))
      if( res.status !== '200' ) return this.$message.error('获取团队成员失败')
      this.memberList = res.data
      console.log(this.memberList)
    },
    // 点击成果获奖列表中的编辑按钮，显示编辑成果获奖对话框
    async showEditAwardDialog(award_id) {
      const { data: res } = await this.$http.post('/award/findAwardById', stringify({ award_id: award_id }))
      if( res.status !== '200' ) return this.$message.error('获取成果获奖信息失败')
      this.editForm = res.data
      this.getMemberList(award_id)
      this.editAwardsDialogVisible = true
    },
    // 编辑成果获奖对话框关闭事件，清空编辑成果获奖表单对象
    editAwardsDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    addMemberDialogClosed() {
      this.$refs.addMemberFormRef.resetFields()
    },
    addMemberSelectionChanged(user_id) {
      this.leaderList.forEach(item => {
        if(item.user_id === user_id) {
          this.addMemberForm.role_name = item.role_name
          this.addMemberForm.department_name = this.departmentObj[item.department_id]
          return
        }
      })
    },
    async deleteMember(user_id) {
      const res2 = await this.$confirm('此操作将永久删除该作者，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res2 === 'cancel' ) return this.$message.info('取消了本次操作')
      const { data: res } = await this.$http.post('/team/delAwardTeamUser', stringify({ award_id: this.editForm.award_id, user_id: user_id }))
      if( res.status !== '200' ) return this.$message.error('删除团队成员失败')
      this.$message.success('删除团队成员成功')
      await this.getMemberList(this.editForm.award_id)
    },
    async addMember() {
      var postObj = JSON.parse(JSON.stringify(this.addMemberForm))
      postObj.award_id = this.editForm.award_id
      console.log(postObj)
      const { data: res } = await this.$http.post('/team/addAwardTeamUser', this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('添加团队成员失败')
      this.$message.success('添加团队成员成功')
      await this.getMemberList(this.editForm.award_id)
      this.addMemberDialogVisible = false
    },
    editMemberDialogClosed() {
      this.$refs.editMemberFormRef.resetFields()
    },
    async showEditMemberDialog(val) {
      console.log(val)
      this.editMemberForm = JSON.parse(JSON.stringify(val))
      await this.getLeaderList(this.editMemberForm.name)
      await this.editMemberSelectionChanged(this.editMemberForm.user_id)
      this.editMemberDialogVisible = true
    },
    editMemberSelectionChanged(user_id) {
      this.leaderList.forEach(item => {
        if(item.user_id === user_id) {
          this.editMemberForm.user_name = item.user_name
          this.editMemberForm.department_name = this.departmentObj[item.department_id]
          return
        }
      })
    },
    async editMember() {
      var postObj = JSON.parse(JSON.stringify( this.editMemberForm ))
      postObj.award_id = this.editForm.award_id
      const { data: res } = await this.$http.post('/team/updateAwardTeamUser', this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('编辑作者信息失败')
      this.$message.success('编辑作者信息成功')
      await this.getMemberList(this.editForm.award_id)
      this.editMemberDialogVisible = false  
    },
    // 上传编辑成果获奖信息
    async editAwards() {
      const { data: res } = await this.$http.post('/award/updateAward', stringify(this.editForm))
      if( res.status !== '200' ) return this.$message.error('上传编辑成果获奖信息失败')
      this.$message.success('上传编辑成果获奖信息成功')
      this.getAwardList()
      this.editAwardsDialogVisible = false
    },
    async showAwardInfoDialog(awardId) {
      const { data: res } = await this.$http.post("/award/findAwardById", this.$qs.stringify({ award_id: awardId }));
      if (res.status !== "200") return this.$message.error("获取获奖成果信息失败");
      this.awardInfo = res.data;
      const { data: res2 } = await this.$http.post('/team/selectAwardTeam', this.$qs.stringify({ award_id: awardId }))
      if( res2.status !== '200' ) return this.$message.error('获取成果获奖团队信息失败')
      this.memberList = res2.data
      this.awardInfo.aod_name = this.departmentObj[this.awardInfo.aod_id];
      this.awardInfo.sc_name = this.scObj[this.awardInfo.sc_id];
      this.awardInfo.subject_name = this.subjectObj[this.awardInfo.subject_id];
      this.awardInfo.sd_name = this.departmentObj[this.awardInfo.sd_id];
      this.awardInfo.award_time = this.awardInfo.award_time.substring(0, 10);
      this.awardInfo.ar_name = this.arObj[this.awardInfo.ar_id];
      this.awardInfo.al_name = this.alObj[this.awardInfo.al_id];
      this.awardInfo.at_name = this.atObj[this.awardInfo.at_id];
      this.awardInfoDialogVisible = true;
    },
  },
};
</script>

<style lang="less" scoped></style>
