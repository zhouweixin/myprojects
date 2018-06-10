$(document).ready(function () {
    $('.details-button').on('click', function () {
        $('#salaryExaminePage2').removeClass('hidden')
        $('#salaryExaminePage1').addClass('hidden')
    })

    $('.path-arrow-left').on('click', function () {
        $('#salaryExaminePage1').removeClass('hidden')
        $('#salaryExaminePage2').addClass('hidden')
    })
})