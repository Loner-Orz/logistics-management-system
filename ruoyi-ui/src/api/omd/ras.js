import request from '@/utils/request'

// 查询售后信息列表
export function listRas(query) {
  return request({
    url: '/omd/ras/list',
    method: 'get',
    params: query
  })
}

// 查询售后信息详细
export function getRas(rasId) {
  return request({
    url: '/omd/ras/' + rasId,
    method: 'get'
  })
}

// 新增售后信息
export function addRas(data) {
  return request({
    url: '/omd/ras',
    method: 'post',
    data: data
  })
}

// 修改售后信息
export function updateRas(data) {
  return request({
    url: '/omd/ras',
    method: 'put',
    data: data
  })
}

// 删除售后信息
export function delRas(rasId) {
  return request({
    url: '/omd/ras/' + rasId,
    method: 'delete'
  })
}


