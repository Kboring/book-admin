<template>
  <div class="layout">
    <Layout>
      <!--  Header 表示头部的位置-->
      <Header>
        <Menu mode="horizontal" theme="dark">
          <div class="layout-logo">
            <img height="50px" width="50px" src="../../assets/logo.png"/>
          </div>
          <div class="layout-nav">
            <language @on-lang-change="setLanguage" style="margin-right: 10px;" :lang="local"/>
          </div>
          <div class="layout-nav">
            <Submenu name="1-1">
              <template slot="title">
                <Icon type="ios-navigate"></Icon>
                <span>用户名:{{login_name}}</span>
              </template>
              <MenuItem name="1">
                <span>真实姓名:{{name}}</span>
              </MenuItem>
              <MenuItem name="2"><span>密码:{{password}}</span></MenuItem>
              <MenuItem name="3"><span>电话号码:{{telephone}}</span></MenuItem>
              <MenuItem name="4"><span>邮箱:{{email}}</span></MenuItem>
              <MenuItem name="5"><span>部门:{{department_name}}</span></MenuItem>
            </Submenu>
            <template><Button type="primary" @click="logout()">注销</Button></template>
          </div>
        </Menu>
      </Header>
      <!-- 此处表示的是左侧的菜单栏的布局 -->
      <Layout>
        <Sider hide-trigger :style="{background: '#fff'}">
          <Menu theme="light" width="auto">
            <Submenu v-for="(child,IndexCode) in dictData" :name="IndexCode">
              <template slot="title">
                <span>{{CategoryIdData[IndexCode]}}:{{child}}</span>
                <ButtonGroup size="small">
                  <Button @click="addFirstTree">增</Button>
                  <Button @click="deleteFirstTree(CategoryIdData[IndexCode])">删</Button>
                  <Button @click="updateFirstTree">改</Button>
                </ButtonGroup>
              </template>
              <MenuItem :name="index" v-for="(item,index) in NameData"
                        v-if="IdData[index] == CategoryIdData[IndexCode]"
                        @click.native="HappySummer(IdNot0Data[index]-1)">
                <span>{{IdNot0Data[index]}}:{{item}}</span>
                <ButtonGroup size="small" shape="circle">
                  <Button @click="deleteSecondTree(IdNot0Data[index])">删</Button>
                  <Button @click="updateFirstTree">改</Button>
                </ButtonGroup>
              </MenuItem>
            </Submenu>
            <template>
              <Button type="primary" long @click="addFirstTree">+增加菜单</Button>
            </template>
          </Menu>
        </Sider>
        <add-tree v-model="addShow" v-on:HappySummer="HappySummer"></add-tree>
        <update-tree v-model="updateShow" v-on:HappySummer="HappySummer"></update-tree>
        <update-tree v-model="updateSecondShow" v-on:HappySummer="HappySummer"></update-tree>
        <Layout :style="{padding: '0 24px 24px'}">
          <!--  此处是面包屑导航条 -->
          <Breadcrumb :style="{margin: '24px 0'}">
            <!--            <BreadcrumbItem>Home</BreadcrumbItem>-->
            <!--            <BreadcrumbItem>Components</BreadcrumbItem>-->
            <!--            <BreadcrumbItem>Layout</BreadcrumbItem>-->
          </Breadcrumb>
          <!-- 此处存放的是文本内容的区域 -->
          <Content :style="{padding: '24px', minHeight: '280px', background: '#ffffff'}">
            <router-view/>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  </div>
