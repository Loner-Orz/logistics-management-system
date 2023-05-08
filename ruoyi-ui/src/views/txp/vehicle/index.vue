<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="vehicleIpn">
        <el-input
          v-model="queryParams.vehicleIpn"
          placeholder="请输入车牌号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库名称" prop="storeName" >
        <el-select v-model="queryParams.vehicleSid" clearable>
          <el-option
            v-for="item in storeOptions"
            :key="item.storeId"
            :label="item.storeName"
            :value="item.storeId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆类型" prop="vehicleType">
        <el-select v-model="queryParams.vehicleType" placeholder="请选择车辆类型" clearable>
          <el-option
            v-for="dict in dict.type.txp_vehicle_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆状态" prop="vehicleState">
        <el-select v-model="queryParams.vehicleState" placeholder="请选择车辆状态" clearable>
          <el-option
            v-for="dict in dict.type.txp_vehicle_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.txp_vehicle_status"
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
          v-hasPermi="['txp:vehicle:add']"
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
          v-hasPermi="['txp:vehicle:edit']"
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
          v-hasPermi="['txp:vehicle:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['txp:vehicle:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vehicleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车辆ID" align="center" prop="vehicleId" />
      <el-table-column label="车牌号" align="center" prop="vehicleIpn" />
      <el-table-column label="所属仓库" align="center" prop="vehicleSid" />
      <el-table-column label="车辆类型" align="center" prop="vehicleType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.txp_vehicle_type" :value="scope.row.vehicleType"/>
        </template>
      </el-table-column>
      <el-table-column label="车辆状态" align="center" prop="vehicleState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.txp_vehicle_state" :value="scope.row.vehicleState"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.txp_vehicle_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="车辆备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['txp:vehicle:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['txp:vehicle:remove']"
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

    <!-- 添加或修改车辆信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="vehicleIpn">
          <el-input v-model="form.vehicleIpn" placeholder="请输入车牌号" />
        </el-form-item>
          <el-form-item label="所属仓库" prop="vehicleSid">
            <el-select v-model="form.vehicleSid" placeholder="请选择仓库">
              <el-option
                v-for="item in storeOptions"
                :key="item.storeId"
                :label="item.storeName"
                :value="item.storeId"
                :disabled="item.status == 1"
              ></el-option>
            </el-select>
          </el-form-item>
        <el-form-item label="车辆类型" prop="vehicleType">
          <el-select v-model="form.vehicleType" placeholder="请选择车辆类型">
            <el-option
              v-for="dict in dict.type.txp_vehicle_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆状态" prop="vehicleState">
          <el-select v-model="form.vehicleState" placeholder="请选择车辆状态">
            <el-option
              v-for="dict in dict.type.txp_vehicle_state"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.txp_vehicle_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆备注" prop="remark">
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
import { addVehicle, delVehicle, getVehicle, listVehicle, updateVehicle} from '@/api/txp/vehicle'
import { listStore, getStore, optionselect as getStoreOptionselect } from '@/api/whse/store'

export default {
  name: "Vehicle",
  dicts: ['txp_vehicle_type', 'txp_vehicle_state', 'txp_vehicle_status'],
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
      // 车辆信息表格数据
      vehicleList: [],
      //仓库选项
      storeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleIpn: null,
        vehicleSid: null,
        vehicleType: null,
        vehicleState: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vehicleSid: [
          { required: true, message: "所属仓库不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getStoreList();
  },
  methods: {
    /** 查询车辆信息列表 */
    getList() {
      this.loading = true;
      listVehicle(this.queryParams).then(response => {
        this.vehicleList = response.rows;
        this.vehicleList.forEach(V => {
            const store = getStore(V.vehicleSid)
              .then(S => S.data.storeName)
              .then(function(data) {
                V.vehicleSid = String(data);// 将仓库名称赋值给vehicleSid属性
              }); // 获取仓库信息
        });
        this.total = response.total;
        this.loading = false;
      });
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
        vehicleId: null,
        vehicleIpn: null,
        vehicleSid: null,
        vehicleType: null,
        vehicleState: null,
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
      console.log(this.queryParams);
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.vehicleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      listStore().then(response => {
        this.storeOptions = response.rows;
        this.open = true;
        this.title = "添加车辆";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vehicleId = row.vehicleId || this.ids
      getVehicle(vehicleId).then(response => {
        this.form = response.data;
        listStore().then(response => {
          this.storeOptions = response.rows;
          this.$set(this.form, "vehicleSid", response.vehicleSid);
        });
        this.open = true;
        this.title = "修改车辆信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vehicleId != null) {
            updateVehicle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVehicle(this.form).then(response => {
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
      const vehicleIds = row.vehicleId || this.ids;
      this.$modal.confirm('是否确认删除车辆信息编号为"' + vehicleIds + '"的数据项？').then(function() {
        return delVehicle(vehicleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('txp/vehicle/export', {
        ...this.queryParams
      }, `vehicle_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
