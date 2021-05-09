<template>
    <div class="row justify-content-md-center">
        <div class="col-sm-4 col-lg-4 col-md-4" style="margin-top: 150px;margin-bottom: 150px">
        <input class="form-control me-2" type="text" v-model="registerForm.username" placeholder="Enter your phone number"/>
        <input class="form-control me-2" type="password" v-model="registerForm.password" placeholder="Enter your password"/>
        <div id="v-model-select" style="width: 60%;float:left">
            <select v-model="registerForm.selected" class="form-select">
                <option selected value=''>Choose...</option>
                <option>What's your mother's name?</option>
                <option>What's your father's name?</option>
                <option>When is your birthplace?</option>
            </select>
        </div>
        <input style="width: 40%" class="form-control me-2" type="text" v-model="registerForm.answer" placeholder="My answer"/>
        <router-link to="/login" class="nav-link active text-dark" aria-current="page" style="text-align:center">I already have account..</router-link>
        <button @click="create" type="button" class="btn btn-dark text-white mt-2">Create!</button>
        </div>
    </div>
</template>

<script>
import Axios from 'axios';

export default {
    name: "Register",
    data () {
        return {
            registerForm: {
                username: '',
                password: '',
                selected: '',
                answer: ''
            },
        };
    },
    
    methods: {
        create () {
            let _this = this;
            if (this.registerForm.username === '' || this.registerForm.password === '') {
                alert('Please enter username and password');
            } else if(this.registerForm.selected === '' || this.registerForm.answer ===  '') {
                alert("Please select and answer the question for your account safety")
            } else if (this.registerForm.password.length < 6) {
                alert('Password should at least contains six characters!')
            } else {
                let message = _this.registerForm
                Axios.post('http://localhost:8181/register',{
                    data: message
                }).then(function(res){
                    console.log(res)
                    if(res.data != true) {
                        alert("please try again!");
                    } else {
                        alert("success!")
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
