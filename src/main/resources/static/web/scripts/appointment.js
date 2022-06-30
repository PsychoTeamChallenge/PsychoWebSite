Vue.createApp({
    data() {
        return {
            appointmentDates: [],
            tattoersList: [],
            picker: ""
        }
    },

    created() {
      axios.get("/api/appointments")
      .then((response) => {
        let dataOwned = response.data;
        dataOwned.forEach((appointment) => {
          console.log(appointment.date);
          let date = appointment.date;
          let year = date.split("-")[0];
          let month = date.split("-")[1];
          let day = date.split("-")[2];


          let totalDate = new Date(year, (parseInt(month) - 1), day);
          this.appointmentDates.push(totalDate);
        });
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth()).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();

        this.picker = MCDatepicker.create({
            el: '#datepicker',
            disableDates: [...this.appointmentDates],
            dateFormat: 'yyyy-mm-dd',
            minDate: new Date(yyyy, mm, dd)
        });
      })
      .catch((error) => {
        console.log(error);
      })

      axios.get("/api/tattoers")
      .then((response) => {
        this.tattoersList = response.data;
      })
      .catch((error) => {
        console.log(error);
      })
    },

    methods: {
      openDatePicker(){
        this.picker.open();
      },
      createAppointment(e){
        e.preventDefault();
        let tattoerId = document.getElementById('tattoerPicker').value;
        let tel = document.getElementById('telephoneClient').value;
        let bodyPart = document.getElementById('body-part').value;
        let dayPick = this.picker.getDate();
        let monthPick = parseInt(this.picker.getMonth()) + 1;
        let yearPick = this.picker.getYear();
        var fullDate = "";
        if(monthPick > 9){
          if(dayPick > 9){
            fullDate = yearPick + "-" + monthPick + "-" + dayPick;
          } else {
            fullDate = yearPick + "-" + monthPick + "-0" + dayPick;
          }

        } else {
          if(dayPick > 9){
            fullDate = yearPick + "-0" + monthPick + "-" + dayPick;
          } else {
            fullDate = yearPick + "-0" + monthPick + "-0" + dayPick;
          }

        }
        let tattooSize = document.getElementById('size-tatoo').value;
        let color = true;
        
        Swal.fire({
          title: 'Are tou sure do you want to make an appoinment?',
          showDenyButton: true,
          showCancelButton: true,
          confirmButtonText: 'Yes,I am sure',
          denyButtonText: `Don't make it`,
        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            Swal.fire('You have an apoinment', '', 'success')
            axios.post("/api/appointments/add", "tattoer_id=" + 1 + "&date=" + fullDate + "&phone=" + tel
            + "&bodyPart=" + bodyPart + "&tattooSize=" + tattooSize + "&color=" + color)
            console.log(fullDate);
          } else if (result.isDenied) {
            Swal.fire('Your appoinment is cancel', '', 'info')
          }
        })
        .then(response => {
          })
        .catch((error) => {
          console.log(error);
        })
      }
    },
    computed: {

    }

}).mount('#app')
