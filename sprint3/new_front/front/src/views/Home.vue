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
        },
        async fetchMovie(id) {
            const _this=this
            Axios.get('http://localhost:8181/movies/movie_id',{
                params:{
                    mid: id
                }
            }).then(function(res){
                _this.movies = res.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
    },
    async created () {
        this.movies = await this.fetchMovies()
    },
}
</script>
