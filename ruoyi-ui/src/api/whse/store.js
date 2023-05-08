import request from '@/utils/request'

// 查询仓库信息列表
export function listStore(query) {
  return request({
    url: '/whse/store/list',
    method: 'get',
    params: query
  })
}

// 查询仓库信息详细
export function getStore(storeId) {
  return request({
    url: '/whse/store/' + storeId,
    method: 'get'
  })
}

// 新增仓库信息
export function addStore(data) {
  return request({
    url: '/whse/store/add',
    method: 'post',
    data: data
  })
}

// 修改仓库信息
export function updateStore(data) {
  return request({
    url: '/whse/store/edit',
    method: 'put',
    data: data
  })
}

// 删除仓库信息
export function delStore(storeId) {
  return request({
    url: '/whse/store/' + storeId,
    method: 'delete'
  })
}

// 获取字仓库选择框列表
export function optionselect() {
  return request({
    url: '/whse/store/optionselect',
    method: 'get'
  })
}


