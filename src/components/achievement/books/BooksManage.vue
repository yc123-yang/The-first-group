<template>
  <div style="margin-top: 25px" v-loading="isLoading">
    <el-button type="primary" size="medium" @click="addBooksDialogVisible = true">录入著作</el-button>
    <download-excel :data="selectionList" :fields="excelFields" style="display: inline; margin-left: 10px">
      <el-button type="warning" size="medium" :disabled="selectionList.length === 0">导出信息</el-button>
    </download-excel>

    <el-table :data="booksList" style="width: 100%; margin-top: 15px" border @selection-change="selectionChange"
      :header-cell-style="{ background: '#f5f7fa' }" v-loading="isLoading">
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
          <el-select class="columnInput" v-model="queryInfo.pl" size="mini" placeholder="请选择" @change="QueryBookList">
            <el-option v-for="item in plList" :key="item.pl_id" :label="item.pl_name" :value="item.pl_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="bt_name" label="著作类型" width="200px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">著作类型</div>
          <el-select class="columnInput" v-model="queryInfo.bt" size="mini" placeholder="请选择" @change="QueryBookList">
            <el-option v-for="item in btList" :key="item.bt_id" :label="item.bt_name" :value="item.bt_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="pp_name" label="出版地" width="200px" align="center">
        <template slot="header" slot-scope="scope"
          >
          <div style="line-height: 14px">出版地</div>
          <el-select class="columnInput" v-model="queryInfo.pp_id" size="mini" placeholder="请选择" @change="QueryBookList">
            <el-option v-for="item in ppList" :key="item.pp_id" :label="item.pp_name" :value="item.pp_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="publish_time" label="发表日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">发表日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0" size="mini" @change="QueryBookList" v-model="queryInfo.publish_time" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"> </el-date-picker>
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
          <div style="line-height: 14px">总字数</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.trans" placeholder="请输入" @change="QueryBookList"> </el-input>
        </template>
      </el-table-column>

      <el-table-column prop="language_name" label="翻译语种" width="200px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">翻译语种</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.language_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in languageList" :key="item.language_id" :label="item.language_name" :value="item.language_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="sc_name" label="学科门类" width="200px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">学科门类</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.sc_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">一级学科</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.subject" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name" :value="item.subject_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="aod_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">归属单位</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.aod_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="sd_name" label="项目来源" width="250px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">项目来源</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.sd_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name" :value="item.department_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="rt_name" label="研究类别" width="250px" align="center">
        <template slot="header" slot-scope="scope">
          <div style="line-height: 14px">研究类别</div>
          <el-select @change="QueryBookList" class="columnInput" v-model="queryInfo.rt_id" multiple size="mini" collapse-tags placeholder="请选择">
            <el-option v-for="item in rtList" :key="item.rt_id" :label="item.rt_name" :value="item.rt_id"> </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditPaperDialog(scope.row.book_id)"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteBookById(scope.row.book_id)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    <!-- 录入著作成果对话框 -->
    <el-dialog title="录入著作成果" :visible.sync="addBooksDialogVisible" width="60%" @close="addBooksDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 著作题目 -->
            <el-form-item label="著作题目:" prop="book_name">
              <el-input v-model="addForm.book_name" size="mini"></el-input>
            </el-form-item>
            <!-- 出版社、出版社级别 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="出版社:" prop="press">
                  <el-input v-model="addForm.press" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出版社级别:" prop="pl">
                  <el-select v-model="addForm.pl_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in plList" :label="item.pl_name" :value="item.pl_id" :key="item.pl_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 著作类别，出版地 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="著作类别:" prop="bt">
                  <el-select v-model="addForm.bt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in btList" :label="item.bt_name" :value="item.bt_id" :key="item.bt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出版地:" prop="pp">
                  <el-select v-model="addForm.pp_id" placeholder="请选择" size="mini" style="width: 100%">
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
                  <el-select v-model="addForm.trans" size="mini">
                    <el-option value="是"></el-option>
                    <el-option value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="翻译语种:" prop="language">
                  <el-select v-model="addForm.language_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in languageList" :label="item.language_name" :value="item.language_id" :key="item.language_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 学科门类，发表时间 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="学科门类:" prop="sc">
                  <el-select v-model="addForm.sc_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in scList" :label="item.sc_name" :value="item.sc_id" :key="item.sc_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="发表时间:" prop="publish_time">
                  <el-date-picker placeholder="请选择日期" v-model="addForm.publish_time" style="width: 100%" size="mini"
                    value-format="yyyy-MM-dd 00:00:00"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 一级学科,成果归属 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="一级学科:" prop="subject">
                  <el-select v-model="addForm.subject_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in subjectList" :label="item.subject_name" :value="item.subject_id" :key="item.subject_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod">
                  <el-select v-model="addForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 项目来源,研究类别 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="项目来源:" prop="sd">
                  <el-select v-model="addForm.sd_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="研究类别:" prop="rt">
                  <el-select v-model="addForm.rt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in rtList" :label="item.rt_name" :value="item.rt_id" :key="item.rt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini" @click="addAuthorDialogVisible = true">添加作者</el-button>
            </el-form-item>

            <el-table :data="addForm.authorList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
              :header-cell-style="{ background: '#f5f7fa' }" :default-sort="{prop: 'author_rate', order: 'descending'}">
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
        <el-button @click="addBooksDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addBooks">确 定</el-button>
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

    <!-- 编辑著作对话框 -->
    <el-dialog title="编辑著作成果" :visible.sync="editBooksDialogVisible" width="60%" @close="editBooksDialogClosed">
      <el-form :model="editForm" :rules="addFormRules" ref="editFormRef" label-width="100px" size="mini">
        <el-row :gutter="20">
          <!-- 表单左侧 -->
          <el-col>
            <!-- 著作题目 -->
            <el-form-item label="著作题目:" prop="book_name">
              <el-input v-model="editForm.book_name" size="mini"></el-input>
            </el-form-item>
            <!-- 出版社、出版社级别 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="出版社:" prop="press">
                  <el-input v-model="editForm.press" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出版社级别:" prop="pl">
                  <el-select v-model="editForm.pl_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in plList" :label="item.pl_name" :value="item.pl_id" :key="item.pl_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 著作类别，出版地 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="著作类别:" prop="bt">
                  <el-select v-model="editForm.bt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in btList" :label="item.bt_name" :value="item.bt_id" :key="item.bt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出版地:" prop="pp">
                  <el-select v-model="editForm.pp_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in ppList" :label="item.pp_name" :value="item.pp_id" :key="item.pp_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- ISBN、总字数 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="ISBN号:" prop="isbn">
                  <el-input v-model="editForm.isbn" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="总字数:" prop="word_number">
                  <el-input v-model="editForm.word_number" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 是否翻译，翻译语种 -->
            <el-row :gutter="20">
                <el-col :span="12">
                <el-form-item label="是否翻译:" prop="trans">
                  <el-select v-model="editForm.trans" size="mini">
                    <el-option value="是"></el-option>
                    <el-option value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="翻译语种:" prop="language">
                  <el-select v-model="editForm.language_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in languageList" :label="item.language_name" :value="item.language_id" :key="item.language_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 学科门类，发表时间 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="学科门类:" prop="sc">
                  <el-select v-model="editForm.sc_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in scList" :label="item.sc_name" :value="item.sc_id" :key="item.sc_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="发表时间:" prop="publish_time">
                  <el-date-picker placeholder="请选择日期" v-model="editForm.publish_time" style="width: 100%" size="mini"
                    value-format="yyyy-MM-dd 00:00:00"> </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

            <!-- 一级学科,成果归属 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="一级学科:" prop="subject">
                  <el-select v-model="editForm.subject_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in subjectList" :label="item.subject_name" :value="item.subject_id" :key="item.subject_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="归属单位:" prop="aod">
                  <el-select v-model="editForm.aod_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- 项目来源,研究类别 -->
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="项目来源:" prop="sd">
                  <el-select v-model="editForm.sd_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="研究类别:" prop="rt">
                  <el-select v-model="editForm.rt_id" placeholder="请选择" size="mini" style="width: 100%">
                    <el-option v-for="item in rtList" :label="item.rt_name" :value="item.rt_id" :key="item.rt_id"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="作者:" prop="author" size="mini">
              <el-button type="primary" size="mini" @click="addMemberDialogVisible = true">添加作者</el-button>
            </el-form-item>

            <el-table :data="memberList" style="width: 100%" ref="authorTableRef" size="mini" border height="250px"
              :header-cell-style="{ background: '#f5f7fa' }" :default-sort="{prop: 'author_rate', order: 'descending'}">
              <!-- 序号列 -->
              <el-table-column type="index" label="#" align="center"></el-table-column>
              <el-table-column prop="name" label="作者姓名" align="center"></el-table-column>
              <el-table-column prop="role_name" label="成员类型" align="center"></el-table-column>
              <el-table-column prop="department_name" label="归属单位" align="center">
                <template slot-scope="scope">{{departmentObj[scope.row.department_id]}}</template>
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
        <el-button @click="editBooksDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editBooks">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑著作成果对话框中添加作者对话框 -->
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
import { stringify } from 'qs';
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
        pageNum: 1,
        // 当前页大小
        pageSize: 5,
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
        leader_id: "",
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
        remark: "",
        authorList: [],
        periodicalSelectionList: [],
      },
      // 录入、修改论文数据表单验证对象
      addFormRules: {
        book_name: [{ required: true, message: "请输入著作题目", trigger: "blur" }],
        sd_id: [{ required: true, message: "请选择项目来源", trigger: "change" }],
        rt_id: [{ required: true, message: "请选择研究类别", trigger: "change" }],
        press: [{ required: true, message: "请输入出版社", trigger: "blur" }],
        word_number: [{ required: true, message: "请输入总字数", trigger: "blur" }],
        trans: [{ required: true, message: "请选择是否翻译", trigger: "change" }],
        language_id: [{ required: true, message: "请输入是或者否", trigger: "blur" }],
        pl_id: [{ required: true, message: "请选择出版社等级", trigger: "change" }],
        bt_id: [{ required: true, message: "请选择著作类型", trigger: "change" }],
        publish_time: [{ required: true, message: "请选择发表日期", trigger: "change" }],
        pp_id: [{ required: true, message: "请选择出版地", trigger: "change" }],
        isbn: [{ required: true, message: "请输入ISBN号", trigger: "blur" }],
        sc_id: [{ required: true, message: "请选择学科门类", trigger: "change" }],
        subject_id: [{ required: true, message: "请选择一级学科", trigger: "change" }],
        aod_id: [{ required: true, message: "请选择成果归属单位", trigger: "change" }]
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
      // 添加作者对话框
      addMemberDialogVisible: false,
      // 正在编辑的位置
      editAuthorIndex: '',
      // 编辑作者表单验证对象
      editAuthorFormRules: {},
      // 编辑著作对话框显示与隐藏的控制变量
      editBooksDialogVisible: false,
      // 编辑著作信息表单
      editForm: {},
      // 著作团队列表
      memberList: [],
      addMemberDialogVisble: false,
      // 编辑著作对话框中添加作者表单对象
      addMemberForm: {
        user_id: '',
        contribution: 1
      },
      // 编辑著作对话框中的编辑作者对话框的显示与隐藏控制变量
      editMemberDialogVisible: false,
      // 编辑著作对话框中的编辑作者表单对象
      editMemberForm: {},
      // 编辑著作编辑作者表单验证规则对象
      editMemberFormRules: {
        user_id: [{ required: true, message: '请输入作者姓名', trigger: 'change' }]
      },
      addMemberFormRules: {
        user_id: [{ required: true, message: '请输入作者姓名', trigger: 'change' }],
      },
      bookInfoDialogVisible: false,
      bookInfo: {},
      isLoading: true,
      selectionList: [],
      excelFields: this.$excelFields.book
    };
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
          console.log(1)
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
          console.log(2)
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
          console.log(3)
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
          console.log(4)
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
          console.log(5)
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
          console.log(6)
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
          console.log(7)
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
          console.log(8)
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
      if(this.queryInfo.publish_time !== '') {
        this.queryInfo.publish_time_start = this.queryInfo.publish_time[0]
        this.queryInfo.publish_time_end = this.queryInfo.publish_time[1]
      } else this.queryInfo.publish_time_start = this.queryInfo.publish_time_end = ''
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
      this.isLoading = false
    },
    // 多选框条件发生变化
    selectionChange(val) {
      this.selectionList = val
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
      this.addForm.authorList = []
      this.$refs.addFormRef.resetFields();
    },
    // 点击确定，录入著作
    addBooks() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return this.$message.error("请正确填写著作成果信息");
        var user_id = []
        var contribution = []
        this.addForm.authorList.forEach(item => {
          user_id.push(item.author_info.user_id)
          contribution.push(item.author_rate)
        })
        this.addForm.leader_id = user_id[0]
        console.log(this.addForm)
        const { data: res } = await this.$http.get(`/bookExamine/addBookExamine?${this.$qs.stringify(this.addForm)}&user_id=${user_id}&contribution=${contribution}`)
        if(res.status !== '200') return this.$message.error('提交著作成果申请失败')
        this.$message.success("收录著作成果成功");
        this.addBooksDialogVisible = false
        this.getBookList()
      })
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
    // 点击按钮，删除著作
    async deleteBookById(bookId) {
      const res = await this.$confirm('此操作将永久删除该著作，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/book/delBook', this.$qs.stringify({ book_id: bookId }))
      if(res2.status !== '200') return this.$message.error('删除著作失败')
      this.$message.success('删除著作成功')
      this.getBookList()
    },
    // 获取所有作者列表
    async getMemberList() {
      const { data: res } = await this.$http.post('/team/selectBookTeam', stringify({ book_id: this.editForm.book_id }))
      if( res.status !== '200' ) return this.$message.error('获取著作团队成员信息失败')
      this.memberList = res.data
      console.log(res.data)
    },
    // 点击编辑按钮，显示编辑著作对话框
    async showEditPaperDialog(book_id) {
      const { data: res } = await this.$http.post('/book/findBookById', stringify({ book_id: book_id }))
      if( res.status !== '200' ) return this.$message.error('获取著作信息失败')
      this.editForm = res.data
      this.getMemberList()
      this.editBooksDialogVisible = true
    },
    // 关闭编辑著作对话框事件，清空表单
    editBooksDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 编辑著作对话框中添加成员对话框的关闭事件
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
      postObj.book_id = this.editForm.book_id
      console.log(postObj)
      const { data: res } = await this.$http.post('/team/addBookTeamUser', this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('添加团队成员失败')
      this.$message.success('添加团队成员成功')
      await this.getMemberList()
      this.addMemberDialogVisible = false
    },
    // 点击删除按钮，删除成员
    async deleteMember(user_id) {
      const res = await this.$confirm('此操作将永久删除该作者，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if( res === 'cancel' ) return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/team/delBookTeamUser', this.$qs.stringify({ book_id: this.editForm.book_id, user_id: user_id }))
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
      postObj.book_id = this.editForm.book_id
      const { data: res } = await this.$http.post('/team/updateBookTeamUser', this.$qs.stringify(postObj))
      if( res.status !== '200' ) return this.$message.error('编辑作者信息失败')
      this.$message.success('编辑作者信息成功')
      await this.getMemberList()
      this.editMemberDialogVisible = false  
    },
    // 点击确定，上传编辑著作表单
    async editBooks() {
      const { data: res } = await this.$http.post('/book/updateBook', stringify(this.editForm))
      if( res.status !== '200' ) return this.$message.error('上传编辑著作信息失败')
      this.$message.success('上传编辑著作信息成功')
      this.getBookList()
      this.editBooksDialogVisible = false
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

<style lang="less" scoped></style>
