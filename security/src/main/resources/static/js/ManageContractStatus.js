$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllContractStatusInformation()
})
/*
/添加合同状态
 */
function addContractStatus() {
    var ContractStatusName = $('#modal-ContractStatusName').val()
    if(!ContractStatusName){
        alert("请输入合同状态名称！")
        return
    }else{
        var urlStr = ipPort + '/contractStatus/add?name=' + ContractStatusName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                console.log(obj)
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllContractStatusInformation()
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
    $('#modal-modifyContractStatusID').val(td.eq(0).text())
    $('#modal-modifyContractStatusName').val(td.eq(1).text())
}
/*
修改合同状态信息/
 */
function modifyContractStatus() {
    var ContractStatusID = $('#modal-modifyContractStatusID').val()
    var ContractStatusName = $('#modal-modifyContractStatusName').val()
    if(!ContractStatusName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/contractStatus/update?id='+ ContractStatusID + "&name=" + ContractStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 8){
                alert(obj.message)
            }
            else if(obj.code == 0){
                alert("修改合同状态信息成功！")
                getAllContractStatusInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除合同状态信息/
 */
function deleteContractStatus(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var ContractStatusID = td.eq(0).text()
    var urlStr = ipPort + '/contractStatus/deleteById?id='+ ContractStatusID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除合同状态信息成功！")
                getAllContractStatusInformation()
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
批量删除合同状态信息/
 */
function deleteContractStatusInBatch() {
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
    var urlStr = ipPort + '/contractStatus/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除合同状态信息成功！")
                getAllContractStatusInformation()
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
通过名称搜索合同状态信息/
 */
function searchContractStatusByName() {
    var ContractStatusName = $('#search-ContractStatusName').val()
    if(!ContractStatusName){
        alert('请输入合同状态名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/contractStatus/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + ContractStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setContractStatusTableInformation(obj)
            }else {
                alert("请输入合同状态名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部合同状态信息/
 */
function getAllContractStatusInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/contractStatus/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setContractStatusTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置合同状态table信息/
 */
function setContractStatusTableInformation(obj) {
    var table_tr = $('.table-tr')
    var ContractStatus_id = $('.contractStatus-id')
    var ContractStatus_name = $('.contractStatus-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        ContractStatus_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        ContractStatus_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}