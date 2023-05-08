import request from '@/utils/request'

// 查询商品信息列表
export function listComm(query) {
  return request({
    url: '/whse/comm/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getComm(commId) {
  return request({
    url: '/whse/comm/' + commId,
    method: 'get'
  })
}

// 新增商品信息
export function addComm(data) {
  return request({
    url: '/whse/comm',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateComm(data) {
  return request({
    url: '/whse/comm',
    method: 'put',
    data: data
  })
}

// 删除商品信息
export function delComm(commId) {
  return request({
    url: '/whse/comm/' + commId,
    method: 'delete'
  })
}

// 获取商品选择框列表
export function optionselect() {
  return request({
    url: '/whse/comm/optionselect',
    method: 'get'
  })
}
