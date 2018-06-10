$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllMilitaryStatusInformation()
})
/*
/添加兵役状况
 */
function addMilitaryStatus() {
    var MilitaryStatusName = $('#modal-MilitaryStatusName').val()
    if(!MilitaryStatusName){
        alert("请输入兵役状况名称！")
        return
    }else{
        var urlStr = ipPort + '/militaryStatus/add?name=' + MilitaryStatusName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllMilitaryStatusInformation()
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
    $('#modal-modifyMilitaryStatusID').val(td.eq(0).text())
    $('#modal-modifyMilitaryStatusName').val(td.eq(1).text())
}
/*
修改兵役状况信息/
 */
function modifyMilitaryStatus() {
    var MilitaryStatusID = $('#modal-modifyMilitaryStatusID').val()
    var MilitaryStatusName = $('#modal-modifyMilitaryStatusName').val()
    if(!MilitaryStatusName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/militaryStatus/update?id='+ MilitaryStatusID + "&name=" + MilitaryStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("修改兵役状况信息成功！")
                getAllMilitaryStatusInformation()
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
删除兵役状况信息/
 */
function deleteMilitaryStatus(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var MilitaryStatusID = td.eq(0).text()
    var urlStr = ipPort + '/militaryStatus/deleteById?id='+ MilitaryStatusID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除兵役状况信息成功！")
                getAllMilitaryStatusInformation()
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
批量删除兵役状况信息/
 */
function deleteMilitaryStatusInBatch() {
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
    var urlStr = ipPort + '/militaryStatus/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除兵役状况信息成功！")
                getAllMilitaryStatusInformation()
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
通过名称搜索兵役状况信息/
 */
function searchMilitaryStatusByName() {
    var MilitaryStatusName = $('#search-MilitaryStatusName').val()
    if(!MilitaryStatusName){
        alert('请输入兵役状况名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/militaryStatus/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + MilitaryStatusName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setMilitaryStatusTableInformation(obj)
            }else {
                alert("请输入兵役状况名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部兵役状况信息/
 */
function getAllMilitaryStatusInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/militaryStatus/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setMilitaryStatusTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置兵役状况table信息/
 */
function setMilitaryStatusTableInformation(obj) {
    var table_tr = $('.table-tr')
    var MilitaryStatus_id = $('.MilitaryStatus-id')
    var MilitaryStatus_name = $('.MilitaryStatus-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        MilitaryStatus_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        MilitaryStatus_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}