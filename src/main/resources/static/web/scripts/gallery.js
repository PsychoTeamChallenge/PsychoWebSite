Vue.createApp({
    data() {
        return {
            posts: [],
            filtradosCategory:[],
            postsFiltrados: [],
            client: {},
            isClient: false,
            piercing: true,
            tattoo: true,
            shearchInput:""
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
           
            if(category == "TATTOO"){
                this.tattoo = !this.tattoo
            }
            else if(category == "PIERCING"){
                this.piercing = !this.piercing
            }
        },
<<<<<<< HEAD
        requestAppointment(){
            window.location.href = "http://localhost:8080/web/appointment.html"
           }
=======
>>>>>>> beac039193964e1db33cf3c018f2e8bb3af59dd7

    },
    computed: {
        shearchFilter(){
            if (this.posts.length > 0 && this.piercing && !this.tattoo) {
                this.filtradosCategory = this.posts.filter(post => post.postType == "PIERCING")
            }
            else if (this.posts.length > 0 && this.tattoo && !this.piercing) {
                this.filtradosCategory = this.posts.filter(post => post.postType == "TATTOO")
            }
            else if(this.tattoo && this.piercing){
                this.filtradosCategory = this.posts;
            }
            else{
                this.filtradosCategory = []
            }

            if(this.shearchInput != ""){
                 this.postsFiltrados =  this.filtradosCategory.filter(post=> post.title.includes(this.shearchInput) ||  post.tattooer.includes(this.shearchInput) ||   post.description.includes(this.shearchInput))
            }
            else{
                this.postsFiltrados = this.filtradosCategory
            }
        }

    }

}).mount('#app')
