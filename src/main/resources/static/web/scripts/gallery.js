Vue.createApp({
    data() {
        return {
            posts: [],
            filtradosCategory: [],
            postsFiltrados: [],
            client: {},
            isClient: false,
            piercing: true,
            tattoo: true,
            shearchInput: "",

            newPost: false,

            postUrlImage:"",
            postTattooer:"",
            postType:"",
            postFires:0,



        }
    },

    created() {
       
        axios.get("/api/post").then(response => {
            this.posts = response.data;
            this.postsFiltrados = this.posts;
        })
            .catch(error => console.log(error))

        axios.get("/api/clients/current")
            .then(response => {
                this.client = response.data;
                this.isClient = true;
            })
            .catch(error => console.log(error))

            
    },

    methods: {

        changeFilterCategory(category) {

            if (category == "TATTOO") {
                this.tattoo = !this.tattoo
            }
            else if (category == "PIERCING") {
                this.piercing = !this.piercing
            }
        },
        requestAppointment() {
            window.location.href = "http://localhost:8080/web/appointment.html"
        },

        openNewPost(){
            this.newPost = !this.newPost;
            document.querySelector("html").classList.toggle("active")
        }, 

        subirImg(){
            let client = filestack.init("A9oZryZIQq2zxTic8yRnDz");
            let options = {
                onUploadDone: (res) => {
                    this.postUrlImage = res.filesUploaded[0].url
                },
            };
            client.picker(options).open();
        },

        refreshPost(){
            axios.get("/api/post").then(response => {
                this.posts = response.data;
                this.postsFiltrados = this.posts;
            })
                .catch(error => console.log(error))
        },

        // ACA CELEEEEEEE n.n GRACIASS
        uploadPost(){
            axios.post("/api/post", "urlImage=" + this.postUrlImage + "&tattooer=" +
            this.postTattooer + "&postType=" + this.postType + "&fires=" + this.postFires
            ).then(response => {
                
                this.refreshPost();
                this.openNewPost();
                
                Swal.fire(
                    "Excelent!",
                    "Your picture has been succesfully posted",
                     "success",
                    "OK",);
        } 
            ).catch(error => {
                if(error.response.status == 403){
                Swal.fire(
                    'Opss!',
                    'Missing data',
                    'error'
                );}
                else if(error.response.status == 401){
                    Swal.fire(
                        'Opss!',
                        'You are not an user, please sign up or log in',
                        'error'
                    );
                }
                else{
                    Swal.fire(
                        'Opss!',
                        'Something came wrong',
                        'error'
                    );
                }

               
            })
        }



    },
    computed: {
        shearchFilter() {
            if (this.posts.length > 0 && this.piercing && !this.tattoo) {
                this.filtradosCategory = this.posts.filter(post => post.postType == "PIERCING")
            }
            else if (this.posts.length > 0 && this.tattoo && !this.piercing) {
                this.filtradosCategory = this.posts.filter(post => post.postType == "TATTOO")
            }
            else if (this.tattoo && this.piercing) {
                this.filtradosCategory = this.posts;
            }
            else {
                this.filtradosCategory = []
            }

            if (this.shearchInput != "") {
                this.postsFiltrados = this.filtradosCategory.filter(post => post.title.includes(this.shearchInput) || post.tattooer.includes(this.shearchInput) || post.description.includes(this.shearchInput))
            }
            else {
                this.postsFiltrados = this.filtradosCategory
            }
        }

    }

}).mount('#app')
