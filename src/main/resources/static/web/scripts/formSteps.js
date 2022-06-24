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

function switchTab(number){
  let tabs = Array.from($('#main-form').children('.tab-steps'));
  let icons = Array.from($('.first ul li').children('a').children('span'));
  if(number > tabs.length){
    return false;
    console.log("Invalid data");
  }
  var i = 0;
  while(i < tabs.length){
    console.log(i);
    if(i == number){
      if($(tabs[i]).hasClass('hidden') == true){
        $(tabs[i]).toggleClass('hidden');

      }
      i++;
    } else {
      if($(tabs[i]).hasClass('hidden') == false){
        $(tabs[i]).toggleClass('hidden');

      }
      i++;
    }
  }

  var j = 0;
  while(j < icons.length){
    console.log(j);
    if(j > number){
      console.log("mayor");
      if($(icons[j]).hasClass('active') == true){
        $(icons[j]).toggleClass('active');
      }
      j++;
    } else {
      if($(icons[j]).hasClass('active') == false){
        $(icons[j]).toggleClass('active');
      }
      j++;
    }
  }
}


  /*for(let i = 0; i < tabs.length; i++){
    if(i != n){
      if($(tabs[i]).hasClass('hidden') == false){
        $(tabs[i]).toggleClass('hidden');
      }
    } else {
      if($(tabs[i]).hasClass('hidden') == true){
        $(tabs[i]).toggleClass('hidden');
      }
    }
  }*/
