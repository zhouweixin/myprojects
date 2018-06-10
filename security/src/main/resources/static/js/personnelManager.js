$(document).ready(function () {
    var activeDetailsBranch
    $('.details-branch').on('click', function (e) {
        if(!activeDetailsBranch){
            $('.details-branch.active').removeClass('active')
            activeDetailsBranch = $(this)
            activeDetailsBranch.addClass('active')
        }
        else{
            var preActiveDetailsBranch = activeDetailsBranch
            preActiveDetailsBranch.removeClass('active')
            activeDetailsBranch = $(this)
            activeDetailsBranch.addClass('active')
        }
    })

    var activeSelectDepartmentA
    $('.select-department-ul li a').on('click', function () {
        if(1){
            activeSelectDepartmentA = $(this)
            $('#dropdownMenu1').val(activeSelectDepartmentA.text())
            $('#dropdownMenu1').html(activeSelectDepartmentA.text() + '<span style="margin-left:4px" class="caret"></span>')
        }
    })

    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })

    $('#add-staff-button').on('click', function () {
        $('#page-empolyee-list').addClass('hidden')
        $('#page-add-staff').removeClass('hidden')
    })

    $('.path-arrow-left').on('click',function () {
        $('#page-add-staff').addClass('hidden')
        $('#page-empolyee-list').removeClass('hidden')
    })

    $('#cancle-store').on('click',function () {
        $('#page-add-staff').addClass('hidden')
        $('#page-empolyee-list').removeClass('hidden')
    })

    $('.probation-menu-ul li a').on('click', function () {
        $('#probation-input').val($(this).text())
    })

    $('.sex-menu-ul li a').on('click', function () {
        $('#sex-input').val($(this).text())
    })

    $('.department-menu-ul li a').on('click', function () {
        $('#department-input').val($(this).text())
    })
})