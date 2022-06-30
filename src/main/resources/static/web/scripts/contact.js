
  Vue.createApp({
    data() {
        return {}
      },
      created() {},
      methods: {
        alertContact(){
            Swal.fire(
                "Excelent!",
                "We will send you an email soon",
                 "success",
              "OK",
        );
        
          }
      },
       computed: {
      }
  
  }).mount('#app')
  