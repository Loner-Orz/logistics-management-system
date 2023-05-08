<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单单号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家姓名" prop="orderBname">
        <el-input
          v-model="queryParams.orderBname"
          placeholder="请输入买家姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="商品ID" prop="orderWid">
        <el-input
          v-model="queryParams.orderWid"
          placeholder="请输入商品ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="仓库ID" prop="orderSid">
        <el-input
          v-model="queryParams.orderSid"
          placeholder="请输入仓库ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付ID" prop="orderPid">
        <el-input
          v-model="queryParams.orderPid"
          placeholder="请输入支付ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物流ID" prop="orderLid">
        <el-input
          v-model="queryParams.orderLid"
          placeholder="请输入物流ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单类型" prop="orderType">
        <el-select v-model="queryParams.orderType" placeholder="请选择订单类型" clearable>
          <el-option
            v-for="dict in dict.type.omd_order_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.omd_order_status"
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
          v-hasPermi="['omd:order:add']"
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
          v-hasPermi="['omd:order:edit']"
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
          v-hasPermi="['omd:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['omd:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单单号" align="center" prop="orderId" />
      <el-table-column label="买家姓名" align="center" prop="orderBname" />
      <el-table-column label="联系电话" align="center" prop="orderPhone" />
      <el-table-column label="地址省市区" align="center" prop="provinceName,cityName,areaName">
        <template slot-scope="scope" >
          {{scope.row.provinceName}}{{scope.row.cityName}}{{scope.row.areaName}}
        </template>
      </el-table-column>
      <el-table-column label="买家详细地址" align="center" prop="drderAddress" />
      <el-table-column label="商品ID" align="center" prop="orderWid" />
      <el-table-column label="商品数量" align="center" prop="orderWcount" />
      <el-table-column label="仓库ID" align="center" prop="orderSid" />
      <el-table-column label="支付ID" align="center" prop="orderPid" />
      <el-table-column label="物流ID" align="center" prop="orderLid" />
      <el-table-column label="订单类型" align="center" prop="orderType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.omd_order_type" :value="scope.row.orderType"/>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.omd_order_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="订单备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['omd:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['omd:order:remove']"
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

    <!-- 添加或修改订单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="买家姓名" prop="orderBname">
          <el-input v-model="form.orderBname" placeholder="请输入买家姓名" />
        </el-form-item>
        <el-form-item label="联系电话" prop="orderPhone">
          <el-input v-model="form.orderPhone" placeholder="请输入联系电话" />
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
        <el-form-item label="买家详细地址" prop="drderAddress">
          <el-input v-model="form.drderAddress" placeholder="请输入买家详细地址" />
        </el-form-item>
        <el-form-item label="商品ID" prop="orderWid">
          <el-input v-model="form.orderWid" placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="商品数量" prop="orderWcount">
          <el-input v-model="form.orderWcount" placeholder="请输入商品数量" />
        </el-form-item>
        <el-form-item label="仓库ID" prop="orderSid">
          <el-input v-model="form.orderSid" placeholder="请输入仓库ID" />
        </el-form-item>
        <el-form-item label="订单类型" prop="orderType">
          <el-select v-model="form.orderType" placeholder="请选择订单类型">
            <el-option
              v-for="dict in dict.type.omd_order_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单备注" prop="remark">
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/omd/order";
import {getListDistrict} from '@/api/district/district'

export default {
  name: "Order",
  dicts: ['omd_order_status', 'omd_order_type'],
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
      // 订单信息表格数据
      orderList: [],
      //初始化省市区
      value:[],
      options:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 订单备注时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        orderBname: null,
        orderWid: null,
        orderSid: null,
        orderPid: null,
        orderLid: null,
        orderType: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderBname: [
          { required: true, message: "买家姓名不能为空", trigger: "blur" }
        ],
        drderAddress: [
          { required: true, message: "买家详细地址不能为空", trigger: "blur" }
        ],
        orderWid: [
          { required: true, message: "商品ID不能为空", trigger: "blur" }
        ],
        orderWcount: [
          { required: true, message: "商品数量不能为空", trigger: "blur" }
        ],
        orderSid: [
          { required: true, message: "仓库ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getData();
    this.getList();
  },
  methods: {
    /** 查询订单信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        orderId: null,
        orderBname: null,
        orderPhone: null,
        drderAddress: null,
        orderWid: null,
        orderWcount: null,
        orderSid: null,
        orderPid: null,
        orderLid: null,
        orderType: null,
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
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除订单信息编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('omd/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
