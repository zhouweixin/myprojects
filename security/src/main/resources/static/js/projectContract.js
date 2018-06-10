$(document).ready(function () {
    document.getElementById("modal-contractStartDate").valueAsDate = new Date()
    document.getElementById("modal-contractEndDate").valueAsDate = new Date()

    $('#modal-projectStatus').val('进行中')
    $('#modal-projectStatus').attr('value', 0)
    $('.projectStatus-menu-ul li a').on('click', function () {
        $('#modal-projectStatus').val($(this).text())
        $('#modal-projectStatus').attr('value', $(this).attr('value'))
    })

    getAllProjectContractInformation()
})
/*
添加项目合同/
 */
function addProjectContract() {
    var projectName = $('#modal-contractName').val()
    var contractCustomerName = $('#modal-contractCustomerName').val()
    var contractStartDate = new Date(($('#modal-contractStartDate').val()))
    contractStartDate = (contractStartDate.toLocaleDateString()).replace(/\//g, '-')
    var contractEndDate = new Date(($('#modal-contractEndDate').val()))
    contractEndDate = (contractEndDate.toLocaleDateString()).replace(/\//g, '-')
    var contractAmount = $('#modal-contractAmount').val()
    var contractReceiptPrice = $('#modal-contractReceiptPrice').val()
    var contractCustomerOfficePhone = $('#modal-contractCustomerOfficePhone').val()
    var contractCustomerFinancePhone = $('#modal-contractCustomerFinancePhone').val()
    var projectStatus = $('#modal-projectStatus').attr('value')
    var contractResponsibleName = $('#modal-contractResponsibleName').val()
    if(projectName == ''){
        alert("请输入项目名称")
        return
    }
    var urlStr = ipPort + '/project/add?name='+ projectName + '&startDate=' + contractStartDate + '&endDate=' + contractEndDate + '&price=' + contractAmount
        + '&receiptPrice=' + contractReceiptPrice + '&customerOfficePhone=' + contractCustomerOfficePhone + '&customerFinancePhone=' + contractCustomerFinancePhone
        + '&customerUnit=' + contractCustomerName + '&leader=' + contractResponsibleName + '&projectStatus=' + projectStatus
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            alert(obj.message)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
搜索所有项目合同/
 */
function getAllProjectContractInformation() {
    var page = 0
    var size = 10
    var sortFieldName = 'id'
    var asc = 1
    var urlStr = ipPort + '/project/getAllByPage?page='+ page + '&size=' + size + '&sortFieldName=' + sortFieldName + '&asc=' + asc
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setProjectContractTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
设置项目table/
 */
function setProjectContractTableInformation(obj) {
    if(obj.data.numberOfElements != 0){
        var table_tr = $('.table-tr')
        var project_id = $('.project-id')
        var project_name = $('.project-name')
        var project_status = $('.project-status')
        var project_period = $('.project-period')
        var project_customerName = $('.project-customerName')
        var project_customerOfficePhone = $('.project-customerOfficePhone')
        var project_customerFinancePhone = $('.project-customerFinancePhone')
        var project_amount = $('.project-amount')
        var project_receivedPrice = $('.project-receivedPrice')
        var project_responsiblePersonName = $('.project-responsiblePersonName')
        var project_startDate = $('.project-startDate')
        var project_project_endDate = $('.project-endDate')
        for(var  i = 0; i < obj.data.numberOfElements; i++){
            table_tr.eq(i).removeClass('hidden')
            project_id.eq(i).html("<input class=\"select-box select-sub-box\" type=\"checkbox\"" +  "value=\"" + obj.data.content[i].id + "\"" + ">" + obj.data.content[i].id)
            project_name.eq(i).text(obj.data.content[i].name)
            project_status.eq(i).text(obj.data.content[i].projectStatus)
            project_period.eq(i).text(obj.data.content[i].period.days)
            project_customerName.eq(i).text(obj.data.content[i].customerUnit)
            project_customerOfficePhone.eq(i).text(obj.data.content[i].customerOfficePhone)
            project_customerFinancePhone.eq(i).text(obj.data.content[i].customerFinancePhone)
            project_amount.eq(i).text(obj.data.content[i].price)
            project_receivedPrice.eq(i).text(obj.data.content[i].receiptPrice)
            project_responsiblePersonName.eq(i).text(obj.data.content[i].leader.name)
            project_responsiblePersonName.eq(i).attr('value', obj.data.content[i].leader.id)
            project_startDate.eq(i).text(obj.data.content[i].startDate)
            project_project_endDate.eq(i).text(obj.data.content[i].endDate)
        }
        for (var i = obj.data.numberOfElements; i < 10; i++){
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
设置修改合同modal/
 */
function setUpdateModalInformation(thisObj) {
    var td = $(thisObj).parent().parent().parent().find('td')
    $('#modal-updateProjectID').val(td.eq(0).text())
    $('#modal-updateProjectName').val(td.eq(1).text())
    $('#modal-updateProjectStatus').val(td.eq(2).text())
    $('#modal-updateProjectCustomerName').val(td.eq(4).text())
    $('#modal-updateProjectCustomerOfficePhone').val(td.eq(5).text())
    $('#modal-updateProjectCustomerFinancePhone').val(td.eq(6).text())
    $('#modal-updateProjectAmount').val(td.eq(7).text())
    $('#modal-updateProjectReceiptPrice').val(td.eq(8).text())
    $('#modal-updateProjectResponsibleName').val(td.eq(9).attr('value'))
    $('#modal-updateProjectStartDate').val(td.eq(10).text())
    $('#modal-updateProjectEndDate').val(td.eq(11).text())
}
/*
修改合同/
 */
function updateProjectContract() {
    var projectID = $('#modal-updateProjectID').val()
    var projectName = $('#modal-updateProjectName').val()
    var contractCustomerName = $('#modal-updateProjectCustomerName').val()
    var contractStartDate = $('#modal-updateProjectStartDate').val()
    var contractEndDate = $('#modal-updateProjectEndDate').val()
    var contractAmount = $('#modal-updateProjectAmount').val()
    var contractReceiptPrice = $('#modal-updateProjectReceiptPrice').val()
    var contractCustomerOfficePhone = $('#modal-updateProjectCustomerOfficePhone').val()
    var contractCustomerFinancePhone = $('#modal-updateProjectCustomerFinancePhone').val()
    var projectStatus = $('#modal-updateProjectStatus').val()
    var contractResponsibleName = $('#modal-updateProjectResponsibleName').val()
    if(projectName == ''){
        alert("请输入项目名称")
        return
    }
    var urlStr = ipPort + '/project/update?name='+ projectName + '&id=' + projectID + '&startDate=' + contractStartDate + '&endDate=' + contractEndDate + '&price=' + contractAmount
        + '&receiptPrice=' + contractReceiptPrice + '&customerOfficePhone=' + contractCustomerOfficePhone + '&customerFinancePhone=' + contractCustomerFinancePhone
        + '&customerUnit=' + contractCustomerName + '&leader=' + contractResponsibleName + '&projectStatus=' + projectStatus
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            alert(obj.message)
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
删除单个合同信息/
 */
function deleteProjectContract(thisObj) {
    var td = $(thisObj).parent().parent().parent().find('td')
    var contractId = td.eq(0).text()
    var urlStr = ipPort + '/project/deleteById?id='+ contractId
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            getAllProjectContractInformation()
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
    var urlStr = ipPort + '/project/deleteByIdBatch'
    $.ajax({
        url:urlStr,
        contentType:'application/json',
        data:myjson,
        dataType:'json',
        type:'post',
        success:function (obj) {
            if(obj.code == 0){
                alert("批量删除项目合同信息成功！")
                getAllProjectContractInformation()
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
通过项目名称搜索/
 */
function getInformationByProjectName() {
    var projectName = $('#projectName-input').val()
    var urlStr = ipPort + '/project/getByNameLikeByPage?name=' + projectName
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            setProjectContractTableInformation(obj)
        },
        error:function (error) {
            console.log(error)
        }
    })
}