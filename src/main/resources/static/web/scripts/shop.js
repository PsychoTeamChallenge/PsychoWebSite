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

            })
       this.actualizarClient()
    },

    methods: {

        actualizarClient(){
            axios.get("/api/clients/current")
            .then(response => {
                this.client = response.data
                this.isClient = true;
                this.cart = this.client.cart
                this.favouritesIds =  this.client.favourites.map(fav => fav.id)
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
        },
        removeFavourite(id){
            axios.patch("/api/clients/current/favourites",`idProduct=${id}`)
            .then(response =>{
                this.actualizarClient()
            })
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
