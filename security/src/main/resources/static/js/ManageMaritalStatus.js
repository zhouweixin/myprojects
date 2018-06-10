$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllMaritalStatusInformation()
})
/*
/添加婚姻状况
 */
function addMaritalStatus() {
    var MaritalStatusName = $('#modal-MaritalStatusName').val()
    if(!MaritalStatusName){
        alert("请输入婚姻状况名称！")
        return
    }else{
        var urlStr = ipPort + '/maritalStatus/add?name=' + MaritalStatusName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllMaritalStatusInformation()
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
    $('#modal-modifyMaritalStatusID').val(td.eq(0).text())
    $('#modal-modifyMaritalStatusName').val(td.eq(1).text())
}
/*
修改婚姻状况信息/
 */
function modifyMaritalStatus() {
    var MaritalStatusID = $('#modal-modifyMaritalStatusID').val()
    var MaritalStatusName = $('#modal-modifyMaritalStatusName').val()
    if(!MaritalStatusName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/maritalStatus/update?id='+ MaritalStatusID + "&name=" + MaritalStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("修改婚姻状况信息成功！")
                getAllMaritalStatusInformation()
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
删除婚姻状况信息/
 */
function deleteMaritalStatus(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var MaritalStatusID = td.eq(0).text()
    var urlStr = ipPort + '/maritalStatus/deleteById?id='+ MaritalStatusID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除婚姻状况信息成功！")
                getAllMaritalStatusInformation()
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
批量删除婚姻状况信息/
 */
function deleteMaritalStatusInBatch() {
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
    var urlStr = ipPort + '/maritalStatus/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除婚姻状况信息成功！")
                getAllMaritalStatusInformation()
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
通过名称搜索婚姻状况信息/
 */
function searchMaritalStatusByName() {
    var MaritalStatusName = $('#search-MaritalStatusName').val()
    if(!MaritalStatusName){
        alert('请输入婚姻状况名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/maritalStatus/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + MaritalStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setMaritalStatusTableInformation(obj)
            }else {
                alert("请输入婚姻状况名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部婚姻状况信息/
 */
function getAllMaritalStatusInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/maritalStatus/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setMaritalStatusTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置婚姻状况table信息/
 */
function setMaritalStatusTableInformation(obj) {
    var table_tr = $('.table-tr')
    var MaritalStatus_id = $('.MaritalStatus-id')
    var MaritalStatus_name = $('.MaritalStatus-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        MaritalStatus_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        MaritalStatus_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}