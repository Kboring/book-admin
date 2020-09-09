<template>
  <div>
    <Card title="书籍管理">
      <div>
        <div style="display:inline-block;float:left;">
          <Button type="success" @click="addBook">+新增书籍</Button>
        </div>
        <div style="display:inline-block;float:right;">
          <Input v-model="search" suffix="ios-search" placeholder="请输入查询信息" style="width: auto"
                 :search=true @on-search="handleSearch"/>
        </div>
      </div>
      <div style="clear: both;"></div>
      <div style="margin-top: 10px;">
        <Table ref="dictTable" @on-sort-change="onSortChange" :columns="columns" :data="dictData" :height="tableHeight">

          <template slot-scope="{ row, index }" slot="BookId">
            <Input type="text" v-model="BookId" v-if="editIndex === index"/>
            <span v-else>{{ row.BookId }}</span>
          </template>

          <template slot-scope="{ row, index }" slot="BookName">
            <Input type="text" v-model="BookName" v-if="editIndex === index"/>
            <span v-else>{{ row.BookName }}</span>
          </template>

          <template slot-scope="{ row, index }" slot="Publisher">
            <Input type="text" v-model="Publisher" v-if="editIndex === index"/>
            <span v-else>{{ row.Publisher}}</span>
          </template>

          <template slot-scope="{ row, index }" slot="Price">
            <Input type="text" v-model="Price" v-if="editIndex === index"/>
            <span v-else>{{ row.Price}}</span>
          </template>

          <template slot-scope="{ row, index }" slot="Subhead" >
            <Input type="text" v-model="Subhead" v-if="editIndex === index"/>
            <span v-else>{{ row.Subhead}}</span>
          </template>

          <template slot-scope="{ row, index }" slot="Introduction">
            <Input type="text" v-model="Introduction" v-if="editIndex === index"/>
            <span v-else>{{ row.Introduction}}</span>
          </template>

          <template slot-scope="{ row, index }" slot="BookThumb">
<!--            <Input type="text" v-model="BookThumb" v-if="editIndex === index"/>-->
<!--            <span v-else>{{ row.BookThumb}}</span>-->
<!--            <a v-else href="static/pdf/2020_0001.pdf" class="preview" target="_blank">预览</a>-->
<!--            <div>-->
<!--              <div class="imageWrap">-->
<!--                <img :src="insideSrc" alt="">-->
<!--              </div>-->
<!--              <Upload-->
<!--                :action="image/upload"-->
<!--                :accept="acceptFile"-->
<!--                :before-upload="beforeUpload"-->
<!--                >-->
<!--                <Button style="width: 150px;" type="primary">上传图片</Button>-->
<!--              </Upload>-->
<!--            </div>-->
            <form action="/fileUpload" method="post" enctype="multipart/form-data">
              <label>上传图片</label>
              <input type="file" name="file"/>
              <input type="submit" value="上传"/>
            </form>
            <p>图片:</p>
            <img src="${filename}"/>
          </template>

          <template slot-scope="{ row, index }" slot="StyleBook">
            <Input type="text" v-model="StyleBook" v-if="editIndex === index"/>
            <span v-else>{{ row.StyleBook}}</span>
            <a v-else href="static/pdf/2020_0001.pdf" class="preview" target="_blank">预览</a>
          </template>

          <template slot-scope="{ row, index }" slot="CategoryId">
            <Input type="text" v-model="CategoryId" v-if="editIndex === index"/>
            <span v-else>{{ row.CategoryId}}</span>
          </template>

          <template slot-scope="{ row, index }" slot="action">
            <div v-if="editIndex === index">
              <Button type="success" @click="handleUpdate(index)">确定</Button>
              <Button type="error" @click="editIndex = -1">取消</Button>
            </div>
            <div v-else>
              <Button type="primary" @click="handleEdit(row, index)" size="small">编辑</Button>
              <Button type="error" @click="handleDelete(row, index)" size="small">删除</Button>
            </div>
          </template>

        </Table>
      </div>
      <div style="margin-top: 10px;">
        <Page show-elevator show-sizer show-total :total="total" :current="current"
              :page-size="pageSize" @on-change="changePage" @on-page-size-change="changePageSize"/>
      </div>
    </Card>
    <add-book v-model="addShow" v-on:handleSearch="handleSearch"></add-book>
  </div>
