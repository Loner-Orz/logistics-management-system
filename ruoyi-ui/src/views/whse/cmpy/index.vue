<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="公司名称" prop="cmpyName">
        <el-input
          v-model="queryParams.cmpyName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="cmpyConName">
        <el-input
          v-model="queryParams.cmpyConName"
          placeholder="请输入公司负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="公司状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="公司状态" clearable>
          <el-option
            v-for="dict in dict.type.whse_cmpy_status"
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
          v-hasPermi="['whse:cmpy:add']"
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
          v-hasPermi="['whse:cmpy:edit']"
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
          v-hasPermi="['whse:cmpy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['whse:cmpy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cmpyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="往来公司ID" align="center" prop="cmpyId" />
      <el-table-column label="公司名称" align="center" prop="cmpyName" />
      <el-table-column label="公司负责人" align="center" prop="cmpyConName" />
      <el-table-column label="公司电话" align="center" prop="cmpyPhone" />
      <el-table-column label="公司邮箱" align="center" prop="cmpyEmail" />
      <el-table-column label="公司省市区" align="center" prop="provinceName,cityName,areaName">
        <template slot-scope="scope" >
          {{scope.row.provinceName}}{{scope.row.cityName}}{{scope.row.areaName}}
        </template>
      </el-table-column>
      <el-table-column label="公司详细地址" align="center" prop="cmpyAddress" />
      <el-table-column label="公司状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.whse_cmpy_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="公司产品详情" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['whse:cmpy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['whse:cmpy:remove']"
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

    <!-- 添加或修改往来公司信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="cmpyName">
          <el-input v-model="form.cmpyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司负责人" prop="cmpyConName">
          <el-input v-model="form.cmpyConName" placeholder="请输入公司负责人" />
        </el-form-item>
        <el-form-item label="公司电话" prop="cmpyPhone">
          <el-input v-model="form.cmpyPhone" placeholder="请输入公司电话" />
        </el-form-item>
        <el-form-item label="公司邮箱" prop="cmpyEmail">
          <el-input v-model="form.cmpyEmail" placeholder="请输入公司邮箱" />
        </el-form-item>

        <el-form-item label="地址省市区" prop="areaCode,cityCode,provinceCode">
          <el-cascader
            v-model="value"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange"
            placeholder="请选择省市区信息">
          </el-cascader>
        </el-form-item>

        <el-form-item label="公司详细地址" prop="cmpyAddress">
          <el-input v-model="form.cmpyAddress" placeholder="请输入公司详细地址"/>
        </el-form-item>

        <el-form-item label="公司状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.whse_cmpy_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="公司产品详情" prop="remark">
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
import { listCmpy, getCmpy, delCmpy, addCmpy, updateCmpy } from "@/api/whse/cmpy";
import {getListDistrict} from '@/api/district/district'

export default {
  name: "Cmpy",
  dicts:['whse_cmpy_status'],
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
      // 往来公司信息表格数据
      cmpyList: [],
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
        cmpyName: undefined,
        cmpyConName: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cmpyName: [
          { required: true, message: "公司名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getData();
    this.getList();
  },
  methods: {
    /** 查询往来公司信息列表 */
    getList() {
      this.loading = true;
      listCmpy(this.queryParams).then(response => {
        this.cmpyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 获取省市区信息 */
    getData(){
      getListDistrict().then(res=>{
        let result = res.data;
        //console.log(result);
        //省数据
        var a1 = result.filter(v => v.level == 1).map(v => ({value:v.code,label:v.name,pid:v.pid}));
        //市数据
        var a2 = result.filter(v => v.level == 2).map(v => ({value:v.code,label:v.name,pid:v.pid}));
        //区县数据
        var a3 = result.filter(v => v.level == 3).map(v => ({value:v.code,label:v.name,pid:v.pid}));
        //关联三级数据
        a2.forEach(item => {item.children = a3.filter(v => v.pid == item.value)});
        a1.forEach(item => {item.children = a2.filter(v => v.pid == item.value)});
        console.log(a1);
        this.options = a1;
      });
    },
    /** 省市区选中数据 */
    handleChange(val){
      console.log(val);
      this.form.areaCode = this.value[2];
      this.form.cityCode = this.value[1];
      this.form.provinceCode = this.value[0];
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        cmpyId: undefined,
        cmpyName: undefined,
        cmpyConName: undefined,
        cmpyPhone: undefined,
        cmpyEmail: undefined,
        cmpyAddress: undefined,
        status: "0",
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
      this.ids = selection.map(item => item.cmpyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加往来公司信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cmpyId = row.cmpyId || this.ids
      getCmpy(cmpyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改往来公司信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cmpyId != null) {
            updateCmpy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCmpy(this.form).then(response => {
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
      const cmpyIds = row.cmpyId || this.ids;
      this.$modal.confirm('是否确认删除往来公司信息编号为"' + cmpyIds + '"的数据项？').then(function() {
        return delCmpy(cmpyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('whse/cmpy/export', {
        ...this.queryParams
      }, `cmpy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
