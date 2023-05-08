
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库名称" prop="storeName">
        <el-select v-model="queryParams.storeName">
          <el-option
            v-for="item in storeOptions"
            :key="item.storeId"
            :label="item.storeName"
            :value="item.storeName"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="仓库负责人" prop="storeConName">
        <el-input
          v-model="queryParams.storeConName"
          placeholder="请输入仓库负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="仓库状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="仓库状态" clearable>
          <el-option
            v-for="dict in dict.type.whse_store_status"
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
          v-hasPermi="['whse:store:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['whse:store:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['whse:store:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['whse:store:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="storeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="仓库ID" align="center" prop="storeId" />
      <el-table-column label="仓库名称" align="center" :show-overflow-tooltip="true" >
        <template slot-scope="scope">
          <router-link :to="'/whse/store-data/index/' + scope.row.storeId" class="link-type">
            <span>{{ scope.row.storeName }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="仓库负责人" align="center" prop="storeConName" />
      <el-table-column label="仓库电话" align="center" prop="storePhone" />
      <el-table-column label="仓库省市区" align="center" prop="provinceName,cityName,areaName">
        <template slot-scope="scope" >
          {{scope.row.provinceName}}{{scope.row.cityName}}{{scope.row.areaName}}
        </template>
      </el-table-column>
      <el-table-column label="仓库详细地址" align="center" prop="storeAddress" />
      <el-table-column label="仓库状态" align="center" prop="status">

        <template slot-scope="scope">
          <dict-tag :options="dict.type.whse_store_status" :value="scope.row.status"/>
        </template>
      </el-table-column>

      <el-table-column label="仓库详细信息" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['whse:store:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['whse:store:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改仓库信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库名称" prop="storeName">
          <el-input v-model="form.storeName" placeholder="请输入仓库名称" />
        </el-form-item>
        <el-form-item label="仓库负责人" prop="storeConName">
          <el-input v-model="form.storeConName" placeholder="请输入仓库负责人" />
        </el-form-item>
        <el-form-item label="仓库电话" prop="storePhone">
          <el-input v-model="form.storePhone" placeholder="请输入仓库电话" />
        </el-form-item>

        <el-form-item label="仓库状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.whse_store_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="仓库省市区">
          <el-cascader
            v-model="value"
            :options="options"
            :props="{ expandTrigger: 'hover'}"
            @change="handleChange"
            placeholder="请选择省市区信息"></el-cascader>
        </el-form-item>

        <el-form-item label="仓库详细地址" prop="storeAddress">
          <el-input v-model="form.storeAddress" placeholder="请输入仓库详细地址" />
        </el-form-item>
        <el-form-item label="仓库详细信息" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listStore, getStore, delStore, addStore, updateStore, optionselect as getStoreOptionselect } from "@/api/whse/store";
import { getListDistrict } from '@/api/district/district'

export default {
  name: "Store",
  dicts:['whse_store_status'],
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
      // 仓库信息表格数据
      storeList: [],
      //仓库下拉列表
      storeOptions: [],
      //初始化省市区
      value:[],
      options:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeName: undefined,
        storeConName: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeName: [
          { required: true, message: "仓库名称不能为空", trigger: "blur" }
        ],
      }
    };
  },


  created() {
    this.getList();
    this.getData();
    this.getStoreList();
  },
  methods: {
    /** 查询仓库信息列表 */
    getList() {
      this.loading = true;
      listStore(this.queryParams).then(response => {
        this.storeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询省市区信息 */
    getData() {
      getListDistrict().then(res => {
        let result = res.data;
        //console.log(result);
        //省数据
        var a1 = result.filter(v => v.level == 1).map(v => ({ value: v.code, label: v.name, pid: v.pid }));
        //市数据
        var a2 = result.filter(v => v.level == 2).map(v => ({ value: v.code, label: v.name, pid: v.pid }));
        //区县数据
        var a3 = result.filter(v => v.level == 3).map(v => ({ value: v.code, label: v.name, pid: v.pid }));
        //关联三级数据
        a2.forEach(item => {
          item.children = a3.filter(v => v.pid == item.value)
        });
        a1.forEach(item => {
          item.children = a2.filter(v => v.pid == item.value)
        });
        //console.log(a1);
        this.options = a1;
      });
    },
    /** 查询字仓库下拉列表 */
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
    /** 省市区选中数据 */
    handleChange(val){
      //console.log(val);
      this.form.areaCode = this.value[2];
      this.form.cityCode = this.value[1];
      this.form.provinceCode = this.value[0];
    },
    // 表单重置
    reset() {
      this.form = {
        storeId: undefined,
        storeName: undefined,
        storeConName: undefined,
        storePhone: undefined,
        storeAddress: undefined,
        status: '0',
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.storeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加仓库信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const storeId = row.storeId || this.ids
      getStore(storeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改仓库信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.storeId != null) {
            updateStore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStore(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const storeIds = row.storeId || this.ids;
      this.$modal.confirm('是否确认删除仓库信息编号为"' + storeIds + '"的数据项？').then(function() {
        return delStore(storeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('whse/store/export', {
        ...this.queryParams
      }, `store_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
