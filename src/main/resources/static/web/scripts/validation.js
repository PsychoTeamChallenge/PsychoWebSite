Vue.createApp({
    data() {
        return {
          isActive:false,
        }
    },

    created() {
        const params = new Proxy(new URLSearchParams(window.location.search), {
            get: (searchParams, prop) => searchParams.get(prop),
          });

          let token = params.token; 

          axios.post("/api/activateAccount/" + token)
          .then(response=> {
              this.isActive = true
          })

    },

    methods: {



    },
    computed: {
    

    }

}).mount('#app')
