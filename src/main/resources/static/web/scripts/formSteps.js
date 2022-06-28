$(document).ready(function () {
  $('.first ul li a').click(function () {
    $('.first ul li a span.active').removeClass('active');
    let closestA = $(this).closest('a');
    console.log(closestA);
    $(this).closest('a').children('span').addClass('active');
  });

  $("#main-form").submit(function (e) {
    return false;
  });

  nextTab();
});

var n = 0;
var s = false;

function nextTab() {
  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if (s == true) {
    s = false;
    n++;
  }
  if (n >= tabs.length) {
    return false;
  } else {
    var i = 0;
    while (i < tabs.length) {
      if (i == n) {
        if ($(tabs[i]).hasClass('hidden') == true) {
          $(tabs[i]).toggleClass('hidden');
        }
        i++;
      } else {
        if ($(tabs[i]).hasClass('hidden') == false) {
          $(tabs[i]).toggleClass('hidden');

        }
        i++;
      }
    }
    nextIcon(n);
  }
}

function switchIcon(number) {
  s = true;
  let icons = Array.from($('.first ul li').children('a').children('span'));
  var j = 0;
  while (j < icons.length) {
    if (j > number) {
      if ($(icons[j]).hasClass('active') == true) {
        $(icons[j]).toggleClass('active');
      }
      j++;
    } else {
      if ($(icons[j]).hasClass('active') == false) {
        $(icons[j]).toggleClass('active');
      }
      j++;
    }
  }
  n = number;
}

function nextIcon(number) {
  let icons = Array.from($('.first ul li').children('a').children('span'));
  var j = 0;
  while (j < icons.length) {
    if (j > number) {
      if ($(icons[j]).hasClass('active') == true) {
        $(icons[j]).toggleClass('active');
      }
      j++;
    } else {
      if ($(icons[j]).hasClass('active') == false) {
        $(icons[j]).toggleClass('active');
      }
      j++;
    }
  }
  n++;
}

function switchTab(number) {

  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if (number > tabs.length) {
    return false;
  }
  var i = 0;
  while (i < tabs.length) {
    if (i == number) {
      if ($(tabs[i]).hasClass('hidden') == true) {
        $(tabs[i]).toggleClass('hidden');
      }
      i++;
    } else {
      if ($(tabs[i]).hasClass('hidden') == false) {
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
      client: {},
      cart: [],


      isClient: false,

    }
  },

  created() {
    this.reloadClient();
  },

  methods: {
    reloadClient() {
      axios.get("/api/clients/current")
        .then(response => {
          this.isClient = true;
          this.client = response.data;
          this.cart = this.client.cart.sort((a, b) => Intl.Collator('en').compare(a.id, b.id));
        })
    },
    addUnit(producId) {
      axios.patch("/api/cart/current/modify", `clientProduct_id=${producId}&quantity=1`)
        .then(response => {
          this.reloadClient()
        })
        .catch((error) => {
          Swal.fire(
            'Error!',
            'Not enought stock for this product.',
            'error'
          );
        })
    },
    subUnit(product) {
      if (product.quantity == 1) {
        Swal.fire({
          title: 'Are you sure?',
          text: "You won't be able to revert this!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
          if (result.isConfirmed) {
            axios.patch("/api/cart/current/modify", "clientProduct_id=" + product.id + "&quantity=" + (-1))
              .then(response => {
                this.reloadClient()
                Swal.fire(
                  'Deleted!',
                  'Your product has been removed.',
                  'success'
                )
                  .then(this.actualizarClient())
              }).catch(console.log(error));

          }
        });
      } else {
        axios.patch("/api/cart/current/modify", "clientProduct_id=" + product.id + "&quantity=" + (-1))
          .then((response) => {
            this.reloadClient();
          }).catch((error) => {
            console.log("error");
          });
      }
    },
    deleteProduct(producId) {
      Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.patch("/api/cart/current", `clientProduct_id=${producId}`)
            .then((response) => {
              this.reloadClient();
            })
            .catch((error) => {
              console.log(error);
            });
          Swal.fire(
            'Deleted!',
            'Your product has been removed.',
            'success'
          )
        }
      });
    },

  },
  computed: {


  }

}).mount('#app')
