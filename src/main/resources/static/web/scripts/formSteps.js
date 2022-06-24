$(document).ready(function(){
         $('.first ul li a').click(function() {
             $('.first ul li a span.active').removeClass('active');
             let closestA = $(this).closest('a');
             console.log(closestA);
             $(this).closest('a').children('span').addClass('active');
         });

         $("#main-form").submit(function(e){
             return false;
         });

         nextTab();
});

var n = 0;
function nextTab(){
  console.log(n);
  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if(n == tabs.length){
    console.log('nothing')
  } else {
    //$('#main-form').children('.tab-steps').toggleClass('hidden');

    console.log(tabs.length);
    if(n > 0){
        $(tabs[n-1]).toggleClass('hidden');
    }
      $(tabs[n]).toggleClass('hidden');
      nextIcon();
    }
}

function nextIcon(){
  console.log(n);
  let tabs = Array.from($('.first ul li').children('a').children('span'));
  console.log(tabs);
  if(n == tabs.length){
    console.log('nothing')
  } else {
    //$('#main-form').children('.tab-steps').toggleClass('hidden');
    if(n > 0){
        //$(tabs[n-1]).toggleClass('active');
    }
      $(tabs[n]).toggleClass('active');
      n++;
    }
}
