Vue.createApp({
    data() {
        return {
            posts: [],
            editAccount: false,
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
      editAccountChange(){
        this.editAccount = true;
      },
      closeEditAccount(){
        this.editAccount = false;
      },
      removePost(id){
        axios.patch("/api/post/delete/" + id)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        })
      }
    },
    computed: {

    }

}).mount('#app')
