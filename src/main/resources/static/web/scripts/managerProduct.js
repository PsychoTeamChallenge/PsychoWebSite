Vue.createApp({
    data() {
        return {
            products: [],
            productName: "",
            productDescription: "",
            productImgUrl: "",
            productStock: 0,
            productPrice: 0.0,
            productSizes: [],
            productColors: [],

            productoToModificar: {},
            productFocusName: "",
            productFocusDescription: "",
            productFocusUrlImg: "",
            productFocusStock: 0,
            productFocusPrice: 0.0,
            productFocusSizes: [],
            productFocusColors: [],

            size: 0,
            color: "",

            addNewProduct: true,
            fullProduct: false,
        }
    },

    created() {
        axios.get("/api/products")
            .then(response => {
                this.products = response.data
            })
    },

    methods: {

        changeMenu(location) {

            if (location == "create") {
                this.addNewProduct = true
            }
            else if (location == "modify") {
                this.addNewProduct = false
            }

            this.fullProduct = false;

            this.productFocusColors = []
            this.productFocusSizes = []
            this.productFocusName = ""
            this.productFocusDescription = ""
            this.productFocusPrice = ""
            this.productFocusUrlImg = ""
            this.productFocusStock = ""
            this.productoToModificar = {}
        },


        addImg() {
            const client = filestack.init("A9oZryZIQq2zxTic8yRnDz");
            const options = {
                onUploadDone: (res) => {
                    this.productImgUrl = res.filesUploaded[0].url;
                },
            };
            client.picker(options).open();
        },
        addSize() {
            if (this.size > 0) {
                this.productSizes.push(this.size);
                this.size = 0
            }
        },
        addColor() {
            if (this.color != "") {
                this.productColors.push(this.color);
                this.color = ""
            }
        },
        removeSize(sizeD) {
            this.productSizes = this.productSizes.filter(size => size != sizeD);
        },
        removeColor(colorD) {
            this.productColors = this.productColors.filter(color => color != colorD);
        },
        createProduct() {
            if (this.productColors.length >= 1 && this.productSizes.length >= 1
                && this.productName != "" && this.productDescription != "" && this.productPrice > 0 && this.productImgUrl != "") {

                axios.post("/api/products", `name=${this.productName}&description=${this.productDescription}&urlImg=${this.productImgUrl}&stock=${this.productStock}&price=${this.productPrice}&sizes=${this.productSizes}&colors=${this.productColors}`)
                    .then(response => {
                        if (response.data == "Product created successfully") {
                            swal("Nice", "Product created!", "success")
                            .then((value) => {
                                window.location.reload()
                            })

                        }
                        else {
                            console.log(response)
                            swal("Oops", "Something went wrong!", "error")
                        }
                    })
                    .catch(error => {


                        swal("Oops", "Something went wrong!", "error")
                    })
            }
            else {
                console.log("datos ")
                swal("Oops", "Missing data!", "error")
            }
        },



        openProduct(product) {
            this.productoToModificar = product;

            this.productFocusColors = this.productoToModificar.colors
            this.productFocusSizes = this.productoToModificar.sizes
            this.productFocusName = this.productoToModificar.name
            this.productFocusDescription = this.productoToModificar.description
            this.productFocusPrice = this.productoToModificar.price
            this.productFocusUrlImg = this.productoToModificar.urlImg
            this.productFocusStock = this.productoToModificar.stock
            this.fullProduct = !this.fullProduct
        },
        changeImgOld() {
            const client = filestack.init("A9oZryZIQq2zxTic8yRnDz");
            const options = {
                onUploadDone: (res) => {
                    this.productFocusImgUrl = res.filesUploaded[0].url;
                },
            };
            client.picker(options).open();
        },
        addSizeOld() {
            if (this.size > 0) {
                this.productFocusSizes.push(this.size);
                this.size = 0
            }
        },
        addColorOld() {
            if (this.color != "") {
                this.productFocusColors.push(this.color);
                this.color = ""
            }
        },
        removeSizeOld(sizeD) {
            this.productFocusSizes = this.productFocusSizes.filter(size => size != sizeD);
        },
        removeColorOld(colorD) {
            this.productFocusColors = this.productFocusColors.filter(color => color != colorD);
        },
        updateProduct() {

            this.productFocusColors.length < 1 ? this.productFocusColors = this.fullProduct.colors : ""
            this.productFocusSizes.length < 1 ? this.productFocusSizes = this.fullProduct.sizes : ""
            this.productFocusName == "" ? this.productFocusName = this.fullProduct.name : ""
            this.productFocusDescription == "" ? this.productFocusDescription = this.fullProduct.description : ""
            this.productFocusPrice <= 0 ? this.productFocusPrice = this.fullProduct.price : ""
            this.productFocusUrlImg == "" ? this.productFocusUrlImg = this.fullProduct.urlImg : ""

            if (this.productFocusColors.length >= 1 && this.productFocusSizes.length >= 1
                && this.productFocusName != "" && this.productFocusDescription != "" && this.productFocusPrice > 0 && this.productFocusUrlImg != "") {
                console.log(`id=${this.productoToModificar.id}&name=${this.productFocusName}&description=${this.productFocusDescription}&urlImg=${this.productFocusUrlImg}&stock=${this.productFocusStock}&price=${this.productFocusPrice}&sizes=${this.productFocusSizes}&colors=${this.productFocusColors}`)
                axios.post("/api/products/modify", `id=${this.productoToModificar.id}&name=${this.productFocusName}&description=${this.productFocusDescription}&urlImg=${this.productFocusUrlImg}&stock=${this.productFocusStock}&price=${this.productFocusPrice}&sizes=${this.productFocusSizes}&colors=${this.productFocusColors}`)
                    .then(response => {
                        if (response.data == "Product update successfully") {
                            swal("Nice", "Product update!", "success")
                            .then((value) => {
                                window.location.reload()
                            })
                        }
                        else {
                            console.log(response)
                            swal("Oops", "Something went wrong!", "error")
                        }
                    })
                    .catch(error => {
                        console.log(error)
                        swal("Oops", "Something went wrong!", "error")
                    })

            }
            else {
                console.log("datos ")
                swal("Oops", "Missing data!", "error")
            }
        },
        deleteProduct() {
            axios.delete(`/api/products/delete/${this.productoToModificar.id}`)
                .then(response => {
                    if (response.data == "Product has been removed") {
                        swal("Nice", "Product delete!", "success")
                        .then((value) => {
                            window.location.reload()
                        })
                    }
                    else {
                        swal("Oops", "Something went wrong!", "error")
                    }
                })
               
                .catch(error => {
                    swal("Oops", "Something went wrong!", "error")
                })
        }

    },
    computed: {

    }

}).directive('dragscroll', VueDragscroll)
    .mount('#app')
