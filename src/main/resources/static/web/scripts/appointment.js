Vue.createApp({
    data() {
        return {
            appointmentDates: [],
            tattoersList: [],
            picker: "",
            isClient:false,
        }
    },

    created() {
      axios.get("/api/clients/current")
      .then(response=>this.isClient = true)
      .catch(error=>{
        this.isClient = false;
      })
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
            theme: {
              date: {
                active: {
                    default: {
                        foreground: 'rgb(255, 255, 255)'
                    },
                    picked: {
                        foreground: 'rgb(255, 255, 255)',
                        background: 'rgb(223, 40, 144)'
                    },
                    today: {
                        foreground: 'rgb(255, 255, 255)',
                        background: 'rgba(255, 0, 0, 0.9)'
                    }
                },
                inactive: {
                    default: {
                        foreground: 'rgb(235 91 173)'
                    },
                    picked: {
                        foreground: '#38ada9',
                        background: '#38ada9'
                    },
                    today: {
                        foreground: 'rgb(235 91 173)',
                        background: 'rgb(235 91 173)'
                    }
                },
                marcked: {
                    foreground: '#ff0000'
                }
              },
              display: {
                  foreground: 'rgba(255, 255, 255, 0.9)',
                  background: 'rgb(223, 40, 144)'
              },
              picker: {
                  foreground: 'rgb(223, 40, 144)',
                  background: '#020001'
              },
              picker_header: {
                  active: 'rgb(255, 255, 255)',
                  inactive: 'rgba(255, 255, 255, 0.8)'
              },
              weekday: {
                  foreground: 'rgb(255, 255, 255)'
              },
              button: {
                  success: {
                      foreground: 'rgb(255, 255, 255)'
                  },
                  danger: {
                      foreground: 'rgb(223, 40, 144)'
                  }
              }
            },
            disableDates: [...this.appointmentDates],
            dateFormat: 'yyyy-mm-dd',
            minDate: new Date(yyyy, mm, dd),

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
          showCancelButton: true,
          confirmButtonText: 'Yes,I am sure',
          denyButtonText: `Don't make it`,
        }).then((result) => {
          if (result.isConfirmed) {

            axios.post("/api/appointments/add", "tattoer_id=" + 1 + "&date=" + fullDate + "&phone=" + tel
            + "&bodyPart=" + bodyPart + "&tattooSize=" + tattooSize + "&color=" + color)
           .then(response=> Swal.fire('You have an apoinment', '', 'success'))
           .catch(error=> Swal.fire('Upss', 'Something came wrong', 'error'))
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
