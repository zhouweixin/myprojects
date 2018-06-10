$(document).ready(function () {
    var activeNumbersOfWork
    $('.numbersOfWork').on('click', function () {
        if(!activeNumbersOfWork){
            activeNumbersOfWork = $(this)
            activeNumbersOfWork.addClass('active')
        }else{
            var previousNumbersOfWork = activeNumbersOfWork
            previousNumbersOfWork.removeClass('active')
            activeNumbersOfWork = $(this)
            activeNumbersOfWork.addClass('active')
        }

    })
})