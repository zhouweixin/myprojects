function changeICNumber() {
    var staffID = $('#modal-staffNumber').val()
    var icNumber = $('#modal-cardNumberAfter').val()
    if(staffID == '' || icNumber == ''){
        alert('请输入员工编号和新ic卡号！')
    }
    var urlStr = ipPort + '/user/update?id=' + staffID + '&ic=' + icNumber
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
function cancelChangeICNumber() {
    $('#modal-staffNumber').val('')
    $('#modal-staffName').val('')
    $('#modal-cardNumberBefore').val('')
    $('#modal-cardNumberAfter').val('')
}