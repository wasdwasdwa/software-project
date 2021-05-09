<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a class="navbar-brand" >My orders..</a>
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <router-link to="/login" class="nav-link active" aria-current="page">Log out</router-link>
                </li>
            </ul>
        </nav>
        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">
            <div class="row justify-content-md-center">
                <div class="col-sm-8 col-lg-8 col-md-8" v-for="order in orders" :key="order.id">
                <myorder :order="order"/>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
import myorder from '../components/myorder'
import Axios from 'axios'

export default {
    name: 'MyOrder',
    components: {
        myorder
    },
    data () {
        return {
            orders: Array,
        }
    },
    methods: {
        async fetchorders() {
            let getName = localStorage.getItem("Uname");
            const _this = this
            Axios.get('http://localhost:8181/order/myorder',{
                params:{
                  data:getName
                }
            }).then(function(res){
                _this.orders = res.data;
            }).catch(function (error) {
                console.log(error);
            });
        }
    },
    async created () {
        this.fetchorders()
    },
}
</script>

<style scoped>

</style>
