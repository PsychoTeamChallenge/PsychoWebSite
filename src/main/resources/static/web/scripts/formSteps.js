
$('body').ready(function () {
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
var current = 0;
var s = false;

function nextTab() {
  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if (s == true) {
    s = false;
    n++;
    current = n;
  }
  if (validateSingleForm() == true) {
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

function validateSingleForm() {
  var tabs = document.getElementsByClassName('tab-steps');
  var variableN = n - 1;
  if (variableN == -1) {

  } else {
    var currentTab = tabs[variableN].getElementsByTagName('input');
    if (currentTab == undefined) { }
    for (let i = 0; i < currentTab.length; i++) {
      if (currentTab[i].value == "") {
        console.log(currentTab[i]);
        Swal.fire(
          'Error!',
          'Missing fields to be completed',
          'error'
        );
        return false;
      }
    }
  }
  return true;
}

function validateAllForms(variableN) {
  var tabs = document.getElementsByClassName('tab-steps');
  if (variableN == -1) {

  } else {
    for (let j = 0; j < variableN; j++) {
      var currentTab = tabs[j].getElementsByTagName('input');
      for (let i = 0; i < currentTab.length; i++) {
        if (currentTab[i].value == "") {
          Swal.fire(
            'Error!',
            'Missing fields to be completed',
            'error'
          );
          return false;
        }
      }
    }
  }
  return true;
}

function switchTab(number) {
  let tabs = Array.from($('#main-form').children('.tab-steps'));
  if(n == tabs.length){
    return false;
  }
  if (number > tabs.length) {
    return false;
  }
  if (validateAllForms(number) == true) {
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
}

Vue.createApp({
  data() {
    return {
      client: {},
      cart: [],

      isClient: false,

      nameInput: "",
      lastNameInput: "",
      cityInput: "",
      addressInput: "",
      cardNumber: "",
      expiry: "",
      cvv: "",
      cardHolder: "",
      expense: 0,
      idPurchase:0,
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
    dateToday() {
      const months = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];

      let date = new Date();
      let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate()

      return day + " " + months[date.getMonth()].toLowerCase() + " " + date.getFullYear()
    },
    totalExpense() {
      this.expense = 0;
      this.cart.forEach(product => {
        this.expense += product.price * product.quantity
      });
      return this.expense;
    },
    makePayment() {
      if (this.cart.length > 0) {
        if (this.cardNumber != "" & this.expiry != "" & this.cvv != "" & this.cardHolder != "") {
          $('#loading').css("display", "flex")

          let cardData = {
            "number": this.cardNumber,
            'cardHolder': this.cardHolder,
            'category': "Others",
            'description': "Make purchase in Psycho Store",
            'expiry': this.expiry,
            'cvv': this.cvv,
            'amount': this.expense + 1000
          }
          let purchase = {
            "shipmentType": "ADDRESS",
            "paymentMethod": "Credit card",
            "address": this.addressInput,

          }
          axios.post("https://bankrdox.herokuapp.com/api/transactions/makePayment", cardData)
            .then(response => {
              axios.post("/api/purchase/complete", purchase)
                .then(response => {
                  $('#loading').fadeOut();
                  Swal.fire(
                    'Accepted!',
                    'Your payment was successful!',
                    'success'
                  ).then(() => {
                    this.idPurchase = response.data
                    nextTab();
                  })

                }
                )
                .catch(error => {
                  $('#loading').fadeOut();
                  Swal.fire(
                    'Opss!',
                    'There was a psycho problem!',
                    'error'
                  )
                  console.log(error)
                })
            })
            .catch(error => {
              $('#loading').fadeOut();
              Swal.fire(
                'Opss!',
                'There was a payment problem!',
                'error'
              )
            })
        }
        else {
          Swal.fire(
            'Opss!',
            "Missing data!",
            'error'
          )
        }
      }
      else {
        Swal.fire(
          'Opss!',
          "You don't have any products!",
          'error'
        )
      }
    },
    getPDF() {
      axios.post("/api/purchase/resume", "idPurchase=" + this.idPurchase, { "responseType": 'blob' })
        .then(response => {
          console.log(response)
          let blob = new Blob([response.data]);
          let link = document.createElement('a');
          link.href = window.URL.createObjectURL(blob);
          link.download = `Pyscho_Resume_${this.client.firstName}_${this.client.lastName}.pdf`;
          link.click();
        })
        .catch(error => console.log(error))
    }
  },
  computed: {

  }

}).directive('dragscroll', VueDragscroll).mount('#app')
