// whole-script strict mode syntax
// javaScript is very flexible
// flexible === dangerous
// added ECMAScript 5

'use strict';

$(document).ready(function () {

    var pageNo = ""
    var keyword = ""
    var orderBy = $("#selectBox").val();
    var categoryNo = ""

    search(pageNo, keyword, orderBy, categoryNo)


    $("#selectBox").on('change', function () {
        pageNo = "";
        orderBy = $("#selectBox").val();
        search(pageNo, keyword, orderBy, categoryNo);
    })

    $("#search-input").on('keydown', function (e) {
        if (e.keyCode === 13) {
            pageNo = "";
            keyword = $("#search-input").val()
            search(pageNo, keyword, orderBy, categoryNo)
        }
    })

    $("#search-icon").on('click', function () {
        pageNo = "";
        keyword = $("#search-input").val()
        search(pageNo, keyword, orderBy, categoryNo)
    })

    $("#page-box").on('click', 'button', function () {
        pageNo = $(this).data("pageno");
        search(pageNo, keyword, orderBy, categoryNo)
    })

    $("#category-box").on('click', 'li', function () {
        pageNo = "";

        $("#category-box > .active").attr('class', '#')
        $(this).attr('class', 'active')

        if ($(this).val() == 0) {
            categoryNo = null
        } else {
            categoryNo = $(this).val();
        }
        search(pageNo, keyword, orderBy, categoryNo)
    })
})

function search(pageNo, keyword, orderBy, categoryNo) {
    var data = {};
    data.pageNo = pageNo;
    data.keyword = keyword;
    data.orderBy = orderBy;
    data.categoryNo = categoryNo;

    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "POST",
        url: "/recipes",
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            var pg = result.pg;
            var recipes = result.recipes

            $("#show-recipes").empty();

            if (pg.totalRows == 0) {
                $("#show-recipes").html("<h2 class='text-center'>조회하신 레시피에 대한  검색 결과가 없습니다.</h2>")
                $("#page-box").empty()
                return
            }

            $.each(recipes, function (index, recipe) {
                let appendInfo = `<div class='col-sm-3'>
									<div class='thumbnail'>
										<a href='/recipes/${recipe.recipeNo}'>
											<img src='/images/recipe/thumbnail/${recipe.thumbnailImg}'>
										</a>
										<div class='caption'>
											<p id='recipeName'>${recipe.recipeName}</p>
											<p>by <span id='nickName'>${recipe.nickName}</span> | 스크랩 <span id='scrap'>${recipe.cnt}회</span></p>
											<p><span id='date'>${recipe.fullDate}</p>
										</div>
									</div>
								</div>`

                $("#show-recipes").append(appendInfo);
            })

            let bar = "";

            if (pg.currentBlockNo > 1) {
                bar += `<button class='w3-button' data-pageno=${pg.beginPage - 1}>이전구간</button>`
            }

            if (pg.pageNo != 1) {
                bar += `<button class='w3-button' data-pageno=${pg.pageNo - 1}>&laquo;</button>`
            }
            if (pg.totalPageCount < pg.endPage) {
                for (var i = pg.beginPage; i <= pg.totalPageCount; i++) {
                    if (pg.pageNo == i) {
                        bar += `<button class='w3-button w3-blue' data-pageno=${i}>${i}</button>`
                    } else {
                        bar += `<button class='w3-button' data-pageno=${i}>${i}</button>`
                    }
                }
            } else {
                for (var i = pg.beginPage; i <= pg.endPage; i++) {

                    if (pg.pageNo == i) {
                        bar += `<button class='w3-button w3-blue' data-pageno=${i}>${i}</button>`
                    } else {
                        bar += `<button class='w3-button' data-pageno=${i}>${i}</button>`
                    }
                }
            }
            if (pg.pageNo != pg.totalPageCount) {
                bar += `<button class='w3-button' data-pageno=${pg.pageNo + 1}>&raquo;</button>`
            }

            if (pg.currentBlockNo < pg.totalBlocksCount) {
                bar += `<button class='w3-button' data-pageno=${pg.endPage + 1}>다음구간</button>`
            }
            $("#page-box").empty().append(bar);

        }
    })
}


