<template>
    <div class="row justify-content-md-center">
        <div class="col-sm-4 col-lg-4 col-md-4" style="margin-top: 150px;margin-bottom: 150px">
        <input @blur="select()" class="form-control me-2" type="text" v-model="passForm.username" placeholder="Enter your phone number"/>
        <input class="form-control me-2" type="password" v-model="passForm.password" placeholder="Enter your password"/>
        <input class="form-control me-2" type="password" v-model="passForm.repassword" placeholder="Enter your password again"/>
        <label class="form-control me-2">{{selected}}</label>
        <input class="form-control me-2" type="text" v-model="passForm.answer" placeholder="My answer"/>
        <button @click="reset" type="button" class="btn btn-dark text-white mt-2">Reset!</button>
        <router-link to="/login" class="nav-link active text-dark" aria-current="page" style="text-align:center">Back to login</router-link>
        </div>
    </div>
</template>

<script>
import Axios from 'axios';

export default {
    name: "Password",
    data () {
        return {
            passForm: {
                username: '',
                password: '',
                repassword: '',
                answer: ''
            },
            selected: 'Your qustion here...'
        // userToken: ''
        };
    },
    
    methods: {
        select() {
            let _this = this;
            if(_this.passForm.username != '') {
              let username = _this.passForm.username
                Axios.post('http://localhost:8181/login/reset',{
                    data: username
                }).then(function(res){
                    if(res.status != 200) {
                        alert("Something went wrong!");
                    } else {
                        _this.selected = res.data
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        reset () {
            let _this = this;
            if (this.passForm.username === '' || this.passForm.password === '') {
                alert('Please enter username and password');
            } else if(this.passForm.password != this.passForm.repassword) {
                alert("The password should be the same")
            } else if (this.passForm.password.length < 6) {
                alert('Password should at least contains six characters!')
            } else {
                Axios.post('http://localhost:8181/login/changePwd',{
                    data: _this.passForm
                }).then(function(res){
                    if(res.data != "success") {
                      console.log(res);
                        alert("Please try again!");
                    } else {
                        _this.$router.push("/login");
                    }
                }).catch(function (error) {
                    console.log(error);
                });
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
</style>
