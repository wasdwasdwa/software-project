<template>
<div>
    <Carousel :movies="movies"/>
    <Movies :movies="movies"/>
</div>
</template>

<script>
import Carousel from '../components/Carousel.vue'
import Movies from '../components/Movies.vue'
import Axios from 'axios'

export default {
    name: 'Home',
    components: {
        Carousel,
        Movies
    },
    data () {
        return {
            movies: []
        }
    },
    methods: {
        async fetchMovies() {
            let re = await Axios.get('http://localhost:8181/movies')
            return re.data
            // const res = await fetch('api/movies')

            // const data = await res.json()

            // return data
        },
        async fetchMovie(id) {
            Axios.get('http://localhost:8181/movies',{
                params:{
                    mid: id
                }
            }).then(function(res){
                this.movies = res.data;
            }).catch(function (error) {
                console.log(error);
            });
            // const res = await fetch(`api/movies/${id}`)
            // const res = await fetch('api/movies',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(id)
            // })

            // const data = await res.json()

            // return data
        },
    },
    async created () {
        this.movies = await this.fetchMovies()
    },
}
</script>
