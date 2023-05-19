<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="rasOid">
        <el-select v-model="queryParams.rasOid" placeholder="请选择订单编号" clearable>
          <el-option
            v-for="item in orderOptions"
            :key="item.orderId"
            :label="item.orderId"
            :value="item.orderId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="售后类型" prop="rasStatus">
        <el-select v-model="queryParams.rasStatus" placeholder="请选择售后类型" clearable>
          <el-option
            v-for="dict in dict.type.omd_ras_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="售后状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择售后状态" clearable>
          <el-option
            v-for="dict in dict.type.omd_ras_status"
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
          v-hasPermi="['omd:ras:add']"
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
          v-hasPermi="['omd:ras:edit']"
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
          v-hasPermi="['omd:ras:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['omd:ras:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rasList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="售后编号" align="center" prop="rasId" />
      <el-table-column label="订单编号" align="center" prop="rasOid" />
      <el-table-column label="售后类型" align="center" prop="rasStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.omd_ras_type" :value="scope.row.rasStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="售后具体原因" align="center" prop="rasReason" />
      <el-table-column label="售后状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.omd_ras_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="售后备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['omd:ras:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['omd:ras:remove']"
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

    <!-- 添加或修改售后信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="rasOid">
          <el-select v-model="form.rasOid">
            <el-option
              v-for="item in orderOptions"
              :key="item.orderId"
              :label="item.orderId"
              :value="item.orderId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="售后类型" prop="rasStatus">
          <el-radio-group v-model="form.rasStatus">
            <el-radio
              v-for="dict in dict.type.omd_ras_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="售后具体原因" prop="rasReason">
          <el-input v-model="form.rasReason" placeholder="请输入售后具体原因" />
        </el-form-item>
        <el-form-item label="售后状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.omd_ras_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="售后备注" prop="remark">
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
import { listRas, getRas, delRas, addRas, updateRas} from "@/api/omd/ras";
import {optionselect as getOrderOptionselect } from '@/api/omd/order';


export default {
  name: "Ras",
  dicts: ['omd_ras_status', 'omd_ras_type'],
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
      // 售后信息表格数据
      rasList: [],
      //获取订单编号列表
      orderOptions:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        rasOid: null,
        rasStatus: null,
        rasReason: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        rasOid: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getOrderList();
  },
  methods: {
    /** 查询售后信息列表 */
    getList() {
      this.loading = true;
      listRas(this.queryParams).then(response => {
        this.rasList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询订单编号列表 */
    getOrderList() {
      getOrderOptionselect().then(response => {
        this.orderOptions = response.data;
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
        rasId: null,
        rasOid: null,
        rasStatus: null,
        rasReason: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.rasId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加售后信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const rasId = row.rasId || this.ids
      getRas(rasId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改售后信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.rasId != null) {
            updateRas(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRas(this.form).then(response => {
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
      const rasIds = row.rasId || this.ids;
      this.$modal.confirm('是否确认删除售后信息编号为"' + rasIds + '"的数据项？').then(function() {
        return delRas(rasIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('omd/ras/export', {
        ...this.queryParams
      }, `ras_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
