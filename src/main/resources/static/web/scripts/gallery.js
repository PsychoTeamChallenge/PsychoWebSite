Vue.createApp({
    data() {
        return {
            posts: [],
            client:{},
            isClient:false,

        }
    },

    created() {
        axios.get("/api/post").then(response=> this.posts = response.data).catch(error=> console.log(error))
        
        axios.get("/api/clients/current")
        .then(response=> {
            console.log(response)
            this.client = response.data;
            this.isClient = true;
        })
        .catch(error=> console.log(error))

    },

    methods: {

     

    },
    computed: {

    }

}).mount('#app')
