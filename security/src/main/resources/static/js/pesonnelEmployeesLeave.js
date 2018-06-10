$(document).ready(function () {
    $('.select-department-ul li a').on('click', function () {
        $('#all-dropdownMenu1').val($(this).text())
        $('#all-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#employeesLeft-dropdownMenu1').val($(this).text())
        $('#employeesLeft-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#employeesLeaving-dropdownMenu1').val($(this).text())
        $('#employeesLeaving-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#employeesLeftAndBan-dropdownMenu1').val($(this).text())
        $('#employeesLeftAndBan-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })

    var leavingTabSelectDepartmentA = $('.leavingTab-selectDepartment-ul li a')
    for(var i = 1; i < departmentsName.length + 1; i++){
        leavingTabSelectDepartmentA.eq(i).parent().removeClass('hidden')
        leavingTabSelectDepartmentA.eq(i).text(departmentsName[i - 1])
        leavingTabSelectDepartmentA.eq(i).attr('value', departmentsID[i - 1])
    }
    $('.leavingTab-selectDepartment-ul li a').on('click', function () {
        $('#leavingTab-selectDepartment').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#leavingTab-selectDepartment').attr('value', $(this).attr('value'))
    })

    var leftTabSelectDepartmentA = $('.leftTab-selectDepartment-ul li a')
    for(var i = 1; i < departmentsName.length + 1; i++){
        leftTabSelectDepartmentA.eq(i).parent().removeClass('hidden')
        leftTabSelectDepartmentA.eq(i).text(departmentsName[i - 1])
        leftTabSelectDepartmentA.eq(i).attr('value', departmentsID[i - 1])
    }
    $('.leftTab-selectDepartment-ul li a').on('click', function () {
        $('#leftTab-selectDepartment').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#leftTab-selectDepartment').attr('value', $(this).attr('value'))
    })

    var allTabSelectDepartmentA = $('.allTab-selectDepartment-ul li a')
    for(var i = 1; i < departmentsName.length + 1; i++){
        allTabSelectDepartmentA.eq(i).parent().removeClass('hidden')
        allTabSelectDepartmentA.eq(i).text(departmentsName[i - 1])
        allTabSelectDepartmentA.eq(i).attr('value', departmentsID[i - 1])
    }
    $('.allTab-selectDepartment-ul li a').on('click', function () {
        $('#allTab-selectDepartment').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#allTab-selectDepartment').attr('value', $(this).attr('value'))
    })

    var leftAndBanTabSelectDepartmentA = $('.leftAndBanTab-selectDepartment-ul li a')
    for(var i = 1; i < departmentsName.length + 1; i++){
        leftAndBanTabSelectDepartmentA.eq(i).parent().removeClass('hidden')
        leftAndBanTabSelectDepartmentA.eq(i).text(departmentsName[i - 1])
        leftAndBanTabSelectDepartmentA.eq(i).attr('value', departmentsID[i - 1])
    }
    $('.leftAndBanTab-selectDepartment-ul li a').on('click', function () {
        $('#leftAndBanTab-selectDepartment').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#leftAndBanTab-selectDepartment').attr('value', $(this).attr('value'))
    })

    getAllStaffInformationByPage()
})
/*
获取全部员工信息/
 */
function getAllStaffInformationByPage() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/user/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                setAllLeaveStaffTableInformation(obj)
                setLeftLeaveStaffTableInformation(obj)
                setLeavingLeaveStaffTableInformation(obj)
                setLeftAndBanLeaveStaffTableInformation(obj)
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
allTab通过部门和姓名搜索信息/
 */
function allTabSearchByDepartmentAndStaffName() {
    var departmentId = $('#allTab-selectDepartment').attr('value')
    var staffName = $('#staffName-searchInput-allTab').val()
    var urlStr = ipPort + "/user/getByDepartmentAndNameLikeByPage?id=" + departmentId + "&name=" + staffName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setAllLeaveStaffTableInformation(obj)
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
leftTab通过部门和姓名搜索信息/
 */
function leftTabSearchByDepartmentAndStaffName() {
    var departmentId = $('#leftTab-selectDepartment').attr('value')
    var staffName = $('#staffName-searchInput-leftTab').val()
    var urlStr = ipPort + "/user/getByDepartmentAndNameLikeByPage?id=" + departmentId + "&name=" + staffName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setLeftLeaveStaffTableInformation(obj)
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
leavingTab通过部门和姓名搜索信息/
 */
function leavingTabSearchByDepartmentAndStaffName() {
    var departmentId = $('#leavingTab-selectDepartment').attr('value')
    var staffName = $('#staffName-searchInput-leavingTab').val()
    var urlStr = ipPort + "/user/getByDepartmentAndNameLikeByPage?id=" + departmentId + "&name=" + staffName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setLeavingLeaveStaffTableInformation(obj)
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
leftAndBanTab通过部门和姓名搜索信息/
 */
function leftAndBanTabSearchByDepartmentAndStaffName() {
    var departmentId = $('#leftAndBanTab-selectDepartment').attr('value')
    var staffName = $('#staffName-searchInput-leftAndBanTab').val()
    var urlStr = ipPort + "/user/getByDepartmentAndNameLikeByPage?id=" + departmentId + "&name=" + staffName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                if(obj.data.numberOfElements == 0){
                    alert("未搜索到信息")
                    return
                }
                setLeftAndBanLeaveStaffTableInformation(obj)
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
allTab获取全部员工信息/
 */
function allTabGetAllStaffInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/user/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                setAllLeaveStaffTableInformation(obj)
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
leftTab获取全部员工信息/
 */
function leftTabGetAllStaffInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/user/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                setLeftLeaveStaffTableInformation(obj)
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
leavingTab获取全部员工信息/
 */
function leavingTabGetAllStaffInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/user/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                setLeavingLeaveStaffTableInformation(obj)
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
leftAndBanTab获取全部员工信息/
 */
function leftAndBanTabGetAllStaffInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/user/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                setLeftAndBanLeaveStaffTableInformation(obj)
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置全部离职table/
 */
function setAllLeaveStaffTableInformation(obj) {
    console.log(obj)
    var table_tr = $('.table-tr')
    if(obj.data.numberOfElements > 0){
        var staffName = $('.allTab-staff-name')
        var staffID = $('.allTab-staff-number')
        var staffDepartment = $('.allTab-staff-department')
        var staffRole = $('.allTab-staff-position')
        var staffJobNature = $('.allTab-staff-jobNature')
        var staffJoinDate = $('.allTab-staff-joinDate')
        var staffLeaveDate = $('.allTab-staff-LeaveDate')
        var number = 0
        for(var i = 0, j = 0; j < obj.data.numberOfElements; j++ ){
            if(obj.data.content[j].jobNature.name == "已离职" || obj.data.content[j].jobNature.name == "待离职" || obj.data.content[j].jobNature.name == "离职禁用"){
                table_tr.eq(i).removeClass('hidden')
                staffName.eq(i).text(obj.data.content[j].name)
                staffName.eq(i).attr('value', obj.data.content[j].id)
                staffID.eq(i).text(obj.data.content[j].id)
                staffDepartment.eq(i).text(obj.data.content[j].department.name)
                staffRole.eq(i).text(obj.data.content[j].role.name)
                staffJobNature.eq(i).text(obj.data.content[j].jobNature.name)
                staffJobNature.eq(i).attr('value', obj.data.content[j].jobNature.id)
                staffJoinDate.eq(i).text(obj.data.content[j].employDate)
                staffLeaveDate.eq(i).text('')
                i++
                number++
            }
        }
    }
    for (var i = number; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}
/*
设置已离职table/
 */
function setLeftLeaveStaffTableInformation(obj) {
    var table_tr = $('.table-tr-left')
    if(obj.data.numberOfElements > 0){
        var staffName = $('.leftTab-staff-name')
        var staffID = $('.leftTab-staff-number')
        var staffDepartment = $('.leftTab-staff-department')
        var staffRole = $('.leftTab-staff-position')
        var staffJobNature = $('.leftTab-staff-jobNature')
        var staffJoinDate = $('.leftTab-staff-joinDate')
        var staffLeaveDate = $('.leftTab-staff-LeaveDate')
        var number = 0
        for(var i = 0, j = 0; j < obj.data.numberOfElements; j++ ){
            if(obj.data.content[j].jobNature.name == "已离职"){
                table_tr.eq(i).removeClass('hidden')
                staffName.eq(i).text(obj.data.content[j].name)
                staffName.eq(i).attr('value', obj.data.content[j].id)
                staffID.eq(i).text(obj.data.content[j].id)
                staffDepartment.eq(i).text(obj.data.content[j].department.name)
                staffRole.eq(i).text(obj.data.content[j].role.name)
                staffJobNature.eq(i).text(obj.data.content[j].jobNature.name)
                staffJobNature.eq(i).attr('value', obj.data.content[j].jobNature.id)
                staffJoinDate.eq(i).text(obj.data.content[j].employDate)
                staffLeaveDate.eq(i).text('')
                i++
                number++
            }
        }
    }
    for (var i = number; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}
/*
设置待离职table/
 */
function setLeavingLeaveStaffTableInformation(obj) {
    var table_tr = $('.table-tr-leaving')
    if(obj.data.numberOfElements > 0){
        var staffName = $('.leavingTab-staff-name')
        var staffID = $('.leavingTab-staff-number')
        var staffDepartment = $('.leavingTab-staff-department')
        var staffRole = $('.leavingTab-staff-position')
        var staffJobNature = $('.leavingTab-staff-jobNature')
        var staffJoinDate = $('.leavingTab-staff-joinDate')
        var staffLeaveDate = $('.leavingTab-staff-LeaveDate')
         var number = 0
        for(var i = 0, j = 0; j < obj.data.numberOfElements; j++ ){
            if(obj.data.content[j].jobNature.name == "待离职"){
                table_tr.eq(i).removeClass('hidden')
                staffName.eq(i).text(obj.data.content[j].name)
                staffName.eq(i).attr('value', obj.data.content[j].id)
                staffID.eq(i).text(obj.data.content[j].id)
                staffDepartment.eq(i).text(obj.data.content[j].department.name)
                staffRole.eq(i).text(obj.data.content[j].role.name)
                staffJobNature.eq(i).text(obj.data.content[j].jobNature.name)
                staffJobNature.eq(i).attr('value', obj.data.content[j].jobNature.id)
                staffJoinDate.eq(i).text(obj.data.content[j].employDate)
                staffLeaveDate.eq(i).text('')
                i++
                number++
            }
        }
    }
    for (var i = number; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}
/*
设置离职禁用table/
 */
function setLeftAndBanLeaveStaffTableInformation(obj) {
    var table_tr = $('.table-tr-leftAndBan')
    if(obj.data.numberOfElements > 0){
        var staffName = $('.leftAndBanTab-staff-name')
        var staffID = $('.leftAndBanTab-staff-number')
        var staffDepartment = $('.leftAndBanTab-staff-department')
        var staffRole = $('.leftAndBanTab-staff-position')
        var staffJobNature = $('.leftAndBanTab-staff-jobNature')
        var staffJoinDate = $('.leftAndBanTab-staff-joinDate')
        var staffLeaveDate = $('.leftAndBanTab-staff-LeaveDate')
        var number = 0
        for(var i = 0, j = 0; j < obj.data.numberOfElements; j++ ){
            if(obj.data.content[j].jobNature.name == "离职禁用"){
                table_tr.eq(i).removeClass('hidden')
                staffName.eq(i).text(obj.data.content[j].name)
                staffName.eq(i).attr('value', obj.data.content[j].id)
                staffID.eq(i).text(obj.data.content[j].id)
                staffDepartment.eq(i).text(obj.data.content[j].department.name)
                staffRole.eq(i).text(obj.data.content[j].role.name)
                staffJobNature.eq(i).text(obj.data.content[j].jobNature.name)
                staffJobNature.eq(i).attr('value', obj.data.content[j].jobNature.id)
                staffJoinDate.eq(i).text(obj.data.content[j].employDate)
                staffLeaveDate.eq(i).text('')
                i++
                number++
            }
        }
    }
    for (var i = number; i < 10; i++){
        table_tr.eq(i).addClass('hidden')
    }
}
/*
禁用/
 */
function forbiddenLeftButton(thisObj) {
    var staffID = $(thisObj).parent().parent().parent().parent().parent().find('td').eq(1).text()
    var urlStr = ipPort + '/user/update?jobNature=8' + '&id=' + staffID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert(obj.message)
                getAllStaffInformationByPage()
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
离职恢复/
 */
function recoveryJobButton(thisObj) {
    var staffID = $(thisObj).parent().parent().parent().parent().parent().find('td').eq(1).text()
    var urlStr = ipPort + '/user/update?jobNature=1' + '&id=' + staffID
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert(obj.message)
                getAllStaffInformationByPage()
            }else{
                alert(obj.message)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
离职证明/
 */
function leftToProveButton(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var staffName = td.eq(0).text()
    var department = td.eq(2).text()
    var role = td.eq(3).text()
    var joinDate = td.eq(5).text()
    var joinDateArr = joinDate.split('-')
    var leftDate = td.eq(6).text()
    var date = new Date()
    $('.leave-name-inp').val(staffName)
    $('.entry-year-inp').val(joinDateArr[0])
    $('.entry-month-inp').val(joinDateArr[1])
    $('.entry-day-inp').val(joinDateArr[2])
    $('.leave-department-inp').val(department)
    $('.leave-position-inp').val(role)
    $('.leave-year-inp').val()
    $('.leave-month-inp').val()
    $('.leave-day-inp').val()
    $('.now-year-inp').val(date.getFullYear())
    $('.now-month-inp').val(date.getMonth() + 1)
    $('.now-day-inp').val(date.getDate())
}