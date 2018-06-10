$(document).ready(function () {
    $('.select-department-ul li a').on('click', function () {
        $('#dropdownMenu1').val($(this).text())
        $('#dropdownMenu1').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })

    $('.select-changeCondition-ul li a').on('click', function () {
        $('#dropdownMenu2').val($(this).text())
        $('#dropdownMenu2').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })

    $('.select-changeState-ul li a').on('click', function () {
        $('#dropdownMenu3').val($(this).text())
        $('#dropdownMenu3').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })

    $('.add-change-ul li a').on('click', function () {
        $('#dropdownMenu4').val($(this).text())
        $('#dropdownMenu4').html($(this).text() + '<span style="margin-left:4px" class="caret"></span>')
    })

    $('.changeDepartment-menu-ul li a').on('click', function () {
        $('#modal-changeDepartment').val($(this).text())
    })

    document.getElementById('modal-startDate').valueAsDate = new Date()
    document.getElementById('modal-planConversionDate').valueAsDate = new Date()
    document.getElementById('modal-actualConversionDate').valueAsDate = new Date()
})