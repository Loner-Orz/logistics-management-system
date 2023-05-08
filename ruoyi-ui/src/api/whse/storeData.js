import request from '@/utils/request'

// 查询仓库和商品关联详细
export function getStore_comm(storeId) {
  return request({
    url: '/whse/store_comm/' + storeId,
    method: 'get'
  })
}

// 查询商品详细信息
export function selectCommDto(query) {
  return request({
    url: '/whse/store_comm/list',
    method: 'get',
    params: query
  })
}

// 新增商品
export function addStore_comm(data) {
  return request({
    url: '/whse/store_comm',
    method: 'post',
    data: data
  })
}

// 删除商品信息
export function delStore_comm(storeId,commIds) {
  return request({
    url: '/whse/store_comm/' +commIds+'/'+storeId,
    method: 'delete'
  })
}

