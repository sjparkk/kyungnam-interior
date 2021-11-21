$( window ).resize(function() {
    var windowWidth = $( window ).width();
    if(windowWidth < 976) {
        $("#aside").css("display" , "none");
    } else {
        $("#aside").css("display" , "block");
    }
});