Vue.createApp({
    data() {
        return {
            products: [],
            productsFilters: [],
            clothOrPier: true,
            filtersShow: false,
        }
    },

    created() {
        axios.get("/api/products")
            .then(response => {
                this.products = response.data;
                this.productsFilters = this.products;
            })
    },

    methods: {
        changeFilterCategory(category) {
            this.productsFilters = this.products.filter(product => product.category == category);
            category == "CLOTHING" ? this.clothOrPier = true : this.clothOrPier = false
        },
        openFilters() {
            this.filtersShow = !this.filtersShow;
            document.querySelector("html").classList.toggle("active")
        },

    },
    computed: {

    }

}).mount('#app')
