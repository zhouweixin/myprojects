$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllDepartmentInformation()
})
/*
/添加部门
 */
function addDepartment() {
    var departmentName = $('#modal-departmentName').val()
    var departmentShortName = $('#modal-departmentShortName').val()
    var departmentDescription = $('#modal-departmentDescription').val()
    if(!departmentName){
        alert("请输入部门名称！")
        return
    }else{
        var urlStr = ipPort + '/department/add?name=' + departmentName + '&shortName=' + departmentShortName + '&description=' + departmentDescription
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 8){
                    alert('新增失败, 部门简称只能是唯一的2位字母！')
                }else{
                    alert(obj.message)
                    getAllDepartmentInformation()
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
    $('#modal-modifyDepartmentID').val(td.eq(0).text())
    $('#modal-modifyDepartmentName').val(td.eq(1).text())
    $('#modal-modifyDepartmentShortName').val(td.eq(2).text())
    $('#modal-modifyDepartmentDescription').val(td.eq(3).text())
}
/*
修改部门信息/
 */
function modifyDepartment() {
    var departmentID = $('#modal-modifyDepartmentID').val()
    var departmentName = $('#modal-modifyDepartmentName').val()
    var departmentShortName = $('#modal-modifyDepartmentShortName').val()
    var departmentDescription = $('#modal-modifyDepartmentDescription').val()
    var urlStr = ipPort + '/department/update?id='+ departmentID + "&name=" + departmentName + "&shortName=" + departmentShortName + "&description=" + departmentDescription
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 8){
                alert('修改失败, 部门简称只能是唯一的2位字母！')
            }
            else if(obj.code == 0){
                alert("修改部门信息成功！")
                getAllDepartmentInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除部门信息/
 */
function deleteDepartment(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var departmentID = td.eq(0).text()
    var urlStr = ipPort + '/department/deleteById?id='+ departmentID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除部门信息成功！")
                getAllDepartmentInformation()
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
批量删除部门信息/
 */
function deleteDepartmentInBatch() {
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
    var urlStr = ipPort + '/department/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除部门信息成功！")
                getAllDepartmentInformation()
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
通过ID搜索部门信息/
 */
function searchDepartmentByID() {
    var departmentID = $('#search-departmentID').val()
    var urlStr = ipPort + '/department/getById?id='+ departmentID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            var table_tr = $('.table-tr')
            var department_id = $('.department-id')
            var department_name = $('.department-name')
            var department_shortName = $('.department-shortName')
            var department_description = $('.department-description')
            if(obj.code == 0){
                if(obj.data == null){
                    alert("未搜索到信息")
                    return
                }
                for(var i = 0; i < 1; i++){
                    table_tr.eq(i).removeClass('hidden')
                    department_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value='" + obj.data.id + "'" + ">" + obj.data.id)
                    department_name.eq(i).text(obj.data.name)
                    department_shortName.eq(i).text(obj.data.shortName)
                    department_description.eq(i).text(obj.data.description)
                }
                for (var i = 1; i < 10; i++){
                    table_tr.eq(i).addClass('hidden')
                }
            }else {
                alert("请输入部门ID")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部部门信息/
 */
function getAllDepartmentInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/department/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setDepartmentTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置部门table信息/
 */
function setDepartmentTableInformation(obj) {
    var table_tr = $('.table-tr')
    var department_id = $('.department-id')
    var department_name = $('.department-name')
    var department_shortName = $('.department-shortName')
    var department_description = $('.department-description')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        department_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        department_name.eq(i).text(obj.data.content[i].name)
        department_shortName.eq(i).text(obj.data.content[i].shortName)
        department_description.eq(i).text(obj.data.content[i].description)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}