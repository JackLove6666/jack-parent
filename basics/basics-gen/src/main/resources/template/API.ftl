import request from '@/utils/request'

export function page${className}(query) {
    return request({
        url: '/${className?uncap_first}/page',
        method: 'get',
        params: query
    })
}

export function find${className}(obj) {
    return request({
        url: '/${className?uncap_first}/list',
        method: 'post',
        data: obj
    })
}

export function add${className}(obj) {
    return request({
        url: '/${className?uncap_first}',
        method: 'post',
        data: obj
    })
}

export function get${className}(id) {
    return request({
        url: '/${className?uncap_first}/' + id,
        method: 'get'
    })
}

export function del${className}(id) {
    return request({
        url: '/${className?uncap_first}/' + id,
        method: 'delete'
    })
}

export function put${className}(obj) {
    return request({
        url: '/${className?uncap_first}',
        method: 'put',
        data: obj
    })
}


