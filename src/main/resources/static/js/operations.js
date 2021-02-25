var post = document.querySelectorAll("#posts");

post.forEach(function () {
    $('#heart').click(function () {

        $('#heart').toggleClass('fa fa heart');

        $('#heart').toggleClass('far fa heart');
        $('#heart').css('color', 'red');


    })

})