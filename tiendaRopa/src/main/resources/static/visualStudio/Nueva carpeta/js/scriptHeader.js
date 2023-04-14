$(function () {
    $("header nav:first-of-type > img").click(function () {
        $("body nav:first-of-type div ul").stop().animate({"left":"0", "opacity":"1"});
        $("body nav:first-of-type div ul li a").stop().animate({"opacity":"1"});
    });

    $("header nav:first-of-type div ul > img").click(function () {
        $("body nav:first-of-type div ul").stop().animate({"left":"-100%", "opacity":"0"});
    });

    $(window).resize(function () {
        if ($(window).width() >= 770){
            $("body nav:first-of-type div ul").css({"opacity":"1"});
            $("body nav:first-of-type div ul li a").css({"left":"0"});
        }
    })
})