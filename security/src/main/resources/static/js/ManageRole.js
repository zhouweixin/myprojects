$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    getAllRoleInformation()
})
/*
/添加角色
 */
function addRole() {
    var RoleName = $('#modal-RoleName').val()
    var RoleDescription = $('#modal-RoleDescription').val()
    if(!RoleName){
        alert("请输入角色名称！")
        return
    }else{
        var urlStr = ipPort + '/role/add?name=' + RoleName + '&description=' + RoleDescription
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 0){
                    alert(obj.message)
                    getAllRoleInformation()
                }else{
                    alert(obj.message)
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
    $('#modal-modifyRoleID').val(td.eq(0).text())
    $('#modal-modifyRoleName').val(td.eq(1).text())
    $('#modal-modifyRoleDescription').val(td.eq(2).text())
}
/*
修改角色信息/
 */
function modifyRole() {
    var RoleID = $('#modal-modifyRoleID').val()
    var RoleName = $('#modal-modifyRoleName').val()
    var RoleDescription = $('#modal-modifyRoleDescription').val()
    if(!RoleName){
        alert("名称不可为空！")
        return
    }
    var urlStr = ipPort + '/role/update?id='+ RoleID + "&name=" + RoleName + '&description=' + RoleDescription
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("修改角色信息成功！")
                getAllRoleInformation()
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
删除角色信息/
 */
function deleteRole(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var RoleID = td.eq(0).text()
    var urlStr = ipPort + '/role/deleteById?id='+ RoleID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert("删除角色信息成功！")
                getAllRoleInformation()
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
批量删除角色信息/
 */
function deleteRoleInBatch() {
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
    var urlStr = ipPort + '/role/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除角色信息成功！")
                getAllRoleInformation()
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
通过名称搜索角色信息/
 */
function searchRoleByName() {
    var RoleName = $('#search-RoleName').val()
    if(!RoleName){
        alert('请输入角色名称')
        return
    }
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/role/getByNameLikeByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&name=' + RoleName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setRoleTableInformation(obj)
            }else {
                alert("请输入角色名称")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取全部角色信息/
 */
function getAllRoleInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/role/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setRoleTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置角色table信息/
 */
function setRoleTableInformation(obj) {
    var table_tr = $('.table-tr')
    var Role_id = $('.Role-id')
    var Role_name = $('.Role-name')
    var Role_description = $('.Role-description')
    for(var i = 0; i < obj.data.numberOfElements; i++){
        table_tr.eq(i).removeClass('hidden')
        Role_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
        Role_name.eq(i).text(obj.data.content[i].name)
        Role_description.eq(i).text(obj.data.content[i].description)
    }
    for (var i = obj.data.numberOfElements; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}