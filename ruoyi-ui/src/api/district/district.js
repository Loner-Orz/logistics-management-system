import request from '@/utils/request'

// 查询地区信息列表
export function getListDistrict(query) {
  return request({
    url: '/district/getDistrict',
    method: 'get',
    params: query
  })
}
