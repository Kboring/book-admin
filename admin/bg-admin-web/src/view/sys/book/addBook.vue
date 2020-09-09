<template>
  <Modal v-model="show" title="新增书籍" @on-ok="ok" :loading="loading" :mask-closable="false">
    <Form ref="dictForm" :model="dictForm" :rules="dictFormRule">
      <FormItem label="书籍名称" prop="BookName">
        <Input type="text" :maxlength=50 v-model="dictForm.BookName" placeholder="请输入书籍名称"/>
      </FormItem>
      <FormItem label="副标题" prop="Subhead">
        <Input type="text" :maxlength=50 v-model="dictForm.Subhead" placeholder="请输入副标题"/>
      </FormItem>
      <FormItem label="出版社" prop="Publisher">
        <Input type="text" :maxlength=50 v-model="dictForm.Publisher" placeholder="请输入出版社"/>
      </FormItem>
      <FormItem label="价格" prop="Price">
        <Input type="text" :maxlength=50 v-model="dictForm.Price" placeholder="请输入价格"/>
      </FormItem>
      <FormItem label="简介" prop="Introduction">
        <Input type="text" :maxlength=50 v-model="dictForm.Introduction" placeholder="请输入简介"/>
      </FormItem>
      <FormItem label="缩略图，存储路径" prop="BookThumb">
        <Input type="text" :maxlength=50 v-model="dictForm.BookThumb" placeholder="请输入字典缩略图，存储路径"/>
      </FormItem>
      <FormItem label="样书，存储路径" prop="StyleBook">
        <Input type="text" :maxlength=50 v-model="dictForm.StyleBook" placeholder="请输入样书，存储路径"/>
      </FormItem>
      <FormItem label="二级目录id" prop="CategoryId">
        <Input type="text" :maxlength=50 v-model="dictForm.CategoryId" placeholder="请输入二级目录id"/>
      </FormItem>
    </Form>
  </Modal>
</template>
<script>

  import {addBook} from '../../../api/sys/book/book.api'

  export default {
    name: "addBook",
    props: {
      value: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        show: this.value,
        loading: true,
        dictForm: {
          BookName: '',
          Subhead: '',
          Publisher: '',
          Price: '',
          Introduction: '',
          BookThumb: '',
          StyleBook: '',
          CategoryId:''
        },
        dictFormRule: {
          BookName: [
            {required: true, message: '书籍名称不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '书籍名称不能大于50个字符', trigger: 'blur'}
          ],
          Subhead: [
            {required: true, message: '副标题不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '副标题不能大于50个字符', trigger: 'blur'},
            // {
            //   validator: this.check({
            //     response: 'exist'
            //   }), trigger: 'blur'
            // }
          ],
          Publisher: [
            {required: true, message: '出版社不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '出版社不能大于50个字符', trigger: 'blur'}
          ],
          Price: [
            {required: true, message: '价格不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '价格不能大于50个字符', trigger: 'blur'}
          ],
          Introduction: [
            {required: true, message: '简介不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '简介不能大于50个字符', trigger: 'blur'}
          ],
          BookThumb: [
            {required: false, message: '缩略图，存储路径不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '字典数值不能大于50个字符', trigger: 'blur'}
          ],
          StyleBook: [
            {required: false, message: '样书，存储路径不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '字典数值不能大于50个字符', trigger: 'blur'}
          ],
          CategoryId: [
            {required: true, message: '二级目录id不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '字典数值不能大于50个字符', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      ok() {
        this.$refs['dictForm'].validate((valid) => {
          if (valid) {
            addBook(this.dictForm).then(res => {
              if (res.code == 200) {
                this.$Message.success(res.msg);
                // 提交表单数据成功则关闭当前的modal框
                this.closeModal(false);
                // 同时调用父页面的刷新页面的方法
                this.$emit('handleSearch');
              } else {
                this.$Message.error( res.msg);
              }
            })
          } else {
            this.$Message.error('新增数据字典失败');
          }
          setTimeout(() => {
            this.loading = false;
            this.$nextTick(() => {
              this.loading = true;
            });
          }, 1000);
        });
      },
      // check() {
      //   let _this = this;
      //   return function (rule, value, callback) {
      //     let dictType = _this.dictForm.dictType;
      //     let dictCode = value;
      //     checkTypeAndCode({dictType, dictCode}).then(res => {
      //       if (res.obj.success == 'pass') {
      //         callback();
      //       } else {
      //         callback(new Error(_this.$t('dict.checkFail')));
      //       }
      //     });
      //   };
      // },
      closeModal(val) {
        this.$emit('input', val);
      }
    },
    watch: {
      value(val) {
        this.show = val;
      },
      show(val) {
        if (val) {
          this.$refs['dictForm'].resetFields();
        } else {// 反之则关闭页面
          this.closeModal(val);
        }
      }
    }
  }
</script>
