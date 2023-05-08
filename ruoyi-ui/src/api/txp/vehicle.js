import request from '@/utils/request'

// 查询车辆信息列表
export function listVehicle(query) {
  return request({
    url: '/txp/vehicle/list',
    method: 'get',
    params: query
  })
}

// 查询车辆信息详细
export function getVehicle(vehicleId) {
  return request({
    url: '/txp/vehicle/' + vehicleId,
    method: 'get'
  })
}

// 新增车辆信息
export function addVehicle(data) {
  return request({
    url: '/txp/vehicle',
    method: 'post',
    data: data
  })
}

// 修改车辆信息
export function updateVehicle(data) {
  return request({
    url: '/txp/vehicle',
    method: 'put',
    data: data
  })
}

// 删除车辆信息
export function delVehicle(vehicleId) {
  return request({
    url: '/txp/vehicle/' + vehicleId,
    method: 'delete'
  })
}

// 展示车辆信息列表
export function showListVehicle(query) {
  return request({
    url: '/txp/vehicle/showlist',
    method: 'get',
    params: query
  })
}
