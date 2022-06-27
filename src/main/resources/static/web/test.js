function closeCart(){
  if($(window).width() < 800){
    $('#cartMobileContainer').css("display", "none");
  } else {
    $('#cartDesktopContainer').css("display", "none");
  }
}


function openCart(){
  console.log("xd");
  if($(window).width() < 800){
    $('#cartMobileContainer').css("display", "block");
  } else {
    $('#cartDesktopContainer').css("display", "block");
  }
}
