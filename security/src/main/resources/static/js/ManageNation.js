$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllNationInformation()
})
/*
/添加民族
 */
function addNation() {
    var NationName = $('#modal-NationName').val()
    if(!NationName){
        alert("请输入民族名称！")
        return
    }else{
        var urlStr = ipPort + '/nation/add?name=' + NationName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                console.log(obj)
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllNationInformation()
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
    $('#modal-modifyNationID').val(td.eq(0).text())
    $('#modal-modifyNationName').val(td.eq(1).text())
}
/*
修改民族信息/
 */
function modifyNation() {
    var NationID = $('#modal-modifyNationID').val()
    var NationName = $('#modal-modifyNationName').val()
    if(!NationName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/nation/update?id='+ NationID + "&name=" + NationName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 8){
                alert(obj.message)
            }
            else if(obj.code == 0){
                alert("修改民族信息成功！")
                getAllNationInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除民族信息/
 */
function deleteNation(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var NationID = td.eq(0).text()
    var urlStr = ipPort + '/nation/deleteById?id='+ NationID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除民族信息成功！")
                getAllNationInformation()
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
批量删除民族信息/
 */
function deleteNationInBatch() {
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
    var urlStr = ipPort + '/nation/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除民族信息成功！")
                getAllNationInformation()
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
通过名称搜索民族信息/
 */
function searchNationByName() {
    var NationName = $('#search-NationName').val()
    if(!NationName){
        alert('请输入民族名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/nation/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + NationName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setNationTableInformation(obj)
            }else {
                alert("请输入民族名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部民族信息/
 */
function getAllNationInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/nation/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setNationTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置民族table信息/
 */
function setNationTableInformation(obj) {
    var table_tr = $('.table-tr')
    var Nation_id = $('.nation-id')
    var Nation_name = $('.nation-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        Nation_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        Nation_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}