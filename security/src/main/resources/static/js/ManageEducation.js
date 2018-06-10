$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllEducationInformation()
})
/*
/添加学历
 */
function addEducation() {
    var EducationName = $('#modal-EducationName').val()
    if(!EducationName){
        alert("请输入学历名称！")
        return
    }else{
        var urlStr = ipPort + '/education/add?name=' + EducationName
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 8){
                    alert(obj.message)
                }else{
                    alert(obj.message)
                    getAllEducationInformation()
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
    $('#modal-modifyEducationID').val(td.eq(0).text())
    $('#modal-modifyEducationName').val(td.eq(1).text())
}
/*
修改学历信息/
 */
function modifyEducation() {
    var EducationID = $('#modal-modifyEducationID').val()
    var EducationName = $('#modal-modifyEducationName').val()
    if(!EducationName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/education/update?id='+ EducationID + "&name=" + EducationName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("修改学历信息成功！")
                getAllEducationInformation()
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
删除学历信息/
 */
function deleteEducation(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var EducationID = td.eq(0).text()
    var urlStr = ipPort + '/education/deleteById?id='+ EducationID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除学历信息成功！")
                getAllEducationInformation()
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
批量删除学历信息/
 */
function deleteEducationInBatch() {
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
    var urlStr = ipPort + '/education/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除学历信息成功！")
                getAllEducationInformation()
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
通过名称搜索学历信息/
 */
function searchEducationByName() {
    var EducationName = $('#search-EducationName').val()
    if(!EducationName){
        alert('请输入学历名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/education/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + EducationName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setEducationTableInformation(obj)
            }else {
                alert("请输入学历名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部学历信息/
 */
function getAllEducationInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/education/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setEducationTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置学历table信息/
 */
function setEducationTableInformation(obj) {
    var table_tr = $('.table-tr')
    var Education_id = $('.Education-id')
    var Education_name = $('.Education-name')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        Education_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        Education_name.eq(i).text(obj.data.content[i].name)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}