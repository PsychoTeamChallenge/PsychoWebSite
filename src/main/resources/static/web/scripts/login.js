Vue.createApp({
    data() {
        return {
            loginEmail: "",
            loginPass: "",

            registerFirstName: "",
            registerLastName: "",
            registerUsername: "",
            registerEmail: "",
            registerPass: "",
        }
    },

    created() {

    },

    methods: {
        login() {
            axios.post("/api/login", "email=" + this.loginEmail + "&password=" + this.loginPass)
                .then(response => window.location.href = "/web/index.html")
                .catch(error => {
                    if(error.response.status == 900){
                        Swal.fire(
                            'Opss!',
                            'Email Address or Password are incorrect',
                            'error'
                        );
                    }
                    else {
                        Swal.fire(
                            'Opss!',
                            'Something came wrong',
                            'error'
                        );
                }
                }
                    )
        },
        register() {
            axios.post("/api/clients", "firstName=" + this.registerFirstName + "&lastName=" + this.registerLastName + "&email=" + this.registerEmail + "&password=" + this.registerPass + "&userName=" + this.registerUsername)
                .then(response => {

                    Swal.fire(
                        'Nice!',
                        'Check your email, validation was sent to you to activate your account!',
                        'success'
                    );
                })
                .catch(error => {
                    if (error.response.data == "Email already in use" || error.response.data == "Username already in use") {
                        Swal.fire(
                            'Opss!',
                            'Email or Username are already in use',
                            'error'
                        );
                    }
                    else {
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

    }

}).mount('#app')



/* --------------- FORM  -----------*/

$('.form').find('input, textarea').on('keyup blur focus', function (e) {

    var $this = $(this),
        label = $this.prev('label');

    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {

        if ($this.val() === '') {
            label.removeClass('highlight');
        }
        else if ($this.val() !== '') {
            label.addClass('highlight');
        }
    }

});

$('.tab a').on('click', function (e) {

    e.preventDefault();

    $(this).parent().addClass('active');
    $(this).parent().siblings().removeClass('active');

    target = $(this).attr('href');

    $('.tab-content > div').not(target).hide();

    $(target).fadeIn(600);

});

