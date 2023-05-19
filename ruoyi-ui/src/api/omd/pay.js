import request from '@/utils/request'

// 查询支付信息列表
export function listPay(query) {
  return request({
    url: '/omd/pay/list',
    method: 'get',
    params: query
  })
}
