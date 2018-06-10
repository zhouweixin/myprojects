$(document).ready(function () {
    $('.select-department-ul li a').on('click', function () {
        $('#alreadySigned-dropdownMenu1').val($(this).text())
        $('#alreadySigned-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#notSigned-dropdownMenu1').val($(this).text())
        $('#notSigned-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#dueDate-dropdownMenu1').val($(this).text())
        $('#dueDate-dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })
    $('.select-jobCondition-ul li a').on('click', function () {
        $('#alreadySigned-dropdownMenu2').val($(this).text())
        $('#alreadySigned-dropdownMenu2').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })
    $('.select-contractCondition-ul li a').on('click', function () {
        $('#alreadySigned-dropdownMenu3').val($(this).text())
        $('#alreadySigned-dropdownMenu3').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#dueDate-dropdownMenu2').val($(this).text())
        $('#dueDate-dropdownMenu2').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })

    $('.contractType-menu-ul li a').on('click', function () {
        $('#modal-contractType').val($(this).text())
    })
    $('.contractDuration-menu-ul li a').on('click', function () {
        $('#modal-contractDuration').val($(this).text())
    })
    $('.contractCondition-menu-ul li a').on('click', function () {
        $('#modal-contractCondition').val($(this).text())
    })
    /*
    获取全部合同信息/
     */
    getAllContractInformation()
    getAllDueDateContractInformation()
    /*
    搜索栏/
     */
    var alreadySignedDepartmentA = $('.alreadySigned-department-ul li a')
    for(var i = 1; i < departmentsName.length + 1; i ++ ){
        alreadySignedDepartmentA.eq(i).parent().removeClass('hidden')
        alreadySignedDepartmentA.eq(i).text(departmentsName[i - 1])
        alreadySignedDepartmentA.eq(i).attr('value', departmentsID[i - 1])
    }
    $('.alreadySigned-department-ul li a').on('click', function () {
        $('#alreadySigned-department').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#alreadySigned-department').attr('value', $(this).attr('value'))
    })

    var alreadySignedJobNatureA = $('.alreadySigned-jobNature-ul li a')
    for(var i = 1; i < jobNaturesName.length + 1; i ++ ){
        alreadySignedJobNatureA.eq(i).parent().removeClass('hidden')
        alreadySignedJobNatureA.eq(i).text(jobNaturesName[i - 1])
        alreadySignedJobNatureA.eq(i).attr('value', jobNatureID[i - 1])
    }
    $('.alreadySigned-jobNature-ul li a').on('click', function () {
        $('#alreadySigned-jobNature').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#alreadySigned-jobNature').attr('value', $(this).attr('value'))
    })

    var alreadySignedContractTypeA = $('.alreadySigned-contractType-ul li a')
    for(var i = 1; i < personnelContractTypeName.length + 1; i ++ ){
        alreadySignedContractTypeA.eq(i).parent().removeClass('hidden')
        alreadySignedContractTypeA.eq(i).text(personnelContractTypeName[i - 1])
        alreadySignedContractTypeA.eq(i).attr('value', personnelContractTypeID[i - 1])
    }
    $('.alreadySigned-contractType-ul li a').on('click', function () {
        $('#alreadySigned-contractType').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
        $('#alreadySigned-contractType').attr('value', $(this).attr('value'))
    })
    /*
    修改modal/
     */
    var modalContractStatusA = $('.modal-contractStatus-menu-ul li a')
    for(var i = 0; i < contractStatusName.length + 1; i ++ ){
        modalContractStatusA.eq(i).parent().removeClass('hidden')
        modalContractStatusA.eq(i).text(contractStatusName[i - 1])
        modalContractStatusA.eq(i).attr('value', contractStatusID[i - 1])
    }
    $('.modal-contractStatus-menu-ul li a').on('click', function () {
        $('#modal-contractStatus').val($(this).text())
        $('#modal-contractStatus').attr('value', $(this).attr('value'))
    })
})
/*
搜索全部合同信息/
 */
function getAllContractInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/contract/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setContractTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置合同已签订table/
 */
function setContractTableInformation(obj) {
    if(obj.data.numberOfElements != 0){
        var table_tr = $('.table-tr')
        var alreadySignedStaff_name = $('.alreadySignedStaff-name')
        var alreadySignedStaff_id = $('.alreadySignedStaff-id')
        var alreadySignedStaff_department = $('.alreadySignedStaff-department')
        var alreadySignedStaff_contractNumber = $('.alreadySignedStaff-contractNumber')
        var alreadySignedStaff_contractType = $('.alreadySignedStaff-contractType')
        var alreadySignedStaff_contractStartDate = $('.alreadySignedStaff-contractStartDate')
        var alreadySignedStaff_contractEndDate = $('.alreadySignedStaff-contractEndDate')
        var alreadySignedStaff_contractCondition = $('.alreadySignedStaff-contractCondition')
        var number  = 0
        for(var j = 0, i = 0; j < obj.data.numberOfElements; j++){
            if(obj.data.content[j].user != null){
                table_tr.eq(i).removeClass('hidden')
                alreadySignedStaff_name.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[j].id + "\"" + ">" + obj.data.content[j].user.name)
                alreadySignedStaff_id.eq(i).text(obj.data.content[j].user.id)
                alreadySignedStaff_department.eq(i).text(obj.data.content[j].user.department.name)
                alreadySignedStaff_contractNumber.eq(i).text(obj.data.content[j].id)
                alreadySignedStaff_contractType.eq(i).text(obj.data.content[j].contractType.name)
                alreadySignedStaff_contractStartDate.eq(i).text(obj.data.content[j].startDate)
                alreadySignedStaff_contractEndDate.eq(i).text(obj.data.content[j].endDate)
                alreadySignedStaff_contractCondition.eq(i).text(obj.data.content[j].contractStatus.name)
                i++
            }
            number = i
        }
        for (var i = number; i < 10; i++){
            table_tr.eq(i).addClass('hidden')
        }
    }else{
        for (var i = 0; i < 10; i++){
            var table_tr = $('.table-tr')
            table_tr.eq(i).addClass('hidden')
        }
    }
}
/*
通过各种参数搜索合同信息/
 */
function getInformationByParameters() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var departmentID = $('#alreadySigned-department').attr('value')
    var jobNatureID = $('#alreadySigned-jobNature').attr('value')
    var contractTypeID = $('#alreadySigned-contractType').attr('value')
    var startDate = $('#alreadySigned-startDate').val()
    var endDate = $('#alreadySigned-endDate').val()
    var userName = $('#alreadySigned-userName').val()
    var urlStr = ipPort + '/contract/getByParametersByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    + '&departmentId=' + departmentID + '&jobNatureId=' + jobNatureID + '&contractTypeId=' + contractTypeID + '&date1=' + startDate
        + '&date2=' + endDate + '&userName=' + userName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setContractTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
修改合同信息/
 */
function updateContractInformation() {
    var contractID = $('#modal-contractID').val()
    var contractType = $('#modal-contractType').attr('value')
    var contractStartDate = $('#modal-contractStartDate').val()
    var contractEndDate = $('#modal-contractEndDate').val()
    var contractStatus = $('#modal-contractStatus').attr('value')
    var contractContent = $('#modal-contractContent').val()
    var contractScanningCopy = $('#modal-contractScanningCopy').val()
    var urlStr = ipPort + '/contract/update?id=' + contractID + '&contractType=' + contractType + '&startDate=' + contractStartDate + '&endDate=' + contractEndDate
        + '&contractStatus=' + contractStatus + '&content=' + contractContent + '&scanningCopy=' + contractScanningCopy
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(obj.code == 0){
                alert(obj.message)
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
设置修改合同modal/
 */
function setUpdateModalInformation(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var contractId = td.eq(3).text()
    var urlStr = ipPort + '/contract/getById?id='+ contractId
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            $('#modal-contractStaffName').val(obj.data.user.name)
            $('#modal-contractStaffID').val(obj.data.user.id)
            $('#modal-contractID').val(obj.data.id)
            $('#modal-contractType').val(obj.data.contractType.name)
            $('#modal-contractType').attr('value', obj.data.contractType.id)
            $('#modal-contractStartDate').val(obj.data.startDate)
            $('#modal-contractEndDate').val(obj.data.endDate)
            $('#modal-contractPeriod').val(obj.data.period)
            $('#modal-contractStatus').val(obj.data.contractStatus.name)
            $('#modal-contractStatus').attr('value', obj.data.contractStatus.id)
            $('#modal-contractContent').val(obj.data.content)
            $('#modal-contractScanningCopy').val(obj.data.scanningCopy)
            console.log(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除单个合同信息/
 */
function deleteContract(thisObj) {
    var td = $(thisObj).parent().parent().find('td')
    var contractId = td.eq(3).text()
    var urlStr = ipPort + '/contract/deleteById?id='+ contractId
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            if(thisObj.attr('value') == 'dueDate'){
                getAllDueDateContractInformation()
            }else {
                getAllContractInformation()
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
批量删除合同信息/
 */
function deleteContractInBatch() {
    var select_sub_box = $('.select-sub-box')
    var jsonArr = []
    for(var i = 0; i < select_sub_box.length; i++){
        if(select_sub_box.eq(i).is(':checked') == true){
            var json = {}
            json['id'] = select_sub_box.eq(i).attr('value')
            jsonArr.push(json)
        }
    }
    let myjson = JSON.stringify(jsonArr)
    var urlStr = ipPort + '/contract/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除合同信息成功！")
                if($('.deleteDepartmentInBatch-button').attr('value') == 'dueDate'){
                    getAllDueDateContractInformation()
                }else {
                    getAllContractInformation()
                }
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
搜索全部即将到期合同信息/
 */
function getAllDueDateContractInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var dueDate = 30
    var urlStr = ipPort + '/contract/getByEndDayByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc + '&endDay=' + dueDate
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setDueDateContractTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置合同即将到期table/
 */
function setDueDateContractTableInformation(obj) {
    if(obj.data.numberOfElements != 0){
        var table_trr = $('.table-trr')
        var dueDate_name = $('.dueDate-name')
        var dueDate_id = $('.dueDate-id')
        var dueDate_department = $('.dueDate-department')
        var dueDate_contractNumber = $('.dueDate-contractNumber')
        var dueDate_contractType = $('.dueDate-contractType')
        var dueDate_contractStartDate = $('.dueDate-contractStartDate')
        var dueDate_contractEndDate = $('.dueDate-contractEndDate')
        var dueDate_contractCondition = $('.dueDate-contractCondition')
        var number  = 0
        for(var j = 0, i = 0; j < obj.data.numberOfElements; j++){
            if(obj.data.content[j].user != null){
                table_trr.eq(i).removeClass('hidden')
                dueDate_name.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[j].id + "\"" + ">" + obj.data.content[j].user.name)
                dueDate_id.eq(i).text(obj.data.content[j].user.id)
                dueDate_department.eq(i).text(obj.data.content[j].user.department.name)
                dueDate_contractNumber.eq(i).text(obj.data.content[j].id)
                dueDate_contractType.eq(i).text(obj.data.content[j].contractType.name)
                dueDate_contractStartDate.eq(i).text(obj.data.content[j].startDate)
                dueDate_contractEndDate.eq(i).text(obj.data.content[j].endDate)
                dueDate_contractCondition.eq(i).text(obj.data.content[j].contractStatus.name)
                i++
            }
            number = i
        }
        for (var i = number; i < 10; i++){
            table_trr.eq(i).addClass('hidden')
        }
    }
}