</template>
<script>
  import Language from '../../components/language';
  import {
    deleteFirstTree, deleteSecondTree,
    getTreeByCategoryId,
    getTreeByCategoryIdNot0,
    getTreeByTreeId,
    getTreeByTreeName,
    getTreeList,
    updateFirstTree,
  } from "../../api/sys/tree/tree.api"
  import addTree from "../sys/tree/addTree";
  import UpdateTree from "../sys/tree/updateTree";

  export default {
    components: {
      UpdateTree,
      Language,
      addTree,
      updateFirstTree
    },
    data() {
      return {
        addShow:false,
        updateShow:false,
        updateSecondShow:false,
        local: localStorage.getItem("lang"),
        BookName:'',
        dictData:[],
        IdData:[],
        NameData:[],
        IdNot0Data:[],
        CategoryIdData:[],
        login_name:sessionStorage.getItem("login_name"),
        name:sessionStorage.getItem("name"),
        password:sessionStorage.getItem("password"),
        telephone:sessionStorage.getItem("telephone"),
        email:sessionStorage.getItem("email"),
        department_name:sessionStorage.getItem("department_name")
      }
    },
    methods: {
      addFirstTree(){
        this.addShow = true
      },
      updateFirstTree(){
        this.updateShow = true
      },
      HappySummer(NewIndex){
        this.$router.push({
          path: '/sys/bookList',
          query: {NewIndex: NewIndex+1}}
        );
      },
      deleteFirstTree(index){
        this.$Modal.confirm({
          title: '提示',
          content: '<p>是否删除该菜单？</p>',
          onOk: () => {
            deleteFirstTree({index}).then(res => {
              if (res.code == 200) {
                this.$Message.success('菜单删除成功');
                // 删除数据成功同时刷新grid
                this.HappySummer();
              } else {
                this.$Message.warning('菜单删除失败');
              }
            });
          },
          onCancel: () => {
            this.$Message.info('取消删除请求');
          }
        });
      },
      deleteSecondTree(index){
        this.$Modal.confirm({
          title: '提示',
          content: '<p>是否删除该菜单？</p>',
          onOk: () => {
            deleteSecondTree({index}).then(res => {
              if (res.code == 200) {
                this.$Message.success('菜单删除成功');
                // 删除数据成功同时刷新grid
                this.HappySummer();
              } else {
                this.$Message.warning('菜单删除失败');
              }
            });
          },
          onCancel: () => {
            this.$Message.info('取消删除请求');
          }
        });
      },
      logout(){
        this.$Modal.confirm({
          title: '提示',
          content: '<p>是否注销用户？</p>',
          onOk: () =>{
            sessionStorage.clear()
            this.$router.push({
              path: '/login'
            })
          },
          onCancel: () => {
            this.$Message.info('取消注销请求');
          }
        })
      },
      /**
       * 顶部跟随着滚动条的变化而滚动
       */
      // handleScroll() {
      //   let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      //   if (scrollTop >= 60) {
      //     document.querySelector('#layout-header-scroll').style.top = scrollTop + 'px';
      //   } else {
      //     document.querySelector('#layout-header-scroll').style.top = '0px';
      //   }
      // },
      setLanguage(lang) {
        this.local = lang
        localStorage.setItem('lang', lang)
      },
      getTreeListAll(){
        const _this = this;
        getTreeList().then(res =>{
          _this.dictData = res.obj.rows
          console.log("dictData"+_this.dictData)
        })
      },
      getTreeByTreeId(){
        const _this = this;
        getTreeByTreeId().then(res =>{
          _this.IdData = res.obj.rows
          console.log("IdData"+_this.IdData)
        })
      },
      getTreeByTreeName(){
        const _this = this;
        getTreeByTreeName().then(res =>{
          _this.NameData = res.obj.rows
          console.log("NameData"+_this.NameData)
        })
      },
      getTreeByCategoryIdNot0(){
        const _this = this;
        getTreeByCategoryIdNot0().then(res =>{
          _this.IdNot0Data = res.obj.rows
          console.log("IdNot0"+_this.IdNot0Data)
        })
      },
      getTreeByCategoryId(){
        const _this = this;
        getTreeByCategoryId().then(res =>{
          _this.CategoryIdData = res.obj.rows
          console.log("CategoryId"+_this.CategoryIdData)
        })
      },
    },
    watch:{
      $route(to, from){
        //console.log(this.$route.params.NewIndex)
      }
    },
    mounted() {
      /**
       * 监听滚动条的滚动事件
       * window.addEventListener('scroll', this.handleScroll)
       */
      this.getTreeListAll()
      this.getTreeByTreeId()
      this.getTreeByTreeName()
      this.getTreeByCategoryId()
      this.getTreeByCategoryIdNot0()
    },
    created() {
    }
  }
