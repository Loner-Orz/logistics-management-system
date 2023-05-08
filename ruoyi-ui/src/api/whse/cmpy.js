import request from '@/utils/request'

// 查询往来公司信息列表
export function listCmpy(query) {
  return request({
    url: '/whse/cmpy/list',
    method: 'get',
    params: query
  })
}

// 查询往来公司信息详细
export function getCmpy(cmpyId) {
  return request({
    url: '/whse/cmpy/' + cmpyId,
    method: 'get'
  })
}

// 新增往来公司信息
export function addCmpy(data) {
  return request({
    url: '/whse/cmpy/add',
    method: 'post',
    data: data
  })
}

// 修改往来公司信息
export function updateCmpy(data) {
  return request({
    url: '/whse/cmpy/edit',
    method: 'put',
    data: data
  })
}

// 删除往来公司信息
export function delCmpy(cmpyId) {
  return request({
    url: '/whse/cmpy/' + cmpyId,
    method: 'delete'
  })
}
