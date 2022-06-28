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
var s = false;

function nextTab(){
  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if(s == true){
    s = false;
    n++;
  }
  if(n >= tabs.length){
    return false;
  } else {
    var i = 0;
    while(i < tabs.length){
      if(i == n){
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
    nextIcon(n);
  }
}

function switchIcon(number){
  s = true;
  let icons = Array.from($('.first ul li').children('a').children('span'));
  var j = 0;
  while(j < icons.length){
    if(j > number){
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
  n = number;
}

function nextIcon(number){
  let icons = Array.from($('.first ul li').children('a').children('span'));
  var j = 0;
  while(j < icons.length){
    if(j > number){
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
  n++;
}

function switchTab(number){

  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if(number > tabs.length){
    console.log("Invalid data");
    return false;
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
  switchIcon(number);
}

Vue.createApp({
  data() {
      return {
        client:{},
        cart:[],
       
       
        isClient:false,
        
      }
  },

  created() {
     axios.get("/api/clients/current")
        .then(response=> {
            this.isClient = true;
            this.client = response.data;
            this.cart = this.client.cart;
            console.log(this.cart)
        })

  },

  methods: {



  },
  computed: {
  

  }

}).mount('#app')