</script>
<style scoped>
  .layout-header {
    position: relative;
    z-index: 999;
    height: 60px;
  }

  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-logo {
    width: 100px;
    height: 30px;
    border-radius: 10px;
    float: left;
    position: relative;
    left: 20px;
    top: 5px;
  }

  .layout-nav {
    width: auto;
    float: right;
    margin: 0 auto;
    margin-right: 20px;
  }
</style>





<!--  左侧的菜单栏的布局  原代码  <Layout>-->
<!--      <Sider hide-trigger :style="{background: '#fff'}">-->
<!--        <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">-->
<!--          <Submenu name="1">-->
<!--            <template slot="title">-->
<!--              <Icon type="ios-navigate"></Icon>-->
<!--              系统管理-->
<!--            </template>-->
<!--            <MenuItem name="1-1" to="bookList">书籍管理</MenuItem>-->
<!--            <MenuItem name="1-2" to="treeList">菜单管理</MenuItem>-->
<!--            <MenuItem name="1-3">Option 3</MenuItem>-->
<!--          </Submenu>-->
<!--        </Menu>-->
<!--      </Sider>-->
<!--      <Layout :style="{padding: '0 24px 24px'}">-->
<!--        &lt;!&ndash;  此处是面包屑导航条 &ndash;&gt;-->
<!--        <Breadcrumb :style="{margin: '24px 0'}">-->
<!--          <BreadcrumbItem>Home</BreadcrumbItem>-->
<!--          <BreadcrumbItem>Components</BreadcrumbItem>-->
<!--          <BreadcrumbItem>Layout</BreadcrumbItem>-->
<!--        </Breadcrumb>-->
<!--        &lt;!&ndash; 此处存放的是文本内容的区域 &ndash;&gt;-->
<!--        <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">-->
<!--          <router-view/>-->
<!--        </Content>-->
<!--      </Layout>-->
<!--    </Layout>-->



<!-- 修改后的 含有死数据   <Layout>-->
<!--      <Sider hide-trigger :style="{background: '#fff'}">-->
<!--        <Menu theme="light" width="auto" :open-names="['1']">-->
<!--          <Submenu name="1" @click="test01">-->
<!--            <template slot="title">-->
<!--              <Icon type="ios-navigate"></Icon>-->
<!--              小学-->
<!--            </template>-->
<!--            <MenuItem name="1-1" to="bookList">快乐暑假</MenuItem>-->
<!--            <MenuItem name="1-2" to="bookList">考前讲练测</MenuItem>-->
<!--            <MenuItem name="1-3">课外阅读</MenuItem>-->
<!--          </Submenu>-->
<!--        </Menu>-->
<!--      </Sider>-->
<!--      <Layout :style="{padding: '0 24px 24px'}">-->
<!--        &lt;!&ndash;  此处是面包屑导航条 &ndash;&gt;-->
<!--        <Breadcrumb :style="{margin: '24px 0'}">-->
<!--          <BreadcrumbItem>Home</BreadcrumbItem>-->
<!--          <BreadcrumbItem>Components</BreadcrumbItem>-->
<!--          <BreadcrumbItem>Layout</BreadcrumbItem>-->
<!--        </Breadcrumb>-->
<!--        &lt;!&ndash; 此处存放的是文本内容的区域 &ndash;&gt;-->
<!--        <Content :style="{padding: '24px', minHeight: '280px', background: '#ffffff'}">-->
<!--          <router-view/>-->
<!--        </Content>-->
<!--      </Layout>-->
<!--    </Layout>-->
<!--    </Layout>-->
