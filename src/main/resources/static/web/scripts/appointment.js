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
        this.picker = MCDatepicker.create({
            el: '#datepicker',
            disableDates: [...this.appointmentDates],
            dateFormat: 'yyyy-mm-dd'
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
        let fullDate = "";
        if(monthPick > 9){
          fullDate = yearPick + "-" + monthPick + "-" + dayPick
        } else {
          yearPick + "-0" + monthPick + "-" + dayPick
        }
        console.log(fullDate);
        let tattooSize = document.getElementById('size-tatoo').value;
        let color = true;

        axios.post("/api/appointments/add", "tattoer_id=" + 1 + "&date=" + fullDate + "&phone=" + tel
        + "&bodyPart=" + bodyPart + "&tattooSize=" + tattooSize + "&color=" + color)
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