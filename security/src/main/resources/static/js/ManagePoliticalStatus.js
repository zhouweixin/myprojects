$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllPoliticalStatusInformation()
})
/*
/添加政治面貌
 */
function addPoliticalStatus() {
    var PoliticalStatusName = $('#modal-PoliticalStatusName').val()
    if(!PoliticalStatusName){
        alert("请输入政治面貌名称！")
        return
    }else{
        var urlStr = ipPort + '/politicalStatus/add?name=' + PoliticalStatusName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                console.log(obj)
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllPoliticalStatusInformation()
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
    $('#modal-modifyPoliticalStatusID').val(td.eq(0).text())
    $('#modal-modifyPoliticalStatusName').val(td.eq(1).text())
}
/*
修改政治面貌信息/
 */
function modifyPoliticalStatus() {
    var PoliticalStatusID = $('#modal-modifyPoliticalStatusID').val()
    var PoliticalStatusName = $('#modal-modifyPoliticalStatusName').val()
    if(!PoliticalStatusName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/politicalStatus/update?id='+ PoliticalStatusID + "&name=" + PoliticalStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 8){
                alert(obj.message)
            }
            else if(obj.code == 0){
                alert("修改政治面貌信息成功！")
                getAllPoliticalStatusInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除政治面貌信息/
 */
function deletePoliticalStatus(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var PoliticalStatusID = td.eq(0).text()
    var urlStr = ipPort + '/politicalStatus/deleteById?id='+ PoliticalStatusID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除政治面貌信息成功！")
                getAllPoliticalStatusInformation()
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
批量删除政治面貌信息/
 */
function deletePoliticalStatusInBatch() {
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
    var urlStr = ipPort + '/politicalStatus/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除政治面貌信息成功！")
                getAllPoliticalStatusInformation()
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
通过名称搜索政治面貌信息/
 */
function searchPoliticalStatusByName() {
    var PoliticalStatusName = $('#search-PoliticalStatusName').val()
    if(!PoliticalStatusName){
        alert('请输入政治面貌名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/politicalStatus/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + PoliticalStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setPoliticalStatusTableInformation(obj)
            }else {
                alert("请输入政治面貌名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部政治面貌信息/
 */
function getAllPoliticalStatusInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/politicalStatus/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setPoliticalStatusTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置政治面貌table信息/
 */
function setPoliticalStatusTableInformation(obj) {
    var table_tr = $('.table-tr')
    var PoliticalStatus_id = $('.PoliticalStatus-id')
    var PoliticalStatus_name = $('.PoliticalStatus-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        PoliticalStatus_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        PoliticalStatus_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}