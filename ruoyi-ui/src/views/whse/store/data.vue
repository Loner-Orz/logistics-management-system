<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库名称" prop="storeName">
        <el-select v-model="queryParams.storeId">
          <el-option
            v-for="item in storeOptions"
            :key="item.storeId"
            :label="item.storeName"
            :value="item.storeId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品名称" prop="commName">
        <el-input
          v-model="queryParams.commName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品类别" prop="commType">
        <el-select v-model="queryParams.commType" placeholder="商品类别" clearable>
          <el-option
            v-for="dict in dict.type.whse_comm_types"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['whse:store_comm:add']"
        >新增商品或增加商品数量</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['whse:store_comm:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['whse:store_comm:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['whse:store_comm:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getCommList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="store_commList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品ID" align="center" prop="commId" />
      <el-table-column label="商品名称" align="center" prop="commName" />
      <el-table-column label="商品单价" align="center" prop="commPrice" />
      <el-table-column label="商品数量" align="center" prop="commCount" />
      <el-table-column label="商品类型" align="center" prop="commType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.whse_comm_types" :value="scope.row.commType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['whse:store_comm:edit']"-->
<!--          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['whse:store_comm:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getCommList"
    />

    <!-- 添加或修改仓库和商品关联对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="commName">
          <el-select v-model="form.commId">
            <el-option
              v-for="item in commOptions"
              :key="item.commId"
              :label="item.commName"
              :value="item.commId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="商品数量" prop="commCount">
          <el-input v-model="form.commCount" placeholder="请输入商品数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import { getStore_comm, selectCommDto, addStore_comm, delStore_comm} from "@/api/whse/storeData";
import { optionselect as getStoreOptionselect } from "@/api/whse/store";
import { optionselect as getCommOptionselect } from "@/api/whse/comm";

export default {
  name: "Store_comm",
  dicts: ['whse_comm_types'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      //仓库列表
      storeOptions:[],
      //商品列表
      commOptions:[],
      // 仓库和商品关联表格数据
      store_commList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeId: null,
        commName: null,
        commType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        commId: [
          { required: true, message: "商品不能为空", trigger: "red" }
        ],
      }
    };
  },
  created() {
    const storeId = this.$route.params && this.$route.params.storeId;
    //this.queryParams.storeId = storeId;
    this.getCommList(storeId);
    this.getStoreList();
  },
  methods: {
    /** 查询仓库和商品关联列表 */
    getCommList(storeId) {
      this.loading = true;
      getStore_comm(storeId).then(response => {
        this.store_commList = response.data;
        this.total = response.data.length;
        //console.log(response);
      });
      this.loading = false;
    },
    /** 查询字仓库列表 */
    getStoreList() {
      getStoreOptionselect().then(response => {
        this.storeOptions = response.data;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        storeId: null,
        commId: null,
        commCount: null,
        commName: null,
        commPrice: null,
        commType: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      if(this.queryParams.storeId != null){
        this.$route.params.storeId = this.queryParams.storeId;
      }
      this.queryParams.pageNum = 1;
      //console.log(this.queryParams);
      selectCommDto(this.queryParams).then(response => {
        this.store_commList = response.rows;
        this.total = response.rows.length;
        //console.log(response);
      });
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.commId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getCommOptionselect().then(response => {
        this.commOptions = response.data;
      });
      this.open = true;
      this.title = "添加商品";
    },
    /** 提交按钮 */
    submitForm() {
      const storeId = this.$route.params && this.$route.params.storeId;
      this.form.storeId = storeId;
      console.log(this.form);
      this.$refs["form"].validate(valid => {
        if (valid) {
          addStore_comm(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getCommList(storeId);
          });
          // if (this.form.commId ！= null) {
          //   updateStore_comm(this.form).then(response => {
          //     this.$modal.msgSuccess("修改成功");
          //     this.open = false;
          //     this.getCommList(storeId);
          //   });
          // } else {
          //   addStore_comm(this.form).then(response => {
          //     this.$modal.msgSuccess("新增成功");
          //     this.open = false;
          //     this.getCommList(storeId);
          //   });
          // }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const nowstoreId = this.$route.params && this.$route.params.storeId;
      const commIds = row.commId || this.ids;
      this.$modal.confirm('是否确认删除？').then(function() {
        return delStore_comm(nowstoreId,commIds);
      }).then(() => {
        this.getCommList(nowstoreId);
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const storeId = this.$route.params && this.$route.params.storeId;
      this.download('whse/store_comm/export/{storeId}', {
        ...this.queryParams,storeId
      }, `store_comm_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