</template>
<script>

  import {updateBook,deleteBook, queryBookList} from '../../../api/sys/book/book.api'
  import addBook from "./addBook";


  export default {
    name:'ImageUpload',
    components:{
      addBook
    },
    data() {
      return {
        // 展示选中的的imageSrc
        insideSrc: '',
        // 接受上传的文件类型
        acceptFile: 'image/png,image/jpeg',
        addShow:false,
        search: '',
        dictData: [ ],
        columns: [
          {
            title: '书籍序号',
            key: 'BookId',
            slot: 'BookId',
            sortable: true
          },
          {
            title: '书籍名称',
            slot: 'BookName',
            key: 'BookName',
          },
          {
            title: '作者',
            slot: 'Subhead',
            key: 'Subhead',
          },
          {
            title: '出版社',
            slot: 'Publisher',
            key: 'Publisher',
          },
          {
            title: '书籍价格',
            slot: 'Price',
            key: 'Price',
          },
          {
            title: '简介',
            slot: 'Introduction',
            key: 'Introduction',
          },
          {
            title: '缩略图(地址)',
            slot: 'BookThumb',
            key: 'BookThumb',
          },
          {
            title: '样书(地址)',
            slot: 'StyleBook',
            key: 'StyleBook',
          },
          {
            title: '二级目录id',
            slot: 'CategoryId',
            key: 'CategoryId',
          },
          {
            title: '操作',
            slot: 'action'
          }
        ],
        key:' BookId',
        order:'asc',
        editIndex: -1,  // 当前聚焦的输入框的行数
        editId: '',
        BookId: '',
        BookName: '',
        Subhead:'',
        Publisher: '',
        Price:'',
        Introduction:'',
        BookThumb:'',
        StyleBook:'',
        CategoryId:'',
        tableHeight:200,
        total: 1,
        current: 1,
        pageSize: 10,
        NewIndex: 0
      }
    },
    methods: {
      addBook() {
        this.addShow = true
      },
      changePage(current) {
        this.current = current;
        this.handleSearch();
      },
      changePageSize(pageSize) {// 改变每页记录的条数
        this.pageSize = pageSize;
        this.handleSearch();
      },
      handleSearch(){
        let current = this.current
        let pageSize = this.pageSize
        let search = this.search
        let orderKey = this.key
        let orderByValue = this.order
        let NewIndex = this.NewIndex
        const _this = this;
        queryBookList({
          current,
          pageSize,
          search,
          orderKey,
          orderByValue,
          NewIndex
        }).then(res => {
          if (res.code == 200) {
            this.$Message.success('查询书籍成功')
            _this.total = res.obj.total
            _this.dictData = res.obj.rows
            _this.BookThumb = res.obj.BookThumb
          } else {
            this.$Message.error(  '查询书籍成失败,' + res.msg)
          }
        });
      },
      beforeUpload (file) {
        if (!this.acceptFile.split(',').includes(file.type)) {
          this.$Message.warning('未选中图片格式的文件')
          return false
        }
        if (file.size / 1024 > this.maxSize) {
          this.$Message.warning(`文件大小超过了${this.maxSize}KB`)
          return false
        }
        // 转换为base64
        const reader = new FileReader()
        // 将读取到的文件编码成Data URL
        reader.readAsDataURL(file)
        reader.onload = (event) => {
          this.insideSrc = event.srcElement.result
        }
        // 若返回 false 则停止上传,此时中断则判断大小无法使用max-size属性判断
        return false
    },
      handleUpdate(index){
        updateBook({
          BookId: this.BookId,
          Publisher: this.Publisher,
          BookName: this.BookName,
          Price: this.Price,
          Subhead: this.Subhead,
          Introduction: this.Introduction,
          BookThumb:this.BookThumb,
          StyleBook: this.StyleBook,
          CategoryId: this.CategoryId,
          //dictCode: this.editDictCode
        }).then(res => {
          if (res.code == 200) {
            this.$Message.success('更新书籍数据成功')
            this.editIndex = -1
            this.handleSearch()
            console.log(this.Price)
          } else {
            this.$Message.error( '更新书籍数失败,' + res.msg)
          }
        });
      },
      handleEdit(row, index){
        //this.editDictCode = row.BookId 不用修改序号
        this.BookName = row.BookName
        this.Publisher = row.Publisher
        this.Price = row.Price
        this.BookId = row.BookId
        this.Subhead = row.Subhead
        this.Introduction = row.Introduction
        this.StyleBook = row.StyleBook
        this.BookThumb = row.BookThumb
        this.CategoryId = row.CategoryId
        this.editIndex = index
      },
      handleDelete(row, index){
        this.$Modal.confirm({
          title: '提示',
          content: '<p>是否删除字典数据？</p>',
          onOk: () => {
            deleteBook({id: row.BookId}).then(res => {
              if (res.code == 200) {
                this.$Message.success('字典数据删除成功');
                // 删除数据成功同时刷新grid
                this.handleSearch();
              } else {
                this.$Message.warning('字典数据删除失败');
              }
            });
          },
          onCancel: () => {
            this.$Message.info('取消删除请求');
          }
        });
      },
      onSortChange(sort){
        console.log(sort.key+'-'+sort.order)
        if(sort.order=='normal'){
          this.order = '';
        }else{
          this.key = sort.key;
          this.order = sort.order;
        }
        this.handleSearch();
      }
    },
    watch:{
      $route(to, from){
        if (this.$route.query.NewIndex == null){
          this.NewIndex = localStorage.getItem("NewIndex")
        }else {
          this.NewIndex = this.$route.query.NewIndex
          localStorage.setItem("NewIndex",this.NewIndex)
        }
        this.handleSearch()
      }
    },
    mounted() {
      // 初始化完成组件的时候执行以下的逻辑
      if (this.$route.query.NewIndex == null){
        this.NewIndex = localStorage.getItem("NewIndex")
      }else {
        this.NewIndex = this.$route.query.NewIndex
        localStorage.setItem("NewIndex",this.NewIndex)
      }
      this.handleSearch();
      this.tableHeight = window.innerHeight - this.$refs.dictTable.$el.offsetTop - 270
    }
  }

</script>
<style scoped>
  .imageWrap {
    width: 100%;
    height: 100px;
    border: 1px solid #ffffff;
  }
</style>
