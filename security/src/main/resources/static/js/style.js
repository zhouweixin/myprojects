$(document).ready(function () {
    $('#select-all').on('click', function () {
        if(this.checked == true){
            $('.select-sub-box').prop('checked',true)
        }
        else{
            $('.select-sub-box').prop('checked',false)
        }
    })
    //一级菜单的事件处理
    var activeRow
    var subMenuActiveRow
    var subMenuFlag = 0
    $('.left-nav-menu').on('click', '.left-nav-menu-li',function (e) {
        if(!activeRow){
            //第一次处理激活的li
            activeRow = $(this)
            if(activeRow.find('.glyphicon').length == 0){
                activeRow.addClass('li-active')
            }
            else{
                var subMenu = activeRow.find('.sub-menu')
                subMenu.removeClass('none')
            }
            var imgItem = activeRow.find('img')
            var imgSrc = imgItem[0].src
            imgSrc = imgSrc.replace(".png","_fill.png")
            imgItem.attr('src', imgSrc)
            var spanItem = activeRow.find('span')
            if(spanItem){
                spanItem.removeClass('glyphicon-triangle-right')
                spanItem.addClass('glyphicon-triangle-bottom')
            }
        }
        else {
            //处理上一个激活的li
            var prevActiveRow = activeRow
            activeRow = $(this)
            if(prevActiveRow.find('.glyphicon').length == 0){
                prevActiveRow.removeClass('li-active')
            }
            else{
                var subMenu = prevActiveRow.find('.sub-menu')
                subMenu.addClass('none')
            }
            var prevImgItem = prevActiveRow.find('img')
            var prevImgSrc = prevImgItem[0].src
            prevImgSrc = prevImgSrc.replace("_fill.png",".png")
            prevImgItem.attr('src', prevImgSrc)
            var prevSpanItem = prevActiveRow.find('span')
            if(prevSpanItem){
                prevSpanItem.removeClass('glyphicon-triangle-bottom')
                prevSpanItem.addClass('glyphicon-triangle-right')
            }

            //处理上一个subli
            var preSubMenuActiveRow = subMenuActiveRow
            if(preSubMenuActiveRow && !subMenuFlag){
                preSubMenuActiveRow.removeClass('sub-li-active')
            }
            subMenuFlag = 0

            //处理当前激活li
            if(activeRow.find('.glyphicon').length == 0){
                activeRow.addClass('li-active')
            }
            else{
                var subMenu = activeRow.find('.sub-menu')
                subMenu.removeClass('none')
            }
            var imgItem = activeRow.find('img')
            var imgSrc = imgItem[0].src
            imgSrc = imgSrc.replace(".png","_fill.png")
            imgItem.attr('src', imgSrc)
            var spanItem = activeRow.find('span')
            if(spanItem){
                spanItem.removeClass('glyphicon-triangle-right')
                spanItem.addClass('glyphicon-triangle-bottom')
            }
        }
    })

    //二级菜单事件处理
    $('.sub-menu').on('click', '.sub-menu-li', function (e) {
        subMenuFlag = 1
        //第一次处理subli
        if(!subMenuActiveRow){
            subMenuActiveRow = $(this)
            subMenuActiveRow.addClass('sub-li-active')
        }
        else{
            //处理上一个subli
            var preSubMenuActiveRow = subMenuActiveRow
            preSubMenuActiveRow.removeClass('sub-li-active')
            //处理当前subli
            subMenuActiveRow = $(this)
            subMenuActiveRow.addClass('sub-li-active')
        }
    })
})
function selectAllCheckButton(thisObj) {
    if(thisObj.checked == true){
        $('.select-sub-box').prop('checked',true)
    }
    else{
        $('.select-sub-box').prop('checked',false)
    }
}
