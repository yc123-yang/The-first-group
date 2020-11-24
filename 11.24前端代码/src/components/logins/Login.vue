<template>
  <!-- 登录框表单区域 -->
      <div class="loginBoxContent">
        <el-form ref="loginFormRef" label-width="0px" class="loginForm" :model="loginForm" :rules="loginFormRules">
          <p>登录</p>
          <!-- 用户名 -->
          <label class="formLabel">用户名 / 邮箱</label>
          <el-form-item prop="user_act">
            <el-input prefix-icon="el-icon-user" v-model="loginForm.user_act"></el-input>
          </el-form-item>
          <!-- 密码 -->
          <label class="formLabel">密码</label>
          <el-form-item prop="user_pwd">
            <el-input prefix-icon="el-icon-lock" v-model="loginForm.user_pwd" type="password"></el-input>
          </el-form-item>
          <!-- 按钮区域 -->
          <div class="btns">
            <el-form-item>
              <el-button type="primary" class="loginBtn" @click="login">登录</el-button>
              <el-button type="text" class="registerBtn" @click="gotoRegister">创建用户</el-button>
              <el-button type="text" class="forgetPwdBtn">忘记密码?</el-button>
            </el-form-item>
          </div>
        </el-form>
      </div>
</template>

<script>
export default {
  data () {
    return {
      // 表单绑定对象
      loginForm: {
        user_act: 'admin',
        user_pwd: '123456'
      },
      // 表单验证规则对象
      loginFormRules: {
        user_act: [
          { required: true, message: '请输入用户名或邮箱', trigger: 'blur' }
        ],
        user_pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login () {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return false
        const { data: res } = await this.$http.post('user/login', this.$qs.stringify(this.loginForm))
        console.log(res)
        // 如果登录失败，显示失败信息
        if (res.status !== '0') return this.$message.error(res.msg)
        // 登录成功，跳转到主页
        this.$message.success(res.msg)
        window.sessionStorage.setItem('token', res.data[0].userData.token)
        this.$router.push('/home')
      })
    },
    gotoRegister () {
      this.$router.push('/register')
    }
  }
}
</script>

<style lang="less" scoped>
.loginBoxContent{
  display: inline-block;
  height: 100%;
  width: 400px;
  padding: 0 50px 0 50px;
  vertical-align: top;

  p{
    font-size: 32px;
    font-family: Arial;
    margin: 0 0 32px 0;
  }
}
.formLabel{
  font-size: 16px;
  font-family: Arial;
  display: block;
  margin: 8px 0;
}
.btns{
  padding: 10px 0 0 0;

  .loginBtn{
    font-size: 16px;
    width: 100%;
  }
  .el-button{
    margin: 0;
  }
  .forgetPwdBtn{
    color: #aeaeae;
    float: right;
  }
}
</style>
