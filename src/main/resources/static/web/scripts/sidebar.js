Vue.createApp({
    data() {
        return {
            posts: [],
            editAccount: false,
            filtradosCategory:[],
            postsFiltrados: [],
            purchases:[],
            client: {},
            isClient: false,
            piercing: true,
            tattoo: true,
            shearchInput:""
        }
    },

    created() {
      axios.get("/api/clients/current")
      .then(response => {
          this.client = response.data;
          this.postsFiltrados = this.client.posts
          this.purchases = this.client.purchases
          this.isClient = true;
      })
      // .catch(error => )

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
          Swal.fire(
            'Removed!',
            'This post has been deleted.',
            'success'
          );
          this.refreshSession();
        })
        .catch((error) => {
          Swal.fire(
            'Error!',
            'Error: ' + error,
            'error'
          );
          this.refreshSession();
        })
      },
      removeFavourite(id){
        axios.patch("/api/clients/current/favourites", "idProduct=" + id)
        .then((response) => {
          Swal.fire(
            'Removed!',
            'This product has been removed from your favourites list',
            'success'
          );
          this.refreshSession();
        })
        .catch((error) => {
          Swal.fire(
            'Error!',
            'Error: ' + error,
            'error'
          );
          this.refreshSession();
        })
      },
      refreshSession(){
        axios.get("/api/clients/current")
            .then(response => {
                this.client = response.data;
                this.postsFiltrados = this.client.posts
                this.isClient = true;
            })
            .catch(error => console.log(error))
      },
      formatDate(dateInput){
        const date = new Date(dateInput);
        let day = date.getDate() < 10? "0"+ date.getDate() : date.getDate();
        let month = date.getMonth() < 10? "0"+ date.getMonth() : date.getMonth();
        let year = date.getFullYear();
        return day + "/" + month + "/" + year
      },
      
    },
    computed: {

    }

}).mount('#app')


function logout(){
  axios.post("/api/logout")
  .then(window.location.href = "/web/index.html")
}
