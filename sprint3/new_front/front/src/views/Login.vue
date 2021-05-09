<template>
    <div class="row justify-content-md-center">
        <div class="col-sm-3 col-lg-3 col-md-3" style="margin-top: 100px;margin-bottom: 150px">
        <img :src="require('../assets/icon/fg.png')" class="d-block" style="width: 40%;margin-bottom: 10px; left: 30%; position: relative;">
        <input class="form-control me-2" type="text" v-model="loginForm.username" placeholder="Phone number"/>
        <input class="form-control me-2" type="password" v-model="loginForm.password" placeholder="Password"/>
        <router-link to="/password" class="nav-link active text-dark" aria-current="page" style="text-align: center">forget password?</router-link>
        <button @click="login" type="button" class="btn btn-dark text-white mt-2">Login</button>
        <router-link to="/register" class="nav-link active text-dark" aria-current="page" style="text-align: center">no account yet?</router-link>
        </div>
    </div>
</template>

<script>
import { mapMutations } from 'vuex';
import Axios from 'axios';

export default {
    name: "Login",
    data () {
        return {
        loginForm: {
            username: '',
            password: ''
        },
        // userToken: ''
        };
    },
    
    methods: {
        ...mapMutations(['changeLogin']),
        login () {
        let _this = this;
        if (this.loginForm.username === '' || this.loginForm.password === '') {
            alert('Please enter username and password');
        } else {
            Axios.post('http://localhost:8181/login',{
                data: _this.loginForm
            }).then(function(res){
              console.log(res)
              // if(_this.loginForm.username == "py") {
              //   _this.$store.dispatch("userLogin", true, _this.loginForm.username);
              //   localStorage.setItem("Flag", "isLogin");
              //   localStorage.setItem("Uname", _this.loginForm.username)
              //   _this.$router.go(-1)
              // }
                if(res.data != true) {
                  console.log(res.data)
                    alert("Please try again!");
                } else {
                    _this.$store.dispatch("userLogin", true, _this.loginForm.username);
                    localStorage.setItem("Flag", "isLogin");
                    localStorage.setItem("Uname", _this.loginForm.username)
                    alert("Success!");
                    _this.$router.push("/");
                }
            }).catch(function (error) {
                console.log(error);
            });

            // if(this.loginForm.username == "robin" && this.loginForm.password == "123") {
            //     this.$store.dispatch("userLogin", true, this.loginForm.username);
            //     localStorage.setItem("Flag", "isLogin");
            //     localStorage.setItem("Uname", this.loginForm.username)
            //     this.$router.go(-1)
            // }
        }
        }
    }
};
</script>

<style scoped>
    input{
        width: 100%;
    }
    button {
        width: 100%;
    }

    img {
        width: 20%;
    }
</style>

