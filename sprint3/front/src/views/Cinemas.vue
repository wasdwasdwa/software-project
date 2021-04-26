<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a class="navbar-brand" >All Cinemas</a>
            <ul class="nav nav-pills">
                <li class="nav-item">
                <a class="nav-link bg-dark text-light" href="#">More Coming Soon...</a>
                </li>
            </ul>
        </nav>
        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">
            <div class="row justify-content-md-center">
                <div class="col-sm-8 col-lg-8 col-md-8" v-for="cinema in cinemas" :key="cinema.id">
                <Cinema :cinema="cinema" :mid="mid"/>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
import Cinema from '../components/Cinema'
import Axios from 'axios'

export default {
    name: 'Cinemas',
    components: {
        Cinema
    },
    data () {
        return {
            cinemas: [],
            mid: parseInt(this.$route.query.id)
        }
    },
    methods: {
        async fetchCinemas() {
            const _this = this
            Axios.get('http://localhost:8181/cinemas').then(res=>{
                console.log(res.data)
                _this.cinemas = res.data;
            }).catch(error=> {
                console.log(error);
            });
            // const res = await fetch('api/cinemas')

            // const data = await res.json()

            // return data
        },
        async fetchCinemasWithMid(mid) {
            const _this = this
            Axios.get('http://localhost:8181/movieCinemas',{
                params:{
                    mid: mid
                }
            }).then(function(res){
                _this.cinemas = res.data;
            }).catch(function (error) {
                console.log(error);
            });
            // const res = await fetch(`api/movie${mid}Cinemas`)

            // const data = await res.json()

            // return data
        },
    },
    async created () {
        if (this.$route.query.id) {
            this.fetchCinemasWithMid(this.$route.query.id)
        } else {
            this.mid = 0
            this.fetchCinemas()
        }
    },
}
</script>

<style scoped>

</style>
