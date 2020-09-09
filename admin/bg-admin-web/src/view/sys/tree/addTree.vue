<template>
  <Modal v-model="show" title="增加菜单" @on-ok="ok" :loading="loading" :mask-closable="false">
    <Form ref="treeFormName" :model="treeForm" :rules="treeFormRule">
      <FormItem label="菜单名称">
        <Input type="text" v-model="treeForm.parentTreeName" placeholder="请输入菜单名称"/>
      </FormItem>
      <FormItem label="ParentId">
        <Input type="text" v-model="treeForm.parentTreeId" placeholder="增加一级菜单请输入0;增加二级菜单请输入左侧一级菜单序列"/>
      </FormItem>
    </Form>
  </Modal>
</template>
<script>
  import {addFirstTree} from "../../../api/sys/tree/tree.api"

  export default {
    name: "addTree",
    props: {
      value: {
        type: Boolean,
        default: false
      },
      parentTreeName: {
        type: String
      },
      parentTreeId: {
        type: String
      }
    },
    data() {
      return {
        show: this.value,
        loading: true,
        treeForm: {
          parentTreeName: '',
          parentTreeId:''
        },
        treeFormRule: {
          treeName: [
            {required: true, message: '菜单名称不能为空！', trigger: 'blur'},
            {type: 'string', max: 50, message: '菜单名称最大长度为50.', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      ok() {
        var firstName = this.treeForm.parentTreeName
        var parentTreeId = this.treeForm.parentTreeId
        addFirstTree({firstName,parentTreeId}).then(res => {
          if (res.code == 200) {
            this.$Message.success('新增一级菜单节点成功');
            // 提交表单数据成功则关闭当前的modal框
            this.closeModal(false);
            // 同时调用父页面的刷新页面的方法
            this.$emit('reloadTree');
          } else {
            this.$Message.error( res.msg);
          }
        })
        setTimeout(() => {
          this.loading = false;
          this.$nextTick(() => {
            this.loading = true;
          });
        }, 1000);
      },
      closeModal(val) {
        this.$emit('input', val);
      }
    },
    watch: {
      value(val) {
        this.show = val;
      },
      show(val) {
        //当重新显示增加数据的时候重置整个form表单
        if (val) {
          this.$refs['treeFormName'].resetFields();
          this.treeForm.parentTreeName = this.parentTreeName;
        } else {// 反之则关闭页面
          this.closeModal(val);
        }
      }
    }
  }

</script>
