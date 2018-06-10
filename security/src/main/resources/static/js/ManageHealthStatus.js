$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllHealthStatusInformation()
})
/*
/添加健康状况
 */
function addHealthStatus() {
    var HealthStatusName = $('#modal-HealthStatusName').val()
    if(!HealthStatusName){
        alert("请输入健康状况名称！")
        return
    }else{
        var urlStr = ipPort + '/healthStatus/add?name=' + HealthStatusName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllHealthStatusInformation()
                }
            },
            error:function (error) {
                console.log(error)
            }
        })
    }
}
/*
设置修改面板信息/
 */
function setModifyModalInformation(obj) {
    var td = $(obj).parent().parent().find('td')
    $('#modal-modifyHealthStatusID').val(td.eq(0).text())
    $('#modal-modifyHealthStatusName').val(td.eq(1).text())
}
/*
修改健康状况信息/
 */
function modifyHealthStatus() {
    var HealthStatusID = $('#modal-modifyHealthStatusID').val()
    var HealthStatusName = $('#modal-modifyHealthStatusName').val()
    if(!HealthStatusName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/healthStatus/update?id='+ HealthStatusID + "&name=" + HealthStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("修改健康状况信息成功！")
                getAllHealthStatusInformation()
            }
            else {
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除健康状况信息/
 */
function deleteHealthStatus(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var HealthStatusID = td.eq(0).text()
    var urlStr = ipPort + '/healthStatus/deleteById?id='+ HealthStatusID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除健康状况信息成功！")
                getAllHealthStatusInformation()
            }else {
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
批量删除健康状况信息/
 */
function deleteHealthStatusInBatch() {
    var select_sub_box = $('.select-sub-box')
    var jsonArr = []
    for(var i = 0; i < select_sub_box.length; i++){
        if(select_sub_box.eq(i).is(':checked') == true){
            var json = {}
            json['id'] = parseInt(select_sub_box.eq(i).attr('value'));
            jsonArr.push(json)
        }
    }
    let myjson = JSON.stringify(jsonArr)
    var urlStr = ipPort + '/healthStatus/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除健康状况信息成功！")
                getAllHealthStatusInformation()
            }else {
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
通过名称搜索健康状况信息/
 */
function searchHealthStatusByName() {
    var HealthStatusName = $('#search-HealthStatusName').val()
    if(!HealthStatusName){
        alert('请输入健康状况名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/healthStatus/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + HealthStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setHealthStatusTableInformation(obj)
            }else {
                alert("请输入健康状况名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部健康状况信息/
 */
function getAllHealthStatusInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/healthStatus/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setHealthStatusTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置健康状况table信息/
 */
function setHealthStatusTableInformation(obj) {
    var table_tr = $('.table-tr')
    var HealthStatus_id = $('.HealthStatus-id')
    var HealthStatus_name = $('.HealthStatus-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        HealthStatus_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        HealthStatus_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}