$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllJobNatureInformation()
})
/*
/添加工作性质
 */
function addJobNature() {
    var jobNatureName = $('#modal-jobNatureName').val()
    if(!jobNatureName){
        alert("请输入工作性质名称！")
        return
    }else{
        var urlStr = ipPort + '/jobNature/add?name=' + jobNatureName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                console.log(obj)
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllJobNatureInformation()
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
    $('#modal-modifyJobNatureID').val(td.eq(0).text())
    $('#modal-modifyJobNatureName').val(td.eq(1).text())
}
/*
修改工作性质信息/
 */
function modifyJobNature() {
    var jobNatureID = $('#modal-modifyJobNatureID').val()
    var jobNatureName = $('#modal-modifyJobNatureName').val()
    if(!jobNatureName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/jobNature/update?id='+ jobNatureID + "&name=" + jobNatureName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 8){
                alert(obj.message)
            }
            else if(obj.code == 0){
                alert("修改工作性质信息成功！")
                getAllJobNatureInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除工作性质信息/
 */
function deleteJobNature(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var jobNatureID = td.eq(0).text()
    var urlStr = ipPort + '/jobNature/deleteById?id='+ jobNatureID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除工作性质信息成功！")
                getAllJobNatureInformation()
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
批量删除工作性质信息/
 */
function deleteJobNatureInBatch() {
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
    var urlStr = ipPort + '/jobNature/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除工作性质信息成功！")
                getAllJobNatureInformation()
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
通过名称搜索工作性质信息/
 */
function searchJobNatureByName() {
    var jobNatureName = $('#search-jobNatureName').val()
    if(!jobNatureName){
        alert('请输入工作性质名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/jobNature/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + jobNatureName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setJobNatureTableInformation(obj)
            }else {
                alert("请输入工作性质名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部工作性质信息/
 */
function getAllJobNatureInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/jobNature/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setJobNatureTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置工作性质table信息/
 */
function setJobNatureTableInformation(obj) {
    var table_tr = $('.table-tr')
    var jobNature_id = $('.jobNature-id')
    var jobNature_name = $('.jobNature-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        jobNature_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        jobNature_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}