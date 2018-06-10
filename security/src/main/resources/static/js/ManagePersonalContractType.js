$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllPersonalContractTypeInformation()
})
/*
/添加合同类型
 */
function addPersonalContractType() {
    var PersonalContractTypeName = $('#modal-PersonalContractTypeName').val()
    if(!PersonalContractTypeName){
        alert("请输入合同类型名称！")
        return
    }else{
        var urlStr = ipPort + '/contractType/add?name=' + PersonalContractTypeName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                console.log(obj)
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllPersonalContractTypeInformation()
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
    $('#modal-modifyPersonalContractTypeID').val(td.eq(0).text())
    $('#modal-modifyPersonalContractTypeName').val(td.eq(1).text())
}
/*
修改合同类型信息/
 */
function modifyPersonalContractType() {
    var PersonalContractTypeID = $('#modal-modifyPersonalContractTypeID').val()
    var PersonalContractTypeName = $('#modal-modifyPersonalContractTypeName').val()
    if(!PersonalContractTypeName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/contractType/update?id='+ PersonalContractTypeID + "&name=" + PersonalContractTypeName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 8){
                alert(obj.message)
            }
            else if(obj.code == 0){
                alert("修改合同类型信息成功！")
                getAllPersonalContractTypeInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除合同类型信息/
 */
function deletePersonalContractType(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var PersonalContractTypeID = td.eq(0).text()
    var urlStr = ipPort + '/contractType/deleteById?id='+ PersonalContractTypeID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除合同类型信息成功！")
                getAllPersonalContractTypeInformation()
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
批量删除合同类型信息/
 */
function deletePersonalContractTypeInBatch() {
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
    var urlStr = ipPort + '/contractType/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除合同类型信息成功！")
                getAllPersonalContractTypeInformation()
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
通过名称搜索合同类型信息/
 */
function searchPersonalContractTypeByName() {
    var PersonalContractTypeName = $('#search-PersonalContractTypeName').val()
    if(!PersonalContractTypeName){
        alert('请输入合同类型名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/contractType/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + PersonalContractTypeName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setPersonalContractTypeTableInformation(obj)
            }else {
                alert("请输入合同类型名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部合同类型信息/
 */
function getAllPersonalContractTypeInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/contractType/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setPersonalContractTypeTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置合同类型table信息/
 */
function setPersonalContractTypeTableInformation(obj) {
    var table_tr = $('.table-tr')
    var PersonalContractType_id = $('.personalContractType-id')
    var PersonalContractType_name = $('.personalContractType-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        PersonalContractType_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        PersonalContractType_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}