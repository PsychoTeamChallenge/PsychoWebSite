Vue.createApp({
    data() {
        return {

            products: [],
            productsList: [],
            productsOrder: [],
            productsFilters: [],
            productsListFilter: [],

            client: {},
            cart: [],
            currentProduct: {},
            totalCart: 0,
            favouritesIds:[],

            clothOrPier: true,
            filtersShow: false,
            isClient: false,

            order: "lowPrice",
            category: "ALL",
            shearchInput: "",

        }
    },

    created() {
        axios.get("/api/products")
            .then(response => {
                this.products = response.data;
                this.productsFilters = this.products;
                this.changeFilterCategory("CLOTHING")
                this.actualizarClient();
            })

    },

    methods: {

        actualizarClient(){
            axios.get("/api/clients/current")
            .then(response => {
                this.client = response.data;
                this.isClient = true;
                this.cart = this.client.cart.sort((a,b)=>Intl.Collator('en').compare(a.id, b.id))
                this.favouritesIds =  this.client.favourites.map(fav => fav.id)

                this.totalCart = 0;
                this.cart.forEach(product => {
                  this.totalCart += product.quantity * product.price;
                });

            })

            .catch(error => {
                this.isClient = false;
            })
        },
        changeFilterCategory(category) {
            this.productsFilters = this.products.filter(product => product.category == category);
            category == "CLOTHING" ? this.clothOrPier = true : this.clothOrPier = false
            this.category = "ALL"
            this.orderProducts()
        },

        openFilters() {
            this.filtersShow = !this.filtersShow;
            document.querySelector("html").classList.toggle("active")
        },

        changeOrder(order) {
            this.order = order;
        },
        changeCategory(category) {
            this.category = category;
        },

        orderProducts() {
            const collator = new Intl.Collator('en');
            if (this.order == "lowPrice") {
                this.productsOrder = this.productsFilters.sort((a, b) => collator.compare(a.price, b.price))
                this.filterProducts()

            }
            else if (this.order == "highPrice") {
                this.productsOrder = this.productsFilters.sort((a, b) => collator.compare(b.price, a.price))
                this.filterProducts()
            }
            else if (this.order == "lowApha") {
                this.productsOrder = this.productsFilters.sort((a, b) => collator.compare(a.name, b.name))
                this.filterProducts()
            }
            else if (this.order == "highApha") {
                this.productsOrder = this.productsFilters.sort((a, b) => collator.compare(b.name, a.name))
                this.filterProducts()
            }

        },
        filterProducts() {
            if (this.category == "ALL") {
                this.productsListFilter = this.productsOrder
            }
            else {
                this.productsListFilter = this.productsOrder.filter(product => product.filter == this.category)
            }
        },

        addFavourite(id){
            axios.post("/api/clients/current/favourites",`idProduct=${id}`)
            .then(response =>{
                this.actualizarClient()
            })
            .catch(error=>{
              if(error.code == "ERR_BAD_REQUEST"){
                Swal.fire({
                  title: 'Oh woow!',
                  text: "You can't do that! Do you want to log in?",
                  icon: 'warning',
                  showCancelButton: true,
                  confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                  confirmButtonText: 'Yes, log in!'
                }).then((result) => {
                  if (result.isConfirmed) {
                   window.location.href= "/web/login.html"
                  }
                });
              }
            })
        },
        removeFavourite(id){
            axios.patch("/api/clients/current/favourites",`idProduct=${id}`)
            .then(response =>{
                this.actualizarClient()
            })
        },
        removeProduct(id){
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
                  axios.patch("/api/cart/current","clientProduct_id=" + id)
                  .then((response) => {
                    this.actualizarClient();
                    Swal.fire(
                      'Deleted!',
                      'Your product has been removed.',
                      'success'
                    )
                  })
                  .catch((error) => {
                    console.log(error);
                  });
                  
                }
              });
        },
        addProduct(product){
          axios.patch("/api/cart/current/modify", "clientProduct_id=" + product.id + "&quantity=" + 1)
          .then((response) => {
            this.actualizarClient();
          })
            .catch((error) => {
              Swal.fire(
              'Error!',
              'Not enought stock for this product.',
              'error'
            );

          }
          );
        },
        subProduct(product){
          if(product.quantity == 1){
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
                axios.patch("/api/cart/current/modify", "clientProduct_id=" + product.id + "&quantity=" + (-1)).then(this.actualizarClient).catch(console.log("error"));
                Swal.fire(
                  'Deleted!',
                  'Your product has been removed.',
                  'success'
                )
                .then(this.actualizarClient())
              }
            });
          } else {
            axios.patch("/api/cart/current/modify", "clientProduct_id=" + product.id + "&quantity=" + (-1))
            .then((response) => {
                this.actualizarClient();
              }).catch((error) => {
                console.log("error");
              });
          }

        },
        addToCart(id_product){
          let colorValue = $('input[name="colorProduct"]:checked').val();
          let sizeValue = $('input[name="sizeProduct"]:checked').val();
          if(colorValue == undefined || sizeValue == undefined){
            Swal.fire(
              'Error!',
              'Please choose a valid size and color',
              'error'
            );
          } else {
            axios.post("/api/cart/current", "size=" + sizeValue + "&color=" + colorValue + "&id_product=" + id_product)
            .then((response) => {
              Swal.fire(
                'Product added!',
                'Your product has been added to the shopping cart',
                'success'
              );
              this.actualizarClient();
              this.closeCheckProduct();
            })
            .catch((error) => {
              Swal.fire({
                title: 'Oh woow!',
                text: "You can't do that! Do you want to log in?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, log in!'
              }).then((result) => {
                if (result.isConfirmed) {
                 window.location.href= "/web/login.html"
                }
              });
              this.closeCheckProduct();
            });
          }
        },
        openCheckProduct(product){
          this.currentProduct = product;
          if($(window).width() < 800){
            $('#productMobileContainer').css("top", "0%");
            // $('html').toggleClass("active");
            this.closeCart();
            this.filtersShow = false;
          } else {
            $('#productDesktopContainer').css('top', '0%');
            // $('html').toggleClass("active");
            this.closeCart();
            this.filtersShow = false;
          }
        },
        closeCheckProduct(){
          if($(window).width() < 800){
            $('#productMobileContainer').css("top", "100%");
            $('html').toggleClass("active");
            $("input[type=radio][name=colorProduct]").prop('checked', false);
          } else {
            $('#productDesktopContainer').css('top', '100%');
            $('html').toggleClass("active");
            $("input[type=radio][name=colorProduct]").prop('checked', false);
            $("input[type=radio][name=sizeProduct]").prop('checked', false);
          }
        },
        closeCart(){
          if($(window).width() < 800){
            $('#cartMobileContainer').css("left", "-100%");
            $('html').toggleClass("active");
          } else {
            $('#cartDesktopContainer').css("left", "-40%");
            $('html').toggleClass("active");
          }
        },
        openCart(){
          if($(window).width() < 800){
            $('#cartMobileContainer').css("left", "0%");
            $('html').toggleClass("active");
          } else {
            $('#cartDesktopContainer').css("left", "0%");
            $('html').toggleClass("active");
          }
        },
        removeCart(){
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
              if(this.cart.length == 0){
                Swal.fire(
                  'Error!',
                  'Cart is empty.',
                  'error'
                )
              } else {
                axios.patch("/api/cart/current/empty").then(this.actualizarClient).catch(console.log("error"));
                Swal.fire(
                  'Deleted!',
                  'Your cart has been removed.',
                  'success'
                )
              }

            }
          });

        },
        finishBuy(){
          window.location.href = "/web/formSteps.html"
        }
    },
    computed: {
        shearch() {
            if (this.shearch == "") {
                this.productsList = this.productsListFilter
            }
            else {
                this.productsList = this.productsListFilter.filter(product => product.name.includes(this.shearchInput) ||
                    product.description.includes(this.shearchInput) ||
                    product.category.includes(this.shearchInput) ||
                    product.colors.includes(this.shearchInput) ||
                    product.filter.includes(this.shearchInput))
            }
        },

    }

}).mount('#app')
