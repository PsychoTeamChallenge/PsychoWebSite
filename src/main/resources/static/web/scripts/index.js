$(document).ready(function(){
  checkScroll();
  $(window).scroll(function(){
    checkScroll();
  })

  if($(window).width() < 800){
    console.log("ye");
    /*
    $('#logoTongue').addClass('logo-tongue');
    $('#logoTongue').removeClass('logo-tongue-navbar');*/
    $('#barbedWire').addClass('barbedWire');
    $('#barbedWire').removeClass('barbedWire-navbar');
  }
})

function checkScroll(){
  let navbar = $('.nav-bar');
  let scrollPositionY = $(window).scrollTop();
  if(($(window).width() < 800)){
    $('#logoTongue').addClass('logo-tongue');
    $('#logoTongue').removeClass('logo-tongue-navbar');

    $('#barbedWire').addClass('barbedWire');
    $('#barbedWire').removeClass('barbedWire-navbar');
  } else {
    if(scrollPositionY > 351){
      navbar.css("background-color", "black");
      $('#logoTongue').addClass('logo-tongue-navbar');
      $('#logoTongue').removeClass('logo-tongue');

      $('#barbedWire').removeClass('barbedWire');
      $('#barbedWire').addClass('barbedWire-navbar');
    } else {
      navbar.css("background-color", "transparent");
      $('#logoTongue').addClass('logo-tongue');
      $('#logoTongue').removeClass('logo-tongue-navbar');

      $('#barbedWire').addClass('barbedWire');
      $('#barbedWire').removeClass('barbedWire-navbar');
    }
  }

}
