<template>
  <div class="loginContainer">
    <div class="loginBox">
      <!-- 登录框左边logo+图像区域 -->
      <div class="loginBoxInfo">
        <img class="logo" src="../assets/image/sicnuLogo.png" alt="">
        <img class="sciImage" src="../assets/image/SciImage.jpg" alt="">
      </div>
      <!-- 登录框右半区域，登录、注册、找回密码功能 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  created() {
  }
}
</script>

<style lang="less" scoped>
.loginContainer{
  height: 100%;
  background-color: #fafafa;
}
.loginBox{
  display: flex;
  width: 1000px;
  height: 520px;
  background-color: #ffffff;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 0 10px #999999;
  padding: 40px 0 40px 0;
}
.loginBoxInfo{
  display: inline-block;
  height: 100%;
  width: 360px;
  padding: 0 69px 0 70px;
  border-right: 1px #e4e4e4 solid;
  vertical-align: top;

  .logo{
    display: block;
    height: 50px;
    margin: 0 auto;
  }
  .sciImage{
    display: block;
    width: 325px;
    margin: 0 auto;
  }
}
</style>
