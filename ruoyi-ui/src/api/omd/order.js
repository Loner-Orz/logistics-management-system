import request from '@/utils/request'

// 查询订单信息列表
export function listOrder(query) {
  return request({
    url: '/omd/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单信息详细
export function getOrder(orderId) {
  return request({
    url: '/omd/order/' + orderId,
    method: 'get'
  })
}

// 新增订单信息
export function addOrder(data) {
  return request({
    url: '/omd/order',
    method: 'post',
    data: data
  })
}

// 修改订单信息
export function updateOrder(data) {
  return request({
    url: '/omd/order',
    method: 'put',
    data: data
  })
}

// 删除订单信息
export function delOrder(orderId) {
  return request({
    url: '/omd/order/' + orderId,
    method: 'delete'
  })
}

// 获取订单编号框列表
export function optionselect() {
  return request({
    url: '/omd/order/optionselect',
    method: 'get'
  })
}
